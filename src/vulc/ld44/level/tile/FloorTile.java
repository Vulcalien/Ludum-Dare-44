/*******************************************************************************
 * Copyright (C) 2019 Vulcalien
 * This code is licenced under MIT Licence (see LICENCE.txt)
 ******************************************************************************/
package vulc.ld44.level.tile;

import vulc.ld44.gfx.Atlas;
import vulc.ld44.gfx.Screen;
import vulc.ld44.level.Level;

public class FloorTile extends Tile {

	public FloorTile(int id) {
		super(id);
	}

	public void init(Level level, int xt, int yt) {
		if(random.nextInt(10) == 0) {
			level.setData((byte) 1, xt, yt);
		} else {
			level.setData((byte) 0, xt, yt);
		}
	}

	public void render(Screen screen, Level level, int xt, int yt) {
		if(level.getTile(xt, yt) != Tile.FLOOR || level.getData(xt, yt) == 0) {
			screen.renderSprite(Atlas.getTexture(14, 0), xt << T_SIZE, yt << T_SIZE);
		} else {
			screen.renderSprite(Atlas.getTexture(13, 0), xt << T_SIZE, yt << T_SIZE);
		}
	}

}
