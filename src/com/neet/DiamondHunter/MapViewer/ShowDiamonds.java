/* Get the position of Diamonds
 * and return the value
 * Get Diamonds sprite
 * */

package com.neet.DiamondHunter.MapViewer;
import com.neet.DiamondHunter.Manager.Content;

import javafx.scene.image.WritableImage;

public class ShowDiamonds{

	public int[] coordinate;
	public int row;
	public int col;
	public WritableImage Diamonds;

	public ShowDiamonds(){
		coordinate = new int[2];
		getDiamondsPosition();
	}
	
	//get the sprite
	public WritableImage getDiamonds(){		
		return new ImageConversion(Content.DIAMOND[0]).getWrImg();
	}
	
	//get current position of the Diamonds
	public void getDiamondsPosition(){
		//3 indicates line 3 which is the Diamonds's coordinate
		coordinate = WriteCoord.getCoord(3);
		row = coordinate[0];
		col = coordinate[1];
	}
	
	//update current position
	public void updateDiamondsPosition(){
		String coords = Integer.toString(row) + "," + Integer.toString(col);
		WriteCoord.overwriteFile(coords,2);
	}
	
}