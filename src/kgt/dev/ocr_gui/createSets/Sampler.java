package kgt.dev.ocr_gui.createSets;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.JPanel;

import org.opencv.core.Mat;

public class Sampler extends JPanel implements Serializable{
	private static final long serialVersionUID = 6836705476905363993L;

	private int w,h;
	
	private transient Mat m = new Mat(20,20,1);
	
	private SampleData data;
	
	private boolean[][] pixelData;
	
	/**
	 * 
	 * @param newData sample data to add to the paint component
	 */
	public Sampler(SampleData newData){
		
		this.data = newData;
		this.w = newData.getSampleMat().cols();
		this.h = newData.getSampleMat().rows();
		this.pixelData = newData.bipolarMatrix();
	
	}
	
	/**
	 * Creates a panel of squares
	 */
	@Override
	public void paint(final Graphics g){
		
		if(this.data == null){
			return;
		}
		int x,y;
		
		int vCell = this.getHeight() / this.data.getHeight();
		int hCell = this.getWidth() / this.data.getWidth();
		
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setColor(Color.black);
		
		for(y= 0; y < this.data.getHeight();y++){
			g.drawLine(0, y * vCell, this.getWidth(), y * vCell);
		}
		
		for(x= 0; x < this.data.getWidth();x++){
			g.drawLine(x*hCell, 0, x * hCell, this.getHeight());
		}
		
		//check to see if the pixel block is filled or not
		for(y = 0; y < this.data.getHeight(); y++){
			for(x = 0; x < this.data.getWidth();x++){
				if(this.pixelData[x][y]){
					g.fillRect(y*hCell, x * vCell, hCell, vCell);
				}
			}	
		}
		
		g.setColor(Color.black);
		g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
	}
	
	//Debugging 
	public void print(){
		for(int x = 0;x < pixelData.length;x++){
			System.out.println();
			for(int y = 0;y < pixelData[0].length;y++){
				if(pixelData[x][y] == true){
					System.out.print(1);
				}else{
					System.out.print(0);
				}
			}
		}
	}
}
