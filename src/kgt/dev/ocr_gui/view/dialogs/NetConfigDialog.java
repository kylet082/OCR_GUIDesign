package kgt.dev.ocr_gui.view.dialogs;

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

public class NetConfigDialog {

	private JFrame frame;
	
	private JPanel rootPanel, topPanel,top1Panel,
					midPanel,mid1Panel,btmPanel,btm1Panel;
	
	private JButton loadBtn,clearBtn,createBtn,saveBtn;
	
	private JComboBox<String> networkBox;
	
	private JLabel netType,netLbl;
	
	private JTextArea configText;
	
	private JCheckBox automateCheck;
	
	private final String[] netCombo = {"SOM","Feed-Forward"};
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param newFrame
	 */
	public NetConfigDialog(JFrame newFrame){
		this.frame = newFrame;
	}
	
	/**
	 * Initialize the network config Dialog
	 */
	public void init(){
		frame.setBounds(300,150,250,350);
		frame.setPreferredSize(new Dimension(200,300));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		rootPanel = new JPanel();
		BoxLayout rootLayout = new BoxLayout(rootPanel,BoxLayout.Y_AXIS);
		
		topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(290,30));
		netType = new JLabel("Network Type :");
		networkBox = new JComboBox<String>(netCombo);
		topPanel.add(netType);
		topPanel.add(networkBox);
		
		top1Panel = new JPanel();
		automateCheck = new JCheckBox("Automate Creation");
		top1Panel.add(automateCheck);
		
		midPanel = new JPanel();
		loadBtn = new JButton("Load Sets");
		clearBtn = new JButton("Clear Set");
		loadBtn.setPreferredSize(new Dimension(frame.getPreferredSize().width/2, 30));
		clearBtn.setPreferredSize(new Dimension(frame.getPreferredSize().width/2, 30));
		midPanel.add(loadBtn);
		midPanel.add(clearBtn);
		
		mid1Panel = new JPanel();
		netLbl = new JLabel("No Network set");
		mid1Panel.add(netLbl);
		
		btmPanel = new JPanel();
		Border textBorder = BorderFactory.createTitledBorder("Configuration Parameters");
		btmPanel.setBorder(textBorder);
		configText = new JTextArea();
		configText.setPreferredSize(new Dimension(190,80));
		btmPanel.add(configText);
		
		btm1Panel = new JPanel();
		createBtn = new JButton("Create");
		saveBtn = new JButton("Save");
		createBtn.setPreferredSize(new Dimension(frame.getPreferredSize().width/2, 30));
		saveBtn.setPreferredSize(new Dimension(frame.getPreferredSize().width/2, 30));
		btm1Panel.add(createBtn);
		btm1Panel.add(saveBtn);
		
		rootPanel.add(topPanel);
		rootPanel.add(top1Panel);
		rootPanel.add(midPanel);
		rootPanel.add(mid1Panel);
		rootPanel.add(btmPanel);
		rootPanel.add(btm1Panel);
		
		frame.add(rootPanel);
		frame.setVisible(true);
	}
	
	/**
	 * @return
	 */
	public JFrame getFrame(){
		return this.frame;
	}
	/**
	 * @return - label
	 */
	public JLabel getNetLabel(){
		return netLbl;
	}
	
	/**
	 * @param text - set label
	 */
	public void setNetLabel(String text){
		this.netLbl.setText(text);
	}
	
	/**
	 * @return - text area
	 */
	public JTextArea getConfigText(){
		return configText;
	}
	
	/**
	 * @param text
	 */
	public void setConfigText(String text){
		this.configText.setText(text);;
	}
	
	/**
	 * @return - load a training set
	 */
	public JButton getLoadBtn(){
		return loadBtn;
	}
	
	/** 
	 * @return - clear loaded training set button
	 */
	public JButton getClearBtn(){
		return clearBtn;
	}
	
	/**
	 * @return - create network button
	 */
	public JButton getCreateBtn(){
		return createBtn;
	}

	/**
	 * @return - save network button
	 */
	public JButton getSaveBtn(){
		return saveBtn;
	}
}
