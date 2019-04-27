package vulc.ld44.item;

import vulc.ld44.gfx.Screen;

public class Item {

	public String name;

	public void renderSprite(Screen screen, int x, int y) {
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

}
