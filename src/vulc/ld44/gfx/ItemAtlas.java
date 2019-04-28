package vulc.ld44.gfx;

import java.io.IOException;

import javax.imageio.ImageIO;

import vulc.bitmap.Bitmap;

public class ItemAtlas {

	private static Bitmap atlas;

	public static void init() {
		try {
			atlas = new Bitmap(ImageIO.read(Atlas.class.getResourceAsStream("/gfx/item_atlas.png")));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static Bitmap getTexture(int xs, int ys) {
		return getTexture(xs, ys, 1, 1);
	}

	public static Bitmap getTexture(int xs, int ys, int w, int h) {
		return atlas.getSubimage(xs << 3, ys << 3, w << 3, h << 3);
	}

}
