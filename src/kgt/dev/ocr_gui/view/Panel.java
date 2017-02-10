package kgt.dev.ocr_gui.view;

import javax.swing.JPanel;

public abstract class Panel extends JPanel{
	
	/**
	 * @param newWidth - width of the panel
	 * @param newHeight - height of the panel
	 */
	public Panel(int newWidth, int newHeight){
		
	}
	/**
	 * Panel initialization
	 */
	public abstract void init();
	
}
