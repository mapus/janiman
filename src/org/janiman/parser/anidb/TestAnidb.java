package org.janiman.parser.anidb;


import java.util.List;

import org.janiman.db.impl.DBMapper;

import net.anidb.Anime;
import net.anidb.File;
import net.anidb.udp.UdpConnection;
import net.anidb.udp.UdpConnectionFactory;
import net.anidb.udp.mask.AnimeFileMask;
import net.anidb.udp.mask.AnimeMask;
import net.anidb.udp.mask.FileMask;

public class TestAnidb {

	//|file|[gg]_EUREKA_SEVEN_AO_-_13_[3A595BE2].mkv|410710518|e9b7367757613a826797bd240B457eec|/

	public static void main(String[] argv)
	{
		UdpConnectionFactory factory;
        UdpConnection conn = null;
        FileMask fileMask = FileMask.ALL;
        AnimeFileMask animeMask = AnimeFileMask.ALL;

        long size = 410710518;
       
        
        factory = UdpConnectionFactory.getInstance();
        try {
            conn = factory.connect(1025);
            conn.authenticate("gramaz","441139");
           List<File> erg =  conn.getFiles(size, "e9b7367757613a826797bd240B457eec", fileMask, animeMask);
           DBMapper mapper = DBMapper.getInstance();
           File blub = erg.get(0);

        	   mapper.addADBFile(blub);
        	   mapper.addFileLoc("E:/Anime-Watching/[gg]_EUREKA_SEVEN_AO_-_13_[3A595BE2].mkv", blub.getEpisode().getAnime().getAnimeId(), "e9b7367757613a826797bd240B457eec", blub.getEpisode().getEpisodeId(), blub.getFileId());
        	   

            
        	   Thread.sleep(3000);
        	   Anime a=conn.getAnime(blub.getEpisode().getAnime().getAnimeId(),AnimeMask.ALL);
        	   System.out.println(a.getEnglishName());
        	   System.out.println(a.getKanjiName());
        	   mapper.addADBAnime(a);
        } catch (Throwable t) {
	t.printStackTrace();
    } finally {
	try {
                if (conn != null) {
		conn.close();
                }
	} catch (Throwable t) {
                t.printStackTrace();
	}
    }
	}
}
