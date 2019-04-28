package vulc.ld44.level;

import vulc.ld44.item.Item;
import vulc.ld44.item.ItemList;

public abstract class LevelInfo {

	public static final String[][] SHOPKEEPER_DIALOGS = new String[][] {
		new String[] {//id 0
			"Test 0",
			"Debug 0"
		},
		new String[] {//id 1
			"Test 1",
			"Debug 1"
		},
		new String[] {//id 2
			"Test 2",
			"Debug 2",
			"d 2",
			"a 2"
		},
	};

	public static final Item[][] SHOPKEEPER_ITEMS = new Item[][] {
		new Item[] {//id 0
			ItemList.WOODEN_SWORD
		},
		new Item[] {//id 1

		},
		new Item[] {//id 2

		},
	};

	public static void init() {
	}

}
