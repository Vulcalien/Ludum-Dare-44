package vulc.ld44.level.tile;

import vulc.ld44.item.Item;
import vulc.ld44.level.Level;
import vulc.ld44.level.entity.Player;

public class WinTile extends Tile {

	public WinTile(int id) {
		super(id);
	}

	public void interactOn(Level level, int xt, int yt, Player player, Item item) {
		player.wonLevel = true;
	}

}
