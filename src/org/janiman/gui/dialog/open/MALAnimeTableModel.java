package org.janiman.gui.dialog.open;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import org.janiman.parser.myanimelist.MALAnime;

public class MALAnimeTableModel  extends AbstractTableModel{
	
	public static final int COL_ID = 0;
	public static final int COL_NAME = 1;
	public static final int COL_EPISODES = 2;
	public static final int COL_TYPE=3;
	
	ArrayList<MALAnime> list;
	
	public MALAnimeTableModel(ArrayList<MALAnime> list)
	{
		this.list=list;
	}
	public void updateList(ArrayList<MALAnime> ulist)
	{
		this.list=ulist;
		super.fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		MALAnime anime = list.get(rowIndex);
		switch(columnIndex){
		
			case(COL_ID): 
				return anime.getId();
			case(COL_NAME):
				return anime.getTitle();
			case(COL_EPISODES):
				return anime.getEpisodes();
			case(COL_TYPE):
				return anime.getType();

		}
		return new String("Vespingas");
	}
}
