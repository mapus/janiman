/*
 * Java AniDB API - A Java API for AniDB.net
 * (c) Copyright 2010 grizzlyxp
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

import net.anidb.AnimeNotification;
import net.anidb.MessageNotification;
import net.anidb.Notification;
import net.anidb.NotificationListEntry;
import net.anidb.NotificationType;

/**
 * A factory for notifications.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 14.01.2010
 */
public class UdpNotificationFactory {
	/** The logging. */
	private final static Log LOG = LogFactory.getLog(
			UdpNotificationFactory.class);
	
	/** The connection. */
	private UdpConnection conn;
	
	/**
	 * Creates a factory.
	 * @param conn The connection.
	 */
	private UdpNotificationFactory(final UdpConnection conn) {
		super();
		this.conn = conn;
	}
	
	/**
	 * Returns an instance of this class.
	 * @param conn The connection.
	 * @return The instance.
	 * @throws IllegalArgumentException If the connection is <code>null</code>.
	 */
	public synchronized static UdpNotificationFactory getInstance(
		final UdpConnection conn) {
		
		if (conn == null) {
			throw new IllegalArgumentException("Argument conn is null.");
		}
		return (new UdpNotificationFactory(conn));
	}
	
	/**
	 * <p>Returns a list of entries of all pending (not acknowledged) new
	 * private message and new file notifications.</p>
	 * <p>Buddy events cannot be acknowledged.</p> 
	 * @return The list.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 */
	public List<NotificationListEntry> getNotifyList()
		throws UdpConnectionException, AniDbException {
		
		UdpRequest request;
		UdpResponse response;
		Vector<NotificationListEntry> list;
		int count;
		UdpResponseEntry entry;
		String type;
		long id;
		
		if (!this.conn.isOpen()) {
			throw new UdpConnectionException("Connection is already closed.");
		}
		if (!this.conn.isLoggedIn()) {
			throw new UdpConnectionException("Client is not logged in.");
		}
		request = new UdpRequest("NOTIFYLIST");
		response = this.conn.communicate(request);
		if (response.getReturnCode() != UdpReturnCodes.NOTIFYLIST) {
			throw new AniDbException(response.getReturnCode(),
					response.getReturnString(), response.getMessageString());
		}
		list = new Vector<NotificationListEntry>();
		count = response.getEntryCount();
		for (int index = 0; index < count; index++) {
			entry = response.getEntryAt(index);
			if (entry.getDataFieldCount() < 2) {
				throw new UdpConnectionException(
						"Received invalid response to the command NOTIFYLIST: "
						+ "The entry has less than 2 data fields: "
						+ response.toString());
			} else if (entry.getDataFieldCount() > 2) {
				LOG.warn("The entry of the response to the command NOTIFYLIST "
						+ "has more than 2 data fields: "
						+ response.toString());
			}
			type = entry.getDataFieldAt(0).getValue();
			try {
				id = entry.getDataFieldAt(1).getValueAsLongValue();
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response entry to the command "
						+ "NOTIFYLIST: " + response.toString(), dfe);
			}
			list.addElement(new NotificationListEntry(type, id));
		}
		return list;
	}
	
	/**
	 * Returns the notification for the given list entry.
	 * @param listEntry The list entry.
	 * @return The notification.
	 * @throws IllegalArgumentException If the list entry is null.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_MESSAGE
	 * @see UdpReturnCodes#NO_SUCH_NOTIFY
	 */
	public Notification getNotification(final NotificationListEntry listEntry)
			throws UdpConnectionException, AniDbException {
		
		if (listEntry == null) {
			throw new IllegalArgumentException("Argument listEntry is null.");
		}
		return this.getNotification(listEntry.getType(), listEntry.getId());
	}
	
	/**
	 * Returns the notification for the given type and Id.
	 * @param type The type.
	 * @param id The Id.
	 * @return The notification.
	 * @throws IllegalArgumentException If the type is <code>null</code> or the
	 * given type isn't supported.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_MESSAGE
	 * @see UdpReturnCodes#NO_SUCH_NOTIFY
	 */
	public Notification getNotification(final String type, final long id)
			throws UdpConnectionException, AniDbException {
		
		UdpRequest request;
		UdpResponse response;
		NotificationListEntry listEntry;
		
		if (type == null) {
			throw new IllegalArgumentException("Argument type is null.");
		}
		if (!NotificationType.MESSAGE.getValue().equals(type)
				&& !NotificationType.NOTIFICATION.getValue().equals(
						type)) {
			throw new IllegalArgumentException("The notification type '"
					+ type + "' isn't supported.");
		}
		if (!this.conn.isOpen()) {
			throw new UdpConnectionException("Connection is already closed.");
		}
		if (!this.conn.isLoggedIn()) {
			throw new UdpConnectionException("Client is not logged in.");
		}
		request = new UdpRequest("NOTIFYGET");
		request.addParameter("type", type);
		request.addParameter("id", id);
		response = this.conn.communicate(request);
		if ((response.getReturnCode() != UdpReturnCodes.NOTIFYGET_MESSAGE)
				&& (response.getReturnCode()
						!= UdpReturnCodes.NOTIFYGET_NOTIFY)) {
			throw new AniDbException(response.getReturnCode(),
					response.getReturnString(), response.getMessageString());
		}
		if (response.getEntryCount() < 1) {
			throw new UdpConnectionException(
					"Received invalid response to the command NOTIFYGET: "
					+ "The response has less than 1 entry: "
					+ response.toString());
		} else if (response.getEntryCount() > 1) {
			LOG.warn("Response to the command NOTIFYGET has more than 1 entry: "
					+ response.toString());
		}
		listEntry = new NotificationListEntry(type, id);
		if (response.getReturnCode() == UdpReturnCodes.NOTIFYGET_MESSAGE) {
			return this.getMessageNotification(response, listEntry);
		}
		return this.getAnimeNotification(response, listEntry);
	}
	
	// FIXME Test message notification.
	
	/**
	 * Returns a message notification for the given response.
	 * @param response The response.
	 * @param listEntry The notification list entry.
	 * @return The message notification.
	 * @throws UdpConnectionException If a connection problem occured.
	 */
	private MessageNotification getMessageNotification(
			final UdpResponse response, final NotificationListEntry listEntry)
			throws UdpConnectionException {
		
		UdpResponseEntry entry;
		long messageId, fromUserId, date, type;
		String fromUserName, title, body;
		
		entry = response.getEntryAt(0);
		if (entry.getDataFieldCount() < 7) {
			throw new UdpConnectionException(
					"Received invalid response to the command NOTIFYGET: "
					+ "The entry has less than 7 data fields: "
					+ response.toString());
		} else if (entry.getDataFieldCount() > 7) {
			LOG.warn("The entry of the response to the command NOTIFYGET has "
					+ "more than 7 data fields: " + response.toString());
		}
		try {
			messageId = entry.getDataFieldAt(0).getValueAsLongValue();
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response entry to the command "
					+ "NOTIFYGET: " + entry.toString(), dfe);
		}
		try {
			fromUserId = entry.getDataFieldAt(1).getValueAsLongValue();
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response entry to the command "
					+ "NOTIFYGET: " + entry.toString(), dfe);
		}
		fromUserName = entry.getDataFieldAt(2).getValue();
		try {
			date = entry.getDataFieldAt(3).getValueAsLongValue();
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response entry to the command "
					+ "NOTIFYGET: " + entry.toString(), dfe);
		}
		try {
			type = entry.getDataFieldAt(4).getValueAsLongValue();
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response entry to the command "
					+ "NOTIFYGET: " + entry.toString(), dfe);
		}
		title = entry.getDataFieldAt(5).getValue();
		body = entry.getDataFieldAt(6).getValue();
		return (new MessageNotification(listEntry, messageId, fromUserId,
				fromUserName, date, type, title, body));
	}
	
	/**
	 * Returns an anime notification for the given response.
	 * @param response The response.
	 * @param listEntry The notification list entry.
	 * @return The anime notification.
	 * @throws UdpConnectionException If a connection problem occured.
	 */
	private AnimeNotification getAnimeNotification(final UdpResponse response,
			final NotificationListEntry listEntry)
			throws UdpConnectionException {
		
		UdpResponseEntry entry;
		int count;
		long relId, type, date;
		String relIdName;
		List<Long> fileIdList;
		
		entry = response.getEntryAt(0);
		if (entry.getDataFieldCount() < 6) {
			throw new UdpConnectionException(
					"Received invalid response to the command NOTIFYGET: "
					+ "The entry has less than 6 data fields: "
					+ response.toString());
		} else if (entry.getDataFieldCount() > 6) {
			LOG.warn("The entry of the response to the command NOTIFYGET has "
					+ "more than 6 data fields: " + response.toString());
		}
		try {
			relId = entry.getDataFieldAt(0).getValueAsLongValue();
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response entry to the command "
					+ "NOTIFYGET: " + entry.toString(), dfe);
		}
		try {
			type = entry.getDataFieldAt(1).getValueAsLongValue();
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response entry to the command "
					+ "NOTIFYGET: " + entry.toString(), dfe);
		}
		try {
			count = entry.getDataFieldAt(2).getValueAsIntegerValue();
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response entry to the command "
					+ "NOTIFYGET: " + entry.toString(), dfe);
		}
		try {
			date = entry.getDataFieldAt(3).getValueAsLongValue();
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response entry to the command "
					+ "NOTIFYGET: " + entry.toString(), dfe);
		}
		relIdName = entry.getDataFieldAt(4).getValue();
		try {
			fileIdList = entry.getDataFieldAt(5).getValuesAsLong();
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response entry to the command "
					+ "NOTIFYGET: " + entry.toString(), dfe);
		}
		return (new AnimeNotification(listEntry, relId, type, count, date,
				relIdName, fileIdList));
	}
	
	// FIXME Test acknowledge notification methods.
	
	/**
	 * Acknowledges the notification for the given list entry.
	 * @param listEntry The list entry.
	 * @throws IllegalArgumentException If the list entry is null.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_ENTRY_M
	 * @see UdpReturnCodes#NO_SUCH_ENTRY_N
	 */
	public void acknowledgeNotification(final NotificationListEntry listEntry)
			throws UdpConnectionException, AniDbException {
		
		if (listEntry == null) {
			throw new IllegalArgumentException("Argument listEntry is null.");
		}
		this.acknowledgeNotification(listEntry.getType(), listEntry.getId());
	}
	
	/**
	 * Acknowledges the notification for the given type and Id.
	 * @param type The type.
	 * @param id The Id.
	 * @throws IllegalArgumentException If the type is <code>null</code> or the
	 * given type isn't supported.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_ENTRY_M
	 * @see UdpReturnCodes#NO_SUCH_ENTRY_N
	 */
	public void acknowledgeNotification(final String type, final long id)
			throws UdpConnectionException, AniDbException {
		
		UdpRequest request;
		UdpResponse response;
		
		if (type == null) {
			throw new IllegalArgumentException("Argument type is null.");
		}
		if (!NotificationType.MESSAGE.getValue().equals(type)
				&& !NotificationType.NOTIFICATION.getValue().equals(
						type)) {
			throw new IllegalArgumentException("The notification type '"
					+ type + "' isn't supported.");
		}
		if (!this.conn.isOpen()) {
			throw new UdpConnectionException("Connection is already closed.");
		}
		if (!this.conn.isLoggedIn()) {
			throw new UdpConnectionException("Client is not logged in.");
		}
		request = new UdpRequest("NOTIFYACK");
		request.addParameter("type", type);
		request.addParameter("id", id);
		response = this.conn.communicate(request);
		if ((response.getReturnCode() != UdpReturnCodes.NOTIFYACK_SUCCESSFUL_M)
				&& (response.getReturnCode()
						!= UdpReturnCodes.NOTIFYACK_SUCCESSFUL_N)) {
			throw new AniDbException(response.getReturnCode(),
					response.getReturnString(), response.getMessageString());
		}
	}
}
