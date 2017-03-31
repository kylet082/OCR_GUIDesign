package kgt.dev.ocr_gui.controller;

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

import kgt.dev.ocr_gui.model.ModelHandler;
import kgt.dev.ocr_gui.view.ViewHandler;

public class ImageToolController {

	private ModelHandler model;
	
	private ViewHandler view;
	
	private ChangeListener actionContrast,actionHistEqual,
		actionSmoothing,actionGuaissianNoise,actionBinaryThresh;
	
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
		histEqualAction();
		smoothingAction();
		guassianNoiseAction();
		thresholdAction();
		contValAction();
		threshValAction();
		guassianValAction();
		smoothValAction();
	}
	
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
	private void contrastAction(){
		
	}
	
	/**
	 * 
	 */
	private void thresholdAction(){
		actionBinaryThresh = new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				view.getToolPanel().getThresh().setValue(view.getToolPanel().getThresh().getSlider().getValue());
			}
			
		};
		view.getToolPanel().getThresh().getSlider().addChangeListener(actionBinaryThresh);
	}
	
	/**
	 * 
	 */
	private void histEqualAction(){
		
		actionHistEqual = new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				view.getToolPanel().getContrast().setValue(view.getToolPanel().getContrast().getSlider().getValue());
			}
			
		};
		view.getToolPanel().getContrast().getSlider().addChangeListener(actionHistEqual);
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
			}
			
		};
		view.getToolPanel().getGuassian().getSlider().addChangeListener(actionGuaissianNoise);
	} 
}
