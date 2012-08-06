package org.janiman.gui.main;

import javax.swing.JFrame;


public class TestMainMenuBar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		frame.setJMenuBar(new MainMenuBar(frame));
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
