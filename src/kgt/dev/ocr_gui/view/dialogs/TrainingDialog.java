package kgt.dev.ocr_gui.view.dialogs;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class TrainingDialog{
	
	private JFrame frame;
	
	private int width,height;
	
	private JPanel rootP,paramP,inParamP,textOutPanel;
	
	private JButton importBtn, clearSetBtn, saveSetBtn, exportCSVBtn;
	
	private JTextArea setName, setDetails;
	
	private JCheckBox preview;
	
	private JComboBox<Integer> widthCombo,heightCombo;
	
	private Integer[] comboInt = new Integer[10];
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param frame -new JFrame
	 */
	public TrainingDialog(JFrame frame) {
		this.frame = frame;
		this.width = frame.getWidth();
		this.height = frame.getHeight();
	}

	/**
	 * Initialize the training dialog
	 */
	public void init() {
		
		frame.setBounds(210,110,width,height);
		frame.setPreferredSize(new Dimension(width,height));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		rootP = new JPanel();
		//rootP.setBackground(Color.DARK_GRAY);
		BoxLayout rooLayout = new BoxLayout(rootP,BoxLayout.Y_AXIS);
		
		int x=0;
		for(int q = 1; q < 101;q++){
			if(q % 10 == 0){
				comboInt[x] = q;
				x++;
			}
		}
		//Parameters panel
		paramP = new JPanel();
		paramP.setPreferredSize(new Dimension(250,345));
		Border paramBorder = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(2,0,0,0),
				BorderFactory.createTitledBorder("Sample Parameters "));
		paramP.setBorder(paramBorder);
		
		setName = new JTextArea();
		setName.setText(" No Fonts sets imported!");
		setName.setPreferredSize(new Dimension(200,20));
		setName.setBackground(Color.LIGHT_GRAY);
		setName.setEditable(false);
		
		JLabel widthLbl = new JLabel("Width :");
		widthCombo = new JComboBox<Integer>(comboInt);
		
		JLabel heightLbl = new JLabel("Height");
		heightCombo = new JComboBox<Integer>(comboInt);
		
		inParamP = new JPanel();
		inParamP.setPreferredSize(new Dimension(240,250));
		//inParamP.setBackground(Color.white);
		importBtn = new JButton("Import Set");
		importBtn.setPreferredSize(new Dimension(100,30));
		
		clearSetBtn = new JButton("Clear Set");
		clearSetBtn.setPreferredSize(new Dimension(100,30));
		
		preview = new JCheckBox("Preview Characters");
		
		textOutPanel = new JPanel();
		//textOutPanel.setBackground(Color.darkGray);
		textOutPanel.setPreferredSize(new Dimension(230,150));
		
		Border textBorder = BorderFactory.createTitledBorder("Set Details");
		textOutPanel.setBorder(textBorder);
		
		setDetails = new JTextArea("<< Nothing to Show >>");
		setDetails.setEditable(false);
		setDetails.setPreferredSize(new Dimension(210,110));
		
		textOutPanel.add(setDetails);
		
		saveSetBtn = new JButton("Save Set");
		saveSetBtn.setPreferredSize(new Dimension(100,25));
		
		exportCSVBtn= new JButton("Export CSV");
		exportCSVBtn.setPreferredSize(new Dimension(100,25));
		
		inParamP.add(preview);
		inParamP.add(importBtn);
		inParamP.add(clearSetBtn);
		inParamP.add(textOutPanel);
		inParamP.add(saveSetBtn);
		inParamP.add(exportCSVBtn);
		
		paramP.add(setName);
		paramP.add(widthLbl);
		paramP.add(widthCombo);
		paramP.add(heightLbl);
		paramP.add(heightCombo);
		paramP.add(inParamP);
		/***********************************/
		//
		rootP.add(paramP);
		
		frame.add(rootP);
		frame.setVisible(true);
	}
	
	/**
	 * @return - import button
	 */
	public JButton getImportBtn() {
		return importBtn;
	}

	/**
	 * @return - clear button
	 */
	public JButton getClearSetBtn() {
		return clearSetBtn;
	}
	
	/**
	 * @return  -save set button 
	 */
	public JButton getSaveSetBtn() {
		return saveSetBtn;
	}
	
	/**
	 * @return
	 */
	public JButton getExportCSVBtn(){
		return exportCSVBtn;
	}
	
	/**
	 * @return - set name text area
	 */
	public JTextArea getSetName() {
		return setName;
	}

	/**
	 * @return -get set details text area
	 */
	public JTextArea getSetDetails() {
		return setDetails;
	}

	/**
	 * @return - preview check box
	 */
	public JCheckBox getPreview() {
		return preview;
	}

	/**
	 * @return -width combo box
	 */
	public JComboBox getWidthCombo() {
		return widthCombo;
	}
	
	/**
	 * @return - height combo box
	 */
	public JComboBox getHeightCombo() {
		return heightCombo;
	}

	/**
	 * @return -combo box array values
	 */
	public Integer[] getComboInt() {
		return comboInt;
	}
	
	/**
	 * @return -training dialog JFrame
	 */
	public JFrame getFrame(){
		return this.frame;
	}
}
