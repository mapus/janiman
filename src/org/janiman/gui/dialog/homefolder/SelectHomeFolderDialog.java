package org.janiman.gui.dialog.homefolder;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.janiman.db.impl.DBMapper;
import org.janiman.event.bus.EventBus;

import net.anidb.Anime;

public class SelectHomeFolderDialog extends JDialog {
	private SelectHomeFolderDialog self;
	private File selectedFolder;
	JButton buttonOpenFolder, buttonSave;
	JLabel labelOpenFolder, labelSelectedFolder;
	private Anime anime;
	private Controller controller;
	private JFileChooser chooser;
	
	public SelectHomeFolderDialog(Anime anime)
	{
		this.anime=anime;
		initComponents();
		self = this;
		setUp();
	}
	private void initComponents()
	{
		controller = new Controller();
		
		
		buttonOpenFolder = new JButton("Select Folder");
		buttonOpenFolder.setActionCommand("select_folder");
		buttonOpenFolder.addActionListener(controller);
		
		buttonSave = new JButton("Save");
		buttonSave.addActionListener(controller);
		buttonSave.setActionCommand("save");
		
		
		labelOpenFolder = new JLabel("Select Homefolder for" + anime.getRomajiName());
		labelSelectedFolder = new JLabel();
	}
	private void setUp()
	{
		super.setLayout(new GridLayout(0,2));
		super.add(labelOpenFolder);
		super.add(new JPanel());
		super.add(labelSelectedFolder);
		super.add(buttonOpenFolder);
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
			    if (chooser.showOpenDialog(self) == JFileChooser.APPROVE_OPTION) { 
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
						String homeFolder = DBMapper.getInstance().fetchHomeFolder(anime.getAnimeId());
						if(homeFolder!=null)
						{
							//TODO homefolderchange;
						}
						else
						{
							DBMapper.getInstance().addHomeFolder(selectedFolder.getAbsolutePath(),anime.getAnimeId());
						}
						EventBus.getInstance().publishEvent("refresh",null);
					}
				}
			}
			
			
		}
		
	}


