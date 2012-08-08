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

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.Charset;
import java.util.List;

import net.anidb.Anime;
import net.anidb.Character;
import net.anidb.Creator;
import net.anidb.Episode;
import net.anidb.File;
import net.anidb.Group;
import net.anidb.GroupStatus;
import net.anidb.GroupStatusState;
import net.anidb.Notification;
import net.anidb.NotificationListEntry;
import net.anidb.udp.mask.AnimeFileMask;
import net.anidb.udp.mask.AnimeMask;
import net.anidb.udp.mask.FileMask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>A connection to AniDB via UDP.</p>
 * <p>For further information on the UDP communication look at the
 * <a href="http://wiki.anidb.info/w/UDP_API_Definition">UDP API Definition</a>.
 * </p>
 * <p>The flood protection limits the packets / requests per minute:<br>
 * <ul><li>You have a bonus (or a counter) with 4 packets right from the start.
 * Each time you send a packet, this counter will be decremented and the packets
 * will be send by a rate of 0.5 packets per seconds (120 packets per minute).
 * I recommend that you should use the first 4 requests for <code>AUTH</code>,
 * <code>NOTIFY</code> and <code>BUDDYLIST</code>.</li>
 * <li>After that counter reaches 0, each packet will be send by a rate of 1
 * packet per 30 seconds (2 packets per minute). If you don't send packets,
 * the counter will be incremented by 1 every 30 seconds to a maximum of 4. Is
 * the counter greater than 0, packets will be send by a rate of 0.5 packets per
 * seconds.</li></ul><br>
 * If you turn off the flood protection you have take care of it by
 * yourself.<br>
 * See also: <a href="http://wiki.anidb.info/w/UDP_API_Definition#Flood_Protection">
 * Flood Protection</a><p>
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 29.11.2009
 */
public class UdpConnection {
	/** The logging. */
	private final static Log LOG = LogFactory.getLog(UdpConnection.class);
	
	/** The supported protocol version. */
	public final static int PROTOCOL_VERSION = 3;
	/** The maximum number of bonus packets. */
	public final static int MAXMIMUM_NUMBER_OF_BONUS_PACKETS = 4;
	/** The time between the bonus packets in milliseconds. */
	public final static int TIME_BETWEEN_BONUS_PACKETS = 500;
	/** The time between normal packets in milliseconds. */
	public final static int TIME_BETWEEN_NORMAL_PACKETS = 30000;
	/** The client name of the Java AniDB API. */
	public final static String CLIENT_NAME = "javaanidbapi";
	/** The client version of this version of the Java AniDB API. */
	public final static int CLIENT_VERSION = 1;
	
	/** The datagram socket. */
	private DatagramSocket socket;
	/** The state if the client is logged in. */
	private boolean loggedIn = false;
	/** The session key. */
	private String sessionKey;
	/** The state of the flood protection. */
	private boolean floodProtection;
	/**
	 * The count of bonus packets (Range: <code>0</code> to
	 * {@link #MAXMIMUM_NUMBER_OF_BONUS_PACKETS}).
	 */
	private int packetBonusCount = MAXMIMUM_NUMBER_OF_BONUS_PACKETS;
	/** The timestamp of the last send packet. */
	private long timestampLastPacketSend = 0;
	/** The monitor for thread safety. */
	private Object monitor = new Object();
	/** The current encoding. */
	private Charset charset;
	/** The preferred encoding. */
	private Charset preferredCharset;
	
	/** The anime factory. */
	private UdpAnimeFactory animeFactory;
	/** The anime description factory. */
	private UdpAnimeDescriptionFactory animeDescFactory;
	/** The episode factory. */
	private UdpEpisodeFactory episodeFactory;
	/** The file factory. */
	private UdpFileFactory fileFactory;
	/** The group factory. */
	private UdpGroupFactory groupFactory;
	/** The group status factory. */
	private UdpGroupStatusFactory groupStatusFactory;
	/** The creator factory. */
	private UdpCreatorFactory creatorFactory;
	/** The character factory. */
	private UdpCharacterFactory characterFactory;
	/** The notification factory. */
	private UdpNotificationFactory notificationFactory;
	
	/**
	 * <p>Creates an UDP connection.</p>
	 * @param socket The datagram socket.
	 * @param floodProtection The state of the flood protection.
	 * @throws IllegalArgumentException If the socket is <code>null</code>.
	 * @throws UdpConnectionException If the socket is not connected, the
	 * default connection charset <code>US-ASCII</code> or the preferred
	 * connection charset <code>UTF</code> couldn't be set.
	 */
	protected UdpConnection(final DatagramSocket socket,
		final boolean floodProtection) throws UdpConnectionException {
		
		super();
		if (socket == null) {
			throw new IllegalArgumentException("Argument socket is null.");
		}
		this.socket = socket;
		if (!this.socket.isConnected()) {
			throw new UdpConnectionException(
				"The UDP socket is not connected.");
		}
		this.floodProtection = floodProtection;
		this.animeFactory = UdpAnimeFactory.getInstance(this);
		this.animeDescFactory = UdpAnimeDescriptionFactory.getInstance(this);
		this.episodeFactory = UdpEpisodeFactory.getInstance(this);
		this.fileFactory = UdpFileFactory.getInstance(this);
		this.groupFactory = UdpGroupFactory.getInstance(this);
		this.groupStatusFactory = UdpGroupStatusFactory.getInstance(this);
		this.creatorFactory = UdpCreatorFactory.getInstance(this);
		this.characterFactory = UdpCharacterFactory.getInstance(this);
		this.notificationFactory = UdpNotificationFactory.getInstance(this);
		try {
			this.charset = Charset.forName("US-ASCII");
		} catch (Throwable t) {
			throw new UdpConnectionException(
					"Couldn't set the default connection charset 'US-ASCII'.",
					t);
		}
		try {
			this.preferredCharset = Charset.forName("UTF-8");
		} catch (Throwable t) {
			throw new UdpConnectionException(
					"Couldn't set the preferred connection charset 'UTF-8'.",
					t);
		}
	}
	
	/**
	 * Return the state if connection is open.
	 * @return The state: <code>true</code>, if the connection is currently
	 * open, otherwise <code>false</code>.
	 */
	public boolean isOpen() {
		return (this.socket != null);
	}
	
	/**
	 * <p>Closes the connection.</p>
	 * <p>If the client is not logged out, a logout will be performed. And if
	 * there any problems the logout will raise an exception. In spite of that
	 * the connection will be closed by all means.</p>
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see #logout()
	 */
	public void close() throws UdpConnectionException, AniDbException {
		synchronized (this.monitor) {
			try {
				if (this.loggedIn) {
					this.logout();
				}
			} finally {
				if (this.socket != null) {
					try {
						this.socket.close();
					} finally {
						this.socket = null;
					}
				}
			}
		}
	}
	
	/**
	 * Checks the state of the connection by analyzing the response.
	 * @param response The response.
	 */
	private void checkState(final UdpResponse response) {
		switch (response.getReturnCode()) {
			case UdpReturnCodes.LOGIN_ACCEPTED:
				this.loggedIn = true;
				break;
			case UdpReturnCodes.LOGIN_ACCEPTED_NEW_VER:
				this.loggedIn = true;
				break;
			case UdpReturnCodes.LOGGED_OUT:
				this.loggedIn = false;
				break;
			case UdpReturnCodes.LOGIN_FAILED:
				this.loggedIn = false;
				break;
			case UdpReturnCodes.LOGIN_FIRST:
				this.loggedIn = false;
				break;
		}
	}
	
	/**
	 * Return the state if the client is logged in.
	 * @return The state: <code>true</code>, if the client is currently logged
	 * in, otherwise <code>false</code>.
	 */
	public boolean isLoggedIn() {
		return this.loggedIn;
	}
	
	/**
	 * This method cares for the flood protection, if enabled.
	 * @throws UdpConnectionException If the flood protection was interrupted
	 * by an {@link InterruptedException}.
	 */
	private void doFloodProtection() throws UdpConnectionException {
		long now, timeDifference, waitUntil, sleepTime;
		
		if (!this.floodProtection) {
			return;
		}
		LOG.debug("packet bonus count: " + this.packetBonusCount);
		// Calculating time difference between this and the last request
		now = System.currentTimeMillis();
		timeDifference = now - this.timestampLastPacketSend;
		if (timeDifference < 0) {
			timeDifference = 0;
		}
		// Increase bonus, if possible.
		if (timeDifference > TIME_BETWEEN_NORMAL_PACKETS) {
			this.packetBonusCount += (timeDifference
					/ TIME_BETWEEN_NORMAL_PACKETS);
			if (this.packetBonusCount > MAXMIMUM_NUMBER_OF_BONUS_PACKETS) {
				this.packetBonusCount = MAXMIMUM_NUMBER_OF_BONUS_PACKETS;
			}
			LOG.debug("New packet bonus count: " + this.packetBonusCount);
			this.timestampLastPacketSend += (timeDifference
					% TIME_BETWEEN_NORMAL_PACKETS);
		}
		if (this.packetBonusCount > 0) {
			waitUntil = this.timestampLastPacketSend
				+ TIME_BETWEEN_BONUS_PACKETS;
			this.packetBonusCount--;
			LOG.debug("Bonus packet wait time.");
		} else {
			waitUntil = this.timestampLastPacketSend
				+ TIME_BETWEEN_NORMAL_PACKETS;
			LOG.debug("Normal packet wait time.");
		}
		sleepTime = waitUntil - now;
		if (sleepTime > 0) {
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException ie) {
				throw new UdpConnectionException(
						"The flood protection was interrupted: "
						+ ie.getLocalizedMessage(), ie);
			}
		}
	}
	
	/**
	 * <p>Returns the charset which is currently used for encoding.</p>
	 * <p>At the beginning of a connection the charset <code>US-ASCII</code>
	 * will be used - as defined in the API documentation. If you don't specify
	 * any encoding at the <code>AUTHENTICATE</code> command, the preferred
	 * charset will be used after that command.</p>
	 * <p><b>Remark:</b> In the current version of this class you can't specifiy
	 * a encoding at the <code>AUTHENTICATE</code> command. This will be
	 * implemented later on.</p>
	 * @return The charset.
	 * @see #getPreferredEncoding()
	 * @see #authenticate(String, String)
	 * @see #authenticate(String, String, String, int)
	 */
	public Charset getCurrentEncoding() {
		return this.charset;
	}
	
	/**
	 * <p>Returns the preferred charset for encoding.</p>
	 * <p>If you don't specify any encoding at the <code>AUTHENTICATE</code>
	 * command, this charset will be used. But please keep in mind that the
	 * used charset at the beginning of a connection is allways
	 * <code>US-ASCII</code> - as defined in the API documentation.</p>
	 * <p><b>Remark:</b> In the current version of this class you can't specifiy
	 * a encoding at the <code>AUTHENTICATE</code> command. This will be
	 * implemented later on.</p>
	 * @return The charset.
	 * @see #getCurrentEncoding()
	 * @see #authenticate(String, String)
	 * @see #authenticate(String, String, String, int)
	 */
	public Charset getPreferredEncoding() {
		return this.preferredCharset;
	}
	
	/**
	 * <p>Sends a request and receives a response from the server.</p>
	 * <p>The session key will be set automatically, if the client is logged
	 * in.</p>
	 * @param request The request.
	 * @return The response.
	 * @throws IllegalArgumentException If the request is <code>null</code>.
	 * @throws UdpConnectionException If a connection problem occured.
	 */
	protected UdpResponse communicate(final UdpRequest request)
		throws UdpConnectionException {
		
		DatagramPacket packet;
		byte[] buffer;
		UdpResponse response;
		
		if (request == null) {
			throw new IllegalArgumentException("Argument request is null.");
		}
		synchronized (this.monitor) {
			if (this.socket == null) {
				throw new UdpConnectionException(
						"Connection is already closed.");
			}
			if (this.sessionKey != null) {
				request.addParameter("s", this.sessionKey);
			}
			LOG.debug("send = [" + request.toString() + "]");
			// Flood protection.
			this.doFloodProtection();
			try {
				packet = request.createPacket(this.charset);
			} catch (UnsupportedEncodingException uee) {
				throw new UdpConnectionException(
						"Couldn't encode the request.", uee);
			}
			try {
				// Remember timestamp for flood protection.
				this.timestampLastPacketSend = System.currentTimeMillis();
				this.socket.send(packet);
			} catch (Throwable t) {
				throw new UdpConnectionException("Couldn't send request: "
					+ t.getLocalizedMessage(), t);
			}
			// All packets should be smaller than 64k -> maximum MTU size.
			buffer = new byte[65536];
			packet = new DatagramPacket(buffer, buffer.length);
			try {
				this.socket.receive(packet);
			} catch (Throwable t) {
				throw new UdpConnectionException("Couldn't receive reply: "
					+ t.getLocalizedMessage(), t);
			}
		}
		try {
			response = UdpResponse.getInstance(packet, this.charset);
		} catch (UnsupportedEncodingException uee) {
			throw new UdpConnectionException(
					"Couldn't decode the response.", uee);
		}
		this.checkState(response);
		return response;
	}
	
	// TODO Listener for state changes and activity?
	// TODO Session Caching of FILE/EP/ANIME/GROUP/... requests
	
	// ROADMAP Permanent Caching of FILE/EP/ANIME/GROUP/... info
	// ROADMAP Use Data Dumps for cache and search (AnimeTitlesDownloader)
	// ROADMAP NOTIFY (not more than once every 20 minutes)
	// ROADMAP PUSH, PUSHACK and keeping session alive (PING, UPTIME)
	// ROADMAP Tag option (queue mode? needed for PUSH)
	// ROADMAP NAT? AUTH NAT=1 + PING
	// ROADMAP MTU size
	// ROADMAP ENCRYPT
	// ROADMAP BUDDYADD, BUDDYDEL, BUDDYACCEPT, BUDDYDENY, BUDDYLIST, BUDDYSTATE
	// ROADMAP MYLIST, MYLISTADD, MYLISTDEL, MYLISTSTATS, VOTE, RANDOM, MYLISTEXPORT
	// ROADMAP STATS, TOP
	
	/**
	 * Authenticates the user and client to AniDB.
	 * @param username The username.
	 * @param password The password.
	 * @throws IllegalArgumentException If the username is <code>null</code>.
	 * @throws IllegalArgumentException If the password is <code>null</code>.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#LOGIN_ACCEPTED
	 * @see UdpReturnCodes#LOGIN_ACCEPTED_NEW_VER
	 * @see UdpReturnCodes#LOGIN_FAILED
	 * @see UdpReturnCodes#ACCESS_DENIED
	 * @see UdpReturnCodes#CLIENT_VERSION_OUTDATED
	 * @see UdpReturnCodes#CLIENT_BANNED
	 * @see UdpReturnCodes#ILLEGAL_INPUT_OR_ACCESS_DENIED
	 * @see UdpReturnCodes#ANIDB_OUT_OF_SERVICE
	 */
	public void authenticate(final String username, final String password)
		throws UdpConnectionException, AniDbException {
		
		if (username == null) {
			throw new IllegalArgumentException("Argument username is null.");
		}
		if (password == null) {
			throw new IllegalArgumentException("Argument password is null.");
		}
		this.authenticate(username, password, CLIENT_NAME, CLIENT_VERSION,
				false, false, null, null, false);
	}
	
	/**
	 * Authenticates the user and client to AniDB.
	 * @param username The username.
	 * @param password The password.
	 * @param clientname The name of the client.
	 * @param clientversion The version of the client.
	 * @throws IllegalArgumentException If the username is <code>null</code>.
	 * @throws IllegalArgumentException If the password is <code>null</code>.
	 * @throws IllegalArgumentException If the client name is <code>null</code>.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#LOGIN_ACCEPTED
	 * @see UdpReturnCodes#LOGIN_ACCEPTED_NEW_VER
	 * @see UdpReturnCodes#LOGIN_FAILED
	 * @see UdpReturnCodes#ACCESS_DENIED
	 * @see UdpReturnCodes#CLIENT_VERSION_OUTDATED
	 * @see UdpReturnCodes#CLIENT_BANNED
	 * @see UdpReturnCodes#ILLEGAL_INPUT_OR_ACCESS_DENIED
	 * @see UdpReturnCodes#ANIDB_OUT_OF_SERVICE
	 */
	public void authenticate(final String username, final String password,
		final String clientname, final int clientversion)
		throws UdpConnectionException, AniDbException {
		
		if (username == null) {
			throw new IllegalArgumentException("Argument username is null.");
		}
		if (password == null) {
			throw new IllegalArgumentException("Argument password is null.");
		}
		if (clientname == null) {
			throw new IllegalArgumentException("Argument clientname is null.");
		}
		this.authenticate(username, password, clientname, clientversion, false,
				false, null, null, false);
	}
	
	/**
	 * Authenticates the user and client to AniDB.
	 * @param username The username.
	 * @param password The password.
	 * @param clientname The name of the client.
	 * @param clientversion The version of the client.
	 * @param nat The NAT state.
	 * @param compression The compression state.
	 * @param encoding The encoding.
	 * @param mtu The MTU size.
	 * @param imgserver The image server state.
	 * @throws IllegalArgumentException If the username is <code>null</code>.
	 * @throws IllegalArgumentException If the password is <code>null</code>.
	 * @throws IllegalArgumentException If the client name is <code>null</code>.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 */
	private void authenticate(final String username, final String password,
		final String clientname, final int clientversion, final boolean nat,
		final boolean compression, final Charset encoding, final Integer mtu,
		final boolean imgserver) throws UdpConnectionException, AniDbException {
		
		UdpRequest request;
		UdpResponse response;
		int index;
		
		if (username == null) {
			throw new IllegalArgumentException("Argument username is null.");
		}
		if (password == null) {
			throw new IllegalArgumentException("Argument password is null.");
		}
		if (clientname == null) {
			throw new IllegalArgumentException("Argument clientname is null.");
		}
		request = new UdpRequest("AUTH");
		request.addParameter("user", username);
		request.addParameter("pass", password);
		request.addParameter("protover", PROTOCOL_VERSION);
		request.addParameter("client", clientname);
		request.addParameter("clientver", clientversion);
		// ROADMAP NAT Option.
		if (nat) {
			request.addParameter("nat", true);
		}
		if (compression) {
			request.addParameter("comp", true);
		}
		if (encoding != null) {
			request.addParameter("enc", encoding.name());
		} else {
			request.addParameter("enc", this.preferredCharset.name());
		}
		if (mtu != null) {
			request.addParameter("mtu", mtu.intValue());
		}
		if (imgserver) {
			request.addParameter("imgserver", true);
		}
		response = this.communicate(request);
		if ((response.getReturnCode() != UdpReturnCodes.LOGIN_ACCEPTED)
				&& (response.getReturnCode()
					!= UdpReturnCodes.LOGIN_ACCEPTED_NEW_VER)) {
			throw new AniDbException(response.getReturnCode(),
					response.getReturnString(), response.getMessageString());
		}
		if (encoding != null) {
			this.charset = encoding;
		} else {
			this.charset = this.preferredCharset;
		}
		// TODO Solution for return code 201, maybe listener event?
		index = response.getReturnString().indexOf(' ');
		this.sessionKey = response.getReturnString().substring(0, index);
		this.sessionKey = this.sessionKey.trim();
	}
	
	/**
	 * <p>Logs the client out.</p>
	 * <p>If client is currently not logged in, an exception will be thrown.</p>
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 */
	public void logout() throws UdpConnectionException, AniDbException {
		UdpRequest request;
		UdpResponse response;
		
		if (this.socket == null) {
			throw new UdpConnectionException("Connection is already closed.");
		}
		if (!this.loggedIn) {
			throw new UdpConnectionException("Client is not logged in.");
		}
		request = new UdpRequest("LOGOUT");
		response = this.communicate(request);
		if (response.getReturnCode() != UdpReturnCodes.LOGGED_OUT) {
			throw new AniDbException(response.getReturnCode(),
					response.getReturnString(), response.getMessageString());
		}
	}
	
	/**
	 * <p>Sends a ping to a server.</p>
	 * <p>If the server doesn't respond, an exception will be thrown.</p>
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 */
	public void ping() throws UdpConnectionException, AniDbException {
		UdpRequest request;
		UdpResponse response;
		
		if (!this.isOpen()) {
			throw new UdpConnectionException("Connection is already closed.");
		}
		request = new UdpRequest("PING");
		// ROADMAP NAT Option.
//		request.addParameter("nat", true);
		response = this.communicate(request);
		if (response.getReturnCode() != UdpReturnCodes.PONG) {
			throw new AniDbException(response.getReturnCode(),
					response.getReturnString(), response.getMessageString());
		}
	}
	
	/**
	 * Returns the server version.
	 * @return The version.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 */
	public String getServerVersion()
		throws UdpConnectionException, AniDbException {
		
		UdpRequest request;
		UdpResponse response;
		
		if (!this.isOpen()) {
			throw new UdpConnectionException("Connection is already closed.");
		}
		request = new UdpRequest("VERSION");
		response = this.communicate(request);
		if (response.getReturnCode() != UdpReturnCodes.VERSION) {
			throw new AniDbException(response.getReturnCode(),
					response.getReturnString(), response.getMessageString());
		}
		return response.getTrimedMessageString();
	}
	
	/**
	 * <p>Returns the UDP server uptime in milliseconds.</p>
	 * <p>If client is currently not logged in, an exception will be thrown.</p>
	 * @return The uptime.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 */
	public long getUptime() throws UdpConnectionException, AniDbException {
		UdpRequest request;
		UdpResponse response;
		UdpResponseEntry entry;
		
		if (!this.isOpen()) {
			throw new UdpConnectionException("Connection is already closed.");
		}
		if (!this.isLoggedIn()) {
			throw new UdpConnectionException("Client is not logged in.");
		}
		request = new UdpRequest("UPTIME");
		response = this.communicate(request);
		if (response.getReturnCode() != UdpReturnCodes.UPTIME) {
			throw new AniDbException(response.getReturnCode(),
					response.getReturnString(), response.getMessageString());
		}
		if (response.getEntryCount() < 1) {
			throw new UdpConnectionException(
					"Received invalid response to the command UPTIME: "
					+ "The response has less than 1 entry: "
					+ response.toString());
		} else if (response.getEntryCount() > 1) {
			LOG.warn("Response to the command UPTIME has more than 1 "
					+ "entry: " + response.toString());
		}
		entry = response.getEntryAt(0);
		if (entry.getDataFieldCount() < 1) {
			throw new UdpConnectionException(
					"Received invalid response to the command UPTIME: "
					+ "The entry has less than 1 data fields: "
					+ response.toString());
		} else if (response.getEntryCount() > 1) {
			LOG.warn("The entry of the response to the command UPTIME has more "
					+ "than 1 data fields: " + response.toString());
		}
		try {
			return entry.getDataFieldAt(0).getValueAsLongValue();
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command UPTIME: "
					+ response.toString(), dfe);
		}
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
		
		return this.animeFactory.getAnime(animeId);
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
		
		return this.animeFactory.getAnime(animeName);
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
		
		return this.animeFactory.getAnime(animeId, mask);
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
		
		return this.animeFactory.getAnime(animeName, mask);
	}
	
	/**
	 * <p>Returns the description for the anime with the given Id.</p>
	 * <p>It should be considered that this method has to use more than one
	 * request for retrieving the complete description. The reason is that
	 * a descrption can take up to ~5000 characters while the maximum data of
	 * an UDP packet is ~1400 bytes.</p>
	 * @param animeId The anime Id.
	 * @return The description.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_ANIME
	 * @see UdpReturnCodes#NO_SUCH_ANIME_DESCRIPTION
	 */
	public String getAnimeDescription(final long animeId)
		throws UdpConnectionException, AniDbException {
		
		return this.animeDescFactory.getAnimeDescription(animeId);
	}
	
	/**
	 * Returns the episode with the given Id.
	 * @param episodeId The episode Id.
	 * @return The episode.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_EPISODE
	 */
	public Episode getEpisode(final long episodeId)
		throws UdpConnectionException, AniDbException {
		
		return this.episodeFactory.getEpisode(episodeId);
	}
	
	/**
	 * Returns the episode with the given anime name and episode number.
	 * @param animeName The anime name.
	 * @param episodeNumber The episode number.
	 * @return The episode.
	 * @throws IllegalArgumentException If the anime name is <code>null</code>.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_EPISODE
	 */
	public Episode getEpisode(final String animeName, final long episodeNumber)
		throws UdpConnectionException, AniDbException {
		
		return this.episodeFactory.getEpisode(animeName, episodeNumber);
	}
	
	/**
	 * Returns the episode with the given anime Id and episode number.
	 * @param animeId The anime Id.
	 * @param episodeNumber The episode number.
	 * @return The episode.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_EPISODE
	 */
	public Episode getEpisode(final long animeId, final long episodeNumber)
		throws UdpConnectionException, AniDbException {
		
		return this.episodeFactory.getEpisode(animeId, episodeNumber);
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
		
		return this.fileFactory.getFile(fileId, fileMask, animeFileMask);
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
		
		return this.fileFactory.getFiles(size, ed2kHash, fileMask,
				animeFileMask);
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
		
		return this.fileFactory.getFiles(animeName, groupName, episodeNumber,
				fileMask, animeFileMask);
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
		
		return this.fileFactory.getFiles(animeName, groupId, episodeNumber,
				fileMask, animeFileMask);
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
		
		return this.fileFactory.getFiles(animeId, groupName, episodeNumber,
				fileMask, animeFileMask);
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
		
		return this.fileFactory.getFiles(animeId, groupId, episodeNumber,
				fileMask, animeFileMask);
	}
	
	/**
	 * Returns the group with the given group Id.
	 * @param groupId The group Id.
	 * @return The group.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_GROUP
	 */
	public Group getGroup(final long groupId)
		throws UdpConnectionException, AniDbException {
		
		return this.groupFactory.getGroup(groupId);
	}
	
	/**
	 * Returns the group with the given group name or short name.
	 * @param groupName The group name or short name.
	 * @return The group.
	 * @throws IllegalArgumentException If the group name is <code>null</code>.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_GROUP
	 */
	public Group getGroup(final String groupName)
		throws UdpConnectionException, AniDbException {
		
		return this.groupFactory.getGroup(groupName);
	}
	
	/**
	 * <p>Returns a list of the group status for the anime with the given anime
	 * Id.</p>
	 * @param animeId The anime Id.
	 * @return The list of the group status. 
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_GROUPS_FOUND
	 * @see UdpReturnCodes#NO_SUCH_ANIME
	 */
	public List<GroupStatus> getGroupStatus(final long animeId)
		throws UdpConnectionException, AniDbException {
		
		return this.groupStatusFactory.getGroupStatus(animeId);
	}
	
	/**
	 * <p>Returns a list of the group status for the anime with the given anime
	 * Id and the given state.</p>
	 * @param animeId The anime Id.
	 * @param state The state.
	 * @return The list of the group status. 
	 * @throws IllegalArgumentException If the state is <code>null</code>.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_GROUPS_FOUND
	 * @see UdpReturnCodes#NO_SUCH_ANIME
	 */
	public List<GroupStatus> getGroupStatus(final long animeId,
		final GroupStatusState state)
		throws UdpConnectionException, AniDbException {
		
		return this.groupStatusFactory.getGroupStatus(animeId, state);
	}
	
	/**
	 * Returns the creator with the given creator Id.
	 * @param creatorId The creator Id.
	 * @return The creator.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_CREATOR
	 */
	public Creator getCreator(final long creatorId)
		throws UdpConnectionException, AniDbException {
		
		return this.creatorFactory.getCreator(creatorId);
	}
	
	/**
	 * Returns the character with the given character Id.
	 * @param characterId The character Id.
	 * @return The character.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_CHARACTER
	 */
	public Character getCharacter(final long characterId)
		throws UdpConnectionException, AniDbException {
		
		return this.characterFactory.getCharacter(characterId);
	}
	
	/**
	 * <p>Returns a list of entries of all pending (not acknowledged) new
	 * private message and new file notifications.</p>
	 * <p>Buddy events cannot be acknowledged.</p> 
	 * @return The list.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 */
	public List<NotificationListEntry> getNotifyList()
		throws UdpConnectionException, AniDbException {
		
		return this.notificationFactory.getNotifyList();
	}
	
	
	/**
	 * Returns the notification for the given list entry.
	 * @param listEntry The list entry.
	 * @return The notification.
	 * @throws IllegalArgumentException If the list entry is null.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_MESSAGE
	 * @see UdpReturnCodes#NO_SUCH_NOTIFY
	 */
	public Notification getNotification(final NotificationListEntry listEntry)
			throws UdpConnectionException, AniDbException {
		
		return this.notificationFactory.getNotification(listEntry);
	}
	
	/**
	 * Returns the notification for the given type and Id.
	 * @param type The type.
	 * @param id The Id.
	 * @return The notification.
	 * @throws IllegalArgumentException If the type is <code>null</code> or the
	 * given type isn't supported.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_MESSAGE
	 * @see UdpReturnCodes#NO_SUCH_NOTIFY
	 */
	public Notification getNotification(final String type, final long id)
			throws UdpConnectionException, AniDbException {
		
		return this.notificationFactory.getNotification(type, id);
	}
	
	
	/**
	 * Acknowledges the notification for the given list entry.
	 * @param listEntry The list entry.
	 * @throws IllegalArgumentException If the list entry is null.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_ENTRY_M
	 * @see UdpReturnCodes#NO_SUCH_ENTRY_N
	 */
	public void acknowledgeNotification(final NotificationListEntry listEntry)
			throws UdpConnectionException, AniDbException {
		
		this.notificationFactory.acknowledgeNotification(listEntry);
	}
	
	/**
	 * Acknowledges the notification for the given type and Id.
	 * @param type The type.
	 * @param id The Id.
	 * @throws IllegalArgumentException If the type is <code>null</code> or the
	 * given type isn't supported.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_ENTRY_M
	 * @see UdpReturnCodes#NO_SUCH_ENTRY_N
	 */
	public void acknowledgeNotification(final String type, final long id)
			throws UdpConnectionException, AniDbException {
		
		this.notificationFactory.acknowledgeNotification(type, id);
	}
	
	/**
	 * Sends a message to the user with the given user name.
	 * @param userName The user name.
	 * @param title The title.
	 * @param body The body.
	 * @throws IllegalArgumentException If the user name is <code>null</code>.
	 * @throws IllegalArgumentException If the title is <code>null</code> or
	 * has more than 50 chars.
	 * @throws IllegalArgumentException If the body is <code>null</code> or has
	 * more than 900 chars.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#SENDMSG_SUCCESSFUL
	 * @see UdpReturnCodes#NO_SUCH_USER
	 * @see UdpReturnCodes#LOGIN_FIRST
	 */
	public void sendMessage(final String userName, final String title,
			final String body) throws UdpConnectionException, AniDbException {
		
		UdpRequest request;
		UdpResponse response;
		
		if (userName == null) {
			throw new IllegalArgumentException("Argument userName is null.");
		}
		if (title == null) {
			throw new IllegalArgumentException("Argument title is null.");
		}
		if (title.length() > 50) {
			throw new IllegalArgumentException(
					"The length of argument title is greater than 50: "
					+ title.length());
		}
		if (body == null) {
			throw new IllegalArgumentException("Argument body is null.");
		}
		if (body.length() > 900) {
			throw new IllegalArgumentException(
					"The length of argument title is greater than 900: "
					+ body.length());
		}
		if (!this.isOpen()) {
			throw new UdpConnectionException("Connection is already closed.");
		}
		if (!this.isLoggedIn()) {
			throw new UdpConnectionException("Client is not logged in.");
		}
		request = new UdpRequest("SENDMSG");
		request.addParameter("to", userName);
		request.addParameter("title", title);
		request.addParameter("body", body);
		response = this.communicate(request);
		if (response.getReturnCode() != UdpReturnCodes.SENDMSG_SUCCESSFUL) {
			throw new AniDbException(response.getReturnCode(),
					response.getReturnString(), response.getMessageString());
		}
	}
	
	/**
	 * Returns the user Id for the user with the given user name.
	 * @param userName The user name.
	 * @return The user Id.
	 * @throws IllegalArgumentException If the user name is <code>null</code>.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @throws AniDbException If a problem with AniDB occured.
	 * @see UdpReturnCodes#NO_SUCH_USER
	 */
	public long getUserId(final String userName)
			throws UdpConnectionException, AniDbException {
		
		UdpRequest request;
		UdpResponse response;
		UdpResponseEntry entry;
		
		if (userName == null) {
			throw new IllegalArgumentException("Argument userName is null.");
		}
		if (!this.isOpen()) {
			throw new UdpConnectionException("Connection is already closed.");
		}
		request = new UdpRequest("USER");
		request.addParameter("user", userName);
		response = this.communicate(request);
		if (response.getReturnCode() != UdpReturnCodes.USER) {
			throw new AniDbException(response.getReturnCode(),
					response.getReturnString(), response.getMessageString());
		}
		if (response.getEntryCount() < 1) {
			throw new UdpConnectionException(
					"Received invalid response to the command USER: "
					+ "The response has less than 1 entry: "
					+ response.toString());
		} else if (response.getEntryCount() > 1) {
			LOG.warn("Response to the command USER has more than 1 "
					+ "entry: " + response.toString());
		}
		entry = response.getEntryAt(0);
		if (entry.getDataFieldCount() < 1) {
			throw new UdpConnectionException(
					"Received invalid response to the command USER: "
					+ "The entry has less than 1 data fields: "
					+ response.toString());
		} else if (response.getEntryCount() > 1) {
			LOG.warn("The entry of the response to the command USER has more "
					+ "than 1 data fields: " + response.toString());
		}
		try {
			return entry.getDataFieldAt(0).getValueAsLongValue();
		} catch (DataFieldException dfe) {
			throw new UdpConnectionException(
					"Received invalid response to the command USER: "
					+ response.toString(), dfe);
		}
	}
}
