package kgt.dev.ocr_gui.utilities;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import kgt.dev.ocr_gui.controller.ControlHandler;

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
	
	/**
	 * 
	 * @param imgMat
	 * @param value
	 * @return
	 */
	public static Mat contrastEqual(Mat imgMat){

		Mat dst = new Mat(imgMat.rows(),imgMat.cols(),imgMat.type());
		Imgproc.cvtColor(imgMat,dst,Imgproc.COLOR_BGR2GRAY);
		Imgproc.equalizeHist(dst, dst);
		
		return dst;
	}
	
	/**
	 * 
	 * @param imgMat
	 * @param value
	 * @return
	 */
	public static Mat setContrast(Mat imgMat,int value){
		
		Mat dst = new Mat(imgMat.rows(),imgMat.cols(),imgMat.type());
		imgMat.convertTo(dst,-1, 10d * value/100);
		
		return dst;
	}
	
	/**
	 * 
	 * @param imgMat
	 * @param value
	 * @return
	 */
	public static Mat setThreshhold(Mat imgMat, int value){
		Mat m = new Mat();
		Imgproc.threshold(imgMat, m, value, 255, Imgproc.THRESH_BINARY);
		
		return m;
	}
	
	public static Mat setGaussianBlur(Mat imgMat,int val){
		
		Mat dest = new Mat();
		Imgproc.GaussianBlur(imgMat, dest,new Size(val,val), 0);
		
		return dest;		
	}
	
	/**
	 * Dectect lines using hough line transformation.
	 * 
	 * @param imgMat
	 * @return
	 */
	public static Mat houghLine(Mat imgMat){

		Mat canny = new Mat();
		Mat colorCanny = new Mat();
		
		Imgproc.Canny(imgMat, canny, 100, 240);
		Imgproc.cvtColor(canny, colorCanny, Imgproc.COLOR_GRAY2BGR);
		
		for(int x = 1;x < 10;x++){
			Mat lines = new Mat();
			Imgproc.HoughLines(canny, lines, 2, Math.PI/180, 200);
			
			double[] data;
			double rho,theta;
			
			Point pt1 = new Point();
			Point pt2 = new Point();
			
			double a, b;
			double x0, y0;
			
			for (int i = 0; i < lines.cols(); i++) {
		         data = lines.get(0, i);
		       
		         rho = data[0];
		         theta = data[1];
		         
		         a = Math.cos(theta);
		         b = Math.sin(theta);
		         x0 = a*rho;
		         y0 = b*rho;
		         
		         pt1.x = Math.round(x0 + 1000*(-b));
		         pt1.y = Math.round(y0 + 1000*(a));
		         pt2.x = Math.round(x0 - 1000*(-b));
		         pt2.y = Math.round(y0 - 1000 *(a));
		         
		         Imgproc.line(colorCanny, pt1, pt2, new Scalar(0, 0, 255), 3);
			}
		}
			
		return colorCanny;
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
