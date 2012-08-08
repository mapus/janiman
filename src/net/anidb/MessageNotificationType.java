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
 * The type of the message.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 14.01.2010
 * @see MessageNotification#getType()
 */
public class MessageNotificationType {
	/** Normal message. */
	public final static MessageNotificationType NORMAL_MESSAGE
		= new MessageNotificationType(0);
	/** Annonymous. */
	public final static MessageNotificationType ANNONYMOUS
		= new MessageNotificationType(1);
	/** System message. */
	public final static MessageNotificationType SYSTEM_MESSAGE
		= new MessageNotificationType(2);
	/** Mod message. */
	public final static MessageNotificationType MOD_MESSAGE
		= new MessageNotificationType(3);
	
	/** The value. */
	private long value;
	
	private MessageNotificationType(final long value) {
		super();
		this.value = value;
	}
	
	/**
	 * Returns an instance of the class for the given value.
	 * @param value The value.
	 * @return The instance of <code>null</code>, if there is no instance for
	 * the given value.
	 */
	public static MessageNotificationType getInstance(final int value) {
		if (NORMAL_MESSAGE.value == value) {
			return NORMAL_MESSAGE;
		} else if (ANNONYMOUS.value == value) {
			return ANNONYMOUS;
		} else if (SYSTEM_MESSAGE.value == value) {
			return SYSTEM_MESSAGE;
		} else if (MOD_MESSAGE.value == value) {
			return MOD_MESSAGE;
		}
		return null;
	}
	
	/**
	 * Returns the value.
	 * @return The value.
	 */
	public long getValue() {
		return this.value;
	}
	
	public int hashCode() {
		int result = 17;
		
		result = 37 * result + (int)(this.value ^ (this.value >>> 32));
		return result;
	}
	
	public boolean equals(final Object obj) {
		MessageNotificationType type;
		
		if (obj instanceof MessageNotificationType) {
			type = (MessageNotificationType) obj;
			if (this.value == type.value) {
				return true;
			}
		}
		return false;
	}
}
