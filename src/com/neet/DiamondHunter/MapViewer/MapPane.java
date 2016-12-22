package com.neet.DiamondHunter.MapViewer;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 * Loads the tiles and maps to be drawn onto the canvas.
 * Class specific to handle the game map only.
 *
 */
public class MapPane {

	// position
	private int x;
	private int y;

	// map
	private int[][] map;

	private int tileSize;
	private int numRows;
	private int numCols;

	// tileset
	private BufferedImage tileset;
	private int numTilesAcross;
	private WritableImage[][] tiles;

	// dimensions
	// HEIGHT is the playing area size
	public static final int WIDTH = 640;
	public static final int HEIGHT = 640;

	public MapPane() {
		//The number of pixels of each tile
		tileSize = 16;
	}
	
	/**
	 * Load the tiles and store a reference over each tile.
	 * @param s The tile image URI
	 */
	public void loadTiles(String s) {

		try {

			tileset = ImageIO.read(getClass().getResourceAsStream(s));
			numTilesAcross = tileset.getWidth() / tileSize;
			tiles = new WritableImage[2][numTilesAcross];
			BufferedImage subimage1, subimage2;
			for (int col = 0; col < numTilesAcross; col++) {
				subimage1 = tileset.getSubimage(col * tileSize, 0, tileSize, tileSize);
				subimage2 = tileset.getSubimage(col * tileSize, tileSize, tileSize, tileSize);

				WritableImage wr1, wr2;
				wr1 = wr2 = null;
				if (subimage1 != null && subimage2 != null) {
					wr1 = new WritableImage(subimage1.getWidth(), subimage1.getHeight());
					wr2 = new WritableImage(subimage2.getWidth(), subimage2.getHeight());
					PixelWriter pw1 = wr1.getPixelWriter();
					PixelWriter pw2 = wr2.getPixelWriter();
					for (int x = 0; x < subimage1.getWidth(); x++) {
						for (int y = 0; y < subimage1.getHeight(); y++) {
							pw1.setArgb(x, y, subimage1.getRGB(x, y));
						}
					}
					for (int x = 0; x < subimage2.getWidth(); x++) {
						for (int y = 0; y < subimage2.getHeight(); y++) {
							pw2.setArgb(x, y, subimage2.getRGB(x, y));
						}
					}
				}
				tiles[0][col] = wr1;
				tiles[1][col] = wr2;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Gets the reference to the resource it should load when loading tiles and initialises tile information.
	 * Coordinate reference:-<br>
	 * - Player interactable (Tile.BLOCKED): (21 - Dead tree =AXE choppable=), (22 - Water tile =BOAT crossable=)<br>
	 * - Obstacle (Tile.BLOCKED): (20 - Green tree)<br>
	 * - Walkable (Tile.NORMAL): (1 - Normal green tile), (2 - Bush tile), (3 - Flower tile)
	 * @param s Map resource URI
	 */
	public void loadMap(String s) {
		try {

			InputStream in = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
			map = new int[numRows][numCols];
			for (int row = 0; row < numRows; row++) {
				String line = br.readLine();
				if(line != null) {
					String[] tokens = line.split("\\s+");
					for(int col = 0; col < numCols; col++) {
						map[row][col] = Integer.parseInt(tokens[col]);
					}
				}
				else {
					System.err.println("Invalid or no URI input. Terminating program...");
					System.exit(1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Draws the map based on the mapping data loaded from {@link com.neet.DiamondHunter.MapViewer.MapPane#loadMap(String)}.
	 * @param gc The graphic context of the canvas.
	 */
	public void drawImage(GraphicsContext gc) {

		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				if (map[row][col] == 0)
					continue;

				int rc = map[row][col];
				int r = rc / numTilesAcross;
				int c = rc % numTilesAcross;
				gc.save();
				gc.drawImage(tiles[r][c], x + col * tileSize, y + row * tileSize);
			}
		}
	}
	
	/**
	 * Retrieves the size of each tile.
	 * Used to uniform the GridPane layout.
	 * @return The number of pixels of each tile
	 */
	public int getTileSize() {
		return tileSize;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumCols() {
		return numCols;
	}
	
	/**
	 * Retrieves the tile image type from the mapping data loaded from {@link com.neet.DiamondHunter.MapViewer.MapPane#loadMap(String)}.
	 * @param r The row index of the GridPane
	 * @param c The column index of the GridPane
	 * @return The tile image reference
	 */
	public int getTileImageFromMap(int r, int c) {
		return map[r][c];
	}
}
