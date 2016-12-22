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
	private static int coordCount = 0;

	public ShowDiamonds(){
		getEntityPosition();
	}

	@Override
	public void getEntityPosition() {
		//3 indicates line 3 which is the Diamonds's coordinate
		coordinate = WriteCoord.getCoord(2);
	}

	@Override
	public WritableImage getEntity(int type) {
		return new ImageConversion(Content.DIAMOND[0][0]).getWrImg();
	}

	@Override
	public boolean compareCoordinates(int row, int col, int type) {
		if(coordCount < coordinate.length - 1) {
			for(int i = 0; i < coordinate.length; i += 2) {
				if(row == coordinate[i] && col == coordinate[i + 1]) {
					coordCount += 2;
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void updateEntityPosition() {
		String coords = Integer.toString(coordinate[0]) + "," + Integer.toString(coordinate[1]);
		WriteCoord.overwriteFile(coords,3);
	}

}