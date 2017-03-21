package kgt.dev.ocr_gui.createSets;

import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import kgt.dev.ocr_gui.model.ImageMatrix;

public class Entry {
	
	private List<ImageMatrix> imgList;
	
	private Mat imgMat, sampleMat;
	
	protected int[] dwnSampleTop, dwnSampleBtm,
				  dwnSampleLeft,dwnSampleRight;
	
	private int sampleWidth, sampleHeight;
	
	private TrainingSet ts;
	
	private String name;
	
	/**
	 * CONSTRUCTOR
	 *  
	 * @param imageList
	 * @param setSampleWidth
	 * @param setSampleHeight
	 */
	public Entry(String setName,List<ImageMatrix> imageList, int setSampleWidth, int setSampleHeight){
		this.ts = new TrainingSet(setName);
		this.imgList = imageList;
		this.sampleWidth = setSampleWidth;
		this.sampleHeight = setSampleHeight;
	}
	
	/**
	 * Called to down-sample the images and create training
	 * data of the image and add it to a training Data set. 
	 * 
	 * @param w
	 * @param h
	 */
	public void downSample(){
		
		//iterate through the image list and normalize the data
		for(ImageMatrix imgMatrix : imgList){
			this.name = imgMatrix.getName();
			this.imgMat = imgMatrix.getImgMatrix();
			
			int height = imgMat.rows(); 
			int width = imgMat.cols();
			
			//convert to Gray and apply Threshold
			
			//Imgproc.cvtColor(imgMat, imgMat, Imgproc.COLOR_RGB2GRAY);
			Imgproc.threshold(imgMat, imgMat, 123, 255, Imgproc.THRESH_BINARY);
			
			//System.out.println("Mat Dims: " + width + " " + height);
			findBounds(width,height);
		
			//crop the matrix to new bounds
			sampleMat = imgMat.submat(this.dwnSampleTop[1], this.dwnSampleBtm[1], this.dwnSampleLeft[0], this.dwnSampleRight[0]);
		
			//resize the image to the sample size
			Imgproc.resize(sampleMat, sampleMat, new Size(this.sampleWidth,this.sampleHeight));
			
			//Add sampled data to the training data list.
			SampleData sampleData = new SampleData(this.name.charAt(0),sampleMat);
			ts.add(sampleData);
		}
	}
	
	/**
	 * Find the white space to crop the image bounds
	 * 
	 * @param w
	 * @param h
	 */
	private void findBounds(int w, int h) {
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
				double[] index = this.imgMat.get(x, y);
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
				double[] index = this.imgMat.get(x, y);
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
				double[] index = this.imgMat.get(y, x);
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
				double[] index = this.imgMat.get(y, x);
				if(index[0] == 0 && flag){
					flag = false;
					this.dwnSampleRight[0]=x;
					this.dwnSampleRight[1]=y;
				}
			}
		}
	}
	
	/**
	 * @return training set list
	 */
	public TrainingSet getTrainingSet(){
		return ts;
	}
	
	/**
	 * @return list of images loaded
	 */
	public List<ImageMatrix> getMatrixList(){
		return imgList;
	}
	
}
