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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.anidb.Anime;
import net.anidb.udp.mask.AnimeMask;

/**
 * A factory for {@link Anime} objects.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 25.12.2009
 */
public class UdpAnimeFactory {
	/** The logging. */
	private final static Log LOG = LogFactory.getLog(UdpAnimeFactory.class);
	
	/** The connection. */
	private UdpConnection conn;
	
	/**
	 * Creates a factory.
	 * @param conn The connection.
	 */
	private UdpAnimeFactory(final UdpConnection conn) {
		super();
		this.conn = conn;
	}
	
	/**
	 * Returns an instance of this class.
	 * @param conn The connection.
	 * @return The instance.
	 * @throws IllegalArgumentException If the connection is <code>null</code>.
	 */
	public synchronized static UdpAnimeFactory getInstance(
		final UdpConnection conn) {
		
		if (conn == null) {
			throw new IllegalArgumentException("Argument conn is null.");
		}
		return (new UdpAnimeFactory(conn));
	}
	
	/**
	 * Returns the anime with the given Id.
	 * @param animeId The anime Id.
	 * @return The anime.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_ANIME
	 */
	public Anime getAnime(final long animeId)
		throws UdpConnectionException, AniDbException {
		
		UdpRequest request;
		UdpResponse response;
		
		if (!this.conn.isOpen()) {
			throw new UdpConnectionException("Connection is already closed.");
		}
		if (!this.conn.isLoggedIn()) {
			throw new UdpConnectionException("Client is not logged in.");
		}
		request = new UdpRequest("ANIME");
		request.addParameter("aid", animeId);
		response = this.conn.communicate(request);
		if (response.getReturnCode() != UdpReturnCodes.ANIME) {
			throw new AniDbException(response.getReturnCode(),
					response.getReturnString(), response.getMessageString());
		}
		if (response.getEntryCount() < 1) {
			throw new UdpConnectionException(
					"Received invalid response to the command ANIME: "
					+ "The response has less than 1 entry: "
					+ response.toString());
		} else if (response.getEntryCount() > 1) {
			LOG.warn("Response to the command ANIME has more than 1 "
					+ "entry: " + response.toString());
		}
		return this.getAnime(response);
	}
	
	/**
	 * Returns the anime with the given name.
	 * @param animeName The anime name.
	 * @return The anime.
	 * @throws IllegalArgumentException If the anime name is <code>null</code>.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_ANIME
	 */
	public Anime getAnime(final String animeName)
		throws UdpConnectionException, AniDbException {
		
		UdpRequest request;
		UdpResponse response;
		
		if (animeName == null) {
			throw new IllegalArgumentException("Argument animeName is null.");
		}
		if (!this.conn.isOpen()) {
			throw new UdpConnectionException("Connection is already closed.");
		}
		if (!this.conn.isLoggedIn()) {
			throw new UdpConnectionException("Client is not logged in.");
		}
		request = new UdpRequest("ANIME");
		request.addParameter("aname", animeName);
		response = this.conn.communicate(request);
		if (response.getReturnCode() != UdpReturnCodes.ANIME) {
			throw new AniDbException(response.getReturnCode(),
					response.getReturnString(), response.getMessageString());
		}
		if (response.getEntryCount() < 1) {
			throw new UdpConnectionException(
					"Received invalid response to the command ANIME: "
					+ "The response has less than 1 entry: "
					+ response.toString());
		} else if (response.getEntryCount() > 1) {
			LOG.warn("Response to the command ANIME has more than 1 "
					+ "entry: " + response.toString());
		}
		return this.getAnime(response);
	}
	
	/**
	 * Returns the anime from the response.
	 * @param response The response.
	 * @return The anime.
	 * @throws UdpConnectionException If a connection problem occured.
	 */
	private Anime getAnime(final UdpResponse response)
		throws UdpConnectionException {
		
		UdpResponseEntry entry;
		int fieldNumber = 0;
		Anime anime;
		System.out.println("lol");
		entry = response.getEntryAt(0);
		if (entry.getDataFieldCount() < 19) {
			throw new UdpConnectionException(
					"Received invalid response to the command ANIME: "
					+ "The entry has less than 19 data fields: "
					+ response.toString());
		} else if (entry.getDataFieldCount() > 33) {
			LOG.warn("The entry of the response to the command ANIME has more "
					+ "than 19 data fields: " + response.toString());
		}
		
		anime = new Anime();
		try {
			anime.setAnimeId(entry.getDataFieldAt(fieldNumber++)
					.getValueAsLong());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command ANIME: "
					+ response.toString(), dfe);
		}
		try {
			anime.setEpisodes(entry.getDataFieldAt(fieldNumber++)
					.getValueAsLong());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command ANIME: "
					+ response.toString(), dfe);
		}
		try {
			anime.setNormalEpisodeCount(entry.getDataFieldAt(fieldNumber++)
					.getValueAsLong());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command ANIME: "
					+ response.toString(), dfe);
		}
		try {
			anime.setSpecialEpisodeCount(entry.getDataFieldAt(fieldNumber++)
					.getValueAsLong());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command ANIME: "
					+ response.toString(), dfe);
		}
		try {
			anime.setRating(entry.getDataFieldAt(fieldNumber++)
					.getValueAsLong());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command ANIME: "
					+ response.toString(), dfe);
		}
		try {
			anime.setVoteCount(entry.getDataFieldAt(fieldNumber++)
					.getValueAsLong());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command ANIME: "
					+ response.toString(), dfe);
		}
		try {
			anime.setTempRating(entry.getDataFieldAt(fieldNumber++)
					.getValueAsLong());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command ANIME: "
					+ response.toString(), dfe);
		}
		try {
			anime.setTempVoteCount(entry.getDataFieldAt(fieldNumber++)
					.getValueAsLong());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command ANIME: "
					+ response.toString(), dfe);
		}
		try {
			anime.setAverageReviewRating(entry.getDataFieldAt(fieldNumber++)
					.getValueAsLong());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command ANIME: "
					+ response.toString(), dfe);
		}
		try {
			anime.setReviewCount(entry.getDataFieldAt(fieldNumber++)
					.getValueAsLong());
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command ANIME: "
					+ response.toString(), dfe);
		}
		anime.setYear(entry.getDataFieldAt(fieldNumber++).getValue());
		anime.setType(entry.getDataFieldAt(fieldNumber++).getValue());
		anime.setRomajiName(entry.getDataFieldAt(fieldNumber++).getValue());
		anime.setKanjiName(entry.getDataFieldAt(fieldNumber++).getValue());
		anime.setEnglishName(entry.getDataFieldAt(fieldNumber++).getValue());
		anime.setOtherNames(entry.getDataFieldAt(fieldNumber++).getValues());
		anime.setShortNameList(entry.getDataFieldAt(fieldNumber++).getValues());
		anime.setSynonymList(entry.getDataFieldAt(fieldNumber++).getValues());
		anime.setCategoryList(entry.getDataFieldAt(fieldNumber++).getValues());
		anime.setPicname(entry.getDataFieldAt(23).getValue());
		return anime;
	}
	
	/**
	 * <p>Returns the anime with the given Id.</p>
	 * <p>Only the fields specified by the mask will be filled.</p>
	 * @param animeId The anime Id.
	 * @param mask The mask.
	 * @return The anime.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_ANIME
	 */
	public Anime getAnime(final long animeId, final AnimeMask mask)
		throws UdpConnectionException, AniDbException {
		
		UdpRequest request;
		UdpResponse response;
		
		if (mask == null) {
			throw new IllegalArgumentException("Argument mask is null.");
		}
		if (!this.conn.isOpen()) {
			throw new UdpConnectionException("Connection is already closed.");
		}
		if (!this.conn.isLoggedIn()) {
			throw new UdpConnectionException("Client is not logged in.");
		}
		request = new UdpRequest("ANIME");
		request.addParameter("aid", animeId);
		request.addParameter("amask", mask.getHexMask());
		response = this.conn.communicate(request);
		if (response.getReturnCode() != UdpReturnCodes.ANIME) {
			throw new AniDbException(response.getReturnCode(),
					response.getReturnString(), response.getMessageString());
		}
		if (response.getEntryCount() < 1) {
			throw new UdpConnectionException(
					"Received invalid response to the command ANIME: "
					+ "The response has less than 1 entry: "
					+ response.toString());
		} else if (response.getEntryCount() > 1) {
			LOG.warn("Response to the command ANIME has more than 1 "
					+ "entry: " + response.toString());
		}
		return this.getAnime(response, mask);
	}
	
	/**
	 * <p>Returns the anime with the given name.</p>
	 * <p>Only the fields specified by the mask will be filled.</p>
	 * @param animeName The anime name.
	 * @param mask The mask.
	 * @return The anime.
	 * @throws IllegalArgumentException If the anime name is <code>null</code>.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_ANIME
	 */
	public Anime getAnime(final String animeName, final AnimeMask mask)
		throws UdpConnectionException, AniDbException {
		
		UdpRequest request;
		UdpResponse response;
		
		if (animeName == null) {
			throw new IllegalArgumentException("Argument animeName is null.");
		}
		if (mask == null) {
			throw new IllegalArgumentException("Argument mask is null.");
		}
		if (!this.conn.isOpen()) {
			throw new UdpConnectionException("Connection is already closed.");
		}
		if (!this.conn.isLoggedIn()) {
			throw new UdpConnectionException("Client is not logged in.");
		}
		request = new UdpRequest("ANIME");
		request.addParameter("aname", animeName);
		request.addParameter("amask", mask.getHexMask());
		response = this.conn.communicate(request);
		if (response.getReturnCode() != UdpReturnCodes.ANIME) {
			throw new AniDbException(response.getReturnCode(),
					response.getReturnString(), response.getMessageString());
		}
		if (response.getEntryCount() < 1) {
			throw new UdpConnectionException(
					"Received invalid response to the command ANIME: "
					+ "The response has less than 1 entry: "
					+ response.toString());
		} else if (response.getEntryCount() > 1) {
			LOG.warn("Response to the command ANIME has more than 1 "
					+ "entry: " + response.toString());
		}
		return this.getAnime(response, mask);
	}
	
	/**
	 * Returns the anime from the response.
	 * @param response The response.
	 * @param mask The mask.
	 * @return The anime.
	 * @throws UdpConnectionException If a connection problem occured.
	 */
	private Anime getAnime(final UdpResponse response, final AnimeMask mask)
		throws UdpConnectionException {
		
		UdpResponseEntry entry;
		int fieldNumber = 0;
		Anime anime;
		
		entry = response.getEntryAt(0);
		if (entry.getDataFieldCount() != mask.getFlagCount()) {
			throw new UdpConnectionException(
					"Received invalid response to the command ANIME: "
					+ "The entry has not " + mask.getFlagCount()
					+ " data fields: " + response.toString());
		}
		anime = new Anime();
		if (mask.isAnimeId()) {
			try {
				anime.setAnimeId(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIME: "
						+ response.toString(), dfe);
			}
		}
		if (mask.isYear()) {
			anime.setYear(entry.getDataFieldAt(fieldNumber++).getValue());
		}
		if (mask.isType()) {
			anime.setType(entry.getDataFieldAt(fieldNumber++).getValue());
		}
		if (mask.isRelatedAidList()) {
			anime.setRelatedAidList(entry.getDataFieldAt(fieldNumber++)
					.getValues());
			// TODO Implement List<Long> or List<Anime>
		}
		if (mask.isRelatedAidType()) {
			anime.setRelatedAidType(entry.getDataFieldAt(fieldNumber++)
					.getValues());
			// FIXME Re-implement with other data type?
		}
		if (mask.isCategoryList()) {
			anime.setCategoryList(entry.getDataFieldAt(fieldNumber++)
					.getValues());
		}
		if (mask.isCategoryWeightList()) {
			anime.setCategoryWeightList(entry.getDataFieldAt(fieldNumber++)
					.getValues());
			// FIXME Re-implement with other data type?
		}
		if (mask.isRomajiName()) {
			anime.setRomajiName(entry.getDataFieldAt(fieldNumber++).getValue());
		}
		if (mask.isKanjiName()) {
			anime.setKanjiName(entry.getDataFieldAt(fieldNumber++).getValue());
		}
		if (mask.isEnglishName()) {
			anime.setEnglishName(entry.getDataFieldAt(fieldNumber++).getValue());
		}
		if (mask.isOtherNames()) {
			anime.setOtherNames(entry.getDataFieldAt(fieldNumber++)
					.getValues());
		}
		if (mask.isShortNameList()) {
			anime.setShortNameList(entry.getDataFieldAt(fieldNumber++)
					.getValues());
		}
		if (mask.isSynonymList()) {
			anime.setSynonymList(entry.getDataFieldAt(fieldNumber++)
					.getValues());
		}
		if (mask.isEpisodes()) {
			try {
				anime.setEpisodes(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIME: "
						+ response.toString(), dfe);
			}
		}
		if (mask.isNormalEpisodeCount()) {
			try {
				anime.setNormalEpisodeCount(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIME: "
						+ response.toString(), dfe);
			}
		}
		if (mask.isSpecialEpisodeCount()) {
			try {
				anime.setSpecialEpisodeCount(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIME: "
						+ response.toString(), dfe);
			}
		}
		if (mask.isAirDate()) {
			try {
				anime.setAirDate(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIME: "
						+ response.toString(), dfe);
			}
		}
		if (mask.isEndDate()) {
			try {
				anime.setEndDate(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIME: "
						+ response.toString(), dfe);
			}
		}
		if (mask.isUrl()) {
			anime.setUrl(entry.getDataFieldAt(fieldNumber++).getValue());
		}
		if (mask.isPicname()) {
			anime.setPicname(entry.getDataFieldAt(fieldNumber++).getValue());
		}
		if (mask.isCategoryIdList()) {
			anime.setCategoryIdList(entry.getDataFieldAt(fieldNumber++)
					.getValues());
			// TODO Implement List<Long> or List<Category>
		}
		if (mask.isRating()) {
			try {
				anime.setRating(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIME: "
						+ response.toString(), dfe);
			}
		}
		if (mask.isVoteCount()) {
			try {
				anime.setVoteCount(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIME: "
						+ response.toString(), dfe);
			}
		}
		if (mask.isTempRating()) {
			try {
				anime.setTempRating(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIME: "
						+ response.toString(), dfe);
			}
		}
		if (mask.isTempVoteCount()) {
			try {
				anime.setTempVoteCount(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIME: "
						+ response.toString(), dfe);
			}
		}
		if (mask.isAverageReviewRating()) {
			try {
				anime.setAverageReviewRating(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIME: "
						+ response.toString(), dfe);
			}
		}
		if (mask.isReviewCount()) {
			try {
				anime.setReviewCount(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIME: "
						+ response.toString(), dfe);
			}
		}
		if (mask.isAwardList()) {
			anime.setAwardList(entry.getDataFieldAt(fieldNumber++).getValues());
		}
		if (mask.is18PlusRestricted()) {
			try {
				anime.set18PlusRestricted(entry.getDataFieldAt(fieldNumber++)
						.getValueAsBoolean());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIME: "
						+ response.toString(), dfe);
			}
		}
		if (mask.isAnimePlanetId()) {
			try {
				anime.setAnimePlanetId(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIME: "
						+ response.toString(), dfe);
			}
		}
		if (mask.isAnnId()) {
			try {
				anime.setAnnId(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIME: "
						+ response.toString(), dfe);
			}
		}
		if (mask.isAllCinemaId()) {
			try {
				anime.setAllCinemaId(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIME: "
						+ response.toString(), dfe);
			}
		}
		if (mask.isAnimeNfoId()) {
			anime.setAnimeNfoId(entry.getDataFieldAt(fieldNumber++).getValue());
		}
		if (mask.isDateRecordUpdated()) {
			try {
				anime.setDateRecordUpdated(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIME: "
						+ response.toString(), dfe);
			}
		}
		if (mask.isCharacterIdList()) {
			try {
				anime.setCharacterIdList(entry.getDataFieldAt(fieldNumber++)
						.getValuesAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIME: "
						+ response.toString(), dfe);
			}
			// TODO Implement List<Character>
		}
		if (mask.isCreatorIdList()) {
			try {
				anime.setCreatorIdList(entry.getDataFieldAt(fieldNumber++)
						.getValuesAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIME: "
						+ response.toString(), dfe);
			}
			// TODO Implement List<Creator>
		}
		if (mask.isProducerIdList()) {
			try {
				anime.setProducerIdList(entry.getDataFieldAt(fieldNumber++)
						.getValuesAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIME: "
						+ response.toString(), dfe);
			}
			// TODO Implement List<Producer>
		}
		if (mask.isProducerNameList()) {
			anime.setProducerNameList(entry.getDataFieldAt(fieldNumber++)
					.getValues());
		}
		if (mask.isSpecialsCount()) {
			try {
				anime.setSpecialsCount(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIME: "
						+ response.toString(), dfe);
			}
		}
		if (mask.isCreditsCount()) {
			try {
				anime.setCreditsCount(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIME: "
						+ response.toString(), dfe);
			}
		}
		if (mask.isOtherCount()) {
			try {
				anime.setOtherCount(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIME: "
						+ response.toString(), dfe);
			}
		}
		if (mask.isTrailerCount()) {
			try {
				anime.setTrailerCount(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIME: "
						+ response.toString(), dfe);
			}
		}
		if (mask.isParodyCount()) {
			try {
				anime.setParodyCount(entry.getDataFieldAt(fieldNumber++)
						.getValueAsLong());
			} catch (DataFieldException dfe) {
				throw new UdpConnectionException(
						"Received invalid response to the command ANIME: "
						+ response.toString(), dfe);
			}
		}
		return anime;
	}
}
