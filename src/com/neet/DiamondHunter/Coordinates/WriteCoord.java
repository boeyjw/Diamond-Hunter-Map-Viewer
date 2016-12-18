package com.neet.DiamondHunter.Coordinates;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class WriteCoord {

	public static int[] getCoord(String name, String defPosition){
		File CoordFile = new File("Resources/Sprites/" + name + ".txt");
		//If the file does not exist in the specified path
				if(!CoordFile.exists() || CoordFile.isDirectory()) {
					try {
						//Create the file first. If this fails, NullPointerException will be thrown as return value is null
						if(CoordFile.createNewFile()) {
							FileWriter wrItemCoords = new FileWriter(CoordFile);
							wrItemCoords.write(defPosition); 
							wrItemCoords.close();
						}
					} catch (IOException e) {
						System.err.println("Unable to create or write file");
						e.printStackTrace();
					}
				}
				
				//File exist and is ready to be read
				if(CoordFile.canRead()) {
					try {
						FileInputStream rdCoords = new FileInputStream(CoordFile);
						byte[] data = new byte[(int) CoordFile.length()];
						
						//Read the entire file in one go
						rdCoords.read(data);
						rdCoords.close();
						String[] strCoords = new String(data, "UTF-8").split(",");
						
						//Get the coordinates
						int[] Coords = new int[strCoords.length];
						for(int i = 0; i < strCoords.length; i++) {
							Coords[i] = Integer.parseInt(strCoords[i]);
						}
						return Coords;
					} catch (FileNotFoundException e) {
						System.err.println("File does not exist");
						e.printStackTrace();
					} catch (IOException e) {
						System.err.println("No read access to file");
						e.printStackTrace();
					}
				}
				else {
					System.err.println("Error in reading file");
					return null;
				}
				
				return null;
	}
	
}
