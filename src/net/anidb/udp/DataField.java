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

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * A data field of an entry of a response or a sub data field of a data field.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 29.11.2009
 */
public class DataField {
	/** The value. */
	private String value;
	/** The values. */
	private Vector<String> values = new Vector<String>();
	/** The sub data fields. */
	private Vector<DataField> dataFields = new Vector<DataField>();
	
	/**
	 * Creates a data field.
	 * @param value The value.
	 * @param charset The charset.
	 * @throws IllegalArgumentException If the value is null.
	 * @throws UnsupportedEncodingException If the given charset isn't
	 * supported.
	 */
	protected DataField(final String value, final Charset charset)
		throws UnsupportedEncodingException {
		
		super();
		
		DataDecoder decoder;
		String[] v;
		String vv;
		boolean subFields = false;
		
		if (value == null) {
			throw new IllegalArgumentException("Argument value is null.");
		}
		decoder = DataDecoder.getInstance();
		this.value = decoder.decode(value);
		if (value.indexOf('\'') >= 0) {
			v = value.split("\\'", -1);
			for (int index = 0; index < v.length; index++) {
				vv = v[index];
				this.values.addElement(decoder.decode(vv));
				if (!subFields && (vv.indexOf(',') >= 0)) {
					subFields = true;
				}
			}
			if (subFields) {
				for (int index = 0; index < v.length; index++) {
					vv = v[index];
					this.dataFields.addElement(new DataField(vv, charset));
				}
			}
		} else if (value.indexOf(',') >= 0) {
			v = value.split(",", -1);
			for (int index = 0; index < v.length; index++) {
				this.values.addElement(decoder.decode(v[index]));
			}
		} else {
			this.values.add(this.value);
		}
	}
	
	/**
	 * <p>Returns the value.</p>
	 * <p>If the data field contains more than one value, the values will be
	 * returned as one including the seperator characters.</p>
	 * @return The value.
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
	 * Returns the value as an integer value.
	 * @return The integer value.
	 * @throws DataFieldException If the field does not contain a parsable
	 * integer.
	 */
	public int getValueAsIntegerValue() throws DataFieldException {
		try {
			return Integer.parseInt(this.value);
		} catch (NumberFormatException nfe) {
			throw new DataFieldException(
					"Couldn't parse value into an integer. Value: "
					+ this.value, nfe);
		}
	}
	
	/**
	 * Returns the value as an {@link Integer}.
	 * @return The {@link Integer}.
	 * @throws DataFieldException If the field does not contain a parsable
	 * integer.
	 */
	public Integer getValueAsInteger() throws DataFieldException {
		return Integer.valueOf(this.getValueAsIntegerValue());
	}
	
	/**
	 * Returns the value as a long value.
	 * @return The long value.
	 * @throws DataFieldException If the field does not contain a parsable
	 * long.
	 */
	public long getValueAsLongValue() throws DataFieldException {
		try {
			return Long.parseLong(this.value);
		} catch (NumberFormatException nfe) {
			throw new DataFieldException(
					"Couldn't parse value into a long. Value: " + this.value,
					nfe);
		}
	}
	
	/**
	 * Returns the value as a {@link Long}.
	 * @return The {@link Long}.
	 * @throws DataFieldException If the field does not contain a parsable
	 * long.
	 */
	public Long getValueAsLong() throws DataFieldException {
		return Long.valueOf(this.getValueAsLongValue());
	}
	
	/**
	 * Returns the value as a {@link Boolean}.
	 * @return The {@link Boolean}
	 * @throws DataFieldException If the field does not contain a parsable
	 * long or does not contain one of the following values: <code>0</code> or
	 * <code>1</code>.
	 */
	public Boolean getValueAsBoolean() throws DataFieldException {
		int value;
		
		value = this.getValueAsIntegerValue();
		if (value == 1) {
			return Boolean.TRUE;
		} else if (value == 0) {
			return Boolean.FALSE;
		}
		throw new DataFieldException("Expected values for a boolean value: "
				+ "0 or 1; value: " + value);
	}
	
	/**
	 * <p>Returns the values.</p>
	 * <p>If the data field contains only one value, a list with only one
	 * element will be returned.</p>
	 * @return The values.
	 */
	public List<String> getValues() {
		return (new Vector<String>(this.values));
	}
	
	/**
	 * <p>Returns the values as {@link Integer} objects.</p>
	 * <p>If the data field contains only one value, a list with only one
	 * element will be returned.</p>
	 * @return The values of class {@link Integer}.
	 * @throws DataFieldException If the field does not contain parsable integer
	 * values.
	 */
	public List<Integer> getValuesAsInteger() throws DataFieldException {
		Iterator<String> iterator;
		Vector<Integer> values;
		int count = 0;
		
		if (this.value.length() == 0) {
			return (new Vector<Integer>());
		}
		values = new Vector<Integer>();
		iterator = this.values.iterator();
		while (iterator.hasNext()) {
			count++;
			try {
				values.addElement(Integer.valueOf(Integer.parseInt(
						iterator.next())));
			} catch (NumberFormatException nfe) {
				throw new DataFieldException("Couldn't parse the " + count
						+ " part of the value. Value: " + this.value, nfe);
			}
		}
		return values;
	}
	
	/**
	 * <p>Returns the values as {@link Long} objects.</p>
	 * <p>If the data field contains only one value, a list with only one
	 * element will be returned.</p>
	 * @return The values of class {@link Long}.
	 * @throws DataFieldException If the field does not contain parsable long
	 * values.
	 */
	public List<Long> getValuesAsLong() throws DataFieldException {
		Iterator<String> iterator;
		Vector<Long> values;
		int count = 0;
		
		if (this.value.length() == 0) {
			return (new Vector<Long>());
		}
		values = new Vector<Long>();
		iterator = this.values.iterator();
		while (iterator.hasNext()) {
			count++;
			try {
				values.addElement(Long.valueOf(Long.parseLong(
						iterator.next())));
			} catch (NumberFormatException nfe) {
				throw new DataFieldException("Couldn't parse the " + count
						+ " part of the value. Value: " + this.value, nfe);
			}
		}
		return values;
	}
	
	/**
	 * Returns the status if this data field has sub fields.
	 * @return The state: <code>true</code>, if this data field has sub fields,
	 * otherwise <code>false</code>.
	 */
	public boolean hasDataFields() {
		if (this.dataFields.size() > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the count the sub data fields.
	 * @return The count.
	 */
	public int getDataFieldCount() {
		return this.dataFields.size();
	}
	
	/**
	 * Returns the sub data field on the given index.
	 * @param index The index.
	 * @return The sub data field.
	 * @throws IndexOutOfBoundsException If the index is out of bounds.
	 */
	public DataField getDataFieldAt(final int index) {
		if ((index < 0) || (index >= this.dataFields.size())) {
			throw new IndexOutOfBoundsException("Index is out of bounds. Lower "
					+ "limit: 0; upper limit: " + (this.dataFields.size() - 1)
					+ "; index: " + index);
		}
		return this.dataFields.elementAt(index);
	}
}
