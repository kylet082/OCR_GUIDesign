package kgt.dev.ocr_gui.init;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.opencv.core.Core;

import kgt.dev.ocr_gui.controller.ControlHandler;
import kgt.dev.ocr_gui.model.ModelHandler;
import kgt.dev.ocr_gui.view.ViewHandler;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;

public class Application {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
			            // Set cross-platform Java L&F (also called "Metal")
						UIManager.setLookAndFeel(
								"com.jtattoo.plaf.acryl.AcrylLookAndFeel");
						UIManager.put("BaseButtonUI.select", Color.red);
						System.out.println(1);
				    } 
				    catch (UnsupportedLookAndFeelException e) {
				       // handle exception
				    	System.out.println(2);
				    }
				    catch (ClassNotFoundException e) {
				       // handle exception
				    	System.out.println(3);
				    }
				    catch (InstantiationException e) {
				       // handle exception
				    	System.out.println(4);
				    }
				    catch (IllegalAccessException e) {
				       // handle exception
				    	System.out.println(5);
				    }
					
					Application window = new Application();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
		 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		ImageIcon ic = new ImageIcon("res/icons/OCRLogo_C30X18.png");
		frame.setIconImage(ic.getImage());
		
		ModelHandler model = new ModelHandler(null);
		model.init();
		
		ViewHandler view = new ViewHandler(frame);
		view.initFrame();
		
		ControlHandler controller = new ControlHandler(view,model); 
		controller.init();
	}
	
	

}
