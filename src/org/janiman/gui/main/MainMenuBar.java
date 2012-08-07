package org.janiman.gui.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingWorker;

import org.janiman.gui.dialog.open.AddAnimeDialog;
import org.janiman.gui.dialog.usersettings.AnidbUserSettingsDialog;

public class MainMenuBar extends JMenuBar {
	
	JMenu menuFile;
	JMenu menuEdit;
	
	JMenuItem itemAddAnime;
	JMenuItem itemSettings;
	
	
	Controller cont;
	SwingWorker worker;
	JFrame parent;
	
	public MainMenuBar(JFrame parent)
	{
		super();
		this.parent=parent;
		cont = new Controller();
		initComponents();
		setUp();
	}
	private void initComponents()
	{
		menuFile=new JMenu("File");
		menuEdit=new JMenu("Edit");
		
		itemAddAnime=new JMenuItem("Add Anime");
		itemAddAnime.addActionListener(cont);
		itemAddAnime.setActionCommand("addAnime");
		
		
		itemSettings=new JMenuItem("Settings");
		itemSettings.addActionListener(cont);
		itemSettings.setActionCommand("settings");
	}
	private void setUp()
	{
		super.add(menuFile);
		menuFile.add(itemAddAnime);
		
		super.add(menuEdit);
		menuEdit.add(itemSettings);
		
	}
	class Controller implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("settings"))
			{
				AnidbUserSettingsDialog dialog = new AnidbUserSettingsDialog(parent);
				dialog.setVisible(true);
				dialog.pack();
			}
			
			if(e.getActionCommand().equals("addAnime"))
			{
				AddAnimeDialog dialog = new AddAnimeDialog(parent);
				dialog.setVisible(true);
				dialog.setModal(true);
				dialog.pack();
				
			}
			
		}
		
	}

}
