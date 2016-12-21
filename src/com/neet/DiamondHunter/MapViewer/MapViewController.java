package com.neet.DiamondHunter.MapViewer;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.neet.DiamondHunter.Main.Game;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

/**
 * Main controller for the interface.
 * Any interaction in the MapViewInterface.fxml is computed here.
 *
 */
public class MapViewController implements Initializable {
	
	private AxeShip as;
	private ShowPlayer sp;
	private MapPane mp;
	private GraphicsContext gc;
	private TileInformation[][] tileInfo;
	boolean isLaunchedMainGame;
	
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
		//At launch of Map Viewer, the game itself is never launched
		isLaunchedMainGame = false;
		//MapPane has all the loaders for the map
		mp = new MapPane();
		as = new AxeShip();
		sp = new ShowPlayer();
		
		//The tile information display box is never editable
		tileInfoText.setEditable(false);
		initMapCanvas();
		
		//Initialises the size of the StackPane that contains the map in canvas and GridPane
		mapStack.relocate(20, 20);
		mapStack.setPrefSize((double) (mp.getNumRows() * mp.getTileSize()), (double) (mp.getNumCols() * mp.getTileSize()));
		
		/*
		 * This is the base size of the entire application.
		 * The AnchorPain (main window) is adjusted based on the size of the map.
		 * However, this is FXML overridden if the application grows.
		 */
		mainPane.setMinSize(mapStack.getPrefWidth() + 100, mapStack.getPrefHeight() + 100);
		
		WriteCoord.checkExist();
		
		initTileMapping();
	}
	
	/**
	 * Initialises the map in a non-FXML canvas and draws onto FXML canvas in a whole.
	 */
	private void initMapCanvas() {
		mvCanvas.setWidth((double) MapPane.WIDTH);
		mvCanvas.setHeight((double) MapPane.HEIGHT);

		mp.loadTiles("/Tilesets/testtileset.gif");
		mp.loadMap("/Maps/testmap.map");
		gc = mvCanvas.getGraphicsContext2D();
		mp.drawImage(gc);

		mvCanvas.getGraphicsContext2D().drawImage(gc.getCanvas().snapshot(new SnapshotParameters(), null), 0, 0);
	}
	
	/**
	 * Initialises the grid on top of the map that handles input validation and movement of boat and axe.
	 */
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
	
	/**
	 * Adds a label to each tile that contains information of the current tile.
	 * @param colIndex The column index of the GridPane.
	 * @param rowIndex The row index of the GridPane.
	 */
	private void addPane(int colIndex, int rowIndex) {
		Label label = new Label();
		label.setUserData(tileInfo[rowIndex][colIndex]);
		String tileText = "Coordinate: " + Integer.toString(rowIndex + 1) + " x " + Integer.toString(colIndex + 1) + "\nTile Image: ";

		if (tileInfo[rowIndex][colIndex].getTileImageType() == TileInformation.GRASS) {
			tileText += "Grassy tile";
		} else if (tileInfo[rowIndex][colIndex].getTileImageType() == TileInformation.BUSH) {
			tileText += "Bushy tile";
		} else if (tileInfo[rowIndex][colIndex].getTileImageType() == TileInformation.FLOWER) {
			tileText += "Flowery tile";
		} else if (tileInfo[rowIndex][colIndex].getTileImageType() == TileInformation.GREENTREE) {
			tileText += "Big green tree";
		} else if (tileInfo[rowIndex][colIndex].getTileImageType() == TileInformation.DEADTREE) {
			tileText += "Dead tree";
		} else if (tileInfo[rowIndex][colIndex].getTileImageType() == TileInformation.WATER) {
			tileText += "Water";
		}
		
		//display boat on top of tile
		if(colIndex == as.boatPosition[1] && rowIndex == as.boatPosition[0]){
			label.setGraphic(new ImageView(as.getItem(AxeShip.BOAT)));
			tileInfo[rowIndex][colIndex].setEntity(true);
			tileText += "\nA boat!";
			label.setOnDragDetected(e -> {
				Dragboard db = label.startDragAndDrop(TransferMode.ANY);
				e.consume();
			});
		}
		//display axe on top of tile
		if(colIndex == as.axePosition[1] && rowIndex == as.axePosition[0]){
			label.setGraphic(new ImageView(as.getItem(AxeShip.AXE)));
			tileInfo[rowIndex][colIndex].setEntity(true);
			tileText += "\nAn axe!";
		}
		//display player initial position on map
		if(colIndex == sp.coordinate[1] && rowIndex == sp.coordinate[0]){
			label.setGraphic(new ImageView(sp.getPlayer()));
			tileInfo[rowIndex][colIndex].setEntity(true);
			tileText += "\nYou are here!";
		}
		
		
		
		if (tileInfo[rowIndex][colIndex].isNormal()) {
			tileText += "\nWalkable";
		} else {
			tileText += "\nBlocked";
		}

		final String tt = tileText;

		label.setOnMouseEntered(e -> {
			tileInfoText.setText(tt);
		});
		tileMapping.add(label, colIndex, rowIndex);
	}
	
	@FXML
	private void saveCoor() {
		as = new AxeShip();
		as.updateItemPosition();
	}
	
	/**
	 * Exits the game and let's the garbage collector release the resources held by the application.
	 */
	@FXML
	private void exitMapView() {
		System.exit(0);
	}
	
	/**
	 * Launched the game if the game has never been launched during the lifecycle of Map Viewer application.
	 * If the game is launched at most once, the game will be kept alive throughout the lifecycle of the Map Viewer application.
	 * Resources are not released throughout the lifecycle.
	 */
	@FXML
	private void playGame() {
		if(!isLaunchedMainGame) {
			Game.runDHMainGame();
			Game.getWindow().setAutoRequestFocus(true);
			isLaunchedMainGame = true;
		}
		else {
			Game.getWindow().setVisible(true);
		}
	}
}
