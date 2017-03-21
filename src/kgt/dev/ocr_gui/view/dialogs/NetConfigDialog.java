package kgt.dev.ocr_gui.view.dialogs;

import java.awt.Dimension;

import javax.swing.JFrame;

public class NetConfigDialog {

	private JFrame frame;
	
	public NetConfigDialog(JFrame newFrame){
		this.frame = newFrame;
	}
	
	public void init(){
		frame.setBounds(300,150,300,300);
		frame.setPreferredSize(new Dimension(300,300));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		frame.setVisible(true);
	}
}
