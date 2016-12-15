package com.neet.DiamondHunter.ViewVivian;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public class MapPane extends Pane {

	// dimensions
	// HEIGHT is the playing area size
	// HEIGHT2 includes the bottom window
	public static final int WIDTH = 128;
	public static final int HEIGHT = 128;
	public static final int HEIGHT2 = HEIGHT + 16;
	public static final int SCALE = 3;
	private MapCanvas canvas;

	public MapPane() {
		setPrefSize(WIDTH * SCALE, HEIGHT2 * SCALE);
		canvas = new MapCanvas(16, WIDTH * SCALE, HEIGHT2 * SCALE);
		canvas.loadTiles("/Tilesets/testtileset.gif");
		canvas.loadMap("/Maps/testmap.map");
		GraphicsContext gc = canvas.getGraphicsContext2D();
		canvas.drawImage(gc);
		getChildren().add(canvas);

	}
}
