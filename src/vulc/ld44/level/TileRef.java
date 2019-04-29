/*******************************************************************************
 * Copyright (C) 2019 Vulcalien
 * This code is licenced under MIT Licence (see LICENCE.txt)
 ******************************************************************************/
package vulc.ld44.level;

public class TileRef {

	public final int xt, yt;

	public TileRef(int xt, int yt) {
		this.xt = xt;
		this.yt = yt;
	}

	public boolean equals(Object obj) {
		if(obj instanceof TileRef) {
			TileRef tile = (TileRef) obj;
			return tile.xt == xt && tile.yt == yt;
		}
		return false;
	}

}
