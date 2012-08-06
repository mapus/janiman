package org.janiman.gui.dialog.open;

import javax.swing.JFrame;

public class TestNewAnimeView {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new NewAnimeController().getView());
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}

}
