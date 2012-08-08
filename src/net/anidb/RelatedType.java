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
 * The related type.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 07.12.2009
 * @see Anime#getRelatedAidType()
 * @see Anime#setRelatedAidType(java.util.List)
 */
public class RelatedType {
	/** Sequal. */
	public final static RelatedType SEQUAL = new RelatedType(1);
	/** Prequel. */
	public final static RelatedType PREQUEL = new RelatedType(2);
	/** Same setting. */
	public final static RelatedType SAME_SETTING = new RelatedType(11);
	/** Alternative setting. */
	public final static RelatedType ALTERNATIVE_SETTING = new RelatedType(21);
	/** Alternative version. */
	public final static RelatedType ALTERNATIVE_VERSION = new RelatedType(32);
	/** Music video. */
	public final static RelatedType MUSIC_VIDEO = new RelatedType(41);
	/** Character. */
	public final static RelatedType CHARACTER = new RelatedType(42);
	/** Side story. */
	public final static RelatedType SIDE_STORY = new RelatedType(51);
	/** Parent story. */
	public final static RelatedType PARENT_STORY = new RelatedType(52);
	/** Summary. */
	public final static RelatedType SUMMARY = new RelatedType(61);
	/** Full story. */
	public final static RelatedType FULL_STORY = new RelatedType(62);
	/** Other. */
	public final static RelatedType OTHER = new RelatedType(100);
	
	/** The value. */
	private long value;
	
	private RelatedType(final long value) {
		super();
		this.value = value;
	}
	
	/**
	 * Returns an instance of the class for the given value.
	 * @param value The value.
	 * @return The instance of <code>null</code>, if there is no instance for
	 * the given value.
	 */
	public static RelatedType getInstance(final long value) {
		if (SEQUAL.value == value) {
			return SEQUAL;
		} else if (PREQUEL.value == value) {
			return PREQUEL;
		} else if (SAME_SETTING.value == value) {
			return SAME_SETTING;
		} else if (ALTERNATIVE_SETTING.value == value) {
			return ALTERNATIVE_SETTING;
		} else if (ALTERNATIVE_VERSION.value == value) {
			return ALTERNATIVE_VERSION;
		} else if (MUSIC_VIDEO.value == value) {
			return MUSIC_VIDEO;
		} else if (CHARACTER.value == value) {
			return CHARACTER;
		} else if (SIDE_STORY.value == value) {
			return SIDE_STORY;
		} else if (PARENT_STORY.value == value) {
			return PARENT_STORY;
		} else if (SUMMARY.value == value) {
			return SUMMARY;
		} else if (FULL_STORY.value == value) {
			return FULL_STORY;
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
		
		result = 37 * result + (int)(this.value ^ (this.value >>> 32));
		return result;
	}
	
	public boolean equals(final Object obj) {
		RelatedType type;
		
		if (obj instanceof RelatedType) {
			type = (RelatedType) obj;
			if (this.value == type.value) {
				return true;
			}
		}
		return false;
	}
}
