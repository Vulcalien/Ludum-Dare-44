package vulc.ld44.item;

import vulc.bitmap.Bitmap;
import vulc.ld44.gfx.ItemAtlas;
import vulc.ld44.gfx.Screen;
import vulc.ld44.level.entity.Player;

public abstract class Item {

	public String name;
	public int xs, ys;

	public Item(String name, int xs, int ys) {
		this.name = name;
		this.xs = xs;
		this.ys = ys;
	}

	public Bitmap getSprite() {
		return ItemAtlas.getTexture(xs, ys);
	}

	public void renderSprite(Screen screen, int x, int y) {
		screen.renderSprite(getSprite(), x, y);
	}

	public boolean mayAttack() {
		return true;
	}

	public boolean mayInteract() {
		return true;
	}

	public int getDamageBonus() {
		return 0;
	}

	public int getAttackRangeBonus() {
		return 0;
	}

	public boolean use(Player player, Inventory inventory) {
		return false;
	}

}
