package kgt.dev.ocr_gui.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import kgt.dev.ocr_gui.model.TrainingSet;
import kgt.dev.ocr_gui.neuralnet.createSets.SampleData;

public class ExportToCSV {
	
	/**
	 * CONSTRUCTOR
	 */
	private ExportToCSV(){
	}
	
	/**
	 * Generates a CSV file of the Normalized Training data. Can be
	 * used to bench mark and test Neural networks in Encog WorkBench
	 * 
	 * @param ts - current training set
	 * @param f	- file to save the CSV file
	 * @throws IOException
	 */
	public static void generateCSV(TrainingSet ts, File f) throws IOException{
		
		BufferedWriter br = new BufferedWriter(new FileWriter(f));
		
		for(int x = 0; x < ts.getTrainingSet().size(); x++){
			
			StringBuilder sb = new StringBuilder();
			SampleData sd = ts.getTrainingSet().get(x);
			int[][] n = getNormilizedData(sd);
			sd.getSampler().print();
			System.out.println("");
			
			for(int z = 0; z < n.length;z++){
				for(int i = 0; i < n[0].length; i++){
					
					sb.append(Integer.toString(n[z][i]));
					sb.append(',');
				}
			}
			sb.append(sd.getSymbol());
		
			sb.append('\n');
			br.write(sb.toString());
		}
		br.close();
		
	}
	
	/**
	 * Normalizes the matrix data for use of Sigmoid Activation function
	 * 
	 * @param d - Sample data of a matrix
	 * @return
	 */
	private static int[][] getNormilizedData(SampleData d){
		final int[][] norm = new int[d.getConvertedMat().length][d.getConvertedMat()[0].length];
		
		for(int x = 0; x < norm.length;x++){
			for(int y = 0; y < norm[0].length;y++){
				if(d.getConvertedMat()[x][y] == 0){
					norm[x][y] = 1;
				}else{
					norm[x][y] = 0;
				}
			}
		}
		return norm;
	}
}
