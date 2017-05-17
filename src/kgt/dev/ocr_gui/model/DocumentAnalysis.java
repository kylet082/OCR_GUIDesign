package kgt.dev.ocr_gui.model;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class DocumentAnalysis {
	
	 private Mat mat,dst;
	 
	 private String docName;
	 
	 private double[] histData;
	 
	 private List<int[]> lineBins;
	 
	 /**
	  * CONSTRUCTOR
	  * 
	  * @param newMat
	  */
	 public DocumentAnalysis(Mat newMat){
		this.mat = newMat;
	 }
	 
	 /**
	  * Calculate the pixel density along the y axis
	  */
	 public void calculateHist(){
		 
		 histData = new double[this.mat.rows()];
		 int count;
		 int total;
		 Mat colorCon = new Mat();
		 dst = new Mat();
		 
		 Imgproc.cvtColor(this.mat, colorCon,Imgproc.COLOR_RGB2GRAY);
		 Imgproc.threshold(colorCon, dst,123,255,Imgproc.THRESH_BINARY);
		 
		 for(int x = 0; x < dst.rows();x++){
			 count = 0;
			 for(int y = 0; y < dst.cols();y++){
				 
				 double[] index = dst.get(x, y);
				 if(index[0] == 0.0){
					 count++;
				 }
			 }
			 histData[x] = count;
		 }
	 }
	 
	 /**
	  * Calculate the start and end point of each word line along the Y-Axis
	  */
	 public void calcLineProjection(){
		
		 lineBins = new ArrayList<int[]>();
		 
		 double last = 0.0;
		 int x,y;
		 
		 //Finds the starting point of a text line along the Y-Axis
		 for(x= 0; x < histData.length; x++){
			 int[] binDim = new int[4];
			 
			 if(histData[x] > 0 && last == 0.0){
				 binDim[1] = x;
				 lineBins.add(binDim);
			 }
			 last = histData[x];
		 }
		 
		 //finds the end point of the text line along it's Y_Axis
		 int step = 0;
		 for(y = 0; y < histData.length;y++){
			 
			 if(histData[y] == 0 && last > 0.0){
				 lineBins.get(step)[3] = y + 2;
				 step++;
			 }
			 last = histData[y];
		 }
		 
		 //Finds end point and the start point along the X_Axis
		 for(int q = 0; q < lineBins.size(); q++){
			 int[] bins = lineBins.get(q);
			 
			 int temp;
			 int start_X = this.dst.cols();
			 int end_X = 0;
			 
			 for(y = bins[1]; y < bins[3];y++){
				 for(x = 0; x < this.dst.cols(); x++){
					 
					 double[] index = this.dst.get(y, x);
					 if(index[0] == 0.0){
						 temp = x;
						 if(temp < start_X){
							 start_X = temp;
							 lineBins.get(q)[0] = start_X;
						 }
					 }
				 }
			 }
				 
			 //ending point
			 for(y = bins[1]; y < bins[3]; y++){
				 for(x = this.dst.cols() - 1; x > 0; x--){
					 
					 double[] index = this.dst.get(y,x);
					 if(index[0] == 0.0){
						 temp = x;
						 if(temp > end_X){
							 end_X = temp;
							 lineBins.get(q)[2] = end_X;
						 }
					 }
				 }
			 }
		 }
	 }
	 
	 /**
	 * Returns the bounding box that surrounds each line of text.
	 * Returns a list of int[3]. where int[0] is the starting y co-ord,
	 * and int[2] is the end y co-ord. int[1] is the starting x co-ord, 
	 * int[3] is the end x co-ord. 
	 * 
	 * @return list of int[]
	 */
	 public List<int[]> getLineBinsDim(){
			return this.lineBins;
	 }
	
	 /**
	  * @return histogram density and plotting data
	  */
	 public double[] getHistogramData(){
		 return this.histData;
	 }
	 
}
