package org.janiman.gui.animedetails;

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

import org.janiman.event.bus.EventBus;
import org.janiman.event.bus.EventSubscriber;

import net.anidb.Anime;

public class AnimeDetailsView extends JPanel {
	
	Controller controller;
	Anime currentAnime;
	EventBus bus;
	AnimeDetailsView self;
	
	
	public AnimeDetailsView()
	{
		super();
		
		initComponents();
		setUp();
	}
	
	private void initComponents()
	{
		self=this;
		controller=new Controller();
		bus = EventBus.getInstance();
		bus.subscribe(controller, "site_list_changed");
	}
	private void setUp()
	{
		super.setLayout(new GridLayout(1,0));
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
				self.add(new JLabel(new ImageIcon(getImage())));
				self.revalidate();
			}
			
		}
		
	}
}
