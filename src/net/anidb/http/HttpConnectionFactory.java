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

import net.anidb.udp.UdpConnectionFactory;

/**
 * Creates and organizes HTTP connections to AniDB.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 12.01.2010
 */
public class HttpConnectionFactory {
	/** The singleton object. */
	private static HttpConnectionFactory factory = null;
	
	/** The standard host. */
	public final static String HOST = "api.anidb.net";
	/** The standard CGI. */
	public final static String CGI = "httpapi";
	/** The standard remote port. */
	public final static int PORT = 9000;
	/** The connection timeout. */
	public final static int TIMEOUT = 10000;
	
	/** The major version of the Java AniDB API. */
	public final static int MAJOR_VERSION = UdpConnectionFactory.MAJOR_VERSION;
	/** The minor version of the Java AniDB API. */
	public final static int MINOR_VERSION = UdpConnectionFactory.MINOR_VERSION;
	
	private HttpConnectionFactory() {
		super();
	}
	
	/**
	 * Returns a instance of the class.
	 * @return The instance.
	 */
	public static synchronized HttpConnectionFactory getInstance() {
		if (factory == null) {
			factory = new HttpConnectionFactory();
		}
		return factory;
	}
	
	// TODO Implement HttpConnectionFactory.
}
