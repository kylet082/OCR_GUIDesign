package kgt.dev.ocr_gui.utilities;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class ImageProc {
	
	//private Mat imageMat,clonedImageMat;
	
	private static BufferedImage imageBuff;

	public static final int 
						AXIS_WIDTH = 1,
						AXIS_HEIGHT = 2;
	
	/**
	 * CONSTRUCTOR
	 */
	private ImageProc(){
		throw new AssertionError();
	}
	/**
	 * Convert the Matrix image into a Buffered Image
	 * 
	 * @param imageMat - the image matrix to convert
	 * @return - resulting BufferedImage
	 */
	public static BufferedImage cvtMatToBufferImg(Mat imageMat){
		//determine # of channels
		int type = BufferedImage.TYPE_BYTE_GRAY;
		if(imageMat.channels() > 1){
			type = BufferedImage.TYPE_3BYTE_BGR;
		}
		int bufferSize = imageMat.channels() * imageMat.cols() * imageMat.rows();
		byte[] bytes = new byte[bufferSize];
		//return all the pixels
		imageMat.get(0, 0, bytes);
		imageBuff = new BufferedImage(imageMat.cols(),imageMat.rows(),type);
		byte[] targPixels = ((DataBufferByte) imageBuff.getRaster().getDataBuffer()).getData(); 
		System.arraycopy(bytes,0,targPixels,0,bytes.length);
		
		return imageBuff;
	}
	
	/**
	 * Resize the image while keeping the aspect ratio
	 * 
	 * @param imgMat - image matrix 
	 * @param size - size of the image matrix (rows and cols)
	 * @param newVal - the new side size
	 * @param axis - the axis to apply the new size value to 
	 * @return - re-calculated image matrix 
	 */
	public static Mat resizeImgAspect(Mat imgMat,Size size,int newVal,int axis){
		Mat newMat = imgMat;
		BigDecimal dWidth, dHeight,dAxis, dResult;
		int result;
		double newAxis;
		dWidth = new BigDecimal(size.width);
		dHeight = new BigDecimal(size.height);
		dAxis = new BigDecimal(newVal);
		
		if(axis == AXIS_HEIGHT){
			dResult = dWidth.divide(dHeight,3,RoundingMode.CEILING);
			dResult = dResult.multiply(dAxis);
			
			newAxis = Double.parseDouble(dResult.toString());
			result = (int)newAxis;
			
			imgMat = resizeImageMat(imgMat,new Size(result,newVal));
		}
		else if(axis == AXIS_WIDTH){
			dResult  = dHeight.divide(dWidth,3,RoundingMode.CEILING);
			dResult = dResult.multiply(dAxis);
			
			newAxis = Double.parseDouble(dResult.toString());
			result = (int)newAxis;
			
			imgMat = resizeImageMat(imgMat,new Size(newVal,result));
		}
		
		return newMat;
	}
	
	/**
	 * 
	 * @param imgMat - image matrix to resize
	 * @param sizeTo -The dimensions to resize to
	 * 
	 * @return  -resized image matrix
	 */
	public static Mat resizeImageMat(Mat imgMat,Size sizeTo){
		Size size = imgMat.size();
		Size newSize = new Size(sizeTo.width,sizeTo.height);
		
		if(size.width != sizeTo.width || size.height != sizeTo.height){
			Imgproc.resize(imgMat, imgMat, newSize);
		}
		
		return imgMat;
	}
	
	public static Mat setContrast(Mat imgMat,int value){
		
		Mat dst = new Mat(imgMat.rows(),imgMat.cols(),imgMat.type());
		imgMat.convertTo(dst,-1, 10d * value/100);
		
		return dst;
	}
	
	/**
	 * @param imageMat image matrix to clone
	 * @return - the cloned image matrix
	 */
	public static Mat cloneImageMat(Mat imageMat){
		Mat clonedImageMat = new Mat();
		clonedImageMat = imageMat; 
		return clonedImageMat; 
	}
}
