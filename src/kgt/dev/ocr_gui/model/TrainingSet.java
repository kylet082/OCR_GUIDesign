package kgt.dev.ocr_gui.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;

import kgt.dev.ocr_gui.neuralnet.createSets.SampleData;

public class TrainingSet implements Serializable{
	private static final long serialVersionUID = 4782378682627313991L;

	private List<SampleData> trainingSet;
	
	private String setName;
	
	/**
	 * CONSTRUCTOR 
	 * 
	 * @param newSetName  Name of the Font type
	 */
	public TrainingSet(String newSetName){
		this.setName = newSetName;
		trainingSet = new ArrayList<SampleData>(); 
	}
	
	/**
	 * @param data
	 */
	public void add(SampleData data){
		trainingSet.add(data);
	}
	
	/**
	 * @return the training sets data
	 */
	public List<SampleData> getTrainingSet(){
		return trainingSet;
	}
	
	/**
	 * @return the training set name
	 */
	public String getSetName(){
		return setName;
	}
	
	/**
	 * 
	 * @return the training sets sample width [0], and the sample height[1]
	 * 
	 */
	public int[] getSampleDimensions(){
		 
		Mat sample = this.getTrainingSet().get(0).getMat();
		
		int sampleWidth = 10;//sample.cols();
		int sampleHeight = 10;//sample.rows();
		
		final int[] dimensions = {sampleWidth,sampleHeight};
		
		return dimensions;
	}
}
