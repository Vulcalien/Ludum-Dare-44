package vulc.ld44.level.tile;

import vulc.ld44.gfx.Atlas;
import vulc.ld44.gfx.Screen;
import vulc.ld44.level.Level;
import vulc.ld44.level.entity.Entity;

public class WallTile extends Tile {

	public WallTile(int id) {
		super(id);
		connectsToWall = true;
	}

	public void render(Screen screen, Level level, int xt, int yt) {
		Tile underTile = level.getTile(xt, yt + 1);
		if(underTile == null || underTile.connectsToWall || !level.hasLight(xt, yt + 1)) {
			screen.renderSprite(Atlas.getTexture(13, 1), xt << T_SIZE, yt << T_SIZE);
		} else {
			screen.renderSprite(Atlas.getTexture(14, 1), xt << T_SIZE, yt << T_SIZE);
		}
	}

	public boolean mayPass(Entity e, int xm, int ym, Level level, int xt, int yt) {
		return false;
	}

	public boolean mayPassLight(Level level, int xt, int yt) {
		return false;
	}

}
