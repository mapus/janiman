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
 * <p>Decodes the data of a response.</p>
 * <p>See <a href="http://wiki.anidb.info/w/UDP_API_Definition#Content_Encoding">
 * Content Encoding</a> for further information.</p>
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 12.01.2010
 */
public class DataDecoder {
	/** The singleton object. */
	private static DataDecoder decoder;
	
	/**
	 * Creates a decoder.
	 */
	private DataDecoder() {
		super();
	}
	
	/**
	 * Returns an instance of the class.
	 * @return The instance.
	 */
	public synchronized static DataDecoder getInstance() {
		if (decoder == null) {
			decoder = new DataDecoder();
		}
		return decoder;
	}
	
	/**
	 * Decodes the given string.
	 * @param rawData The string.
	 * @return The decoded string.
	 * @throws IllegalArgumentException If the given string is
	 * <code>null</code>.
	 */
	public String decode(final String rawData) {
		String decodedData;
		
		if (rawData == null) {
			throw new IllegalArgumentException("Argument rawData is null.");
		}
		decodedData = rawData.replaceAll("<br />", "\n");
		return decodedData;
	}
}
