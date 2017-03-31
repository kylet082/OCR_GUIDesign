package kgt.dev.ocr_gui.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import kgt.dev.ocr_gui.view.tools.BinaryThreshTool;
import kgt.dev.ocr_gui.view.tools.ContrastTool;
import kgt.dev.ocr_gui.view.tools.GuassianNoiseTool;
import kgt.dev.ocr_gui.view.tools.SmoothingTool;
import kgt.dev.ocr_gui.view.tools.Tools;

public class ToolPanel extends Panel {
	
	private static final long serialVersionUID = 1L;
	
	private int width, height;
	
	private JPanel outerPanel, topPanel,btmPanel,imagingToolPanel,documentPanel;
	
	private Tools contrast,smoothing,guassian,thresh;
	
	/**
	 * CONSTRUCTOR
	 * 
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
		BoxLayout outerLayout = new BoxLayout(outerPanel, BoxLayout.Y_AXIS);
	
		Dimension topDim = new Dimension(this.getPreferredSize().width -17,this.getPreferredSize().height/2 + 70);
		topPanel = new JPanel();
		topPanel.setPreferredSize(topDim);
		topPanel.setBackground(new Color(96, 125, 139));
		FlowLayout tabLayout = new FlowLayout(FlowLayout.CENTER);
		tabLayout.setVgap(-5);
		topPanel.setLayout(tabLayout);
		JTabbedPane utilities = new JTabbedPane();
		
		imagingToolPanel = new JPanel();
		imagingToolPanel.setPreferredSize(topDim);
		imagingToolPanel.setBackground(new Color(52, 73, 94));
		BoxLayout imgLayout = new BoxLayout(imagingToolPanel, BoxLayout.Y_AXIS);
		
		initImagingTools(imagingToolPanel);
		
		documentPanel = new JPanel();
		documentPanel.setPreferredSize(topDim);
		documentPanel.setBackground(new Color(52, 73, 94));
		BoxLayout docLayout = new BoxLayout(documentPanel,BoxLayout.Y_AXIS);
		
		initDocAnalysisTools(documentPanel);
		
		utilities.add("Imaging", imagingToolPanel);
		utilities.addTab("Document", documentPanel);
		
		topPanel.add(utilities);
		
		JSeparator separate = new JSeparator(SwingConstants.HORIZONTAL);
		separate.setPreferredSize(new Dimension(width-20,5));
		
		btmPanel = new JPanel();
		btmPanel.setPreferredSize(new Dimension(this.getPreferredSize().width-20, this.getPreferredSize().height/2 -165));
		btmPanel.setBackground(new Color(52, 73, 94));
		
		outerPanel.add(topPanel);
		outerPanel.add(separate);
		outerPanel.add(btmPanel);
		this.add(outerPanel);
	}
	
	private void initImagingTools(JPanel panel){
		
		contrast = new ContrastTool("Contrast",100);
		contrast.init();
		thresh = new BinaryThreshTool("Binary Threshold",255);
		thresh.init();
		guassian = new GuassianNoiseTool("Guassian Noise",100);
		guassian.init();
		smoothing = new SmoothingTool("Smoothing",100);
		smoothing.init();
		
		panel.add(contrast);
		panel.add(thresh);
		panel.add(guassian);
		panel.add(smoothing);
	}
	
	private void initDocAnalysisTools(JPanel panel){
		
	}
	
	public Tools getContrast() {
		return contrast;
	}

	public Tools getSmoothing() {
		return smoothing;
	}

	public Tools getGuassian() {
		return guassian;
	}

	public Tools getThresh() {
		return thresh;
	}
}
