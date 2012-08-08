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
package net.anidb.http;

import net.anidb.udp.UdpConnection;

/**
 * <p>A connection to AniDB via HTTP.</p>
 * <p>For further information on the UDP communication look at the
 * <a href="http://wiki.anidb.info/w/HTTP_API_Definition">HTTP API
 * Definition</a>.</p>
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 12.01.2010
 */
public class HttpConnection {
	/** The supported protocol version. */
	public final static int PROTOCOL_VERSION = 3;
	/** The client name of the Java AniDB API. */
	public final static String CLIENT_NAME = UdpConnection.CLIENT_NAME;
	/** The client version of this version of the Java AniDB API. */
	public final static int CLIENT_VERSION = UdpConnection.CLIENT_VERSION;
	
	/**
	 * Creates a HTTP connection.
	 */
	protected HttpConnection() {
		super();
	}
	
	// TODO Implement HttpConnection.
}
