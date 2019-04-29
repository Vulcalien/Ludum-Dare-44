/*******************************************************************************
 * Copyright (C) 2019 Vulcalien
 * This code is licenced under MIT Licence (see LICENCE.txt)
 ******************************************************************************/
package vulc.ld44;

public abstract class Debug {

	public static Game game;

	public static void init(Game game) {
		Debug.game = game;
	}

	public static void tick() {
	}

}
