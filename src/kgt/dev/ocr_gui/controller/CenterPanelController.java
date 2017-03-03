package kgt.dev.ocr_gui.controller;

import java.awt.Image;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;


import kgt.dev.ocr_gui.model.ImageMatrix;
import kgt.dev.ocr_gui.model.ModelHandler;
import kgt.dev.ocr_gui.utilities.ImageProc;
import kgt.dev.ocr_gui.view.ViewHandler;

public class CenterPanelController {
	
	
	private ViewHandler view;
	private ModelHandler model;
	private boolean isShiftBtn;
	private boolean isZBtn;
	private int zoomFact = 2;
	private KeyListener btnMn;
	
	public CenterPanelController(ViewHandler newView, ModelHandler newModel){
		this.view = newView;
		this.model = newModel;
	}
	/**
	 * Initialize the controller
	 */
	public void init(){
		
		btnMnemonic();
		zoomControl();
	}
	
	/**
	 * Button Mnemonics for the center panel
	 */
	public void btnMnemonic(){
		System.out.println("Added qqq");
		isShiftBtn = false;
		isZBtn = false;
		btnMn = new KeyListener(){
			
			@Override
			public void keyPressed(KeyEvent e) {
				//for zoom control
				if(e.getKeyCode() == KeyEvent.VK_CONTROL){
					isShiftBtn = true;	
				}
				
				if(e.getKeyCode() == KeyEvent.VK_Z){
					isZBtn = true;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				//for zoom control
				if(e.getKeyCode() == KeyEvent.VK_CONTROL){
					isShiftBtn = false; 
				}
				
				if(e.getKeyCode() == KeyEvent.VK_Z){
					isZBtn = false;
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
		};
		
		view.getFrame().addKeyListener(btnMn);
		
		/*view.getFrame().addKeyListener(new KeyListener(){
			
			@Override
			public void keyPressed(KeyEvent e) {
				//for zoom control
				if(e.getKeyCode() == KeyEvent.VK_CONTROL){
					isShiftBtn = true;	
				}
				
				if(e.getKeyCode() == KeyEvent.VK_Z){
					isZBtn = true;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				//for zoom control
				if(e.getKeyCode() == KeyEvent.VK_CONTROL){
					isShiftBtn = false; 
				}
				
				if(e.getKeyCode() == KeyEvent.VK_Z){
					isZBtn = false;
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			});*/
		
	}
	
	/**
	 * the center panel image zooming
	 */
	public void zoomControl(){
		System.out.println("Added");
		view.getCenterPanel().getView().addMouseWheelListener(new MouseWheelListener(){

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				System.out.println("WORKING");
				int pip = e.getWheelRotation();
				if(pip < 0 && isShiftBtn && isZBtn){
					zoomInAction();
					System.out.println("Zooming in :UP " + pip);
				}
				else if(pip > 0 && isShiftBtn && isZBtn){
					zoomOutAction();
					System.out.println("Zooming out :DWN " + pip);
				}
			}
		});
	}
	
	public void zoomInAction(){
		System.out.println("Zooming in ");
	}
	
	public void zoomOutAction(){
		System.out.println("Zooming out");
		
	}
}
