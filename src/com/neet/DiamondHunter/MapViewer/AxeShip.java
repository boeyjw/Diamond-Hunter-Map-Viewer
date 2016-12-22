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
public class AxeShip extends ASPositionUpdate {
	
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
		 * 0 indicates line 0 which is the axe and boat coordinates.
		 * Array: axe_xaxis, axe_yaxis, boat_xaxis, boat_yaxis
		 */
		coordinates = WriteCoord.getCoord(1);
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
	public void updateEntityPosition(int ar, int ac, int br, int bc) {
		
		coordinates[0] = ar;
		coordinates[1] = ac;
		coordinates[2] = br;
		coordinates[3] = bc;
		
		String coords = Integer.toString(ar) + "," + Integer.toString(ac) + "," 
						+ Integer.toString(br) + "," + Integer.toString(bc);
		System.out.println(coords);
		WriteCoord.overwriteFile(coords,0);
}

}
