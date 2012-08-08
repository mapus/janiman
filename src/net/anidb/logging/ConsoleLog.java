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
package net.anidb.logging;

/**
 * <p>A logger who writes his output to {@link System#err}.</p>
 * <p>For further information (options, etc.) look at {@link AbstractLog}.</p>
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 11.01.2010
 */
public class ConsoleLog extends AbstractLog {
	/**
	 * Creates a logger.
	 * @param className The class name.
	 */
	public ConsoleLog(final String className) {
		super(className);
	}
	
	protected void printMessage(final String logLevelName, final Object message,
		final Throwable cause) {
		
		System.err.println(this.createMessage(logLevelName, message));
		if (cause != null) {
			cause.printStackTrace(System.err);
		}
	}
}
