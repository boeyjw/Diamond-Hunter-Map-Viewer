/* Get the position of axe and ship
 * and return the value
 * */

package com.neet.DiamondHunter.MapViewer;

import com.neet.DiamondHunter.Manager.Content;
import com.neet.DiamondHunter.Manager.ImageConversion;
import com.neet.DiamondHunter.Entity.Player;
import com.neet.DiamondHunter.GameState.PlayState;

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AxeShip {

	private WritableImage item;
	private int type;
	private int[] boatPosition;
	private int[] axePosition;
	private int[] coordinates;
	
	int row;
	int col;
	
	//items
	private static final int BOAT = 0;
	private static final int AXE = 1;
	
	private AxeShip(WritableImage item, int type) {
		this.item = item;
		this.type = type;
	}
	
	private int getType() { return type; }
	
	//get current position of the axe and boat
	private int[] getItemPosition(){
		
		PlayState ps = new PlayState(null);
		coordinates = ps.getItemCoord();
		
		if(type == BOAT){
			row = boatPosition[0] = coordinates[2];
			col = boatPosition[1] = coordinates[3];
			
			return boatPosition;
			
		}else if(type == AXE){
			row = axePosition[0] = coordinates[0];
			col = axePosition[1] = coordinates[1];
			
			return axePosition;
		}else return null;
	}
	
	//updates the game on the new position of the axe and boat
	private void updateItemPosition() throws IOException{
		File itemCoordFile = new File("Resources/Sprites/Item-Coordinates.txt");
		FileWriter newItemCoordFile;
		
		String coords="";
		coords = Integer.toString(axePosition[0]) + "," + Integer.toString(axePosition[1]) + "," + Integer.toString(boatPosition[0]) + "," + Integer.toString(boatPosition[1]);
		
		if(itemCoordFile.exists()){
			newItemCoordFile = new FileWriter(itemCoordFile,false);
			newItemCoordFile.write(coords);
			newItemCoordFile.close();
		}
	}
	
	//get the sprites
	private WritableImage getItem(){
		ImageConversion ic;
		
		ic = new ImageConversion(Content.ITEMS[row][col]);
			
		return ImageConversion.getWrImg();
	}
	
}
