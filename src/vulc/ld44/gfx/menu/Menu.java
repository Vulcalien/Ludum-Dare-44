/*******************************************************************************
 * Copyright (C) 2019 Vulcalien
 * This code or part of it is licensed under MIT License by Vulcalien
 ******************************************************************************/
package vulc.ld44.gfx.menu;

import vulc.ld44.Game;
import vulc.ld44.gfx.Screen;

public abstract class Menu {

	public final Game game;

	public Menu(Game game) {
		this.game = game;
	}

	public void tick() {
	}

	public void render(Screen screen) {
	}

	public boolean blocksLevelTick() {
		return true;
	}

	public boolean blocksLevelRender() {
		return true;
	}

}
