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
package net.anidb;

/**
 * The creator type.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 07.12.2009
 * @see Creator#getType()
 * @see Creator#setType(Integer)
 */
public class CreatorType {
	/** Person. */
	public final static CreatorType PERSON = new CreatorType(1);
	/** Company. */
	public final static CreatorType COMPANY = new CreatorType(2);
	/** Collaboration. */
	public final static CreatorType COLLABORATION = new CreatorType(3);
	
	/** The value. */
	private int value;
	
	private CreatorType(final int value) {
		super();
		this.value = value;
	}
	
	/**
	 * Returns an instance of the class for the given value.
	 * @param value The value.
	 * @return The instance of <code>null</code>, if there is no instance for
	 * the given value.
	 */
	public static CreatorType getInstance(final int value) {
		if (PERSON.value == value) {
			return PERSON;
		} else if (COMPANY.value == value) {
			return COMPANY;
		} else if (COLLABORATION.value == value) {
			return COLLABORATION;
		}
		return null;
	}
	
	/**
	 * Returns the value.
	 * @return The value.
	 */
	public long getValue() {
		return this.value;
	}
	
	public int hashCode() {
		int result = 17;
		
		result = 37 * result + this.value;
		return result;
	}
	
	public boolean equals(final Object obj) {
		CreatorType type;
		
		if (obj instanceof CreatorType) {
			type = (CreatorType) obj;
			if (this.value == type.value) {
				return true;
			}
		}
		return false;
	}
}
