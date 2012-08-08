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

import net.anidb.Group;

/**
 * A factory for {@link Group} objects.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 26.12.2009
 */
public class UdpGroupFactory {
	/** The logging. */
	private final static Log LOG = LogFactory.getLog(UdpGroupFactory.class);
	
	/** The connection. */
	private UdpConnection conn;
	
	/**
	 * Creates a factory.
	 * @param conn The connection.
	 */
	private UdpGroupFactory(final UdpConnection conn) {
		super();
		this.conn = conn;
	}
	
	/**
	 * Returns an instance of this class.
	 * @param conn The connection.
	 * @return The instance.
	 * @throws IllegalArgumentException If the connection is <code>null</code>.
	 */
	public synchronized static UdpGroupFactory getInstance(
		final UdpConnection conn) {
		
		if (conn == null) {
			throw new IllegalArgumentException("Argument conn is null.");
		}
		return (new UdpGroupFactory(conn));
	}
	
	/**
	 * Returns the group with the given group Id.
	 * @param groupId The group Id.
	 * @return The group.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_GROUP
	 */
	public Group getGroup(final long groupId)
		throws UdpConnectionException, AniDbException {
		
		UdpRequest request;
		UdpResponse response;
		
		if (!this.conn.isOpen()) {
			throw new UdpConnectionException("Connection is already closed.");
		}
		if (!this.conn.isLoggedIn()) {
			throw new UdpConnectionException("Client is not logged in.");
		}
		request = new UdpRequest("GROUP");
		request.addParameter("gid", groupId);
		response = this.conn.communicate(request);
		if (response.getReturnCode() != UdpReturnCodes.GROUP) {
			throw new AniDbException(response.getReturnCode(),
					response.getReturnString(), response.getMessageString());
		}
		if (response.getEntryCount() < 1) {
			throw new UdpConnectionException(
					"Received invalid response to the command GROUP: "
					+ "The response has less than 1 entry: "
					+ response.toString());
		} else if (response.getEntryCount() > 1) {
			LOG.warn("Response to the command GROUP has more than 1 entry: "
					+ response.toString());
		}
		return this.getGroup(response);
	}
	
	/**
	 * Returns the group with the given group name or short name.
	 * @param groupName The group name or short name.
	 * @return The group.
	 * @throws IllegalArgumentException If the group name is <code>null</code>.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_GROUP
	 */
	public Group getGroup(final String groupName)
		throws UdpConnectionException, AniDbException {
		
		UdpRequest request;
		UdpResponse response;
		
		if (groupName == null) {
			throw new IllegalArgumentException("Argument groupName is null.");
		}
		if (!this.conn.isOpen()) {
			throw new UdpConnectionException("Connection is already closed.");
		}
		if (!this.conn.isLoggedIn()) {
			throw new UdpConnectionException("Client is not logged in.");
		}
		request = new UdpRequest("GROUP");
		request.addParameter("gname", groupName);
		response = this.conn.communicate(request);
		if (response.getReturnCode() != UdpReturnCodes.GROUP) {
			throw new AniDbException(response.getReturnCode(),
					response.getReturnString(), response.getMessageString());
		}
		if (response.getEntryCount() < 1) {
			throw new UdpConnectionException(
					"Received invalid response to the command GROUP: "
					+ "The response has less than 1 entry: "
					+ response.toString());
		} else if (response.getEntryCount() > 1) {
			LOG.warn("Response to the command GROUP has more than 1 entry: "
					+ response.toString());
		}
		return this.getGroup(response);
	}
	
	/**
	 * Returns the group from the response.
	 * @param response The response.
	 * @return The group.
	 * @throws UdpConnectionException If a connection problem occured.
	 */
	private Group getGroup(final UdpResponse response)
		throws UdpConnectionException {
		
		UdpResponseEntry entry;
		int fieldNumber = 0;
		Group group;
		
		entry = response.getEntryAt(0);
		if (entry.getDataFieldCount() < 11) {
			throw new UdpConnectionException(
					"Received invalid response to the command GROUP: "
					+ "The entry has less than 11 data fields: "
					+ response.toString());
		} else if (entry.getDataFieldCount() > 11) {
			LOG.warn("The entry of the response to the command GROUP has "
					+ "more than 11 data fields: " + response.toString());
		}
		group = new Group();
		try {
			group.setGroupId(entry.getDataFieldAt(fieldNumber++)
					.getValueAsLong());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command GROUP: "
					+ response.toString(), dfe);
		}
		try {
			group.setRating(entry.getDataFieldAt(fieldNumber++)
					.getValueAsLong());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command GROUP: "
					+ response.toString(), dfe);
		}
		try {
			group.setVotes(entry.getDataFieldAt(fieldNumber++)
					.getValueAsLong());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command GROUP: "
					+ response.toString(), dfe);
		}
		try {
			group.setAnimeCount(entry.getDataFieldAt(fieldNumber++)
					.getValueAsLong());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command GROUP: "
					+ response.toString(), dfe);
		}
		try {
			group.setFileCount(entry.getDataFieldAt(fieldNumber++)
					.getValueAsLong());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command GROUP: "
					+ response.toString(), dfe);
		}
		group.setName(entry.getDataFieldAt(fieldNumber++).getValue());
		group.setShortName(entry.getDataFieldAt(fieldNumber++).getValue());
		group.setIrcChannel(entry.getDataFieldAt(fieldNumber++).getValue());
		group.setIrcServer(entry.getDataFieldAt(fieldNumber++).getValue());
		group.setUrl(entry.getDataFieldAt(fieldNumber++).getValue());
		group.setPicname(entry.getDataFieldAt(fieldNumber++).getValue());
		return group;
	}
}
