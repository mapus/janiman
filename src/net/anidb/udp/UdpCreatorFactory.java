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

import net.anidb.Creator;

/**
 * A factory for {@link Creator} objects.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 28.12.2009
 */
public class UdpCreatorFactory {
	/** The logging. */
	private final static Log LOG = LogFactory.getLog(UdpCreatorFactory.class);
	
	/** The connection. */
	private UdpConnection conn;
	
	/**
	 * Creates a factory.
	 * @param conn The connection.
	 */
	private UdpCreatorFactory(final UdpConnection conn) {
		super();
		this.conn = conn;
	}
	
	/**
	 * Returns an instance of this class.
	 * @param conn The connection.
	 * @return The instance.
	 * @throws IllegalArgumentException If the connection is <code>null</code>.
	 */
	public synchronized static UdpCreatorFactory getInstance(
		final UdpConnection conn) {
		
		if (conn == null) {
			throw new IllegalArgumentException("Argument conn is null.");
		}
		return (new UdpCreatorFactory(conn));
	}
	
	/**
	 * Returns the creator with the given creator Id.
	 * @param creatorId The creator Id.
	 * @return The creator.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_CREATOR
	 */
	public Creator getCreator(final long creatorId)
		throws UdpConnectionException, AniDbException {
		
		UdpRequest request;
		UdpResponse response;
		
		if (!this.conn.isOpen()) {
			throw new UdpConnectionException("Connection is already closed.");
		}
		if (!this.conn.isLoggedIn()) {
			throw new UdpConnectionException("Client is not logged in.");
		}
		request = new UdpRequest("CREATOR");
		request.addParameter("creatorid", creatorId);
		response = this.conn.communicate(request);
		if (response.getReturnCode() != UdpReturnCodes.CREATOR) {
			throw new AniDbException(response.getReturnCode(),
					response.getReturnString(), response.getMessageString());
		}
		if (response.getEntryCount() < 1) {
			throw new UdpConnectionException(
					"Received invalid response to the command CREATOR: "
					+ "The response has less than 1 entry: "
					+ response.toString());
		} else if (response.getEntryCount() > 1) {
			LOG.warn("Response to the command CREATOR has more than 1 "
					+ "entry: " + response.toString());
		}
		return this.getCreator(response);
	}
	
	/**
	 * Returns the creator from the response.
	 * @param response The response.
	 * @return The creator.
	 * @throws UdpConnectionException If a connection problem occured.
	 */
	private Creator getCreator(final UdpResponse response)
		throws UdpConnectionException {
		
		UdpResponseEntry entry;
		int fieldNumber = 0;
		Creator creator;
		
		entry = response.getEntryAt(0);
		if (entry.getDataFieldCount() < 10) {
			throw new UdpConnectionException(
					"Received invalid response to the command CREATOR: "
					+ "The entry has less than 10 data fields: "
					+ response.toString());
		} else if (entry.getDataFieldCount() > 10) {
			LOG.warn("The entry of the response to the command CREATOR has "
					+ "more than 10 data fields: " + response.toString());
		}
		creator = new Creator();
		try {
			creator.setCreatorId(entry.getDataFieldAt(fieldNumber++)
					.getValueAsLong());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command CREATOR: "
					+ response.toString(), dfe);
		}
		creator.setKanjiName(entry.getDataFieldAt(fieldNumber++).getValue());
		creator.setNameTranscription(entry.getDataFieldAt(fieldNumber++)
				.getValue());
		try {
			creator.setType(entry.getDataFieldAt(fieldNumber++)
					.getValueAsInteger());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command CREATOR: "
					+ response.toString(), dfe);
		}
		creator.setPicname(entry.getDataFieldAt(fieldNumber++).getValue());
		creator.setUrlEnglish(entry.getDataFieldAt(fieldNumber++).getValue());
		creator.setUrlJapanese(entry.getDataFieldAt(fieldNumber++).getValue());
		creator.setWikiUrlEnglish(entry.getDataFieldAt(fieldNumber++)
				.getValue());
		creator.setWikiUrlJapanese(entry.getDataFieldAt(fieldNumber++)
				.getValue());
		try {
			creator.setLastUpdateDate(entry.getDataFieldAt(fieldNumber++)
					.getValueAsLong());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command CREATOR: "
					+ response.toString(), dfe);
		}
		return creator;
	}
}
