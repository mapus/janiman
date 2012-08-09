package org.janiman.gui.animedetails;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import org.janiman.db.impl.DBMapper;
import org.janiman.event.bus.EventBus;
import org.janiman.event.bus.EventSubscriber;

import net.anidb.Anime;

public class AnimeDetailsView extends JPanel {
	
	Controller controller;
	Anime currentAnime;
	EventBus bus;
	AnimeDetailsView self;
	JLabel labelImage;
	JTextArea areaDescription;
	JLabel labelTitle;
	SwingWorker worker;
	
	
	public AnimeDetailsView()
	{
		super();
		
		initComponents();
		setUp();
	}
	
	private void initComponents()
	{
		self=this;
		labelImage=new JLabel();
		
		areaDescription = new JTextArea();
		areaDescription.setLineWrap(true);
		areaDescription.setWrapStyleWord(true);
		

		labelTitle = new JLabel();
		controller=new Controller();
		bus = EventBus.getInstance();
		bus.subscribe(controller, "site_list_changed");
		super.setMaximumSize(new Dimension(500,500));
	}
	private void setUp()
	{
		super.setLayout(new BorderLayout());
		super.add(labelTitle,BorderLayout.NORTH);
		super.add(labelImage,BorderLayout.CENTER);
		super.add(areaDescription,BorderLayout.SOUTH);
	}
	
	class Controller implements EventSubscriber
	{
			
		public BufferedImage getImage()
		{
			BufferedImage image= null;
			System.out.println("getting IMAGE");
			if(!(new File("res/img/"+currentAnime.getPicname()).exists()))
			{
				System.out.println("downloading image");
				try {
					URL url = new URL("http://img7.anidb.net/pics/anime/"+currentAnime.getPicname());
					image=ImageIO.read(url);
					ImageIO.write(image, "jpg",new File("res/img/"+ currentAnime.getPicname()));

				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else
			{
				try {
					image= ImageIO.read(new File("res/img/"+ currentAnime.getPicname()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return image;
			
		}

		@Override
		public void eventPerformed(String key, Object o) {
			if(key.equals("site_list_changed"))
			{
				currentAnime = (Anime) o;
				System.out.println(currentAnime.getRomajiName());
				
				labelTitle.setText(currentAnime.getRomajiName());
				ImageIcon image =new ImageIcon(getImage());
				labelImage.setIcon(image);
				updateDescription(currentAnime.getAnimeId());
			}
			
		}
		private void updateDescription(final long animeId)
		{
			worker = new SwingWorker()
			{

				@Override
				protected Object doInBackground() throws Exception {
					String description = DBMapper.getInstance().fetchADBDescription(animeId);
					//TODO escape [URL] Tags to standard html tags;
					areaDescription.setText(description);
					self.revalidate();
					
					return null;
				}
				
			};
			worker.execute();
		}
		
	}
}
