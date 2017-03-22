package kgt.dev.ocr_gui.controller.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import kgt.dev.ocr_gui.controller.ControlHandler;
import kgt.dev.ocr_gui.model.ImageMatrix;
import kgt.dev.ocr_gui.neuralnet.createSets.Entry;
import kgt.dev.ocr_gui.neuralnet.createSets.ZipReader;
import kgt.dev.ocr_gui.utilities.ExportToCSV;
import kgt.dev.ocr_gui.utilities.SerializeObj;
import kgt.dev.ocr_gui.view.dialogs.PreviewChars;
import kgt.dev.ocr_gui.view.dialogs.TrainingDialog;

public class TrainingDialogController {
	
	private TrainingDialog td;
	
	private JFrame previewFrame;
	
	private ActionListener actionImport,actionClear, actionSave, actionExportCSV;
	
	private ZipReader zipReader;
	
	private Entry entries;
	
	private File f;
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param newTd training dialog view
	 */
	public TrainingDialogController(TrainingDialog newTd){
		this.td = newTd;
	}
	
	/**
	 *  Initialize the controller
	 */
	public void control(){
		importAction();
		clearAction();
		saveAction();
		exportCSVAction();
	}
	
	/**
	 * import button action event
	 */
	public void importAction(){
		
		actionImport = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(td.getWidthCombo().getSelectedItem() != null && td.getHeightCombo().getSelectedItem() != null){
					f = ControlHandler.primaryActions.zipChooser(td.getFrame());
					int wSelect =(int) td.getWidthCombo().getSelectedItem();
					int hSelect =(int) td.getHeightCombo().getSelectedItem();
					
					zipReader = ZipReader.getInstance(f);
					entries = new Entry(zipReader.getSetName(),zipReader.getImageSet(),wSelect,hSelect);
					entries.downSample();
					
					outPutSetDetails();
					
					//display char previews
					if(td.getPreview().isSelected()){
						previewFrame = new JFrame("Character Previews");
						//display char previews
						PreviewChars pc = new PreviewChars(previewFrame);
						pc.init();
						
						PreviewCharsController pcc = new PreviewCharsController(pc,entries);
						pcc.control();
					
					}else{
						JOptionPane.showMessageDialog(td.getFrame(),
								"Import Successful", "Done",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else{
					System.out.println("Fill in the required fields first!");
				}
			}
		};
		td.getImportBtn().addActionListener(actionImport);;
	}
	
	/**
	 * Output to the screen the details of the import character sets.
	 */
	protected void outPutSetDetails(){
		td.getSetName().setText("");
		td.getSetName().setText("Font : " + zipReader.getSetName());
		
		td.getSetDetails().setText("");
		String font = ">> Font Type           : " + zipReader.getSetName();
		String dimW = ">> Sample Width   : " + td.getWidthCombo().getSelectedItem();
		String dimH = ">> Sample Height : " + td.getHeightCombo().getSelectedItem();
		String count =">> Char Count       : " + zipReader.getImageSet().size();
		td.getSetDetails().setText(font + "\n" + dimW + "\n" + dimH + "\n" + count);
	}
	
	/**
	 * Clear the currently imported data set action 
	 */
	public void clearAction(){
		
		actionClear = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(zipReader != null){
					zipReader = null;
					for(ImageMatrix img: entries.getMatrixList()){
						img.setImgMat(null);
					}
					control();
					clearData();
					JOptionPane.showMessageDialog(td.getFrame(),
							"Set successfully removed", "Done",
							JOptionPane.INFORMATION_MESSAGE);
				}else{
					System.out.println("Nothing to clear !");
				}
			}
		};
		td.getClearSetBtn().addActionListener(actionClear);
	}
	/**
	 * clearing the data sets
	 */
	private void clearData(){
	
		td.getSetName().setText(" No Fonts sets imported!");
		td.getSetDetails().setText("");
		td.getHeightCombo().setSelectedIndex(0);
		td.getWidthCombo().setSelectedIndex(0);
	}
	
	/**
	 * Saving the imported data sets to a .ser file 
	 */
	public void saveAction(){
		
		actionSave = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Saving....");
				Path path = SerializeObj.createDir("TrainingSets");
				File f = new File(path.toString() +"/"+ ZipReader.getSetName() +".ser");
				SerializeObj.save(f, entries.getTrainingSet());
				JOptionPane.showMessageDialog(td.getFrame(),
						"Save Successful", "Done",
						JOptionPane.INFORMATION_MESSAGE);
			}
		};
		td.getSaveSetBtn().addActionListener(actionSave);
	}
	
	/**
	 * Export the current training set to a .CSV file
	 */
	public void exportCSVAction(){
		
		actionExportCSV = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Path path = SerializeObj.createDir("WorkBench");
				File f = new File(path.toString() + "/" + ZipReader.getSetName() + ".csv");
				//entries.getTrainingSet().getTrainingSet().get(0).getSampler().print();
				try {
					ExportToCSV.generateCSV(entries.getTrainingSet(), f);
					JOptionPane.showMessageDialog(td.getFrame(),
							"CSV export successful", "Done",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		};
		td.getExportCSVBtn().addActionListener(actionExportCSV);
	}
}
