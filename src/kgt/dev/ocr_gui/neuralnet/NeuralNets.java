package kgt.dev.ocr_gui.neuralnet;

import kgt.dev.ocr_gui.neuralnet.createSets.SampleData;
import kgt.dev.ocr_gui.neuralnet.createSets.TrainingSet;

public abstract class NeuralNets {
	
	/**
	 * The training set list
	 */
	protected TrainingSet trainSet;
	
	/**
	 * Test if the network has been trained
	 */
	private boolean isTrained;
	
	/**
	 * Param for the training set matrix
	 */
	protected int SAMPLE_WIDTH,SAMPLE_HEIGHT,SET_SIZE;
	
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
	public NeuralNets(int width,int height) {
		this.SAMPLE_WIDTH = width;
		this.SAMPLE_HEIGHT = height;
	}
	
	/**
	 * Train method for the neural networks
	 */
	public abstract void train();
	
	/**
	 * @param test sample data to recoginize
	 * @return
	 */
	public abstract char recognise(SampleData test);
	
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
