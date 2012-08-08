
package org.janiman.gui.list;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import net.anidb.Anime;

import org.janiman.parser.myanimelist.MALAnime;

public class AnimeListModel extends AbstractListModel {
	
	public ArrayList<Anime> listData;
	
	public AnimeListModel(ArrayList<Anime> listData)
	{
		this.listData=listData;
	}
	
	@Override
	public int getSize() {
		return listData.size();
	}

	@Override
	public Object getElementAt(int index) {
		return listData.get(index).getRomajiName();
	}

}
