package vulc.ld44.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import vulc.ld44.level.tile.Tile;

public abstract class LevelLoader {

	public static Level loadLevel(String file) {
		try {
			BufferedImage image = ImageIO.read(LevelLoader.class.getResourceAsStream(file));
			int w = image.getWidth();
			int h = image.getHeight();

			int[] pixels = new int[image.getWidth() * image.getHeight()];
			image.getRGB(0, 0, w, h, pixels, 0, w);

			Level level = new Level(w, h);
			for(int i = 0; i < pixels.length; i++) {
				int xt = i % w;
				int yt = i / w;

				int data = pixels[i];

				int tileData = data & 0xffffff;
				Tile tile = null;

				switch(tileData) {
					case 0x000000:
						tile = Tile.FLOOR;
						break;

					case 0xaaaaaa:
						tile = Tile.WALL;
						break;

					case 0x503020:
						tile = Tile.DOOR;
						break;

					default:
						System.out.println("Error: unrecognized tile color code");
						break;
				}
				level.setTile(tile, xt, yt);

				int entityData = (data >> 24) & 0xff;
				if(entityData == 200) {
					level.xSpawn = xt;
					level.ySpawn = yt;
				}
			}
			return level;
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
