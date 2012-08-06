package org.janiman.gui.dialog.open;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchBarView extends JPanel {
	
	ArrayList<PropertyChangeListener> listener;
	
	JTextField inputField;
	JButton inputButton;
	public SearchBarView()
	{
		super();
		initComponents();
		setUp();
	}
	private void initComponents()
	{
		
		listener = new ArrayList<PropertyChangeListener>();
		inputField=new JTextField();
		inputButton = new JButton("Search");
	}
	
	private void setUp()
	{
		super.add(inputField);
		inputField.setPreferredSize(new Dimension(200,50));
		super.add(inputButton);
		inputButton.addActionListener(new Controller());
	}
	public void addPropertyChangeListener(PropertyChangeListener pcl)
	{
		listener.add(pcl);
	}
	public void removePropertyChangeListener(PropertyChangeListener pcl)
	{
		listener.remove(pcl);
	}
	private void firePropertyChange(PropertyChangeEvent pce)
	{
		for(PropertyChangeListener pl : listener)
		{
			pl.propertyChange(pce);
		}
	}
	
	class Controller implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			String searchText;
			searchText = inputField.getText();
			if(searchText!=null)
			{
				firePropertyChange(new PropertyChangeEvent(this,"searchValue",searchText,searchText));
			}
			
		}
		
	}
	

}
