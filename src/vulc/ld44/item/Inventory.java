package vulc.ld44.item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

	public final int size;

	public final List<Item> items = new ArrayList<Item>();

	//size = -1 means infinite slots
	public Inventory(int size) {
		this.size = size;
	}

	public boolean add(Item item) {
		if(items.size() != size) {
			items.add(item);
			return true;
		}
		return false;
	}

	public void remove(Item item) {
		items.remove(item);
	}

}
