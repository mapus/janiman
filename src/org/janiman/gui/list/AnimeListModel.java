
package org.janiman.gui.list;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import org.janiman.parser.myanimelist.MALAnime;

public class AnimeListModel extends AbstractListModel {
	
	public ArrayList<MALAnime> listData;
	
	public AnimeListModel(ArrayList<MALAnime> listData)
	{
		this.listData=listData;
	}
	
	@Override
	public int getSize() {
		return listData.size();
	}

	@Override
	public Object getElementAt(int index) {
		return listData.get(index);
	}

}
