package org.janiman.gui.addFiles;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import org.janiman.event.bus.EventBus;
import org.janiman.event.bus.EventSubscriber;
import org.janiman.parser.anidb.AnidbApi;

import net.iharder.dnd.FileDrop;
import net.iharder.dnd.FileDrop.Listener;
import net.iharder.dnd.FileDropEvent;
import net.iharder.dnd.FileDropListener;

public class AddFilesView extends JPanel {
	
	FileDrop dropper;
	SwingWorker worker;
	Controller controller;
	File[] files;
	JButton buttonStart;
	JProgressBar progressBar;
	JProgressBar progressBarHash;
	EventBus bus;
	JLabel labelBus;
	
	
	//TODO - Improve gui and adding System
	
	JList list;
	
	public AddFilesView()
	{
		super();

		initComponents();
		setUp();
	}
	private void initComponents()
	{
		controller = new Controller();
		dropper = new FileDrop(this,controller);
		bus = EventBus.getInstance();
		bus.subscribe(controller,"anidbapi_add_message");
		bus.subscribe(controller, "hash_file_finished");
		
		labelBus = new JLabel();
		list=new JList();
		buttonStart = new JButton("Start");
		buttonStart.setActionCommand("start");
		buttonStart.addActionListener(controller);
		
		progressBar = new JProgressBar();
		progressBarHash=new JProgressBar();

	}
	private void setUp()
	{
		super.setLayout(new BorderLayout());
		super.add(list,BorderLayout.WEST);
		super.add(buttonStart,BorderLayout.EAST);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,1));
		panel.add(progressBar);
		panel.add(progressBarHash);
		super.add(panel,BorderLayout.SOUTH);

		super.add(labelBus,BorderLayout.NORTH);
	}
	
	
	class Controller implements FileDropListener, Listener, ActionListener, EventSubscriber
	{

		@Override
		public void filesDropped(FileDropEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void filesDropped(File[] arg0) {
			list.setListData(arg0);
			files=arg0;
			
		}

		@SuppressWarnings("rawtypes")
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("start"))
			{
				/*
				worker = new SwingWorker()
				{

					@Override
					protected Object doInBackground() throws Exception {
						progressBar.setIndeterminate(true);
						buttonStart.setEnabled(false);
						long timeBefore = System.currentTimeMillis();
						AnidbApi.getInstance().hashAndAddFiles(new ArrayList<File>(Arrays.asList(files)));
						long timeAfter = System.currentTimeMillis();
						
						System.out.println("Duration : "+(timeAfter-timeBefore)/1000);
						progressBar.setIndeterminate(false);
						buttonStart.setEnabled(false);
						
						bus.publishEvent("update_side_list", null);
						return null;
					}
					
				};
				worker.execute();
				*/
				
				
				worker=new SwingWorker()
				{

					@Override
					protected Object doInBackground() throws Exception {
						ArrayList<File> filesToHash=new ArrayList<File>(Arrays.asList(files));
						ArrayList<MyHashFile> hashedFiles=new ArrayList<MyHashFile>();
						progressBar.setIndeterminate(true);
						progressBarHash.setMinimum(0);
						progressBarHash.setMaximum(filesToHash.size());
						buttonStart.setEnabled(false);
						
						
						
						long timeBefore = System.currentTimeMillis();
						
						
						
						for(File file : filesToHash)
						{
							hashedFiles.add(new MyHashFile(file));
						}
						
						while(!allDone(hashedFiles))
						{
							Thread.sleep(1000);
						}
						
						final ArrayList<MyHashFile> toInsert = hashedFiles;
						Thread threadL = new Thread(){
							@Override
							public void run()
							{
								
								AnidbApi.getInstance().hashAndAddFiles(toInsert);
							}
						};
						threadL.setPriority(Thread.MAX_PRIORITY);
						threadL.run();
						
						
						long timeAfter = System.currentTimeMillis();
						System.out.println("Duration : "+(timeAfter-timeBefore)/1000);
						progressBar.setIndeterminate(false);
						buttonStart.setEnabled(true);
						
						bus.publishEvent("update_side_list", null);
						return null;
						
						
					}
					public boolean allDone(ArrayList<MyHashFile> mhf)
					{
						boolean done = true;
						for(MyHashFile myHashFile : mhf)
						{
							if(myHashFile.isDone()==true)
							{
								
							}
							else
							{
								done = false;
							}
							
						}
						return done;
					}
					
				};
				worker.execute();
				
			}
			
		}

		@Override
		public void eventPerformed(String key, Object o) {
			if(key.equals("anidbapi_add_message"))
			{
				String message = (String) o;
				labelBus.setText(message);
			}

			if(key.equals("hash_file_finished"))
			{
				System.out.println("HashFinishedEvent geto");
				int val = progressBarHash.getValue();
				val=val+1;
				System.out.println(val);
				System.out.println(progressBarHash.getMinimum() + "-" +progressBarHash.getMaximum());
				progressBarHash.setValue(val++);
				progressBarHash.setStringPainted(true);
				progressBarHash.setString("Hashed "+val+ "of  "+progressBarHash.getMaximum()+"  Files!");
			}
			
		}
		
	}

}
