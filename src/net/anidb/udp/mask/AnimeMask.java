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
 * <p>The anime mask for the <code>ANIME</code> command.</p>
 * <p>With this mask you can specify which data fields are returned.</p>
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 06.12.2009
 */
public class AnimeMask extends Mask {
	/** A mask with all fields set. */
	public final static AnimeMask ALL = new AnimeMask(true, true, true, true,
			true, true, true, true, true, true, true, true, true, true, true,
			true, true, true, true, true, true, true, true, true, true, true,
			true, true, true, true, true, true, true, true, true, true, true,
			true, true, true, true, true, true);
	
	/** The anime Id. */
	private boolean animeId;
	/** The year. */
	private boolean year;
	/** The type. */
	private boolean type;
	/** The related anime Id list. */
	private boolean relatedAidList;
	/** The related anime type. */
	private boolean relatedAidType;
	/** The category list. */
	private boolean categoryList;
	/** The category weight list. */
	private boolean categoryWeightList;
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
	/** The number of episodes. */
	private boolean episodes;
	/** The normal episode count. */
	private boolean normalEpisodeCount;
	/** The special episode count. */
	private boolean specialEpisodeCount;
	/** The air date. */
	private boolean airDate;
	/** The end date. */
	private boolean endDate;
	/** The url. */
	private boolean url;
	/** The picname. */
	private boolean picname;
	/** The category Id list. */
	private boolean categoryIdList;
	/** The rating. */
	private boolean rating;
	/** The vote count. */
	private boolean voteCount;
	/** The temporary rating. */
	private boolean tempRating;
	/** The temporary vote count. */
	private boolean tempVoteCount;
	/** The average review rating. */
	private boolean averageReviewRating;
	/** The review count. */
	private boolean reviewCount;
	/** The award list. */
	private boolean awardList;
	/** The 'is 18+ restricted' flag. */
	private boolean is18PlusRestricted;
	/** The Anime Planet Id. */
	private boolean animePlanetId;
	/** The Ann Id. */
	private boolean annId;
	/** The AllCinema Id. */
	private boolean allCinemaId;
	/** The AnimeNfo Id. */
	private boolean animeNfoId;
	/** The record update date. */
	private boolean dateRecordUpdated;
	/** The character Id list. */
	private boolean characterIdList;
	/** The creator Id list. */
	private boolean creatorIdList;
	/** The producer Id list. */
	private boolean producerIdList;
	/** The producer name list. */
	private boolean producerNameList;
	/** The specials count. */
	private boolean specialsCount;
	/** The credits count. */
	private boolean creditsCount;
	/** The other count. */
	private boolean otherCount;
	/** The trailer count. */
	private boolean trailerCount;
	/** The parody count. */
	private boolean parodyCount;
	
	/**
	 * Creates an anime mask.
	 * @param animeId The anime Id.
	 * @param year The year.
	 * @param type The type.
	 * @param relatedAidList The related anime Id list.
	 * @param relatedAidType The related anime type.
	 * @param categoryList The category list.
	 * @param categoryWeightList The category weight list.
	 * @param romajiName The romaji name.
	 * @param kanjiName The kanji name.
	 * @param englishName The english name.
	 * @param otherNames The other names list.
	 * @param shortNameList The short name list.
	 * @param synonymList The synonym list.
	 * @param episodes The number of episodes.
	 * @param normalEpisodeCount The normal episode count.
	 * @param specialEpisodeCount The special episode count.
	 * @param airDate The air date.
	 * @param endDate The end date.
	 * @param url The url.
	 * @param picname The picname.
	 * @param categoryIdList The category Id list.
	 * @param rating The rating.
	 * @param voteCount The vote count.
	 * @param tempRating The temporary rating.
	 * @param tempVoteCount The temporary vote count.
	 * @param averageReviewRating The average review rating.
	 * @param reviewCount The review count.
	 * @param awardList The award list.
	 * @param is18PlusRestricted The 'is 18+ restricted' flag.
	 * @param animePlanetId The Anime Planet Id.
	 * @param annId The Ann Id.
	 * @param allCinemaId The AllCinema Id.
	 * @param animeNfoId The AnimeNfo Id.
	 * @param dateRecordUpdated The record update date.
	 * @param characterIdList The character Id list.
	 * @param creatorIdList The creator Id list.
	 * @param producerIdList The producer Id list.
	 * @param producerNameList The producer name list.
	 * @param specialsCount The specials count.
	 * @param creditsCount The credits count.
	 * @param otherCount The other count.
	 * @param trailerCount The trailer count.
	 * @param parodyCount The parody count.
	 */
	public AnimeMask(final boolean animeId, final boolean year,
		final boolean type, final boolean relatedAidList,
		final boolean relatedAidType, final boolean categoryList,
		final boolean categoryWeightList, final boolean romajiName,
		final boolean kanjiName, final boolean englishName,
		final boolean otherNames, final boolean shortNameList,
		final boolean synonymList, final boolean episodes,
		final boolean normalEpisodeCount, final boolean specialEpisodeCount,
		final boolean airDate, final boolean endDate, final boolean url,
		final boolean picname, final boolean categoryIdList,
		final boolean rating, final boolean voteCount, final boolean tempRating,
		final boolean tempVoteCount, final boolean averageReviewRating,
		final boolean reviewCount, final boolean awardList,
		final boolean is18PlusRestricted, final boolean animePlanetId,
		final boolean annId, final boolean allCinemaId,
		final boolean animeNfoId, final boolean dateRecordUpdated,
		final boolean characterIdList, final boolean creatorIdList,
		final boolean producerIdList, final boolean producerNameList,
		final boolean specialsCount, final boolean creditsCount,
		final boolean otherCount, final boolean trailerCount,
		final boolean parodyCount) {
		
		this.animeId = animeId;
		this.year = year;
		this.type = type;
		this.relatedAidList = relatedAidList;
		this.relatedAidType = relatedAidType;
		this.categoryList = categoryList;
		this.categoryWeightList = categoryWeightList;
		this.romajiName = romajiName;
		this.kanjiName = kanjiName;
		this.englishName = englishName;
		this.otherNames = otherNames;
		this.shortNameList = shortNameList;
		this.synonymList = synonymList;
		this.episodes = episodes;
		this.normalEpisodeCount = normalEpisodeCount;
		this.specialEpisodeCount = specialEpisodeCount;
		this.airDate = airDate;
		this.endDate = endDate;
		this.url = url;
		this.picname = picname;
		this.categoryIdList = categoryIdList;
		this.rating = rating;
		this.voteCount = voteCount;
		this.tempRating = tempRating;
		this.tempVoteCount = tempVoteCount;
		this.averageReviewRating = averageReviewRating;
		this.reviewCount = reviewCount;
		this.awardList = awardList;
		this.is18PlusRestricted = is18PlusRestricted;
		this.animePlanetId = animePlanetId;
		this.annId = annId;
		this.allCinemaId = allCinemaId;
		this.animeNfoId = animeNfoId;
		this.dateRecordUpdated = dateRecordUpdated;
		this.characterIdList = characterIdList;
		this.creatorIdList = creatorIdList;
		this.producerIdList = producerIdList;
		this.producerNameList = producerNameList;
		this.specialsCount = specialsCount;
		this.creditsCount = creditsCount;
		this.otherCount = otherCount;
		this.trailerCount = trailerCount;
		this.parodyCount = parodyCount;
	}
	
	/**
	 * Returns the anime Id.
	 * @return The anime Id.
	 */
	public boolean isAnimeId() {
		return this.animeId;
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
	 * @return The typ.
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
	 * Returns the related anime type.
	 * @return The related anime type.
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
	 * Returns the category weight list.
	 * @return The category weight list.
	 */
	public boolean isCategoryWeightList() {
		return this.categoryWeightList;
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
	 * Returns the number of episodes.
	 * @return The number of episodes.
	 */
	public boolean isEpisodes() {
		return this.episodes;
	}
	
	/**
	 * Returns the normal episode count.
	 * @return The normal episode count.
	 */
	public boolean isNormalEpisodeCount() {
		return this.normalEpisodeCount;
	}
	
	/**
	 * Returns the special episode count.
	 * @return The special episode count.
	 */
	public boolean isSpecialEpisodeCount() {
		return this.specialEpisodeCount;
	}
	
	/**
	 * Returns the air date.
	 * @return The air date.
	 */
	public boolean isAirDate() {
		return this.airDate;
	}
	
	/**
	 * Returns the end date.
	 * @return The end date.
	 */
	public boolean isEndDate() {
		return this.endDate;
	}
	
	/**
	 * Returns the url.
	 * @return The url.
	 */
	public boolean isUrl() {
		return this.url;
	}
	
	/**
	 * Returns the picname.
	 * @return The picname.
	 */
	public boolean isPicname() {
		return this.picname;
	}
	
	/**
	 * Returns the category Id list.
	 * @return The category Id list.
	 */
	public boolean isCategoryIdList() {
		return this.categoryIdList;
	}
	
	/**
	 * Returns the rating.
	 * @return The rating.
	 */
	public boolean isRating() {
		return this.rating;
	}
	
	/**
	 * Returns the vote count.
	 * @return The vote count.
	 */
	public boolean isVoteCount() {
		return this.voteCount;
	}
	
	/**
	 * Returns the temporary rating.
	 * @return The temporary rating.
	 */
	public boolean isTempRating() {
		return this.tempRating;
	}
	
	/**
	 * Returns the temporary vote count.
	 * @return The temporary vote count.
	 */
	public boolean isTempVoteCount() {
		return this.tempVoteCount;
	}
	
	/**
	 * Returns the average review rating.
	 * @return The average review rating.
	 */
	public boolean isAverageReviewRating() {
		return this.averageReviewRating;
	}
	
	/**
	 * Returns the review count.
	 * @return The review count.
	 */
	public boolean isReviewCount() {
		return this.reviewCount;
	}
	
	/**
	 * Returns the award list.
	 * @return The award list.
	 */
	public boolean isAwardList() {
		return this.awardList;
	}
	
	/**
	 * Returns the 'is 18+ restricted' flag.
	 * @return The 'is 18+ restricted' flag.
	 */
	public boolean is18PlusRestricted() {
		return this.is18PlusRestricted;
	}
	
	/**
	 * Returns the Anime Planet Id.
	 * @return The Anime Planet Id.
	 */
	public boolean isAnimePlanetId() {
		return this.animePlanetId;
	}
	
	/**
	 * Returns the Ann Id.
	 * @return The Ann Id.
	 */
	public boolean isAnnId() {
		return this.annId;
	}
	
	/**
	 * Return ths AllCinema Id.
	 * @return The AllCinema Id.
	 */
	public boolean isAllCinemaId() {
		return this.allCinemaId;
	}
	
	/**
	 * Returns the AnimeNfo Id.
	 * @return The AnimeNfo Id.
	 */
	public boolean isAnimeNfoId() {
		return this.animeNfoId;
	}
	
	/**
	 * Returns the record update date.
	 * @return The record update date.
	 */
	public boolean isDateRecordUpdated() {
		return this.dateRecordUpdated;
	}
	
	/**
	 * Returns the character Id list.
	 * @return The character Id list
	 */
	public boolean isCharacterIdList() {
		return this.characterIdList;
	}
	
	/**
	 * Returns the creator Id list.
	 * @return The creator Id list.
	 */
	public boolean isCreatorIdList() {
		return this.creatorIdList;
	}
	
	/**
	 * Returns the producer Id list.
	 * @return The producer Id list.
	 */
	public boolean isProducerIdList() {
		return this.producerIdList;
	}
	
	/**
	 * Returns the producer name list.
	 * @return The producer name list.
	 */
	public boolean isProducerNameList() {
		return this.producerNameList;
	}
	
	/**
	 * Returns the specials count.
	 * @return The specials count.
	 */
	public boolean isSpecialsCount() {
		return this.specialsCount;
	}
	
	/**
	 * Returns the credits count.
	 * @return The credits count.
	 */
	public boolean isCreditsCount() {
		return this.creditsCount;
	}
	
	/**
	 * Returns the other count.
	 * @return The other count.
	 */
	public boolean isOtherCount() {
		return this.otherCount;
	}
	
	/**
	 * Returns the trailer count.
	 * @return The trailer count.
	 */
	public boolean isTrailerCount() {
		return this.trailerCount;
	}
	
	/**
	 * Returns the parody count.
	 * @return The parody count.
	 */
	public boolean isParodyCount() {
		return this.parodyCount;
	}
	
	public long getMask() {
		long mask = 0;
		
		if (this.animeId) mask = this.setBit(1, 7, 7, mask);
		if (this.year) mask = this.setBit(1, 7, 5, mask);
		if (this.type) mask = this.setBit(1, 7, 4, mask);
		if (this.relatedAidList) mask = this.setBit(1, 7, 3, mask);
		if (this.relatedAidType) mask = this.setBit(1, 7, 2, mask);
		if (this.categoryList) mask = this.setBit(1, 7, 1, mask);
		if (this.categoryWeightList) mask = this.setBit(1, 7, 0, mask);
		if (this.romajiName) mask = this.setBit(2, 7, 7, mask);
		if (this.kanjiName) mask = this.setBit(2, 7, 6, mask);
		if (this.englishName) mask = this.setBit(2, 7, 5, mask);
		if (this.otherNames) mask = this.setBit(2, 7, 4, mask);
		if (this.shortNameList) mask = this.setBit(2, 7, 3, mask);
		if (this.synonymList) mask = this.setBit(2, 7, 2, mask);
		if (this.episodes) mask = this.setBit(3, 7, 7, mask);
		if (this.normalEpisodeCount) mask = this.setBit(3, 7, 6, mask);
		if (this.specialEpisodeCount) mask = this.setBit(3, 7, 5, mask);
		if (this.airDate) mask = this.setBit(3, 7, 4, mask);
		if (this.endDate) mask = this.setBit(3, 7, 3, mask);
		if (this.url) mask = this.setBit(3, 7, 2, mask);
		if (this.picname) mask = this.setBit(3, 7, 1, mask);
		if (this.categoryIdList) mask = this.setBit(3, 7, 0, mask);
		if (this.rating) mask = this.setBit(4, 7, 7, mask);
		if (this.voteCount) mask = this.setBit(4, 7, 6, mask);
		if (this.tempRating) mask = this.setBit(4, 7, 5, mask);
		if (this.tempVoteCount) mask = this.setBit(4, 7, 4, mask);
		if (this.averageReviewRating) mask = this.setBit(4, 7, 3, mask);
		if (this.reviewCount) mask = this.setBit(4, 7, 2, mask);
		if (this.awardList) mask = this.setBit(4, 7, 1, mask);
		if (this.is18PlusRestricted) mask = this.setBit(4, 7, 0, mask);
		if (this.animePlanetId) mask = this.setBit(5, 7, 7, mask);
		if (this.annId) mask = this.setBit(5, 7, 6, mask);
		if (this.allCinemaId) mask = this.setBit(5, 7, 5, mask);
		if (this.animeNfoId) mask = this.setBit(5, 7, 4, mask);
		if (this.dateRecordUpdated) mask = this.setBit(5, 7, 0, mask);
		if (this.characterIdList) mask = this.setBit(6, 7, 7, mask);
		if (this.creatorIdList) mask = this.setBit(6, 7, 6, mask);
		if (this.producerIdList) mask = this.setBit(6, 7, 5, mask);
		if (this.producerNameList) mask = this.setBit(6, 7, 4, mask);
		if (this.specialsCount) mask = this.setBit(7, 7, 7, mask);
		if (this.creditsCount) mask = this.setBit(7, 7, 6, mask);
		if (this.otherCount) mask = this.setBit(7, 7, 5, mask);
		if (this.trailerCount) mask = this.setBit(7, 7, 4, mask);
		if (this.parodyCount) mask = this.setBit(7, 7, 3, mask);
		return mask;
	}
	
	public String getHexMask() {
		return this.getHexMask(14);
	}
	
	public int getFlagCount() {
		int count = 0;
		
		if (this.animeId) count++;
		if (this.year) count++;
		if (this.type) count++;
		if (this.relatedAidList) count++;
		if (this.relatedAidType) count++;
		if (this.categoryList) count++;
		if (this.categoryWeightList) count++;
		if (this.romajiName) count++;
		if (this.kanjiName) count++;
		if (this.englishName) count++;
		if (this.otherNames) count++;
		if (this.shortNameList) count++;
		if (this.synonymList) count++;
		if (this.episodes) count++;
		if (this.normalEpisodeCount) count++;
		if (this.specialEpisodeCount) count++;
		if (this.airDate) count++;
		if (this.endDate) count++;
		if (this.url) count++;
		if (this.picname) count++;
		if (this.categoryIdList) count++;
		if (this.rating) count++;
		if (this.voteCount) count++;
		if (this.tempRating) count++;
		if (this.tempVoteCount) count++;
		if (this.averageReviewRating) count++;
		if (this.reviewCount) count++;
		if (this.awardList) count++;
		if (this.is18PlusRestricted) count++;
		if (this.animePlanetId) count++;
		if (this.annId) count++;
		if (this.allCinemaId) count++;
		if (this.animeNfoId) count++;
		if (this.dateRecordUpdated) count++;
		if (this.characterIdList) count++;
		if (this.creatorIdList) count++;
		if (this.producerIdList) count++;
		if (this.producerNameList) count++;
		if (this.specialsCount) count++;
		if (this.creditsCount) count++;
		if (this.otherCount) count++;
		if (this.trailerCount) count++;
		if (this.parodyCount) count++;
		return count;
	}
	
	public int hashCode() {
		int result = 17;
		
		result = 37 * result + (this.animeId ? 0 : 1);
		result = 37 * result + (this.year ? 0 : 1);
		result = 37 * result + (this.type ? 0 : 1);
		result = 37 * result + (this.relatedAidList ? 0 : 1);
		result = 37 * result + (this.relatedAidType ? 0 : 1);
		result = 37 * result + (this.categoryList ? 0 : 1);
		result = 37 * result + (this.categoryWeightList ? 0 : 1);
		result = 37 * result + (this.romajiName ? 0 : 1);
		result = 37 * result + (this.kanjiName ? 0 : 1);
		result = 37 * result + (this.englishName ? 0 : 1);
		result = 37 * result + (this.otherNames ? 0 : 1);
		result = 37 * result + (this.shortNameList ? 0 : 1);
		result = 37 * result + (this.synonymList ? 0 : 1);
		result = 37 * result + (this.episodes ? 0 : 1);
		result = 37 * result + (this.normalEpisodeCount ? 0 : 1);
		result = 37 * result + (this.specialEpisodeCount ? 0 : 1);
		result = 37 * result + (this.airDate ? 0 : 1);
		result = 37 * result + (this.endDate ? 0 : 1);
		result = 37 * result + (this.url ? 0 : 1);
		result = 37 * result + (this.picname ? 0 : 1);
		result = 37 * result + (this.categoryIdList ? 0 : 1);
		result = 37 * result + (this.rating ? 0 : 1);
		result = 37 * result + (this.voteCount ? 0 : 1);
		result = 37 * result + (this.tempRating ? 0 : 1);
		result = 37 * result + (this.tempVoteCount ? 0 : 1);
		result = 37 * result + (this.averageReviewRating ? 0 : 1);
		result = 37 * result + (this.reviewCount ? 0 : 1);
		result = 37 * result + (this.awardList ? 0 : 1);
		result = 37 * result + (this.is18PlusRestricted ? 0 : 1);
		result = 37 * result + (this.animePlanetId ? 0 : 1);
		result = 37 * result + (this.annId ? 0 : 1);
		result = 37 * result + (this.allCinemaId ? 0 : 1);
		result = 37 * result + (this.animeNfoId ? 0 : 1);
		result = 37 * result + (this.dateRecordUpdated ? 0 : 1);
		result = 37 * result + (this.characterIdList ? 0 : 1);
		result = 37 * result + (this.creatorIdList ? 0 : 1);
		result = 37 * result + (this.producerIdList ? 0 : 1);
		result = 37 * result + (this.producerNameList ? 0 : 1);
		result = 37 * result + (this.specialsCount ? 0 : 1);
		result = 37 * result + (this.creditsCount ? 0 : 1);
		result = 37 * result + (this.otherCount ? 0 : 1);
		result = 37 * result + (this.trailerCount ? 0 : 1);
		result = 37 * result + (this.parodyCount ? 0 : 1);
		
		return result;
	}
	
	public boolean equals(final Object obj) {
		AnimeMask mask;
		
		if (obj instanceof AnimeMask) {
			mask = (AnimeMask) obj;
			if ((this.animeId == mask.animeId)
				&& (this.year == mask.year)
				&& (this.type == mask.type)
				&& (this.relatedAidList == mask.relatedAidList)
				&& (this.relatedAidType == mask.relatedAidType)
				&& (this.categoryList == mask.categoryList)
				&& (this.categoryWeightList == mask.categoryWeightList)
				&& (this.romajiName == mask.romajiName)
				&& (this.kanjiName == mask.kanjiName)
				&& (this.englishName == mask.englishName)
				&& (this.otherNames == mask.otherNames)
				&& (this.shortNameList == mask.shortNameList)
				&& (this.synonymList == mask.synonymList)
				&& (this.episodes == mask.episodes)
				&& (this.normalEpisodeCount == mask.normalEpisodeCount)
				&& (this.specialEpisodeCount == mask.specialEpisodeCount)
				&& (this.airDate == mask.airDate)
				&& (this.endDate == mask.endDate)
				&& (this.url == mask.url)
				&& (this.picname == mask.picname)
				&& (this.categoryIdList == mask.categoryIdList)
				&& (this.rating == mask.rating)
				&& (this.voteCount == mask.voteCount)
				&& (this.tempRating == mask.tempRating)
				&& (this.tempVoteCount == mask.tempVoteCount)
				&& (this.averageReviewRating == mask.averageReviewRating)
				&& (this.reviewCount == mask.reviewCount)
				&& (this.awardList == mask.awardList)
				&& (this.is18PlusRestricted == mask.is18PlusRestricted)
				&& (this.animePlanetId == mask.animePlanetId)
				&& (this.annId == mask.annId)
				&& (this.allCinemaId == mask.allCinemaId)
				&& (this.animeNfoId == mask.animeNfoId)
				&& (this.dateRecordUpdated == mask.dateRecordUpdated)
				&& (this.characterIdList == mask.characterIdList)
				&& (this.creatorIdList == mask.creatorIdList)
				&& (this.producerIdList == mask.producerIdList)
				&& (this.producerNameList == mask.producerNameList)
				&& (this.specialsCount == mask.specialsCount)
				&& (this.creditsCount == mask.creditsCount)
				&& (this.otherCount == mask.otherCount)
				&& (this.trailerCount == mask.trailerCount)
				&& (this.parodyCount == mask.parodyCount)) {
				return true;
			}
		}
		return false;
	}
}
