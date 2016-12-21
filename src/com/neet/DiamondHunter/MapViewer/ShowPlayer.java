/* Get the position of player
 * and return the value
 * Get player sprite
 * */

package com.neet.DiamondHunter.MapViewer;

import com.neet.DiamondHunter.Entity.Player;
import com.neet.DiamondHunter.Manager.Content;
import com.neet.DiamondHunter.Manager.ImageConversion;

import javafx.scene.image.WritableImage;

public class ShowPlayer{
	
	public int[] coordinate;
	public int row;
	public int col;
	public WritableImage player;

	public ShowPlayer(){
		coordinate = new int[2];
		getPlayerPosition();
	}
	
	//get the sprite
	public WritableImage getPlayer(){		
		player = Player.onWater ? new ImageConversion(Content.PLAYER[4]).getWrImg() : new ImageConversion(Content.PLAYER[0]).getWrImg();
		return player;
	}
	
	//get current position of the player
	public void getPlayerPosition(){
		//2 indicates line 2 which is the player's coordinate
		coordinate = WriteCoord.getCoord(2);
		row = coordinate[0];
		col = coordinate[1];
	}
	
	//update current position
	public void updatePlayerPosition(){
		String coords = Integer.toString(row) + "," + Integer.toString(col);
		WriteCoord.overwriteFile(coords,2);
	}
	
}