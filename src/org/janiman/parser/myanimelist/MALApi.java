package org.janiman.parser.myanimelist;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.janiman.gui.dialog.usersettings.MALUser;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


public class MALApi {
	
	private static MALApi instance = new MALApi();
	
	public static MALApi getInstance()
	{
		return instance;
	}
	
	public ArrayList<MALAnime> findMALAnime(String searchName)
	{
		ArrayList<MALAnime> result=null;
		Authenticator.setDefault(new MALAuthenticator());
		MALAnimeContentHandler handler = new MALAnimeContentHandler();
		
		XMLReader myReader;
		

		
		
		try {
			myReader = XMLReaderFactory.createXMLReader();
			URL url = new URL("http://mal-api.com/anime/search?q="+searchName+"&format=xml");
			InputSource is = new InputSource(url.openStream());
			is.setEncoding("utf-8");
			/*
			String preEscape = convertStreamToString(is.getByteStream());
			String pastInput=StringEscapeUtils.unescapeXml(preEscape);
			is= new InputSource(new StringReader(pastInput));
			*/
			myReader.setEntityResolver(new MALEntityResolver());
			myReader.setContentHandler(handler);
			//myReader.setEntityResolver(resolver);


				myReader.parse(is);
		}
			 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			result=handler.getResults();

		return result;
	}
	
	String convertStreamToString(InputStream is) {

		String theString = null;
		try {
			StringWriter writer = new StringWriter();
			IOUtils.copy(is, writer, "utf-8");
			theString = writer.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theString;

	}
	
	public int verifyCredentials(String username,String password)
	{
		Authenticator.setDefault(new MALAuthenticator(username,password));
		XMLReader myReader;
		MALUser user=null;
		try {
			myReader=XMLReaderFactory.createXMLReader();
			URL url = new URL("http://myanimelist.net/api/account/verify_credentials.xml");
			MALUserContentHandler handler = new MALUserContentHandler();
			myReader.setContentHandler(handler);
			myReader.parse(new InputSource(url.openStream()));
			
			user=handler.getUser();
			
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user!=null)
			return user.getMALid();
		else
			return -1;
		
	}

}
