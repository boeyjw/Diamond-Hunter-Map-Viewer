package com.neet.DiamondHunter.MapViewer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This is a class to write coordinates of axe, boat, player and diamonds into a
 * text file namely Item-Coordinates.txt If the file does not exist, create the
 * file and write all default position.
 * 
 * @return The coordinates which is needed. Arrangement: line 1 :
 *         {@code AXE_xaxis, AXE_yaxis, BOAT_xaxis, BOAT_yaxis} line 2 :
 *         {@code player_xaxis, player_yaxis} line 3-17:
 *         {@code diamond_xaxis, diamond_yaxis}
 **/

public class WriteCoord {

	private static File coordFile = new File("Entity-Coordinates.txt");

	private static final String[] diamond_coords = { "20,20", "12,36", "28,4", "4,34", "28,19", "35,26", "38,36",
			"27,28", "20,30", "14,25", "4,21", "9,14", "4,3", "20,14", "13,20" };

	// check if coordinate file exist in path
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
						if (i != diamond_coords.length)
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
				String[] strCoords = {};

				// Get only the line for axe/boat coordinates or player
				// coordinate
				if (line == 1)
					strCoords = new String(strLines[line-1]).trim().split(",");
				else if (line == 2)
					strCoords = new String(strLines[line-1]).trim().split(",");
				else{
					for(int i = line-1; i < strLines.length; i++)
						strCoords = new String(strLines[i]).trim().split(",");
						System.out.println(strCoords);
				}

				// Get the coordinates
				int[] coords = new int[strCoords.length];
				for (int i = 0; i < strCoords.length; i++) {
					coords[i] = Integer.parseInt(strCoords[i]);
					System.out.println(coords[i]);
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

	// overwrite file to update position of items or player
	public static void overwriteFile(String data, int line) {
		try {
			System.out.println("ok1");
			BufferedReader coordFile = new BufferedReader(new FileReader("Entity-Coordinates.txt"));
			System.out.println("ok2");
			int count = 0;
			String l;
			while ((l = coordFile.readLine()) != null) {
				System.out.println("ok3");
				count++;
				if (count == line) {
					System.out.println("ok4");
					l.replace(l, data);
					System.out.println("ok5");
					break;
				}
			}
			coordFile.close();
		} catch (FileNotFoundException e) {
			System.err.println("File does not exist");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("No read access to file");
			e.printStackTrace();
		}
	}

}
