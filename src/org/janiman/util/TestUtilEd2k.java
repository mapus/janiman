package org.janiman.util;

import java.io.File;

public class TestUtilEd2k {

	public static void main(String[] argv)
	{
		File f = new File("E:/Anime-Watching/[gg]_EUREKA_SEVEN_AO_-_13_[3A595BE2].mkv");
		System.out.println(UtilEd2k.generateEd2kHash(f));
	}
}
