package org.janiman.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.janiman.db.impl.DBMapper;

public class UtilFileMove {
	
	
	public static void moveFile(String absFilePath,String absPathtoDictionary,long episodeId)
	{
		InputStream inStream = null;
		OutputStream outStream = null;
	 
	    	try{
	 
	    		System.out.println("startet file Moviing");
	    	    File afile =new File(absFilePath);
	    	    File bfile =new File(absPathtoDictionary);
	    	    bfile.getName();
	    	    File targetFile= new File(absPathtoDictionary+"/"+afile.getName());
	 
	    	    inStream = new FileInputStream(afile);
	    	    outStream = new FileOutputStream(targetFile);
	 
	    	    byte[] buffer = new byte[1024];
	 
	    	    int length;
	    	    //copy the file content in bytes 
	    	    while ((length = inStream.read(buffer)) > 0){
	 
	    	    	outStream.write(buffer, 0, length);
	 
	    	    }
	 
	    	    inStream.close();
	    	    outStream.close();
	    	    DBMapper.getInstance().updateFileLoc(absPathtoDictionary+"\\+"+afile.getName(),episodeId);
	    	    //delete the original filenewFolder
	    	    afile.delete();
	    	    System.out.println("File is copied successful!");
	    	    //TODO update database;
	 
	    	}catch(IOException e){
	    	    e.printStackTrace();
	    	}
	}

}
