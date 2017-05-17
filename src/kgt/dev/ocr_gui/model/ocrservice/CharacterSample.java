package kgt.dev.ocr_gui.model.ocrservice;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import kgt.dev.ocr_gui.model.ImageMatrix;
import kgt.dev.ocr_gui.neuralnet.createSets.SampleData;

public class CharacterSample {
	
	private boolean[][] biPolarMat;
	
	private List<ImageMatrix> inputChar;
	
	private List<SampleData> sampledList;
	
	/**
	 * Holds the bounding dimensions
	 */
	private int[] sampleDim;
	private int[] dwnSampleTop;
	private int[] dwnSampleBtm;
	private int[] dwnSampleLeft;
	private int[] dwnSampleRight;
	
	//CONSTRUCTOR
	public CharacterSample(List<ImageMatrix> newChars, int[] sampleDimensions){
		this.inputChar = newChars;
		this.sampleDim = sampleDimensions;
		this.sampledList = new ArrayList<SampleData>();
	}
	
	/**
	 * converts and samples down the input characters into the same size as the 
	 * training set samples. 
	 * 
	 * @return - sampled list of characters
	 */
	public List<SampleData>  sampleConverter(){
		
		for(int x = 0; x < this.inputChar.size(); x++){
			Mat img = inputChar.get(x).getImgMatrix();
			
			Mat resized = new Mat();
			
			int width = img.cols();
			int height = img.rows();
			
			Imgproc.threshold(img,img,123,255,Imgproc.THRESH_BINARY);
			
			findBounds(img,width,height);
			
			Mat sampled = img.submat(this.dwnSampleTop[1], this.dwnSampleBtm[1],
					this.dwnSampleLeft[0], this.dwnSampleRight[0]); 
			
			//get the training set sample size ref.
			Imgproc.resize(sampled, resized, new Size(this.sampleDim[0],this.sampleDim[1]));
			
			SampleData inputSamples = new SampleData(' ',resized);
			
			sampledList.add(inputSamples);
		}
		
		return sampledList;
	}
	
	/**
	 * Remove the white space around character images
	 * 
	 * @param input - mat image working on
	 * @param w - width of mat
	 * @param h - height of mat
	 */
	private void findBounds(Mat input, int w, int h) {
		boolean flag = true;
		
		//place the found x and y coo-ordinates
		this.dwnSampleTop = new int[2];
		this.dwnSampleBtm = new int[2];
		this.dwnSampleLeft = new int[2];
		this.dwnSampleRight = new int[2];
		
		int x,y;
		
		//top of image
		for(x = 0;x < h; x++){
			for(y = 0; y < w;y++){
				double[] index = input.get(x, y);
				if(index[0] == 0 && flag){
					flag = false;
					this.dwnSampleTop[0] = y;
					this.dwnSampleTop[1] = x;
				}
			}
		}
		
		flag=true;
		
		//bottom of image
		for(x = h -1; x >= 0;x--){
			for(y = 0; y < w;y++){
				double[] index = input.get(x, y);
				if(index[0] == 0 && flag){
					flag = false;
					this.dwnSampleBtm[0] = y;
					this.dwnSampleBtm[1] = x;
				}
			}
		}
		
		flag=true;
		
		//left of the image
		for(x = 0; x < w; x++){
			//System.out.println(x);
			for(y = 0; y < h; y++){
				//System.out.println(y);
				double[] index = input.get(y, x);
				if(index[0] == 0 && flag){
					flag = false;
					this.dwnSampleLeft[0] = x;
					this.dwnSampleLeft[1] = y;
				}
			}
		}
		
		flag = true;
		
		//right of the image
		for(x = w-1; x >= 0;x--){
			for(y = 0; y<h; y++){
				//System.out.println(y);
				double[] index = input.get(y, x);
				if(index[0] == 0 && flag){
					flag = false;
					this.dwnSampleRight[0]=x;
					this.dwnSampleRight[1]=y;
				}
			}
		}
	}//end of downSample
	
}
