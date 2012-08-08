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
 * The group status for an anime.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 19.12.2009
 */
public class GroupStatus {
	/** The anime. */
	private Anime anime;
	/** The group. */
	private Group group;
	/** The completion state. */
	private Integer completionState;
	/** The last episode number. */
	private Integer lastEpisodeNumber;
	/** The rating. */
	private Integer rating;
	/** The votes. */
	private Integer votes;
	/** The episode ranges list. */
	private List<String> episodeRanges;
	
	/**
	 * Creates a group status.
	 */
	public GroupStatus() {
		super();
	}
	
	/**
	 * Creates a group status.
	 * @param anime The anime.
	 * @param group The group.
	 * @param completionState The completion state.
	 * @param lastEpisodeNumber The last episode number.
	 * @param rating The rating.
	 * @param votes The votes.
	 * @param episodeRanges The episode ranges list.
	 */
	public GroupStatus(final Anime anime, final Group group,
		final Integer completionState, final Integer lastEpisodeNumber,
		final Integer rating, final Integer votes,
		final List<String> episodeRanges) {
		
		super();
		this.anime = anime;
		this.group = group;
		this.completionState = completionState;
		this.lastEpisodeNumber = lastEpisodeNumber;
		this.rating = rating;
		this.votes = votes;
		if (episodeRanges != null) {
			this.episodeRanges = new Vector<String>(episodeRanges);
		}
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
	 * Returns the group.
	 * @return The group or <code>null</code>, if the group isn't set.
	 */
	public Group getGroup() {
		return this.group;
	}
	
	/**
	 * Sets the group.
	 * @param group The group.
	 */
	public void setGroup(final Group group) {
		this.group = group;
	}
	
	/**
	 * Returns the completion state.
	 * @return The completion state or <code>null</code>, if the completion
	 * state isn't set.
	 * @see GroupStatusState
	 */
	public Integer getCompletionState() {
		return this.completionState;
	}
	
	/**
	 * Sets the completion state.
	 * @param completionState The completion state.
	 * @see GroupStatusState
	 */
	public void setCompletionState(final Integer completionState) {
		this.completionState = completionState;
	}
	
	/**
	 * Returns the last episode number.
	 * @return The last episode number or <code>null</code>, if the last episode
	 * number isn't set.
	 */
	public Integer getLastEpisodeNumber() {
		return this.lastEpisodeNumber;
	}
	
	/**
	 * Sets the last episode number.
	 * @param lastEpisodeNumber The last episode number.
	 */
	public void setLastEpisodeNumber(final Integer lastEpisodeNumber) {
		this.lastEpisodeNumber = lastEpisodeNumber;
	}
	
	/**
	 * Returns the rating.
	 * @return The rating or <code>null</code>, if the rating isn't set.
	 */
	public Integer getRating() {
		return this.rating;
	}
	
	/**
	 * Sets the rating.
	 * @param rating The rating.
	 */
	public void setRating(final Integer rating) {
		this.rating = rating;
	}
	
	/**
	 * Returns the votes.
	 * @return The votes or <code>null</code>, if the votes aren't set.
	 */
	public Integer getVotes() {
		return this.votes;
	}
	
	/**
	 * Sets the votes.
	 * @param votes The votes.
	 */
	public void setVotes(final Integer votes) {
		this.votes = votes;
	}
	
	/**
	 * Returns the episode ranges list.
	 * @return The episode ranges list or <code>null</code>, if the episode
	 * ranges list isn't set.
	 */
	public List<String> getEpisodeRanges() {
		if (this.episodeRanges == null) {
			return null;
		}
		return (new Vector<String>(this.episodeRanges));
	}
	
	/**
	 * Sets the episode ranges list.
	 * @param episodeRanges The episode ranges list.
	 */
	public void setEpisodeRanges(final List<String> episodeRanges) {
		if (episodeRanges == null) {
			this.episodeRanges = null;
		} else {
			this.episodeRanges = new Vector<String>(episodeRanges);
		}
	}
	
	public int hashCode() {
		int result = 17;
		
		result = ObjectKit.hash(this.anime, result);
		result = ObjectKit.hash(this.group, result);
		return result;
	}
	
	public boolean equals(final Object obj) {
		GroupStatus status;
		
		if (obj instanceof GroupStatus) {
			status = (GroupStatus) obj;
			if (ObjectKit.equals(status.anime, this.anime)
					&& ObjectKit.equals(status.group, this.group)) {
				return true;
			}
		}
		return false;
	}
}
