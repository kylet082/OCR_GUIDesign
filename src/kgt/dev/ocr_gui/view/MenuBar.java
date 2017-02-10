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
	private JMenu fileMenu,editMenu,helpMenu;
	//File node menu items
	private JMenuItem newMenuItem,openFileItem,saveFileItem;
	
	/**
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
		
		frame.setJMenuBar(getMenuBar());
		
	}
	
	/**
	 * Intializes the File menu components
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
	 * 	Initailizes the Edit menu components
	 */
	protected void initEditMenu(){
		editMenu = new JMenu("Edit");
	}
	
	/**
	 * @return
	 */
	public JMenuBar getMenuBar(){
		return menuBar;
	}
	
	/**
	 * @return
	 */
	/*public JFrame getMenuFrame(){
		return frame;
	}*/
	
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
}