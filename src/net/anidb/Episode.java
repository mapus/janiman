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
 * An episode of an anime.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 19.12.2009
 */
public class Episode implements Comparable<Episode> {
	/** The episode Id. */
	private Long episodeId;
	/** The anime. */
	private Anime anime;
	/** The length in minutes. */
	private Long length;
	/** The rating. */
	private Long rating;
	/** The votes. */
	private Long votes;
	/** The episode number. */
	private String episodeNumber;
	/** The english title. */
	private String englishTitle;
	/** The romaji title. */
	private String romajiTitle;
	/** The kanji title. */
	private String kanjiTitle;
	/** The air date. */
	private Long aired;
	
	/**
	 * Creates an episode.
	 */
	public Episode() {
		super();
	}
	
	/**
	 * Creates an episode.
	 * @param episodeId The episode Id.
	 * @param anime The anime.
	 * @param length The length in minutes.
	 * @param rating The rating.
	 * @param votes The votes.
	 * @param episodeNumber The episode number.
	 * @param englishTitle The english title.
	 * @param romajiTitle The romaji title.
	 * @param kanjiTitle The kanji title.
	 * @param aired The air date.
	 */
	public Episode(final Long episodeId, final Anime anime, final Long length,
		final Long rating, final Long votes, final String episodeNumber,
		final String englishTitle, final String romajiTitle,
		final String kanjiTitle, final Long aired) {
		
		super();
		this.episodeId = episodeId;
		this.anime = anime;
		this.length = length;
		this.rating = rating;
		this.votes = votes;
		this.episodeNumber = episodeNumber;
		this.englishTitle = englishTitle;
		this.romajiTitle = romajiTitle;
		this.kanjiTitle = kanjiTitle;
		this.aired = aired;
	}
	
	/**
	 * Returns the episode Id.
	 * @return The episode Id or <code>null</code>, if the episode Id isn't set.
	 */
	public Long getEpisodeId() {
		return this.episodeId;
	}
	
	/**
	 * Sets the episode Id.
	 * @param episodeId The episode Id.
	 */
	public void setEpisodeId(final Long episodeId) {
		this.episodeId = episodeId;
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
	 * Returns the length in minutes.
	 * @return The length or <code>null</code>, if the length isn't set.
	 */
	public Long getLength() {
		return this.length;
	}
	
	/**
	 * Sets the length in minutes.
	 * @param length The length.
	 */
	public void setLength(final Long length) {
		this.length = length;
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
	 * Returns the votes.
	 * @return The votes or <code>null</code>, if the votes aren't set.
	 */
	public Long getVotes() {
		return this.votes;
	}
	
	/**
	 * Sets the votes.
	 * @param votes The votes.
	 */
	public void setVotes(final Long votes) {
		this.votes = votes;
	}
	
	/**
	 * Returns the episode number.
	 * @return The episode number or <code>null</code>, if the episode number
	 * isn't set.
	 * @see EpisodeType
	 */
	public String getEpisodeNumber() {
		return this.episodeNumber;
	}
	
	/**
	 * Sets the episode number.
	 * @param episodeNumber The episode number.
	 * @see EpisodeType
	 */
	public void setEpisodeNumber(final String episodeNumber) {
		this.episodeNumber = episodeNumber;
	}
	
	/**
	 * Returns the english title.
	 * @return The english title or <code>null</code>, if the english title
	 * isn't set.
	 */
	public String getEnglishTitle() {
		return this.englishTitle;
	}
	
	/**
	 * Sets the english title.
	 * @param englishTitle The english title.
	 */
	public void setEnglishTitle(final String englishTitle) {
		this.englishTitle = englishTitle;
	}
	
	/**
	 * Returns the romaji title.
	 * @return The romaji title or <code>null</code>, if the romaji title isn't
	 * set.
	 */
	public String getRomajiTitle() {
		return this.romajiTitle;
	}
	
	/**
	 * Sets the romaji title.
	 * @param romajiTitle The romaji title.
	 */
	public void setRomajiTitle(final String romajiTitle) {
		this.romajiTitle = romajiTitle;
	}
	
	/**
	 * Returns the kanji title.
	 * @return The kanji title or <code>null</code>, if the kanji title isn't
	 * set.
	 */
	public String getKanjiTitle() {
		return this.kanjiTitle;
	}
	
	/**
	 * Sets the kanji title.
	 * @param kanjiTitle The kanji title.
	 */
	public void setKanjiTitle(final String kanjiTitle) {
		this.kanjiTitle = kanjiTitle;
	}
	
	/**
	 * Returns the air date.
	 * @return The air date or <code>null</code>, if the air date isn't set.
	 */
	public Long getAired() {
		return this.aired;
	}
	
	/**
	 * Sets the air date.
	 * @param aired The air date.
	 */
	public void setAired(final Long aired) {
		this.aired = aired;
	}
	
	public int hashCode() {
		int result = 17;
		
		result = ObjectKit.hash(this.episodeId, result);
		return result;
	}
	
	public boolean equals(final Object obj) {
		Episode episode;
		
		if (obj instanceof Episode) {
			episode = (Episode) obj;
			if (ObjectKit.equals(episode.episodeId, this.episodeId)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int compareTo(Episode o) {
		return Integer.valueOf(this.getEpisodeNumber()).compareTo(Integer.valueOf(o.getEpisodeNumber()));
	}
}
