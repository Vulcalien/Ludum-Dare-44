/*******************************************************************************
 * Copyright (C) 2019 Vulcalien
 * This code is licenced under MIT Licence (see LICENCE.txt)
 ******************************************************************************/
package vulc.ld44.level;

import static vulc.ld44.item.ItemList.*;

public abstract class LevelInfo {

	public static final String[][] SHOPKEEPER_DIALOGS = new String[][] {
		new String[] {//id 0
			"Hey you",
			"Yes, you. You are... in a Dungeon!",
			"I am the shopkeeper of this dungeon",
			"I will sell usefull items to you.. but..",
			"Like all the shopkeepers...",
			"I want to be paid...",
			"With your blood!!",
			"That number in the bottom of the screen!",
			"It indicates how much blood you have.",
			"To buy items you have to press L",
			"while watching the item you want.",
			"If your inventory has enought space",
			"you will get the item.",
			"To use it you will have to equip it.",
			"Let's try!",
			"Open that door (with L)",
			"You will find a table with a stick in it",
			"get enought near and press L"
		},
		new String[] {//id 1
			"Well, did you take the item?",
			"How you can see, i took your blood",
			"but don't worry, you will get more",
			"blood killing monsters.",
			"Now, open your inventory (with I)",
			"and press L selecting the item.",
			"You can attack using K",
			"You cannot talk to me if",
			"the room contains enemies."
		},
		new String[] {//id 2
			"Good Job, you killed your first monster!",
			"This dungeon is full of them.",
			"They also drop blood that you can",
			"give to me for items"
		},
		new String[] {//id 3
			"Well, you completed the tutorial!",
			"I suggest you to buy a better weapon.",
			"If you find a good trade think",
			"about buying it, because when",
			"you open the door, you cannot go back."
		},
		new String[] {//id 4
			"This is an armor",
			"It protects you!"
		},
		new String[] {//id 5
		},
		new String[] {//id 6
			"Hey you",
			"Yes, you. You are... in a Dungeon!",
			"I am... Did I already say it??",
		},
		new String[] {//id 7
			"Hello!"
		},
		new String[] {//id 8
			"Hello again!"
		},
		new String[] {//id 9
		},
		new String[] {//id 10
		},
		new String[] {//id 11
			"You won the game!!",
			"To finish, open that last door!"
		}
	};

	public static final Trade[][] SHOPKEEPER_ITEMS = new Trade[][] {
		new Trade[] {//id 0
		},
		new Trade[] {//id 1
			TRADE_USELESS_STICK
		},
		new Trade[] {//id 2
		},
		new Trade[] {//id 3
			TRADE_WOODEN_SWORD
		},
		new Trade[] {//id 4
			TRADE_WOODEN_ARMOR
		},
		new Trade[] {//id 5
			TRADE_IRON_SWORD,
			TRADE_FIRE_ARMOR
		},
		new Trade[] {//id 6
			TRADE_OCEANIC_ARMOR,
			null,
			TRADE_FIRE_SWORD
		},
		new Trade[] {//id 7
		},
		new Trade[] {//id 8
		},
		new Trade[] {//id 9
			TRADE_OCEANIC_SWORD,
			TRADE_LEAF_ARMOR
		},
		new Trade[] {//id 10
			TRADE_LEAF_SWORD,
			TRADE_SUPER_ARMOR
		},
		new Trade[] {//id 11
		},
	};

	public static void init() {
	}

}
