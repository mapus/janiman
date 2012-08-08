package org.janiman.gui.main;

import java.awt.BorderLayout;

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
import org.janiman.gui.list.ListView;


public class MainView extends JFrame {
	
	MainMenuBar menuBar;
	ListView listview;
	
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
		listview = new ListView();
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
		panelMain.setLayout(new BorderLayout());
		panelMain.add(listview,BorderLayout.WEST);
		panelMain.add(new AnimeDetailsView(),BorderLayout.CENTER);
		
		JPanel panelRight = new JPanel();
		panelMain.add(panelRight,BorderLayout.EAST);
		
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
