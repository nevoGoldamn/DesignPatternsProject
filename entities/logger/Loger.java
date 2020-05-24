package logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * The Singelton Class Loger.
 */
public class Loger {
	
	/** The my file. */
	private static File myFile;
	
	/** The Constant LOGER. */
	private final static Loger LOGER = new Loger();
	
	/**
	 * Instantiates a new loger.
	 */
	private Loger(){
		
		//createTXTfile
		myFile = new File("Log.txt");
	}
	
	/**
	 * Gets the loger.
	 *
	 * @return the loger
	 */
	public static Loger getLoger() {
		return LOGER;
	}
	
	/**
	 * Write line to text file.
	 *
	 * @param message
	 */
	public static void writeLineToTextFile(String message) {
		
		 try { 
	            // Open given file in append mode. 
	            BufferedWriter out = new BufferedWriter( 
	                   new FileWriter(myFile, true)); 
	            out.write(message); 
	            out.close(); 
	        } 
	        catch (IOException e) { 
	            System.out.println("exception occoured" + e);
	        } 
	}
	
}
