package org.janiman.gui.dialog.usersettings;

public class TestMALUserFactory {
	
	public static void main(String[] argv)
	{
		MALUserFactory factory = MALUserFactory.getInstance();
		factory.loadUserData();
		System.out.println(factory.getMALUser().toString());
		factory.saveUserData("bernd","123456",5555);
		System.out.println(factory.getMALUser().toString());
		
	}

}
