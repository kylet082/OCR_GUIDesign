package kgt.dev.ocr_gui.controller;

import kgt.dev.ocr_gui.model.ModelHandler;
import kgt.dev.ocr_gui.view.ViewHandler;

public class ControlHandler {
	
	private ModelHandler model;
	private ViewHandler view;
	public static PrimaryActionController primaryActions;
	
	/**
	 * 
	 * @param newView
	 * @param newModel
	 */
	public ControlHandler(ViewHandler newView, ModelHandler newModel){
		this.view = newView;
		this.model = newModel;
	}
	/**
	 *  Initialize the Control handler
	 */
	public void init(){
		
		primaryActions = new PrimaryActionController(view, model);
		
		MenuBarController menuBar_C = new MenuBarController(view,model);
		menuBar_C.control();
		
		ImageListController listPanel_C = new ImageListController(view,model);
		listPanel_C.init();
	}
	
	/**
	 * 
	 * @return - Primary action controller
	 */
	public static PrimaryActionController getPrimActions(){
		return primaryActions;
	}
}
