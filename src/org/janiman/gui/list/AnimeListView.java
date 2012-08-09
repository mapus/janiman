package org.janiman.gui.list;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.anidb.Anime;

import org.janiman.db.impl.DBMapper;
import org.janiman.event.bus.EventBus;
import org.janiman.event.bus.EventSubscriber;
import org.janiman.gui.dialog.homefolder.SelectHomeFolderDialog;
import org.janiman.parser.myanimelist.MALAnime;

public class AnimeListView extends JPanel implements EventSubscriber {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JList list;
	AnimeListModel model;
	ArrayList<Anime>data;
	EventBus bus = EventBus.getInstance();
	SwingWorker worker;
	ListSelectionModel selectionModel;
	JPopupMenu popupMenu;
	PopupListener popupListener;
	
	
	
	
	public AnimeListView()
	{
		list=new JList();
		loadData();
		super.add(list);
		popupListener= new PopupListener();

		
		bus.subscribe(this,"update_side_list");
		bus.subscribe(this,"refresh");
		list.addListSelectionListener(new Controller());
		setUpPopupMenu();
		

	}
	private void setUpPopupMenu()
	{
		popupMenu = new JPopupMenu();
		JMenuItem menuItemHomeFolder = new JMenuItem("Select Home Folder");
		menuItemHomeFolder.setActionCommand("select_home_folder");
		menuItemHomeFolder.addActionListener(new Controller());
		popupMenu.add(menuItemHomeFolder);
		
		list.addMouseListener(popupListener);
	}
	private void loadData()
	{
		worker = new SwingWorker(){

			@Override
			protected Object doInBackground() throws Exception {
				data=DBMapper.getInstance().fetchOwnADBAnime();
				model=new AnimeListModel(data);
				list.setModel(model);

				
				
				return null;
			}
			

		};
		worker.execute();

	}
	@Override
	public void eventPerformed(String key, Object o) {
		if(key.equals("update_side_list"))
		{
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
				Anime selected=data.get(list.getSelectedIndex());
				bus.publishEvent("site_list_changed",selected);
				bus.publishEvent("anime_selection_changed",selected);
			}

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("select_home_folder"))
			{
				Anime selected = data.get(list.getSelectedIndex());
				if(selected!=null)
				{
					SelectHomeFolderDialog homdialog = new SelectHomeFolderDialog(selected);
					homdialog.setVisible(true);
					homdialog.pack();
				}
			}
			
		}
		
	}
	class PopupListener extends MouseAdapter
	{
		
	    public void mousePressed(MouseEvent e) {
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
