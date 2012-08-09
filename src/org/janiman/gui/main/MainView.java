package org.janiman.gui.main;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.janiman.db.impl.DBMapper;
import org.janiman.gui.MALDetailsView;
import org.janiman.gui.addFiles.AddFilesView;
import org.janiman.gui.animedetails.AnimeDetailsView;
import org.janiman.gui.list.AnimeListView;
import org.janiman.gui.list.EpisodeListView;


public class MainView extends JFrame {
	
	MainMenuBar menuBar;
	AnimeListView listview;
	
	JTabbedPane tabPane;
	
	
	Controller controller;
	
	
	public MainView()
	{
		super();
		initComponents();
		setUp();
		setUpTab1();
		setUpTab2();
	}
	private void initComponents()
	{
		menuBar = new MainMenuBar(this);
		controller = new Controller();
		listview = new AnimeListView();
		tabPane=new JTabbedPane();
	}
	private void setUp()
	{
		super.setJMenuBar(menuBar);
		
		super.add(tabPane);
		
		
	}
	private void setUpTab1()
	{
		JPanel panelMain = new JPanel();
		JPanel panelLists = new JPanel();
		panelLists.setLayout(new GridLayout(0,1));
		panelLists.add(listview);
		panelLists.add(new EpisodeListView());
		
		panelMain.setLayout(new BorderLayout());
		panelMain.add(panelLists,BorderLayout.WEST);
		
		JPanel panelRight = new JPanel();
		panelRight.add(new AnimeDetailsView());
		panelMain.add(panelRight,BorderLayout.CENTER);
		
		tabPane.addTab("Main",panelMain);
	}
	private void setUpTab2()
	{
		AddFilesView dv = new AddFilesView();
		tabPane.addTab("Pingas",dv);
	}
	
	class Controller
	{
		
	}

}
