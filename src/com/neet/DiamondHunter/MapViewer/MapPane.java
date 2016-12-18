package com.neet.DiamondHunter.MapViewer;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;

public class MapPane extends Pane {

	// dimensions
	// HEIGHT is the playing area size
	public static final int WIDTH = 638; //638
	public static final int HEIGHT = 625; //625
	private MapCanvas canvas;

	public MapPane() {
		setPrefSize(WIDTH , HEIGHT);	
		canvas = new MapCanvas(16, WIDTH, HEIGHT);
		canvas.loadTiles("/Tilesets/testtileset.gif");
		canvas.loadMap("/Maps/testmap.map");
		GraphicsContext gc = canvas.getGraphicsContext2D();
		canvas.drawImage(gc);
		getChildren().add(canvas);
	}
	
	public WritableImage getCanvas() {
		return canvas.snapshot(new SnapshotParameters(), null);
	}
	
	public int getNumRows() {
		return canvas.getNumRows();
	}

	public int getNumCols() {
		return canvas.getNumCols();
	}
	
	public int getTileSize() {
		return canvas.getTileSize();
	}
}
