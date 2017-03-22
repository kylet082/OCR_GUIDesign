package kgt.dev.ocr_gui.model;

import kgt.dev.ocr_gui.neuralnet.NeuralNets;

public class NeuralNetworkModel {
	
	private NeuralNets net;
	
	public NeuralNetworkModel(NeuralNets newNet){
		this.net = newNet;
	}
	
	public boolean isTrained(){
		return this.net.isTrained();
	}
	
	public void setNeuralNet(NeuralNets newNet){
		this.net = newNet;
	}
	
	public NeuralNets getNeuralNet() throws NullPointerException{
		return this.net;
	}
}
