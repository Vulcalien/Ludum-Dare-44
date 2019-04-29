/*******************************************************************************
 * Copyright (C) 2019 Vulcalien
 * This code is licenced under MIT Licence (see LICENCE.txt)
 ******************************************************************************/
package vulc.ld44.gfx.menu;

import vulc.ld44.Game;
import vulc.ld44.gfx.Screen;
import vulc.ld44.input.KeyBinding;

public class LoseMenu extends Menu {

	public LoseMenu(Game game) {
		super(game);
	}

	public void tick() {
		if(KeyBinding.ENTER.isPressed()) {
			game.initLevel();
		}
	}

	public void render(Screen screen) {
		screen.writeCentredAbs("YOU LOST :(", 0xffffff, screen.width / 2, screen.height / 2);
		screen.writeAbs("Made by Vulcalien", 0xdddddd, 1, screen.height - 1 - Screen.FONT.getHeight());

		screen.writeCentredAbs("Press Enter to Restart", 0xdddddd, screen.width / 2, screen.height / 2 + 3 + Screen.FONT.getHeight());
	}

}
