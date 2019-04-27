package vulc.ld44.level.entity;

import vulc.ld44.item.Item;

public class ShopKeeper extends Mob {

	public ShopKeeper(int xt, int yt) {
		this.x = (xt << T_SIZE) + (1 << T_SIZE) / 2;
		this.y = (yt << T_SIZE) + (1 << T_SIZE) / 2;

		xr = 6;
		yr = 0;//TODO shopkeeper's yr
	}

	public boolean interactOn(Player player, Item item) {
		return super.interactOn(player, item);
	}

}
