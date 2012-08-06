package org.janiman.gui.dialog.open;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.janiman.event.bus.EventBus;
import org.janiman.parser.myanimelist.MALAnime;

public class SelectFolderView extends JPanel {
	private SelectFolderView parent;
	
	private MALAnime selectedAnime;
	private File selectedFolder;
	JButton buttonOpenFolder, buttonSave, buttonBack;
	JLabel labelOpenFolder, labelSelectedFolder;
	
	JFileChooser chooser;
	
	
	ArrayList<PropertyChangeListener> listener = new ArrayList<PropertyChangeListener>();
	
	public void addPropertyChangeListener(PropertyChangeListener pcl)
	{
		listener.add(pcl);
	}
	public void removeropertyChangeListener(PropertyChangeListener pcl)
	{
		listener.remove(pcl);
	}
	private void firePropertyChange(PropertyChangeEvent ev)
	{
		for(PropertyChangeListener ppc : listener)
		{
			ppc.propertyChange(ev);
		}
	}
	
	public SelectFolderView(MALAnime selectedAnime)
	{
		super();
		parent = this;
		this.selectedAnime=selectedAnime;
		initComponents();
		setUp();
		super.revalidate();
	}
	private void initComponents()
	{
		
		buttonOpenFolder = new JButton("Select Folder");
		buttonOpenFolder.setActionCommand("select_folder");
		buttonOpenFolder.addActionListener(new Controller());
		
		buttonSave = new JButton("Save");
		buttonSave.addActionListener(new Controller());
		buttonSave.setActionCommand("save");
		
		buttonBack = new JButton("Back");
		
		labelOpenFolder = new JLabel("Select Homefolder for" + selectedAnime.getTitle());
		labelSelectedFolder = new JLabel();
		
	}
	private void setUp()
	{
		super.setLayout(new GridLayout(0,2));
		super.add(labelOpenFolder);
		super.add(new JPanel());
		super.add(labelSelectedFolder);
		super.add(buttonOpenFolder);
		super.add(buttonBack);
		super.add(buttonSave);
		
		
	}

	
	
	
	class Controller implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("select_folder"))
			{
				int result;
		        
			    chooser = new JFileChooser(); 
			    chooser.setCurrentDirectory(new java.io.File("."));
			    chooser.setDialogTitle("Select HomeFolder");
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    //
			    // disable the "All files" option.
			    //
			    chooser.setAcceptAllFileFilterUsed(false);
			    //    
			    if (chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) { 
			    		selectedFolder=chooser.getSelectedFile();
			    		labelSelectedFolder.setText(selectedFolder.getAbsolutePath());
			    		
			      }
			    else {
			      System.out.println("No Selection ");
			      }
			     }
			if(e.getActionCommand().equals("save"))
				{
					if(selectedFolder!=null)
					{
						firePropertyChange(new PropertyChangeEvent(this,"folder",selectedFolder,selectedFolder));
						EventBus.getInstance().publishEvent("refresh",null);
					}
				}
			}
			
			
		}
		
	}

