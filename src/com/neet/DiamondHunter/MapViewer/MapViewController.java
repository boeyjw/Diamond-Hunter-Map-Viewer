package com.neet.DiamondHunter.MapViewer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

public class MapViewController implements Initializable {

	private AxeShip as;
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
	@FXML
	private TextArea tileInfoText;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		mp = new MapPane();

		initMapCanvas();

		mapStack.relocate(20, 20);
		mapStack.setPrefSize((double) (mp.getNumRows() * mp.getTileSize()),
				(double) (mp.getNumCols() * mp.getTileSize()));

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
		for (int i = 0; i < tileInfo.length; i++) {
			tileMapping.getColumnConstraints().add(new ColumnConstraints((double) (mp.getTileSize())));
			tileMapping.getRowConstraints().add(new RowConstraints((double) (mp.getTileSize())));
		}

		for (int row = 0; row < mp.getNumRows(); row++) {
			for (int col = 0; col < mp.getNumCols(); col++) {
				tileInfo[row][col] = new TileInformation(mp.getTileImageFromMap(row, col));
				addPane(col, row);
			}
		}
	}

	public void saveCoor() {
		as = new AxeShip();
		as.updateItemPosition();
	}

	public void exitMapView() {
		System.exit(0);
	}

	private void addPane(int colIndex, int rowIndex) {
		Label label = new Label();

		String toolText = "Coordinate: " + Integer.toString(rowIndex + 1) + " x " + Integer.toString(colIndex + 1)
				+ "\nTile Image: ";

		if (tileInfo[rowIndex][colIndex].getTileImageType() == TileInformation.GRASS) {
			toolText += "Grassy tile";
		} else if (tileInfo[rowIndex][colIndex].getTileImageType() == TileInformation.BUSH) {
			toolText += "Bushy tile";
		} else if (tileInfo[rowIndex][colIndex].getTileImageType() == TileInformation.FLOWER) {
			toolText += "Flowery tile";
		} else if (tileInfo[rowIndex][colIndex].getTileImageType() == TileInformation.GREENTREE) {
			toolText += "Big green tree";
		} else if (tileInfo[rowIndex][colIndex].getTileImageType() == TileInformation.DEADTREE) {
			toolText += "Dead tree";
		} else if (tileInfo[rowIndex][colIndex].getTileImageType() == TileInformation.WATER) {
			toolText += "Water";
		}

		if (tileInfo[rowIndex][colIndex].isNormal()) {
			toolText += "\nWalkable";
		} else {
			toolText += "\nBlocked";
		}

		final String tt = toolText;

		label.setOnMouseEntered(e -> {
			tileInfoText.setText(tt);
		});
		tileMapping.add(label, colIndex, rowIndex);
	}
}
