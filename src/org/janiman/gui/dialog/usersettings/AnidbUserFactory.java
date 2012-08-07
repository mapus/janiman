package org.janiman.gui.dialog.usersettings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AnidbUserFactory  {
	
	public static String SAVE_FILE_NAME = "usersettings.big";
	private static AnidbUserFactory instance = new AnidbUserFactory();
	private AnidbUser user = new AnidbUser();
	
	private AnidbUserFactory()
	{

	}
	public static AnidbUserFactory getInstance()
	{
		return instance;

	}
	public AnidbUser getAnidbUser()
	{
		return user;
	}
	public void saveUserData(String username, String password, long anidbApi)
	{
		try {
			FileOutputStream fos = new FileOutputStream(SAVE_FILE_NAME);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			user.setUsername(username);
			user.setPassword(password);
			user.setId(anidbApi);
			
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
	public AnidbUser loadUserData()
	{
		try {
			FileInputStream fis = new FileInputStream(SAVE_FILE_NAME);
			ObjectInputStream ooi = new ObjectInputStream(fis);
			user = (AnidbUser) ooi.readObject();
			
			ooi.close();
			fis.close();
			
		
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
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
