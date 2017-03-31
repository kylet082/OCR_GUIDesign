package kgt.dev.ocr_gui.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import org.opencv.core.Size;

import kgt.dev.ocr_gui.model.ImageMatrix;
import kgt.dev.ocr_gui.model.ModelHandler;
import kgt.dev.ocr_gui.utilities.ImageProc;
import kgt.dev.ocr_gui.view.DisplayImage;
import kgt.dev.ocr_gui.view.ViewHandler;

public class ImageListController {

	private ViewHandler view;
	private ModelHandler model;
	private ImageMatrix focusImg = null;
	private List<ImageMatrix> imageList;
	
	private ActionListener actionOpen, actionCopy, actionSave;
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param newView
	 * @param newModel
	 */
	public ImageListController(ViewHandler newView, ModelHandler newModel){
		this.view = newView;
		this.model = newModel;
	}
	
	/**
	 * Initialize the controller
	 */
	public void init(){
		addImgToViewBtn();
		clearImgFromView();
		openAction();
	}
	
	/**
	 * 
	 * @param imgMat - image matrix to add listeners to thumb image 
	 * 				   in the open image list.
	 */
	public void imgListListener(ImageMatrix imgMat){
		
		Border focused = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 1, 0, 1),
				BorderFactory.createEtchedBorder(EtchedBorder.RAISED, new Color(0,20,77), new Color(0,66,255)));
		Border unfocused = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 1, 0, 1),
				BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		imgMat.getImgListItem().addMouseListener(new MouseAdapter(){
		@Override
			public void mouseClicked(MouseEvent e){
						//alternate the focus of the img items in the list
				    	if(focusImg == null){
							imgMat.setIsSelected(true);
							imgMat.getImgListItem().setBorder(focused);
							imgMat.getImgListItem().getComponent(0).setBackground(new Color(59,110,190,130));
							imgMat.getImgListItem().getComponent(1).setBackground(new Color(59,110,190,130));
							focusImg = imgMat;
						}else if(e.getComponent() == focusImg.getImgListItem()){
							focusImg.setIsSelected(false);
							focusImg.getImgListItem().setBorder(unfocused);
							focusImg.getImgListItem().getComponent(0).setBackground(new Color(241,241,241));
							focusImg.getImgListItem().getComponent(1).setBackground(new Color(241,241,241));
							focusImg = null;
						}
						else{
							focusImg.setIsSelected(false);
							focusImg.getImgListItem().setBorder(unfocused);
							focusImg.getImgListItem().getComponent(0).setBackground(new Color(241,241,241));
							focusImg.getImgListItem().getComponent(1).setBackground(new Color(241,241,241));
							
							imgMat.setIsSelected(true);
							imgMat.getImgListItem().setBorder(focused);
							imgMat.getImgListItem().getComponent(0).setBackground(new Color(59,110,190,130));
							imgMat.getImgListItem().getComponent(1).setBackground(new Color(59,110,190,130));
							focusImg = imgMat;
						}
			}
		});
	}
	
	/**
	 * Listener for the view button 
	 */
	public void addImgToViewBtn(){
		
		imageList = model.getOpenImages().getImageList();
		view.getImgListPane().getViewBtn().addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for(int x = 0; x < imageList.size();x++){
					
					ImageMatrix tmpImgMat = imageList.get(x);
					
					//Adds the selected image to the center panel viewport
					if(tmpImgMat.getIsSelected()){	
						
						ControlHandler.getPrimActions().setFocusedImage(tmpImgMat);
						double width = (double)(view.getCenterPanel().getWidth());
						double height = (double)(view.getCenterPanel().getHeight());
						ImageProc.resizeImgAspect(tmpImgMat.getImgMatrix(), new Size(width -410,height),view.getCenterPanel().getHeight(), ImageProc.AXIS_HEIGHT);
						Image img = ImageProc.cvtMatToBufferImg(tmpImgMat.getImgMatrix());
						//view.getCenterPanel().removeAll();
						DisplayImage display = (DisplayImage) view.getCenterPanel().getDisplayPanel();
						
						//view.getCenterPanel().getCenterViewPort().setPreferredSize(new Dimension(1000,700));
						display.setDisplayImage(img);
						display.update();
						
						//ControlHandler.getCenterController().btnMnemonic();
						//ControlHandler.getCenterController().zoomControl();
					}
				}
			}
		});
	}
	
	/**
	 * 
	 */
	public void clearImgFromView(){
		
		imageList = model.getOpenImages().getImageList();
		view.getImgListPane().getClearBtn().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				for(int x = 0; x < imageList.size();x++){
					ImageMatrix tmpImgMat = imageList.get(x);
					
					if(tmpImgMat.getIsSelected()){
						imageList.remove(x);
						ControlHandler.getPrimActions().upDateImgList();
						view.getImgListPane().revalidate();
					}
				}	
			}
		});
	}
	
	/**
	 * Open new image via the interface button
	 */
	public void openAction(){
		
		actionOpen = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ControlHandler.getPrimActions().ImgChooser(PrimaryActionController.OPEN);
			}
		};
		view.getImgListPane().getOpenBtn().addActionListener(actionOpen);
	}
	
	/**
	 * Copy the image and add it to the imag list
	 */
	public void copyAction(){
		actionCopy = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
			
		};
	}
	
	/**
	 * Save the current image
	 */
	public void saveAction(){
		actionSave = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		};
	}
}
