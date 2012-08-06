package org.janiman.parser.anidb;

import java.util.List;

import org.janiman.db.impl.DBMapper;
import org.janiman.event.bus.EventBus;
import org.janiman.util.UtilEd2k;

import net.anidb.Anime;
import net.anidb.File;
import net.anidb.udp.AniDbException;
import net.anidb.udp.UdpConnection;
import net.anidb.udp.UdpConnectionException;
import net.anidb.udp.UdpConnectionFactory;
import net.anidb.udp.mask.AnimeFileMask;
import net.anidb.udp.mask.AnimeMask;
import net.anidb.udp.mask.FileMask;

public class AnidbApi {
	private static int TIMEOUT = 3000;
	private static AnidbApi instance = new AnidbApi();
	private long lastUsedTime=0;
	private EventBus bus = EventBus.getInstance();
	private DBMapper mapper = DBMapper.getInstance();
	private AnidbUser user;
	
	UdpConnectionFactory factory;
    UdpConnection conn = null;
    FileMask fileMask = FileMask.ALL;
    AnimeFileMask animeMask = AnimeFileMask.ALL;
	
	private AnidbApi()
	{
		factory = UdpConnectionFactory.getInstance();
		//Todo init user
		user=new AnidbUser();
		//Todo load user from data
		user.setUsername("username_here");
		user.setPassword("password_here");
	}
	public static AnidbApi getInstance()
	{
		return instance;
	}
	public void hashAndAddFile(java.io.File file)
	{
		waitTimeout();
		try {
			conn = factory.connect(1025);
			conn.authenticate(user.getUsername(),user.getPassword());
			System.out.println("logged - in");
			List<File> erg =  conn.getFiles(file.length(),UtilEd2k.generateEd2kHash(file),fileMask,animeMask);
			File toInsert = erg.get(0);
			mapper.addADBFile(toInsert);
			mapper.addADBEpisode(toInsert.getEpisode());
			bus.publishEvent("hashEvent_success",new String(toInsert.getEpisode().getAnime().getRomajiName()));
			
			waitTimeout();
     	   Anime a=conn.getAnime(toInsert.getEpisode().getAnime().getAnimeId(),AnimeMask.ALL);
     	   mapper.addADBAnime(a);
     	   mapper.addADBCategory(a);
			
			
		} catch (UdpConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AniDbException e) {
			System.out.println(e.getReturnCode());
			e.printStackTrace();
		}
	}
	
	
	
	
	
	private void waitTimeout()
	{
		if(lastUsedTime!=0)
		{
			long deltaTime= System.currentTimeMillis()-lastUsedTime ;
			if(deltaTime > TIMEOUT)
			{
				
			}
			else
			{
				try {
					Thread.sleep(TIMEOUT-deltaTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
	
	
	

}
