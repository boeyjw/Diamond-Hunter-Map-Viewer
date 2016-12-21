package com.neet.DiamondHunter.MapViewer;

/**
 * Contains all information of a single tile.
 *
 */
public class TileInformation {
	
	private int tileImageType;
	private boolean isEntity;
	private boolean isNormal;
	
	//tile type
	public static final boolean NORMAL = true;
	public static final boolean BLOCKED = false;
	
	//tile image, obstacles
	public static final int GREENTREE = 20;
	public static final int DEADTREE = 21;
	public static final int WATER = 22;
	
	//tile image, walkable
	public static final int GRASS = 1;
	public static final int BUSH = 2;
	public static final int FLOWER = 3;
	
	//tile coordinate
	private int row;
	private int col;
	
	public TileInformation(int tileImageType, int row, int col) {
		this.tileImageType = tileImageType;
		this.isNormal = (tileImageType == GRASS || tileImageType == BUSH || tileImageType == FLOWER) ? true : false;
		this.isEntity = false;
		this.row = row;
		this.col = col;
	}

	public boolean isEntity() {
		return isEntity;
	}

	public void setEntity(boolean isEntity) {
		this.isEntity = isEntity;
	}

	public int getTileImageType() {
		return tileImageType;
	}

	public boolean isNormal() {
		return isNormal;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
}
