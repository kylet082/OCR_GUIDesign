package kgt.dev.ocr_gui.controller.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;

import javax.swing.JOptionPane;

import kgt.dev.ocr_gui.controller.ControlHandler;
import kgt.dev.ocr_gui.model.ModelHandler;
import kgt.dev.ocr_gui.model.TrainingSet;
import kgt.dev.ocr_gui.neuralnet.NeuralNets;
import kgt.dev.ocr_gui.neuralnet.SOM_Net;
import kgt.dev.ocr_gui.utilities.SerializeObj;
import kgt.dev.ocr_gui.view.dialogs.NetConfigDialog;

public class NetConfigController {
	
	private NetConfigDialog view;
	
	private ModelHandler model;
	
	private TrainingSet ts = null;
	
	private boolean isLoaded = false;
	
	private ActionListener actionLoad, actionClear,
							actionSave,actionCreate,actionNetSelect;
	
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param newView
	 */
	public NetConfigController(NetConfigDialog newView, ModelHandler newModel){
		this.view = newView;
		this.model = newModel;
	}
	
	/**
	 * Initialize the controller
	 */
	public void control(){
		netSelectAction();
		loadAction();
		clearAction();
		createAction();
		saveAction();
	}
	
	private void netSelectAction(){
		actionNetSelect = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(view.getNetComboBox().getSelectedItem() == "Feed Forward"){
					view.getLoadBtn().setEnabled(true);
				}
				else if(view.getNetComboBox().getSelectedItem() == "SOM"){
					view.getLoadBtn().setEnabled(true);
				}
				else{
					view.getLoadBtn().setEnabled(false);
				}
			}
		};
		view.getNetComboBox().addActionListener(actionNetSelect);
	}
	
	/**
	 * 
	 */
	private void loadAction(){
		actionLoad = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ts = ControlHandler.getPrimActions().trainingSetChooser();
				model.setNetTrainingSet(ts);
				isLoaded = true;
				view.getClearBtn().setEnabled(true);
				view.setNetLabel("Training set : " + model.getTrainingSet().getSetName());
				setTrainTextData();
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
				
				if(!isLoaded){
					JOptionPane.showMessageDialog(view.getFrame(),
							"There is no training set to clear", "Error",
							JOptionPane.ERROR_MESSAGE);
				}else{
					ts = null;
					isLoaded = false;
					view.getClearBtn().setEnabled(false);
					view.setNetLabel("No Network set");
					view.setConfigText("");
					view.getSaveBtn().setEnabled(false);
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
				
				if(view.getNetComboBox().getSelectedItem().toString() == "SOM"){
					final NeuralNets som = new SOM_Net(ts);
					som.train();
					model.setNetModel(som);
					view.getSaveBtn().setEnabled(true);
					JOptionPane.showMessageDialog(view.getFrame(),
							"Network Created and trained", "Done",
							JOptionPane.INFORMATION_MESSAGE);
				
				}
				else if(view.getNetComboBox().getSelectedItem().toString() == "Feed Forward"){
					
					JOptionPane.showMessageDialog(view.getFrame(),
							"Feed forward Network coming soon!", "Done",
							JOptionPane.INFORMATION_MESSAGE);
					System.out.println("Comming Soon !");
				}
				else{
				
				}
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
				
				String name = JOptionPane.showInputDialog(view.getFrame(), "Name the Network");
				Path path = SerializeObj.createDir("Neural Networks");
				File f = new File(path.toString() + "/" + name + ".net");
				
				SerializeObj.save(f, model.getNeuralNet());
			}
			
		};
		view.getSaveBtn().addActionListener(actionSave);
	}
	
	private void setTrainTextData(){
		int width = ts.getTrainingSet().get(0).getWidth();
		int height = ts.getTrainingSet().get(0).getHeight();
		int count = ts.getTrainingSet().size();
		view.setConfigText(
				">> Sample Width : " + width + "\n" + 
				">> Sample Height: " + height + "\n" +
				">> Char Count   : " + count+ "\n" + 
				"--------------------------------------------------------------------" + "\n" + 
				">> Network Type : " + view.getNetComboBox().getSelectedItem() + "\n" + 
				">> Input Neurons: " +  width * height + "\n" + 
				">> Hidden Neurons: " + 0 + "\n" + 
				">> Output Neurons: " + count + "\n");
	}
}

