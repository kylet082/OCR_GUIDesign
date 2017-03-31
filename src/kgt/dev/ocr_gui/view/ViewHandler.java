package kgt.dev.ocr_gui.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class ViewHandler {
	
	private int Width, Height,tpWidth,cpWidth,ilWidth;
	
	private int[] centerPanelDim;
	
	private JFrame rootFrame;
	
	private BorderLayout rootLayout;
	
	private CenterPanel centerPanel;
	
	private ToolPanel toolPanel;
	
	private ImageListPanel imgListPanel;
	
	private MenuBar menuBar;
	
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param newFrame - the main content frame
	 */
	public ViewHandler(JFrame newFrame){
		this.rootFrame = newFrame;
		getNativeResolution(0,50);
		setAllPanelWIdths();
	}
	
	/**
	 * Initialization of the View Handler
	 */
	public void initFrame(){
		setMainFrame(rootFrame);
	}
	
	/**
	 * Set the main content Frame
	 * 
	 * @param frame - main content JFrame
	 */
	public void setMainFrame(JFrame frame){
		
		frame.setBounds(10, 10, Width, Height);
		frame.setPreferredSize(new Dimension(Width,Height));
		//frame.setMinimumSize(new Dimension((Width/7)+(Width/6),Height));
		frame.setFocusable(true);
		frame.setResizable(true);
		menuBar = new MenuBar(frame);
		menuBar.initMenu();
		
		rootLayout = new BorderLayout(0,0);
		frame.setLayout(rootLayout);
		
		/*
		 * Adding all the UI Component segments
		 */
		toolPanel = new ToolPanel(tpWidth,Height);
		toolPanel.init();
		
		centerPanel = new CenterPanel(cpWidth,Height);
		centerPanel.init();
		
		imgListPanel = new ImageListPanel(ilWidth,Height);
		imgListPanel.init();
		
		//Dialog handlers
		
		frame.add(toolPanel,BorderLayout.WEST);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.add(imgListPanel, BorderLayout.EAST);
		
		frame.pack();	
	}
	
	/**
	 * Resolves the devices native screen size and resolution
	 * 
	 * @param width - width restriction
	 * @param height - height restriction
	 */
	private void getNativeResolution(int width,int height){
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		Width = gd.getDisplayMode().getWidth()-width;
		Height = gd.getDisplayMode().getHeight()-height;
	}
	
	/**
	 * Keep the width ratio for internal panels in 
	 * accordance with the native screen resolution
	 */
	private void setAllPanelWIdths(){
		tpWidth = Width/6;
		ilWidth = Width/6;
		cpWidth = Width - (tpWidth + ilWidth);
    }
	
	/**
	 * @return - main content frame
	 */
	public JFrame getFrame(){
		return rootFrame;
	}
	
	/**
	 * @return - menu bar
	 */
	public MenuBar getMenuBar(){
		return menuBar;
	}
	
	/**
	 * @return - Center JPanel
	 */
	public CenterPanel getCenterPanel(){
		return centerPanel;
	}
	
	/**
	 * @return - Image list JPanel
	 */
	public ImageListPanel getImgListPane(){
		return imgListPanel;
	}
	
	/**
	 * @return - Tool panel
	 */
	public ToolPanel getToolPanel(){
		return this.toolPanel;
	}
}
