package kgt.dev.ocr_gui.model;

import kgt.dev.ocr_gui.neuralnet.NeuralNets;

public class ModelHandler {
	
	private OpenImages openImages;
	private NeuralNetworkModel netModel;
	private TrainingSet trainingSet = null;
	private String projectName;
	
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param newProjName - the new project name
	 */
	public ModelHandler(String newProjName){
		this.projectName = newProjName;
	}
	
	/**
	 * initialize the Model handler
	 */
	public void init(){
		openImages = new OpenImages();
		netModel = new NeuralNetworkModel(null);
	}
	
	/**
	 * @return - the OpenImages object of the opened images
	 */
	public OpenImages getOpenImages(){
		return openImages;
	}
	
	/**
	 * @return - current Neural Network
	 */
	public NeuralNets getNeuralNet(){
		return netModel.getNeuralNet();
	}
	
	/**
	 * @param som
	 */
	public void setNetModel(NeuralNets net){
		this.netModel.setNeuralNet(net);;
	}
	
	/**
	 * @param ts - training set
	 */
	public void setNetTrainingSet(TrainingSet ts){
		this.trainingSet = ts;
	}
	
	/**
	 * @return - training set
	 */
	public TrainingSet getTrainingSet(){
		return this.trainingSet;
	}
}
