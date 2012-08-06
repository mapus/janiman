package org.janiman.gui.dialog.usersettings;

import java.io.Serializable;

public class MALUser implements Serializable {
	
	private String username;
	private String password;
	private int MALid;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getMALid() {
		return MALid;
	}
	public void setMALid(int mALid) {
		MALid = mALid;
	}
	public String toString()
	{
		return new String(username + " " + MALid +" " + password);
	}

	
	
	

}
