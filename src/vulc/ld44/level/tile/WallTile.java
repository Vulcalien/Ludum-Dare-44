package vulc.ld44.level.tile;

import vulc.ld44.gfx.Atlas;
import vulc.ld44.gfx.Screen;
import vulc.ld44.level.Level;
import vulc.ld44.level.entity.Entity;

public class WallTile extends Tile {

	public WallTile(int id) {
		super(id);
	}

	public void render(Screen screen, Level level, int xt, int yt) {
		screen.renderSprite(Atlas.getTexture(13, 0), xt << T_SIZE, yt << T_SIZE);
	}

	public boolean mayPass(Entity e, int xm, int ym, Level level, int xt, int yt) {
		return false;
	}

	public boolean mayPassLight(Level level, int xt, int yt) {
		return false;
	}

}
