package kgt.dev.ocr_gui.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.text.View;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;

import kgt.dev.ocr_gui.utilities.ImageProc;

public class CenterPanel extends Panel{
	private static final long serialVersionUID = -140751702785592126L;

	private int width, height;
	
	private JScrollPane scrolPane;
	
	private JViewport centerViewport;
	
	private DisplayImage display;
	
	private JPanel view;
	
	/**
	 * CONSTRUCTOR
	 * 
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
		this.setBackground(new Color(149, 165, 166));
		
		/*this.setLayout(new FlowLayout());
		((FlowLayout) this.getLayout()).setVgap(0);*/
		this.setLayout(new BorderLayout());
		
		view = new JPanel();
		
		scrolPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrolPane.setPreferredSize(new Dimension(getPreferredSize().width-10,getPreferredSize().height-50));
		//centerViewport = scrolPane.getViewport(); 
		scrolPane.setViewportView(view);
		
		addBackground(scrolPane.getPreferredSize().width,scrolPane.getPreferredSize().height);
		//System.out.println(scrolPane.getViewport().getWidth() + " " + scrolPane.getViewport().getHeight());
		this.add(scrolPane,BorderLayout.CENTER);
	}
	
	
	/**
	 * @param p_width - the center panel width
	 * @param p_height - the center panel height
	 */
	public void addBackground(int p_width,int p_height){
		
		Mat backDropMat = Imgcodecs.imread("res/images/backdrop.png");
		backDropMat = ImageProc.resizeImageMat(backDropMat, new Size(p_width,p_height));
		
		Image backDropImg = ImageProc.cvtMatToBufferImg(backDropMat);
		
		display = new DisplayImage(backDropImg);
		display.setPreferredSize(new Dimension(backDropImg.getWidth(null),backDropImg.getHeight(null)));
		view.add(display);
		
	}
	
	/**
	 * @return  -center panel viewport (scrollPanel)
	 */
	public JPanel getCenterViewPort(){
		return this.view;
	}
	
	/**
	 * @return - the center panel scrollpanel
	 */
	public JScrollPane getScrolPane(){
		return scrolPane;
	}
	
	/**
	 * @return -center panel root JPanel 
	 */
	public JPanel getView(){
		return view;
	}
	
	public DisplayImage getDisplayPanel(){
		return this.display;
	}
}
