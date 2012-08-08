package org.janiman.gui.addFiles;

import java.io.File;

import javax.swing.SwingWorker;

import org.janiman.event.bus.EventBus;
import org.janiman.util.UtilEd2k;

public class MyHashFile {
	private SwingWorker worker;
	private File file;
	private String ed2kHash;
	private boolean done;
	private EventBus bus = EventBus.getInstance();
	
	public MyHashFile(File bfile)
	{
		done=false;
		this.file=bfile;
		
		
		worker=new SwingWorker()
		{

			@Override
			protected Object doInBackground() throws Exception {
				bus.publishEvent("anidbapi_add_message", new String( "Started Hashing File" + file.getAbsolutePath()));
				ed2kHash = UtilEd2k.generateEd2kHash(file);
				bus.publishEvent("anidbapi_add_message", new String( "Finished Hashing File" + file.getAbsolutePath()));
				bus.publishEvent("hash_file_finished", null);
				done=true;
				return null;	
			}
			
		};
		worker.execute();
		
	}
	public boolean isDone()
	{
		return done;
	}
	public File getFile()
	{
		return file;
	}
	public String getEd2kHash()
	{
		return ed2kHash;
	}

}
