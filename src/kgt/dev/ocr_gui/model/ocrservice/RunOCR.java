package kgt.dev.ocr_gui.model.ocrservice;

import java.util.List;

import kgt.dev.ocr_gui.neuralnet.NeuralNets;
import kgt.dev.ocr_gui.neuralnet.createSets.SampleData;

public class RunOCR {
	
	private List<SampleData> characters;
	
	private NeuralNets neuralNet;
	
	private StringBuilder sb;
	
	char winner;
	
	/**
	 * For use of a list of many characters
	 * 
	 * @param listCharacters
	 */
	public RunOCR(List<SampleData> listCharacters, NeuralNets neuralNet){
		this.characters = listCharacters;
		this.neuralNet = neuralNet;
	}
	
	/**
	 * Run the OCR Process
	 */
	public void run(){
		
		if(this.neuralNet.isTrained()){
			
			sb = new StringBuilder();
			System.out.println("Running ocr....");
			for(int x = 0; x < characters.size(); x++){
				
				winner = neuralNet.recognise(characters.get(x));
				
				System.out.println("OUT : " + winner);
			}
			
			System.out.println("OCR processes is completed");
		}
		else{
			System.out.println("The Neural Network has on training set");
		}
		
	}
	
	/**
	 * Return the list of winning characters. 
	 * 
	 * @return
	 */
	public char getOutput(){
		return winner;
	}
	
	
}
