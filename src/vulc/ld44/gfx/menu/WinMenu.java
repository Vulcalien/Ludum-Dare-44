/*******************************************************************************
 * Copyright (C) 2019 Vulcalien
 * This code is licenced under MIT Licence (see LICENCE.txt)
 ******************************************************************************/
package vulc.ld44.gfx.menu;

import vulc.ld44.Game;
import vulc.ld44.gfx.Screen;

public class WinMenu extends Menu {

	public WinMenu(Game game) {
		super(game);
	}

	public void render(Screen screen) {
		screen.writeCentredAbs("YOU WON =D", 0xffffff, screen.width / 2, screen.height / 2);
		screen.writeAbs("Made by Vulcalien", 0xdddddd, 1, screen.height - 1 - Screen.FONT.getHeight());
	}

}
