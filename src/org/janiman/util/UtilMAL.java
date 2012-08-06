package org.janiman.util;

public class UtilMAL {
	
	
	public static String convertTumbnailUrlToUrl(String  url)
	{
		StringBuilder sb = new StringBuilder(url);
		sb.lastIndexOf("t");
		sb.deleteCharAt(sb.lastIndexOf("t"));
		String newurl = sb.toString().trim();
		return newurl;
	}

}
