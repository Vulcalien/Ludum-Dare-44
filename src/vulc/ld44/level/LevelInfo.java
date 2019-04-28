package vulc.ld44.level;

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

	public static final Trade[][] SHOPKEEPER_ITEMS = new Trade[][] {
		new Trade[] {//id 0
			new Trade(1, ItemList.WOODEN_SWORD)
		},
		new Trade[] {//id 1

		},
		new Trade[] {//id 2

		},
	};

	public static void init() {
	}

}
