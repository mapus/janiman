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
 * A entry in a notify list.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 13.01.2010
 */
public class NotificationListEntry {
	/** The type. */
	private String type;
	/** The notification Id. */
	private long id;
	
	/**
	 * Creates an entry.
	 * @param type The type.
	 * @param id The notification Id.
	 * @throws IllegalArgumentException If the type is <code>null</code>.
	 */
	public NotificationListEntry(final String type, final long id) {
		super();
		if (type == null) {
			throw new IllegalArgumentException("Argument type is null.");
		}
		this.type = type;
		this.id = id;
	}
	
	/**
	 * Returns the type.
	 * @return The type.
	 * @see NotificationType
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * Returns the notification Id.
	 * @return The notification Id.
	 */
	public long getId() {
		return this.id;
	}
	
	public int hashCode() {
		int result = 17;
		
		result = 37 * result + this.type.hashCode();
		result = 37 * result + (int)(this.id ^ (this.id >>> 32));
		return result;
	}
	
	public boolean equals(final Object obj) {
		NotificationListEntry entry;
		
		if (obj instanceof NotificationListEntry) {
			entry = (NotificationListEntry) obj;
			if (entry.type.equals(this.type) && (entry.id == this.id)) {
				return true;
			}
		}
		return false;
	}
}
