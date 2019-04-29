package vulc.ld44.level;

import static vulc.ld44.item.ItemList.*;

@SuppressWarnings("unused")
public abstract class LevelInfo {

	public static final String[][] SHOPKEEPER_DIALOGS = new String[][] {
		new String[] {//id 0
			"Test 0",
			"Debug 0"
		},
		new String[] {//id 1
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
		},
		new Trade[] {//id 1
		},
		new Trade[] {//id 2
		},
	};

	public static void init() {
	}

}
