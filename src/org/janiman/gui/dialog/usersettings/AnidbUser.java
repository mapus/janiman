package org.janiman.gui.dialog.usersettings;

import java.io.Serializable;

public class AnidbUser implements Serializable {
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	private String password;
	private String username;
	private long id;

}
