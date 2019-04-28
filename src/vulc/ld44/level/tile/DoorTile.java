package vulc.ld44.level.tile;

import vulc.ld44.gfx.Atlas;
import vulc.ld44.gfx.Screen;
import vulc.ld44.item.Item;
import vulc.ld44.level.Level;
import vulc.ld44.level.entity.Entity;
import vulc.ld44.level.entity.Player;
import vulc.ld44.level.entity.animation.DoorAnimation;
import vulc.ld44.sfx.Sound;

public class DoorTile extends Tile {

	public DoorTile(int id) {
		super(id);
		connectsToWall = true;
	}

	public void init(Level level, int xt, int yt) {
		level.setData((byte) 1, xt, yt);
	}

	public void render(Screen screen, Level level, int xt, int yt) {
		Tile.FLOOR.render(screen, level, xt, yt);
		int tOffset = level.getData(xt, yt) == 0 ? 0 : 1;

		Tile underTile = level.getTile(xt, yt + 1);
		if(underTile != null && underTile.connectsToWall) {
			screen.renderSprite(Atlas.getTexture(11 + tOffset, 0), xt << T_SIZE, yt << T_SIZE);
		} else {
			screen.renderSprite(Atlas.getTexture(11 + tOffset, 1), xt << T_SIZE, yt << T_SIZE);
		}
	}

	public void interactOn(Level level, int xt, int yt, Player player, Item item) {
		if(level.remainingEnemies == 0 && level.getData(xt, yt) == 1) {
			level.setData((byte) 0, xt, yt);
			Sound.OPEN_DOOR.play();

			level.spreadLight(xt, yt);

			player.animation = new DoorAnimation(player, xt, yt, player.dir);
		}
	}

	public boolean mayPass(Entity e, int xm, int ym, Level level, int xt, int yt) {
		return level.getData(xt, yt) == 0;
	}

	public boolean mayPassLight(Level level, int xt, int yt) {
		return level.getData(xt, yt) == 0;
	}

	public void onExit(Level level, int xt, int yt, Entity e) {
		if(e instanceof Player) {
			level.setData((byte) 2, xt, yt);
			level.clearLight();
			level.spreadLight(e.x >> T_SIZE, e.y >> T_SIZE);
			//FIX bug: you can be stuck in an already clear room
		}
	}

}
