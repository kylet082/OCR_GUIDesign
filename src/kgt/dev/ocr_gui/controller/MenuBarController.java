package kgt.dev.ocr_gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import kgt.dev.ocr_gui.controller.dialogs.TrainingDialogController;
import kgt.dev.ocr_gui.model.ModelHandler;
import kgt.dev.ocr_gui.view.ViewHandler;
import kgt.dev.ocr_gui.view.dialogs.TrainingDialog;

public class MenuBarController {
	
	private ViewHandler view;
	private ModelHandler model;
	
	private ActionListener actionOpen,
							actionT_Dialog;

	/**
	 * CONSTRUCTOR
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
		trainingDialogAction();
	}
	/**
	 * open action event listener
	 */
	private void actionOpen(){
		actionOpen = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ControlHandler.getPrimActions().ImgChooser(PrimaryActionController.OPEN);
			}
		};
		view.getMenuBar().getOpenMenuItem().addActionListener(actionOpen);
	}
	
	/**
	 *  initialize the TrainingDialog GUI
	 */
	private void trainingDialogAction(){
		actionT_Dialog = new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame frame = new JFrame();
				frame.setBounds(10,10,270,390);
				
				TrainingDialog td = new TrainingDialog(frame);
				td.init();
				
				TrainingDialogController tdc = new TrainingDialogController(td);
				tdc.control();
			}
		};
		view.getMenuBar().getNNMenu().addActionListener(actionT_Dialog);
	} 
}
