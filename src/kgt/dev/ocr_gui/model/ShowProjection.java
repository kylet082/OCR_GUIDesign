package kgt.dev.ocr_gui.model;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;

public class ShowProjection extends JPanel {

	private static final long serialVersionUID = 2353557304096690169L;
	
	private BufferedImage img;
	private List<int[]>points;
	
	public ShowProjection(List<int[]> points,BufferedImage newImg){
		this.points = points;
		this.img = newImg;
	}
	
	@Override
	public void paint(Graphics g){
		//g = img.getGraphics();
		//g.drawLine(0, 0, 30, 60);
		g.drawImage(img, 0, 0, null);
		g.setColor(Color.RED);
		System.out.println(points.size() + "\n");
		for(int[] point : points){
			
			//System.out.println(point[3] +  " " + point[1]);
		
			g.drawRect(point[0]-4, point[1]-2,(point[2] - point[0]) + 6,(point[3]-point[1]) + 4);
		}
	}
}
