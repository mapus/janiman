package org.janiman.gui.dialog.usersettings;

import javax.swing.JFrame;

public class TestUserSettingsDialog {
	
	public static void main(String[] argv)
	{
		MALUserSettingsDialog dialog = new MALUserSettingsDialog(new JFrame());
		dialog.setVisible(true);
		dialog.pack();
	}

}
