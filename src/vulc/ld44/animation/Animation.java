/*******************************************************************************
 * Copyright (C) 2019 Vulcalien
 * This code or part of it is licensed under MIT License by Vulcalien
 ******************************************************************************/
package vulc.ld44.animation;

import vulc.ld44.Game;

public abstract class Animation {

	public static final int T_SIZE = Game.T_SIZE;

	public boolean ended = false;

	public void tick() {
	}

}
