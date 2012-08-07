package org.janiman.db.impl.et;

import net.anidb.Episode;
import net.anidb.File;

public class JAMFile extends File {
	
	public String getFullFilePath() {
		return fullFilePath;
	}

	public void setFullFilePath(String fullFilePath) {
		this.fullFilePath = fullFilePath;
	}

	private String fullFilePath;
	
	public JAMFile()
	{

	}
	
	

}
