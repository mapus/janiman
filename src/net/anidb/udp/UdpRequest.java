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

/**
 * A request to the server.
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 29.11.2009
 */
public class UdpRequest {
	/** The command. */
	private String command;
	/** The parameters. */
	private Vector<Parameter> parameters = new Vector<Parameter>();
	/** The encoder. */
	private DataEncoder encoder;
	
	/**
	 * Creates a request.
	 * @param command The command.
	 * @throws IllegalArgumentException If the command is <code>null</code>.
	 */
	protected UdpRequest(final String command) {
		super();
		if (command == null) {
			throw new IllegalArgumentException("Argument command is null.");
		}
		this.command = command.toUpperCase();
		this.encoder = DataEncoder.getInstance();
	}
	
	/**
	 * Adds a parameter to the request.
	 * @param name The name of the parameter.
	 * @param value The value of the parameter.
	 * @throws IllegalArgumentException If the name is <code>null</code>.
	 * @throws IllegalArgumentException If the value is <code>null</code>.
	 */
	public void addParameter(final String name, final String value) {
		if (name == null) {
			throw new IllegalArgumentException("Argument name is null.");
		}
		if (value == null) {
			throw new IllegalArgumentException("Argument value is null.");
		}
		this.parameters.addElement(new Parameter(name, value));
	}
	
	/**
	 * Adds a parameter to the request.
	 * @param name The name of the parameter.
	 * @param value The value of the parameter.
	 * @throws IllegalArgumentException If the name is <code>null</code>.
	 */
	public void addParameter(final String name, final boolean value) {
		Parameter param;
		
		if (name == null) {
			throw new IllegalArgumentException("Argument name is null.");
		}
		if (value) {
			param = new Parameter(name, "1");
		} else {
			param = new Parameter(name, "0");
		}
		this.parameters.addElement(param);
	}
	
	/**
	 * Adds a parameter to the request.
	 * @param name The name of the parameter.
	 * @param value The value of the parameter.
	 * @throws IllegalArgumentException If the name is <code>null</code>.
	 */
	public void addParameter(final String name, final long value) {
		if (name == null) {
			throw new IllegalArgumentException("Argument name is null.");
		}
		this.parameters.addElement(new Parameter(name, Long.toString(value)));
	}
	
	private String createCommandString() {
		StringBuffer sb;
		Iterator<Parameter> iterator;
		Parameter param = null;
		
		sb = new StringBuffer();
		sb.append(this.command);
		iterator = this.parameters.iterator();
		while (iterator.hasNext()) {
			if (param == null) {
				sb.append(' ');
			} else {
				sb.append('&');
			}
			param = iterator.next();
			sb.append(param.getName());
			sb.append('=');
			sb.append(this.encoder.encode(param.getValue()));
		}
		return sb.toString();
	}
	
	/**
	 * Creates a datagram packet.
	 * @param charset The charset.
	 * @return The datagram packet.
	 * @throws UnsupportedEncodingException If the given charset isn't
	 * supported.
	 */
	public DatagramPacket createPacket(final Charset charset)
		throws UnsupportedEncodingException {
		
		ByteBuffer byteBuffer;
		byte[] buffer;
		
		byteBuffer = charset.encode(this.createCommandString());
		buffer = byteBuffer.array();
		return (new DatagramPacket(buffer, buffer.length));
	}
	
	public String toString() {
		return this.createCommandString();
	}
}
