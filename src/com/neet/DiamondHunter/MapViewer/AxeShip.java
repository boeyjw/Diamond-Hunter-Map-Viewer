/* Get position of axe and boat
 * and return the value
 * Update position of axe and boat
 * Get axe and boat sprites
 * */

package com.neet.DiamondHunter.MapViewer;

import com.neet.DiamondHunter.Manager.Content;

import javafx.scene.image.WritableImage;

public class AxeShip {

	private WritableImage item;
	public int[] boatPosition;
	public int[] axePosition;
	private int[] coordinates;
	
	private int col;
	
	//items
	public static final int BOAT = 0;
	public static final int AXE = 1;
	
	public AxeShip() {
		boatPosition = new int[2];
		axePosition = new int[2];
		coordinates = new int[4];
		getItemPosition();
	}

	//get current position of the axe and boat
	public void getItemPosition(){
		//1 indicates line 1 which is the axe and boat coordinates
		coordinates = WriteCoord.getCoord(1);
		boatPosition[0] = coordinates[2];
		boatPosition[1] = coordinates[3];
		axePosition[0] = coordinates[0];
		axePosition[1] = coordinates[1];
	}
	
	//updates the game on the new position of the axe and boat
	public void updateItemPosition(){
		String coords = Integer.toString(axePosition[0]) + "," + Integer.toString(axePosition[1]) + "," + Integer.toString(boatPosition[0]) + "," + Integer.toString(boatPosition[1]);
		WriteCoord.overwriteFile(coords,1);
	}
	
	//get the sprites
	public WritableImage getItem(int type){
		if(type == BOAT){
			col = 0;
		}
		if(type == AXE){
			col = 1;
		}
		
		item = new ImageConversion(Content.ITEMS[1][col]).getWrImg();	
		return item;

	}
	
}
