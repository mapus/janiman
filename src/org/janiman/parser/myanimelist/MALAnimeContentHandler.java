package org.janiman.parser.myanimelist;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class MALAnimeContentHandler implements ContentHandler {
	
	private ArrayList<MALAnime> alleMALAnime = new ArrayList<MALAnime>();
	private String currentValue;
	private MALAnime anime;

	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub

	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		if(localName.equals("anime"))
		{
			anime = new MALAnime();
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		
		if(localName.equals("id"))
		{
			anime.setId(Integer.valueOf(currentValue));
		}
		if(localName.equals("title"))
		{
			anime.setTitle(currentValue);
		}
		if(localName.equals("episodes"))
		{
			anime.setEpisodes(Integer.valueOf(currentValue));
		}
		if(localName.equals("score"))
		{
			anime.setScore(currentValue);
		}
		if(localName.equals("type"))
		{
			anime.setType(currentValue);
		}
		if(localName.equals("status"))
		{
			anime.setStatus(currentValue);
		}
		if(localName.equals("start_date"))
		{
			anime.setStart_date(currentValue);
		}
		if(localName.equals("end_date"))
		{
			anime.setEnd_date(currentValue);
		}
		if(localName.equals("synopsis"))
		{
			anime.setSynopsis(currentValue);
		}
		if(localName.equals("image_url"))
		{
			anime.setImage_url(currentValue);
		}
		if(localName.equals("anime"))
		{
			alleMALAnime.add(anime);
		}
		if(localName.equals("rank"))
		{
			anime.setRank(currentValue);
		}
		if(localName.equals("popularity_rank"))
		{
			anime.setPopularity_rank(currentValue);
		}
		if(localName.equals("classification"))
		{
			anime.setClassification(currentValue);
		}
		if(localName.equals("members_score"))
		{
			anime.setMembers_score(currentValue);
		}
		if(localName.equals("members_count"))
		{
			try{
			anime.setMembers_count(Integer.valueOf(currentValue));
				}
			catch(NumberFormatException nfe)
			{
				anime.setMembers_count(-1);
			}
		}
		if(localName.equals("favorited_count"))
		{
			anime.setFavorited_count(currentValue);
		}
		if(localName.equals("listed_anime_id"))
		{
			
			try{
				
			anime.setListed_anime_id(Integer.valueOf(currentValue));
			}
			catch(NumberFormatException nfe)
			{
				anime.setListed_anime_id(-1);
			}
		}
	
		
		if(localName.equals("watched_episodes"))
		{
			try{
			anime.setWatched_episodes(Integer.valueOf(currentValue));
			}
			catch(NumberFormatException nfe)
			{
				anime.setWatched_episodes(-1);
			}
		}

		

	}
	public ArrayList<MALAnime> getResults()
	{
		return alleMALAnime;
	}


	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		currentValue = new String(ch,start,length);
		System.out.println(currentValue);

	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void processingInstruction(String target, String data)
			throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub

	}

}
