package kgt.dev.ocr_gui.controller;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import kgt.dev.ocr_gui.model.ImageMatrix;
import kgt.dev.ocr_gui.model.ImageProc;
import kgt.dev.ocr_gui.model.ModelHandler;
import kgt.dev.ocr_gui.model.OpenImages;
import kgt.dev.ocr_gui.view.ViewHandler;

public class PrimaryActionController {

	private ViewHandler view;
	private ModelHandler model;
	private ImageListController listController;
	private JFileChooser fc;
	private File file;
	private Mat openMat;
	private OpenImages openImages;
	private ImageMatrix focusedImg = null;
	
	public static final int OPEN = 0,
							SAVE = 1;
	
	/**
	 * 
	 * @param newView
	 * @param newModel
	 */
	public PrimaryActionController(ViewHandler newView,ModelHandler newModel){
		this.view = newView;
		this.model = newModel;
		this.openImages = model.getOpenImages();
		listController = new ImageListController(newView,newModel);
	}
	
	public void openProject(){
		
	}
	
	/**
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
			//clear the viewport in the scrollpane
			view.getImgListPane().getImgListViewPort().removeAll();
			
			for(int x = 0; x < model.getOpenImages().getImageList().size(); x++ ){
				ImageMatrix tmp  = model.getOpenImages().getImageList().get(x);
				view.getImgListPane().addOpenedImgs(tmp);
				listController.imgListListener(tmp);
			}
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
		
		Image displayedImg = ImageProc.cvtMatToBufferImg(imgMat);
		JLabel centerImg = new JLabel(new ImageIcon(displayedImg));
		view.getCenterPanel().getCenterViewPort().add(centerImg);
		
	}
}
