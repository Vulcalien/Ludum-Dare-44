package vulc.ld44.level.entity;

import vulc.bitmap.Bitmap;
import vulc.ld44.gfx.Screen;
import vulc.ld44.item.Item;

public class ShopKeeper extends Mob {

	public ShopKeeper(int xt, int yt) {
		this.x = (xt << T_SIZE) + (1 << T_SIZE) / 2;
		this.y = (yt << T_SIZE) + (1 << T_SIZE) / 2;

		xr = 8;
		yr = 8;
	}

	public void render(Screen screen) {
		Bitmap sprite = new Bitmap(16, 16, 0xff0000);
		screen.renderSprite(sprite, x - sprite.width / 2, y - sprite.height / 2);
	}

	public boolean onInteract(Player player, Item item) {
		return true;
	}

}
