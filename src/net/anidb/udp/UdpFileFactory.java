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
package net.anidb.udp;

import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.anidb.Anime;
import net.anidb.Episode;
import net.anidb.File;
import net.anidb.Group;
import net.anidb.RelatedEpisode;
import net.anidb.udp.mask.AnimeFileMask;
import net.anidb.udp.mask.FileMask;

/**
 * A factory for {@link File} objects.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 25.12.2009
 */
public class UdpFileFactory {
	/** The logging. */
	private final static Log LOG = LogFactory.getLog(UdpFileFactory.class);
	
	/** The connection. */
	private UdpConnection conn;
	
	/**
	 * Creates a factory.
	 * @param conn The connection.
	 */
	private UdpFileFactory(final UdpConnection conn) {
		super();
		this.conn = conn;
	}
	
	/**
	 * Returns an instance of this class.
	 * @param conn The connection.
	 * @return The instance.
	 * @throws IllegalArgumentException If the connection is <code>null</code>.
	 */
	public synchronized static UdpFileFactory getInstance(
		final UdpConnection conn) {
		
		if (conn == null) {
			throw new IllegalArgumentException("Argument conn is null.");
		}
		return (new UdpFileFactory(conn));
	}
	
	/**
	 * <p>Returns the file with the given Id.</p>
	 * <p>Only the fields specified by the two masks will be filled.</p>
	 * @param fileId The file Id.
	 * @param fileMask The file mask.
	 * @param animeFileMask The anime file mask.
	 * @return The file.
	 * @throws IllegalArgumentException If the file mask is <code>null</code>.
	 * @throws IllegalArgumentException If the anime file mask is
	 * <code>null</code>.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_FILE
	 */
	public File getFile(final long fileId, final FileMask fileMask,
		final AnimeFileMask animeFileMask)
		throws UdpConnectionException, AniDbException {
		
		UdpRequest request;
		UdpResponse response;
		
		if (fileMask == null) {
			throw new IllegalArgumentException("Argument fileMask is null.");
		}
		if (animeFileMask == null) {
			throw new IllegalArgumentException(
					"Argument animeFileMask is null.");
		}
		if (!this.conn.isOpen()) {
			throw new UdpConnectionException("Connection is already closed.");
		}
		if (!this.conn.isLoggedIn()) {
			throw new UdpConnectionException("Client is not logged in.");
		}
		request = new UdpRequest("FILE");
		request.addParameter("fid", fileId);
		request.addParameter("fmask", fileMask.getHexMask());
		request.addParameter("amask", animeFileMask.getHexMask());
		response = this.conn.communicate(request);
		if (response.getReturnCode() != UdpReturnCodes.FILE) {
			throw new AniDbException(response.getReturnCode(),
					response.getReturnString(), response.getMessageString());
		}
		if (response.getEntryCount() < 1) {
			throw new UdpConnectionException(
					"Received invalid response to the command FILE: "
					+ "The response has less than 1 entry: "
					+ response.toString());
		} else if (response.getEntryCount() > 1) {
			LOG.warn("Response to the command FILE has more than 1 "
					+ "entry: " + response.toString());
		}
		return this.getFile(response, fileMask, animeFileMask);
	}
	
	/**
	 * <p>Returns the files with the given size and ed2k hash.</p>
	 * <p>Only the fields specified by the two masks will be filled.</p>
	 * @param size The size.
	 * @param ed2kHash The ed2k hash.
	 * @param fileMask The file mask.
	 * @param animeFileMask The anime file mask.
	 * @return The files.
	 * @throws IllegalArgumentException If the size is less than <code>1</code>.
	 * @throws IllegalArgumentException If the ed2k hash is <code>null</code>.
	 * @throws IllegalArgumentException If the file mask is <code>null</code>.
	 * @throws IllegalArgumentException If the anime file mask is
	 * <code>null</code>.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_FILE
	 */
	public List<File> getFiles(final long size, final String ed2kHash,
		final FileMask fileMask, final AnimeFileMask animeFileMask)
		throws UdpConnectionException, AniDbException {
		
		UdpRequest request;
		UdpResponse response;
		Vector<File> files;
		
		if (size < 1) {
			throw new IllegalArgumentException(
					"Value of argument size is less than 1: " + size);
		}
		if (ed2kHash == null) {
			throw new IllegalArgumentException("Argument ed2kHash is null.");
		}
		if (fileMask == null) {
			throw new IllegalArgumentException("Argument fileMask is null.");
		}
		if (animeFileMask == null) {
			throw new IllegalArgumentException(
					"Argument animeFileMask is null.");
		}
		if (!this.conn.isOpen()) {
			throw new UdpConnectionException("Connection is already closed.");
		}
		if (!this.conn.isLoggedIn()) {
			throw new UdpConnectionException("Client is not logged in.");
		}
		request = new UdpRequest("FILE");
		request.addParameter("size", size);
		request.addParameter("ed2k", ed2kHash);
		request.addParameter("fmask", fileMask.getHexMask());
		request.addParameter("amask", animeFileMask.getHexMask());
		response = this.conn.communicate(request);
		if ((response.getReturnCode() != UdpReturnCodes.FILE)
				&& (response.getReturnCode()
						!= UdpReturnCodes.MULTIPLE_FILES_FOUND)) {
			throw new AniDbException(response.getReturnCode(),
					response.getReturnString(), response.getMessageString());
		}
		if (response.getEntryCount() < 1) {
			throw new UdpConnectionException(
					"Received invalid response to the command FILE: "
					+ "The response has less than 1 entry: "
					+ response.toString());
		} else if (response.getEntryCount() > 1) {
			LOG.warn("Response to the command FILE has more than 1 "
					+ "entry: " + response.toString());
		}
		if (response.getReturnCode() == UdpReturnCodes.FILE) {
			files = new Vector<File>();
			files.addElement(this.getFile(response, fileMask, animeFileMask));
			return files;
		}
		return this.getFiles(response, fileMask, animeFileMask);
	}
	
	/**
	 * <p>Returns the files with the given anime name, group name and episode
	 * number.</p>
	 * <p>Only the fields specified by the two masks will be filled.</p>
	 * @param animeName The anime name.
	 * @param groupName The group name.
	 * @param episodeNumber The episode number.
	 * @param fileMask The file mask.
	 * @param animeFileMask The anime file mask.
	 * @return The files.
	 * @throws IllegalArgumentException If the anime name is <code>null</code>.
	 * @throws IllegalArgumentException If the group name is <code>null</code>.
	 * @throws IllegalArgumentException If the file mask is <code>null</code>.
	 * @throws IllegalArgumentException If the anime file mask is
	 * <code>null</code>.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_FILE
	 */
	public List<File> getFiles(final String animeName, final String groupName,
		final long episodeNumber, final FileMask fileMask,
		final AnimeFileMask animeFileMask)
		throws UdpConnectionException, AniDbException {
		
		UdpRequest request;
		UdpResponse response;
		Vector<File> files;
		
		if (animeName == null) {
			throw new IllegalArgumentException("Argument animeName is null.");
		}
		if (groupName == null) {
			throw new IllegalArgumentException("Argument groupName is null.");
		}
		if (fileMask == null) {
			throw new IllegalArgumentException("Argument fileMask is null.");
		}
		if (animeFileMask == null) {
			throw new IllegalArgumentException(
					"Argument animeFileMask is null.");
		}
		if (!this.conn.isOpen()) {
			throw new UdpConnectionException("Connection is already closed.");
		}
		if (!this.conn.isLoggedIn()) {
			throw new UdpConnectionException("Client is not logged in.");
		}
		request = new UdpRequest("FILE");
		request.addParameter("aname", animeName);
		request.addParameter("gname", groupName);
		request.addParameter("epno", episodeNumber);
		request.addParameter("fmask", fileMask.getHexMask());
		request.addParameter("amask", animeFileMask.getHexMask());
		response = this.conn.communicate(request);
		if ((response.getReturnCode() != UdpReturnCodes.FILE)
				&& (response.getReturnCode()
						!= UdpReturnCodes.MULTIPLE_FILES_FOUND)) {
			throw new AniDbException(response.getReturnCode(),
					response.getReturnString(), response.getMessageString());
		}
		if (response.getEntryCount() < 1) {
			throw new UdpConnectionException(
					"Received invalid response to the command FILE: "
					+ "The response has less than 1 entry: "
					+ response.toString());
		} else if (response.getEntryCount() > 1) {
			LOG.warn("Response to the command FILE has more than 1 "
					+ "entry: " + response.toString());
		}
		if (response.getReturnCode() == UdpReturnCodes.FILE) {
			files = new Vector<File>();
			files.addElement(this.getFile(response, fileMask, animeFileMask));
			return files;
		}
		return this.getFiles(response, fileMask, animeFileMask);
	}
	
	/**
	 * <p>Returns the files with the given anime name, group Id and episode
	 * number.</p>
	 * <p>Only the fields specified by the two masks will be filled.</p>
	 * @param animeName The anime name.
	 * @param groupId The group Id.
	 * @param episodeNumber The episode number.
	 * @param fileMask The file mask.
	 * @param animeFileMask The anime file mask.
	 * @return The files.
	 * @throws IllegalArgumentException If the anime name is <code>null</code>.
	 * @throws IllegalArgumentException If the file mask is <code>null</code>.
	 * @throws IllegalArgumentException If the anime file mask is
	 * <code>null</code>.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_FILE
	 */
	public List<File> getFiles(final String animeName, final long groupId,
		final long episodeNumber, final FileMask fileMask,
		final AnimeFileMask animeFileMask)
		throws UdpConnectionException, AniDbException {
		
		UdpRequest request;
		UdpResponse response;
		Vector<File> files;
		
		if (animeName == null) {
			throw new IllegalArgumentException("Argument animeName is null.");
		}
		if (fileMask == null) {
			throw new IllegalArgumentException("Argument fileMask is null.");
		}
		if (animeFileMask == null) {
			throw new IllegalArgumentException(
					"Argument animeFileMask is null.");
		}
		if (!this.conn.isOpen()) {
			throw new UdpConnectionException("Connection is already closed.");
		}
		if (!this.conn.isLoggedIn()) {
			throw new UdpConnectionException("Client is not logged in.");
		}
		request = new UdpRequest("FILE");
		request.addParameter("aname", animeName);
		request.addParameter("gid", groupId);
		request.addParameter("epno", episodeNumber);
		request.addParameter("fmask", fileMask.getHexMask());
		request.addParameter("amask", animeFileMask.getHexMask());
		response = this.conn.communicate(request);
		if ((response.getReturnCode() != UdpReturnCodes.FILE)
				&& (response.getReturnCode()
						!= UdpReturnCodes.MULTIPLE_FILES_FOUND)) {
			throw new AniDbException(response.getReturnCode(),
					response.getReturnString(), response.getMessageString());
		}
		if (response.getEntryCount() < 1) {
			throw new UdpConnectionException(
					"Received invalid response to the command FILE: "
					+ "The response has less than 1 entry: "
					+ response.toString());
		} else if (response.getEntryCount() > 1) {
			LOG.warn("Response to the command FILE has more than 1 "
					+ "entry: " + response.toString());
		}
		if (response.getReturnCode() == UdpReturnCodes.FILE) {
			files = new Vector<File>();
			files.addElement(this.getFile(response, fileMask, animeFileMask));
			return files;
		}
		return this.getFiles(response, fileMask, animeFileMask);
	}
	
	/**
	 * <p>Returns the files with the given anime Id, group name and episode
	 * number.</p>
	 * <p>Only the fields specified by the two masks will be filled.</p>
	 * @param animeId The anime Id.
	 * @param groupName The group name.
	 * @param episodeNumber The episode number.
	 * @param fileMask The file mask.
	 * @param animeFileMask The anime file mask.
	 * @return The files.
	 * @throws IllegalArgumentException If the group name is <code>null</code>.
	 * @throws IllegalArgumentException If the file mask is <code>null</code>.
	 * @throws IllegalArgumentException If the anime file mask is
	 * <code>null</code>.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_FILE
	 */
	public List<File> getFiles(final long animeId, final String groupName,
		final long episodeNumber, final FileMask fileMask,
		final AnimeFileMask animeFileMask)
		throws UdpConnectionException, AniDbException {
		
		UdpRequest request;
		UdpResponse response;
		Vector<File> files;
		
		if (groupName == null) {
			throw new IllegalArgumentException("Argument groupName is null.");
		}
		if (fileMask == null) {
			throw new IllegalArgumentException("Argument fileMask is null.");
		}
		if (animeFileMask == null) {
			throw new IllegalArgumentException(
					"Argument animeFileMask is null.");
		}
		if (!this.conn.isOpen()) {
			throw new UdpConnectionException("Connection is already closed.");
		}
		if (!this.conn.isLoggedIn()) {
			throw new UdpConnectionException("Client is not logged in.");
		}
		request = new UdpRequest("FILE");
		request.addParameter("aid", animeId);
		request.addParameter("gname", groupName);
		request.addParameter("epno", episodeNumber);
		request.addParameter("fmask", fileMask.getHexMask());
		request.addParameter("amask", animeFileMask.getHexMask());
		response = this.conn.communicate(request);
		if ((response.getReturnCode() != UdpReturnCodes.FILE)
				&& (response.getReturnCode()
						!= UdpReturnCodes.MULTIPLE_FILES_FOUND)) {
			throw new AniDbException(response.getReturnCode(),
					response.getReturnString(), response.getMessageString());
		}
		if (response.getEntryCount() < 1) {
			throw new UdpConnectionException(
					"Received invalid response to the command FILE: "
					+ "The response has less than 1 entry: "
					+ response.toString());
		} else if (response.getEntryCount() > 1) {
			LOG.warn("Response to the command FILE has more than 1 "
					+ "entry: " + response.toString());
		}
		if (response.getReturnCode() == UdpReturnCodes.FILE) {
			files = new Vector<File>();
			files.addElement(this.getFile(response, fileMask, animeFileMask));
			return files;
		}
		return this.getFiles(response, fileMask, animeFileMask);
	}
	
	/**
	 * <p>Returns the files with the given anime Id, group Id and episode
	 * number.</p>
	 * <p>Only the fields specified by the two masks will be filled.</p>
	 * @param animeId The anime Id.
	 * @param groupId The group Id.
	 * @param episodeNumber The episode number.
	 * @param fileMask The file mask.
	 * @param animeFileMask The anime file mask.
	 * @return The files.
	 * @throws IllegalArgumentException If the file mask is <code>null</code>.
	 * @throws IllegalArgumentException If the anime file mask is
	 * <code>null</code>.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_FILE
	 */
	public List<File> getFiles(final long animeId, final long groupId,
		final long episodeNumber, final FileMask fileMask,
		final AnimeFileMask animeFileMask)
		throws UdpConnectionException, AniDbException {
		
		UdpRequest request;
		UdpResponse response;
		Vector<File> files;
		
		if (fileMask == null) {
			throw new IllegalArgumentException("Argument fileMask is null.");
		}
		if (animeFileMask == null) {
			throw new IllegalArgumentException(
					"Argument animeFileMask is null.");
		}
		if (!this.conn.isOpen()) {
			throw new UdpConnectionException("Connection is already closed.");
		}
		if (!this.conn.isLoggedIn()) {
			throw new UdpConnectionException("Client is not logged in.");
		}
		request = new UdpRequest("FILE");
		request.addParameter("aid", animeId);
		request.addParameter("gid", groupId);
		request.addParameter("epno", episodeNumber);
		request.addParameter("fmask", fileMask.getHexMask());
		request.addParameter("amask", animeFileMask.getHexMask());
		response = this.conn.communicate(request);
		if ((response.getReturnCode() != UdpReturnCodes.FILE)
				&& (response.getReturnCode()
						!= UdpReturnCodes.MULTIPLE_FILES_FOUND)) {
			throw new AniDbException(response.getReturnCode(),
					response.getReturnString(), response.getMessageString());
		}
		if (response.getEntryCount() < 1) {
			throw new UdpConnectionException(
					"Received invalid response to the command FILE: "
					+ "The response has less than 1 entry: "
					+ response.toString());
		} else if (response.getEntryCount() > 1) {
			LOG.warn("Response to the command FILE has more than 1 "
					+ "entry: " + response.toString());
		}
		if (response.getReturnCode() == UdpReturnCodes.FILE) {
			files = new Vector<File>();
			files.addElement(this.getFile(response, fileMask, animeFileMask));
			return files;
		}
		return this.getFiles(response, fileMask, animeFileMask);
	}
	
	/**
	 * Returns the file from the response.
	 * @param response The response.
	 * @param fileMask The file mask.
	 * @param animeFileMask The anime file mask.
	 * @return The file.
	 * @throws UdpConnectionException If a connection problem occured.
	 */
	private File getFile(final UdpResponse response, final FileMask fileMask,
		final AnimeFileMask animeFileMask) throws UdpConnectionException {
		
		UdpResponseEntry entry;
		int flagCount, fieldNumber = 0;
		Anime anime;
		Episode episode;
		Group group;
		File file;
		long fileId;
		
		flagCount = fileMask.getFlagCount() + animeFileMask.getFlagCount() + 1;
		entry = response.getEntryAt(0);
		if (entry.getDataFieldCount() != flagCount) {
			throw new UdpConnectionException(
					"Received invalid response to the command FILE: "
					+ "Response should have " + flagCount
					+ " fields, but has "
					+ entry.getDataFieldCount());
		}
		try {
			fileId = entry.getDataFieldAt(fieldNumber++)
				.getValueAsLongValue();
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command FILE: "
					+ response.toString(), dfe);
		}
		anime = new Anime();
		episode = new Episode();
		episode.setAnime(anime);
		group = new Group();
		file = new File();
		file.setFileId(Long.valueOf(fileId));
		file.setEpisode(episode);
		file.setGroup(group);
		// File mask options.
		if (fileMask.isAnimeId()) {
			try {
				anime.setAnimeId(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command FILE: "
						+ response.toString(), dfe);
			}
		}
		if (fileMask.isEpisodeId()) {
			try {
				episode.setEpisodeId(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command FILE: "
						+ response.toString(), dfe);
			}
		}
		if (fileMask.isGroupId()) {
			try {
				group.setGroupId(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command FILE: "
						+ response.toString(), dfe);
			}
		}
		if (fileMask.isMyListId()) {
			try {
				file.setMyListId(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command FILE: "
						+ response.toString(), dfe);
			}
		}
		if (fileMask.isListOtherEpisodes()) {
			try {
				file.setOtherEpisodes(this.getRelatedEpisodes(
						entry.getDataFieldAt(fieldNumber++)));
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command FILE: "
						+ response.toString(), dfe);
			}
		}
		if (fileMask.isDeprecated()) {
			try {
				file.setDeprecated(entry.getDataFieldAt(fieldNumber++)
						.getValueAsBoolean());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command FILE: "
						+ response.toString(), dfe);
			}
		}
		if (fileMask.isState()) {
			try {
				file.setState(entry.getDataFieldAt(fieldNumber++)
						.getValueAsInteger());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command FILE: "
						+ response.toString(), dfe);
			}
		}
		if (fileMask.isSize()) {
			try {
				file.setSize(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command FILE: "
						+ response.toString(), dfe);
			}
		}
		if (fileMask.isEd2k()) {
			file.setEd2k(entry.getDataFieldAt(fieldNumber++).getValue());
		}
		if (fileMask.isMd5()) {
			file.setMd5(entry.getDataFieldAt(fieldNumber++).getValue());
		}
		if (fileMask.isSha1()) {
			file.setSha1(entry.getDataFieldAt(fieldNumber++).getValue());
		}
		if (fileMask.isCrc32()) {
			file.setCrc32(entry.getDataFieldAt(fieldNumber++).getValue());
		}
		if (fileMask.isQuality()) {
			file.setQuality(entry.getDataFieldAt(fieldNumber++).getValue());
		}
		if (fileMask.isSource()) {
			file.setSource(entry.getDataFieldAt(fieldNumber++).getValue());
		}
		if (fileMask.isAudioCodecList()) {
			file.setAudioCodecList(entry.getDataFieldAt(fieldNumber++)
					.getValues());
		}
		if (fileMask.isAudioBitrateList()) {
			try {
				file.setAudioBitrateList(entry.getDataFieldAt(fieldNumber++)
						.getValuesAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command FILE: "
						+ response.toString(), dfe);
			}
		}
		if (fileMask.isVideoCodec()) {
			file.setVideoCodec(entry.getDataFieldAt(fieldNumber++).getValue());
		}
		if (fileMask.isVideoBitrate()) {
			try {
				file.setVideoBitrate(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command FILE: "
						+ response.toString(), dfe);
			}
		}
		if (fileMask.isVideoResolution()) {
			file.setVideoResolution(entry.getDataFieldAt(fieldNumber++)
					.getValue());
		}
		if (fileMask.isFileType()) {
			file.setFileType(entry.getDataFieldAt(fieldNumber++).getValue());
		}
		if (fileMask.isDubLanguage()) {
			file.setDubLanguage(entry.getDataFieldAt(fieldNumber++).getValue());
		}
		if (fileMask.isSubLanguage()) {
			file.setSubLanguage(entry.getDataFieldAt(fieldNumber++).getValue());
		}
		if (fileMask.isLengthInSeconds()) {
			try {
				file.setLengthInSeconds(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command FILE: "
						+ response.toString(), dfe);
			}
		}
		if (fileMask.isDescription()) {
			file.setDescription(entry.getDataFieldAt(fieldNumber++).getValue());
		}
		if (fileMask.isAiredDate()) {
			try {
				file.setAiredDate(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command FILE: "
						+ response.toString(), dfe);
			}
		}
		if (fileMask.isAniDbFileName()) {
			file.setAniDbFileName(entry.getDataFieldAt(fieldNumber++)
					.getValue());
		}
		// Anime file mask options.
		if (animeFileMask.isAnimeTotalEpisode()) {
			try {
				anime.setEpisodes(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command FILE: "
						+ response.toString(), dfe);
			}
		}
		if (animeFileMask.isHighestEpisodeNumber()) {
			try {
				anime.setNormalEpisodeCount(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command FILE: "
						+ response.toString(), dfe);
			}
		}
		if (animeFileMask.isYear()) {
			anime.setYear(entry.getDataFieldAt(fieldNumber++).getValue());
		}
		if (animeFileMask.isType()) {
			anime.setType(entry.getDataFieldAt(fieldNumber++).getValue());
		}
		if (animeFileMask.isRelatedAidList()) {
			anime.setRelatedAidList(entry.getDataFieldAt(fieldNumber++)
					.getValues());
		}
		if (animeFileMask.isRelatedAidType()) {
			anime.setRelatedAidType(entry.getDataFieldAt(fieldNumber++)
					.getValues());
		}
		if (animeFileMask.isCategoryList()) {
			anime.setCategoryList(entry.getDataFieldAt(fieldNumber++)
					.getValues());
		}
		if (animeFileMask.isRomajiName()) {
			anime.setRomajiName(entry.getDataFieldAt(fieldNumber++).getValue());
		}
		if (animeFileMask.isKanjiName()) {
			anime.setKanjiName(entry.getDataFieldAt(fieldNumber++).getValue());
		}
		if (animeFileMask.isEnglishName()) {
			anime.setEnglishName(entry.getDataFieldAt(fieldNumber++)
					.getValue());
		}
		if (animeFileMask.isOtherNames()) {
			anime.setOtherNames(entry.getDataFieldAt(fieldNumber++)
					.getValues());
		}
		if (animeFileMask.isShortNameList()) {
			anime.setShortNameList(entry.getDataFieldAt(fieldNumber++)
					.getValues());
		}
		if (animeFileMask.isSynonymList()) {
			anime.setSynonymList(entry.getDataFieldAt(fieldNumber++)
					.getValues());
		}
		if (animeFileMask.isEpisodeNumber()) {
			episode.setEpisodeNumber(entry.getDataFieldAt(fieldNumber++)
					.getValue());
		}
		if (animeFileMask.isEpisodeName()) {
			episode.setEnglishTitle(entry.getDataFieldAt(fieldNumber++)
					.getValue());
		}
		if (animeFileMask.isEpisodeRomajiName()) {
			episode.setRomajiTitle(entry.getDataFieldAt(fieldNumber++)
					.getValue());
		}
		if (animeFileMask.isEpisodeKanjiName()) {
			episode.setKanjiTitle(entry.getDataFieldAt(fieldNumber++)
					.getValue());
		}
		if (animeFileMask.isEpisodeRating()) {
			try {
				episode.setRating(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command FILE: "
						+ response.toString(), dfe);
			}
		}
		if (animeFileMask.isEpisodeVoteCount()) {
			try {
				episode.setVotes(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command FILE: "
						+ response.toString(), dfe);
			}
		}
		if (animeFileMask.isGroupName()) {
			group.setName(entry.getDataFieldAt(fieldNumber++).getValue());
		}
		if (animeFileMask.isGroupShortName()) {
			group.setShortName(entry.getDataFieldAt(fieldNumber++).getValue());
		}
		if (animeFileMask.isDateAnimeIdRecordUpdate()) {
			try {
				anime.setDateRecordUpdated(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command FILE: "
						+ response.toString(), dfe);
			}
		}
		// TODO All non-set properties are read from cache.
		return file;
	}
	
	/**
	 * Returns the related episodes from the given data field.
	 * @param dataField The data field.
	 * @returnThe related episodes or <code>null</code>, of the given data field
	 * is empty.
	 * @throws DataFieldException If the data fields contains not the required
	 * data.
	 */
	private List<RelatedEpisode> getRelatedEpisodes(
		final DataField dataField) throws DataFieldException {
		
		String value;
		Vector<RelatedEpisode> relatedEpisodes;
		int count;
		DataField subDataField;
		
		value = dataField.getValue();
		if ((value == null) || (value.length() == 0)) {
			return null;
		}
		relatedEpisodes = new Vector<RelatedEpisode>();
		count = dataField.getDataFieldCount();
		if (count > 0) {
			for (int index = 0; index < count; index++) {
				subDataField = dataField.getDataFieldAt(index);
				relatedEpisodes.addElement(this.getRelatedEpisode(
						subDataField));
			}
		} else {
			relatedEpisodes.addElement(this.getRelatedEpisode(dataField));
		}
		return relatedEpisodes;
	}
	
	/**
	 * Returns the related episode from the given data field.
	 * @param dataField The data field.
	 * @return The related episode or <code>null</code>, of the given data field
	 * is empty.
	 * @throws DataFieldException If the data fields contains not the required
	 * data.
	 */
	private RelatedEpisode getRelatedEpisode(final DataField dataField)
		throws DataFieldException {
		
		String value;
		List<String> values;
		Anime anime;
		Episode episode;
		RelatedEpisode relatedEpisode;
		
		value = dataField.getValue();
		values = dataField.getValues();
		if (values.size() != 2) {
			throw new DataFieldException("Data field has not 2 values: "
					+ value);
		}
		// TODO All non-set properties are read from cache.
		anime = new Anime();
		episode = new Episode();
		episode.setAnime(anime);
		try {
			episode.setEpisodeId(Long.valueOf(Long.parseLong(values.get(0))));
		} catch (NumberFormatException nfe) {
			throw new DataFieldException(
					"Couldn't parse value into a long. Value: " + values.get(0),
					nfe);
		}
		relatedEpisode = new RelatedEpisode();
		relatedEpisode.setEpisode(episode);
		relatedEpisode.setCoverage(values.get(1));
		return relatedEpisode;
	}
	
	/**
	 * <p>Returns the files from the response.</p>
	 * <p>For each file the method
	 * {@link #getFile(long, FileMask, AnimeFileMask)} will be called.</p>
	 * @param response The response.
	 * @param fileMask The file mask.
	 * @param animeFileMask The anime file mask.
	 * @return The files.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_FILE
	 */
	private List<File> getFiles(final UdpResponse response,
		final FileMask fileMask, final AnimeFileMask animeFileMask)
		throws UdpConnectionException, AniDbException {
		
		UdpResponseEntry entry;
		long fileId;
		Vector<File> files;
		
		entry = response.getEntryAt(0);
		files = new Vector<File>();
		for (int index = 0; index < entry.getDataFieldCount(); index++) {
			try {
				fileId = entry.getDataFieldAt(index).getValueAsLongValue();
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command FILE: "
						+ response.toString(), dfe);
			}
			files.addElement(this.getFile(fileId, fileMask, animeFileMask));
		}
		return files;
	}
}
