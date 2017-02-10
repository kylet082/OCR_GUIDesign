package kgt.dev.ocr_gui.model;

import java.util.ArrayList;
import java.util.List;

public class OpenImages {
	
	private String projectName;
	private List<ImageMatrix> imageList;
	
	/**
	 * Instantiates the ArrayList to store the open images 
	 */
	public OpenImages(){
		this.imageList = new ArrayList<ImageMatrix>();
	}
	
	/**
	 * @return - ArrayList of the open ImageMatrix object
	 */
	public List<ImageMatrix> getImageList(){
		return imageList;
	}
	
	/**
	 * Add an new ImageMatrix object to the project
	 * 
	 * @param imgMat - ImageMatrix obj to add to the open image ArrayList
	 * @throws NullPointerException
	 */
	public void addImage(ImageMatrix imgMat) throws NullPointerException{
		imageList.add(imgMat);
	}
	
	/**
	 * @return
	 */
	public String getProjectName(){
		return projectName;
	}
	
	/**
	 * @param projName
	 */
	public void setProjectName(String projName){
		this.projectName = projName;
	}
	
	/**
	 * Print the list of open images
	 */
	public void printImageList(){
		for(int x = 0; x < imageList.size(); x++){
			ImageMatrix tmp = imageList.get(x);
			System.out.println(tmp.getName());
		}
	}
}
