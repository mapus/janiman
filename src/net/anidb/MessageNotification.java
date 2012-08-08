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
package net.anidb;

/**
 * A message notification.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 14.01.2010
 */
public class MessageNotification extends Notification {
	/** The message Id. */
	private long messageId;
	/** The sender user Id. */
	private long fromUserId;
	/** The sender user name. */
	private String fromUserName;
	/** The time of the event. */
	private long date;
	/** The type of the message. */
	private long type;
	/** The title. */
	private String title;
	/** The body. */
	private String body;
	
	/**
	 * Creates a message notification.
	 * @param entry The notification list entry.
	 * @param messageId The message Id.
	 * @param fromUserId The sender user Id.
	 * @param fromUserName The sender user name.
	 * @param date The time of the event.
	 * @param type The type of the message.
	 * @param title The title.
	 * @param body The body.
	 * @throws IllegalArgumentException If the notification list entry is
	 * <code>null</code>.
	 * @throws IllegalArgumentException Of the sender user name is
	 * <code>null</code>.
	 * @throws IllegalArgumentException If the title is <code>null</code>.
	 * @throws IllegalArgumentException If the body is <code>null</code>.
	 */
	public MessageNotification(final NotificationListEntry entry,
			final long messageId, final long fromUserId,
			final String fromUserName, final long date, final long type,
			final String title, final String body) {
		
		super(entry);
		this.messageId = messageId;
		this.fromUserId = fromUserId;
		if (fromUserName == null) {
			throw new IllegalArgumentException(
					"Argument fromUserName is null.");
		}
		this.fromUserName = fromUserName;
		this.date = date;
		this.type = type;
		if (title == null) {
			throw new IllegalArgumentException("Argument title is null.");
		}
		this.title = title;
		if (body == null) {
			throw new IllegalArgumentException("Argument body is null.");
		}
		this.body = body;
	}
	
	/**
	 * Returns the message Id.
	 * @return The message Id.
	 */
	public long getMessageId() {
		return this.messageId;
	}
	
	/**
	 * Returns the sender user Id.
	 * @return The sender user Id.
	 */
	public long getFromUserId() {
		return this.fromUserId;
	}
	
	/**
	 * Returns the sender user name.
	 * @return The sender user name.
	 */
	public String getFromUserName() {
		return this.fromUserName;
	}
	
	/**
	 * Returns the time of the event.
	 * @return The time.
	 */
	public long getDate() {
		return this.date;
	}
	
	/**
	 * Returns the type of the message.
	 * @return The type.
	 */
	public long getType() {
		return this.type;
	}
	
	/**
	 * Returns the title.
	 * @return The title.
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * Returns the body.
	 * @return The body.
	 */
	public String getBody() {
		return this.body;
	}
}
