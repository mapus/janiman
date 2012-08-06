package org.janiman.gui.dialog.open;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import org.janiman.parser.myanimelist.MALApi;

public class TestMALTable {
	
	public static void main(String[] argv)
	{
		JFrame frame = new JFrame();
		TableModel model= new MALAnimeTableModel(MALApi.getInstance().findMALAnime("Bleach"));
		
		JTable table = new JTable();
		table.setModel(model);
		frame.add(table);
		System.out.println("lol");
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
