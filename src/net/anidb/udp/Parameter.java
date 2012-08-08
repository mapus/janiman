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
 * A parameter.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 29.11.2009
 */
public class Parameter {
	/** The name. */
	private String name;
	/** The value. */
	private String value;
	
	/**
	 * Creates a parameter.
	 * @param name The name.
	 * @param value The value.
	 * @throws IllegalArgumentException If the name is <code>null</code>.
	 * @throws IllegalArgumentException If the value is <code>null</code>.
	 */
	protected Parameter(final String name, final String value) {
		super();
		if (name == null) {
			throw new IllegalArgumentException("Argument name is null.");
		}
		this.name = name;
		if (value == null) {
			throw new IllegalArgumentException("Argument value is null.");
		}
		this.value = value;
	}
	
	/**
	 * Returns the name.
	 * @return The name.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the value.
	 * @return The value.
	 */
	public String getValue() {
		return this.value;
	}
}
