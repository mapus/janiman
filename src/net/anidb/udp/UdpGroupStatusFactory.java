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
import net.anidb.Group;
import net.anidb.GroupStatus;
import net.anidb.GroupStatusState;

/**
 * A factory for {@link GroupStatus} objects.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 26.12.2009
 */
public class UdpGroupStatusFactory {
	/** The logging. */
	private final static Log LOG = LogFactory.getLog(UdpGroupStatusFactory.class);
	
	/** The connection. */
	private UdpConnection conn;
	
	/**
	 * Creates a factory.
	 * @param conn The connection.
	 */
	private UdpGroupStatusFactory(final UdpConnection conn) {
		super();
		this.conn = conn;
	}
	
	/**
	 * Returns an instance of this class.
	 * @param conn The connection.
	 * @return The instance.
	 * @throws IllegalArgumentException If the connection is <code>null</code>.
	 */
	public synchronized static UdpGroupStatusFactory getInstance(
		final UdpConnection conn) {
		
		if (conn == null) {
			throw new IllegalArgumentException("Argument conn is null.");
		}
		return (new UdpGroupStatusFactory(conn));
	}
	
	/**
	 * <p>Returns a list of the group status for the anime with the given anime
	 * Id.</p>
	 * @param animeId The anime Id.
	 * @return The list of the group status. 
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_GROUPS_FOUND
	 * @see UdpReturnCodes#NO_SUCH_ANIME
	 */
	public List<GroupStatus> getGroupStatus(final long animeId)
		throws UdpConnectionException, AniDbException {
		
		UdpRequest request;
		UdpResponse response;
		
		if (!this.conn.isOpen()) {
			throw new UdpConnectionException("Connection is already closed.");
		}
		if (!this.conn.isLoggedIn()) {
			throw new UdpConnectionException("Client is not logged in.");
		}
		request = new UdpRequest("GROUPSTATUS");
		request.addParameter("aid", animeId);
		response = this.conn.communicate(request);
		if (response.getReturnCode() != UdpReturnCodes.GROUP_STATUS) {
			throw new AniDbException(response.getReturnCode(),
					response.getReturnString(), response.getMessageString());
		}
		if (response.getEntryCount() < 1) {
			throw new UdpConnectionException(
					"Received invalid response to the command GROUPSTATUS: "
					+ "The response has less than 1 entry: "
					+ response.toString());
		}
		return this.getGroupStatus(Long.valueOf(animeId), response);
	}
	
	/**
	 * <p>Returns a list of the group status for the anime with the given anime
	 * Id and the given state.</p>
	 * @param animeId The anime Id.
	 * @param state The state.
	 * @return The list of the group status. 
	 * @throws IllegalArgumentException If the state is <code>null</code>.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_GROUPS_FOUND
	 * @see UdpReturnCodes#NO_SUCH_ANIME
	 */
	public List<GroupStatus> getGroupStatus(final long animeId,
		final GroupStatusState state)
		throws UdpConnectionException, AniDbException {
		
		UdpRequest request;
		UdpResponse response;
		
		if (state == null) {
			throw new IllegalArgumentException("Argument state is null.");
		}
		if (!this.conn.isOpen()) {
			throw new UdpConnectionException("Connection is already closed.");
		}
		if (!this.conn.isLoggedIn()) {
			throw new UdpConnectionException("Client is not logged in.");
		}
		request = new UdpRequest("GROUPSTATUS");
		request.addParameter("aid", animeId);
		request.addParameter("state", state.getValue());
		response = this.conn.communicate(request);
		if (response.getReturnCode() != UdpReturnCodes.GROUP_STATUS) {
			throw new AniDbException(response.getReturnCode(),
					response.getReturnString(), response.getMessageString());
		}
		if (response.getEntryCount() < 1) {
			throw new UdpConnectionException(
					"Received invalid response to the command GROUPSTATUS: "
					+ "The response has less than 1 entry: "
					+ response.toString());
		}
		return this.getGroupStatus(Long.valueOf(animeId), response);
	}
	
	/**
	 * Returns the list of the group status from the response.
	 * @param animeId The anime Id.
	 * @param response The response.
	 * @return The list of the group status.
	 * @throws UdpConnectionException If a connection problem occured.
	 */
	private List<GroupStatus> getGroupStatus(final Long animeId,
		final UdpResponse response) throws UdpConnectionException {
		
		Vector<GroupStatus> list;
		UdpResponseEntry entry;
		
		list = new Vector<GroupStatus>();
		for (int index = 0; index < response.getEntryCount(); index++) {
			entry = response.getEntryAt(index);
			list.addElement(this.getGroupStatus(animeId, entry));
		}
		return list;
	}
	
	/**
	 * Returns the group status from the reponse entry.
	 * @param animeId The anime Id.
	 * @param entry The response entry.
	 * @return The group status.
	 * @throws UdpConnectionException If a connection problem occured.
	 */
	private GroupStatus getGroupStatus(final Long animeId,
		final UdpResponseEntry entry) throws UdpConnectionException {
		
		int fieldNumber = 0;
		GroupStatus status;
		Anime anime;
		Group group;
		
		if (entry.getDataFieldCount() < 7) {
			throw new UdpConnectionException(
					"Received invalid response to the command GROUPSTATUS: "
					+ "The entry has less than 7 data fields: "
					+ entry.getMessageString());
		} else if (entry.getDataFieldCount() > 7) {
			LOG.warn("The entry of the response to the command GROUPSTATUS has "
					+ "more than 7 data fields: " + entry.getMessageString());
		}
		anime = new Anime();
		anime.setAnimeId(animeId);
		group = new Group();
		status = new GroupStatus();
		status.setAnime(anime);
		status.setGroup(group);
		try {
			group.setGroupId(entry.getDataFieldAt(fieldNumber++)
					.getValueAsLong());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response entry to the command "
					+ "GROUPSTATUS: " + entry.toString(), dfe);
		}
		group.setName(entry.getDataFieldAt(fieldNumber++).getValue());
		try {
			status.setCompletionState(entry.getDataFieldAt(fieldNumber++)
					.getValueAsInteger());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response entry to the command "
					+ "GROUPSTATUS: " + entry.toString(), dfe);
		}
		try {
			status.setLastEpisodeNumber(entry.getDataFieldAt(fieldNumber++)
					.getValueAsInteger());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response entry to the command "
					+ "GROUPSTATUS: " + entry.toString(), dfe);
		}
		try {
			status.setRating(entry.getDataFieldAt(fieldNumber++)
					.getValueAsInteger());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response entry to the command "
					+ "GROUPSTATUS: " + entry.toString(), dfe);
		}
		try {
			status.setVotes(entry.getDataFieldAt(fieldNumber++)
					.getValueAsInteger());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response entry to the command "
					+ "GROUPSTATUS: " + entry.toString(), dfe);
		}
		status.setEpisodeRanges(entry.getDataFieldAt(fieldNumber++)
				.getValues());
		return status;
	}
}
