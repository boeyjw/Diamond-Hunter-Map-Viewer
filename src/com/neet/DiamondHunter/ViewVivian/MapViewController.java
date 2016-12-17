package com.neet.DiamondHunter.ViewVivian;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;

public class MapViewController implements Initializable {
	
	@FXML
	private Canvas mvCanvas;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		MapPane mp = new MapPane();
		mvCanvas.setWidth((double) (MapPane.WIDTH));
		mvCanvas.setHeight((double) (MapPane.HEIGHT2));
		mvCanvas.getGraphicsContext2D().drawImage(mp.getCanvas(), 0, 0);
	}
}
