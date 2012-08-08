package org.janiman.gui.addFiles;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JProgressBar;

import org.janiman.parser.anidb.AnidbApi;



public class AddFilesThread extends Thread {
	ArrayList<File> toInsert;
	JProgressBar bar;
	
	public AddFilesThread(ArrayList<File> files,JProgressBar bar)
	{
		super();
		this.toInsert=files;
		this.bar=bar;
	}
	@Override
	public void run()
	{
		bar.setIndeterminate(true);
		AnidbApi.getInstance().hashAndAddFiles(toInsert);
		bar.setIndeterminate(false);
	}


}
