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
	 public DocumentAnalysis(Mat newMat, String name){
		this.mat = newMat; 
		this.docName = name;
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
				 binDim[0] = x+2;
				 lineBins.add(binDim);
			 }
			 last = histData[x];
		 }
		 
		 //finds the end point of the text line along it's Y_Axis
		 int step = 0;
		 for(y = 0; y < histData.length;y++){
			 
			 if(histData[y] == 0 && last > 0.0){
				 lineBins.get(y)[2] = y + 2;
				 step++;
			 }
			 last = histData[y];
		 }
		 
		 //Finds end point and the start point along the X_Axis
		 for(int q = 0; q < lineBins.size(); q++){
			 int[] bins = lineBins.get(q);
			 boolean check;
			 int temp;
			 int right_X = this.dst.rows();
			 int left_X = 0;
			 
			 for(y = bins[0]; y < bins[2];y++){
				 check = false;
				 
				 //starting point
				 for(x = 0; x < this.dst.cols(); x++){
					 
					 double[] index = this.dst.get(y, x);
					 if(index[0] == 0.0){
						 
						 temp = x;
						 if(left_X < temp){
							 left_X = temp;
							 lineBins.get(q)[1] = left_X-2;
						 }
						 check = true;
					 }
				 }
				 
				 //ending point
				 for(y = bins[0]; y < bins[2]; y++){
					 check = false;
					 
					 for(x = this.dst.cols() - 1; x > 0; x--){
						 
						 double[] index = this.dst.get(y,x);
						 if(index[0] == 0.0){
							 
							 temp = x;
							 if(right_X > temp){
								 right_X = temp;
								 lineBins.get(q)[3] = right_X + 2;
							 }
							 check = true;
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
	 
	 /**
	  * @return
	  */
	 public String getDocName(){
		 return this.docName;
	 }
}
