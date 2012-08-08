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

import java.util.Iterator;
import java.util.Vector;

/**
 * An entry of a response.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 29.11.2009
 */
public class UdpResponseEntry {
	/** The data fields. */
	private Vector<DataField> dataFields = new Vector<DataField>();
	
	/**
	 * Creates an entry.
	 */
	protected UdpResponseEntry() {
		super();
	}
	
	/**
	 * Adds a data field to the entry.
	 * @param dataField The data field.
	 * @throws IllegalArgumentException If the data field is null.
	 */
	protected void addDataField(final DataField dataField) {
		if (dataField == null) {
			throw new IllegalArgumentException("Argument dataField is null.");
		}
		this.dataFields.addElement(dataField);
	}
	
	/**
	 * Returns the count the data fields.
	 * @return The count.
	 */
	public int getDataFieldCount() {
		return this.dataFields.size();
	}
	
	/**
	 * Returns the data field on the given index.
	 * @param index The index.
	 * @return The data field.
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
	
	/**
	 * Returns the data fields as an message string.
	 * @return The message string or <code>null</code>, if this entry hasn't
	 * any data fields.
	 */
	public String getMessageString() {
		StringBuffer messageString;
		Iterator<DataField> iterator;
		DataField dataField = null;
		
		if (this.dataFields.size() == 0) {
			return null;
		}
		messageString = new StringBuffer();
		iterator = this.dataFields.iterator();
		while (iterator.hasNext()) {
			if (dataField != null) {
				messageString.append('|');
			}
			dataField = iterator.next();
			messageString.append(dataField.getValue());
		}
		return messageString.toString();
	}
	
	/**
	 * Returns the data fields as an message string without pipes.
	 * @return The message string or <code>null</code>, if this entry hasn't
	 * any data fields.
	 */
	public String getTrimedMessageString() {
		StringBuffer messageString;
		Iterator<DataField> iterator;
		DataField dataField = null;
		
		if (this.dataFields.size() == 0) {
			return null;
		}
		messageString = new StringBuffer();
		iterator = this.dataFields.iterator();
		while (iterator.hasNext()) {
			if (dataField != null) {
				messageString.append(' ');
			}
			dataField = iterator.next();
			messageString.append(dataField.getValue().trim());
		}
		return messageString.toString().trim();
	}
}
