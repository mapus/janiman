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

import java.util.List;
import java.util.Vector;

import net.anidb.util.ObjectKit;

/**
 * An anime character.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 19.12.2009
 */
public class Character {
	/** The character Id. */
	private Long characterId;
	/** The kanji name. */
	private String kanjiName;
	/** The name transcription. */
	private String nameTranscription;
	/** The picname. */
	private String picname;
	/** The anime list. */
	private List<AnimeCharacter> animeList;
	/** The episode list. */
	private List<Integer> episodeList;
	/** The date of the last update. */
	private Long lastUpdateDate;
	
	/**
	 * Creates a character.
	 */
	public Character() {
		super();
	}
	
	/**
	 * Creates a character.
	 * @param characterId The character Id.
	 * @param kanjiName The kanji name.
	 * @param nameTranscription The name transcription.
	 * @param picname The picname.
	 * @param animeList The anime list.
	 * @param episodeList The episode list.
	 * @param lastUpdateDate The date of the last update.
	 */
	public Character(final Long characterId, final String kanjiName,
		final String nameTranscription, final String picname,
		final List<AnimeCharacter> animeList, final List<Integer> episodeList,
		final Long lastUpdateDate) {
		
		super();
		this.characterId = characterId;
		this.kanjiName = kanjiName;
		this.nameTranscription = nameTranscription;
		this.picname = picname;
		if (animeList != null) {
			this.animeList = new Vector<AnimeCharacter>(animeList);
		}
		if (episodeList != null) {
			this.episodeList = new Vector<Integer>(episodeList);
		}
		this.lastUpdateDate = lastUpdateDate;
	}
	
	/**
	 * Returns the character Id.
	 * @return The character Id or <code>null</code>, if the character Id isn't
	 * set.
	 */
	public Long getCharacterId() {
		return this.characterId;
	}
	
	/**
	 * Sets the character Id.
	 * @param characterId The character Id.
	 */
	public void setCharacterId(final Long characterId) {
		this.characterId = characterId;
	}
	
	/**
	 * Returns the kanji name.
	 * @return The kanji name or <code>null</code>, if the kanji name isn't set.
	 */
	public String getKanjiName() {
		return this.kanjiName;
	}
	
	/**
	 * Sets the kanji name.
	 * @param kanjiName The kanji name.
	 */
	public void setKanjiName(final String kanjiName) {
		this.kanjiName = kanjiName;
	}
	
	/**
	 * Returns the name transcription.
	 * @return The name transcription or <code>null</code>, if the name
	 * transcription is <code>null</code>.
	 */
	public String getNameTranscription() {
		return this.nameTranscription;
	}
	
	/**
	 * Sets the name transcription.
	 * @param nameTranscription The name transcription.
	 */
	public void setNameTranscription(final String nameTranscription) {
		this.nameTranscription = nameTranscription;
	}
	
	/**
	 * Returns the picname.
	 * @return The picname or <code>null</code>, if the picname isn't set.
	 */
	public String getPicname() {
		return this.picname;
	}
	
	/**
	 * Sets the picname.
	 * @param picname The picname.
	 */
	public void setPicname(final String picname) {
		this.picname = picname;
	}
	
	/**
	 * Returns the anime list.
	 * @return The anime list or <code>null</code>, if the anime list isn't set.
	 */
	public List<AnimeCharacter> getAnimeList() {
		if (this.animeList == null) {
			return null;
		}
		return (new Vector<AnimeCharacter>(this.animeList));
	}
	
	/**
	 * Sets the anime list.
	 * @param animeList The anime list.
	 */
	public void setAnimeList(final List<AnimeCharacter> animeList) {
		if (animeList == null) {
			this.animeList = null;
		} else {
			this.animeList = new Vector<AnimeCharacter>(animeList);
		}
	}
	
	/**
	 * Returns the episode list.
	 * @return The episode list or <code>null</code>, if the episode list isn't
	 * set.
	 */
	public List<Integer> getEpisodeList() {
		if (this.episodeList == null) {
			return null;
		}
		return (new Vector<Integer>(this.episodeList));
	}
	
	/**
	 * Sets the episode list.
	 * @param episodeList The episode list.
	 */
	public void setEpisodeList(final List<Integer> episodeList) {
		if (episodeList == null) {
			this.episodeList = null;
		} else {
			this.episodeList = new Vector<Integer>(episodeList);
		}
	}
	
	/**
	 * Returns the date of the last update.
	 * @return The date or <code>null</code>, if the date isn't set.
	 */
	public Long getLastUpdateDate() {
		return this.lastUpdateDate;
	}
	
	/**
	 * Sets the date of the last update.
	 * @param lastUpdateDate The date.
	 */
	public void setLastUpdateDate(final Long lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
	public int hashCode() {
		int result = 17;
		
		result = ObjectKit.hash(this.characterId, result);
		return result;
	}
	
	public boolean equals(final Object obj) {
		Character character;
		
		if (obj instanceof Character) {
			character = (Character) obj;
			if (ObjectKit.equals(character.characterId, this.characterId)) {
				return true;
			}
		}
		return false;
	}
}
