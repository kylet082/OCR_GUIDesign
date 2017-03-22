package kgt.dev.ocr_gui.neuralnet;

import kgt.dev.ocr_gui.createSets.SampleData;
import kgt.dev.ocr_gui.createSets.TrainingSet;

public abstract class NeuralNets {
	
	private TrainingSet ts;
	private int width,height;
	
	public NeuralNets(TrainingSet newTs) {
		this.ts = newTs;
	}
	
	public NeuralNets(int width,int height) {
		this.width = width;
		this.height = height;
	}
	
	public abstract void train();
	
	public abstract char recognise(SampleData test);

}
