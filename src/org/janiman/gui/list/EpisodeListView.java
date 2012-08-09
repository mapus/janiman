package org.janiman.gui.list;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.anidb.Anime;
import net.anidb.Episode;

import org.janiman.db.impl.DBMapper;
import org.janiman.event.bus.EventBus;
import org.janiman.event.bus.EventSubscriber;
import org.janiman.gui.dialog.homefolder.SelectHomeFolderDialog;
import org.janiman.gui.list.AnimeListView.Controller;
import org.janiman.gui.list.AnimeListView.PopupListener;

public class EpisodeListView extends JPanel implements EventSubscriber {

	JList list;
	EpisodeListModel model;
	ArrayList<Episode>data;
	EventBus bus = EventBus.getInstance();
	SwingWorker worker;
	ListSelectionModel selectionModel;
	JPopupMenu popupMenu;
	PopupListener popupListener;
	Episode selectedEpisode;
	
	Anime currentanime;
	
	
	
	
	public EpisodeListView()
	{
		list=new JList();
		loadData();
		super.add(list);


		popupListener = new PopupListener();
		bus.subscribe(this,"anime_selection_changed");
		bus.subscribe(this,"refresh");
		list.addListSelectionListener(new Controller());
		setUpPopupMenu();
		

	}

	private void loadData()
	{
		worker = new SwingWorker(){

			@Override
			protected Object doInBackground() throws Exception {
				data=DBMapper.getInstance().fetchEpisodes(currentanime.getAnimeId());
				Collections.sort(data);
				model=new EpisodeListModel(data);
				list.setModel(model);

				
				
				return null;
			}
			

		};
		worker.execute();

	}
	private void setUpPopupMenu()
	{
		popupMenu = new JPopupMenu();
		JMenuItem menuItemHomeFolder = new JMenuItem("Open File");
		menuItemHomeFolder.setActionCommand("openFile");
		menuItemHomeFolder.addActionListener(new Controller());
		popupMenu.add(menuItemHomeFolder);
		
		list.addMouseListener(popupListener);
	}
	@Override
	public void eventPerformed(String key, Object o) {
		if(key.equals("anime_selection_changed"))
		{
			currentanime=(Anime) o;
			loadData();
		}
		if(key.equals("refresh"))
		{
			loadData();
		}
		
	}
	
	class Controller implements ListSelectionListener, ActionListener
	{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(!e.getValueIsAdjusting())
			{
				System.out.println("lol-klick");
				System.out.println(e.getFirstIndex());
				selectedEpisode =data.get(list.getSelectedIndex());
			}

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("openFile"))
			{
				String fileToOpen;
				fileToOpen=DBMapper.getInstance().fetchAbsFilePath(selectedEpisode.getEpisodeId());
				try {
					Desktop.getDesktop().open(new File(fileToOpen));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}


		
	}
	class PopupListener extends MouseAdapter
	{
		
	    public void mousePressed(MouseEvent e) {
	    	System.out.println("rightklick");
	        maybeShowPopup(e);
	    }

	    public void mouseReleased(MouseEvent e) {
	        maybeShowPopup(e);
	    }
		private void maybeShowPopup(MouseEvent e) {
	        if (e.isPopupTrigger()) {
	        	popupMenu.show(e.getComponent(),
	                       e.getX(), e.getY());
	        }
	    }
	}
	

}
