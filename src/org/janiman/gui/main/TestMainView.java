package org.janiman.gui.main;

import java.util.logging.Logger;

import javax.swing.JFrame;

import sun.rmi.runtime.Log;

public class TestMainView {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		MainView view = new MainView();
		view.pack();
		view.setVisible(true);
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
