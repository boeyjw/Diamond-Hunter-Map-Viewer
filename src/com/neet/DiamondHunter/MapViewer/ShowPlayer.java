/* Get the position of player
 * and return the value
 * Get player sprite
 * */

package com.neet.DiamondHunter.MapViewer;

import com.neet.DiamondHunter.Entity.Player;
import com.neet.DiamondHunter.Manager.Content;

import javafx.scene.image.WritableImage;

/**
 * Handles player entity in the GridPane
 *
 */
public class ShowPlayer implements EntityDisplay {

	private int[] coordinate;

	public ShowPlayer(){
		coordinate = new int[2];
		getEntityPosition();
		updateEntityPosition();
	}

	@Override
	public void getEntityPosition() {
		//1 indicates line 1 which is the player's coordinate
		coordinate = WriteCoord.getCoord(1);
	}

	@Override
	public WritableImage getEntity(int type) {
		WritableImage player = Player.onWater ? new ImageConversion(Content.PLAYER[0][5]).getWrImg() : new ImageConversion(Content.PLAYER[0][0]).getWrImg();
		return player;
	}

	@Override
	public boolean compareCoordinates(int row, int col, int type) {
		return (row == coordinate[0] && col == coordinate[1]) ? true : false;
	}

	public void updateEntityPosition() {
		String coords = Integer.toString(coordinate[0]) + "," + Integer.toString(coordinate[1]);
		WriteCoord.overwriteFile(coords,1);
	}

}