package org.janiman.gui.dialog.open;

import javax.swing.JFrame;

public class TestAddAnimeDialog {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		AddAnimeDialog dialog = new AddAnimeDialog(frame);
		dialog.setVisible(true);

	}

}
