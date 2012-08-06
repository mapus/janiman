package org.janiman.parser.myanimelist;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

import org.janiman.gui.dialog.usersettings.MALUser;
import org.janiman.gui.dialog.usersettings.MALUserFactory;

public class MALAuthenticator extends Authenticator {
	
	MALUser user;
	String name;
	String pw;
	
	public MALAuthenticator()
	{
		super();
		user=MALUserFactory.getInstance().loadUserData();
		name=user.getUsername();
		pw=user.getPassword();
	}
	public MALAuthenticator(String name, String pw)
	{
		this.name=name;
		this.pw=pw;
	}
	@Override
	public PasswordAuthentication getPasswordAuthentication() { 
		return new PasswordAuthentication(name,pw.toCharArray());
	}

}
