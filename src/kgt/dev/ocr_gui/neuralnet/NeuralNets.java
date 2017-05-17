package kgt.dev.ocr_gui.neuralnet;

import java.io.Serializable;

import kgt.dev.ocr_gui.model.TrainingSet;
import kgt.dev.ocr_gui.neuralnet.createSets.SampleData;

public abstract class NeuralNets implements Serializable {
	private static final long serialVersionUID = -7152659687984580246L;

	/**
	 * The training set list
	 */
	protected TrainingSet trainSet;
	
	protected String name;
	
	/**
	 * Test if the network has been trained
	 */
	private boolean isTrained;
	
	/**
	 * Param for the training set matrix
	 */
	public int SAMPLE_WIDTH, SAMPLE_HEIGHT, SET_SIZE;
	
	/**
	 * @param newTs
	 */
	public NeuralNets(TrainingSet newTs) {
		this.trainSet = newTs;
	}
	
	/**
	 * @param width
	 * @param height
	 */
	public NeuralNets() {}
	
	/**
	 * Train method for the neural networks
	 */
	public abstract void train();
	
	/**
	 * @param test sample data to recoginize
	 * 
	 * @return
	 */
	public abstract char recognise(SampleData test);
	
	/**
	 * The neural network name
	 * 
	 * @return
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Set the neural networks name
	 * 
	 * @param newName
	 */
	public void setName(String newName){
		this.name = newName;
	}
	
	/**
	 * @param message -status message passing
	 */
	protected abstract void setMessage(String message);
	
	/**
	 * @return - the current status message
	 */
	public abstract String getMessage();

	public boolean isTrained() {
		return isTrained;
	}

	public void setTrained(boolean isTrained) {
		this.isTrained = isTrained;
	}
	
}
