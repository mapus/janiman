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
package net.anidb.checksum;

/**
 * The super class of all checksum / hash classes.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 03.01.2010
 */
public abstract class Checksum {
	/**
	 * Creates a checksum object.
	 */
	public Checksum() {
		super();
	}
	
	/**
	 * <p>Builds and returns the digest.</p>
	 * <p>After calling this method you have to use {@link #reset()}.</p>
	 * @return The digest.
	 * @see #reset()
	 * @see #getHexDigest()
	 */
	public abstract byte[] getDigest();
	
	/**
	 * Calls {@link #getDigest()} and returns the digest into a hex format.</p>
	 * @return The digest in hexdecimal presentation.
	 * @see #getDigest()
	 */
	public String getHexDigest() {
		StringBuffer sb;
		byte[] digest;
		int number;
		String hexNumber;
		
		digest = this.getDigest();
		sb = new StringBuffer();
		for (int index = 0; index < digest.length; index++) {
			number = digest[index];
			if (number < 0) {
				number += 256;
			}
			hexNumber = Integer.toHexString(number);
			if (hexNumber.length() < 2) {
				sb.append('0');
			}
			sb.append(hexNumber);
		}
		return sb.toString();
	}
	
	/**
	 * Resets the checksum object.
	 */
	public abstract void reset();
	
	/**
	 * Updates the checksum with data.
	 * @param buf The data buffer.
	 * @param off The offset.
	 * @param len The length.
	 * @throws IllegalArgumentException If the data buffer is <code>null</code>.
	 * @throws IllegalArgumentException If the offset is less than 0.
	 * @throws IllegalArgumentException If the length is less than 1.
	 * @throws IllegalArgumentException If the offset + the length is greater
	 * than the length of the data buffer.
	 */
	public abstract void update(final byte[] buf, final int off, final int len);
	
	/**
	 * Updates the checksum with data.
	 * @param data The data.
	 */
	public abstract void update(final int data);
}
