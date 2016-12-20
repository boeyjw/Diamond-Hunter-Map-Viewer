package com.neet.DiamondHunter.MapViewer;

import java.awt.image.BufferedImage;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class ImageConversion {

	private BufferedImage buffImg; 
	private BufferedImage[] buffImgList;
	private WritableImage wrImg;
	private WritableImage[] wrImgList;

	public ImageConversion(BufferedImage buffImg) {
		this.buffImg = buffImg;
	}
	
	public ImageConversion(BufferedImage[] buffImgList) {
		this.buffImgList = buffImgList;
	}

	public ImageConversion(WritableImage wrImg) {
		this.wrImg = wrImg;
	}

	public ImageConversion(WritableImage[] wrImgList) {
		this.wrImgList = wrImgList;
	}

	public WritableImage getWrImg() {
		WritableImage wrImg = null;
		if (buffImg != null) {
			wrImg = new WritableImage(buffImg.getWidth(), buffImg.getHeight());
			PixelWriter pw1 = wrImg.getPixelWriter();
			for (int x = 0; x < buffImg.getWidth(); x++) {
				for (int y = 0; y < buffImg.getHeight(); y++) {
					pw1.setArgb(x, y, buffImg.getRGB(x, y));
				}
			}
		}
		return wrImg;
	}
	
	public WritableImage[] getWrImgList() {
		WritableImage[] wrImgList = new WritableImage[buffImgList.length];
		for (int i = 0; i < buffImgList.length; i++) {
			if (buffImgList[i] != null) {
				wrImgList[i] = new WritableImage(buffImgList[i].getWidth(), buffImgList[i].getHeight());
				PixelWriter pw = wrImgList[i].getPixelWriter();
				for (int x = 0; x < buffImgList[i].getWidth(); x++) {
					for (int y = 0; y < buffImgList[i].getHeight(); y++) {
						pw.setArgb(x, y, buffImgList[i].getRGB(x, y));
					}
				}
			}
		}
		return wrImgList;
	}


	public BufferedImage getBuffImg() {
		return SwingFXUtils.fromFXImage(wrImg, null);
	}

	public BufferedImage[] getBuffImgList() {
		BufferedImage[] convBuffImgList = new BufferedImage[wrImgList.length];
		for (int i = 0; i < wrImgList.length; i++)
			convBuffImgList[i] = SwingFXUtils.fromFXImage(wrImgList[i], null);
		return convBuffImgList;
	}

}
