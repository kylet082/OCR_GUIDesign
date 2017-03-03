package kgt.dev.ocr_gui.model;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.opencv.core.Mat;
import org.opencv.core.Size;

import kgt.dev.ocr_gui.utilities.ImageProc;

public class ImageMatrix {

	private Mat imgMat;
	
	private File file;
	
	private boolean isSelected;
	
	private Image thumbImg;
	
	private Size thumbImgSize;
	
	private JPanel imgListItem;
	
	
	/**
	 * CONSTRUCTOR
	 *  
	 * @param newMat image matrix
	 * @param newFile file
	 */
	public ImageMatrix(Mat newMat,File newFile){
		this.file = newFile;
		this.imgMat = newMat;
		this.isSelected = false;
	}
	
	/**
	 * Create the thumb image for the Image list panel
	 * 
	 * @param newWidth - the new width to scale the image to
	 * @return - the resized BufferedImage
	 */
	public Image createImageIcon(int newWidth){
		
		Mat thumbMatImg = new Mat();
		thumbMatImg = ImageProc.resizeImgAspect(imgMat.clone(), imgMat.size(), newWidth, ImageProc.AXIS_WIDTH);
		thumbImg = ImageProc.cvtMatToBufferImg(thumbMatImg);
		
		return thumbImg;
	}
	
	/**
	 * Preview the buffered image in 
	 * a new frame
	 * 
	 * @param img - The Image to preview
	 */
	public void previewBufferImg(Image img){
		
		ImageIcon icon = new ImageIcon(img);
	    JFrame frame = new JFrame();
	    frame.setLayout(new FlowLayout());        
	    frame.setSize(img.getWidth(null)+50, img.getHeight(null)+50);     
	    JLabel lbl=new JLabel();
	    lbl.setIcon(icon);
	    frame.add(lbl);
	    frame.setTitle("Preview of : " + this.getName() );
	    frame.setVisible(true);
	}
	
	/**
	 * Create a thumb image of the image matrix
	 * 
	 * @return - the dimensions of the thumb image
	 */
	public Size getThumbImgSize(){
		thumbImgSize = new Size(thumbImg.getWidth(null),thumbImg.getHeight(null));
		return thumbImgSize;
	}
	
	/**
	 * @param imgItem - JPanel and added components in the image list panel 
	 */
	public void setImgListItem(JPanel imgItem){
		this.imgListItem = imgItem;
	}
	
	/**
	 * @return JPanel and components
	 */
	public JPanel getImgListItem(){
		return imgListItem;
	}
	
	/**
	 * Preview the image matrix in a new frame.
	 */
	public void previewMatrixImg(){
		Image preImg = ImageProc.cvtMatToBufferImg(imgMat);
		previewBufferImg(preImg);
	}
	
	/**
	 * flag if the image item is selected
	 * 
	 * @return - selected flag
	 */
	public boolean getIsSelected(){
		return isSelected;
	}
	
	/**
	 * @return - matrix image
	 */
	public Mat getImgMatrix(){
		return imgMat;
	}
	
	/**
	 * @return - the image file name
	 */
	public String getName(){
		return file.getName();
	}
	
	/**
	 * @param isSelect - set the selected image 
	 */
	public void setIsSelected(boolean isSelect) {
		this.isSelected = isSelect;	
	}
	
	/**
	 * @param m  -set the matrix
	 */
	public void setImgMat(Mat m){
		this.imgMat = m;
	}
}
