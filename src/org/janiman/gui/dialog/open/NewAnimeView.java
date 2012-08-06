package org.janiman.gui.dialog.open;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class NewAnimeView extends JPanel{
	
	SearchBarView sbrview;
	JButton buttonNext,buttonCancel;
	MALAnimeTable table;
	
	public NewAnimeView(SearchBarView sbr,MALAnimeTable table)
	{
		super();
		sbrview = sbr;
		this.table=table;
		initComponents();
		setUp();
		
	}
	private void initComponents()
	{
		super.setLayout(new FlowLayout());
		buttonCancel=new JButton("Cancel");
		buttonCancel.setActionCommand("cancel");
		buttonNext=new JButton("Next");
		buttonNext.setActionCommand("next");
	}
	private void setUp()
	{
		super.add(sbrview);
		super.add(new JScrollPane(table));
		
		
		JPanel panel = new JPanel();
		panel.add(buttonCancel);
		panel.add(buttonNext);
		super.add(panel);
	}
	public void addActionListener(ActionListener a)
	{
		buttonCancel.addActionListener(a);
		buttonNext.addActionListener(a);
	}
	
	


}
