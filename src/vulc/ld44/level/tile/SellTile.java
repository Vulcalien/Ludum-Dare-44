package vulc.ld44.level.tile;

import vulc.bitmap.Bitmap;
import vulc.ld44.gfx.Atlas;
import vulc.ld44.gfx.Screen;
import vulc.ld44.item.Item;
import vulc.ld44.level.Level;
import vulc.ld44.level.TileRef;
import vulc.ld44.level.Trade;
import vulc.ld44.level.entity.Entity;
import vulc.ld44.level.entity.Player;
import vulc.ld44.sfx.Sound;

public class SellTile extends Tile {

	public SellTile(int id) {
		super(id);
	}

	public void render(Screen screen, Level level, int xt, int yt) {
		Tile underTile = level.getTile(xt, yt + 1);
		if(underTile != Tile.SELL) {
			screen.renderSprite(Atlas.getTexture(11, 2), xt << T_SIZE, yt << T_SIZE);
		} else {
			screen.renderSprite(Atlas.getTexture(12, 2), xt << T_SIZE, yt << T_SIZE);
		}

		Trade trade = getTrade(level, xt, yt);
		if(trade != null) {
			trade.item.renderSprite(screen, (xt << T_SIZE) + 4, (yt << T_SIZE));
			if(level.player.xWatched == xt && level.player.yWatched == yt) {
				String cost = trade.cost + "";
				Bitmap heart = Screen.heartSprite;

				int w = Screen.FONT.lengthOf(cost) + 1 + heart.width;
				int x0 = (xt << T_SIZE) + (1 << T_SIZE) / 2 - w / 2;
				int y0 = (yt << T_SIZE) - (1 << T_SIZE) / 2;
				screen.write(cost, 0, x0, y0 - Screen.FONT.getHeight() / 2);
				screen.renderSprite(heart, x0 + Screen.FONT.lengthOf(cost), y0 - heart.height / 2);
			}
		}
	}

	public void init(Level level, int xt, int yt) {
		level.setData((byte) -1, xt, yt);
	}

	public Trade getTrade(Level level, int xt, int yt) {
		byte data = level.getData(xt, yt);
		if(data == -1) return null;
		return level.shopkeeper.trades[data];
	}

	public void receiveLight(Level level, int xt, int yt) {
		level.sellTiles.add(new TileRef(xt, yt));
	}

	public boolean mayPass(Entity e, int xm, int ym, Level level, int xt, int yt) {
		return false;
	}

	public void interactOn(Level level, int xt, int yt, Player player, Item item) {
		if(level.awakenedEnemies != 0) return;

		Trade trade = getTrade(level, xt, yt);
		if(trade != null) {
			if(player.hp > trade.cost) {
				if(player.obtainItem(trade.item)) {
					player.hp -= trade.cost;
					level.setData((byte) -1, xt, yt);
					Sound.BUY.play();
				}
			}
		}
	}

}
