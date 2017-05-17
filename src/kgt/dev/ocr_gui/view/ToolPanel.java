package kgt.dev.ocr_gui.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
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
	
	private JPanel outerPanel, topPanel,btmPanel,imagingToolPanel,documentPanel,spacer;
	
	private Tools contrast,smoothing,guassian,thresh;
	
	private JButton applyBtn,revertBtn, startOCRBtn, nnConfigBtn, loadNetBtn,
								findLinesBtn, findWordsBtn, findCharsBtn, projectionBtn, skewDetectBtn  ;
	
	private JCheckBox preview;
	
	private Dimension topDim;
	
	private JLabel activeNeuralNet;
	
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
	
		topDim = new Dimension(this.getPreferredSize().width -17,this.getPreferredSize().height/2 + 70);
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
		ocrToolPanel(btmPanel);
		
		outerPanel.add(topPanel);
		outerPanel.add(separate);
		outerPanel.add(btmPanel);
		this.add(outerPanel);
	}
	
	/**
	 * Intialize the imaging tools view components
	 * 
	 * @param panel
	 */
	private void initImagingTools(JPanel panel){
		int width = panel.getPreferredSize().width;
		
		//set the range of manipulation for imaging functions
		int[] contRange = {-127,127};
		int[] threshRange = {0,255};
		int[] GuassianRange = {0,60};
		int[] smoothRange = {0,60};
		
		contrast = new ContrastTool("Contrast",contRange,width);
		contrast.init();
		thresh = new BinaryThreshTool("Binary Threshold",threshRange,width);
		thresh.init();
		guassian = new GuassianNoiseTool("Guassian Noise",GuassianRange,width);
		guassian.init();
		smoothing = new SmoothingTool("Smoothing",smoothRange,width);
		smoothing.init();
		
		applyBtn = new JButton("Apply Changes");
		applyBtn.setFont(new Font(this.getFont().getName(),Font.BOLD,12));
		applyBtn.setBackground(new Color(255,87,34));
		applyBtn.setPreferredSize(new Dimension(width/2 +30,35));
		
		ImageIcon back = new ImageIcon("res/icons/back32X32.png");
		revertBtn = new JButton();
		revertBtn.setBackground(new Color(255,87,34));
		revertBtn.setPreferredSize(new Dimension(width/2 -60,35));
		revertBtn.setIcon(back);
		
		panel.add(contrast);
		panel.add(thresh);
		panel.add(guassian);
		panel.add(smoothing);
		
		spacer = new JPanel();
		spacer.setBackground(new Color(52, 73, 94));
		//int spacerHeight = panel.getPreferredSize().height - (90 * 5) + 50;
		spacer.setPreferredSize(new Dimension(topDim.width,10));
		panel.add(spacer);
		panel.add(applyBtn);
		panel.add(revertBtn);
	}
	

	/**
	 * Intialize the document analysis tool components view
	 * 
	 * @param panel
	 */
	private void initDocAnalysisTools(JPanel panel){
		
		Border border = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(2,2,20,2),
				BorderFactory.createTitledBorder("Search for elements"));
		panel.setBorder(border);
		
		int width = panel.getPreferredSize().width;
		int height = panel.getPreferredSize().height;
		Dimension dimBtn = new Dimension(width -70, 40 );
		
		projectionBtn = new JButton("Projection Profile");
		findLinesBtn = new JButton("Text Lines");
		findWordsBtn = new JButton("Words");
		findCharsBtn = new JButton("Letters");
		skewDetectBtn = new JButton("Skew Detection");
		
		findLinesBtn.setPreferredSize(dimBtn);
		findWordsBtn.setPreferredSize(dimBtn);
		findCharsBtn.setPreferredSize(dimBtn);
		projectionBtn.setPreferredSize(dimBtn);
		skewDetectBtn.setPreferredSize(dimBtn);
		
		findLinesBtn.setBackground(new Color(255,87,34));
		findWordsBtn.setBackground(new Color(255,87,34));
		findCharsBtn.setBackground(new Color(255,87,34));
		projectionBtn.setBackground(new Color(255,87,34));
		skewDetectBtn.setBackground(new Color(255,87,34));
		
		panel.add(findLinesBtn);
		panel.add(findWordsBtn);
		panel.add(findCharsBtn);
		panel.add(projectionBtn);
		panel.add(skewDetectBtn);
		
	}

	

	/**
	 * Intialize the the ocr tools view components
	 * 
	 * @param panel
	 */
	private void ocrToolPanel(JPanel panel){
		
		int width = panel.getPreferredSize().width;
		int height = panel.getPreferredSize().height;
		
		JPanel upperPanel = new JPanel();
		upperPanel.setPreferredSize(new Dimension(width -10, height - 160 ));
		upperPanel.setBackground(new Color(52, 73, 94));
		
		activeNeuralNet =  new JLabel("No Network Loaded");
		activeNeuralNet.setFont(new Font(this.getFont().getFontName(), Font.BOLD,14));
		upperPanel.add(activeNeuralNet);
		
		Border border = BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(2,2,2,2),BorderFactory.createTitledBorder("OCR"));
		panel.setBorder(border);
		
		
		startOCRBtn = new JButton("Start OCR");
		nnConfigBtn = new JButton("Configure");
		loadNetBtn = new JButton("Load");
		
		startOCRBtn.setPreferredSize(new Dimension(width-20,40));
		nnConfigBtn.setPreferredSize(new Dimension(width/2 -12,40));
		loadNetBtn.setPreferredSize(new Dimension(width/2 -12,40));
		startOCRBtn.setBackground(new Color(255,87,34));
		nnConfigBtn.setBackground(new Color(255,87,34));
		loadNetBtn.setBackground(new Color(255,87,34));
		
		startOCRBtn.setEnabled(false);
		
		panel.add(upperPanel);
		panel.add(startOCRBtn);
		panel.add(nnConfigBtn);
		panel.add(loadNetBtn);
	
	}
	
	
	/**
	 * 
	 * Getters and setters 
	 */
	
	public JButton getLoadBtn(){
		return loadNetBtn;
	}
	
	public JButton getApplyBtn() {
		return applyBtn;
	}

	public JButton getRevertBtn() {
		return revertBtn;
	}
	
	public JButton getSkewDetectBtn() {
		return skewDetectBtn;
	}
	
	public JLabel getActiveNetLbl() {
		return activeNeuralNet;
	}

	public void setActiveNeuralNet(JLabel activeNeuralNet) {
		this.activeNeuralNet = activeNeuralNet;
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
	
	public JButton getOCRStartBtn(){
		return this.startOCRBtn;
	}
	
	public JButton getConfigureBtn(){
		return this.nnConfigBtn;
	}
	
	public JCheckBox getPreviewCheck(){
		return this.preview;
	}
	
	public JButton getFindLinesBtn() {
		return findLinesBtn;
	}

	public JButton getFindWordsBtn() {
		return findWordsBtn;
	}

	public JButton getFindCharsBtn() {
		return findCharsBtn;
	}

	public JButton getProjectionBtn() {
		return projectionBtn;
	}
}
