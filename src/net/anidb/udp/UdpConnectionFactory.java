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

import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Creates and organizes UDP connections to AniDB.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 29.11.2009
 * @see UdpConnection
 */
public class UdpConnectionFactory {
	/** The singleton object. */
	private static UdpConnectionFactory factory = null;
	
	/** The standard host. */
	public final static String HOST = "api.anidb.net";
	/** The standard remote port. */
	public final static int PORT = 9000;
	/** The connection timeout. */
	public final static int TIMEOUT = 60000;
	
	/** The major version of the Java AniDB API. */
	public final static int MAJOR_VERSION = 0;
	/** The minor version of the Java AniDB API. */
	public final static int MINOR_VERSION = 2;
	
	private UdpConnectionFactory() {
		super();
	}
	
	/**
	 * Returns a instance of the class.
	 * @return The instance.
	 */
	public static synchronized UdpConnectionFactory getInstance() {
		if (factory == null) {
			factory = new UdpConnectionFactory();
		}
		return factory;
	}
	
	/**
	 * <p>Build a connection with the given values.</p>
	 * <p>For further information for the timeout see
	 * {@link DatagramSocket#setSoTimeout(int)}.</p>
	 * @param localPort The local port.
	 * @param host The host, normally {@link #HOST}.
	 * @param remotePort The remote port, normally {@link #PORT}.
	 * @param timeout The timeout in milliseconds.
	 * @param floodProtection The state of the flood protection.
	 * @return The connection.
	 * @throws IllegalArgumentException If the local port is equal or less than
	 * 1024.
	 * @throws IllegalArgumentException If the host is <code>null</code>.
	 * @throws IllegalArgumentException If the timeout is less than
	 * <code>0</code>.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @see UdpConnection#UdpConnection(DatagramSocket, boolean)
	 */
	public UdpConnection connect(final int localPort, final String host,
		final int remotePort, final int timeout, final boolean floodProtection)
		throws UdpConnectionException {
		
		DatagramSocket socket;
		InetAddress address;
		
		if (localPort <= 1024) {
			throw new IllegalArgumentException(
				"Value of argument localPort is less or equal 1024: "
				+ localPort);
		}
		if (host == null) {
			throw new IllegalArgumentException("Argument host is null.");
		}
		if (timeout < 0) {
			throw new IllegalArgumentException(
				"Value of timeout is less than 0: " + timeout);
		}
		try {
			address = InetAddress.getByName(host);
		} catch (Throwable t) {
			throw new UdpConnectionException("Couldn't resolve the host '"
				+ host + "': " + t.getLocalizedMessage(), t);
		}
		try {
			socket = new DatagramSocket(localPort);
		} catch (Throwable t) {
			throw new UdpConnectionException("Couldn't create the UDP socket "
				+ "on local port " + localPort + ": " + t.getLocalizedMessage(),
				t);
		}
		try {
			socket.setSoTimeout(timeout);
		} catch (Throwable t) {
			socket.close();
			throw new UdpConnectionException("Couldn't set timeout of UDP "
				+ "socket to " + timeout + ":" + t.getLocalizedMessage(), t);
		}
		try {
			socket.connect(address, remotePort);
		} catch (Throwable t) {
			socket.close();
			throw new UdpConnectionException("Couldn't connect the UDP socket "
				+ "to host '" + host + "' and remote port " + remotePort + ": "
				+ t.getLocalizedMessage(), t);
		}
		return (new UdpConnection(socket, floodProtection));
	}
	
	/**
	 * <p>Build a connection with the given values.</p>
	 * <p>For the timeout the standard value will be used.</p>
	 * <p>The flood protection will be enabled.</p>
	 * @param localPort The local port.
	 * @param host The host, normally {@link #HOST}.
	 * @param remotePort The remote port, normally {@link #PORT}.
	 * @return The connection.
	 * @throws IllegalArgumentException If the local port is equal or less than
	 * 1024.
	 * @throws IllegalArgumentException If the host is <code>null</code>.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @see UdpConnection#UdpConnection(DatagramSocket, boolean)
	 */
	public UdpConnection connect(final int localPort, final String host,
		final int remotePort) throws UdpConnectionException {
		
		if (localPort <= 1024) {
			throw new IllegalArgumentException(
				"Value of argument localPort is less or equal 1024: "
				+ localPort);
		}
		if (host == null) {
			throw new IllegalArgumentException("Argument host is null.");
		}
		return this.connect(localPort, host, remotePort, TIMEOUT, true);
	}
	
	/**
	 * <p>Build a connection with the given local port.</p>
	 * <p>For the host, the remote port and the timeout the standard values will
	 * be used.</p>
	 * <p>The flood protection will be enabled.</p>
	 * @param localPort The local port.
	 * @return The connection.
	 * @throws IllegalArgumentException If the local port is equal or less than
	 * 1024.
	 * @throws UdpConnectionException If a connection problem occured.
	 * @see UdpConnection#UdpConnection(DatagramSocket, boolean)
	 */
	public UdpConnection connect(final int localPort)
		throws UdpConnectionException {
		
		if (localPort <= 1024) {
			throw new IllegalArgumentException(
				"Value of argument localPort is less or equal 1024: "
				+ localPort);
		}
		return this.connect(localPort, HOST, PORT);
	}
}
