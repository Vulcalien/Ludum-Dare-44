/*******************************************************************************
 * Copyright (C) 2019 Vulcalien
 * This code is licenced under MIT Licence (see LICENCE.txt)
 ******************************************************************************/
package vulc.ld44.gfx.menu;

import vulc.ld44.Game;
import vulc.ld44.gfx.Screen;

public class StartMenu extends ListMenu {

	public StartMenu(Game game) {
		super(game);
		this.orientation = ListOrientation.HORIZONTAL;

		buttons.add(new Button("Credits", () -> game.menu = new CreditsMenu(game)));
		buttons.add(new Button("PLAY", () -> game.menu = null));
		buttons.add(new Button("Instructions", () -> game.menu = new InstructionsMenu(game)));

		focusedPosition = 1;
	}

	public void render(Screen screen) {
		int margin = 5;

		for(int i = 0; i < buttons.size(); i++) {
			Button button = buttons.get(i);
			int color = (i == focusedPosition) ? 0xffff44: 0xffffff;

			if(i == 0)
				screen.writeAbs(button.text, color, margin, screen.height - margin - Screen.FONT.getHeight());
			else if(i == 1)
				screen.writeCentredAbs(button.text, color, screen.width / 2, screen.height/ 2);
			else if(i == 2)
				screen.writeAbs(button.text, color, screen.width - margin - Screen.FONT.lengthOf(button.text), screen.height - margin - Screen.FONT.getHeight());
		}

		String copyright = "Copyright (C) 2019 Vulcalien";
		screen.writeAbs(copyright, 0x707070, screen.width - margin - Screen.FONT.lengthOf(copyright), 2 * margin + Screen.FONT.getHeight());

		String pressEnter = "Press Enter to continue";
		screen.writeAbs(pressEnter, 0x707070, screen.width - margin - Screen.FONT.lengthOf(pressEnter), margin);
	}

}
