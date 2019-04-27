package vulc.ld44.gfx;

import static vulc.ld44.Game.T_SIZE;

import java.io.IOException;

import javax.imageio.ImageIO;

import vulc.bitmap.Bitmap;

public abstract class Atlas {

	private static Bitmap atlas;

	public static void init() {
		try {
			atlas = new Bitmap(ImageIO.read(Atlas.class.getResourceAsStream("/gfx/atlas.png")));
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

}
