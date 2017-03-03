package kgt.dev.ocr_gui.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SerializeObj {
	
	protected static String OUTPUT_DIR = Paths.get(".").toAbsolutePath().normalize().toString();
	
	protected static Path path;
	
	/**
	 * CONSTRUCTOR
	 */
	private SerializeObj(){
		throw new AssertionError();
	}
	
	/**
	 * @param newFile where the file is saved
	 * @param obj serialized object
	 */
	public static void save(File newFile, Serializable obj){
		
		try {
			FileOutputStream outputFile = new FileOutputStream(newFile);
			ObjectOutputStream outputObj = new ObjectOutputStream(outputFile);
			
			outputObj.writeObject(obj);
			outputObj.close();
			outputFile.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param newFile where the file is loaded from
	 * @return the de-serialized object
	 */
	public static Serializable load(File newFile){
		
		Serializable obj = null;
		
		try {
			FileInputStream	inputFile = new FileInputStream(newFile);
			ObjectInputStream inputObj = new ObjectInputStream(inputFile);
			
			obj = (Serializable) inputObj.readObject();
			
			inputObj.close();
			inputFile.close();
			
		} catch (FileNotFoundException e) {
			System.out.println(newFile.getName() + " : Not found!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException io){
			io.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return obj;
	}
	
	/**
	 * @param dir create the a new directory
	 * @return path returns the directory path
	 */
	public static Path createDir(String dir){
		path = Paths.get(OUTPUT_DIR + "/" + dir);
		if(!Files.exists(path)){
			try{
				Files.createDirectories(path);
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return path;
	}
}
