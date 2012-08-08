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
 * The type of a vote. 
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 07.12.2009
 */
public class VoteType {
	/** Anime. */
	public final static VoteType ANIME = new VoteType(1);
	/** Anime temporary vote. */
	public final static VoteType ANIME_TMPVOTE = new VoteType(2);
	/** Group. */
	public final static VoteType GROUP = new VoteType(3);
	
	/** The value. */
	private int value;
	
	private VoteType(final int value) {
		super();
		this.value = value;
	}
	
	/**
	 * Returns an instance of the class for the given value.
	 * @param value The value.
	 * @return The instance of <code>null</code>, if there is no instance for
	 * the given value.
	 */
	public static VoteType getInstance(final int value) {
		if (ANIME.value == value) {
			return ANIME;
		} else if (ANIME_TMPVOTE.value == value) {
			return ANIME_TMPVOTE;
		} else if (GROUP.value == value) {
			return GROUP;
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
		VoteType type;
		
		if (obj instanceof VoteType) {
			type = (VoteType) obj;
			if (this.value == type.value) {
				return true;
			}
		}
		return false;
	}
}
