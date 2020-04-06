package com.pokersimples.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.pokersimples.utils.Logger;

public class FileIO {
    private static final String filepath=".\\src\\rankshandlookup.dat";
		 
	    public static void WriteObjectToFile(Object serObj) {
	 
	        try {
	        	System.getProperty("user.dir");
	            FileOutputStream fileOut = new FileOutputStream(filepath);
	        	BufferedOutputStream buffOut = new BufferedOutputStream(fileOut);
	            ObjectOutputStream objectOut = new ObjectOutputStream(buffOut);
	            objectOut.writeObject(serObj);
	            objectOut.close();
	            System.out.println("The Object  was succesfully written to a file");
	 
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }		
	    
	    public static Object readObjectFromFile() {
	   	 
	        try {
	 
	        	FileInputStream fileIn = new FileInputStream(filepath);
	        	BufferedInputStream buffIn = new BufferedInputStream(fileIn);
	        	ObjectInputStream objectIn = new ObjectInputStream(buffIn);
	            Logger.debug("Starting the read");	        	
	        	Object obj = objectIn.readObject();
	        	objectIn.close();
	        	Logger.debug("The Object has been read from the file");
	            objectIn.close();
	            return obj;

	 
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return null;
	        }
	    }		    

	    
	    
}
