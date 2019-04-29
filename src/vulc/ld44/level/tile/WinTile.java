/*******************************************************************************
 * Copyright (C) 2019 Vulcalien
 * This code is licenced under MIT Licence (see LICENCE.txt)
 ******************************************************************************/
package vulc.ld44.level.tile;

import vulc.ld44.gfx.Atlas;
import vulc.ld44.gfx.Screen;
import vulc.ld44.level.Level;
import vulc.ld44.level.entity.Entity;
import vulc.ld44.level.entity.Player;

public class WinTile extends Tile {

	public WinTile(int id) {
		super(id);
	}

	public void render(Screen screen, Level level, int xt, int yt) {
		screen.renderSprite(Atlas.getTexture(14, 5), xt << T_SIZE, yt << T_SIZE);
	}

	public void onEnter(Level level, int xt, int yt, Entity e) {
		if(e instanceof Player) {
			Player player = (Player) e;
			player.wonLevel = true;
		}
	}

}
