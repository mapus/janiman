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
 * A group.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 19.12.2009
 */
public class Group {
	/** The group Id. */
	private Long groupId;
	/** The rating. */
	private Long rating;
	/** The votes. */
	private Long votes;
	/** The anime count. */
	private Long animeCount;
	/** The file count. */
	private Long fileCount;
	/** The name. */
	private String name;
	/** The short name. */
	private String shortName;
	/** The IRC channel. */
	private String ircChannel;
	/** The IRC server. */
	private String ircServer;
	/** The URL. */
	private String url;
	/** The picname. */
	private String picname;
	
	/**
	 * Creates a group.
	 */
	public Group() {
		super();
	}
	
	/**
	 * Creates a group.
	 * @param groupId The group Id.
	 * @param rating The rating.
	 * @param votes The votes.
	 * @param animeCount The anime count.
	 * @param fileCount The file count.
	 * @param name The name.
	 * @param shortName The short name.
	 * @param ircChannel The IRC channel.
	 * @param ircServer The IRC server.
	 * @param url The URL.
	 * @param picname The picname.
	 */
	public Group(final Long groupId, final Long rating, final Long votes,
		final Long animeCount, final Long fileCount, final String name,
		final String shortName, final String ircChannel, final String ircServer,
		final String url, final String picname) {
		
		super();
		this.groupId = groupId;
		this.rating = rating;
		this.votes = votes;
		this.animeCount = animeCount;
		this.fileCount = fileCount;
		this.name = name;
		this.shortName = shortName;
		this.ircChannel = ircChannel;
		this.ircServer = ircServer;
		this.url = url;
		this.picname = picname;
	}
	
	/**
	 * Returns the group Id.
	 * @return The group Id or <code>null</code>, if the group Id isn't set.
	 */
	public Long getGroupId() {
		return this.groupId;
	}
	
	/**
	 * Sets the group Id.
	 * @param groupId The group Id.
	 */
	public void setGroupId(final Long groupId) {
		this.groupId = groupId;
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
	 * Returns the anime count.
	 * @return The anime count or <code>null</code>, if the anime count isn't
	 * set.
	 */
	public Long getAnimeCount() {
		return this.animeCount;
	}
	
	/**
	 * Sets the anime count.
	 * @param animeCount The anime count.
	 */
	public void setAnimeCount(final Long animeCount) {
		this.animeCount = animeCount;
	}
	
	/**
	 * Returns the file count.
	 * @return The file count or <code>null</code>, if the file count isn't set.
	 */
	public Long getFileCount() {
		return this.fileCount;
	}
	
	/**
	 * Sets the file count.
	 * @param fileCount The file count.
	 */
	public void setFileCount(final Long fileCount) {
		this.fileCount = fileCount;
	}
	
	/**
	 * Returns the name.
	 * @return The name or <code>null</code>, if the name isn't set.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the name.
	 * @param name The name.
	 */
	public void setName(final String name) {
		this.name = name;
	}
	
	/**
	 * Returns the short name.
	 * @return The short name or <code>null</code>, if the name isn't set.
	 */
	public String getShortName() {
		return this.shortName;
	}
	
	/**
	 * Sets the short name.
	 * @param shortName The short name.
	 */
	public void setShortName(final String shortName) {
		this.shortName = shortName;
	}
	
	/**
	 * Returns the IRC channel.
	 * @return The IRC channel or <code>null</code>, if the IRC channel isn't
	 * set.
	 */
	public String getIrcChannel() {
		return this.ircChannel;
	}
	
	/**
	 * Sets the IRC channel.
	 * @param ircChannel The IRC channel.
	 */
	public void setIrcChannel(final String ircChannel) {
		this.ircChannel = ircChannel;
	}
	
	/**
	 * Returns the IRC server.
	 * @return The IRC server or <code>null</code>, if the IRC server isn't set.
	 */
	public String getIrcServer() {
		return this.ircServer;
	}
	
	/**
	 * Sets the IRC server.
	 * @param ircServer The IRC server.
	 */
	public void setIrcServer(final String ircServer) {
		this.ircServer = ircServer;
	}
	
	/**
	 * Returns the URL.
	 * @return The URL or <code>null</code>, if the URL isn't set.
	 */
	public String getUrl() {
		return this.url;
	}
	
	/**
	 * Sets the URL.
	 * @param url The URL.
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
	
	public int hashCode() {
		int result = 17;
		
		result = ObjectKit.hash(this.groupId, result);
		return result;
	}
	
	public boolean equals(final Object obj) {
		Group group;
		
		if (obj instanceof Group) {
			group = (Group) obj;
			if (ObjectKit.equals(group.groupId, this.groupId)) {
				return true;
			}
		}
		return false;
	}
}
