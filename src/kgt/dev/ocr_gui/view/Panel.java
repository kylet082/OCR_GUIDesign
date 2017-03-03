package kgt.dev.ocr_gui.view;

import javax.swing.JPanel;

public abstract class Panel extends JPanel{
	private static final long serialVersionUID = -4216326271396121591L;

	/**
	 * CONSTRUCTOR
	 * 
	 * @param newWidth - width of the panel
	 * @param newHeight - height of the panel
	 */
	public Panel(int newWidth, int newHeight){}

	/**
	 * Panel initialization
	 */
	public abstract void init();
	
}
