package com.neet.DiamondHunter.MapViewer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.neet.DiamondHunter.Entity.Player;
import com.neet.DiamondHunter.Coordinates.WriteCoord;
import com.neet.DiamondHunter.Manager.Content;
import com.neet.DiamondHunter.Manager.ImageConversion;

import javafx.scene.image.WritableImage;

public class ShowPlayer{
	
	int[] coordinate;
	int row;
	int col;

	//get the sprites
	public WritableImage getPlayer(){
		ImageConversion ic;
		
		if(Player.onWater == true){
			ic = new ImageConversion(Content.PLAYER[4]);
		}else
			ic = new ImageConversion(Content.PLAYER[0]);
		return ImageConversion.getWrImg();
	}
	
	//get current position of the player
	public int[] getPlayerPosition(){
		coordinate = WriteCoord.getCoord("Entity-Coordinates","40,40");
		row = coordinate[0];
		col = coordinate[1];
		
		return coordinate;
	}
	
	//update current position
	public void updatePlayerPosition() throws IOException{
		File entityCoordFile = new File("Resources/Sprites/Entity-Coordinates.txt");
		FileWriter newEntityCoordFile;
		
		String coords="";
		coords = Integer.toString(row) + "," + Integer.toString(col);
		
		if(entityCoordFile.exists()){
			newEntityCoordFile = new FileWriter(entityCoordFile,false);
			newEntityCoordFile.write(coords);
			newEntityCoordFile.close();
		}
	}
	
}