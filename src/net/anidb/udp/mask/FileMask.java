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
 * <p>The file mask for the <code>FILE</code> command.</p>
 * <p>With this mask you can specify which data fields are returned.</p>
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 07.12.2009
 */
public class FileMask extends Mask {
	/** A mask with all fields set. */
	public final static FileMask ALL = new FileMask(true, true, true, true,
			true, true, true, true, true, true, true, true, true, true, true,
			true, true, true, true, true, true, true, true, true, true, true);
	
	/** The anime Id. */
	private boolean animeId;
	/** The episode Id. */
	private boolean episodeId;
	/** The group Id. */
	private boolean groupId;
	/** The MyList Id. */
	private boolean myListId;
	/** The list of other episodes. */
	private boolean listOtherEpisodes;
	/** The 'is deprecated' flag. */
	private boolean deprecated;
	/** The state. */
	private boolean state;
	/** The size. */
	private boolean size;
	/** The ed2k hash. */
	private boolean ed2k;
	/** The md5 hash. */
	private boolean md5;
	/** The sha1 hash. */
	private boolean sha1;
	/** The crc32 hash. */
	private boolean crc32;
	/** The quality. */
	private boolean quality;
	/** The source. */
	private boolean source;
	/** The audio codec list. */
	private boolean audioCodecList;
	/** The audio bitrate list. */
	private boolean audioBitrateList;
	/** The video codec. */
	private boolean videoCodec;
	/** The video bitrate. */
	private boolean videoBitrate;
	/** The video resolution. */
	private boolean videoResolution;
	/** The file type. */
	private boolean fileType;
	/** The dub language. */
	private boolean dubLanguage;
	/** The sub language. */
	private boolean subLanguage;
	/** The length in seconds. */
	private boolean lengthInSeconds;
	/** The description. */
	private boolean description;
	/** The aired date. */
	private boolean airedDate;
	/** The AniDB file name. */
	private boolean aniDbFileName;
	
	/**
	 * Creates a file mask.
	 * @param animeId The anime Id.
	 * @param episodeId The episode Id.
	 * @param groupId The group Id.
	 * @param mylistId The MyList Id.
	 * @param listOtherEpisodes The list of other episodes.
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
	public FileMask(final boolean animeId, final boolean episodeId,
		final boolean groupId, final boolean mylistId,
		final boolean listOtherEpisodes, final boolean deprecated,
		final boolean state, final boolean size, final boolean ed2k,
		final boolean md5, final boolean sha1, final boolean crc32,
		final boolean quality, final boolean source,
		final boolean audioCodecList, final boolean audioBitrateList,
		final boolean videoCodec, final boolean videoBitrate,
		final boolean videoResolution, final boolean fileType,
		final boolean dubLanguage, final boolean subLanguage,
		final boolean lengthInSeconds, final boolean description,
		final boolean airedDate, final boolean aniDbFileName) {
		
		super();
		this.animeId = animeId;
		this.episodeId = episodeId;
		this.groupId = groupId;
		this.myListId = mylistId;
		this.listOtherEpisodes = listOtherEpisodes;
		this.deprecated = deprecated;
		this.state = state;
		this.size = size;
		this.ed2k = ed2k;
		this.md5 = md5;
		this.sha1 = sha1;
		this.crc32 = crc32;
		this.quality = quality;
		this.source = source;
		this.audioCodecList = audioCodecList;
		this.audioBitrateList = audioBitrateList;
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
	 * Returns the anime Id.
	 * @return The anime Id.
	 */
	public boolean isAnimeId() {
		return this.animeId;
	}
	
	/**
	 * Returns the episode Id.
	 * @return The episode Id.
	 */
	public boolean isEpisodeId() {
		return this.episodeId;
	}
	
	/**
	 * Returns the group Id.
	 * @return The group Id.
	 */
	public boolean isGroupId() {
		return this.groupId;
	}
	
	/**
	 * Returns the MyList Id.
	 * @return The MyList Id.
	 */
	public boolean isMyListId() {
		return this.myListId;
	}
	
	/**
	 * Returns the list of other episodes.
	 * @return The list of other episodes.
	 */
	public boolean isListOtherEpisodes() {
		return this.listOtherEpisodes;
	}
	
	/**
	 * Returns the 'is deprecated' flag.
	 * @return The 'is deprecated' flag.
	 */
	public boolean isDeprecated() {
		return this.deprecated;
	}
	
	/**
	 * Returns the state.
	 * @return The state.
	 */
	public boolean isState() {
		return this.state;
	}
	
	/**
	 * Returns the size.
	 * @return The size.
	 */
	public boolean isSize() {
		return this.size;
	}
	
	/**
	 * Returns the ed2k hash.
	 * @return The ed2k hash.
	 */
	public boolean isEd2k() {
		return this.ed2k;
	}
	
	/**
	 * Returns the md5 hash.
	 * @return The md5 hash.
	 */
	public boolean isMd5() {
		return this.md5;
	}
	
	/**
	 * Returns the sha1 hash.
	 * @return The sha1 hash.
	 */
	public boolean isSha1() {
		return this.sha1;
	}
	
	/**
	 * Returns the crc32 hash.
	 * @return The crc32 hash.
	 */
	public boolean isCrc32() {
		return this.crc32;
	}
	
	/**
	 * Returns the quality.
	 * @return The quality.
	 */
	public boolean isQuality() {
		return this.quality;
	}
	
	/**
	 * Returns the source.
	 * @return The source.
	 */
	public boolean isSource() {
		return this.source;
	}
	
	/**
	 * Returns the audio codec list.
	 * @return The audio codec list.
	 */
	public boolean isAudioCodecList() {
		return this.audioCodecList;
	}
	
	/**
	 * Returns the audio bitrate list.
	 * @return The audio bitrate list.
	 */
	public boolean isAudioBitrateList() {
		return this.audioBitrateList;
	}
	
	/**
	 * Returns the video codec.
	 * @return The video code.
	 */
	public boolean isVideoCodec() {
		return this.videoCodec;
	}
	
	/**
	 * Returns the video bitrate.
	 * @return The video bitrate.
	 */
	public boolean isVideoBitrate() {
		return this.videoBitrate;
	}
	
	/**
	 * Returns the video resolution.
	 * @return The video resolution.
	 */
	public boolean isVideoResolution() {
		return this.videoResolution;
	}
	
	/**
	 * Returns the file type.
	 * @return The file type.
	 */
	public boolean isFileType() {
		return this.fileType;
	}
	
	/**
	 * Return the dub language.
	 * @return The dub language.
	 */
	public boolean isDubLanguage() {
		return this.dubLanguage;
	}
	
	/**
	 * Returns the sub language.
	 * @return The sub language.
	 */
	public boolean isSubLanguage() {
		return this.subLanguage;
	}
	
	/**
	 * Returns the length in seconds.
	 * @return The length in seconds.
	 */
	public boolean isLengthInSeconds() {
		return this.lengthInSeconds;
	}
	
	/**
	 * Returns the description.
	 * @return The description.
	 */
	public boolean isDescription() {
		return this.description;
	}
	
	/**
	 * Returns the aired date.
	 * @return The aired date.
	 */
	public boolean isAiredDate() {
		return this.airedDate;
	}
	
	/**
	 * Returns the AniDB file name.
	 * @return The AniDB file name.
	 */
	public boolean isAniDbFileName() {
		return this.aniDbFileName;
	}
	
	public long getMask() {
		long mask = 0;
		
		if (this.animeId) mask = this.setBit(1, 4, 6, mask);
		if (this.episodeId) mask = this.setBit(1, 4, 5, mask);
		if (this.groupId) mask = this.setBit(1, 4, 4, mask);
		if (this.myListId) mask = this.setBit(1, 4, 3, mask);
		if (this.listOtherEpisodes) mask = this.setBit(1, 4, 2, mask);
		if (this.deprecated) mask = this.setBit(1, 4, 1, mask);
		if (this.state) mask = this.setBit(1, 4, 0, mask);
		if (this.size) mask = this.setBit(2, 4, 7, mask);
		if (this.ed2k) mask = this.setBit(2, 4, 6, mask);
		if (this.md5) mask = this.setBit(2, 4, 5, mask);
		if (this.sha1) mask = this.setBit(2, 4, 4, mask);
		if (this.crc32) mask = this.setBit(2, 4, 3, mask);
		if (this.quality) mask = this.setBit(3, 4, 7, mask);
		if (this.source) mask = this.setBit(3, 4, 6, mask);
		if (this.audioCodecList) mask = this.setBit(3, 4, 5, mask);
		if (this.audioBitrateList) mask = this.setBit(3, 4, 4, mask);
		if (this.videoCodec) mask = this.setBit(3, 4, 3, mask);
		if (this.videoBitrate) mask = this.setBit(3, 4, 2, mask);
		if (this.videoResolution) mask = this.setBit(3, 4, 1, mask);
		if (this.fileType) mask = this.setBit(3, 4, 0, mask);
		if (this.dubLanguage) mask = this.setBit(4, 4, 7, mask);
		if (this.subLanguage) mask = this.setBit(4, 4, 6, mask);
		if (this.lengthInSeconds) mask = this.setBit(4, 4, 5, mask);
		if (this.description) mask = this.setBit(4, 4, 4, mask);
		if (this.airedDate) mask = this.setBit(4, 4, 3, mask);
		if (this.aniDbFileName) mask = this.setBit(4, 4, 0, mask);
		
		return mask;
	}
	
	public String getHexMask() {
		return this.getHexMask(8);
	}
	
	public int getFlagCount() {
		int count = 0;
		
		if (this.animeId) count++;
		if (this.episodeId) count++;
		if (this.groupId) count++;
		if (this.myListId) count++;
		if (this.listOtherEpisodes) count++;
		if (this.deprecated) count++;
		if (this.state) count++;
		if (this.size) count++;
		if (this.ed2k) count++;
		if (this.md5) count++;
		if (this.sha1) count++;
		if (this.crc32) count++;
		if (this.quality) count++;
		if (this.source) count++;
		if (this.audioCodecList) count++;
		if (this.audioBitrateList) count++;
		if (this.videoCodec) count++;
		if (this.videoBitrate) count++;
		if (this.videoResolution) count++;
		if (this.fileType) count++;
		if (this.dubLanguage) count++;
		if (this.subLanguage) count++;
		if (this.lengthInSeconds) count++;
		if (this.description) count++;
		if (this.airedDate) count++;
		if (this.aniDbFileName) count++;
		
		return count;
	}
	
	public int hashCode() {
		int result = 17;
		
		result = 37 * result + (this.animeId ? 0 : 1);
		result = 37 * result + (this.episodeId ? 0 : 1);
		result = 37 * result + (this.groupId ? 0 : 1);
		result = 37 * result + (this.myListId ? 0 : 1);
		result = 37 * result + (this.listOtherEpisodes ? 0 : 1);
		result = 37 * result + (this.deprecated ? 0 : 1);
		result = 37 * result + (this.state ? 0 : 1);
		result = 37 * result + (this.size ? 0 : 1);
		result = 37 * result + (this.md5 ? 0 : 1);
		result = 37 * result + (this.sha1 ? 0 : 1);
		result = 37 * result + (this.crc32 ? 0 : 1);
		result = 37 * result + (this.quality ? 0 : 1);
		result = 37 * result + (this.source ? 0 : 1);
		result = 37 * result + (this.audioCodecList ? 0 : 1);
		result = 37 * result + (this.audioBitrateList ? 0 : 1);
		result = 37 * result + (this.videoCodec ? 0 : 1);
		result = 37 * result + (this.videoBitrate ? 0 : 1);
		result = 37 * result + (this.videoResolution ? 0 : 1);
		result = 37 * result + (this.fileType ? 0 : 1);
		result = 37 * result + (this.dubLanguage ? 0 : 1);
		result = 37 * result + (this.subLanguage ? 0 : 1);
		result = 37 * result + (this.lengthInSeconds ? 0 : 1);
		result = 37 * result + (this.description ? 0 : 1);
		result = 37 * result + (this.airedDate ? 0 : 1);
		result = 37 * result + (this.aniDbFileName ? 0 : 1);
		
		return result;
	}
	
	public boolean equals(final Object obj) {
		FileMask mask;
		
		if (obj instanceof FileMask) {
			mask = (FileMask) obj;
			if ((this.animeId == mask.animeId)
					&& (this.episodeId == mask.episodeId)
					&& (this.groupId == mask.groupId)
					&& (this.myListId == mask.myListId)
					&& (this.listOtherEpisodes == mask.listOtherEpisodes)
					&& (this.deprecated == mask.deprecated)
					&& (this.state == mask.state)
					&& (this.size == mask.size)
					&& (this.ed2k == mask.ed2k)
					&& (this.md5 == mask.md5)
					&& (this.sha1 == mask.sha1)
					&& (this.crc32 == mask.crc32)
					&& (this.quality == mask.quality)
					&& (this.source == mask.source)
					&& (this.audioCodecList == mask.audioCodecList)
					&& (this.audioBitrateList == mask.audioBitrateList)
					&& (this.videoCodec == mask.videoCodec)
					&& (this.videoBitrate == mask.videoBitrate)
					&& (this.videoResolution == mask.videoResolution)
					&& (this.fileType == mask.fileType)
					&& (this.dubLanguage == mask.dubLanguage)
					&& (this.subLanguage == mask.subLanguage)
					&& (this.lengthInSeconds == mask.lengthInSeconds)
					&& (this.description == mask.description)
					&& (this.airedDate == mask.airedDate)
					&& (this.aniDbFileName == mask.aniDbFileName)) {
				return true;
			}
		}
		return false;
	}
}
