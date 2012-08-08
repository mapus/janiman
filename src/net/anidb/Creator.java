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
 * The creator of an anime.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 19.12.2009
 */
public class Creator {
	/** The creator Id. */
	private Long creatorId;
	/** The kanji name. */
	private String kanjiName;
	/** The name transcription. */
	private String nameTranscription;
	/** The type. */
	private Integer type;
	/** The picname. */
	private String picname;
	/** The english URL. */
	private String urlEnglish;
	/** The japanese URL. */
	private String urlJapanese;
	/** The english Wiki URL. */
	private String wikiUrlEnglish;
	/** The japanese Wiki URL. */
	private String wikiUrlJapanese;
	/** The date of the last update. */
	private Long lastUpdateDate;
	
	/**
	 * Creates a creator.
	 */
	public Creator() {
		super();
	}
	
	/**
	 * Creates a creator.
	 * @param creatorId The creator Id.
	 * @param kanjiName The kanji name.
	 * @param nameTranscription The name transcription.
	 * @param type The type.
	 * @param picname The picname.
	 * @param urlEnglish The english URL.
	 * @param urlJapanese The japanese URL.
	 * @param wikiUrlEnglish The english Wiki URL.
	 * @param wikiUrlJapanese The japanese Wiki URL.
	 * @param lastUpdateDate The date of the last update.
	 */
	public Creator(final Long creatorId, final String kanjiName,
		final String nameTranscription, final Integer type,
		final String picname, final String urlEnglish, final String urlJapanese,
		final String wikiUrlEnglish, final String wikiUrlJapanese,
		final Long lastUpdateDate) {
		
		super();
		this.creatorId = creatorId;
		this.kanjiName = kanjiName;
		this.nameTranscription = nameTranscription;
		this.type = type;
		this.picname = picname;
		this.urlEnglish = urlEnglish;
		this.urlJapanese = urlJapanese;
		this.wikiUrlEnglish = wikiUrlEnglish;
		this.wikiUrlJapanese = wikiUrlJapanese;
		this.lastUpdateDate = lastUpdateDate;
	}
	
	/**
	 * Returns the creator Id.
	 * @return The creator Id or <code>null</code>, if the creator Id isn't set.
	 */
	public Long getCreatorId() {
		return this.creatorId;
	}
	
	/**
	 * Sets the creator Id.
	 * @param creatorId The creator Id.
	 */
	public void setCreatorId(final Long creatorId) {
		this.creatorId = creatorId;
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
	 * transcription isn't set.
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
	 * Returns the type.
	 * @return The type or <code>null</code>, if the type isn't set.
	 * @see CreatorType
	 */
	public Integer getType() {
		return this.type;
	}
	
	/**
	 * Sets the type.
	 * @param type The type.
	 * @see CreatorType
	 */
	public void setType(final Integer type) {
		this.type = type;
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
	 * Returns the english URL.
	 * @return The english URL or <code>null</code>, if the english URL isn't
	 * set.
	 */
	public String getUrlEnglish() {
		return this.urlEnglish;
	}
	
	/**
	 * Sets the english URL.
	 * @param urlEnglish The english URL. 
	 */
	public void setUrlEnglish(final String urlEnglish) {
		this.urlEnglish = urlEnglish;
	}
	
	/**
	 * Returns the japanese URL.
	 * @return The japanese URL or <code>null</code>, if the japanese URL isn't
	 * set.
	 */
	public String getUrlJapanese() {
		return this.urlJapanese;
	}
	
	/**
	 * Sets the japanese URL.
	 * @param urlJapanese The japanese URL.
	 */
	public void setUrlJapanese(final String urlJapanese) {
		this.urlJapanese = urlJapanese;
	}
	
	/**
	 * Returns the english Wiki URL.
	 * @return The english Wiki URL or <code>null</code>, if the english Wiki
	 * URL isn't set.
	 */
	public String getWikiUrlEnglish() {
		return this.wikiUrlEnglish;
	}
	
	/**
	 * Sets the english Wiki URL.
	 * @param wikiUrlEnglish The english Wiki URL.
	 */
	public void setWikiUrlEnglish(final String wikiUrlEnglish) {
		this.wikiUrlEnglish = wikiUrlEnglish;
	}
	
	/**
	 * Returns the japanese Wiki URL.
	 * @return The japanese Wiki URL or <code>null</code>, if the japanese Wiki
	 * URL isn't set.
	 */
	public String getWikiUrlJapanese() {
		return this.wikiUrlJapanese;
	}
	
	/**
	 * Sets the japanese Wiki URL.
	 * @param wikiUrlJapanese The japanese Wiki URL.
	 */
	public void setWikiUrlJapanese(final String wikiUrlJapanese) {
		this.wikiUrlJapanese = wikiUrlJapanese;
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
		
		result = ObjectKit.hash(this.creatorId, result);
		return result;
	}
	
	public boolean equals(final Object obj) {
		Creator creator;
		
		if (obj instanceof Creator) {
			creator = (Creator) obj;
			if (ObjectKit.equals(creator.creatorId, this.creatorId)) {
				return true;
			}
		}
		return false;
	}
}
