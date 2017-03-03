package kgt.dev.ocr_gui.controller.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import kgt.dev.ocr_gui.model.training.Entry;
import kgt.dev.ocr_gui.model.training.SampleData;
import kgt.dev.ocr_gui.model.training.Sampler;
import kgt.dev.ocr_gui.view.dialogs.PreviewChars;

public class PreviewCharsController {

	private PreviewChars pc;
	
	private Entry entries;
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param newPc
	 * @param newEntry
	 */
	public PreviewCharsController(PreviewChars newPc,Entry newEntry){
		this.pc = newPc;
		this.entries = newEntry;
	}
	
	/**
	 * Initialize the controller
	 */
	public void control(){
		
		List<SampleData> sampleData = entries.getTrainingSet().getTrainingSet();
		
		pc.getLayout().setColumns(4);
		pc.getLayout().setRows(sampleData.size()/4);
		pc.getLayout().setHgap(2);
		pc.getLayout().setVgap(2);
		
		for(int x =0; x < sampleData.size();x++){
			JPanel p = new JPanel();
			BorderLayout layout = new BorderLayout();
			p.setLayout(layout);
			
			JLabel l = new JLabel(sampleData.get(x).getSymbol() + "");
			Sampler s = new Sampler(sampleData.get(x));
			s.setPreferredSize(new Dimension(100,100));
			p.add(l, BorderLayout.NORTH);
			p.add(s, BorderLayout.CENTER);
			pc.getViewPortPanel().add(p);
		}
	}
}
