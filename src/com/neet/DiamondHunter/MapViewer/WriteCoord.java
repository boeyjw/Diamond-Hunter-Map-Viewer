package com.neet.DiamondHunter.MapViewer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This is a class to write coordinates of axe, boat, player and diamonds into a
 * text file namely Entity-Coordinates.txt.
 * If the file does not exist, create the
 * file and write all default position.
 * The coordinates which is needed. <br>
 * Arrangement: <br>
 * line 1 : {@code AXE_xaxis, AXE_yaxis, BOAT_xaxis, BOAT_yaxis} 
 * line 2 : {@code player_xaxis, player_yaxis} 
 * line 3-17: {@code diamond_xaxis, diamond_yaxis}
 * @return The coordinate array respective to the entity
 **/

public class WriteCoord {

	private static File coordFile = new File("Entity-Coordinates.txt");

	private static final String[] diamond_coords = { "20,20", "12,36", "28,4", "4,34", "28,19", "35,26", "38,36",
			"27,28", "20,30", "14,25", "4,21", "9,14", "4,3", "20,14", "13,20" };

	/**
	 * Check the existence of the file.
	 * If the file exist, skips the entire method.
	 * Otherwise, create the file with all the default position value of the entities.
	 * This method will fail in strict UAC directory.
	 */
	public static void checkExist() {
		// If the file does not exist in the specified path
		if (!coordFile.exists() || coordFile.isDirectory()) {
			try {
				// Create the file with default value first. If this fails,
				// NullPointerException will be thrown as return value is null
				if (coordFile.createNewFile()) {
					BufferedWriter wrCoords = new BufferedWriter(new FileWriter(coordFile));
					wrCoords.write("26,37,12,4"); // items default coordinates
					wrCoords.newLine();
					wrCoords.write("17,17"); // player default coordinate
					wrCoords.newLine();
					for (int i = 0; i < diamond_coords.length; i++) {
						wrCoords.write(diamond_coords[i]);
						if (i != diamond_coords.length - 1)
							wrCoords.newLine();
					}
					wrCoords.close();
				}
			} catch (IOException e) {
				System.err.println("Unable to create or write file");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Retrieves the coordinates respective to the entity.
	 * If the file does not exist, the method will fail.
	 * @param line The line in which the coordinates of the respective entity is written on.
	 * @return The array of coordinates.
	 */
	public static int[] getCoord(int line) {
		checkExist();
		// File exist and is ready to be read

		if (coordFile.canRead()) {
			try {

				FileInputStream rdCoords = new FileInputStream(coordFile);
				byte[] data = new byte[(int) coordFile.length()];

				// Read the entire file in one go
				rdCoords.read(data);
				rdCoords.close();
				String[] strLines = new String(data, "UTF-8").split("\n");
				String[] strCoords;

				// Get only the line for axe/boat coordinates or player
				// coordinate
				if (line == 1)
					strCoords = new String(strLines[0]).trim().split(",");
				else
					strCoords = new String(strLines[line]).trim().split(",");

				// Get the coordinates
				int[] coords = new int[strCoords.length];
				for (int i = 0; i < strCoords.length; i++) {
					coords[i] = Integer.parseInt(strCoords[i]);
				}
				return coords;
			} catch (FileNotFoundException e) {
				System.err.println("File does not exist");
				e.printStackTrace();
			} catch (IOException e) {
				System.err.println("No read access to file");
				e.printStackTrace();
			}
		} else {
			System.err.println("Error in reading file");
			return null;
		}

		return null;
	}

	/**
	 * Updates the coordinates of the entity by overwriting the entire file.
	 * If the file does not exist, the method will fail.
	 * @param newCoords The updated coordinates of the entity
	 * @param line The line to be rewritten with the updated coordinates.
	 */
	public static void overwriteFile(String newCoords, int line) {
		checkExist();
		try {
			BufferedReader oldCoordFile = new BufferedReader(new FileReader(coordFile));
			int count = 0;
			String ln = "";
			while ((ln += oldCoordFile.readLine()) != null) {
				count++;
				if (count == line) {
					ln += ln.replace(ln, newCoords);
				}
				ln += "\n";
			}
			oldCoordFile.close();
			
			PrintWriter clearCoordFile = new PrintWriter(coordFile);
			clearCoordFile.close();
			
			FileWriter owCoordFile = new FileWriter(coordFile);
			owCoordFile.write(ln);
			owCoordFile.close();
		} catch (FileNotFoundException e) {
			System.err.println("File does not exist");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("No read access to file");
			e.printStackTrace();
		}
	}

}
