package org.janiman.parser.myanimelist;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


public class TestMALApi {

	public static void main(String[] argv)
	{
		try {

			
			Authenticator.setDefault(new MALAuthenticator());
			
			XMLReader myReader = XMLReaderFactory.createXMLReader();
			URL url = new URL("http://myanimelist.net/api/anime/search.xml?q=bleach");
			MALAnimeContentHandler handler = new MALAnimeContentHandler();
			myReader.setContentHandler(handler);
			myReader.parse(new InputSource(url.openStream()));
			
			System.out.println("Finished");
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
