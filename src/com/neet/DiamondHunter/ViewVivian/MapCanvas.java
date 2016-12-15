package com.neet.DiamondHunter.ViewVivian;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import com.neet.DiamondHunter.Main.GamePanel;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class MapCanvas extends Canvas {

	// position
	private int x;
	private int y;
	private int xdest;
	private int ydest;
	private int speed;
	private boolean moving;

	// bounds
	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;

	// map
	private int[][] map;
	private int tileSize;
	private int numRows;
	private int numCols;
	private int width;
	private int height;

	// tileset
	private BufferedImage tileset;
	private int numTilesAcross;
	private Tile[][] tiles;

	// drawing
	private int rowOffset;
	private int colOffset;
	private int numRowsToDraw;
	private int numColsToDraw;

	public MapCanvas(int tileSize, double width, double height) {
		super(width, height);
		this.tileSize = tileSize;
		numRowsToDraw = MapPane.HEIGHT / tileSize + 8;
		numColsToDraw = MapPane.WIDTH / tileSize + 8;
		speed = 4;
	}

	public void loadTiles(String s) {

		try {

			tileset = ImageIO.read(getClass().getResourceAsStream(s));
			numTilesAcross = tileset.getWidth() / tileSize;
			tiles = new Tile[2][numTilesAcross];

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
				tiles[0][col] = new Tile(wr1, Tile.NORMAL);
				tiles[1][col] = new Tile(wr2, Tile.BLOCKED);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void loadMap(String s) {

		try {

			InputStream in = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
			map = new int[numRows][numCols];
			width = numCols * tileSize;
			height = numRows * tileSize;

			xmin = GamePanel.WIDTH - width;
			xmin = -width;
			xmax = 0;
			ymin = GamePanel.HEIGHT - height;
			ymin = -height;
			ymax = 0;

			String delims = "\\s+";
			for (int row = 0; row < numRows; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for (int col = 0; col < numCols; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int getTileSize() {
		return tileSize;
	}

	public int getx() {
		return x;
	}

	public int gety() {
		return y;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumCols() {
		return numCols;
	}

	public int getType(int row, int col) {
		int rc = map[row][col];
		int r = rc / numTilesAcross;
		int c = rc % numTilesAcross;
		return tiles[r][c].getType();
	}

	public int getIndex(int row, int col) {
		return map[row][col];
	}

	public boolean isMoving() {
		return moving;
	}

	public void setTile(int row, int col, int index) {
		map[row][col] = index;
	}

	public void replace(int i1, int i2) {
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				if (map[row][col] == i1)
					map[row][col] = i2;
			}
		}
	}

	public void setPosition(int x, int y) {
		xdest = x;
		ydest = y;
	}

	public void setPositionImmediately(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void fixBounds() {
		if (x < xmin)
			x = xmin;
		if (y < ymin)
			y = ymin;
		if (x > xmax)
			x = xmax;
		if (y > ymax)
			y = ymax;
	}

	public void update() {
		if (x < xdest) {
			x += speed;
			if (x > xdest) {
				x = xdest;
			}
		}
		if (x > xdest) {
			x -= speed;
			if (x < xdest) {
				x = xdest;
			}
		}
		if (y < ydest) {
			y += speed;
			if (y > ydest) {
				y = ydest;
			}
		}
		if (y > ydest) {
			y -= speed;
			if (y < ydest) {
				y = ydest;
			}
		}

		fixBounds();

		colOffset = -this.x / tileSize;
		rowOffset = -this.y / tileSize;

		if (x != xdest || y != ydest)
			moving = true;
		else
			moving = false;

	}

	public void drawImage(GraphicsContext gc) {

		for (int row = rowOffset; row < rowOffset + numRowsToDraw; row++) {

			if (row >= numRows)
				break;

			for (int col = colOffset; col < colOffset + numColsToDraw; col++) {

				if (col >= numCols)
					break;
				if (map[row][col] == 0)
					continue;

				int rc = map[row][col];
				int r = rc / numTilesAcross;
				int c = rc % numTilesAcross;
				 gc.save();
				gc.drawImage(tiles[r][c].getImage(), x + col * tileSize, y + row * tileSize);

			}

		}

	}

}