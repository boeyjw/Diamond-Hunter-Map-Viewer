/* Get the position of player
 * and return the value
 * Get player sprite
 * */

package com.neet.DiamondHunter.MapViewer;

import com.neet.DiamondHunter.Entity.Player;
import com.neet.DiamondHunter.Manager.Content;

import javafx.scene.image.WritableImage;

public class ShowPlayer{
	
	private int row;
	private int col;
	
	/**
	 * Get default player position at start.
	 */
	public ShowPlayer() {
		getPlayerPosition();
	}
	
	//get the sprites
	public WritableImage getPlayer(){
		WritableImage player;
		
		player = (Player.onWater) ? 
				new ImageConversion(Content.PLAYER[4]).getWrImg() : 
					new ImageConversion(Content.PLAYER[0]).getWrImg();

		return player;
	}
	
	//get current position of the player
	public int[] getPlayerPosition(){
		int[] coordinate;
		//2 indicates line 2 which is the player's coordinate
		coordinate = WriteCoord.getCoord(2);
		row = coordinate[0];
		col = coordinate[1];
		
		return coordinate;
	}
	
	//update current position
	public void updatePlayerPosition(){
		String coords = Integer.toString(row) + "," + Integer.toString(col);
		WriteCoord.overwriteFile(coords,2);
	}
	
}