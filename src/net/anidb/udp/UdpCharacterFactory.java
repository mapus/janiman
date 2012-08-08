/*
 * Java AniDB API - A Java API for AniDB.net
 * (c) Copyright 2009 grizzlyxp
 * http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package net.anidb.udp;

import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.anidb.Anime;
import net.anidb.AnimeCharacter;
import net.anidb.Character;
import net.anidb.Creator;

/**
 * A factory for {@link Character} objects.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 28.12.2009
 */
public class UdpCharacterFactory {
	/** The logging. */
	private final static Log LOG = LogFactory.getLog(UdpCharacterFactory.class);
	
	/** The connection. */
	private UdpConnection conn;
	
	/**
	 * Creates a factory.
	 * @param conn The connection.
	 */
	private UdpCharacterFactory(final UdpConnection conn) {
		super();
		this.conn = conn;
	}
	
	/**
	 * Returns an instance of this class.
	 * @param conn The connection.
	 * @return The instance.
	 * @throws IllegalArgumentException If the connection is <code>null</code>.
	 */
	public synchronized static UdpCharacterFactory getInstance(
		final UdpConnection conn) {
		
		if (conn == null) {
			throw new IllegalArgumentException("Argument conn is null.");
		}
		return (new UdpCharacterFactory(conn));
	}
	
	/**
	 * Returns the character with the given character Id.
	 * @param characterId The character Id.
	 * @return The character.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_CHARACTER
	 */
	public Character getCharacter(final long characterId)
		throws UdpConnectionException, AniDbException {
		
		UdpRequest request;
		UdpResponse response;
		
		if (!this.conn.isOpen()) {
			throw new UdpConnectionException("Connection is already closed.");
		}
		if (!this.conn.isLoggedIn()) {
			throw new UdpConnectionException("Client is not logged in.");
		}
		request = new UdpRequest("CHARACTER");
		request.addParameter("charid", characterId);
		response = this.conn.communicate(request);
		if (response.getReturnCode() != UdpReturnCodes.CHARACTER) {
			throw new AniDbException(response.getReturnCode(),
					response.getReturnString(), response.getMessageString());
		}
		if (response.getEntryCount() < 1) {
			throw new UdpConnectionException(
					"Received invalid response to the command CHARACTER: "
					+ "The response has less than 1 entry: "
					+ response.toString());
		} else if (response.getEntryCount() > 1) {
			LOG.warn("Response to the command CHARACTER has more than 1 "
					+ "entry: " + response.toString());
		}
		return this.getCharacter(response);
	}
	
	/**
	 * Returns the character from the response.
	 * @param response The response.
	 * @return The character.
	 * @throws UdpConnectionException If a connection problem occured.
	 */
	private Character getCharacter(final UdpResponse response)
		throws UdpConnectionException {
		
		UdpResponseEntry entry;
		int fieldNumber = 0;
		Character character;
		
		entry = response.getEntryAt(0);
		if (entry.getDataFieldCount() < 7) {
			throw new UdpConnectionException(
					"Received invalid response to the command CHARACTER: "
					+ "The entry has less than 7 data fields: "
					+ response.toString());
		} else if (entry.getDataFieldCount() > 7) {
			LOG.warn("The entry of the response to the command CHARACTER has "
					+ "more than 7 data fields: " + response.toString());
		}
		character = new Character();
		try {
			character.setCharacterId(entry.getDataFieldAt(fieldNumber++)
					.getValueAsLong());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command CHARACTER: "
					+ response.toString(), dfe);
		}
		character.setKanjiName(entry.getDataFieldAt(fieldNumber++).getValue());
		character.setNameTranscription(entry.getDataFieldAt(fieldNumber++)
				.getValue());
		character.setPicname(entry.getDataFieldAt(fieldNumber++).getValue());
		try {
			character.setAnimeList(this.getAnimeList(entry.getDataFieldAt(
					fieldNumber++)));
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command CHARACTER: "
					+ response.toString(), dfe);
		}
		try {
			character.setEpisodeList(entry.getDataFieldAt(fieldNumber++)
					.getValuesAsInteger());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command CHARACTER: "
					+ response.toString(), dfe);
		}
		try {
			character.setLastUpdateDate(entry.getDataFieldAt(fieldNumber++)
					.getValueAsLong());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command EPISODE: "
					+ response.toString(), dfe);
		}
		// int Type: Character/Mecha/Organization/Vessel map to 1/2/3/4
		// String Gender: Unknown/Male/Female/None/Dimorphic/Intersexual map to ?/M/F/-/D/I
		return character;
		// FIXME Implement additional fields.
	}
	
	/**
	 * Returns the anime list from the data field. 
	 * @param dataField The data field.
	 * @return The anime list.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws DataFieldException If the data field does not contain parsable
	 * values.
	 */
	private List<AnimeCharacter> getAnimeList(final DataField dataField)
		throws UdpConnectionException, DataFieldException {
		
		Vector<AnimeCharacter> animeList;
		String value;
		AnimeCharacter animeChar;
		int count;
		
		value = dataField.getValue();
		if ((value == null) || (value.length() == 0)) {
			return null;
		}
		animeList = new Vector<AnimeCharacter>();
		count = dataField.getDataFieldCount();
		if (count > 0) {
			for (int index = 0; index < count; index++) {
				animeChar = this.getAnime(dataField.getDataFieldAt(index));
				animeList.addElement(animeChar);
			}
		} else {
			animeChar = this.getAnime(dataField);
			animeList.addElement(animeChar);
		}
		return animeList;
	}
	
	/**
	 * Returns the appearance of a character from the data field. 
	 * @param dataField The data field.
	 * @return The appearance of a character.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws DataFieldException If the data field does not contain parsable
	 * values.
	 */
	private AnimeCharacter getAnime(final DataField dataField)
		throws UdpConnectionException, DataFieldException {
		
		List<String> values;
		AnimeCharacter animeChar;
		Anime anime;
		Creator creator;
		int boolValue;
		
		values = dataField.getValues();
		if (values == null) {
			throw new UdpConnectionException(
					"Received invalid response to the command CHARACTER: "
					+ "Anime block should have at least 4 fields, but has "
					+ "none: " + dataField.getValue());
		} else if (values.size() < 4) {
			throw new UdpConnectionException(
				"Received invalid response to the command CHARACTER: "
				+ "Anime block should have 4 fields, but has less: "
				+ dataField.getValue());
		} else if (values.size() > 4) {
			LOG.warn("The anime block of the response to the command "
					+ "CHARACTER has more than 4 fields: "
					+ dataField.getValue());
		}
		anime = new Anime();
		creator = new Creator();
		animeChar = new AnimeCharacter();
		animeChar.setAnime(anime);
		animeChar.setCreator(creator);
		try {
			anime.setAnimeId(Long.valueOf(Long.parseLong(values.get(0))));
		} catch (NumberFormatException nfe) {
			throw new DataFieldException(
					"Couldn't parse value into a long. Value: " + values.get(0),
					nfe);
		}
		try {
			animeChar.setType(Integer.valueOf(Integer.parseInt(values.get(1))));
		} catch (NumberFormatException nfe) {
			throw new DataFieldException(
					"Couldn't parse value into an integer. Value: "
					+ values.get(1), nfe);
		}
		try {
			creator.setCreatorId(Long.valueOf(Long.parseLong(values.get(2))));
		} catch (NumberFormatException nfe) {
			throw new DataFieldException(
					"Couldn't parse value into a long. Value: " + values.get(2),
					nfe);
		}
		try {
			boolValue = Integer.parseInt(values.get(3));
		} catch (NumberFormatException nfe) {
			throw new DataFieldException(
					"Couldn't parse value into an integer. Value: "
					+ values.get(3), nfe);
		}
		if (boolValue == 1) {
			animeChar.setMainSeiyuu(Boolean.TRUE);
		} else if (boolValue == 0) {
			animeChar.setMainSeiyuu(Boolean.FALSE);
		} else {
			throw new DataFieldException("Expected values for a boolean "
					+ "value: 0 or 1; value: " + values.get(3));
		}
		// TODO Read anime properties from second level cache.
		return animeChar;
	}
}
