package kgt.dev.ocr_gui.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import kgt.dev.ocr_gui.model.training.SampleData;
import kgt.dev.ocr_gui.model.training.TrainingSet;

public class ExportToCSV {

	private ExportToCSV(){
		
	}
	
	public static void generateCSV(TrainingSet ts, File f) throws IOException{
		StringBuilder sb = new StringBuilder();
		BufferedWriter br = new BufferedWriter(new FileWriter(f));
		
		for(int x = 0; x < ts.getTrainingSet().size(); x++){
			
			SampleData sd = ts.getTrainingSet().get(x);
			int[][] n = getNormilizedData(sd);
			
			for(int z = 0; z < n.length;z++){
				for(int i = 0; i < n[0].length; i++){
					sb.append(Integer.toString(n[z][i]));
					sb.append(',');
				}
			}
			sb.append('\n');
			br.write(sb.toString());
		}
		br.close();
		
	}
	
	public static int[][] getNormilizedData(SampleData d){
		final int[][] norm = new int[d.getConvertedMat().length][d.getConvertedMat()[0].length];
		
		for(int x = 0; x < norm.length;x++){
			for(int y = 0; y < norm[0].length;y++){
				if(d.getConvertedMat()[x][y] == 0){
					norm[x][y] = 0;
				}else{
					norm[x][y] = 1;
				}
			}
		}
		return norm;
	}
}
