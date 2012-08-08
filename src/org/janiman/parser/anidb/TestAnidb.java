package org.janiman.parser.anidb;


import java.util.List;

import org.janiman.db.impl.DBMapper;

import net.anidb.Anime;
import net.anidb.File;
import net.anidb.udp.AniDbException;
import net.anidb.udp.UdpConnection;
import net.anidb.udp.UdpConnectionException;
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
				Anime a = conn.getAnime(8854,AnimeMask.ALL);
				Anime b = conn.getAnime(8854);
				System.out.println("derp");
			} catch (UdpConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AniDbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    
            {
}}}
