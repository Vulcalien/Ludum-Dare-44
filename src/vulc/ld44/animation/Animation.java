/*******************************************************************************
 * Copyright (C) 2019 Vulcalien
 * This code is licenced under MIT Licence (see LICENCE.txt)
 ******************************************************************************/
package vulc.ld44.animation;

import vulc.ld44.Game;

public abstract class Animation {

	public static final int T_SIZE = Game.T_SIZE;

	public boolean ended = false;

	public void tick() {
	}

}
