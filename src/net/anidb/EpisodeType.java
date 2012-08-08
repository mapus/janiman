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

import java.lang.Character;

/**
 * The type of an episode.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 31.12.2009
 * @see Episode#getEpisodeNumber()
 * @see Episode#setEpisodeNumber(String)
 */
public class EpisodeType {
	/** Special. */
	public final static EpisodeType SPECIAL = new EpisodeType('S');
	/** Credits. */
	public final static EpisodeType CREDITS = new EpisodeType('C');
	/** Trailer. */
	public final static EpisodeType TRAILER = new EpisodeType('T');
	/** Parody. */
	public final static EpisodeType PARODY = new EpisodeType('P');
	/** Other. */
	public final static EpisodeType OTHER = new EpisodeType('O');
	
	/** The value. */
	private char value;
	
	private EpisodeType(final char value) {
		super();
		this.value = value;
	}
	
	/**
	 * Returns an instance of the class for the given value.
	 * @param value The value.
	 * @return The instance of <code>null</code>, if there is no instance for
	 * the given value.
	 */
	public static EpisodeType getInstance(final char value) {
		if (SPECIAL.value == value) {
			return SPECIAL;
		} else if (CREDITS.value == value) {
			return CREDITS;
		} else if (TRAILER.value == value) {
			return TRAILER;
		} else if (PARODY.value == value) {
			return PARODY;
		} else if (OTHER.value == value) {
			return OTHER;
		}
		return null;
	}
	
	/**
	 * Returns an instance of the class for the given episode number.
	 * @param episodeNumber The episode number.
	 * @return The instance of <code>null</code>, if there is no instance for
	 * the episode number or the episode number is <code>null</code> or an empty
	 * {@link String}.
	 */
	public static EpisodeType getInstance(final String episodeNumber) {
		if ((episodeNumber == null) || (episodeNumber.length() == 0)) {
			return null;
		}
		return getInstance(Character.toUpperCase(episodeNumber.charAt(0)));
	}
	
	/**
	 * Returns the value.
	 * @return The value.
	 */
	public char getValue() {
		return this.value;
	}
	
	public int hashCode() {
		int result = 17;
		
		result = 37 * result + this.value;
		return result;
	}
	
	public boolean equals(final Object obj) {
		EpisodeType type;
		
		if (obj instanceof EpisodeType) {
			type = (EpisodeType) obj;
			if (this.value == type.value) {
				return true;
			}
		}
		return false;
	}
}
