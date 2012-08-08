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
 * The file state of a MyList entry.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 07.12.2009
 */
public class MyListFileState {
	/** Normal / original. */
	public final static MyListFileState NORMAL = new MyListFileState(0);
	/** Currupted version / invalid crc. */
	public final static MyListFileState CORRUPTED_VERSION
		= new MyListFileState(1);
	/** Self edited. */
	public final static MyListFileState SELF_EDITED = new MyListFileState(2);
	/** Self ripped. */
	public final static MyListFileState SELF_RIPPED = new MyListFileState(10);
	/** On DVD. */
	public final static MyListFileState ON_DVD = new MyListFileState(11);
	/** On VHS. */
	public final static MyListFileState ON_VHS = new MyListFileState(12);
	/** On TV. */
	public final static MyListFileState ON_TV = new MyListFileState(13);
	/** In theaters. */
	public final static MyListFileState IN_THEATERS = new MyListFileState(14);
	/** Streamed. */
	public final static MyListFileState STREAMED = new MyListFileState(15);
	/** Other. */
	public final static MyListFileState OTHER = new MyListFileState(100);
	
	/** The value. */
	private int value;
	
	private MyListFileState(final int value) {
		super();
		this.value = value;
	}
	
	/**
	 * Returns an instance of the class for the given value.
	 * @param value The value.
	 * @return The instance of <code>null</code>, if there is no instance for
	 * the given value.
	 */
	public static MyListFileState getInstance(final int value) {
		if (NORMAL.value == value) {
			return NORMAL;
		} else if (CORRUPTED_VERSION.value == value) {
			return CORRUPTED_VERSION;
		} else if (SELF_EDITED.value == value) {
			return SELF_EDITED;
		} else if (SELF_RIPPED.value == value) {
			return SELF_RIPPED;
		} else if (ON_DVD.value == value) {
			return ON_DVD;
		} else if (ON_VHS.value == value) {
			return ON_VHS;
		} else if (ON_TV.value == value) {
			return ON_TV;
		} else if (IN_THEATERS.value == value) {
			return IN_THEATERS;
		} else if (STREAMED.value == value) {
			return STREAMED;
		} else if (OTHER.value == value) {
			return OTHER;
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
		MyListFileState state;
		
		if (obj instanceof MyListFileState) {
			state = (MyListFileState) obj;
			if (this.value == state.value) {
				return true;
			}
		}
		return false;
	}
}
