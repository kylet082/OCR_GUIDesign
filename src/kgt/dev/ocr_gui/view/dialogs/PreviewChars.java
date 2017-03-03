package kgt.dev.ocr_gui.view.dialogs;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PreviewChars {

	private JFrame frame;
	
	private JPanel viewPortPanel;
	
	private GridLayout gl;
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param newFrame -new JFrame
	 */
	public PreviewChars(JFrame newFrame){
		this.frame = newFrame;
	}
	
	/**
	 * Initialize the preview frame 
	 */
	public void init(){
		frame.setBounds(380,110,600,550);
		frame.setPreferredSize(new Dimension(600,550));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JScrollPane scrolPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
		viewPortPanel = new JPanel();
		
		gl = new GridLayout();
		viewPortPanel.setLayout(gl);
		scrolPane.getViewport().setView(viewPortPanel);
		
		frame.add(scrolPane);
		frame.setVisible(true);
	}
	
	/**
	 * @return -layout manager
	 */
	public GridLayout getLayout(){
		return gl;
	}
	
	/**
	 * @return -scroll panel view port
	 */
	public JPanel getViewPortPanel(){
		return viewPortPanel;
	}
}
