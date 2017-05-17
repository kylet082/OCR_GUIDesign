package kgt.dev.ocr_gui.controller;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.opencv.core.Mat;

import kgt.dev.ocr_gui.controller.dialogs.NetConfigController;
import kgt.dev.ocr_gui.controller.dialogs.TrainingDialogController;
import kgt.dev.ocr_gui.model.ImageMatrix;
import kgt.dev.ocr_gui.model.ModelHandler;
import kgt.dev.ocr_gui.model.ocrservice.CharacterSample;
import kgt.dev.ocr_gui.model.ocrservice.RunOCR;
import kgt.dev.ocr_gui.neuralnet.NeuralNets;
import kgt.dev.ocr_gui.neuralnet.SOM_Net;
import kgt.dev.ocr_gui.neuralnet.createSets.Entry;
import kgt.dev.ocr_gui.neuralnet.createSets.SampleData;
import kgt.dev.ocr_gui.utilities.ImageProc;
import kgt.dev.ocr_gui.view.ViewHandler;
import kgt.dev.ocr_gui.view.dialogs.NetConfigDialog;
import kgt.dev.ocr_gui.view.dialogs.TrainingDialog;

public class OCRController {

	private ViewHandler view;
	private ModelHandler model;
	
	private ActionListener actionStart,actionConfig, actionLoadEngine;
	
	public OCRController(ViewHandler newView, ModelHandler newModel){
		this.view = newView;
		this.model = newModel;
	}
	
	/**
	 * OCR controller initialization
	 */
	public void control(){
		startAction();
		configureAction();
		loadEngineAction();
	}
	
	/**
	 * Action controller for the start OCR process
	 */
	private void startAction(){
		actionStart = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				SOM_Net net = new SOM_Net(model.getTrainingSet());
				net.train();
				
				ImageMatrix imgMat = ControlHandler.getPrimActions().getFocusedImage();
				List<ImageMatrix> list = new ArrayList<ImageMatrix>();
				list.add(imgMat);
				Entry e = new Entry("input", list,10,10 );
				e.downSample();
				
				//net.recognise( e.getTrainingSet().getTrainingSet().get(0));
				
				System.out.println("The out put is : " + net.recognise(e.getTrainingSet().getTrainingSet().get(0)));
				
				/*ImageMatrix imgMat = ControlHandler.getPrimActions().getFocusedImage();
				List<ImageMatrix> list = new ArrayList<ImageMatrix>();
				list.add(imgMat);
				
				Entry e = new Entry("test",list,10,10);
				e.downSample();
				char re = model.getNeuralNet().recognise(e.getTrainingSet().getTrainingSet().get(0));
				System.out.println("The Result is : " + re);
				
				/*System.out.println("Start button pressed");
				
				ImageMatrix imgMat = ControlHandler.getPrimActions().getFocusedImage();
				List<ImageMatrix> list = new ArrayList<ImageMatrix>();
				list.add(imgMat);
				int[] sampleDims = {10,10};
				
				CharacterSample cs = new CharacterSample(list,sampleDims);
				List<SampleData> samples = new ArrayList<SampleData>();
				samples = cs.sampleConverter();
				
				RunOCR nn = new RunOCR(samples,model.getNeuralNet());
				nn.run();
				
				System.out.println("The Output : " + nn.getOutput());*/
			}
			
		};
		view.getToolPanel().getOCRStartBtn().addActionListener(actionStart);
	}
	
	/**
	 * Configure the structure, training etc of the neural network
	 */
	private void configureAction(){
		
		actionConfig = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int reply = JOptionPane.showConfirmDialog(view.getFrame(), "Do you need to create a training set?",
						"Step 1", JOptionPane.YES_NO_OPTION);
				if(reply == JOptionPane.YES_OPTION){
					
					final JFrame frame = new JFrame("Create Training Set");
					frame.setBounds(300,100,270,390);
					
					TrainingDialog td = new TrainingDialog(frame);
					td.init();
					
					TrainingDialogController tdc = new TrainingDialogController(td);
					tdc.control();
					
					WindowListener closedState = new WindowListener(){

						@Override
						public void windowActivated(WindowEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void windowClosed(WindowEvent arg0) {
							NetConfigDialog ncd = new NetConfigDialog(new JFrame());
							ncd.init();
							
							NetConfigController ncc = new NetConfigController(ncd,model);
							ncc.control();
						}

						@Override
						public void windowClosing(WindowEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void windowDeactivated(WindowEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void windowDeiconified(WindowEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void windowIconified(WindowEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void windowOpened(WindowEvent arg0) {
							// TODO Auto-generated method stub
							
						}
						
					};
					frame.addWindowListener(closedState);
					
				}
				else{
					NetConfigDialog ncd = new NetConfigDialog(new JFrame());
					ncd.init();
					
					NetConfigController ncc = new NetConfigController(ncd,model);
					ncc.control();
				}
			}
		};
		view.getToolPanel().getConfigureBtn().addActionListener(actionConfig);
	}
	
	private void loadEngineAction(){
		
		actionLoadEngine = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				NeuralNets net = ControlHandler.getPrimActions().neuralNetChooser();
				if(net != null){
					model.setNetModel(net);
					view.getToolPanel().getOCRStartBtn().setEnabled(true);
					view.getToolPanel().getActiveNetLbl().setText("Loaded : " + net.getName());
					System.out.println(model.getNeuralNet().getName());
				}
				else{
					JOptionPane.showMessageDialog(view.getFrame(), "Error! There seems to be a problem with this Nueral Network. Please try a different network.");
				}
			}
			
		};
		view.getToolPanel().getLoadBtn().addActionListener(actionLoadEngine);
	}
}
