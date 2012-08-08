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

/**
 * <p>Thrown if a problem with AniDB occurs.</p>
 * <p>This is the case if AniDB responds with an error code.</p>
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 03.12.2009
 */
public class AniDbException extends Exception {
	/** The return code. */
	private int returnCode;
	/** The return string. */
	private String returnString;
	/** The message string. */
	private String messageString;
	
	/**
	 * Creates an exception.
	 * @param returnCode The return code.
	 * @param returnString The return string.
	 * @param messageString The message string.
	 * @throws IllegalArgumentException If the return string is null.
	 */
	public AniDbException(final int returnCode, final String returnString,
		final String messageString) {
		
		super(returnCode + " " + returnString
				+ (messageString == null ? "" : ": " + messageString));
		if (returnString == null) {
			throw new IllegalArgumentException(
				"Argument returnString is null.");
		}
		this.returnString = returnString;
		this.messageString = messageString;
	}
	
	/**
	 * Returns the return code.
	 * @see UdpReturnCodes
	 * @return The return code.
	 */
	public int getReturnCode() {
		return this.returnCode;
	}
	
	/**
	 * Returns the return string.
	 * @return The return string.
	 */
	public String getReturnString() {
		return this.returnString;
	}
	
	/**
	 * Return ths message string.
	 * @return The message string or <code>null</code>, if it isn't set.
	 */
	public String getMessageString() {
		return this.messageString;
	}
}
