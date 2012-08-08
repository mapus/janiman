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
 * The group status state.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 07.12.2009
 * @see GroupStatus#getCompletionState()
 * @see GroupStatus#setCompletionState(Integer)
 */
public class GroupStatusState {
	/** Ongoing. */
	public final static GroupStatusState ONGOING = new GroupStatusState(1);
	/** Stalled. */
	public final static GroupStatusState STALLED = new GroupStatusState(2);
	/** Complete. */
	public final static GroupStatusState COMPLETE = new GroupStatusState(3);
	/** Dropped. */
	public final static GroupStatusState DROPPED = new GroupStatusState(4);
	/** Finished. */
	public final static GroupStatusState FINISHED = new GroupStatusState(5);
	/** Specials only. */
	public final static GroupStatusState SPECIALS_ONLY
		= new GroupStatusState(6);
	
	/** The value. */
	private int value;
	
	private GroupStatusState(final int value) {
		super();
		this.value = value;
	}
	
	/**
	 * Returns an instance of the class for the given value.
	 * @param value The value.
	 * @return The instance of <code>null</code>, if there is no instance for
	 * the given value.
	 */
	public static GroupStatusState getInstance(final int value) {
		if (ONGOING.value == value) {
			return ONGOING;
		} else if (STALLED.value == value) {
			return STALLED;
		} else if (COMPLETE.value == value) {
			return COMPLETE;
		} else if (DROPPED.value == value) {
			return DROPPED;
		} else if (FINISHED.value == value) {
			return FINISHED;
		} else if (SPECIALS_ONLY.value == value) {
			return SPECIALS_ONLY;
		}
		return null;
	}
	
	/**
	 * Returns the value.
	 * @return The value.
	 */
	public int getValue() {
		return this.value;
	}
	
	public int hashCode() {
		int result = 17;
		
		result = 37 * result + this.value;
		return result;
	}
	
	public boolean equals(final Object obj) {
		GroupStatusState state;
		
		if (obj instanceof GroupStatusState) {
			state = (GroupStatusState) obj;
			if (this.value == state.value) {
				return true;
			}
		}
		return false;
	}
}
