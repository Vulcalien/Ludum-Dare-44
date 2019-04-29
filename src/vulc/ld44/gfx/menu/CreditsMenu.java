package vulc.ld44.gfx.menu;

import vulc.ld44.Game;
import vulc.ld44.gfx.Screen;
import vulc.ld44.input.KeyBinding;

public class CreditsMenu extends Menu {

	public CreditsMenu(Game game) {
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

		screen.writeCentredAbs("Credits", 0xaaaaaa, (screen.width) / 2, margin + Screen.FONT.getHeight() / 2);

		int offset = 3 * margin + Screen.FONT.getHeight();
		int color = 0xeeeeee;
		screen.writeAbs("Game made by Vulcalien for Ludum Dare 44", color, margin, offset);

		offset += lineDistance;

		offset += lineDistance;
		screen.writeAbs("All the assets where made by me", color, margin, offset);


		String pressEnter = "Press Enter to continue";
		screen.writeAbs(pressEnter, 0x707070, screen.width - margin - Screen.FONT.lengthOf(pressEnter), screen.height - margin - Screen.FONT.getHeight());
	}

}
