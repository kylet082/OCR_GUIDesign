package kgt.dev.ocr_gui.neuralnet.createSets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import kgt.dev.ocr_gui.model.ImageMatrix;



/**
 * 
 * @author Kyle
 *	
 *	De-crypt the zip files that contain the characters for 
 *	the training sets 
 */
public class ZipReader {
	
	protected static String path;
	
	protected static String setPath;
	
	protected static String setName;
	
	private static File[] fileList;
	
	protected static String OUTPUT_DIR = Paths.get(".").toAbsolutePath().normalize().toString();
	
	protected final static int BUFFER_SIZE = 8192;
	
	private static FileOutputStream fos = null;
	
	private static List<ImageMatrix> imageSet;
	
	private static ZipReader zipReader = null;
	
	private static String set;
	
	/**
	 * Singleton pattern for the zipfile reader
	 * 
	 * @param Path
	 */
	protected ZipReader(){
		//empty Constructor
	}
	
	/**
	 * @param f
	 * @return
	 */
	public static ZipReader getInstance(File f){
		
		path = f.getAbsolutePath();
		String[] name = f.getName().split(".zip");
		setName = name[0].toString()+ "\\";
		set = name[0].toString();
		createSetsDir(OUTPUT_DIR);
	
		if(zipReader == null){
			zipReader = new ZipReader();
			imageSet = new ArrayList<ImageMatrix>();
			
			try{
				readZipFile();
			}catch(IOException e){
				System.out.println("Error unZipping file");
			}
		}
		return zipReader;
	} 
	
	
	/**
	 * 
	 * @throws IOException
	 */
	private static void readZipFile() throws IOException{
		
		final ZipFile zipFile = new ZipFile(path);
		
		try{
			final Enumeration<? extends ZipEntry> entries = zipFile.entries();
			while(entries.hasMoreElements()){
				final ZipEntry entry = entries.nextElement();
				extractFile(entry, zipFile.getInputStream(entry));
			}
		}finally{
			zipFile.close();
		}
	}
	
	/**
	 * Read the data from Input stream
	 * 
	 * @param entry 
	 * @param is
	 * @throws IOException 
	 */
	private static void extractFile(ZipEntry entry, InputStream is) throws IOException{
		String extractedFile =entry.getName();
		
		File file = new File(setPath+"\\"+extractedFile);
		fileList = new File[1];
		try{
			fos = new FileOutputStream(file);
			final byte[] buffer = new byte[BUFFER_SIZE];
			int read = 0;
			int length;
			int x = 0;
		
			while((length = is.read(buffer , 0, buffer.length)) >= 0){
				fos.write(buffer, 0, length);
				fileList[x] = file.getAbsoluteFile();
				x++;
				//open and add the image to ImageMatrix list
				Mat image = Imgcodecs.imread(file.getAbsolutePath());
				ImageMatrix imgMat = new ImageMatrix(image,file);
				imageSet.add(imgMat);
			}
		}catch(IOException ex){
			
		}
		
	}
	
	/**
	 * @param dir to create if does not exist
	 */
	private static void createSetsDir(String dir){
		
		Path path = Paths.get(dir + "\\Sets\\" + setName);
		setPath = path.toString();
        //if directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                //fail to create directory
                e.printStackTrace();
            }
        }
	}
	
	public static void clearUnZipImages(){
		/*	clear the images from the sets
		 *  file when finished creating data sets
		 */
	}
	
	/**
	 * @return path of the file
	 */
	public String getSetPath(){
		return setPath;
	}
	
	/**
	 * @return list of raw training images
	 */
	public static List<ImageMatrix> getImageSet(){
		return imageSet;
	}
	
	/**
	 * @return the name of the set
	 */
	public static String getSetName(){
		return set;
	}
}
