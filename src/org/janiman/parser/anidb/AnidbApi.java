package org.janiman.parser.anidb;

import java.util.ArrayList;
import java.util.List;

import org.janiman.db.impl.DBMapper;
import org.janiman.event.bus.EventBus;
import org.janiman.util.UtilEd2k;
import org.janiman.gui.addFiles.MyHashFile;
import org.janiman.gui.dialog.usersettings.AnidbUser;
import org.janiman.gui.dialog.usersettings.AnidbUserFactory;

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
	private long lastUsedTime = 0;
	private EventBus bus = EventBus.getInstance();
	private DBMapper mapper = DBMapper.getInstance();
	private AnidbUser user;

	UdpConnectionFactory factory;
	UdpConnection conn = null;
	FileMask fileMask = FileMask.ALL;
	AnimeFileMask animeMask = AnimeFileMask.ALL;

	private AnidbApi() {
		factory = UdpConnectionFactory.getInstance();
		// Todo init user
		user = AnidbUserFactory.getInstance().loadUserData();
	}

	public static AnidbApi getInstance() {
		return instance;
	}


	public void hashAndAddFiles(ArrayList<MyHashFile> files) {
		try {
			conn = factory.connect(1025);
			conn.authenticate(user.getUsername(), user.getPassword());
			// Verbindungsaufbau
			
			
		} catch (UdpConnectionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (AniDbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("logged - in");
		
		
		
		for (MyHashFile file : files) {
			//Hashing
			
 
			bus.publishEvent("anidbapi_add_message", new String(
					"Getting File infos"));
			try {
				//check if still logged in
				if(!conn.isLoggedIn())
				{
					conn.authenticate(user.getUsername(), user.getPassword());
				}
				// Revieve File data;
				List<File> erg = conn.getFiles(file.getFile().length(), file.getEd2kHash(),
						fileMask, animeMask);
				System.out.println("Adding File" + file.getFile().getAbsolutePath());
				File toInsert = erg.get(0);

				// Adding file+episodedata to database;
				bus.publishEvent("anidbapi_add_message", new String(
						"Adding File+Episode Infos to Database"));
				
				
				mapper.addADBFile(toInsert);

				mapper.addADBEpisode(toInsert.getEpisode(),
						toInsert.getFileId());

				mapper.addFileLoc(file.getFile().getAbsolutePath(), toInsert.getEpisode()
						.getAnime().getAnimeId(), file.getEd2kHash(), toInsert
						.getEpisode().getEpisodeId(), toInsert.getFileId());
				bus.publishEvent("hashEvent_success", new String(toInsert
						.getEpisode().getAnime().getRomajiName()));


				if (!(mapper.isAlreadyInDatabase(toInsert.getEpisode()
						.getAnime().getAnimeId()))) {
					bus.publishEvent("anidbapi_add_message", new String(
							"Getting Anime Infos"));

					Anime abb = conn.getAnime(toInsert.getEpisode().getAnime().getAnimeId(),AnimeMask.MYMASK);

					bus.publishEvent("anidbapi_add_message", new String(
							"Adding Anime Infos to Database"));
					
					DBMapper.getInstance().addADBAnime(abb);
					mapper.addADBCategory(abb);
					bus.publishEvent("anidbapi_add_message", new String(
							"Writing Anime Infos to Database - Success"));
					
					//Getting Synopsis
					String description = conn.getAnimeDescription(toInsert.getEpisode().getAnime().getAnimeId());
					mapper.addADBDescription(toInsert.getEpisode().getAnime().getAnimeId(), description);

				} }

			 catch (UdpConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AniDbException e) {
				if(e.getReturnCode()==602)
				{
					System.out.println("Server Busy");
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				e.printStackTrace();
			}

		}
		try {
			conn.close();
		} catch (UdpConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AniDbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public long validateUser(String name, String password) {
		long userId = -1;
		try {

			conn = factory.connect(1025);
			conn.authenticate(name, password);
			if (conn.isLoggedIn()) {
				userId = conn.getUserId(name);
			}
		} catch (UdpConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AniDbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();

			} catch (UdpConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AniDbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userId;
	}



	public void reloadUser() {
		user = AnidbUserFactory.getInstance().loadUserData();
	}

}
