package kgt.dev.ocr_gui.model;

public class ModelHandler {
	
	private OpenImages openImages;
	private String projectName;
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param newProjName - the new project name
	 */
	public ModelHandler(String newProjName){
		this.projectName = newProjName;
	}
	
	/**
	 * initialize the Model handler
	 */
	public void init(){
		openImages = new OpenImages();
	}
	
	/**
	 * @return - the OpenImages object of the opened images
	 */
	public OpenImages getOpenImages(){
		return openImages;
	}
}
