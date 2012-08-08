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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.logging.Log;

/**
 * <p>The superclass of all logger classes.</p>
 * <p>The subclass defines were the messages are send to. The following system
 * properties are supported to configure the behavior of the subclasses of this
 * logger:
 * <ul>
 * <li><code>net.anidb.logging.defaultlog</code> - Default logging detail level
 * for all instances of the subclasses of AbstractLog. Must be one of ("trace",
 * "debug", "info", "warn", "error", or "fatal"). If not specified, defaults to
 * "info".</li>
 * <li><code>net.anidb.logging.showlogname</code> - Set to true if you want the
 * Log instance name to be included in output messages. Defaults to
 * <code>false</code>.</li>
 * <li><code>net.anidb.logging.showShortLogname</code> - Set to
 * <code>true</code> if you want the last component of the name to be included
 * in output messages. Defaults to <code>true</code>.</li>
 * <li><code>net.anidb.logging.showdatetime</code> - Set to <code>true</code> if
 * you want the current date and time to be included in output messages. Default
 * is <code>false</code>.</li>
 * <li><code>net.anidb.logging.dateTimeFormat</code> - Default date and time
 * format for all instances of AbstractLog. See {@link SimpleDateFormat} for
 * further information. If not specified, defaults to
 * "yyyy/MM/dd HH:mm:ss:SSS zzz".</li>
 * </ul>
 * </p>
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 11.01.2010
 */
public abstract class AbstractLog implements Log {
	/** The logging level <code>TRACE</code>. */
	private final static int LOG_LEVEL_TRACE = 0;
	/** The logging level <code>DEBUG</code>. */
	private final static int LOG_LEVEL_DEBUG = 1;
	/** The logging level <code>INFO</code>. */
	private final static int LOG_LEVEL_INFO = 2;
	/** The logging level <code>WARN</code>. */
	private final static int LOG_LEVEL_WARN = 3;
	/** The logging level <code>ERROR</code>. */
	private final static int LOG_LEVEL_ERROR = 4;
	/** The logging level <code>FATAL</code>. */
	private final static int LOG_LEVEL_FATAL = 5;
	
	/** The standard format for times. */
	private final static DateFormat STD_DATE_FORMATTER
		= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS zzz");
	
	/** The class name with package name. */
	private String className;
	/** The log level. */
	private int logLevel = LOG_LEVEL_INFO;
	/** The class name without package name. */
	private String shortClassName;
	/** The shown class name in the log message. */
	private String shownClassName;
	/** Include the class name in the log message? */
	private boolean showLogName = false;
	/**
	 * <p>Include the short name (last component) of the class name in the log
	 * message.</p>
	 */
	private boolean showShortLogName = true;
	/** Include the current time in the log message. */
	private boolean showDateTime = false;
	/** Used to format times. */
	private DateFormat dateFormatter = STD_DATE_FORMATTER;
	
	/**
	 * Creates a logger.
	 * @param className The class name.
	 */
	public AbstractLog(final String className) {
		super();
		this.className = className;
		this.initialize();
	}
	
	/**
	 * Initializes the object.
	 */
	private void initialize() {
		int index;
		Properties props;
		String packageName, value;
		
		index = this.className.lastIndexOf('.');
		if (index >= 0) {
			this.shortClassName = this.className.substring(index + 1);
		} else {
			this.shortClassName = this.className;
		}
		props = System.getProperties();
		packageName = this.getClass().getPackage().getName();
		// defaultlog
		value = props.getProperty(packageName + ".defaultlog");
		if (value != null) {
			value = value.toLowerCase();
			if (value.equals("trace")) {
				this.logLevel = LOG_LEVEL_TRACE;
			} else if (value.equals("debug")) {
				this.logLevel = LOG_LEVEL_DEBUG;
			} else if (value.equals("info")) {
				this.logLevel = LOG_LEVEL_INFO;
			} else if (value.equals("warn")) {
				this.logLevel = LOG_LEVEL_WARN;
			} else if (value.equals("error")) {
				this.logLevel = LOG_LEVEL_ERROR;
			} else if (value.equals("fatal")) {
				this.logLevel = LOG_LEVEL_FATAL;
			}
		}
		// showlogname
		value = props.getProperty(packageName + ".showlogname");
		if (value != null) {
			this.showLogName = Boolean.parseBoolean(value);
		}
		// showShortLogname
		value = props.getProperty(packageName + ".showShortLogname");
		if (value != null) {
			this.showShortLogName = Boolean.parseBoolean(value);
		}
		// showdatetime
		value = props.getProperty(packageName + ".showdatetime");
		if (value != null) {
			this.showDateTime = Boolean.parseBoolean(value);
		}
		// dateTimeFormat
		value = props.getProperty(packageName + ".dateTimeFormat");
		if (value != null) {
			this.dateFormatter = new SimpleDateFormat(value);
		}
		// Shown name
		if (this.showLogName) {
			if (this.showShortLogName) {
				this.shownClassName = this.shortClassName;
			} else {
				this.shownClassName = this.className;
			}
		} else {
			this.shownClassName = null;
		}
	}
	
	public boolean isTraceEnabled() {
		return (this.logLevel <= LOG_LEVEL_TRACE);
	}
	
	public boolean isDebugEnabled() {
		return (this.logLevel <= LOG_LEVEL_DEBUG);
	}
	
	public boolean isInfoEnabled() {
		return (this.logLevel <= LOG_LEVEL_INFO);
	}
	
	public boolean isWarnEnabled() {
		return (this.logLevel <= LOG_LEVEL_WARN);
	}
	
	public boolean isErrorEnabled() {
		return (this.logLevel <= LOG_LEVEL_ERROR);
	}
	
	public boolean isFatalEnabled() {
		return (this.logLevel <= LOG_LEVEL_FATAL);
	}
	
	public void trace(final Object message) {
		if (this.logLevel <= LOG_LEVEL_TRACE) {
			this.printMessage("trace", message, null);
		}
	}
	
	public void trace(final Object message, final Throwable cause) {
		if (this.logLevel <= LOG_LEVEL_TRACE) {
			this.printMessage("trace", message, cause);
		}
	}
	
	public void debug(final Object message) {
		if (this.logLevel <= LOG_LEVEL_DEBUG) {
			this.printMessage("debug", message, null);
		}
	}
	
	public void debug(final Object message, final Throwable cause) {
		if (this.logLevel <= LOG_LEVEL_DEBUG) {
			this.printMessage("debug", message, cause);
		}
	}
	
	public void info(final Object message) {
		if (this.logLevel <= LOG_LEVEL_INFO) {
			this.printMessage("info", message, null);
		}
	}
	
	public void info(final Object message, final Throwable cause) {
		if (this.logLevel <= LOG_LEVEL_INFO) {
			this.printMessage("info", message, cause);
		}
	}
	
	public void warn(final Object message) {
		if (this.logLevel <= LOG_LEVEL_WARN) {
			this.printMessage("warn", message, null);
		}
	}
	
	public void warn(final Object message, final Throwable cause) {
		if (this.logLevel <= LOG_LEVEL_WARN) {
			this.printMessage("warn", message, cause);
		}
	}
	
	public void error(final Object message) {
		if (this.logLevel <= LOG_LEVEL_ERROR) {
			this.printMessage("error", message, null);
		}
	}
	
	public void error(final Object message, final Throwable cause) {
		if (this.logLevel <= LOG_LEVEL_ERROR) {
			this.printMessage("error", message, cause);
		}
	}
	
	public void fatal(final Object message) {
		if (this.logLevel <= LOG_LEVEL_FATAL) {
			this.printMessage("fatal", message, null);
		}
	}
	
	public void fatal(final Object message, final Throwable cause) {
		if (this.logLevel <= LOG_LEVEL_FATAL) {
			this.printMessage("fatal", message, cause);
		}
	}
	
	/**
	 * Creates a log message.
	 * @param logLevelName The name of the log level.
	 * @param message The message.
	 * @return The log message.
	 */
	protected String createMessage(final String logLevelName,
		final Object message) {
		
		StringBuffer sb = new StringBuffer();
		if (this.showDateTime) {
			sb.append('[');
			sb.append(this.dateFormatter.format(new Date()));
			sb.append("] ");
		}
		sb.append('[');
		sb.append(logLevelName);
		sb.append("] ");
		if (this.shownClassName != null) {
			sb.append('[');
			sb.append(this.shownClassName);
			sb.append("] ");
		}
		sb.append(message);
		return sb.toString();
	}
	
	/**
	 * Log a message.
	 * @param logLevelName The name of the log level.
	 * @param message The message.
	 * @param cause The cause.
	 */
	protected abstract void printMessage(final String logLevelName,
			final Object message, final Throwable cause);
}
