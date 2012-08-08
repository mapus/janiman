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
package net.anidb.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.GZIPInputStream;

/**
 * <p>A class for downloading the anime titles file.</p>
 * <p>You can use these anime titles for the seconds level cache. The xml data
 * will be automatically downloaded and decompressed.</p>
 * <p>You should use the methods in the following order:
 * <ul>
 * <li>If you want to save the xml data to a file: {@link #connect()},
 * {@link #store(File)}, {@link #disconnect()}</li>
 * <li>or if you want to read the xml data from qn {@link InputStream}:
 * {@link #connect()}, {@link #getInputStream()}, {@link #disconnect()}.<br>
 * But keep in mind not to call {@link #disconnect()} until you have read all
 * the information you need from the {@link InputStream}, because this call
 * will close the stream.</li>
 * </ul>You can't call {@link #store(File)} and/or {@link #getInputStream()}
 * more than one time on a connected downloader. Before that you have to
 * disconnect and connect the downloader.</p>
 * <p>For further information look at
 * <a href="http://wiki.anidb.info/w/API#Anime_Titles">Anime Titles</a>.</p>
 * @author grizzlyxp
 * (http://anidb.net/perl-bin/animedb.pl?show=userpage&uid=63935)
 * @version <b>1.0</b>, 12.01.2010
 */
public class AnimeTitlesDownloader {
	/** The default address for the file. */
	public final static String ANIME_TITLES_ADDRESS
		= "http://anidb.net/api/animetitles.xml.gz";
	/** The connection timeout. */
	public final static int TIMEOUT = 10000;
	
	/** The URL of the file. */
	private URL url;
	/** The HTTP connection. */
	private HttpURLConnection conn;
	/** The timout. */
	private int timeout;
	/** The input stream. */
	private InputStream in;
	
	/**
	 * Creates a downloader object.
	 * @throws MalformedURLException If the default address specifies an unknown
	 * protocol.
	 * @see #ANIME_TITLES_ADDRESS
	 */
	public AnimeTitlesDownloader() throws MalformedURLException {
		super();
		this.url = new URL(ANIME_TITLES_ADDRESS);
		this.timeout = TIMEOUT;
	}
	
	/**
	 * Creates a downloader object.
	 * @param url The URL of the file.
	 * @throws IllegalArgumentException If the URL is <code>null</code>.
	 * @throws HttpConnectionException If the protocol of the URL isn't
	 * <code>http</code> or a new instance of the URL couldn't be created.
	 */
	public AnimeTitlesDownloader(final URL url) throws HttpConnectionException {
		super();
		if (url == null) {
			throw new IllegalArgumentException("Argument url is null.");
		}
		if (!url.getProtocol().toLowerCase().equals("http")) {
			throw new HttpConnectionException(
					"The protocol of the url isn't http: " + url.getProtocol());
		}
		try {
			this.url = new URL(url, "");
		} catch (MalformedURLException mue) {
			throw new HttpConnectionException(
					"Couldn't create a new instance of the url.", mue);
		}
		this.timeout = TIMEOUT;
	}
	
	/**
	 * Creates a downloader object.
	 * @param address The address of the file.
	 * @throws IllegalArgumentException If the address is <code>null</code>.
	 * @throws MalformedURLException If the address specifies an unknown
	 * protocol.
	 * @throws HttpConnectionException If the protocol of the address isn't
	 * <code>http</code>.
	 */
	public AnimeTitlesDownloader(final String address)
		throws MalformedURLException, HttpConnectionException {
		
		super();
		if (address == null) {
			throw new IllegalArgumentException("Argument address is null.");
		}
		this.url = new URL(address);
		if (!this.url.getProtocol().toLowerCase().equals("http")) {
			throw new HttpConnectionException(
					"The protocol of the address isn't http: "
					+ this.url.getProtocol());
		}
		this.timeout = TIMEOUT;
	}
	
	/**
	 * Connects the downloader to the server.
	 * @throws IllegalStateException If the downloader is already connected.
	 * @throws IOException If an error occured while opening the connection.
	 */
	public void connect() throws IOException {
		if (this.conn != null) {
			throw new IllegalStateException(
					"The downloader is already connected.");
		}
		this.conn = (HttpURLConnection) this.url.openConnection();
	}
	
	/**
	 * Disconnects the downloader from the server.
	 * @throws IllegalStateException If the downloader is already disconnected.
	 */
	public void disconnect() {
		if (this.conn == null) {
			throw new IllegalStateException(
					"The downloader is already disconnected.");
		}
		this.in = null;
		this.conn.disconnect();
		this.conn = null;
	}
	
	/**
	 * Stores the xml data in a file.
	 * @param file The file.
	 * @throws IllegalArgumentException If the file is <code>null</code>.
	 * @throws IllegalStateException If the downloader is already disconnected.
	 * @throws IllegalStateException If the file has already been downloaded.
	 * @throws IOException If an I/O error occured while downloading the file
	 * from the server or while writting it to the filesystem.
	 */
	public void store(final File file) throws IOException {
		BufferedInputStream bufIn = null;
		GZIPInputStream gzipIn = null;
		FileOutputStream fos = null;
		BufferedOutputStream bufOut = null;
		byte[] buffer = new byte[65536];
		int len;
		
		if (file == null) {
			throw new IllegalArgumentException("Argument file is null.");
		}
		if (this.conn == null) {
			throw new IllegalStateException(
					"The downloader is already disconnected.");
		}
		if (this.in != null) {
			throw new IllegalStateException(
					"The file has already been downloaded.");
		}
		this.conn.setReadTimeout(this.timeout);
		// Open file on server.
		this.in = this.conn.getInputStream();
		try {
			// Open file on filesystem.
			fos = new FileOutputStream(file);
			// Copy data.
			bufIn = new BufferedInputStream(this.in);
			gzipIn = new GZIPInputStream(bufIn);
			bufOut = new BufferedOutputStream(fos);
			do {
				len = gzipIn.read(buffer);
				if (len > 0) {
					bufOut.write(buffer, 0, len);
				}
			} while (len > 0);
			// Close all stream except the server stream.
			bufOut.close();
			fos.close();
			gzipIn.close();
			bufIn.close();
		} finally {
			if (bufOut != null) {
				try {
					bufOut.close();
				} catch (IOException ioe) {}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException ioe) {}
			}
		}
	}
	
	/**
	 * Returns an {@link InputStream} to the xml data.
	 * @return The {@link InputStream}.
	 * @throws IllegalStateException If the downloader is already disconnected.
	 * @throws IllegalStateException If the file has already been downloaded.
	 * @throws IOException If an I/O error occured while opening the stream to
	 * the file on the server.
	 */
	public InputStream getInputStream() throws IOException {
		if (this.conn == null) {
			throw new IllegalStateException(
					"The downloader is already disconnected.");
		}
		if (this.in != null) {
			throw new IllegalStateException(
					"The file has already been downloaded.");
		}
		// Open file on server.
		this.in = this.conn.getInputStream();
		return this.in;
	}
}
