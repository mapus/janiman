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

import java.util.zip.CRC32;

/**
 * A CRC32 checksum.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 03.01.2010
 */
public class Crc32Checksum extends Checksum {
	/** The CRC32. */
	private CRC32 crc32 = new CRC32();
	
	/**
	 * Creates a CRC32 checksum object.
	 */
	public Crc32Checksum() {
		super();
	}
	
	public byte[] getDigest() {
		byte[] digest = new byte[4];
		long value;
		
		value = this.crc32.getValue();
		digest[0] = (byte) ((value >> 24) & 0xFF);
		digest[1] = (byte) ((value >> 16) & 0xFF);
		digest[2] = (byte) ((value >> 8) & 0xFF);
		digest[3] = (byte) (value & 0xFF);
		return digest;
	}
	
	public void reset() {
		this.crc32.reset();
	}
	
	public void update(final byte[] buf, final int off, final int len) {
		if (buf == null) {
			throw new IllegalArgumentException("Argument buf is null.");
		}
		if (off < 0) {
			throw new IllegalArgumentException(
					"Value of argument off is less than 0: " + off);
		}
		if (len < 1) {
			throw new IllegalArgumentException(
					"Value of argument len is less than 1: " + len);
		}
		if (off + len > buf.length) {
			throw new IllegalArgumentException("The sum of the values of the "
					+ "arguments off and len is greater than the length of "
					+ "argument buf: off = " + off + "; len = " + len
					+ "; buf.length = " + buf.length);
		}
		this.crc32.update(buf, off, len);
	}
	
	public void update(final int data) {
		this.crc32.update(data);
	}
}
