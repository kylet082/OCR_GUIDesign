package kgt.dev.ocr_gui.view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class DisplayImage extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private Image image;
	public DisplayImage(Image newImage){
		this.image = newImage;
	}
	
	public void update(){
		this.revalidate();
		this.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.setBackground(new Color(149, 165, 166));
		int x = (this.getWidth() - image.getWidth(null)) / 2;
		int y = (this.getHeight() - image.getHeight(null)) / 2;
		g.drawImage(image, x, y, this);
	}
	
	public void setDisplayImage(Image newImg){
		this.image = newImg;
	}
	
}
