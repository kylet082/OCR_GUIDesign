package kgt.dev.ocr_gui.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.text.View;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;

import kgt.dev.ocr_gui.model.ImageProc;

public class CenterPanel extends Panel{

	private static final long serialVersionUID = 1L;
	private int width, height;
	private JScrollPane scrolPane;
	private JViewport centerViewport;
	
	/**
	 * @param newWidth - Panel width
	 * @param newHeight - Panel height
	 */
	public CenterPanel(int newWidth, int newHeight) {
		super(newWidth, newHeight);
		this.width = newWidth;
		this.height = newHeight;
	}
	
	/**
	 * (non-Javadoc)
	 * @see kgt.dev.ocr_gui.view.Panel#init()
	 * 
	 * Add the panel layout and components
	 */
	@Override
	public void init() {
		
		this.setPreferredSize(new Dimension(width,height));
		this.setMinimumSize(new Dimension(width,height));
		this.setMaximumSize(new Dimension(width,height));
		this.setBackground(new Color(189, 189, 189));
		
		this.setLayout(new FlowLayout());
		((FlowLayout) this.getLayout()).setVgap(0);
		
		scrolPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrolPane.setPreferredSize(new Dimension(getPreferredSize().width-10,getPreferredSize().height-50));
		centerViewport = scrolPane.getViewport(); 
		addBackground(scrolPane.getPreferredSize().width,scrolPane.getPreferredSize().height);
		//System.out.println(scrolPane.getViewport().getWidth() + " " + scrolPane.getViewport().getHeight());
		this.add(scrolPane);
	}
	/**
	 * @param img - show the selected image
	 */
	public void showSlectedImage(Image img){
		
	}
	
	/**
	 * @param p_width - the center panel width
	 * @param p_height - the center panel height
	 */
	public void addBackground(int p_width,int p_height){
		
		Mat backDropMat = Imgcodecs.imread("res/images/backdrop.png");
		backDropMat = ImageProc.resizeImageMat(backDropMat, new Size(p_width,p_height));
		
		Image backDropImg = ImageProc.cvtMatToBufferImg(backDropMat);
		
		JLabel label = new JLabel();
		ImageIcon ic = new ImageIcon(backDropImg);
		label.setIcon(ic);
		
		scrolPane.getViewport().add(label);
	}
	
	/**
	 * @return
	 */
	public JViewport getCenterViewPort(){
		return centerViewport;
	}
}
