package org.janiman.util;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import jonelo.jacksum.JacksumAPI;
import jonelo.jacksum.algorithm.AbstractChecksum;

public class UtilEd2k {
	
	public static String generateEd2kHash(File f)
	{
		AbstractChecksum checksum = null;
		try {
			checksum = JacksumAPI.getChecksumInstance("ed2k");
			checksum.setEncoding(AbstractChecksum.HEX);
			checksum.readFile(f.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return checksum.format("#CHECKSUM");
	}

}
