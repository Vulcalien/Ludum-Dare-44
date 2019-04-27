package vulc.ld44.level.tile;

import vulc.ld44.gfx.Atlas;
import vulc.ld44.gfx.Screen;
import vulc.ld44.item.Item;
import vulc.ld44.level.Level;
import vulc.ld44.level.entity.Entity;
import vulc.ld44.level.entity.Player;

public class DoorTile extends Tile {

	public DoorTile(int id) {
		super(id);
	}

	public void render(Screen screen, Level level, int xt, int yt) {
		Tile.FLOOR.render(screen, level, xt, yt);
		if(level.getData(xt, yt) != 0) {
			screen.renderSprite(Atlas.getTexture(11, 0), xt << T_SIZE, yt << T_SIZE);
		} else {
			screen.renderSprite(Atlas.getTexture(12, 0), xt << T_SIZE, yt << T_SIZE);
		}
	}

	public void interactOn(Level level, int xt, int yt, Player player, Item item) {
		if(level.remainingEnemies == 0) {
			level.setData((byte) 1, xt, yt);
			level.spreadLight(xt, yt);
		}
	}

	public boolean mayPass(Entity e, int xm, int ym, Level level, int xt, int yt) {
		return level.getData(xt, yt) != 0;
	}

	public boolean mayPassLight(Level level, int xt, int yt) {
		return level.getData(xt, yt) != 0;
	}

}
