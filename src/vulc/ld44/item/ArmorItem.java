/*******************************************************************************
 * Copyright (C) 2019 Vulcalien
 * This code is licenced under MIT Licence (see LICENCE.txt)
 ******************************************************************************/
package vulc.ld44.item;

public class ArmorItem extends Item {

	private final int def;

	public ArmorItem(String name, int xs, int ys, int def) {
		super(name, xs, ys);
		this.def = def;
	}

	public int getProtection() {
		return def;
	}

}
