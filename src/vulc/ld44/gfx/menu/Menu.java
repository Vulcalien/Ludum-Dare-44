package vulc.ld44.gfx.menu;

import vulc.ld44.gfx.Screen;

public abstract class Menu {

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
