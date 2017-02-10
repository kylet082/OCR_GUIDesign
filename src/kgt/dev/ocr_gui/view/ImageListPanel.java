package kgt.dev.ocr_gui.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import kgt.dev.ocr_gui.model.ImageMatrix;

public class ImageListPanel extends Panel {

	private static final long serialVersionUID = 1L;
	private int width,height;
	private JPanel outerPanel,topPane,btmPane,
					viewportPanel,thumbCont;

	private JScrollPane imgListPane;
	private JButton openBtn,clearBtn,saveBtn,copyBtn,viewBtn;
	private ImageIcon openIc,clearIc,saveIc,copyIc,viewIc;
	
	/**
	 * @param newWidth
	 * @param newHeight
	 */
	public ImageListPanel(int newWidth, int newHeight) {
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
		this.setPreferredSize(new Dimension(width ,height-60));
		this.setMinimumSize(new Dimension(width,height-60));
		this.setMaximumSize(new Dimension(width,height-60));
		this.setBackground(new Color(96, 125, 139));
		
		Border border =BorderFactory.createEtchedBorder(EtchedBorder.RAISED,
						new Color(117, 117, 117), new Color(189, 189, 189));
		
		this.setBorder(border);

		outerPanel = new JPanel();
		outerPanel.setBackground(new Color(96, 125, 139));
		outerPanel.setPreferredSize(new Dimension(width-5,height));
		outerPanel.setMaximumSize(new Dimension(width-5,height));
		BoxLayout outerLayout = new BoxLayout(outerPanel,BoxLayout.Y_AXIS);
		
		Border outerBorder = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(1,1,65,1),
				BorderFactory.createSoftBevelBorder(BevelBorder.RAISED, new Color(51,51,51), new Color(153,153,153),
						new Color(153,153,153),new Color(204,204,2)));
		outerPanel.setBorder(outerBorder);
		
		//add top and bottom JPanels to split panel  
		topPane = new JPanel();
		topPane.setBackground(new Color(96, 125, 139));
		topPane.setPreferredSize(new Dimension(getPreferredSize().width-15,(getPreferredSize().height-110)));
		btmPane = new JPanel();
		btmPane.setBackground(new Color(69,90,100));
		btmPane.setPreferredSize(new Dimension(getPreferredSize().width-10,100));
		
		//top panel
		Border topPanelBorder = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1),
				BorderFactory.createEtchedBorder(BevelBorder.RAISED));
	
		topPane.setBorder(topPanelBorder);
		
		imgListPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//imgListPane.setPreferredSize(new Dimension(200,300));
		viewportPanel = new JPanel();
		viewportPanel.setPreferredSize(new Dimension(width -55,(getPreferredSize().height -110)));

		imgListPane.setPreferredSize(new Dimension(width -30,(getPreferredSize().height -125)));
		
		imgListPane.getViewport().setBackground(new Color(241,241,241));
		imgListPane.getViewport().add(viewportPanel);
		FlowLayout listLayout = new FlowLayout();
		viewportPanel.setLayout(listLayout);
		((FlowLayout) viewportPanel.getLayout()).setVgap(1);
		topPane.add(imgListPane);
		
		//bottom panel 
		Border btmPanelBorder = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0,2,0,2),
				BorderFactory.createEtchedBorder());
		btmPane.setBorder(btmPanelBorder);
		
		openBtn = new JButton();
		clearBtn = new JButton();
		saveBtn = new JButton();
		copyBtn = new JButton();
		viewBtn = new JButton();
		
		viewIc = new ImageIcon("res/icons/viewimg.png");
		openIc = new ImageIcon("res/icons/open32X32.png");
		copyIc = new ImageIcon("res/icons/copy32X32.png");
		clearIc = new ImageIcon("res/icons/trashCan_32X32.png");
		saveIc = new ImageIcon("res/icons/save32X32.png");
		
		viewBtn.setIcon(viewIc);
		openBtn.setIcon(openIc);
		clearBtn.setIcon(clearIc);
		saveBtn.setIcon(saveIc);
		copyBtn.setIcon(copyIc);
		
		viewBtn.setBackground(new Color(255,87,34));
		copyBtn.setBackground(new Color(255,87,34));
		openBtn.setBackground(new Color(255,87,34));
		clearBtn.setBackground(new Color(255,87,34));
		saveBtn.setBackground(new Color(255,87,34));
		
		viewBtn.setPreferredSize(new Dimension(width-24,40));
		btmPane.add(viewBtn);
		btmPane.add(openBtn);
		btmPane.add(saveBtn);
		btmPane.add(copyBtn);
		btmPane.add(clearBtn);
		
		outerPanel.add(topPane);
		outerPanel.add(btmPane);
		
		this.add(outerPanel);
	}
	
	/**
	 * Adds a thumb image to the image list
	 * 
	 * @param img - ImageMatrix to references and add to the image list panel
	 */
	public void addOpenedImgs(ImageMatrix img){
		
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		thumbCont = new JPanel();
		
		GridLayout layout = new GridLayout(1 ,2,0,0);
		
		thumbCont.setLayout(layout);
		
		Border contBorder = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 1, 0, 1),
				BorderFactory.createEtchedBorder());
		thumbCont.setBorder(contBorder);
		JLabel imgLabel = new JLabel();
		JLabel imgName = new JLabel(img.getName());
		
		ImageIcon icon = new ImageIcon(img.createImageIcon(55));
		imgLabel.setIcon(icon);
		//(int) img.getThumbImgSize().height +16
		thumbCont.setPreferredSize(new Dimension(width-50,(int)img.getThumbImgSize().height+16));
		System.out.println(img.getThumbImgSize());
		panel1.add(imgLabel);
		panel.add(imgName);
		
		thumbCont.add(panel1);
		thumbCont.add(panel);
		
		img.setImgListItem(thumbCont);
		
		viewportPanel.add(thumbCont);
		viewportPanel.revalidate();
		
	}
	
	/**
	 * @return - JPanel in the JScollPane viewport
	 */
	public JPanel getImgListViewPort(){
		return viewportPanel;
	}
	
	/**
	 * @return - JPanel with the added components (image thumb and name) 
	 */
	public JPanel getThumbContainer(){
		return thumbCont;
	}
	
	/**
	 * @return - JButton view image button
	 */
	public JButton getViewBtn(){
		return viewBtn;
	}
	
	/**
	 * @return - JButton open image button
	 */
	public JButton getOpenBtn(){
		return openBtn;
	}
	
	/**
	 * @return - JButton clear image button
	 */
	public JButton getClearBtn(){
		return clearBtn;
	}
	
	/**
	 * @return JButton copy image button
	 */
	public JButton getCopyBtn(){
		return copyBtn;
	}
	
	/**
	 * @return - JButton save image button
	 */
	public JButton getSaveBtn(){
		return saveBtn;
	}
}
