package com.izonion.sestitch;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Stitcher {
	
	public static BufferedImage stitch(BufferedImage posx, BufferedImage negx, BufferedImage posy, BufferedImage negy, BufferedImage posz, BufferedImage negz) {
		BufferedImage output = new BufferedImage(2700, 1800, BufferedImage.TYPE_INT_RGB);
		draw(output, negy, 0, 0);
		draw(output, posy, 900, 0);
		draw(output, negz, 1800, 0);
		draw(output, negx, 0, 900);
		draw(output, posz, 900, 900);
		draw(output, posx, 1800, 900);
		return output;
	}
	
	public static void draw(BufferedImage source, BufferedImage image, int offsetX, int offsetY) {
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				source.setRGB(x + offsetX, y + offsetY, image.getRGB(x, y));
			}
		}
	}
	
	public static BufferedImage importImage(String name) {
		try {
	      return ImageIO.read(new File(name + ".png"));
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
		return null;
	}
	
	public static void main(String[] args) {
		try {
			File outputFile = new File("stitched.png");
			ImageIO.write(stitch(importImage("sky_pos_x"),
						importImage("sky_neg_x"),
						importImage("sky_pos_y"),
						importImage("sky_neg_y"),
						importImage("sky_pos_z"),
						importImage("sky_neg_z")),
					"png",
					outputFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
