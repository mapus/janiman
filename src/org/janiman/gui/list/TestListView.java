package org.janiman.gui.list;

import javax.swing.JFrame;

public class TestListView {
	
	public static void main(String[] argv)
	{
		JFrame frame = new JFrame();
		frame.add(new ListView());
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
