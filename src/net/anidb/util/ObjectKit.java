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
package net.anidb.util;

/**
 * A kit for object operations.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 31.12.2009
 */
public class ObjectKit {
	private ObjectKit() {
		super();
	}
	
	/**
	 * <p>Calculates a new hash for the object and the old hash.</p>
	 * <p>This can be used for calculating the hash of an object.</p>
	 * @param obj The object.
	 * @param hash The old hash.
	 * @return The new hash.
	 * @see Object#hashCode()
	 */
	public static int hash(final Object obj, final int hash) {
		if (obj == null) {
			return 37 * hash;
		}
		return 37 * hash + obj.hashCode();
	}
	
	/**
	 * <p>Compares the two objects.</p>
	 * <p>Attention: If they are both <code>null</code>, they are not equal.</p>
	 * @param thatObj The other object.
	 * @param thisObj The object which called the method.
	 * @return <code>true</code>, if the both objects are equals,
	 * <code>false</code> otherwise.
	 */
	public static boolean equals(final Object thatObj, final Object thisObj) {
		if ((thatObj != null) && (thisObj != null) && thatObj.equals(thisObj)) {
			return true;
		}
		return false;
	}
}
