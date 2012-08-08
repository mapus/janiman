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
package net.anidb.udp.mask;

/**
 * <p>The anime mask for the <code>FILE</code> command.</p>
 * <p>With this mask you can specify which data fields are returned.</p>
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 07.12.2009
 */
public class AnimeFileMask extends Mask {
	/** A mask with all fields set. */
	public final static AnimeFileMask ALL = new AnimeFileMask(true, true, true,
			true, true, true, true, true, true, true, true, true, true, true,
			true, true, true, true, true, true, true, true);
	/** A mask with no field set. */
	public final static AnimeFileMask NONE = new AnimeFileMask(false, false,
			false, false, false, false, false, false, false, false, false,
			false, false, false, false, false, false, false, false, false,
			false, false);
	
	/** The total episodes of the anime. */
	private boolean animeTotalEpisode;
	/** The highest episode number. */
	private boolean highestEpisodeNumber;
	/** The year. */
	private boolean year;
	/** The type. */
	private boolean type;
	/** The related anime Id list. */
	private boolean relatedAidList;
	/** The related anime Id type. */
	private boolean relatedAidType;
	/** The category list. */
	private boolean categoryList;
	/** The romaji name. */
	private boolean romajiName;
	/** The kanji name. */
	private boolean kanjiName;
	/** The english name. */
	private boolean englishName;
	/** The other names list. */
	private boolean otherNames;
	/** The short name list. */
	private boolean shortNameList;
	/** The synonym list. */
	private boolean synonymList;
	/** The episode number. */
	private boolean episodeNumber;
	/** The episode name. */
	private boolean episodeName;
	/** The episode romaji name. */
	private boolean episodeRomajiName;
	/** The episode kanji name. */
	private boolean episodeKanjiName;
	/** The episode rating. */
	private boolean episodeRating;
	/** The episode vote count. */
	private boolean episodeVoteCount;
	/** The group name. */
	private boolean groupName;
	/** The group short name. */
	private boolean groupShortName;
	/** The date of the anime Id record update. */
	private boolean dateAnimeIdRecordUpdate;
	
	/**
	 * Creates an anime file mask.
	 * @param animeTotalEpisode The total episodes of the anime.
	 * @param highestEpisodeNumber The highest episode number.
	 * @param year The year.
	 * @param type The type.
	 * @param relatedAidList The related anime Id list.
	 * @param relatedAidType The related anime Id type.
	 * @param categoryList The category list.
	 * @param romajiName The romaji name.
	 * @param kanjiName The kanji name.
	 * @param englishName The english name.
	 * @param otherNames The other names list.
	 * @param shortNameList The short name list.
	 * @param synonymList The synonym list.
	 * @param episodeNumber The episode number.
	 * @param episodeName The episode name.
	 * @param episodeRomajiName The episode romaji name.
	 * @param episodeKanjiName The episode kanji name.
	 * @param episodeRating The episode rating.
	 * @param episodeVoteCount The episode vote count.
	 * @param groupName The group name.
	 * @param groupShortName The group short name.
	 * @param dateAnimeIdRecordUpdate The date of the anime Id record update.
	 */
	public AnimeFileMask(final boolean animeTotalEpisode,
		final boolean highestEpisodeNumber, final boolean year,
		final boolean type, final boolean relatedAidList,
		final boolean relatedAidType, final boolean categoryList,
		final boolean romajiName, final boolean kanjiName,
		final boolean englishName, final boolean otherNames,
		final boolean shortNameList, final boolean synonymList,
		final boolean episodeNumber, final boolean episodeName,
		final boolean episodeRomajiName, final boolean episodeKanjiName,
		final boolean episodeRating, final boolean episodeVoteCount,
		final boolean groupName, final boolean groupShortName,
		final boolean dateAnimeIdRecordUpdate) {
		
		super();
		this.animeTotalEpisode = animeTotalEpisode;
		this.highestEpisodeNumber = highestEpisodeNumber;
		this.year = year;
		this.type = type;
		this.relatedAidList = relatedAidList;
		this.relatedAidType = relatedAidType;
		this.categoryList = categoryList;
		this.romajiName = romajiName;
		this.kanjiName = kanjiName;
		this.englishName = englishName;
		this.otherNames = otherNames;
		this.shortNameList = shortNameList;
		this.synonymList = synonymList;
		this.episodeNumber = episodeNumber;
		this.episodeName = episodeName;
		this.episodeRomajiName = episodeRomajiName;
		this.episodeKanjiName = episodeKanjiName;
		this.episodeRating = episodeRating;
		this.episodeVoteCount = episodeVoteCount;
		this.groupName = groupName;
		this.groupShortName = groupShortName;
		this.dateAnimeIdRecordUpdate = dateAnimeIdRecordUpdate;
	}
	
	/**
	 * Returns the total episodes of the anime.
	 * @return The total episodes of the anime.
	 */
	public boolean isAnimeTotalEpisode() {
		return this.animeTotalEpisode;
	}
	
	/**
	 * Returns the highest episode number.
	 * @return The highest episode number.
	 */
	public boolean isHighestEpisodeNumber() {
		return this.highestEpisodeNumber;
	}
	
	/**
	 * Returns the year.
	 * @return The year.
	 */
	public boolean isYear() {
		return this.year;
	}
	
	/**
	 * Returns the type.
	 * @return The type.
	 */
	public boolean isType() {
		return this.type;
	}
	
	/**
	 * Returns the related anime Id list.
	 * @return The related anime Id list.
	 */
	public boolean isRelatedAidList() {
		return this.relatedAidList;
	}
	
	/**
	 * Returns the related anime Id type.
	 * @return The related anime Id type.
	 */
	public boolean isRelatedAidType() {
		return this.relatedAidType;
	}
	
	/**
	 * Returns the category list.
	 * @return The category list.
	 */
	public boolean isCategoryList() {
		return this.categoryList;
	}
	
	/**
	 * Returns the romaji name.
	 * @return The romaji name.
	 */
	public boolean isRomajiName() {
		return this.romajiName;
	}
	
	/**
	 * Returns the kanji name.
	 * @return The kanji name.
	 */
	public boolean isKanjiName() {
		return this.kanjiName;
	}
	
	/**
	 * Returns the english name.
	 * @return The english name.
	 */
	public boolean isEnglishName() {
		return this.englishName;
	}
	
	/**
	 * Returns the other names list.
	 * @return The other names list.
	 */
	public boolean isOtherNames() {
		return this.otherNames;
	}
	
	/**
	 * Returns the short name list.
	 * @return The short name list.
	 */
	public boolean isShortNameList() {
		return this.shortNameList;
	}
	
	/**
	 * Returns the synonym list.
	 * @return The synonym list.
	 */
	public boolean isSynonymList() {
		return this.synonymList;
	}
	
	/**
	 * Returns the episode number.
	 * @return The episode number.
	 */
	public boolean isEpisodeNumber() {
		return this.episodeNumber;
	}
	
	/**
	 * Returns the episode name.
	 * @return The episode name.
	 */
	public boolean isEpisodeName() {
		return this.episodeName;
	}
	
	/**
	 * Returns the episode romaji name.
	 * @return The episode romaji name.
	 */
	public boolean isEpisodeRomajiName() {
		return this.episodeRomajiName;
	}
	
	/**
	 * Returns the episode kanji name.
	 * @return The episode kanji name.
	 */
	public boolean isEpisodeKanjiName() {
		return this.episodeKanjiName;
	}
	
	/**
	 * Returns the episode rating.
	 * @return The episode rating.
	 */
	public boolean isEpisodeRating() {
		return this.episodeRating;
	}
	
	/**
	 * Returns the episode vote count.
	 * @return The episode vote count.
	 */
	public boolean isEpisodeVoteCount() {
		return this.episodeVoteCount;
	}
	
	/**
	 * Returns the group name.
	 * @return The group name.
	 */
	public boolean isGroupName() {
		return this.groupName;
	}
	
	/**
	 * Returns the group short name.
	 * @return The group short name.
	 */
	public boolean isGroupShortName() {
		return this.groupShortName;
	}
	
	/**
	 * Returns the date of the anime Id record update.
	 * @return The date of the anime Id record update.
	 */
	public boolean isDateAnimeIdRecordUpdate() {
		return this.dateAnimeIdRecordUpdate;
	}
	
	public long getMask() {
		long mask = 0;
		
		if (this.animeTotalEpisode) mask = this.setBit(1, 4, 7, mask);
		if (this.highestEpisodeNumber) mask = this.setBit(1, 4, 6, mask);
		if (this.year) mask = this.setBit(1, 4, 5, mask);
		if (this.type) mask = this.setBit(1, 4, 4, mask);
		if (this.relatedAidList) mask = this.setBit(1, 4, 3, mask);
		if (this.relatedAidType) mask = this.setBit(1, 4, 2, mask);
		if (this.categoryList) mask = this.setBit(1, 4, 1, mask);
		if (this.romajiName) mask = this.setBit(2, 4, 7, mask);
		if (this.kanjiName) mask = this.setBit(2, 4, 6, mask);
		if (this.englishName) mask = this.setBit(2, 4, 5, mask);
		if (this.otherNames) mask = this.setBit(2, 4, 4, mask);
		if (this.shortNameList) mask = this.setBit(2, 4, 3, mask);
		if (this.synonymList) mask = this.setBit(2, 4, 2, mask);
		if (this.episodeNumber) mask = this.setBit(3, 4, 7, mask);
		if (this.episodeName) mask = this.setBit(3, 4, 6, mask);
		if (this.episodeRomajiName) mask = this.setBit(3, 4, 5, mask);
		if (this.episodeKanjiName) mask = this.setBit(3, 4, 4, mask);
		if (this.episodeRating) mask = this.setBit(3, 4, 3, mask);
		if (this.episodeVoteCount) mask = this.setBit(3, 4, 2, mask);
		if (this.groupName) mask = this.setBit(4, 4, 7, mask);
		if (this.groupShortName) mask = this.setBit(4, 4, 6, mask);
		if (this.dateAnimeIdRecordUpdate) mask = this.setBit(4, 4, 0, mask);
		return mask;
	}
	
	public String getHexMask() {
		return this.getHexMask(8);
	}
	
	public int getFlagCount() {
		int count = 0;
		
		if (this.animeTotalEpisode) count++;
		if (this.highestEpisodeNumber) count++;
		if (this.year) count++;
		if (this.type) count++;
		if (this.relatedAidList) count++;
		if (this.relatedAidType) count++;
		if (this.categoryList) count++;
		if (this.romajiName) count++;
		if (this.kanjiName) count++;
		if (this.englishName) count++;
		if (this.otherNames) count++;
		if (this.shortNameList) count++;
		if (this.synonymList) count++;
		if (this.episodeNumber) count++;
		if (this.episodeName) count++;
		if (this.episodeRomajiName) count++;
		if (this.episodeKanjiName) count++;
		if (this.episodeRating) count++;
		if (this.episodeVoteCount) count++;
		if (this.groupName) count++;
		if (this.groupShortName) count++;
		if (this.dateAnimeIdRecordUpdate) count++;
		return count;
	}
	
	public int hashCode() {
		int result = 17;
		
		result = 37 * result + (this.animeTotalEpisode ? 0 : 1);
		result = 37 * result + (this.highestEpisodeNumber ? 0 : 1);
		result = 37 * result + (this.year ? 0 : 1);
		result = 37 * result + (this.type ? 0 : 1);
		result = 37 * result + (this.relatedAidList ? 0 : 1);
		result = 37 * result + (this.relatedAidType ? 0 : 1);
		result = 37 * result + (this.categoryList ? 0 : 1);
		result = 37 * result + (this.romajiName ? 0 : 1);
		result = 37 * result + (this.kanjiName ? 0 : 1);
		result = 37 * result + (this.englishName ? 0 : 1);
		result = 37 * result + (this.otherNames ? 0 : 1);
		result = 37 * result + (this.shortNameList ? 0 : 1);
		result = 37 * result + (this.synonymList ? 0 : 1);
		result = 37 * result + (this.episodeNumber ? 0 : 1);
		result = 37 * result + (this.episodeName ? 0 : 1);
		result = 37 * result + (this.episodeRomajiName ? 0 : 1);
		result = 37 * result + (this.episodeKanjiName ? 0 : 1);
		result = 37 * result + (this.episodeRating ? 0 : 1);
		result = 37 * result + (this.episodeVoteCount ? 0 : 1);
		result = 37 * result + (this.groupName ? 0 : 1);
		result = 37 * result + (this.groupShortName ? 0 : 1);
		result = 37 * result + (this.dateAnimeIdRecordUpdate ? 0 : 1);
		return result;
	}
	
	public boolean equals(final Object obj) {
		AnimeFileMask mask;
		
		if (obj instanceof AnimeFileMask) {
			mask = (AnimeFileMask) obj;
			if ((this.animeTotalEpisode == mask.animeTotalEpisode)
					&& (this.highestEpisodeNumber == mask.highestEpisodeNumber)
					&& (this.year == mask.year)
					&& (this.type == mask.type)
					&& (this.relatedAidList == mask.relatedAidList)
					&& (this.relatedAidType == mask.relatedAidType)
					&& (this.categoryList == mask.categoryList)
					&& (this.romajiName == mask.romajiName)
					&& (this.kanjiName == mask.kanjiName)
					&& (this.englishName == mask.englishName)
					&& (this.otherNames == mask.otherNames)
					&& (this.shortNameList == mask.shortNameList)
					&& (this.synonymList == mask.synonymList)
					&& (this.episodeNumber == mask.episodeNumber)
					&& (this.episodeName == mask.episodeName)
					&& (this.episodeRomajiName == mask.episodeRomajiName)
					&& (this.episodeKanjiName == mask.episodeKanjiName)
					&& (this.episodeRating == mask.episodeRating)
					&& (this.episodeVoteCount == mask.episodeVoteCount)
					&& (this.groupName == mask.groupName)
					&& (this.groupShortName == mask.groupShortName)
					&& (this.dateAnimeIdRecordUpdate
							== mask.dateAnimeIdRecordUpdate)) {
				return true;
			}
		}
		return false;
	}
}
