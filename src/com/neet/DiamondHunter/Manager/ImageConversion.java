package com.neet.DiamondHunter.Manager;

import java.awt.image.BufferedImage;

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class ImageConversion {
	private BufferedImage buffImg;

	public ImageConversion(BufferedImage buffImg) {
		this.buffImg = buffImg;
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

}
