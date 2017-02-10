package kgt.dev.ocr_gui.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class ToolPanel extends Panel {
	
	private static final long serialVersionUID = 1L;
	private int width, height;
	private JPanel outerPanel;
	
	/**
	 * @param newWidth - panel width
	 * @param newHeight - panel height
	 */
	public ToolPanel(int newWidth, int newHeight) {
		super(newWidth, newHeight);
		this.width = newWidth;
		this.height = newHeight;
	}
	
	/**
	 * (non-Javadoc)
	 * @see kgt.dev.ocr_gui.view.Panel#init()
	 * 
	 * Add the panel layout and components
	 */
	@Override
	public void init() {
		this.setPreferredSize(new Dimension(width,height));
		this.setMinimumSize(new Dimension(width,height));
		this.setMaximumSize(new Dimension(width,height));
		this.setBackground(new Color(96, 125, 139));
		
		Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED,
				new Color(117, 117, 117), new Color(189, 189, 189));
		
		this.setBorder(border);
		
		outerPanel = new JPanel();
		outerPanel.setBackground(new Color(96, 125, 139));
		outerPanel.setPreferredSize(new Dimension(width-5,height));
		Border outerBorder = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3,3,65,3),
				BorderFactory.createSoftBevelBorder(BevelBorder.RAISED, new Color(51,51,51), new Color(153,153,153),
						new Color(153,153,153),new Color(204,204,2)));
		outerPanel.setBorder(outerBorder);
		
		this.add(outerPanel);
		
	}
}
