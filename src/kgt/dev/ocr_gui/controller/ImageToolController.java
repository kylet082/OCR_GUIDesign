package kgt.dev.ocr_gui.controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import kgt.dev.ocr_gui.model.ModelHandler;
import kgt.dev.ocr_gui.utilities.ImageProc;
import kgt.dev.ocr_gui.view.ViewHandler;


public class ImageToolController {

	private ModelHandler model;
	
	private ViewHandler view;
	
	private ChangeListener actionContrast,actionSmoothing,
								actionGuaissianNoise,actionBinaryThresh;
	
	private ActionListener actionHistEqual, actionApply, actionRevert;
	
	private FocusListener  actionConText,actionThreshText,actionGuassianText,actionSmoothText;
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param newView
	 * @param newModel
	 */
	public ImageToolController(ViewHandler newView, ModelHandler newModel){
		this.view = newView;
		this.model = newModel;
	}
	
	/**
	 * 
	 */
	public void init(){
		contrastAction();
		contrastEqualAction();
		smoothingAction();
		guassianNoiseAction();
		thresholdAction();
		contValAction();
		threshValAction();
		guassianValAction();
		smoothValAction();
		applyAction();
		revertAction();
	}
	
	private void contrastEqualAction() {
		actionHistEqual = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Mat m = ControlHandler.getPrimActions().getFocusedImage().getImgMatrix();
				
				Mat equaled = ImageProc.contrastEqual(m);
				
				Image img = ImageProc.cvtMatToBufferImg(equaled);
				
				//apply the contrasting action to the image
				view.getCenterPanel().getDisplayPanel().setDisplayImage(img);
				view.getCenterPanel().getDisplayPanel().update();
				
			}
		};
		
		view.getToolPanel().getContrast().getActionBtn().addActionListener(actionHistEqual);
	}

	/**
	 * 
	 */
	private void contValAction(){
		actionConText = new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {}

			@Override
			public void focusLost(FocusEvent arg0) {
				if(view.getToolPanel().getContrast().getValue() == " " ){
					
				}else{
					view.getToolPanel().getContrast().getSlider().setValue(Integer.parseInt(view.getToolPanel().getContrast().getValue()));
				}
			}
		};
		view.getToolPanel().getContrast().getTextArea().addFocusListener(actionConText);
	}
	
	/**
	 * 
	 */
	private void threshValAction(){
		actionThreshText = new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {}

			@Override
			public void focusLost(FocusEvent arg0) {
				if(view.getToolPanel().getThresh().getValue() == " " ){
					
				}else{
					view.getToolPanel().getThresh().getSlider().setValue(Integer.parseInt(view.getToolPanel().getThresh().getValue()));
				}
			}
		};
		view.getToolPanel().getThresh().getTextArea().addFocusListener(actionThreshText);
	}
	
	/**
	 * 
	 */
	private void guassianValAction(){
		actionGuassianText = new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {}

			@Override
			public void focusLost(FocusEvent arg0) {
				if(view.getToolPanel().getGuassian().getValue() == " " ){
					
				}else{
					view.getToolPanel().getGuassian().getSlider().setValue(Integer.parseInt(view.getToolPanel().getGuassian().getValue()));
				}
			}
		};
		view.getToolPanel().getGuassian().getTextArea().addFocusListener(actionGuassianText);
	}
	
	/**
	 * 
	 */
	private void smoothValAction(){
		actionSmoothText = new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {}

			@Override
			public void focusLost(FocusEvent arg0) {
				if(view.getToolPanel().getSmoothing().getValue() == " " ){
					
				}else{
					view.getToolPanel().getSmoothing().getSlider().setValue(Integer.parseInt(view.getToolPanel().getSmoothing().getValue()));
				}
			}
		};
		view.getToolPanel().getSmoothing().getTextArea().addFocusListener(actionSmoothText);
	}
	
	/**
	 * 
	 */
	private void thresholdAction(){
		actionBinaryThresh = new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				System.out.println("Changing Thresh");
				view.getToolPanel().getThresh().setValue(view.getToolPanel().getThresh().getSlider().getValue());
				int value = view.getToolPanel().getThresh().getSliderValue();
				Mat m = ControlHandler.getPrimActions().getFocusedImage().getImgMatrix();
				
				Mat results = ImageProc.setThreshhold(m, value);
				Image img = ImageProc.cvtMatToBufferImg(results);
				
				//apply the contrasting action to the image
				view.getCenterPanel().getDisplayPanel().setDisplayImage(img);
				view.getCenterPanel().getDisplayPanel().update();
			}
			
		};
		view.getToolPanel().getThresh().getSlider().addChangeListener(actionBinaryThresh);
	}
	
	/**
	 * 
	 */
	private void contrastAction(){
		
		actionContrast= new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				view.getToolPanel().getContrast().setValue(view.getToolPanel().getContrast().getSlider().getValue());
				Mat m = ControlHandler.getPrimActions().getFocusedImage().getImgMatrix();
				Mat m1 = ImageProc.setContrast(m, view.getToolPanel().getContrast().getSlider().getValue());
				Image img = ImageProc.cvtMatToBufferImg(m1);
				
				//apply the contrasting action to the image
				view.getCenterPanel().getDisplayPanel().setDisplayImage(img);
				view.getCenterPanel().getDisplayPanel().update();
			}
			
		};
		view.getToolPanel().getContrast().getSlider().addChangeListener(actionContrast);
	}
	
	/**
	 * 
	 */
	private void smoothingAction(){
		actionSmoothing = new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				view.getToolPanel().getSmoothing().setValue(view.getToolPanel().getSmoothing().getSlider().getValue());
			}
			
		};
		view.getToolPanel().getSmoothing().getSlider().addChangeListener(actionSmoothing);
	}
	
	/**
	 * 
	 */
	private void guassianNoiseAction(){
		actionGuaissianNoise = new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				view.getToolPanel().getGuassian().setValue(view.getToolPanel().getGuassian().getSlider().getValue());
				int val = view.getToolPanel().getGuassian().getSliderValue();
				Mat imgMat = ControlHandler.getPrimActions().getFocusedImage().getImgMatrix();
				
				Mat dest = ImageProc.setGaussianBlur(imgMat, val);
				Image img = ImageProc.cvtMatToBufferImg(dest);
				
				//apply the contrasting action to the image
				view.getCenterPanel().getDisplayPanel().setDisplayImage(img);
				view.getCenterPanel().getDisplayPanel().update();
			}
			
		};
		view.getToolPanel().getGuassian().getSlider().addChangeListener(actionGuaissianNoise);
	} 
	
	/**
	 * Link to memento pattern classes
	 */
	private void applyAction(){
		actionApply = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Image displayImage = view.getCenterPanel().getDisplayPanel().getDisplayImage();
				
			}
			
		};
	}
	
	private void revertAction(){
		actionRevert = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		};
	}
}
