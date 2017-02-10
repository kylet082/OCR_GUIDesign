package kgt.dev.ocr_gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import kgt.dev.ocr_gui.model.ModelHandler;
import kgt.dev.ocr_gui.view.ViewHandler;

public class MenuBarController {
	
	private ViewHandler view;
	private ModelHandler model;
	private ActionListener actionOpen;

	/**
	 * 
	 * @param newView
	 * @param newModel
	 */
	public MenuBarController(ViewHandler newView,ModelHandler newModel){
		this.view = newView;
		this.model = newModel;
	}
	/**
	 *  Initialize the action listeners for the menu bar controller
	 */
	public void control(){
		actionOpen();
	}
	
	private void actionOpen(){
		actionOpen = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				ControlHandler.getPrimActions().ImgChooser(PrimaryActionController.OPEN);
				
			}
			
		};
		view.getMenuBar().getOpenMenuItem().addActionListener(actionOpen);
	}
}
