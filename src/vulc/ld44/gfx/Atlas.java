/*******************************************************************************
 * Copyright (C) 2019 Vulcalien
 * This code is licenced under MIT Licence (see LICENCE.txt)
 ******************************************************************************/
package vulc.ld44.gfx;

import static vulc.ld44.Game.T_SIZE;

import java.io.IOException;

import javax.imageio.ImageIO;

import vulc.bitmap.Bitmap;

public abstract class Atlas {

	private static Bitmap atlas;

	public static void init() {
		try {
			atlas = new Bitmap(ImageIO.read(Atlas.class.getResourceAsStream("/gfx/atlas.png"))).getScaled(2);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static Bitmap getTexture(int xs, int ys) {
		return getTexture(xs, ys, 1, 1);
	}

	public static Bitmap getTexture(int xs, int ys, int w, int h) {
		return atlas.getSubimage(xs << T_SIZE, ys << T_SIZE, w << T_SIZE, h << T_SIZE);
	}

	public static Bitmap getSubimage(int x, int y, int w, int h) {
		return atlas.getSubimage(x, y, w, h);
	}

}
