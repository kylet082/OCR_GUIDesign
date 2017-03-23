package kgt.dev.ocr_gui.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
}
