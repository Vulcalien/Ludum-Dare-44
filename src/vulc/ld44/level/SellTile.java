package vulc.ld44.level;

import vulc.ld44.gfx.Atlas;
import vulc.ld44.gfx.Screen;
import vulc.ld44.item.Item;
import vulc.ld44.level.entity.Entity;
import vulc.ld44.level.entity.Player;
import vulc.ld44.level.tile.Tile;

public class SellTile extends Tile {

	public SellTile(int id) {
		super(id);
	}

	public void render(Screen screen, Level level, int xt, int yt) {
		screen.renderSprite(Atlas.getTexture(11, 2), xt << T_SIZE, yt << T_SIZE);

		Item item = getItem(level, xt, yt);
		if(item != null) {
			item.renderSprite(screen, (xt << T_SIZE) + 4, (yt << T_SIZE));
		}

		screen.writeCentred(level.getData(xt, yt) + "", 0, xt << T_SIZE, yt << T_SIZE);
	}

	public void init(Level level, int xt, int yt) {
//		level.setData((byte) -1, xt, yt);
	}

	public Item getItem(Level level, int xt, int yt) {
		byte data = level.getData(xt, yt);
		if(data == -1) return null;
		return level.shopkeeper.sellingItems[data];
	}

	public void receiveLight(Level level, int xt, int yt) {
		level.sellTiles.add(new TileRef(xt, yt));
	}

	public boolean mayPass(Entity e, int xm, int ym, Level level, int xt, int yt) {
		return false;
	}

	public void interactOn(Level level, int xt, int yt, Player player, Item item) {
		Item sellingItem = getItem(level, xt, yt);
		if(sellingItem != null) {
			//TODO add item to player inventory
		}
	}

}
