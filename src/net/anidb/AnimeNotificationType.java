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
 * The type of the anime notification.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 14.01.2010
 * @see AnimeNotification#getType()
 */
public class AnimeNotificationType {
	/** All. */
	public final static AnimeNotificationType ALL
		= new AnimeNotificationType(0);
	/** New. */
	public final static AnimeNotificationType NEW
		= new AnimeNotificationType(1);
	/** Group. */
	public final static AnimeNotificationType GROUP
		= new AnimeNotificationType(2);
	/** Complete. */
	public final static AnimeNotificationType COMPLETE
		= new AnimeNotificationType(3);
	
	/** The value. */
	private long value;
	
	private AnimeNotificationType(final long value) {
		super();
		this.value = value;
	}
	
	/**
	 * Returns an instance of the class for the given value.
	 * @param value The value.
	 * @return The instance of <code>null</code>, if there is no instance for
	 * the given value.
	 */
	public static AnimeNotificationType getInstance(final int value) {
		if (ALL.value == value) {
			return ALL;
		} else if (NEW.value == value) {
			return NEW;
		} else if (GROUP.value == value) {
			return GROUP;
		} else if (COMPLETE.value == value) {
			return COMPLETE;
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
		AnimeNotificationType type;
		
		if (obj instanceof AnimeNotificationType) {
			type = (AnimeNotificationType) obj;
			if (this.value == type.value) {
				return true;
			}
		}
		return false;
	}
}
