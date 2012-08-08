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
package net.anidb.udp;


/**
 * <p>Encodes the data of a request.</p>
 * <p>See <a href="http://wiki.anidb.info/w/UDP_API_Definition#Content_Encoding">
 * Content Encoding</a> for further information.</p>
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 12.01.2010
 */
public class DataEncoder {
	/** The singleton object. */
	private static DataEncoder encoder;
	
	/**
	 * Creates a encoder.
	 */
	private DataEncoder() {
		super();
	}
	
	/**
	 * Returns an instance of the class.
	 * @return The instance.
	 */
	public synchronized static DataEncoder getInstance() {
		if (encoder == null) {
			encoder = new DataEncoder();
		}
		return encoder;
	}
	
	/**
	 * Encodes the given string.
	 * @param rawData The string.
	 * @return The encoded string.
	 * @throws IllegalArgumentException If the given string is
	 * <code>null</code>.
	 */
	public String encode(final String rawData) {
		StringBuffer encodedData;
		char[] cArray;
		char ch;
		
		if (rawData == null) {
			throw new IllegalArgumentException("Argument rawData is null.");
		}
		encodedData = new StringBuffer();
		cArray = rawData.toCharArray();
		for (int index = 0; index < cArray.length; index++) {
			ch = rawData.charAt(index);
			if (ch == '\n') {
				encodedData.append("<br />");
			} else if (ch == '&') {
				encodedData.append("&amp;");
			} else {
				encodedData.append(ch);
			}
		}
		return encodedData.toString();
	}
}
