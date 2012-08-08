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

/**
 * Thrown if a problem with the HTTP classes occurs.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 12.01.2010
 */
public class HttpConnectionException extends Exception {
	/**
	 * Constructs a new Exception.
	 */
	public HttpConnectionException() {
		super();
	}
	
	/**
	 * Constructs a new Exception with a message.
	 * @param message The message.
	 */
	public HttpConnectionException(final String message) {
		super(message);
	}
	
	/**
	 * Constructs a new Exception with a cause.
	 * @param cause The cause.
	 */
	public HttpConnectionException(final Throwable cause) {
		super(cause);
	}
	
	/**
	 * Constructs a new Exception with a message and a cause.
	 * @param message The message.
	 * @param cause The cause.
	 */
	public HttpConnectionException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
