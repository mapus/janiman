package org.janiman.gui.list;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.anidb.Anime;

import org.janiman.db.impl.DBMapper;
import org.janiman.event.bus.EventBus;
import org.janiman.event.bus.EventSubscriber;
import org.janiman.parser.myanimelist.MALAnime;

public class ListView extends JPanel implements EventSubscriber {
	
	
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
	
	
	
	
	public ListView()
	{
		list=new JList();
		loadData();
		super.add(list);
		bus.subscribe(this,"update_side_list");
		bus.subscribe(this,"refresh");
		list.addListSelectionListener(new Controller());
		

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
	
	class Controller implements ListSelectionListener
	{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(!e.getValueIsAdjusting())
			{
				System.out.println("lol-klick");
				System.out.println(e.getFirstIndex());
				Anime selected=data.get(list.getSelectedIndex());
				bus.publishEvent("site_list_changed",selected);
			}

		}
		
	}
	

}
