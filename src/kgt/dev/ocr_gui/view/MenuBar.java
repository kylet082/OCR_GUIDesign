package kgt.dev.ocr_gui.view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

public class MenuBar {
	
	private JFrame frame;
	
	private JMenuBar menuBar;
	
	private JMenu fileMenu,editMenu,helpMenu,
					nnMenu;
	
	//File node menu items
	private JMenuItem newMenuItem,openFileItem,saveFileItem,
				configNNItem,traingSetItem;
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param newFrame - the main content frame
	 */
	public MenuBar(JFrame newFrame){
		this.frame = newFrame;
		this.menuBar = new JMenuBar();
	}
	
	/**
	 * Initialize the menu bar 
	 */
	public void initMenu(){
		initFileMenu();
		initEditMenu();
		initNeuroNetMenu();
		
		frame.setJMenuBar(getMenuBar());
		
	}
	
	/**
	 * Initializes the File menu components
	 */
	protected void initFileMenu(){
		fileMenu = new JMenu("File");
		
		newMenuItem = new JMenuItem("New");
		openFileItem = new JMenuItem("Open Image");
		saveFileItem = new JMenuItem("Save");
		
		fileMenu.add(newMenuItem);
		fileMenu.add(openFileItem);
		fileMenu.add(saveFileItem);
		
		menuBar.add(fileMenu);

	}
	
	/**
	 * 	Initializes the Edit menu components
	 */
	protected void initEditMenu(){
		editMenu = new JMenu("Edit");
		
		menuBar.add(editMenu);
	}
	
	/**
	 * Initializes the neural Network menu Components
	 */
	protected void initNeuroNetMenu(){
		nnMenu = new JMenu("Neural Network");
		
		configNNItem = new JMenuItem("Configure");
		traingSetItem =new JMenuItem("Training Sets");
		
		nnMenu.add(configNNItem);
		nnMenu.add(traingSetItem);
		
		menuBar.add(nnMenu);
	}
	
	/**
	 * @return - menu bar  
	 */
	public JMenuBar getMenuBar(){
		return menuBar;
	}
	
	
	/**
	 * @return - file menu 
	 */
	public JMenu getFileMenu(){
		return fileMenu;
	}
	
	/**
	 * @return - new menu item
	 */
	public JMenuItem getNewMenuItem(){
		return newMenuItem;
	}
	
	/**
	 * @return - open file menu item 
	 */
	public JMenuItem getOpenMenuItem(){
		return openFileItem;
	}
	
	/**
	 * @return - save file menu item
	 */
	public JMenuItem getSaveMenuItem(){
		return saveFileItem;
	}
	
	/**
	 * @return - training set menu item menu 
	 */
	public JMenuItem getTrainingItemMenu(){
		return traingSetItem;
	}
	
	public JMenuItem getNNCofigItem(){
		return configNNItem;
	}
}