package org.janiman.db.impl.et;

import net.anidb.Episode;

public class JAMEpisode extends Episode {
	private String filePath;
	
	public JAMEpisode()
	{
		super();
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
