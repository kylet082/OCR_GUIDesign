package kgt.dev.ocr_gui.controller.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import kgt.dev.ocr_gui.controller.ControlHandler;
import kgt.dev.ocr_gui.createSets.TrainingSet;
import kgt.dev.ocr_gui.view.dialogs.NetConfigDialog;

public class NetConfigController {
	
	private NetConfigDialog view;
	
	private TrainingSet ts = null;
	
	private ActionListener actionLoad, actionClear,
							actionSave,actionCreate;
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param newView
	 */
	public NetConfigController(NetConfigDialog newView){
		this.view = newView;
	}
	
	/**
	 * Initialize the controller
	 */
	public void control(){
		loadAction();
		clearAction();
		createAction();
		saveAction();
	}
	
	/**
	 * 
	 */
	private void loadAction(){
		actionLoad = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ts = ControlHandler.getPrimActions().trainingSetChooser();
				
			}
		};
		view.getLoadBtn().addActionListener(actionLoad);
	}
	
	/**
	 * 
	 */
	private void clearAction(){
		actionClear = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(ts == null){
					JOptionPane.showMessageDialog(view.getFrame(),
							"There is no training set to clear", "Error",
							JOptionPane.ERROR_MESSAGE);
				}else{
					ts = null;
					JOptionPane.showMessageDialog(view.getFrame(),
							"Training set has been removed", "Done",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
		};
		view.getClearBtn().addActionListener(actionClear);
	}
	
	/**
	 * 
	 */
	private void createAction(){
		actionCreate = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		};
		view.getCreateBtn().addActionListener(actionCreate);
	}
	
	/**
	 * 
	 */
	private void saveAction(){
		actionSave = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		};
		view.getSaveBtn().addActionListener(actionSave);
	}
}

