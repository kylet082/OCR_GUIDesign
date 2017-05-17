package kgt.dev.ocr_gui.view.tools;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public abstract class Tools extends JPanel{
	
	protected JSlider slider;
	protected JTextArea value;
	protected String label;
	protected JButton actionButton;
	protected int pWidth;

	
	public Tools(String newLabel, int[] range, int panelWidth){
		this.label = newLabel;
		this.slider = new JSlider(JSlider.HORIZONTAL,range[0],range[1],20);
		this.pWidth = panelWidth -20;
	}
	
	public abstract void init();
	
	public JSlider getSlider(){
		return this.slider;
	}
	
	public void setValue(int i){
		this.value.setText(String.valueOf(i));
	}
	
	public int getSliderValue(){
		return this.slider.getValue();
	}
	
	public String getValue(){
		return this.value.getText();
	}
	
	public JTextArea getTextArea(){
		return this.value;
	}

	public JButton getActionBtn() {
		return this.actionButton;
	}
	
}
