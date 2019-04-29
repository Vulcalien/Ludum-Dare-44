/*******************************************************************************
 * Copyright (C) 2019 Vulcalien
 * This code is licenced under MIT Licence (see LICENCE.txt)
 ******************************************************************************/
package vulc.ld44.gfx.menu;

import vulc.ld44.Game;
import vulc.ld44.gfx.Screen;
import vulc.ld44.input.KeyBinding;

public class InstructionsMenu extends Menu {

	public InstructionsMenu(Game game) {
		super(game);
	}

	public void tick() {
		if(KeyBinding.ENTER.isPressed() || KeyBinding.SPACE.isPressed()) {
			game.menu = new StartMenu(game);
		}
	}

	public void render(Screen screen) {
		int margin = 5;
		int lineDistance = Screen.FONT.getHeight() + 3;

		screen.writeCentredAbs("Instructions", 0xaaaaaa, (screen.width) / 2, margin + Screen.FONT.getHeight() / 2);

		int offset = 3 * margin + Screen.FONT.getHeight();
		int color = 0xeeeeee;
		screen.writeAbs("In this game you have to exit a dungeon", color, margin, offset);

		offset += lineDistance;
		screen.writeAbs("where lots of monsters live.", color, margin, offset);

		offset += lineDistance;
		screen.writeAbs("To win, you have to buy stuff from the", color, margin, offset);

		offset += lineDistance;
		screen.writeAbs("shopkeeper.. but he doesn't want money...", color, margin, offset);

		offset += lineDistance;
		screen.writeAbs("he wants your blood!!", color, margin, offset);

		offset += lineDistance;

		offset += lineDistance;
		screen.writeAbs("Kill mobs to steal their HPs and add", color, margin, offset);

		offset += lineDistance;
		screen.writeAbs("them to yours.", color, margin, offset);

		offset += lineDistance;
		screen.writeAbs("After you clear the room open the door.", color, margin, offset);

		offset += lineDistance;
		screen.writeAbs("If there is a shopkeeper, talk to him.", color, margin, offset);

		offset += lineDistance;

		offset += lineDistance;
		screen.writeAbs("KeyBindings: ", color, margin, offset);

		offset += lineDistance;
		screen.writeAbs("    WASD  -   move", color, margin, offset);

		offset += lineDistance;
		screen.writeAbs("    L     -   interact", color, margin, offset);

		offset += lineDistance;
		screen.writeAbs("    K     -   attack", color, margin, offset);

		offset += lineDistance;
		screen.writeAbs("    I     -   open inventory", color, margin, offset);

		offset += lineDistance;
		screen.writeAbs("    ESC   -   menu and skip dialogs", color, margin, offset);

		offset += lineDistance;
		screen.writeAbs("    ENTER -   interact (in menus)", color, margin, offset);

		offset += lineDistance;
		screen.writeAbs("    A D   -   move (in menus)", color, margin, offset);


		String pressEnter = "Press Enter to continue";
		screen.writeAbs(pressEnter, 0x707070, screen.width - margin - Screen.FONT.lengthOf(pressEnter), screen.height - margin - Screen.FONT.getHeight());
	}

}
