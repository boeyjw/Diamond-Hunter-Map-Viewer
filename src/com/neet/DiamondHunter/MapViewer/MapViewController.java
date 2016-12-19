package com.neet.DiamondHunter.MapViewer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

public class MapViewController implements Initializable {
	
	private MapPane mp;
	private GraphicsContext gc;
	private TileInformation[][] tileInfo;
	
	@FXML
	private Canvas mvCanvas;
	@FXML
	private GridPane tileMapping;
	@FXML
	private StackPane mapStack;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mp = new MapPane();
		mapStack.relocate(60, 90);
		mapStack.setPrefSize((double) (mp.getNumRows() * mp.getTileSize() - 15), (double) (mp.getNumCols() * mp.getTileSize() - 15));
		
		initMapCanvas();
		
		initTileMapping();
	}

	private void initMapCanvas() {
		mvCanvas.setWidth((double) MapPane.WIDTH);
		mvCanvas.setHeight((double) MapPane.HEIGHT);
		
		mp.loadTiles("/Tilesets/testtileset.gif");
		mp.loadMap("/Maps/testmap.map");
		gc = mvCanvas.getGraphicsContext2D();
		mp.drawImage(gc);
		
		mvCanvas.getGraphicsContext2D().drawImage(gc.getCanvas().snapshot(new SnapshotParameters(), null), 0, 0);
	}
	
	private void initTileMapping() {
		tileInfo = new TileInformation[mp.getNumRows()][mp.getNumCols()];
		
		//Is out by a column for some odd reason
		for(int row = 0; row < mp.getTileSize() - 3; row++) {
			tileMapping.getColumnConstraints().add(new ColumnConstraints((double) (mp.getTileSize())));
			tileMapping.getRowConstraints().add(new RowConstraints((double) (mp.getTileSize())));
			for(int col = 0; col < mp.getTileSize() - 1; col++) {
				Label lb = (col == 0 || row == 0) ? new Label(Integer.toString(col)) : new Label();
				tileMapping.add(lb, col, row);
				tileInfo[row][col] = new TileInformation(mp.getTileImageFromMap(row, col));
			}
		}
	}
	
}
