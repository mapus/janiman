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
 * The file state.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 07.12.2009
 * @see File#getState()
 * @see File#setState(Integer)
 */
public class FileState {
	/** The value. */
	private int value;
	
	private FileState(final int value) {
		super();
		this.value = value;
	}
	
	/**
	 * <p>Returns an instance of the class with the given value.</p>
	 * <p>Only the first 8 bits will be transfered, the rest is
	 * uninteresting.</p>
	 * @param value The value.
	 * @return The instance.
	 */
	public static FileState getInstance(final int value) {
		return (new FileState(value & 255));
	}
	
	/**
	 * Returns the status, if the file matched the official CRC.
	 * @return The status.
	 */
	public boolean isCrcOk() {
		return ((this.value & 1) > 1);
	}
	
	/**
	 * Returns the status, if the file didn't match the official CRC. 
	 * @return The status.
	 */
	public boolean isCrcError() {
		return ((this.value & 2) > 1);
	}
	
	/**
	 * Returns the status, if the file is version 2.
	 * @return The status.
	 */
	public boolean isVersion2() {
		return ((this.value & 4) > 1);
	}
	
	/**
	 * Returns the status, if the file is version 3.
	 * @return The status.
	 */
	public boolean isVersion3() {
		return ((this.value & 8) > 1);
	}
	
	/**
	 * Returns the status, if the file is version 4.
	 * @return The status.
	 */
	public boolean isVersion4() {
		return ((this.value & 16) > 1);
	}
	
	/**
	 * Returns the status, if the file is version 5.
	 * @return The status.
	 */
	public boolean isVersion5() {
		return ((this.value & 32) > 1);
	}
	
	/**
	 * Returns the status, if the file is uncensored.
	 * @return The status.
	 */
	public boolean isUncensored() {
		return ((this.value & 64) > 1);
	}
	
	/**
	 * Returns the status, if the file is censored.
	 * @return The status.
	 */
	public boolean isCensored() {
		return ((this.value & 128) > 1);
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
		FileState state;
		
		if (obj instanceof FileState) {
			state = (FileState) obj;
			if (this.value == state.value) {
				return true;
			}
		}
		return false;
	}
}
