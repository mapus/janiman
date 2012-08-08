package org.janiman.gui.dialog.open;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import org.apache.commons.io.FileUtils;
import org.janiman.db.impl.DBMapper;
import org.janiman.parser.myanimelist.MALAnime;
import org.janiman.parser.myanimelist.MALApi;

public class NewAnimeController implements PropertyChangeListener,
		ActionListener {

	SearchBarView searchBar;
	MALAnimeTable tableView;
	String lastSearchValue;
	ArrayList<MALAnime> list;
	NewAnimeView mainView;
	MALAnime selected;

	ArrayList<PropertyChangeListener> listener = new ArrayList<PropertyChangeListener>();

	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		listener.add(pcl);
	}

	public void removeropertyChangeListener(PropertyChangeListener pcl) {
		listener.remove(pcl);
	}

	private void firePropertyChange(PropertyChangeEvent ev) {
		for (PropertyChangeListener ppc : listener) {
			ppc.propertyChange(ev);
		}
	}

	public NewAnimeController() {
		initComponents();
		setUp();
	}

	public NewAnimeView getView() {
		return mainView;
	}

	private void initComponents() {
		searchBar = new SearchBarView();
		list = new ArrayList<MALAnime>();
		tableView = new MALAnimeTable(list);

		mainView = new NewAnimeView(searchBar, tableView);
	}

	public MALAnime getSelected() {
		return selected;
	}

	private void setUp() {
		searchBar.addPropertyChangeListener(this);
		mainView.addActionListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String newSearchValue = (String) evt.getNewValue();
		if (lastSearchValue != newSearchValue) {
			list = MALApi.getInstance()
					.findMALAnime((String) evt.getNewValue());
			System.out.println("searching da web");

			for (MALAnime anime : list) {

				DBMapper.getInstance().addMALAnime(anime);

				System.out.println(anime.getTitle());

			}
			tableView.setData(list);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("cancel")) {
			// TODO
		}
		if (e.getActionCommand().equals("next")) {
			if (tableView.getSelectedRow() >= 0) {
				selected = list.get(tableView.getSelectedRow());
				System.out.println("firePropertyChange");
				firePropertyChange(new PropertyChangeEvent(this,
						"MALAnime_selected", selected.getId(), selected.getId()));
			}
		}

	}

}
