package vulc.ld44.gfx;

import vulc.bitmap.Bitmap;
import vulc.bitmap.Font;
import vulc.ld44.Game;
import vulc.ld44.gfx.menu.Menu;
import vulc.ld44.level.Level;
import vulc.ld44.level.entity.Player;

public class Screen extends Bitmap {

	public static final Font FONT = new Font(Screen.class.getResourceAsStream("/fonts/non-monospaced.lwfont"));

	private static final int BACKGROUND_COLOR = 0x000000;

	private final Game game;

	public int xOffset = 0, yOffset = 0;

	public Screen(Game game) {
		super(Game.WIDTH, Game.HEIGHT);
		this.game = game;

		setBackground(0xff00ff);
	}

	public void render() {
		clear(BACKGROUND_COLOR);

		boolean levelShouldRender = true;
		Menu menu = game.menu;
		if(menu != null) {
			menu.render(this);
			levelShouldRender = !menu.blocksLevelRender();
		}

		if(levelShouldRender) {
			Level level = game.level;
			if(level != null) {
				level.render(this, 10, 10);
			}

			renderPlayerGui();
		}
	}

	public void renderPlayerGui() {
		Player player = game.player;

		fill(160, 0, 176, 160, 0xff0000);
		fill(0, 160, 176, 176, 0xff00ff);

		//TODO render inventory
	}

	public void setOffset(int x, int y) {
		this.xOffset = x;
		this.yOffset = y;
	}

	public void renderSprite(Bitmap sprite, int x, int y) {
		draw(sprite, x - xOffset, y - yOffset);
	}

	public void write(String text, int color, int x, int y) {
		FONT.write(text, color, this, x - xOffset, y - yOffset);
	}

	public void writeAbs(String text, int color, int x, int y) {
		FONT.write(text, color, this, x, y);
	}

}
