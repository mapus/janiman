package org.janiman.gui.dialog.episode;

import java.awt.Component;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.janiman.parser.myanimelist.MALAnime;

public class AddEpisodeDialog extends JDialog {
	
	Controller controller;
	MALAnime anime;
	JFileChooser fileChooser;
	
	ArrayList<File> episodeFiles;
	

	public AddEpisodeDialog(JFrame parent,MALAnime anime)
	{
		super(parent,true);
		this.anime=anime;
		
		
		episodeFiles = new ArrayList<File>();
		
		
		initComponents();
	}
	private void initComponents()
	{
		controller = new Controller();
	}
	
	
	
	
	
	
	
	class Controller
	{
		
	}
}
