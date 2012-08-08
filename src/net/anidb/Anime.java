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
 * An anime.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 06.12.2009
 */
public class Anime {
	private Long animeId;
	private String year;
	private String type;
	private List<String> relatedAidList;
	private List<String> relatedAidType;
	private List<String> categoryList;
	private List<String> categoryWeightList;
	private String romajiName;
	private String kanjiName;
	private String englishName;
	private List<String>  otherNames;
	private List<String> shortNameList;
	private List<String> synonymList;
	private Long episodes;
	private Long normalEpisodeCount;
	private Long specialEpisodeCount;
	private Long airDate;
	private Long endDate;
	private String url;
	private String picname;
	private List<String> categoryIdList;
	private Long rating;
	private Long voteCount;
	private Long tempRating;
	private Long tempVoteCount;
	private Long averageReviewRating;
	private Long reviewCount;
	private List<String> awardList;
	private Boolean is18PlusRestricted;
	private Long animePlanetId;
	private Long annId;
	private Long allCinemaId;
	private String animeNfoId;
	private Long dateRecordUpdated;
	private List<Long> characterIdList;
	private List<Long> creatorIdList;
	private List<Long> producerIdList;
	private List<String> producerNameList;
	private Long specialsCount;
	private Long creditsCount;
	private Long otherCount;
	private Long trailerCount;
	private Long parodyCount;
	
	/**
	 * Creates an anime mask.
	 */
	public Anime() {
		super();
	}
	
	/**
	 * Creates an anime mask.
	 * @param aid The anime Id.
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
	public Anime(final Long aid, final String year, final String type,
		final List<String> relatedAidList, final List<String> relatedAidType,
		final List<String> categoryList, final List<String> categoryWeightList,
		final String romajiName, final String kanjiName,
		final String englishName, final List<String> otherNames,
		final List<String> shortNameList, final List<String> synonymList,
		final Long episodes, final Long normalEpisodeCount,
		final Long specialEpisodeCount, final Long airDate,
		final Long endDate, final String url, final String picname,
		final List<String> categoryIdList, final Long rating,
		final Long voteCount, final Long tempRating,
		final Long tempVoteCount, final Long averageReviewRating,
		final Long reviewCount, final List<String> awardList,
		final Boolean is18PlusRestricted, final Long animePlanetId,
		final Long annId, final Long allCinemaId,
		final String animeNfoId, final Long dateRecordUpdated,
		final List<Long> characterIdList, final List<Long> creatorIdList,
		final List<Long> producerIdList, final List<String> producerNameList,
		final Long specialsCount, final Long creditsCount,
		final Long otherCount, final Long trailerCount,
		final Long parodyCount) {
		
		this.animeId = aid;
		this.year = year;
		this.type = type;
		if (relatedAidList != null) {
			this.relatedAidList = new Vector<String>(relatedAidList);
		}
		if (relatedAidType != null) {
			this.relatedAidType = new Vector<String>(relatedAidType);
		}
		if (categoryList != null) {
			this.categoryList = new Vector<String>(categoryList);
		}
		if (categoryWeightList != null) {
			this.categoryWeightList = new Vector<String>(categoryWeightList);
		}
		this.romajiName = romajiName;
		this.kanjiName = kanjiName;
		this.englishName = englishName;
		if (otherNames != null) {
			this.otherNames = new Vector<String>(otherNames);
		}
		if (shortNameList != null) {
			this.shortNameList = new Vector<String>(shortNameList);
		}
		if (synonymList != null) {
			this.synonymList = new Vector<String>(synonymList);
		}
		this.episodes = episodes;
		this.normalEpisodeCount = normalEpisodeCount;
		this.specialEpisodeCount = specialEpisodeCount;
		this.airDate = airDate;
		this.endDate = endDate;
		this.url = url;
		this.picname = picname;
		if (categoryIdList != null) {
			this.categoryIdList = new Vector<String>(categoryIdList);
		}
		this.rating = rating;
		this.voteCount = voteCount;
		this.tempRating = tempRating;
		this.tempVoteCount = tempVoteCount;
		this.averageReviewRating = averageReviewRating;
		this.reviewCount = reviewCount;
		if (awardList != null) {
			this.awardList = new Vector<String>(awardList);
		}
		this.is18PlusRestricted = is18PlusRestricted;
		this.animePlanetId = animePlanetId;
		this.annId = annId;
		this.allCinemaId = allCinemaId;
		this.animeNfoId = animeNfoId;
		this.dateRecordUpdated = dateRecordUpdated;
		if (characterIdList != null) {
			this.characterIdList = new Vector<Long>(characterIdList);
		}
		if (creatorIdList != null) {
			this.creatorIdList = new Vector<Long>(creatorIdList);
		}
		if (producerIdList != null) {
			this.producerIdList = new Vector<Long>(producerIdList);
		}
		if (producerNameList != null) {
			this.producerNameList = new Vector<String>(producerNameList);
		}
		this.specialsCount = specialsCount;
		this.creditsCount = creditsCount;
		this.otherCount = otherCount;
		this.trailerCount = trailerCount;
		this.parodyCount = parodyCount;
	}
	
	/**
	 * Returns the anime Id.
	 * @return The anime Id or <code>null</code>, if the anime id isn't set.
	 */
	public Long getAnimeId() {
		return this.animeId;
	}
	
	/**
	 * Sets the anime Id.
	 * @param animeId The anime Id.
	 */
	public void setAnimeId(final Long animeId) {
		this.animeId = animeId;
	}
	
	/**
	 * Returns the year.
	 * @return The year or <code>null</code>, if the year isn't set.
	 */
	public String getYear() {
		return this.year;
	}
	
	/**
	 * Sets the year.
	 * @param year The year.
	 */
	public void setYear(final String year) {
		this.year = year;
	}
	
	/**
	 * Returns the type.
	 * @return The type or <code>null</code>, if the type isn't set.
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * Sets the type.
	 * @param type The type.
	 */
	public void setType(final String type) {
		this.type = type;
	}
	
	/**
	 * Returns the related anime Id list.
	 * @return The related anime Id list or <code>null</code>, if the related
	 * anime Id list isn't set.
	 */
	public List<String> getRelatedAidList() {
		if (this.relatedAidList == null) {
			return null;
		}
		return (new Vector<String>(this.relatedAidList));
	}
	
	/**
	 * Sets the related anime Id list.
	 * @param relatedAidList The related anime Id list.
	 */
	public void setRelatedAidList(final List<String> relatedAidList) {
		if (relatedAidList == null) {
			this.relatedAidList = null;
		} else {
			this.relatedAidList = new Vector<String>(relatedAidList);
		}
	}
	
	/**
	 * Returns the related anime type.
	 * @return The related anime type or <code>null</code>, if the related anime
	 * type isn't set.
	 * @see RelatedType
	 */
	public List<String> getRelatedAidType() {
		if (this.relatedAidType == null) {
			return null;
		}
		return (new Vector<String>(this.relatedAidType));
	}
	
	/**
	 * Sets the related anime type.
	 * @param relatedAidType The related anime type.
	 * @see RelatedType
	 */
	public void setRelatedAidType(final List<String> relatedAidType) {
		if (relatedAidType == null) {
			this.relatedAidType = null;
		} else {
			this.relatedAidType = new Vector<String>(relatedAidType);
		}
	}
	
	/**
	 * Returns the category list.
	 * @return The category list or <code>null</code>, if the category list
	 * isn't set.
	 */
	public List<String> getCategoryList() {
		if (this.categoryList == null) {
			return null;
		}
		return (new Vector<String>(this.categoryList));
	}
	
	/**
	 * Sets the category list.
	 * @param categoryList The category list.
	 */
	public void setCategoryList(final List<String> categoryList) {
		if (categoryList == null) {
			this.categoryList = null;
		} else {
			this.categoryList = new Vector<String>(categoryList);
		}
	}
	
	/**
	 * Returns the category weight list.
	 * @return The category weight list or <code>null</code>, if the category
	 * weight list isn't set.
	 */
	public List<String> getCategoryWeightList() {
		if (this.categoryWeightList == null) {
			return null;
		}
		return (new Vector<String>(this.categoryWeightList));
	}
	
	/**
	 * Sets the category weight list.
	 * @param categoryWeightList The category weight list.
	 */
	public void setCategoryWeightList(final List<String> categoryWeightList) {
		if (categoryWeightList == null) {
			this.categoryWeightList = null;
		} else {
			this.categoryWeightList = new Vector<String>(categoryWeightList);
		}
	}
	
	/**
	 * Returns the romaji name.
	 * @return The romaji name or <code>null</code>, if the romaji name isn't
	 * set.
	 */
	public String getRomajiName() {
		return this.romajiName;
	}
	
	/**
	 * Sets the romaji name.
	 * @param romajiName The romaji name.
	 */
	public void setRomajiName(final String romajiName) {
		this.romajiName = romajiName;
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
	 * Returns the english name.
	 * @return The english name or <code>null</code>, if the english name isn't
	 * set.
	 */
	public String getEnglishName() {
		return this.englishName;
	}
	
	/**
	 * Sets the english name.
	 * @param englishName The english name.
	 */
	public void setEnglishName(final String englishName) {
		this.englishName = englishName;
	}
	
	/**
	 * Returns the other names list.
	 * @return The other names list or <code>null</code>, if the other names
	 * list isn't set.
	 */
	public List<String> getOtherNames() {
		if (this.otherNames == null) {
			return null;
		}
		return (new Vector<String>(this.otherNames));
	}
	
	/**
	 * Sets the other names list.
	 * @param otherNames The other names list.
	 */
	public void setOtherNames(final List<String> otherNames) {
		if (otherNames == null) {
			this.otherNames = null;
		} else {
			this.otherNames = new Vector<String>(otherNames);
		}
	}
	
	/**
	 * Returns the short name list.
	 * @return The short name list or <code>null</code>, if the short name list
	 * isn't set.
	 */
	public List<String> getShortNameList() {
		if (this.shortNameList == null) {
			return null;
		}
		return (new Vector<String>(this.shortNameList));
	}
	
	/**
	 * Sets the short name list.
	 * @param shortNameList The short name list.
	 */
	public void setShortNameList(final List<String> shortNameList) {
		if (shortNameList == null) {
			this.shortNameList = null;
		} else {
			this.shortNameList = new Vector<String>(shortNameList);
		}
	}
	
	/**
	 * Returns the synonym list.
	 * @return The synonym list or <code>null</code>, if the synonym list isn't
	 * set.
	 */
	public List<String> getSynonymList() {
		if (this.synonymList == null) {
			return null;
		}
		return (new Vector<String>(this.synonymList));
	}
	
	/**
	 * Sets the synonym list.
	 * @param synonymList The synonym list.
	 */
	public void setSynonymList(final List<String> synonymList) {
		if (synonymList == null) {
			this.synonymList = null;
		} else {
			this.synonymList = new Vector<String>(synonymList);
		}
	}
	
	/**
	 * Returns the episodes.
	 * @return The episodes or <code>null</code>, if the episodes aren't set. 
	 */
	public Long getEpisodes() {
		return this.episodes;
	}
	
	/**
	 * Sets the episodes.
	 * @param episodes The episodes.
	 */
	public void setEpisodes(final Long episodes) {
		this.episodes = episodes;
	}
	
	/**
	 * Returns the normal episode count.
	 * @return The normal episode count or <code>null</code>, if the normal
	 * episode count isn't set.
	 */
	public Long getNormalEpisodeCount() {
		return this.normalEpisodeCount;
	}
	
	/**
	 * Sets the normal episode count.
	 * @param normalEpisodeCount The normal episode count.
	 */
	public void setNormalEpisodeCount(final Long normalEpisodeCount) {
		this.normalEpisodeCount = normalEpisodeCount;
	}
	
	/**
	 * Returns the special episode count.
	 * @return The special episode count or <code>null</code>, if the special
	 * episode count isn't set.
	 */
	public Long getSpecialEpisodeCount() {
		return this.specialEpisodeCount;
	}
	
	/**
	 * Sets the special episode count.
	 * @param specialEpisodeCount The special episode count.
	 */
	public void setSpecialEpisodeCount(final Long specialEpisodeCount) {
		this.specialEpisodeCount = specialEpisodeCount; 
	}
	
	/**
	 * Returns the air date.
	 * @return The air date or <code>null</code>, if the air date isn't set.
	 */
	public Long getAirDate() {
		return this.airDate;
	}
	
	/**
	 * Sets the air date.
	 * @param airDate The air date.
	 */
	public void setAirDate(final Long airDate) {
		this.airDate = airDate;
	}
	
	/**
	 * Returns the end date.
	 * @return The end date or <code>null</code>, if the end date isn't set.
	 */
	public Long getEndDate() {
		return this.endDate;
	}
	
	/**
	 * Sets the end date.
	 * @param endDate The end date.
	 */
	public void setEndDate(final Long endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * Returns the url.
	 * @return The url or <code>null</code>, if the url isn't set.
	 */
	public String getUrl() {
		return this.url;
	}
	
	/**
	 * Sets the url.
	 * @param url The url.
	 */
	public void setUrl(final String url) {
		this.url = url;
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
	 * Returns the category Id list.
	 * @return The category Id list or <code>null</code>, if the category Id
	 * list isn't set.
	 */
	public List<String> getCategoryIdList() {
		if (this.categoryIdList == null) {
			return null;
		}
		return (new Vector<String>(this.categoryIdList));
	}
	
	/**
	 * Sets the category Id list.
	 * @param categoryIdList The category Id list.
	 */
	public void setCategoryIdList(final List<String> categoryIdList) {
		if (categoryIdList == null) {
			this.categoryIdList = null;
		} else {
			this.categoryIdList = new Vector<String>(categoryIdList);
		}
	}
	
	/**
	 * Returns the rating.
	 * @return The rating or <code>null</code>, if the rating isn't set.
	 */
	public Long getRating() {
		return this.rating;
	}
	
	/**
	 * Sets the rating.
	 * @param rating The rating.
	 */
	public void setRating(final Long rating) {
		this.rating = rating;
	}
	
	/**
	 * Returns the vote count.
	 * @return The vote count or <code>null</code>, if the vote isn't set.
	 */
	public Long getVoteCount() {
		return this.voteCount;
	}
	
	/**
	 * Sets the vote count.
	 * @param voteCount The vote count.
	 */
	public void setVoteCount(final Long voteCount) {
		this.voteCount = voteCount;
	}
	
	/**
	 * Returns the temporary rating.
	 * @return The temporary rating or <code>null</code>, if the temporary
	 * rating isn't set.
	 */
	public Long getTempRating() {
		return this.tempRating;
	}
	
	/**
	 * Sets the temporary rating.
	 * @param tempRating The temporary rating.
	 */
	public void setTempRating(final Long tempRating) {
		this.tempRating = tempRating;
	}
	
	/**
	 * Returns the temporary vote count.
	 * @return The temporary vote count or <code>null</code>, if the temporary
	 * vote count isn't set.
	 */
	public Long getTempVoteCount() {
		return this.tempVoteCount;
	}
	
	/**
	 * Sets the temporary vote count.
	 * @param tempVoteCount The temporary vote count.
	 */
	public void setTempVoteCount(final Long tempVoteCount) {
		this.tempVoteCount = tempVoteCount;
	}
	
	/**
	 * Returns the average review rating.
	 * @return The average review rating or <code>null</code>, if the average
	 * review rating isn't set.
	 */
	public Long getAverageReviewRating() {
		return this.averageReviewRating;
	}
	
	/**
	 * Sets the average review rating.
	 * @param averageReviewRating The average review rating.
	 */
	public void setAverageReviewRating(final Long averageReviewRating) {
		this.averageReviewRating = averageReviewRating;
	}
	
	/**
	 * Returns the review count.
	 * @return The review count or <code>null</code>, if the review count isn't
	 * set.
	 */
	public Long getReviewCount() {
		return this.reviewCount;
	}
	
	/**
	 * Sets the review count.
	 * @param reviewCount The review count.
	 */
	public void setReviewCount(final Long reviewCount) {
		this.reviewCount = reviewCount;
	}
	
	/**
	 * Returns the award list.
	 * @return The award list or <code>null</code>, if the award list isn't set.
	 */
	public List<String> getAwardList() {
		if (this.awardList == null) {
			return this.awardList;
		}
		return (new Vector<String>(this.awardList));
	}
	
	/**
	 * Sets the award list.
	 * @param awardList The award list.
	 */
	public void setAwardList(final List<String> awardList) {
		if (awardList == null) {
			this.awardList = null;
		} else {
			this.awardList = new Vector<String>(awardList);
		}
	}
	
	/**
	 * Returns the 'is 18+ restricted' flag.
	 * @return The 'is 18+ restricted' flag or <code>null</code>, if the 'is 18+
	 * restricted' flag isn't set.
	 */
	public Boolean get18PlusRestricted() {
		return this.is18PlusRestricted;
	}
	
	/**
	 * Sets the 'is 18+ restricted' flag.
	 * @param is18PlusRestricted The 'is 18+ restricted' flag.
	 */
	public void set18PlusRestricted(final Boolean is18PlusRestricted) {
		this.is18PlusRestricted = is18PlusRestricted;
	}
	
	/**
	 * Returns the Anime Planet Id.
	 * @return The Anime Planet Id or <code>null</code>, if the Anime Planet Id
	 * isn't set.
	 */
	public Long getAnimePlanetId() {
		return this.animePlanetId;
	}
	
	/**
	 * Sets the Anime Planet Id.
	 * @param animePlanetId The Anime Planet Id.
	 */
	public void setAnimePlanetId(final Long animePlanetId) {
		this.animePlanetId = animePlanetId;
	}
	
	/**
	 * Returns the Ann Id.
	 * @return The Ann Id or <code>null</code>, if the Ann Id isn't set.
	 */
	public Long getAnnId() {
		return this.annId;
	}
	
	/**
	 * Sets the Ann Id.
	 * @param annId The Ann Id.
	 */
	public void setAnnId(final Long annId) {
		this.annId = annId;
	}
	
	/**
	 * Returns the AllCinema Id.
	 * @return The AllCinema Id or <code>null</code>, if the AllCinema Id isn't
	 * set.
	 */
	public Long getAllCinemaId() {
		return this.allCinemaId;
	}
	
	/**
	 * Sets the AllCinema Id.
	 * @param allCinemaId The AllCinema Id.
	 */
	public void setAllCinemaId(final Long allCinemaId) {
		this.allCinemaId = allCinemaId;
	}
	
	/**
	 * Returns the AnimeNfo Id.
	 * @return The AnimeNfo Id or <code>null</code>, if the AnimeNfo Id isn't
	 * set.
	 */
	public String getAnimeNfoId() {
		return this.animeNfoId;
	}
	
	/**
	 * Sets the AnimeNfo Id.
	 * @param animeNfoId The AnimeNfo Id.
	 */
	public void setAnimeNfoId(final String animeNfoId) {
		this.animeNfoId = animeNfoId;
	}
	
	/**
	 * Returns the record update date.
	 * @return The record update date or <code>null</code>, if the record update
	 * date isn't set.
	 */
	public Long getDateRecordUpdated() {
		return this.dateRecordUpdated;
	}
	
	/**
	 * Sets the record update date.
	 * @param dateRecordUpdated The record update date.
	 */
	public void setDateRecordUpdated(final Long dateRecordUpdated) {
		this.dateRecordUpdated = dateRecordUpdated;
	}
	
	/**
	 * Returns the character Id list.
	 * @return The character Id list or <code>null</code>, if the character Id
	 * list isn't set.
	 */
	public List<Long> getCharacterIdList() {
		if (this.characterIdList == null) {
			return null;
		}
		return (new Vector<Long>(this.characterIdList));
	}
	
	/**
	 * Sets the character Id list.
	 * @param characterIdList The character Id list.
	 */
	public void setCharacterIdList(final List<Long> characterIdList) {
		if (characterIdList == null) {
			this.characterIdList = null;
		} else {
			this.characterIdList = new Vector<Long>(characterIdList);
		}
	}
	
	/**
	 * Returns the creator Id list.
	 * @return The creator Id list or <code>null</code>, if the creator Id list
	 * isn't set.
	 */
	public List<Long> getCreatorIdList() {
		if (this.creatorIdList == null) {
			return null;
		}
		return (new Vector<Long>(this.creatorIdList));
	}
	
	/**
	 * Sets the creator Id list.
	 * @param creatorIdList The creator Id list.
	 */
	public void setCreatorIdList(final List<Long> creatorIdList) {
		if (creatorIdList == null) {
			this.creatorIdList = null;
		} else {
			this.creatorIdList = new Vector<Long>(creatorIdList);
		}
	}
	
	/**
	 * Returns the producer Id list.
	 * @return The producer Id list or <code>null</code>, if the producer Id
	 * list isn't set.
	 */
	public List<Long> getProducerIdList() {
		if (this.producerIdList == null) {
			return null;
		}
		return (new Vector<Long>(this.producerIdList));
	}
	
	/**
	 * Sets the producer Id list.
	 * @param producerIdList The producer Id list.
	 */
	public void setProducerIdList(final List<Long> producerIdList) {
		if (producerIdList == null) {
			this.producerIdList = null;
		} else {
			this.producerIdList = new Vector<Long>(producerIdList);
		}
	}
	
	/**
	 * Returns the producer name list.
	 * @return The producer name list or <code>null</code>, if the producer name
	 * list isn't set.
	 */
	public List<String> getProducerNameList() {
		if (this.producerNameList == null) {
			return null;
		}
		return (new Vector<String>(this.producerNameList));
	}
	
	/**
	 * Sets the producer name list.
	 * @param producerNameList The producer name list.
	 */
	public void setProducerNameList(final List<String> producerNameList) {
		if (producerNameList == null) {
			this.producerNameList = null;
		} else {
			this.producerNameList = new Vector<String>(producerNameList);
		}
	}
	
	/**
	 * Returns the specials count.
	 * @return The specials count or <code>null</code>, if the specials count
	 * isn't set.
	 */
	public Long getSpecialsCount() {
		return this.specialsCount;
	}
	
	/**
	 * Sets the specials count.
	 * @param specialsCount The specials count.
	 */
	public void setSpecialsCount(final Long specialsCount) {
		this.specialsCount = specialsCount;
	}
	
	/**
	 * Returns the credits count.
	 * @return The credits count or <code>null</code>, if the credits count
	 * isn't set.
	 */
	public Long getCreditsCount() {
		return this.creditsCount;
	}
	
	/**
	 * Sets the credits count.
	 * @param creditsCount The credits count.
	 */
	public void setCreditsCount(final Long creditsCount) {
		this.creditsCount = creditsCount;
	}
	
	/**
	 * Returns the other count.
	 * @return The other count or <code>null</code>, if the other count isn't
	 * set.
	 */
	public Long getOtherCount() {
		return this.otherCount;
	}
	
	/**
	 * Sets the other count.
	 * @param otherCount The other count.
	 */
	public void setOtherCount(final Long otherCount) {
		this.otherCount = otherCount;
	}
	
	/**
	 * Returns the trailer count.
	 * @return The trailer count or <code>null</code>, if the trailer count
	 * isn't set.
	 */
	public Long getTrailerCount() {
		return this.trailerCount;
	}
	
	/**
	 * Sets the trailer count.
	 * @param trailerCount The trailer count.
	 */
	public void setTrailerCount(final Long trailerCount) {
		this.trailerCount = trailerCount;
	}
	
	/**
	 * Returns the parody count.
	 * @return The parody count or <code>null</code>, if the parody count isn't
	 * set.
	 */
	public Long getParodyCount() {
		return this.parodyCount;
	}
	
	/**
	 * Sets the parody count.
	 * @param parodyCount The parody count.
	 */
	public void setParodyCount(final Long parodyCount) {
		this.parodyCount = parodyCount;
	}
	
	public int hashCode() {
		int result = 17;
		
		result = ObjectKit.hash(this.animeId, result);
		return result;
	}
	
	public boolean equals(final Object obj) {
		Anime anime;
		
		if (obj instanceof Anime) {
			anime = (Anime) obj;
			if (ObjectKit.equals(anime.animeId, this.animeId)) {
				return true;
			}
		}
		return false;
	}
}
