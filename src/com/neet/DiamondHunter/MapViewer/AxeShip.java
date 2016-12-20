/* Get position of axe and boat
 * and return the value
 * Update position of axe and boat
 * Get axe and boat sprites
 * */

package com.neet.DiamondHunter.MapViewer;

import com.neet.DiamondHunter.Manager.Content;
import com.neet.DiamondHunter.Manager.ImageConversion;
import com.neet.DiamondHunter.Coordinates.WriteCoord;
import javafx.scene.image.WritableImage;

public class AxeShip {

	private WritableImage item;
	int type;
	static int[] boatPosition;
	static int[] axePosition;
	private int[] coordinates;
	
	int row;
	int col;
	
	//items
	private static final int BOAT = 0;
	private static final int AXE = 1;
	
	protected AxeShip(int type) {
		this.type = type;
	}
	
	protected AxeShip() {
		
	}

	//get current position of the axe and boat
	protected void getItemPosition(){
		//1 indicates line 1 which is the axe and boat coordinates
		coordinates = WriteCoord.getCoord(1);
		boatPosition[0] = coordinates[2];
		boatPosition[1] = coordinates[3];
		axePosition[0] = coordinates[0];
		axePosition[1] = coordinates[1];
	}
	
	//updates the game on the new position of the axe and boat
	protected void updateItemPosition(){
		String coords = Integer.toString(axePosition[0]) + "," + Integer.toString(axePosition[1]) + "," + Integer.toString(boatPosition[0]) + "," + Integer.toString(boatPosition[1]);
		WriteCoord.overwriteFile(coords,1);
	}
	
	//get the sprites
	protected WritableImage getItem(){
		if(type == BOAT){
			row = boatPosition[0];
			col = boatPosition[1];
		}
		if(type == AXE){
			row = axePosition[0];
			col = axePosition[1];
		}
		
		item = new ImageConversion(Content.ITEMS[row][col]).getWrImg();	
		return item;

	}
	
}
