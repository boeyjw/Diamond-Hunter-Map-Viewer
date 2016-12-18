package com.neet.DiamondHunter.MapViewer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.GridPane;

public class MapViewController implements Initializable {
	
	@FXML
	private Canvas mvCanvas;
	@FXML
	private GridPane mapCoordinates;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		MapPane mp = new MapPane();
		mvCanvas.relocate(60, 90);
		mvCanvas.setWidth((double) (MapPane.WIDTH));
		mvCanvas.setHeight((double) (MapPane.HEIGHT));
		mvCanvas.getGraphicsContext2D().drawImage(mp.getCanvas(), 0, 0);
	}
}
