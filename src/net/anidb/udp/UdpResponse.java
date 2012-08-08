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
import java.net.DatagramPacket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A response from the server.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 29.11.2009
 */
public class UdpResponse {
	/** The logging. */
	private final static Log LOG = LogFactory.getLog(UdpResponse.class);
	
	/** The return code. */
	private int returnCode;
	/** The return string. */
	private String returnString;
	/** The entries. */
	private Vector<UdpResponseEntry> entries = new Vector<UdpResponseEntry>();
	
	/**
	 * Creates a response.
	 * @param returnCode The return code.
	 * @param returnString The return string.
	 * @throws IllegalArgumentException If the return string is
	 * <code>null</code>.
	 */
	protected UdpResponse(final int returnCode, final String returnString) {
		super();
		this.returnCode = returnCode;
		if (returnString == null) {
			throw new IllegalArgumentException(
				"Argument returnString is null.");
		}
		this.returnString = returnString;
	}
	
	/**
	 * Returns the return code.
	 * @return The return code.
	 */
	public int getReturnCode() {
		return this.returnCode;
	}
	
	/**
	 * Returns the return string.
	 * @return The return string.
	 */
	public String getReturnString() {
		return this.returnString;
	}
	
	/**
	 * Returns a instance of the class.
	 * @param packet The datagram packet.
	 * @param charset The charset.
	 * @return The instance.
	 * @throws IllegalArgumentException If the datagram packet is
	 * <code>null</code>.
	 * @throws UdpConnectionException If the structure of datagram packet isn't
	 * valid.
	 * @throws UnsupportedEncodingException If the given charset isn't
	 * supported.
	 */
	protected static UdpResponse getInstance(final DatagramPacket packet,
		final Charset charset) throws UdpConnectionException,
		UnsupportedEncodingException {
		
		ByteBuffer byteBuffer;
		String data, returnCodeStr, returnString;
		int returnCode;
		UdpResponse response;
		int index;
		String[] entries, dataFields;
		UdpResponseEntry entry;
		
		if (packet == null) {
			throw new IllegalArgumentException("Argument packet is null.");
		}
		if (packet.getLength() < 5) {
			throw new UdpConnectionException("The packet length is less than "
				+ "the minimum of 5 bytes: " + packet.getLength());
		}
		byteBuffer = ByteBuffer.allocate(packet.getLength());
		byteBuffer = (ByteBuffer) byteBuffer.put(packet.getData(), 0,
				packet.getLength()).flip();
		data = charset.decode(byteBuffer).toString();
		LOG.debug("received = [" + data + ']');
		if (data.length() < 5) {
			throw new UdpConnectionException("The string length is less than "
				+ "the minimum of 5 characters: " + data);
		}
		// First 3 chars = return code
		returnCodeStr = data.substring(0, 3);
		data = data.substring(4);
		try {
			returnCode = Integer.parseInt(returnCodeStr);
		} catch (NumberFormatException nfe) {
			throw new UdpConnectionException("Couldn't parse return code: "
				+ returnCodeStr, nfe);
		}
		// Return string.
		index = data.indexOf('\n');
		if (index < 0) {
			returnString = data;
			data = "";
		} else {
			returnString = data.substring(0, index);
			data = data.substring(index + 1);
		}
		response = new UdpResponse(returnCode, returnString);
		// Entries?
		if (data.length() > 0) {
			entries = data.split("\\n", -1);
			for (int eIndex = 0; eIndex < entries.length; eIndex++) {
				if ((entries[eIndex].length() == 0)
						&& (eIndex == entries.length - 1)) {
					break;
				}
				entry = new UdpResponseEntry();
				dataFields = entries[eIndex].split("\\|", -1);
				for (int dfIndex = 0; dfIndex < dataFields.length; dfIndex++) {
					entry.addDataField(new DataField(
							dataFields[dfIndex], charset));
				}
				response.entries.addElement(entry);
			}
			// MTU size problem.
			// TODO Implement a better solution for the MTU problem.
			if ((response.entries.size() > 0) && (packet.getLength() == 1400)) {
				/*
				 * I assume if the packet length is equal to the MTU size, the
				 * last entry is cut and must be removed.
				 */
				index = response.entries.size() - 1;
//				entry = response.entries.elementAt(index);
//				data = entry.getMessageString();
//				LOG.debug("last line = [" + data + ']');
				response.entries.removeElementAt(index);
			}
		}
		return response;
	}
	
	/**
	 * Returns the count of the entries.
	 * @return The count.
	 */
	public int getEntryCount() {
		return this.entries.size();
	}
	
	/**
	 * Returns the entry on the given index.
	 * @param index The index.
	 * @return The entry.
	 * @throws IndexOutOfBoundsException If the index is out of bounds.
	 */
	public UdpResponseEntry getEntryAt(final int index) {
		if ((index < 0) || (index >= this.entries.size())) {
			throw new IndexOutOfBoundsException("Index is out of bounds. Lower "
					+ "limit: 0; upper limit: " + (this.entries.size() - 1)
					+ "; index: " + index);
		}
		return this.entries.elementAt(index);
	}
	
	/**
	 * Returns the entries as an message string.
	 * @return The message string or <code>null</code>, if this response hasn't
	 * any entries.
	 */
	public String getMessageString() {
		StringBuffer messageString;
		Iterator<UdpResponseEntry> iterator;
		UdpResponseEntry entry = null;
		
		if (this.entries.size() == 0) {
			return null;
		}
		messageString = new StringBuffer();
		iterator = this.entries.iterator();
		while (iterator.hasNext()) {
			if (entry != null) {
				messageString.append('\n');
			}
			entry = iterator.next();
			messageString.append(entry.getMessageString());
		}
		return messageString.toString();
	}
	
	/**
	 * Returns the entries as an message string as one line wihout line feeds.
	 * @return The message string or <code>null</code>, if this response hasn't
	 * any entries.
	 */
	public String getTrimedMessageString() {
		StringBuffer messageString;
		Iterator<UdpResponseEntry> iterator;
		UdpResponseEntry entry = null;
		
		if (this.entries.size() == 0) {
			return null;
		}
		messageString = new StringBuffer();
		iterator = this.entries.iterator();
		while (iterator.hasNext()) {
			if (entry != null) {
				messageString.append(' ');
			}
			entry = iterator.next();
			messageString.append(entry.getTrimedMessageString());
		}
		return messageString.toString().trim();
	}
	
	public String toString() {
		StringBuffer sb;
		String entries;
		
		sb = new StringBuffer();
		sb.append(this.returnCode);
		sb.append(' ');
		sb.append(this.returnString);
		entries = this.getMessageString();
		if (entries != null) {
			sb.append('\n');
			sb.append(entries);
		}
		return sb.toString();
	}
}
