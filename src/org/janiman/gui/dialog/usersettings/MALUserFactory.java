package org.janiman.gui.dialog.usersettings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MALUserFactory {
	
	public static String SAVE_FILE_NAME = "usersettings.big";
	private static MALUserFactory instance = new MALUserFactory();
	private MALUser user = new MALUser();;
	
	private MALUserFactory()
	{
	}
	public static MALUserFactory getInstance()
	{
		return MALUserFactory.instance;
	}
	public MALUser getMALUser()
	{
		return user;
	}
	public void saveUserData(String username, String password, int id)
	{
		try {
			FileOutputStream fos = new FileOutputStream(SAVE_FILE_NAME);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			user.setUsername(username);
			user.setPassword(password);
			user.setMALid(id);
			
			oos.writeObject(user);
			
			fos.close();
			oos.close();
			System.out.println("Write success");
			
		} catch (FileNotFoundException e) {
			System.err.println("File not found: savefile");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}		
	public MALUser loadUserData()
	{
		try {
			FileInputStream fis = new FileInputStream(SAVE_FILE_NAME);
			ObjectInputStream ooi = new ObjectInputStream(fis);
			user = (MALUser) ooi.readObject();
			
			ooi.close();
			fis.close();
			
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	

}
