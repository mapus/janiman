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
 * A class that can be used to compute the Tiger Tree checksum of a data stream.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 06.01.2010
 */
public class TigerTreeChecksum extends Checksum {
	/** The Tiger checksum. */
	private TigerChecksum tiger = new TigerChecksum();
	/** The data buffer. */
	private byte[] buffer = new byte[1024];
	/** The offset. */
	private int bufferOffset = 0;
	/** The nodes. */
	private Vector<byte[]> nodes = new Vector<byte[]>();
	
	/**
	 * Creates a Tiger Tree checksum object.
	 */
	public TigerTreeChecksum() {
		super();
	}
	
	public void reset() {
		this.bufferOffset = 0;
		this.nodes.removeAllElements();
	}
	
	private void update() {
		byte[] digest;
		// Node prefix
		this.tiger.update(0);
		this.tiger.update(this.buffer, 0, this.bufferOffset);
		digest = this.tiger.getDigest();
		this.nodes.addElement(digest);
		this.tiger.reset();
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
		Vector<byte[]> nodes;
		Iterator<byte[]> iterator;
		byte[] leftNode, rightNode;
		
		if (this.bufferOffset != 0) {
			this.update();
		}
		if (this.nodes.size() == 0) {
			this.nodes.addElement(this.tiger.getDigest());
			this.tiger.reset();
		}
		nodes = new Vector<byte[]>(this.nodes);
		while (nodes.size() > 1) {
			iterator = nodes.iterator();
			nodes = new Vector<byte[]>();
			while (iterator.hasNext()) {
				leftNode = iterator.next();
				if (iterator.hasNext()) {
					rightNode = iterator.next();
					// Node prefix
					this.tiger.update(1);
					this.tiger.update(leftNode, 0, leftNode.length);
					this.tiger.update(rightNode, 0, rightNode.length);
					nodes.addElement(this.tiger.getDigest());
					this.tiger.reset();
				} else {
					nodes.addElement(leftNode);
				}
			}
		}
		return nodes.get(0);
	}
}
