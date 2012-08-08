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

import net.anidb.util.ObjectKit;

/**
 * An appearance of a character in an anime.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 25.12.2009
 * @see Anime
 * @see Character
 */
public class AnimeCharacter {
	/** The anime. */
	private Anime anime;
	/** The type. */
	private Integer type;
	/** The creator. */
	private Creator creator;
	/** The 'is main seiyuu' flag. */
	private Boolean mainSeiyuu;
	
	/**
	 * Creates an anime character.
	 */
	public AnimeCharacter() {
		super();
	}
	
	/**
	 * Creates an anime character.
	 * @param anime The anime.
	 * @param type The type.
	 * @param creator The creator.
	 * @param mainSeiyuu The 'is main seiyuu' flag.
	 */
	public AnimeCharacter(final Anime anime, final Integer type,
		final Creator creator, final Boolean mainSeiyuu) {
		
		super();
		this.anime = anime;
		this.type = type;
		this.creator = creator;
		this.mainSeiyuu = mainSeiyuu;
	}
	
	/**
	 * Returns the anime.
	 * @return The anime or <code>null</code>, if the anime isn't set.
	 */
	public Anime getAnime() {
		return this.anime;
	}
	
	/**
	 * Sets the anime.
	 * @param anime The anime.
	 */
	public void setAnime(final Anime anime) {
		this.anime = anime;
	}
	
	/**
	 * Returns the type.
	 * @return The type or <code>null</code>, if the type isn't set.
	 * @see AnimeCharacterType
	 */
	public Integer getType() {
		return this.type;
	}
	
	/**
	 * Sets the type.
	 * @param type The type.
	 * @see AnimeCharacterType
	 */
	public void setType(final Integer type) {
		this.type = type;
	}
	
	/**
	 * Returns the creator.
	 * @return The creator or <code>null</code>, if the creator isn't set.
	 */
	public Creator getCreator() {
		return this.creator;
	}
	
	/**
	 * Sets the creator.
	 * @param creator The creator.
	 */
	public void setCreator(final Creator creator) {
		this.creator = creator;
	}
	
	/**
	 * Returns the 'is main seiyuu' flag.
	 * @return The flag or <code>null</code>, if the flag isn't set.
	 */
	public Boolean getMainSeiyuu() {
		return this.mainSeiyuu;
	}
	
	/**
	 * Sets the 'is main seiyuu' flag.
	 * @param mainSeiyuu The flag.
	 */
	public void setMainSeiyuu(final Boolean mainSeiyuu) {
		this.mainSeiyuu = mainSeiyuu;
	}
	
	public int hashCode() {
		int result = 17;
		
		result = ObjectKit.hash(this.anime, result);
		result = ObjectKit.hash(this.type, result);
		result = ObjectKit.hash(this.creator, result);
		result = ObjectKit.hash(this.mainSeiyuu, result);
		return result;
	}
	
	public boolean equals(final Object obj) {
		AnimeCharacter animeChar;
		
		if (obj instanceof AnimeCharacter) {
			animeChar = (AnimeCharacter) obj;
			if (ObjectKit.equals(animeChar.anime, this.anime)
					&& ObjectKit.equals(animeChar.type, this.type)
					&& ObjectKit.equals(animeChar.creator, this.creator)
					&& ObjectKit.equals(animeChar.mainSeiyuu,
							this.mainSeiyuu)) {
				return true;
			}
		}
		return false;
	}
}
