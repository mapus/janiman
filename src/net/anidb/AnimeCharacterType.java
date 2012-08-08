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
 * The type of the character in a specific anime.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 25.12.2009
 * @see AnimeCharacter#getType()
 * @see AnimeCharacter#setType(Integer)
 */
public class AnimeCharacterType {
	/** Appears in. */
	public final static AnimeCharacterType APPEARS_IN
		= new AnimeCharacterType(0);
	/** Cameo appearance in. */
	public final static AnimeCharacterType CAMEO_APPEARANCE_IN
		= new AnimeCharacterType(1);
	/** Main character in. */
	public final static AnimeCharacterType MAIN_CHARACTER_IN
		= new AnimeCharacterType(2);
	
	/** The value. */
	private int value;
	
	private AnimeCharacterType(final int value) {
		super();
		this.value = value;
	}
	
	/**
	 * Returns an instance of the class for the given value.
	 * @param value The value.
	 * @return The instance of <code>null</code>, if there is no instance for
	 * the given value.
	 */
	public static AnimeCharacterType getInstance(final int value) {
		if (APPEARS_IN.value == value) {
			return APPEARS_IN;
		} else if (CAMEO_APPEARANCE_IN.value == value) {
			return CAMEO_APPEARANCE_IN;
		} else if (MAIN_CHARACTER_IN.value == value) {
			return MAIN_CHARACTER_IN;
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
		AnimeCharacterType type;
		
		if (obj instanceof AnimeCharacterType) {
			type = (AnimeCharacterType) obj;
			if (this.value == type.value) {
				return true;
			}
		}
		return false;
	}
}
