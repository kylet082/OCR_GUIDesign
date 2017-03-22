package kgt.dev.ocr_gui.neuralnet.createSets;

import java.io.Serializable;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class SampleData implements Serializable{
	private static final long serialVersionUID = 6025945425763234368L;

	private transient Mat data;
	
	private int[][] convertedMat;
	
	private char symbol;

	private boolean[][] biPolarMatrix;
	
	private Sampler sampler;
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param newSymbol  character reference
	 * @param newData    the matrix data
	 */
	public SampleData(char newSymbol,Mat newData){
		this.symbol = newSymbol;
		this.data = newData;
		this.convertedMat = new int[newData.cols()][newData.rows()];
		this.sampler = new Sampler(this);
		convertedMat = convertMatToInt();
	}
	
	/**
	 * Create a bi-polar matrix based on pixel value,
	 * black or white.
	 * 
	 * @return Bi-Polar Matrix of the image
	 */
	public boolean[][] bipolarMatrix(){
		boolean[][] biPolMat = new boolean[this.data.cols()][this.data.rows()];
		for(int x =0; x < this.data.cols();x++){
			for(int y = 0; y < this.data.rows();y++){
				double[] index = data.get(x, y);
				
				if(index[0] > 0){
					biPolMat[x][y] = false;
				}else{
					biPolMat[x][y] = true;
				}
			}
		}
		return biPolMat;
	}
	
	/**
	 * Allows for the Mat to be converted so it can be serialized
	 * 
	 * @return
	 */
	private int[][] convertMatToInt(){
		
		for(int x = 0; x < this.data.cols();x++){
			for(int y = 0; y < this.data.rows();y++){
				double[] index = data.get(x,y);
				this.convertedMat[x][y] = (int)index[0];
			}
		}
		
		return this.convertedMat;
	}
	
	/**
	 * Allows for the conversion of the int[][] to converted
	 * back into a Mat after serialization
	 * 
	 * MUST BE CALLED IF TRAINING SET HAS BEEN DESERIALIZED!
	 */
	public void convertIntToMat(){
		this.data = new Mat(convertedMat.length,convertedMat[0].length,CvType.CV_8U);
		
		for(int x = 0; x < convertedMat.length;x++){
			for(int y = 0; y < convertedMat[0].length;y++){		
				double[] index = new double[1];
				index[0]= convertedMat[x][y];
				this.data.put(x, y, index);
			}
		}
	}
	
	/**
	 * @return the sampleData Matrix
	 */
	public Mat getMat(){
		return this.data;
	}
	/**
	 * @return the character/symbol of the data
	 */
	public char getSymbol() {
		return symbol;
	}
	
	/**
	 * @return raw pixel value data of matrix
	 */
	public Mat getSampleMat(){
		return data;
	}
	
	/**
	 * @return matrix height
	 */
	public int getHeight(){
		return data.cols();
	}
	/**
	 * @return matrix width
	 */
	public int getWidth(){
		return data.rows();
	}
	
	/**
	 * @return the sampleData sampler (to preview output)
	 */
	public Sampler getSampler(){
		return this.sampler;
	}
	
	/**
	 * @return the convert Matrix 
	 */
	public int[][] getConvertedMat(){
		return convertedMat;
	}
	
	public boolean getBiPolarData(int x,int y){
		return biPolarMatrix[x][y];
	}
}
