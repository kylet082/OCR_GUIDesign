package kgt.dev.ocr_gui.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.opencv.core.Mat;

import kgt.dev.ocr_gui.model.DocumentAnalysis;
import kgt.dev.ocr_gui.model.ModelHandler;
import kgt.dev.ocr_gui.model.ShowProjection;
import kgt.dev.ocr_gui.utilities.ImageProc;
import kgt.dev.ocr_gui.view.ViewHandler;
import kgt.dev.ocr_gui.view.charts.HistogramChart;

public class DocumentAnalysisController {
	
	private ViewHandler view;
	private ModelHandler model;
	
	private ActionListener linesAction, wordsAction,charsAction,projectionAction, skewDetectAction;
	
	public DocumentAnalysisController(ViewHandler newView,ModelHandler newModel){
		this.view = newView;
		this.model = newModel;
	}
	
	/**
	 * Initialise the control methods
	 */
	public void control(){
		actionFindLines();
		actionFindWords();
		actionFindChars();
		actionProjection();
		actionSkewDetect();
	}
	
	/**
	 * Demos the line segmentation
	 */
	private void actionFindLines(){
		linesAction = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Mat imgMat = ControlHandler.getPrimActions().getFocusedImage().getImgMatrix();
				DocumentAnalysis docAnalysis = new DocumentAnalysis(imgMat);
				docAnalysis.calculateHist();
				docAnalysis.calcLineProjection();
				
				ShowProjection show = new ShowProjection(docAnalysis.getLineBinsDim(),ImageProc.cvtMatToBufferImg(imgMat));
				
				//Convert the JPanel to a BufferedImage to display
				BufferedImage bi = new BufferedImage(imgMat.cols(), imgMat.rows(), BufferedImage.TYPE_INT_RGB);
			    Graphics2D g = bi.createGraphics();
			    show.paint(g);
			    //Update the center display image
				view.getCenterPanel().getDisplayPanel().setDisplayImage(bi);
				view.getCenterPanel().getDisplayPanel().update();
			}
			
		};
		view.getToolPanel().getFindLinesBtn().addActionListener(linesAction);
	}
	
	private void actionFindWords(){
		wordsAction = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
			
		};
		view.getToolPanel().getFindWordsBtn().addActionListener(wordsAction);
	}
	
	private void actionFindChars(){
		charsAction = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
			
		};
		view.getToolPanel().getFindCharsBtn().addActionListener(charsAction);
	}
	
	private void actionProjection(){
		projectionAction = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Mat imgMat = ControlHandler.getPrimActions().getFocusedImage().getImgMatrix();
				DocumentAnalysis docAnalysis = new DocumentAnalysis(imgMat);
				docAnalysis.calculateHist();
				
				HistogramChart chart = new HistogramChart(docAnalysis.getHistogramData());
				
				Mat resized = ImageProc.resizeImgAspect(imgMat, imgMat.size(), 600, ImageProc.AXIS_HEIGHT);
				Image img = ImageProc.cvtMatToBufferImg(resized);
				JLabel imgIcon = new JLabel(new ImageIcon(img)); 
				
				JPanel container = new JPanel();
				container.setBackground(Color.white);
				BorderLayout box = new BorderLayout();
				container.setLayout(box);
				JPanel spacer = new JPanel();
				spacer.setPreferredSize(new Dimension(400,10));
				container.add(spacer, BorderLayout.NORTH);
				container.add(imgIcon, BorderLayout.CENTER);
				
				JFrame testF = new JFrame();
				testF.setBackground(Color.WHITE);
				BorderLayout layout = new BorderLayout();
				testF.setLayout(layout);
				testF.add(chart.draw("Histogram Result"),BorderLayout.WEST);
				testF.add(container,BorderLayout.EAST);
				testF.pack();
				testF.setVisible(true);
			}
			
		};
		view.getToolPanel().getProjectionBtn().addActionListener(projectionAction);
	}
	
	private void actionSkewDetect(){
		
		skewDetectAction = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Mat imgMat = ControlHandler.getPrimActions().getFocusedImage().getImgMatrix();
				
				Mat result = ImageProc.houghLine(imgMat);
				
				Image img = ImageProc.cvtMatToBufferImg(result);
				
				view.getCenterPanel().getDisplayPanel().setDisplayImage(img);
				view.getCenterPanel().getDisplayPanel().update();
			}
			
		};
		view.getToolPanel().getSkewDetectBtn().addActionListener(skewDetectAction);
	}
}
