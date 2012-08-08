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
 * A file of an anime episode.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 19.12.2009
 */
public class File {
	/** The file Id. */
	private Long fileId;
	/** The episode. */
	private Episode episode;
	/** The group. */
	private Group group;
	/** The MyList Id. */
	private Long myListId;
	/** The list of other episodes. */
	private List<RelatedEpisode> otherEpisodes;
	/** The 'is deprecated' flag. */
	private Boolean deprecated;
	/** The state. */
	private Integer state;
	/** The size. */
	private Long size;
	/** The ed2k hash. */
	private String ed2k;
	/** The md5 hash. */
	private String md5;
	/** The sha1 hash. */
	private String sha1;
	/** The crc32 hash. */
	private String crc32;
	/** The quality. */
	private String quality;
	/** The source. */
	private String source;
	/** The audio codec list. */
	private List<String> audioCodecList;
	/** The audio bitrate list. */
	private List<Long> audioBitrateList;
	/** The video codec. */
	private String videoCodec;
	/** The video bitrate. */
	private Long videoBitrate;
	/** The video resolution. */
	private String videoResolution;
	/** The file type. */
	private String fileType;
	/** The dub language. */
	private String dubLanguage;
	/** The sub language. */
	private String subLanguage;
	/** The length in seconds. */
	private Long lengthInSeconds;
	/** The description. */
	private String description;
	/** The aired date. */
	private Long airedDate;
	/** The AniDB file name. */
	private String aniDbFileName;
	
	/**
	 * Creates a file.
	 */
	public File() {
		super();
	}
	
	/**
	 * Creates a file.
	 * @param fileId The file Id.
	 * @param episode The episode.
	 * @param group The group.
	 * @param myListId The MyList Id.
	 * @param otherEpisodes The list of other episodes.
	 * @param deprecated The 'is deprecated' flag.
	 * @param state The state.
	 * @param size The size.
	 * @param ed2k The ed2k hash.
	 * @param md5 The md5 hash.
	 * @param sha1 The sha1 hash.
	 * @param crc32 The crc32 hash.
	 * @param quality The quality.
	 * @param source The source.
	 * @param audioCodecList The audio codec list.
	 * @param audioBitrateList The audio bitrate list.
	 * @param videoCodec The video codec.
	 * @param videoBitrate The video bitrate.
	 * @param videoResolution The video resolution.
	 * @param fileType The file type.
	 * @param dubLanguage The dub language.
	 * @param subLanguage The sub language.
	 * @param lengthInSeconds The length in seconds.
	 * @param description The description.
	 * @param airedDate The aired date.
	 * @param aniDbFileName The AniDB file name.
	 */
	public File(final Long fileId, final Episode episode, final Group group,
		final Long myListId, final List<RelatedEpisode> otherEpisodes,
		final Boolean deprecated, final Integer state, final Long size,
		final String ed2k, final String md5, final String sha1,
		final String crc32, final String quality, final String source,
		final List<String> audioCodecList, final List<Long> audioBitrateList,
		final String videoCodec, final Long videoBitrate,
		final String videoResolution, final String fileType,
		final String dubLanguage, final String subLanguage,
		final Long lengthInSeconds, final String description,
		final Long airedDate, final String aniDbFileName) {
		
		super();
		this.fileId = fileId;
		this.episode = episode;
		this.group = group;
		this.myListId = myListId;
		if (otherEpisodes != null) {
			this.otherEpisodes = new Vector<RelatedEpisode>(otherEpisodes);
		}
		this.deprecated = deprecated;
		this.state = state;
		this.size = size;
		this.ed2k = ed2k;
		this.md5 = md5;
		this.sha1 = sha1;
		this.crc32 = crc32;
		this.quality = quality;
		this.source = source;
		if (audioCodecList != null) {
			this.audioCodecList = new Vector<String>(audioCodecList);
		}
		if (audioBitrateList != null) {
			this.audioBitrateList = new Vector<Long>(audioBitrateList);
		}
		this.videoCodec = videoCodec;
		this.videoBitrate = videoBitrate;
		this.videoResolution = videoResolution;
		this.fileType = fileType;
		this.dubLanguage = dubLanguage;
		this.subLanguage = subLanguage;
		this.lengthInSeconds = lengthInSeconds;
		this.description = description;
		this.airedDate = airedDate;
		this.aniDbFileName = aniDbFileName;
	}
	
	/**
	 * Returns the file Id.
	 * @return The file Id or <code>null</code>, if the file Id isn't set.
	 */
	public Long getFileId() {
		return this.fileId;
	}
	
	/**
	 * Sets the file Id.
	 * @param fileId The file Id.
	 */
	public void setFileId(final Long fileId) {
		this.fileId = fileId;
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
	 * Returns the MyList Id.
	 * @return The MyList Id or <code>null</code>, if the MyList Id isn't set.
	 */
	public Long getMyListId() {
		return this.myListId;
	}
	
	/**
	 * Sets the MyList Id.
	 * @param myListId The MyList Id.
	 */
	public void setMyListId(final Long myListId) {
		this.myListId = myListId;
	}
	
	/**
	 * Returns the list of other episodes.
	 * @return The list of other episodes if the list of other episodes isn't
	 * set.
	 */
	public List<RelatedEpisode> getOtherEpisodes() {
		if (this.otherEpisodes == null) {
			return null;
		}
		return (new Vector<RelatedEpisode>(this.otherEpisodes));
	}
	
	/**
	 * Sets the list of other episodes.
	 * @param otherEpisodes The list of other episodes.
	 */
	public void setOtherEpisodes(final List<RelatedEpisode> otherEpisodes) {
		if (otherEpisodes == null) {
			this.otherEpisodes = null;
		} else {
			this.otherEpisodes = new Vector<RelatedEpisode>(otherEpisodes);
		}
	}
	
	/**
	 * Returns the 'is deprecated' flag.
	 * @return The 'is deprecated' flag or <code>null</code>, if the 'is
	 * deprecated' flag isn't set.
	 */
	public Boolean getDeprecated() {
		return this.deprecated;
	}
	
	/**
	 * Sets the 'is deprecated' flag.
	 * @param deprecated The 'is deprecated' flag.
	 */
	public void setDeprecated(final Boolean deprecated) {
		this.deprecated = deprecated;
	}
	
	/**
	 * Returns the state.
	 * @return The state or <code>null</code>, if the state isn't set.
	 * @see FileState
	 */
	public Integer getState() {
		return this.state;
	}
	
	/**
	 * Sets the state.
	 * @param state The state.
	 * @see FileState
	 */
	public void setState(final Integer state) {
		this.state = state;
	}
	
	/**
	 * Returns the size.
	 * @return The size or <code>null</code>, if the size isn't set.
	 */
	public Long getSize() {
		return this.size;
	}
	
	/**
	 * Sets the size.
	 * @param size The size.
	 */
	public void setSize(final Long size) {
		this.size = size;
	}
	
	/**
	 * Returns the ed2k hash.
	 * @return The ed2k hash or <code>null</code>, if the ed2k hash isn't set.
	 */
	public String getEd2k() {
		return this.ed2k;
	}
	
	/**
	 * Sets the ed2k hash.
	 * @param ed2k The ed2k hash.
	 */
	public void setEd2k(final String ed2k) {
		this.ed2k = ed2k;
	}
	
	/**
	 * Returns the md5 hash.
	 * @return The md5 hash or <code>null</code>, if the md5 hash isn't set.
	 */
	public String getMd5() {
		return this.md5;
	}
	
	/**
	 * Sets the md5 hash.
	 * @param md5 The md5 hash.
	 */
	public void setMd5(final String md5) {
		this.md5 = md5;
	}
	
	/**
	 * Returns the sha1 hash.
	 * @return The sha1 hash or <code>null</code>, if the sha1 hash isn't set.
	 */
	public String getSha1() {
		return this.sha1;
	}
	
	/**
	 * Sets the sha1 hash.
	 * @param sha1 The sha1 hash.
	 */
	public void setSha1(final String sha1) {
		this.sha1 = sha1;
	}
	
	/**
	 * Returns the crc32 hash.
	 * @return The crc32 hash or <code>null</code>, if the crc32 hash isn't set.
	 */
	public String getCrc32() {
		return this.crc32;
	}
	
	/**
	 * Sets the crc32 hash.
	 * @param crc32 The crc32 hash.
	 */
	public void setCrc32(final String crc32) {
		this.crc32 = crc32;
	}
	
	/**
	 * Returns the quality.
	 * @return The quality or <code>null</code>, if the quality isn't set.
	 */
	public String getQuality() {
		return this.quality;
	}
	
	/**
	 * Sets the quality.
	 * @param quality The quality.
	 */
	public void setQuality(final String quality) {
		this.quality = quality;
	}
	
	/**
	 * Returns the source.
	 * @return The source or <code>null</code>, if the source isn't set.
	 */
	public String getSource() {
		return this.source;
	}
	
	/**
	 * Sets the source.
	 * @param source The source.
	 */
	public void setSource(final String source) {
		this.source = source;
	}
	
	/**
	 * Returns the audio codec list.
	 * @return The audio codec list or <code>null</code>, if the audio codec
	 * list isn't set.
	 */
	public List<String> getAudioCodecList() {
		if (this.audioCodecList == null) {
			return null;
		}
		return (new Vector<String>(this.audioCodecList));
	}
	
	/**
	 * Sets the audio codec list.
	 * @param audioCodecList The audio codec list.
	 */
	public void setAudioCodecList(final List<String> audioCodecList) {
		if (audioCodecList == null) {
			this.audioCodecList = null;
		} else {
			this.audioCodecList = new Vector<String>(audioCodecList);
		}
	}
	
	/**
	 * Returns the audio bitrate list.
	 * @return The audio bitrate list or <code>null</code>, if the audio bitrate
	 * list isn't set.
	 */
	public List<Long> getAudioBitrateList() {
		if (this.audioBitrateList == null) {
			return null;
		}
		return this.audioBitrateList;
	}
	
	/**
	 * Sets the audio bitrate list.
	 * @param audioBitrateList The audio bitrate list.
	 */
	public void setAudioBitrateList(final List<Long> audioBitrateList) {
		if (audioBitrateList == null) {
			this.audioBitrateList = null;
		} else {
			this.audioBitrateList = new Vector<Long>(audioBitrateList);
		}
	}
	
	/**
	 * Returns the video codec.
	 * @return The video codec or <code>null</code>, if the video codec isn't
	 * set.
	 */
	public String getVideoCodec() {
		return this.videoCodec;
	}
	
	/**
	 * Sets the video codec.
	 * @param videoCodec The video codec.
	 */
	public void setVideoCodec(final String videoCodec) {
		this.videoCodec = videoCodec;
	}
	
	/**
	 * Returns the video bitrate.
	 * @return The video bitrate or <code>null</code>, if the video bitrate
	 * isn't set.
	 */
	public Long getVideoBirate() {
		return this.videoBitrate;
	}
	
	/**
	 * Sets the video bitrate.
	 * @param videoBitrate The video bitrate.
	 */
	public void setVideoBitrate(final Long videoBitrate) {
		this.videoBitrate = videoBitrate;
	}
	
	/**
	 * Returns the video resolution.
	 * @return The video resolution or <code>null</code>, if video resolution
	 * isn't set.
	 */
	public String getVideoResolution() {
		return this.videoResolution;
	}
	
	/**
	 * Sets the video resolution.
	 * @param videoResolution The video resolution.
	 */
	public void setVideoResolution(final String videoResolution) {
		this.videoResolution = videoResolution;
	}
	
	/**
	 * Returns the file type.
	 * @return The file type or <code>null</code>, if the file type isn't set.
	 */
	public String getFileType() {
		return this.fileType;
	}
	
	/**
	 * Sets the file type.
	 * @param fileType The file type.
	 */
	public void setFileType(final String fileType) {
		this.fileType = fileType;
	}
	
	/**
	 * Returns the dub language.
	 * @return The dub language or <code>null</code>, if the dub language isn't
	 * set.
	 */
	public String getDubLanguage() {
		return this.dubLanguage;
	}
	
	/**
	 * Sets the dub language.
	 * @param dubLanguage The dub language.
	 */
	public void setDubLanguage(final String dubLanguage) {
		this.dubLanguage = dubLanguage;
	}
	
	/**
	 * Returns the sub language.
	 * @return The sub language or <code>null</code>, if the sub language isn't
	 * set.
	 */
	public String getSubLanguage() {
		return this.subLanguage;
	}
	
	/**
	 * Sets the sub language.
	 * @param subLanguage The sub language.
	 */
	public void setSubLanguage(final String subLanguage) {
		this.subLanguage = subLanguage;
	}
	
	/**
	 * Returns the length in seconds.
	 * @return The length or <code>null</code>, if the length isn't set.
	 */
	public Long getLengthInSeconds() {
		return this.lengthInSeconds;
	}
	
	/**
	 * Sets the length in seconds.
	 * @param lengthInSeconds The length.
	 */
	public void setLengthInSeconds(final Long lengthInSeconds) {
		this.lengthInSeconds = lengthInSeconds;
	}
	
	/**
	 * Returns the description.
	 * @return The description or <code>null</code>, if the description isn't
	 * set.
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Sets the description.
	 * @param description The description.
	 */
	public void setDescription(final String description) {
		this.description = description;
	}
	
	/**
	 * Returns the aired date.
	 * @return The aired date or <code>null</code>, if the aired date isn't set.
	 */
	public Long getAiredDate() {
		return this.airedDate;
	}
	
	/**
	 * Sets the aired date.
	 * @param airedDate The aired date.
	 */
	public void setAiredDate(final Long airedDate) {
		this.airedDate = airedDate;
	}
	
	/**
	 * Returns the AniDB file name.
	 * @return The AniDB file name or <code>null</code>, if the AniDB file name
	 * isn't set.
	 */
	public String getAniDbFileName() {
		return this.aniDbFileName;
	}
	
	/**
	 * Sets the AniDB file name.
	 * @param aniDbFileName The AniDB file name.
	 */
	public void setAniDbFileName(final String aniDbFileName) {
		this.aniDbFileName = aniDbFileName;
	}
	
	public int hashCode() {
		int result = 17;
		
		result = ObjectKit.hash(this.fileId, result);
		return result;
	}
	
	public boolean equals(final Object obj) {
		File file;
		
		if (obj instanceof File) {
			file = (File) obj;
			if (ObjectKit.equals(file.fileId, this.fileId)) {
				return true;
			}
		}
		return false;
	}
}
