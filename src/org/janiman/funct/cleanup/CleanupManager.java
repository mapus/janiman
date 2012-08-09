package org.janiman.funct.cleanup;

import java.util.ArrayList;

import net.anidb.Anime;

import org.janiman.db.impl.DBMapper;

public class CleanupManager extends Thread {
	
	public static CleanupManager instance = new CleanupManager();
	DBMapper mapper = DBMapper.getInstance();
	ArrayList<Anime> allAnime = new ArrayList<Anime>();
	
	private CleanupManager()
	{
		super();
		allAnime = mapper.fetchOwnADBAnime();
	}
	
	public void loadData()
	{
		
		allAnime=mapper.fetchOwnADBAnime();
	}
	@Override
	public void run()
	{
		for(Anime anime : allAnime)
		{
			String homefolder = mapper.fetchHomeFolder(anime.getAnimeId());
			if(homefolder != null)
			{
				//TODO
			}
		}
	}

}
