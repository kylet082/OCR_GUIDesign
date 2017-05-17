package kgt.dev.ocr_gui.model.ocrservice;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataPair;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.som.SOM;
import org.encog.neural.som.training.clustercopy.SOMClusterCopyTraining;

import kgt.dev.ocr_gui.model.TrainingSet;
import kgt.dev.ocr_gui.neuralnet.createSets.SampleData;

public class OCR {
	
	private SOM net;
	private TrainingSet ts;
	private int SAMPLE_WIDTH, SAMPLE_HEIGHT;
	
	public OCR(TrainingSet newTs){
		this.ts = newTs;
		this.SAMPLE_WIDTH = ts.getTrainingSet().get(0).getWidth();
		this.SAMPLE_HEIGHT = ts.getTrainingSet().get(0).getHeight();
		
	}
	
	/**
	 * Map the output neurons with a character
	 * 
	 * @return
	 */
	public char[] mapNeurons(){
		
		final char map[] = new char[this.ts.getTrainingSet().size()];
		
		for(int a = 0; a < map.length; a++){
			map[a] = '?';
		}
		for(int i = 0; i < this.ts.getTrainingSet().size();i++){
			final MLData input = new BasicMLData(20 * 20);
			int index = 0;
			final SampleData ds = ts.getTrainingSet().get(i);
			for(int y = 0;y < ds.getWidth();y++){
				for(int x = 0; x < ds.getHeight();x++){
					
					if(ds.getBiPolarData(y, x)){
						input.setData(index++, 1.0);
					}else{
						input.setData(index++,-1.0);
					}
				}
				final int best = this.net.classify(input);
				map[best] = ds.getSymbol();
			}
		}
		
		return map;
	}
	
	/**
	 * Train the SOM
	 */
	public void TrainSOM(){
		
		final int inputNeuron = 20 * 20;
		final int outputNeuron = this.ts.getTrainingSet().size();
		
		final MLDataSet trainingSet = new BasicMLDataSet();
		
		for(int i = 0; i < this.ts.getTrainingSet().size(); i++){
			System.out.println("New sample data : " + i);
			final MLData item = new BasicMLData(inputNeuron);
			
			int index = 0;
			final SampleData ds = this.ts.getTrainingSet().get(i);
			System.out.println("Learning :" + ds.getSymbol());
			for(int y = 0; y < ds.getWidth();y++){
				for(int x = 0; x < ds.getHeight();x++){
					
					if(ds.getBiPolarData(y, x)){
						item.setData(index++, 1.0);
					}else{
						item.setData(index++,-1.0);
					}
				}
			}
			
			trainingSet.add(new BasicMLDataPair(item,null));
		}
		
		this.net = new SOM(inputNeuron,outputNeuron);
		this.net.reset();
		
		SOMClusterCopyTraining train = new SOMClusterCopyTraining(this.net, trainingSet);
		
		
		train.iteration();
		
		System.out.println("Training complete");
	}
	
	public void recognizes(SampleData sampleTest){
		
		if(this.net == null){
			return;
		}
		
		final MLData input = new BasicMLData(20 * 20);
		int index = 0;
		final SampleData ds = sampleTest;
		System.out.println(ds.getSymbol());
		for(int x = 0;x < ds.getWidth();x++){
			for(int y = 0; y < ds.getHeight();y++){
				if(ds.getBiPolarData(x, y)){
					
					input.setData(index++, 1.0);
				}else{
					input.setData(index++,-1.0);
				}
			}
		}
		final int best = this.net.classify(input);
		final char map[] = mapNeurons();
		System.out.println("Fired Neuron is " + map[best] + " Neuron Number :" + best);
	}
	
}
