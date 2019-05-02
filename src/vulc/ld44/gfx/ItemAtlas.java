/*******************************************************************************
 * Copyright (C) 2019 Vulcalien
 * This code or part of it is licensed under MIT License by Vulcalien
 ******************************************************************************/
package vulc.ld44.gfx;

import java.io.IOException;

import javax.imageio.ImageIO;

import vulc.bitmap.Bitmap;

public class ItemAtlas {

	private static Bitmap atlas;

	public static void init() {
		try {
			atlas = new Bitmap(ImageIO.read(Atlas.class.getResourceAsStream("/gfx/item_atlas.png"))).getScaled(2);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static Bitmap getTexture(int xs, int ys) {
		return getTexture(xs, ys, 1, 1);
	}

	public static Bitmap getTexture(int xs, int ys, int w, int h) {
		return atlas.getSubimage(xs << 4, ys << 4, w << 4, h << 4);
	}

}
