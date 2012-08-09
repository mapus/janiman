package org.janiman.gui.list;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import net.anidb.Anime;
import net.anidb.Episode;

public class EpisodeListModel extends AbstractListModel {

	public ArrayList<Episode> listData;
	
	public  EpisodeListModel(ArrayList<Episode> listData)
	{
		this.listData=listData;
	}
	
	@Override
	public int getSize() {
		return listData.size();
	}

	@Override
	public Object getElementAt(int index) {
		Episode toDisplay = listData.get(index);
		return new String("Episode-_-"+toDisplay.getEpisodeNumber() +"-_-"+toDisplay.getEnglishTitle()) ;
	}
}
