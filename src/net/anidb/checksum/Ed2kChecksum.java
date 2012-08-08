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

import java.util.Iterator;
import java.util.Vector;

/**
 * A class that can be used to compute the Ed2k checksum of a data stream.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 06.01.2010
 */
public class Ed2kChecksum extends Checksum {
	/** The size of a chunk. */
	private final static int CHUNK_SIZE = 9728000;
	
	/** The MD4 checksum. */
	private Md4Checksum md4 = new Md4Checksum();
	/** The data buffer. */
	private byte[] buffer = new byte[1024];
	/** The offset. */
	private int bufferOffset = 0;
	/** The MD4 hash list. */
	private Vector<byte[]> hashList = new Vector<byte[]>();
	/** The processed bytes. */
	private long count;
	
	/**
	 * Creates an Ed2k checksum object.
	 */
	public Ed2kChecksum() {
		super();
	}
	
	public void reset() {
		this.md4.reset();
		this.bufferOffset = 0;
		this.hashList.removeAllElements();
		this.count = 0;
	}
	
	private void update() {
		this.md4.update(this.buffer, 0, this.bufferOffset);
		this.count += this.bufferOffset;
		this.bufferOffset = 0;
		if (this.count % CHUNK_SIZE == 0) {
			this.hashList.addElement(this.md4.getDigest());
			this.md4.reset();
		}
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
		for (int index = 0; index < len; index++) {
			this.buffer[this.bufferOffset++] = buf[off + index];
			if (this.bufferOffset == this.buffer.length) {
				this.update();
			}
		}
	}
	
	public void update(final int data) {
		this.buffer[this.bufferOffset++] = (byte) data;
		if (this.bufferOffset == this.buffer.length) {
			this.update();
		}
	}
	
	public byte[] getDigest() {
		Iterator<byte[]> iterator;
		byte[] digest;
		int rest;
		
		if (this.bufferOffset != 0) {
			this.update();
		}
		rest = (int) (this.count % CHUNK_SIZE);
		if (rest > 0) {
			this.hashList.addElement(this.md4.getDigest());
			this.md4.reset();
		}
		if (rest == 0) {
			this.hashList.addElement(this.md4.getDigest());
			this.md4.reset();
		}
		
		if (this.hashList.size() == 0) {
			return this.md4.getDigest();
		} else if (this.hashList.size() == 1) {
			return this.hashList.firstElement();
		}
		iterator = this.hashList.iterator();
		while (iterator.hasNext()) {
			digest = iterator.next();
			this.md4.update(digest, 0, digest.length);
		}
		return this.md4.getDigest();
	}
}
