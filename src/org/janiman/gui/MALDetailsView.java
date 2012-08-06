package org.janiman.gui;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.janiman.event.bus.EventBus;
import org.janiman.event.bus.EventSubscriber;
import org.janiman.parser.myanimelist.MALAnime;
// Penispenis123
public class MALDetailsView extends JPanel implements EventSubscriber {
	
	JLabel labelTitle;
	MALAnime anime;
	EventBus bus;
	
	public MALDetailsView()
	{
		super();
		bus = EventBus.getInstance();
		bus.subscribe(this,"site_list_changed");
		
	}

	@Override
	public void eventPerformed(String key, Object o) {
		if(key.equals("site_list_changed"))
		{
			anime=(MALAnime) o;
			URL url;
			try {
				url = new URL(anime.getImage_url());
				java.awt.Toolkit.getDefaultToolkit();
				Image image = Toolkit.getDefaultToolkit().createImage(url);
				super.add(new JLabel(new ImageIcon(image)));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
	}
	

}
