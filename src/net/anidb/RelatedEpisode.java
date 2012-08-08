/*
 * Java AniDB API - A Java API for AniDB.net
 * (c) Copyright 2010 grizzlyxp
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
 * A file related episode.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 08.01.2010
 * @see File#getOtherEpisodes()
 * @see File#setOtherEpisodes(java.util.List)
 */
public class RelatedEpisode {
	/** The episode. */
	private Episode episode;
	/** The percentage of coverage. */
	private String coverage;
	
	/**
	 * Creates a file related episode.
	 */
	public RelatedEpisode() {
		super();
	}
	
	/**
	 * Creates a file related episode.
	 * @param episode The episode.
	 * @param coverage The percentage of coverage.
	 */
	public RelatedEpisode(final Episode episode, final String coverage) {
		super();
		this.episode = episode;
		this.coverage = coverage;
	}
	
	/**
	 * Returns the episode.
	 * @return The episode or <code>null</code>, if the episode isn't set.
	 */
	public Episode getEpisode() {
		return this.episode;
	}
	
	/**
	 * Sets the episode.
	 * @param episode The episode.
	 */
	public void setEpisode(final Episode episode) {
		this.episode = episode;
	}
	
	/**
	 * Returns the percentage of coverage.
	 * @return The percentage of coverage or <code>null</code>, if percentage of
	 * coverage isn't set.
	 */
	public String getCoverage() {
		return this.coverage;
	}
	
	/**
	 * Sets the percentage of coverage.
	 * @param coverage The percentage of coverage.
	 */
	public void setCoverage(final String coverage) {
		this.coverage = coverage;
	}
	
	public int hashCode() {
		int result = 17;
		
		result = ObjectKit.hash(this.episode, result);
		result = ObjectKit.hash(this.coverage, result);
		return result;
	}
	
	public boolean equals(final Object obj) {
		RelatedEpisode relatedEpisode;
		
		if (obj instanceof RelatedEpisode) {
			relatedEpisode = (RelatedEpisode) obj;
			if (ObjectKit.equals(relatedEpisode.episode, this.episode)
					&& ObjectKit.equals(relatedEpisode.coverage,
							this.coverage)) {
				return true;
			}
		}
		return false;
	}
}
