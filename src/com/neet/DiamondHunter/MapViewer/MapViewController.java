package com.neet.DiamondHunter.MapViewer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

public class MapViewController implements Initializable {
	
	private MapPane mp;
	private GraphicsContext gc;
	private TileInformation[][] tileInfo;
	
	@FXML
	private Canvas mvCanvas;
	@FXML
	private TilePane tileMapping;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mp = new MapPane();
		
		initMapCanvas();
		
		initTileMapping();
	}

	private void initMapCanvas() {
		mvCanvas.relocate(60, 90);
		mvCanvas.setWidth((double) MapPane.WIDTH);
		mvCanvas.setHeight((double) MapPane.HEIGHT);
		
		mp.loadTiles("/Tilesets/testtileset.gif");
		mp.loadMap("/Maps/testmap.map");
		gc = mvCanvas.getGraphicsContext2D();
		mp.drawImage(gc);
		
		mvCanvas.getGraphicsContext2D().drawImage(gc.getCanvas().snapshot(new SnapshotParameters(), null), 0, 0);
	}
	
	private void initTileMapping() {
		tileMapping.relocate(mvCanvas.getLayoutX() + 1, mvCanvas.getLayoutY() - 2);
		tileMapping.setPrefSize((double) (mp.getNumCols() * mp.getTileSize()), (double) (mp.getNumCols() * mp.getTileSize()));
		tileMapping.setPrefTileWidth((double) (mp.getTileSize()));
		tileMapping.setPrefTileHeight((double) (mp.getTileSize()));
		tileMapping.setPrefRows(mp.getNumRows());
		tileMapping.setPrefColumns(mp.getNumCols());

		tileInfo = new TileInformation[mp.getNumCols()][mp.getNumRows()];
		
		for(int row = 0; row < tileMapping.getPrefRows(); row++) {
			for(int col = 0; col < tileMapping.getPrefColumns(); col++) {
				Label tileCoord = new Label();
				tileCoord.setId("o" + Integer.toString(row) + "x" + Integer.toString(col));
				tileCoord.setVisible(false);
				tileInfo[row][col] = new TileInformation(mp.getTileImageFromMap(row, col));
				tileMapping.getChildren().add(tileCoord);
			}
		}
	}
	
}
