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
package net.anidb.udp.mask;

/**
 * The superclass of all mask classes.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 19.12.2009
 */
public abstract class Mask {
	/**
	 * Creates a mask.
	 */
	public Mask() {
		super();
	}
	
	/**
	 * Returns the mask as a value.
	 * @return The value.
	 */
	public abstract long getMask();
	
	/**
	 * Returns the mask as a hex value.
	 * @param length The length of the mask.
	 * @return The hex value.
	 */
	protected String getHexMask(final int length) {
		StringBuffer sb;
		
		sb = new StringBuffer(Long.toHexString(this.getMask()));
		while (sb.length() < length) {
			sb.insert(0, '0');
		}
		return sb.toString();
	}
	
	/**
	 * <p>Returns the mask as a hex value.</p>
	 * <p>The hex value will allways have the constant and full length.</p>
	 * @return The hex value.
	 */
	public abstract String getHexMask();
	
	/**
	 * Sets the given bit in the given byte of the given value.
	 * @param byteNr The byte number.
	 * @param byteCount the count of bytes.
	 * @param bitNr The bit number.
	 * @param value The value.
	 * @return The modified value.
	 */
	protected long setBit(final int byteNr, final int byteCount,
		final int bitNr, final long value) {
		
		long v = 1L << (((byteCount - byteNr) * 8) + bitNr);
		return value | v;
	}
	
	/**
	 * Returns the count how many flags are set.
	 * @return The count.
	 */
	public abstract int getFlagCount();
	
	public abstract int hashCode();
	
	public abstract boolean equals(final Object obj);
}
