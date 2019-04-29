package vulc.ld44.level.tile;

import vulc.bitmap.Bitmap;
import vulc.ld44.animation.DoorAnimation;
import vulc.ld44.gfx.Atlas;
import vulc.ld44.gfx.Screen;
import vulc.ld44.item.Item;
import vulc.ld44.level.Level;
import vulc.ld44.level.entity.Entity;
import vulc.ld44.level.entity.Player;
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
		byte data = level.getData(xt, yt);
		int tOffset = data == 0 ? 1 : 0;

		Tile underTile = level.getTile(xt, yt + 1);
		if(underTile != null && underTile.connectsToWall) {
			screen.renderSprite(Atlas.getTexture(14, 2 + tOffset), xt << T_SIZE, yt << T_SIZE);
		} else {
			screen.renderSprite(Atlas.getTexture(13, 2 + tOffset), xt << T_SIZE, yt << T_SIZE);
		}

		if(level.awakenedEnemies == 0 && level.talkedToShopkeeper && data == 1) {
			Bitmap arrow = Atlas.getTexture(tickCount / 20 % 2, 7);
			screen.renderSprite(arrow, xt << T_SIZE, (yt - 1) << T_SIZE);
		}
	}

	public void interactOn(Level level, int xt, int yt, Player player, Item item) {
		if(level.canOpenDoor() && level.getData(xt, yt) == 1) {
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
		}
	}

}
