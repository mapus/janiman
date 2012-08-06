package org.janiman.gui.dialog.open;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import org.janiman.db.impl.DBMapper;
import org.janiman.parser.myanimelist.MALAnime;
import org.janiman.util.UtilMAL;

public class AddAnimeDialog extends JDialog {
	AddAnimeDialog self;
	JPanel contentPanel;
	NewAnimeController cont;
	Controller controller;
	SelectFolderView folderView;
	SwingWorker worker;
	
	
	public AddAnimeDialog(JFrame parent)
	{
		super(parent,true);
		initComponents();
		setUp();
	}
	private void initComponents()
	{
		self=this;
		contentPanel = new JPanel();
		cont = new NewAnimeController();
		controller = new Controller();
		
		cont.addPropertyChangeListener(controller);
		
		
		
	}
	private void setUp()
	{
		super.add(contentPanel);
		contentPanel.add(cont.getView());
		super.pack();
	}
	
	
	
	
	
	
	
	class Controller implements PropertyChangeListener
	{
		File selectedFolder;
		MALAnime selectedAnime;
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if(evt.getPropertyName()=="MALAnime_selected")
			{
				worker= new SwingWorker(){

					@Override
					protected Object doInBackground() throws Exception {
						System.out.println("propertyChange recived");
						contentPanel.remove(cont.getView());
						selectedAnime=cont.getSelected();
						//Show 2 Dialog;
						folderView = new SelectFolderView(selectedAnime);
						contentPanel.add(folderView);
						folderView.addPropertyChangeListener(controller);
						self.repaint();
						self.pack();
						return null;
					}

					
				};
				worker.execute();

				contentPanel.revalidate();
				self.pack();

			}
			if(evt.getPropertyName()=="folder")
			{
				this.selectedFolder=(File) evt.getNewValue();
				
				System.out.println("Selected Anime: "+ selectedAnime.getTitle());
				System.out.println("Selected Folder :"+ selectedFolder.getAbsolutePath());
				
				DBMapper.getInstance().addFolder(selectedAnime, selectedFolder);
				
				//Downloading image
				try {
					URL website = new URL(UtilMAL.convertTumbnailUrlToUrl(selectedAnime.getImage_url()));
					System.out.println("loading shit from :"+selectedAnime.getImage_url());
				    ReadableByteChannel rbc = Channels.newChannel(website.openStream());
				    FileOutputStream fos = new FileOutputStream("res/"+selectedAnime.getId()+".jpg");
				    fos.getChannel().transferFrom(rbc, 0, 1 << 24);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				self.dispose();
			}
			
		}

}
}