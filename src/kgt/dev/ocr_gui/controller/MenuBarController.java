package kgt.dev.ocr_gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import kgt.dev.ocr_gui.controller.dialogs.TrainingDialogController;
import kgt.dev.ocr_gui.model.ModelHandler;
import kgt.dev.ocr_gui.view.ViewHandler;
import kgt.dev.ocr_gui.view.dialogs.NetConfigDialog;
import kgt.dev.ocr_gui.view.dialogs.TrainingDialog;

public class MenuBarController {
	
	private ViewHandler view;
	private ModelHandler model;
	
	private ActionListener actionOpen,
							actionT_Dialog, actionNN_Dialog;

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
		configNetDialogAction();
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
				final JFrame frame = new JFrame("Create Training Set");
				frame.setBounds(300,100,270,390);
				
				TrainingDialog td = new TrainingDialog(frame);
				td.init();
				
				TrainingDialogController tdc = new TrainingDialogController(td);
				tdc.control();
			}
		};
		view.getMenuBar().getTrainingItemMenu().addActionListener(actionT_Dialog);
	} 
	
	private void configNetDialogAction(){
		actionNN_Dialog = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				final JFrame frame = new JFrame("Network Cofiguration");
				
				final NetConfigDialog netConfig = new NetConfigDialog(frame);
				netConfig.init();
			}
		};
		view.getMenuBar().getNNCofigItem().addActionListener(actionNN_Dialog);
	}
}
