package org.janiman.parser.myanimelist;

import java.io.IOException;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.ext.EntityResolver2;

public class MALEntityResolver implements EntityResolver {

	@Override
	public InputSource resolveEntity(String publicId, String systemId)
			throws SAXException, IOException {
		InputSource source = null;  
		System.out.println("Publicid :" +publicId);
		System.out.println("Systemid :" +systemId);
		return source;
		}
	}




