package kgt.dev.ocr_gui.view.tools;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class BinaryThreshTool extends Tools {
	
	public BinaryThreshTool(String newLabel, int[] range, int panelWidth) {
		super(newLabel, range, panelWidth);
	}

	@Override
	public void init() {
		this.setPreferredSize(new Dimension(this.pWidth,95));
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);
		
		this.setBackground(new Color(189, 195, 199));
		this.slider.setBackground(new Color(189, 195, 199));
		this.slider.setMinorTickSpacing(15);
		this.slider.setMajorTickSpacing(255);
		this.slider.setPaintTicks(true);
		this.slider.setPaintLabels(true);
		
		Border border = BorderFactory.createCompoundBorder(
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),BorderFactory.createTitledBorder(this.label));
		
		this.setBorder(border);
		JPanel p = new JPanel();
		p.setBackground(new Color(189, 195, 199));
		
		JPanel p1 = new JPanel();
		p1.setBackground(new Color(189, 195, 199));
		JPanel empty = new JPanel();
		empty.setBackground(new Color(189, 195, 199));
		
		GridLayout grid = new GridLayout(1,1);
		p.setLayout(grid);
		
		this.value = new JTextArea();
		Border textBorder =BorderFactory.createBevelBorder(BevelBorder.LOWERED, new Color(51,51,51), new Color(153,153,153),
				new Color(153,153,153),new Color(204,204,2));
		this.value.setBorder(textBorder);
		this.value.setPreferredSize(new Dimension(30,18));
		JLabel valLabel = new JLabel("Value : ");
		valLabel.setFont(new Font(this.getFont().getName(),Font.BOLD,12));
		p1.add(valLabel);
		p1.add(value);
		
		p.add(empty);
		p.add(p1);
		
		this.add(p,BorderLayout.SOUTH);
		this.add(this.slider,BorderLayout.CENTER);
	}
}
