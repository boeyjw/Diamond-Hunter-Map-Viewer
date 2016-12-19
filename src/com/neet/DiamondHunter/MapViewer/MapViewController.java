package com.neet.DiamondHunter.MapViewer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

public class MapViewController implements Initializable {
	
	private MapPane mp;
	private GraphicsContext gc;
	private TileInformation[][] tileInfo;
	
	@FXML
	private AnchorPane mainPane;
	@FXML
	private Canvas mvCanvas;
	@FXML
	private GridPane tileMapping;
	@FXML
	private StackPane mapStack;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mp = new MapPane();
		
		initMapCanvas();
		
		mapStack.relocate(20, 20);
		mapStack.setPrefSize((double) (mp.getNumRows() * mp.getTileSize()), (double) (mp.getNumCols() * mp.getTileSize()));
		
		mainPane.setMinSize(mapStack.getPrefWidth() + 100, mapStack.getPrefHeight() + 100);
		
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
		for(int i = 0; i < tileInfo.length; i++) {
			tileMapping.getColumnConstraints().add(new ColumnConstraints((double) (mp.getTileSize())));
			tileMapping.getRowConstraints().add(new RowConstraints((double) (mp.getTileSize())));
		}
		
		for(int row = 0; row < mp.getNumRows(); row++) {
			for(int col = 0; col < mp.getNumCols(); col++) {
				tileInfo[row][col] = new TileInformation(mp.getTileImageFromMap(row, col));
				addPane(col, row);
			}
		}
	}
	
	private void addPane(int colIndex, int rowIndex) {
        Pane pane = new Pane();
        pane.setOnMouseEntered(e -> {
            System.out.println("Box is: " + tileInfo[rowIndex][colIndex].getTileImageType());
        });
        tileMapping.add(pane, colIndex, rowIndex);
    }
}
