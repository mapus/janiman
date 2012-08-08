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

import java.util.List;
import java.util.Vector;

/**
 * An anime notification.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 14.01.2010
 */
public class AnimeNotification extends Notification {
	/** The id of the related anime. */
	private long relId;
	/** The type of the notification. */
	private long type;
	/** The number of events pending for this subscription. */
	private int count;
	/** The time of the event. */
	private long date;
	/** The name of the related anime. */
	private String relIdName;
	/** The list of the affected file Ids. */
	private List<Long> fileIdList;
	
	/**
	 * Creates an anime notification.
	 * @param entry The notification list entry.
	 * @param relId The id of the related anime.
	 * @param type The type of the notification.
	 * @param count The number of events pending for this subscription.
	 * @param date The time of the event.
	 * @param relIdName The name of the related anime.
	 * @param fileIdList The list of the affected file Ids.
	 * @throws IllegalArgumentException If the notification list entry is
	 * <code>null</code>.
	 * @throws IllegalArgumentException If the name is <code>null</code>.
	 * @throws IllegalArgumentException If the list is <code>null</code>.
	 */
	public AnimeNotification(final NotificationListEntry entry,
			final long relId, final long type, final int count, final long date,
			final String relIdName, final List<Long> fileIdList) {
		
		super(entry);
		this.relId = relId;
		this.type = type;
		this.count = count;
		this.date = date;
		if (relIdName == null) {
			throw new IllegalArgumentException("Argument relIdName is null.");
		}
		this.relIdName = relIdName;
		if (fileIdList == null) {
			throw new IllegalArgumentException("Argument fileIdList is null.");
		}
		this.fileIdList = new Vector<Long>(fileIdList);
	}
	
	/**
	 * Returns the id of the related anime.
	 * @return The id.
	 */
	public long getRelId() {
		return this.relId;
	}
	
	/**
	 * Returns the type of the notification.
	 * @return The type.
	 * @see AnimeNotificationType
	 */
	public long getType() {
		return this.type;
	}
	
	/**
	 * Returns the number of events pending for this subscription.
	 * @return The number.
	 */
	public int getCount() {
		return this.count;
	}
	
	/**
	 * Returns the time of the event.
	 * @return The time.
	 */
	public long getDate() {
		return this.date;
	}
	
	/**
	 * Returns the name of the related anime.
	 * @return The name.
	 */
	public String getRelIdName() {
		return this.relIdName;
	}
	
	/**
	 * Returns the list of the affected file Ids.
	 * @return The list.
	 */
	public List<Long> getFileIdList() {
		return (new Vector<Long>(this.fileIdList));
	}
}
