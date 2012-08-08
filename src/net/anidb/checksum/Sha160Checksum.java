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
 * A class that can be used to compute the SHA160 checksum of a data stream.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 03.01.2010
 */
public class Sha160Checksum extends Checksum {
	/** The block size. */
	private final static int BLOCK_SIZE = 64;
	/** The buffer size. */
	private final static int BUFFER_SIZE = 512;
	/** The constants. */
	private final static int[] CONSTS = {
		0x5a827999, 0x6ed9eba1, 0x8f1bbcdc, 0xca62c1d6
	};
	
	/** The data buffer. */
	private byte[] buffer = new byte[BUFFER_SIZE];
	/** The offset. */
	private int bufferOffset = 0;
	
	/** Current state. */
	private int[] state = new int[5];
	/** Count of bytes processed. */
	private long count;
	/** The current block. */
	private byte[] block = new byte[BLOCK_SIZE];
	
	/**
	 * Creates a SHA160 checksum object.
	 */
	public Sha160Checksum() {
		super();
		this.reset();
	}
	
	public void reset() {
		this.bufferOffset = 0;
		this.state[0] = 0x67452301;
		this.state[1] = 0xefcdab89;
		this.state[2] = 0x98badcfe;
		this.state[3] = 0x10325476;
		this.state[4] = 0xc3d2e1f0;
		this.count = 0;
	}
	
	private void transform() {
		int a, b, c, d, e, r, t, f;
		int[] words = new int[80];
		
		// Copy state.
		a = this.state[0];
		b = this.state[1];
		c = this.state[2];
		d = this.state[3];
		e = this.state[4];
		
		// Convert to 32 bit words.
		for (int index = 0; index < 16; index++) {
			words[index] = (this.block[index * 4] << 24)
				| ((this.block[(index * 4) + 1] & 0xFF) << 16)
				| ((this.block[(index * 4) + 2] & 0xFF) << 8)
				| (this.block[(index * 4) + 3] & 0xFF);
		}
		for (r = 16; r < 80; r++) {
			t = words[r - 3] ^ words[r - 8] ^ words[r - 14] ^ words[r - 16];
			words[r] = (t << 1) | (t >>> 31);
		}
		for (r = 0; r < 80; r++) {
			if (r < 20) {
				f = ((b & c) | (~b & d));
			} else if (r < 40) {
				f = (b ^ c ^ d);
			} else if (r < 60) {
				f = ((b & c) | (b & d) | (c & d));
			} else {
				f = (b ^ c ^ d);
			}
			
			t = ((a << 5) | (a >>> 27)) + f + e + words[r] + CONSTS[r / 20];
			e = d;
			d = c;
			c = (b << 30) | (b >>> 2);
			b = a;
			a = t;
		}
		
		// Add to state.
		this.state[0] += a;
		this.state[1] += b;
		this.state[2] += c;
		this.state[3] += d;
		this.state[4] += e;
	}
	
	private void update() {
		int blockIndex, bufferIndex = 0;
		
		if (this.bufferOffset == 0) {
			return;
		}
		// Calculate old block offset.
		blockIndex = (int) (this.count % BLOCK_SIZE);
		this.count += this.bufferOffset;
		while (bufferIndex < this.bufferOffset) {
			this.block[blockIndex] = this.buffer[bufferIndex];
			blockIndex++;
			if (blockIndex == BLOCK_SIZE) {
				this.transform();
				blockIndex = 0;
			}
			bufferIndex++;
		}
		this.bufferOffset = 0;
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
			if (this.bufferOffset == BUFFER_SIZE) {
				this.update();
			}
		}
	}
	
	public void update(final int data) {
		this.buffer[this.bufferOffset++] = (byte) data;
		if (this.bufferOffset == BUFFER_SIZE) {
			this.update();
		}
	}
	
	private void finish() {
		int blockIndex;
		long bits;
		
		this.update();
		blockIndex = (int) (this.count % BLOCK_SIZE);
		// Padding
		this.block[blockIndex++] = (byte) 0x80;
		if (blockIndex > 56) {
			while (blockIndex < BLOCK_SIZE) {
				this.block[blockIndex++] = 0;
			}
			this.transform();
			blockIndex = 0;
		}
		while (blockIndex < 56) {
			this.block[blockIndex++] = 0;
		}
		bits = this.count << 3;
		this.block[blockIndex++] = (byte) (bits >>> 56);
		this.block[blockIndex++] = (byte) (bits >>> 48);
		this.block[blockIndex++] = (byte) (bits >>> 40);
		this.block[blockIndex++] = (byte) (bits >>> 32);
		this.block[blockIndex++] = (byte) (bits >>> 24);
		this.block[blockIndex++] = (byte) (bits >>> 16);
		this.block[blockIndex++] = (byte) (bits >>> 8);
		this.block[blockIndex] = (byte) bits;
		this.transform();
	}
	
	public byte[] getDigest() {
		byte[] digest = new byte[20];
		
		this.finish();
		for (int index = 0; index < this.state.length; index++) {
			digest[index * 4] = (byte) (this.state[index] >>> 24);
			digest[(index * 4) + 1] = (byte) (this.state[index] >>> 16);
			digest[(index * 4) + 2] = (byte) (this.state[index] >>> 8);
			digest[(index * 4) + 3] = (byte) this.state[index];
		}
		return digest;
	}
}
