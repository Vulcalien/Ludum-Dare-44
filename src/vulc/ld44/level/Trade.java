/*******************************************************************************
 * Copyright (C) 2019 Vulcalien
 * This code is licenced under MIT Licence (see LICENCE.txt)
 ******************************************************************************/
package vulc.ld44.level;

import vulc.ld44.item.Item;

public class Trade {

	public final int cost;
	public final Item item;

	public Trade(int cost, Item item) {
		this.cost = cost;
		this.item = item;
	}

}
