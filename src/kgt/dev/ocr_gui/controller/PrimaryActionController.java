package kgt.dev.ocr_gui.controller;

import java.awt.Color;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import kgt.dev.ocr_gui.model.ImageMatrix;
import kgt.dev.ocr_gui.model.ModelHandler;
import kgt.dev.ocr_gui.model.OpenImages;
import kgt.dev.ocr_gui.model.TrainingSet;
import kgt.dev.ocr_gui.neuralnet.createSets.ZipReader;
import kgt.dev.ocr_gui.utilities.ImageProc;
import kgt.dev.ocr_gui.utilities.SerializeObj;
import kgt.dev.ocr_gui.view.ViewHandler;

public class PrimaryActionController {

	private ViewHandler view;
	
	private ModelHandler model;
	
	private ImageListController listController;
	
	private JFileChooser fc;
	
	private File file;
	
	private Mat openMat;
	
	private OpenImages openImages;
	
	private ImageMatrix focusedImg;
	
	private JLabel centerImg;
	
	private ZipReader zipReader;
	
	
	public static final int OPEN = 0,
							SAVE = 1;
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param newView current view handler
	 * @param newModel current model handler
	 */
	public PrimaryActionController(ViewHandler newView,ModelHandler newModel){
		this.view = newView;
		this.model = newModel;
		this.openImages = model.getOpenImages();
		listController = new ImageListController(newView,newModel);
	}
	
	/**
	 * 
	 */
	public void openProject(){
		
	}
	
	/**
	 * Opening the images and adding to the GUI 
	 * 
	 * @param action
	 */
	public void ImgChooser(int action){
		fc = new JFileChooser();
		if(action == OPEN){
			if(fc.showOpenDialog(view.getFrame()) == JFileChooser.APPROVE_OPTION){
				file = fc.getSelectedFile();
				openMat = Imgcodecs.imread(file.getAbsolutePath()); 
				openImageAction(openMat,file);
			}
		}else if(action == SAVE){
			
		}
	}
	
	/**
	 * @return - sample data obj
	 */
	public TrainingSet trainingSetChooser(){
		TrainingSet ts = null;
		fc = new JFileChooser("./TrainingSets");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		"Serialized Samples","ser");
		fc.setFileFilter(filter);
		if(fc.showOpenDialog(view.getFrame()) == JFileChooser.APPROVE_OPTION){
			file = fc.getSelectedFile();
			ts=(TrainingSet)SerializeObj.load(file);
		}
		
		return ts;
	}
	
	/**
	 * Opening Zip files
	 * 
	 * @param frame
	 * @return
	 */
	public File zipChooser(JFrame frame){
		file = null;
		fc = new JFileChooser();
		if(fc.showOpenDialog( frame) == JFileChooser.APPROVE_OPTION){
			file = fc.getSelectedFile();
		}
		return file;
	}
	
	public void upDateImgList(){
		//clear the viewport in the scrollpane
		view.getImgListPane().getImgListViewPort().removeAll();
		
		for(int x = 0; x < model.getOpenImages().getImageList().size(); x++ ){
			ImageMatrix tmp  = model.getOpenImages().getImageList().get(x);
			view.getImgListPane().addOpenedImgs(tmp);
			listController.imgListListener(tmp);
			view.getImgListPane().getImgListViewPort().revalidate();
			view.getImgListPane().getImgListViewPort().repaint();
		}
	}
	
	/**
	 * Adds the opened image to the open project and adds
	 * the thumb image to the Image list.
	 * 
	 * @param matrix - the new image matrix
	 * @param file - the file details associated with the image
	 */
	public void openImageAction(Mat matrix, File file){
		
		ImageMatrix imgMat = new ImageMatrix(matrix,file);
		//imgMat.previewMatrixImg();
		openImages.addImage(imgMat);
		
		//add the thumb images to the list
		if(!openImages.getImageList().isEmpty()){
			upDateImgList();
		}
		else{
			System.out.println("Something went wrong loading the image icon");
		}
	}
	
	/**
	 * Displays the image to the center panel
	 * 
	 * @param imgMat - ImageMatrix to display
	 */
	public void displaySelectedImage(Mat imgMat){
		view.getCenterPanel().getView().removeAll();
		Image displayedImg = ImageProc.cvtMatToBufferImg(imgMat);
		centerImg = new JLabel(new ImageIcon(displayedImg));
		view.getCenterPanel().getView().add(centerImg);
		view.getCenterPanel().getView().setBackground(new Color(44,62,80));
		
		view.getCenterPanel().getView().revalidate();
	}
	
	public void removeImageFromList(){
		
	}
	
	/**
	 * @param focusedImage - set the image to manipulate
	 */
	public void setFocusedImage(ImageMatrix focusedImage){
		this.focusedImg =  focusedImage;
	}
	
	/**
	 * @return - the currently displayed image to manipulate
	 */
	public ImageMatrix getFocusedImage(){
		return focusedImg;
	}	
	
	/**
	 * @return zip file reader
	 */
	public ZipReader getZipReader(){
		return zipReader;
	}
	
}
