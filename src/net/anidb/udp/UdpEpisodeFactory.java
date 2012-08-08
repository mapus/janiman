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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.anidb.Anime;
import net.anidb.Episode;

/**
 * A factory for {@link Episode} objects.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 25.12.2009
 */
public class UdpEpisodeFactory {
	/** The logging. */
	private final static Log LOG = LogFactory.getLog(UdpEpisodeFactory.class);
	
	/** The connection. */
	private UdpConnection conn;
	
	/**
	 * Creates a factory.
	 * @param conn The connection.
	 */
	private UdpEpisodeFactory(final UdpConnection conn) {
		super();
		this.conn = conn;
	}
	
	/**
	 * Returns an instance of this class.
	 * @param conn The connection.
	 * @return The instance.
	 * @throws IllegalArgumentException If the connection is <code>null</code>.
	 */
	public synchronized static UdpEpisodeFactory getInstance(
		final UdpConnection conn) {
		
		if (conn == null) {
			throw new IllegalArgumentException("Argument conn is null.");
		}
		return (new UdpEpisodeFactory(conn));
	}
	
	/**
	 * Returns the episode with the given Id.
	 * @param episodeId The episode Id.
	 * @return The episode.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_EPISODE
	 */
	public Episode getEpisode(final long episodeId)
		throws UdpConnectionException, AniDbException {
		
		UdpRequest request;
		UdpResponse response;
		
		if (!this.conn.isOpen()) {
			throw new UdpConnectionException("Connection is already closed.");
		}
		if (!this.conn.isLoggedIn()) {
			throw new UdpConnectionException("Client is not logged in.");
		}
		request = new UdpRequest("EPISODE");
		request.addParameter("eid", episodeId);
		response = this.conn.communicate(request);
		if (response.getReturnCode() != UdpReturnCodes.EPISODE) {
			throw new AniDbException(response.getReturnCode(),
					response.getReturnString(), response.getMessageString());
		}
		if (response.getEntryCount() < 1) {
			throw new UdpConnectionException(
					"Received invalid response to the command EPISODE: "
					+ "The response has less than 1 entry: "
					+ response.toString());
		} else if (response.getEntryCount() > 1) {
			LOG.warn("Response to the command EPISODE has more than 1 "
					+ "entry: " + response.toString());
		}
		return this.getEpisode(response);
	}
	
	/**
	 * Returns the episode with the given anime name and episode number.
	 * @param animeName The anime name.
	 * @param episodeNumber The episode number.
	 * @return The episode.
	 * @throws IllegalArgumentException If the anime name is <code>null</code>.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_EPISODE
	 */
	public Episode getEpisode(final String animeName,
		final long episodeNumber)
		throws UdpConnectionException, AniDbException {
		
		UdpRequest request;
		UdpResponse response;
		
		if (animeName == null) {
			throw new IllegalArgumentException("Argument animeName is null.");
		}
		if (!this.conn.isOpen()) {
			throw new UdpConnectionException("Connection is already closed.");
		}
		if (!this.conn.isLoggedIn()) {
			throw new UdpConnectionException("Client is not logged in.");
		}
		request = new UdpRequest("EPISODE");
		request.addParameter("aname", animeName);
		request.addParameter("epno", episodeNumber);
		response = this.conn.communicate(request);
		if (response.getReturnCode() != UdpReturnCodes.EPISODE) {
			throw new AniDbException(response.getReturnCode(),
					response.getReturnString(), response.getMessageString());
		}
		if (response.getEntryCount() < 1) {
			throw new UdpConnectionException(
					"Received invalid response to the command EPISODE: "
					+ "The response has less than 1 entry: "
					+ response.toString());
		} else if (response.getEntryCount() > 1) {
			LOG.warn("Response to the command EPISODE has more than 1 "
					+ "entry: " + response.toString());
		}
		return this.getEpisode(response);
	}
	
	/**
	 * Returns the episode with the given anime Id and episode number.
	 * @param animeId The anime Id.
	 * @param episodeNumber The episode number.
	 * @return The episode.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_EPISODE
	 */
	public Episode getEpisode(final long animeId, final long episodeNumber)
		throws UdpConnectionException, AniDbException {
		
		UdpRequest request;
		UdpResponse response;
		
		if (!this.conn.isOpen()) {
			throw new UdpConnectionException("Connection is already closed.");
		}
		if (!this.conn.isLoggedIn()) {
			throw new UdpConnectionException("Client is not logged in.");
		}
		request = new UdpRequest("EPISODE");
		request.addParameter("aid", animeId);
		request.addParameter("epno", episodeNumber);
		response = this.conn.communicate(request);
		if (response.getReturnCode() != UdpReturnCodes.EPISODE) {
			throw new AniDbException(response.getReturnCode(),
					response.getReturnString(), response.getMessageString());
		}
		if (response.getEntryCount() < 1) {
			throw new UdpConnectionException(
					"Received invalid response to the command EPISODE: "
					+ "The response has less than 1 entry: "
					+ response.toString());
		} else if (response.getEntryCount() > 1) {
			LOG.warn("Response to the command EPISODE has more than 1 "
					+ "entry: " + response.toString());
		}
		return this.getEpisode(response);
	}
	
	/**
	 * Returns the episode from the response.
	 * @param response The response.
	 * @return The episode.
	 * @throws UdpConnectionException If a connection problem occured.
	 */
	private Episode getEpisode(final UdpResponse response)
		throws UdpConnectionException {
		
		UdpResponseEntry entry;
		int fieldNumber = 0;
		Anime anime;
		Episode episode;
		
		entry = response.getEntryAt(0);
		if (entry.getDataFieldCount() < 10) {
			throw new UdpConnectionException(
					"Received invalid response to the command EPISODE: "
					+ "The entry has less than 10 data fields: "
					+ response.toString());
		} else if (entry.getDataFieldCount() > 10) {
			LOG.warn("The entry of the response to the command EPISODE has "
					+ "more than 10 data fields: " + response.toString());
		}
		anime = new Anime();
		episode = new Episode();
		episode.setAnime(anime);
		try {
			episode.setEpisodeId(entry.getDataFieldAt(fieldNumber++)
					.getValueAsLong());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command EPISODE: "
					+ response.toString(), dfe);
		}
		try {
			anime.setAnimeId(entry.getDataFieldAt(fieldNumber++)
					.getValueAsLong());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command EPISODE: "
					+ response.toString(), dfe);
		}
		try {
			episode.setLength(entry.getDataFieldAt(fieldNumber++)
					.getValueAsLong());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command EPISODE: "
					+ response.toString(), dfe);
		}
		try {
			episode.setRating(entry.getDataFieldAt(fieldNumber++)
					.getValueAsLong());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command EPISODE: "
					+ response.toString(), dfe);
		}
		try {
			episode.setVotes(entry.getDataFieldAt(fieldNumber++)
					.getValueAsLong());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command EPISODE: "
					+ response.toString(), dfe);
		}
		episode.setEpisodeNumber(entry.getDataFieldAt(fieldNumber++)
				.getValue());
		episode.setEnglishTitle(entry.getDataFieldAt(fieldNumber++).getValue());
		episode.setRomajiTitle(entry.getDataFieldAt(fieldNumber++).getValue());
		episode.setKanjiTitle(entry.getDataFieldAt(fieldNumber++).getValue());
		try {
			episode.setAired(entry.getDataFieldAt(fieldNumber++)
					.getValueAsLong());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command EPISODE: "
					+ response.toString(), dfe);
		}
		// TODO Read anime properties from second level cache.
		return episode;
	}
}
