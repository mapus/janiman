package org.janiman.gui.dialog.open;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import org.janiman.parser.myanimelist.MALAnime;
import org.janiman.parser.myanimelist.MALApi;

public class MALAnimeTable extends JTable {
	
	ArrayList<MALAnime> list;
	MALAnimeTableModel model;
	
	public MALAnimeTable(ArrayList<MALAnime> list)
	{
		model=new MALAnimeTableModel(list);
		super.setModel(model);
		
		this.list=list;				
	}
	public void setData(ArrayList<MALAnime> list)
	{
		super.setModel(new MALAnimeTableModel(list));
		super.repaint();
	}
	
	

	

}
