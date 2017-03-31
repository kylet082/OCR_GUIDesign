package kgt.dev.ocr_gui.view.tools;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public abstract class Tools extends JPanel{
	
	protected JSlider slider;
	protected JTextArea value;
	protected String label;
	
	public Tools(String newLabel, int range){
		this.label = newLabel;
		this.slider = new JSlider(JSlider.HORIZONTAL,0,range,20);
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
}
