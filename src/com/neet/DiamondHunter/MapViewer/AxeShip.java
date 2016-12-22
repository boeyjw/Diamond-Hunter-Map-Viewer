/* Get position of axe and boat
 * and return the value
 * Update position of axe and boat
 * Get axe and boat sprites
 * */

package com.neet.DiamondHunter.MapViewer;

import com.neet.DiamondHunter.Manager.Content;

import javafx.scene.image.WritableImage;

/**
 * Handles axe and ship entity for the GridPane.
 *
 */
public class AxeShip implements EntityDisplay {
	
	private int[] coordinates;

	//items
	public static final int BOAT = 0;
	public static final int AXE = 1;

	public AxeShip() {
		coordinates = new int[4];
		getEntityPosition();
	}
	
	@Override
	public void getEntityPosition() {
		/*
		 * 1 indicates line 1 which is the axe and boat coordinates.
		 * Array: axe_xaxis, axe_yaxis, boat_xaxis, boat_yaxis
		 */
		coordinates = WriteCoord.getCoord(0);
	}
	
	@Override
	public WritableImage getEntity(int type) {
		if(type == BOAT){
			return new ImageConversion(Content.ITEMS[1][0]).getWrImg();
		}
		else if(type == AXE){
			return new ImageConversion(Content.ITEMS[1][1]).getWrImg();
		}
		
		return null;
	}
	
	@Override
	public boolean compareCoordinates(int row, int col, int type) {
		if(type == BOAT) {
			return (row == coordinates[2] && col == coordinates[3]) ? true : false;
		}
		else if(type == AXE) {
			return (row == coordinates[0] && col == coordinates[1]) ? true : false;
		}
		
		return false;
	}
	
	@Override
	public void updateEntityPosition() {
		String coords = Integer.toString(coordinates[0]) + "," + Integer.toString(coordinates[1]) + "," 
						+ Integer.toString(coordinates[0]) + "," + Integer.toString(coordinates[1]);
		WriteCoord.overwriteFile(coords,1);
	}

}
