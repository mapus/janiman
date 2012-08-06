package org.janiman.parser.anidb;

import java.io.File;

public class TestAnidbApi {
	public static void main(String[] argv)
	{
		System.out.println("LOOL");
		long timebefore = System.currentTimeMillis();
		AnidbApi.getInstance().hashAndAddFile(new File("E:/Anime-Watching/[gg]_EUREKA_SEVEN_AO_-_13_[3A595BE2].mkv"));
		long timeafter = System.currentTimeMillis();
		System.out.println("used time"+(timeafter-timebefore)/1000);
	}

}
