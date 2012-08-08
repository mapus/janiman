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


/**
 * A factory for anime descriptions.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 25.12.2009
 */
public class UdpAnimeDescriptionFactory {
	/** The logging. */
	private final static Log LOG = LogFactory.getLog(
			UdpAnimeDescriptionFactory.class);
	
	/** The connection. */
	private UdpConnection conn;
	
	/**
	 * Creates a factory.
	 * @param conn The connection.
	 */
	private UdpAnimeDescriptionFactory(final UdpConnection conn) {
		super();
		this.conn = conn;
	}
	
	/**
	 * Returns an instance of this class.
	 * @param conn The connection.
	 * @return The instance.
	 * @throws IllegalArgumentException If the connection is <code>null</code>.
	 */
	public synchronized static UdpAnimeDescriptionFactory getInstance(
		final UdpConnection conn) {
		
		if (conn == null) {
			throw new IllegalArgumentException("Argument conn is null.");
		}
		return (new UdpAnimeDescriptionFactory(conn));
	}
	
	/**
	 * <p>Returns the description for the anime with the given Id.</p>
	 * <p>It should be considered that this method has to use more than one
	 * request for retrieving the complete description. The reason is that
	 * a descrption can take up to ~5000 characters while the maximum data of
	 * an UDP packet is ~1400 bytes.</p>
	 * @param animeId The anime Id.
	 * @return The description.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_ANIME
	 * @see UdpReturnCodes#NO_SUCH_ANIME_DESCRIPTION
	 */
	public String getAnimeDescription(final long animeId)
		throws UdpConnectionException, AniDbException {
		
		UdpRequest request;
		UdpResponse response;
		UdpResponseEntry entry;
		StringBuffer description;
		long currentPart, maxParts, index = 0;
		String descriptionPart;
		
		if (!this.conn.isOpen()) {
			throw new UdpConnectionException("Connection is already closed.");
		}
		if (!this.conn.isLoggedIn()) {
			throw new UdpConnectionException("Client is not logged in.");
		}
		description = new StringBuffer();
		do {
			request = new UdpRequest("ANIMEDESC");
			request.addParameter("aid", animeId);
			request.addParameter("part", index);
			response = this.conn.communicate(request);
			if (response.getReturnCode() != UdpReturnCodes.ANIME_DESCRIPTION) {
				throw new AniDbException(response.getReturnCode(),
						response.getReturnString(), response.getMessageString());
			}
			if (response.getEntryCount() < 1) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIMEDESC: "
						+ "The response has less than 1 entry: "
						+ response.toString());
			} else if (response.getEntryCount() > 1) {
				LOG.warn("Response to the command ANIMEDESC has more than 1 "
						+ "entry: " + response.toString());
			}
			entry = response.getEntryAt(0);
			if (entry.getDataFieldCount() < 3) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIMEDESC: "
						+ "The entry has less than 3 data fields: "
						+ response.toString());
			} else if (entry.getDataFieldCount() > 3) {
				LOG.warn("The entry of the response to the command ANIMEDESC "
						+ "has more than 3 data fields: "
						+ response.toString());
			}
			try {
				currentPart = entry.getDataFieldAt(0).getValueAsLongValue();
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIMEDESC: "
						+ response.toString(), dfe);
			}
			if (currentPart != index) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIMEDESC: "
						+ "Requested part of description is different from "
						+ "received part: index = " + index
						+ "; current part = " + currentPart);
			}
			try {
				maxParts = entry.getDataFieldAt(1).getValueAsLongValue();
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIMEDESC: "
						+ response.toString(), dfe);
			}
			descriptionPart = entry.getDataFieldAt(2).getValue();
			if (descriptionPart != null) {
				description.append(descriptionPart);
			}
			index++;
		} while (index < maxParts);
		return description.toString();
	}
}
