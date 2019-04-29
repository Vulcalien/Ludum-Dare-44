package vulc.ld44.item;

import java.util.ArrayList;
import java.util.List;

import vulc.ld44.Game;
import vulc.ld44.gfx.menu.InventoryMenu;

public class Inventory {

	public final int size;

	public final List<Item> items = new ArrayList<Item>();

	//size = -1 means infinite slots
	public Inventory(int size) {
		this.size = size;
	}

	public void tick() {
	}

	public boolean add(Item item) {
		if(items.size() != size) {
			items.add(item);
			return true;
		}
		return false;
	}

	public Item get(int index) {
		if(index < items.size()) return items.get(index);
		return null;
	}

	public void remove(Item item) {
		items.remove(item);
	}

	public void remove(int index) {
		items.remove(index);
	}

	public void openMenu(Game game) {
		game.menu = new InventoryMenu(game, this);
	}

}
