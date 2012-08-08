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
 * The type of a notification.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 13.01.2010
 * @see NotificationListEntry#getType()
 */
public class NotificationType {
	/** Message. */
	public final static NotificationType MESSAGE = new NotificationType("M");
	/** Notification. */
	public final static NotificationType NOTIFICATION
		= new NotificationType("N");
	
	/** The value. */
	private String value;
	
	private NotificationType(final String value) {
		super();
		this.value = value;
	}
	
	/**
	 * Returns an instance of the class for the given value.
	 * @param value The value.
	 * @return The instance of <code>null</code>, if there is no instance for
	 * the given value.
	 */
	public static NotificationType getInstance(final String value) {
		if (MESSAGE.value.equals(value)) {
			return MESSAGE;
		} else if (NOTIFICATION.value.equals(value)) {
			return NOTIFICATION;
		}
		return null;
	}
	
	/**
	 * Returns the value.
	 * @return The value.
	 */
	public String getValue() {
		return this.value;
	}
	
	public int hashCode() {
		int result = 17;
		
		result = 37 * result + this.value.hashCode();
		return result;
	}
	
	public boolean equals(final Object obj) {
		NotificationType type;
		
		if (obj instanceof NotificationType) {
			type = (NotificationType) obj;
			if (this.value.equals(type.value)) {
				return true;
			}
		}
		return false;
	}
}
