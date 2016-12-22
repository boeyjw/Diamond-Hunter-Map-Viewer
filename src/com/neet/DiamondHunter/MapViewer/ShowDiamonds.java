/* Get the position of Diamonds
 * and return the value
 * Get Diamonds sprite
 * */

package com.neet.DiamondHunter.MapViewer;
import com.neet.DiamondHunter.Manager.Content;

import javafx.scene.image.WritableImage;

/**
 * Handles diamond entity in the GridPane
 *
 */
public class ShowDiamonds implements EntityDisplay{

	private int[] coordinate;

	public ShowDiamonds(){
		coordinate = new int[30];
		getEntityPosition();
	}

	@Override
	public void getEntityPosition() {
		//3 indicates line 3 which is the Diamonds's coordinate
		coordinate = WriteCoord.getCoord(2);
		for(int i : coordinate) {
			System.out.print(i + " ");
		}
	}

	@Override
	public WritableImage getEntity(int type) {
		return new ImageConversion(Content.DIAMOND[0][0]).getWrImg();
	}

	@Override
	public boolean compareCoordinates(int row, int col, int type) {
		boolean flag = false;
		for(int i = 0; i < 15; i+=2){
			if(row == coordinate[i] && col == coordinate[i+1]) flag = true;
		} 
		return flag;
	}

	@Override
	public void updateEntityPosition() {
		String coords = Integer.toString(coordinate[0]) + "," + Integer.toString(coordinate[1]);
		WriteCoord.overwriteFile(coords,2);
	}

}