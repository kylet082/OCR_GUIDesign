package kgt.dev.ocr_gui.model;

import java.io.Serializable;

import kgt.dev.ocr_gui.neuralnet.NeuralNets;

public class NeuralNetworkModel implements Serializable {

	private static final long serialVersionUID = 7020102062297388859L;

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
