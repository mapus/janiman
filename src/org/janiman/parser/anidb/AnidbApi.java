package org.janiman.parser.anidb;

import java.util.ArrayList;
import java.util.List;

import org.janiman.db.impl.DBMapper;
import org.janiman.event.bus.EventBus;
import org.janiman.util.UtilEd2k;
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

	public void hashAndAddFile(java.io.File file) {
		waitTimeout();
		try {
			conn = factory.connect(1025);
			conn.authenticate(user.getUsername(), user.getPassword());
			System.out.println("logged - in");
			String ed2kHash = UtilEd2k.generateEd2kHash(file);
			List<File> erg = conn.getFiles(file.length(), ed2kHash, fileMask,
					animeMask);
			File toInsert = erg.get(0);
			mapper.addADBFile(toInsert);
			mapper.addADBEpisode(toInsert.getEpisode());
			mapper.addFileLoc(file.getAbsolutePath(), toInsert.getEpisode()
					.getAnime().getAnimeId(), ed2kHash, toInsert.getEpisode()
					.getEpisodeId(), toInsert.getFileId());
			bus.publishEvent("hashEvent_success", new String(toInsert
					.getEpisode().getAnime().getRomajiName()));

			waitTimeout();
			Anime a = conn.getAnime(toInsert.getEpisode().getAnime()
					.getAnimeId(), AnimeMask.ALL);
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

	public void hashAndAddFiles(ArrayList<java.io.File> files) {
		waitTimeout();
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
		for (java.io.File file : files) {
			System.out.println("Hashing File" + file.getAbsolutePath());
			String ed2kHash = UtilEd2k.generateEd2kHash(file);
			try {
				List<File> erg = conn.getFiles(file.length(), ed2kHash,
						fileMask, animeMask);
				System.out.println("Adding File" + file.getAbsolutePath());
				File toInsert = erg.get(0);
				mapper.addADBFile(toInsert);
				mapper.addADBEpisode(toInsert.getEpisode());
				mapper.addFileLoc(file.getAbsolutePath(), toInsert.getEpisode()
						.getAnime().getAnimeId(), ed2kHash, toInsert
						.getEpisode().getEpisodeId(), toInsert.getFileId());
				bus.publishEvent("hashEvent_success", new String(toInsert
						.getEpisode().getAnime().getRomajiName()));
				waitTimeout();
				Anime a = conn.getAnime(toInsert.getEpisode().getAnime()
						.getAnimeId(), AnimeMask.ALL);
				mapper.addADBAnime(a);
				mapper.addADBCategory(a);
				waitTimeout();
			} catch (UdpConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AniDbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public long validateUser(String name,String password)
	{
			long userId = -1;
		try {		
		
			conn = factory.connect(1025);
			conn.authenticate(user.getUsername(), user.getPassword());
			if(conn.isLoggedIn())
			{
				userId=conn.getUserId(user.getUsername());
			}
		} catch (UdpConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AniDbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userId;
	}
	private void waitTimeout() {
		if (lastUsedTime != 0) {
			long deltaTime = System.currentTimeMillis() - lastUsedTime;
			if (deltaTime > TIMEOUT) {

			} else {
				try {
					Thread.sleep(TIMEOUT - deltaTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}
	public void reloadUser()
	{
		user = AnidbUserFactory.getInstance().loadUserData();
	}

}
