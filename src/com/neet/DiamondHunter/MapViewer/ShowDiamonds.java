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
		coordinate = new int[2];
		getEntityPosition();
	}

	@Override
	public void getEntityPosition() {
		//3 indicates line 3 which is the Diamonds's coordinate
		coordinate = WriteCoord.getCoord(3);
	}

	@Override
	public WritableImage getEntity(int type) {
		return new ImageConversion(Content.DIAMOND[0]).getWrImg();
	}

	@Override
	public boolean compareCoordinates(int row, int col, int type) {
		return (row == coordinate[0] && col == coordinate[1]) ? true : false;
	}

	@Override
	public void updateEntityPosition() {
		String coords = Integer.toString(coordinate[0]) + "," + Integer.toString(coordinate[1]);
		WriteCoord.overwriteFile(coords,3);
	}

}