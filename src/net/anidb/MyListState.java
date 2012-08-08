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
package net.anidb;

/**
 * The state of a MyList entry.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 07.12.2009
 */
public class MyListState {
	/**
	 * <p>Unknown.</p>
	 * <p>The state is unknown or the user doesn't want to provide this
	 * information.</p>
	 **/
	public final static MyListState UNKNOWN = new MyListState(0);
	/**
	 * <p>On hdd.</p>
	 * <p>The file is stored on hdd (but is not shared).</p>
	 */
	public final static MyListState ON_HDD = new MyListState(1);
	/**
	 * <p>On CD.</p>
	 * <p>The file is stored on cd.</p>
	 */
	public final static MyListState ON_CD = new MyListState(2);
	/**
	 * <p>Deleted.</p>
	 * <p>The file has been deleted or is not available for other reasons
	 * (i.e. reencoded).</p>
	 */
	public final static MyListState DELETED = new MyListState(3);
	
	/** The value. */
	private int value;
	
	private MyListState(final int value) {
		super();
		this.value = value;
	}
	
	/**
	 * Returns an instance of the class for the given value.
	 * @param value The value.
	 * @return The instance of <code>null</code>, if there is no instance for
	 * the given value.
	 */
	public static MyListState getInstance(final int value) {
		if (UNKNOWN.value == value) {
			return UNKNOWN;
		} else if (ON_HDD.value == value) {
			return ON_HDD;
		} else if (ON_CD.value == value) {
			return ON_CD;
		} else if (DELETED.value == value) {
			return DELETED;
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
		
		result = 37 * result + this.value;
		return result;
	}
	
	public boolean equals(final Object obj) {
		MyListState state;
		
		if (obj instanceof MyListState) {
			state = (MyListState) obj;
			if (this.value == state.value) {
				return true;
			}
		}
		return false;
	}
}
