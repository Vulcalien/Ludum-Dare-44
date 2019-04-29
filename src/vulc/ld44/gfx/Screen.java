package vulc.ld44.gfx;

import vulc.bitmap.Bitmap;
import vulc.bitmap.Font;
import vulc.ld44.Game;
import vulc.ld44.gfx.menu.Menu;
import vulc.ld44.item.ArmorItem;
import vulc.ld44.item.WeaponItem;
import vulc.ld44.level.Level;
import vulc.ld44.level.entity.Player;

public class Screen extends Bitmap {

	public static final Font FONT = new Font(Screen.class.getResourceAsStream("/fonts/non-monospaced.lwfont"));
	public static final Font LARGE = FONT.getScaled(2);

	private static final int BACKGROUND_COLOR = 0x000000;
	public static Bitmap bloodSprite;

	private final Game game;

	public int xOffset = 0, yOffset = 0;

	public Screen(Game game) {
		super(Game.WIDTH, Game.HEIGHT);
		this.game = game;

		setBackground(0xff00ff);
	}

	public static void init() {
		bloodSprite = Atlas.getSubimage(128, 0, 22, 20);
	}

	public void render() {
		clear(BACKGROUND_COLOR);

		boolean levelShouldRender = true;
		Menu menu = game.menu;
		if(menu != null) levelShouldRender = !menu.blocksLevelRender();

		if(levelShouldRender) {
			Level level = game.level;
			if(level != null) {
				level.render(this, 10, 8);
			}

			renderPlayerGui();
		}

		if(menu != null) menu.render(this);
	}

	public void renderPlayerGui() {
		Player player = game.player;

		fill(320, 0, 352, 256, 0x666666);
		fill(0, 256, 352, 288, 0x666666);

		String hp = "HP: " + (player.hp);
		writeAbs(hp, 0x000000, 1, 257);
		draw(bloodSprite, 1 + FONT.lengthOf("HP: ") + (FONT.lengthOf(String.valueOf(player.hp)) - bloodSprite.width) / 2, 258 + FONT.getHeight());

		{
			WeaponItem weapon = player.weapon;

			String label = "Weapon";
			String dmg = "";
			if(weapon != null) {
				dmg = "dmg: " + weapon.getDamageBonus();
			}

			int contWidth = 19 + Math.max(FONT.lengthOf(label), FONT.lengthOf(dmg));

			int x0 = (width - contWidth) / 2;
			int yBox = 264;
			fill(x0, yBox, x0 + 17, yBox + 17, 0x999999);
			if(weapon != null) {
				draw(weapon.getSprite(), x0 + 1, yBox + 1);
			}

			int lineDistance = FONT.getHeight() + 3;
			int yWrite = 256 + (32 - FONT.getHeight() - lineDistance) / 2;
			writeAbs(label, 0x000000, x0 + 19, yWrite);

			yWrite += FONT.getHeight() + 3;
			writeAbs(dmg, 0x000000, x0 + 19, yWrite);
		}

		{
			ArmorItem armor = player.armor;

			String label = "Armor";
			String def = "";
			if(armor != null) {
				def = "def: " + armor.getProtection();
			}

			int contWidth = 19 + Math.max(FONT.lengthOf(label), FONT.lengthOf(def));

			int x0 = 320 - contWidth;
			int yBox = 264;
			fill(x0, yBox, x0 + 17, yBox + 17, 0x999999);
			if(armor != null) {
				draw(armor.getSprite(), x0 + 1, yBox + 1);
			}

			int lineDistance = FONT.getHeight() + 3;
			int yWrite = 256 + (32 - FONT.getHeight() - lineDistance) / 2;
			writeAbs(label, 0x000000, x0 + 19, yWrite);

			yWrite += FONT.getHeight() + 3;
			writeAbs(def, 0x000000, x0 + 19, yWrite);
		}
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

	public void writeCentred(String text, int color, int x, int y) {
		write(text, color, x - FONT.lengthOf(text) / 2, y - FONT.getHeight() / 2);
	}

	public void writeCentredAbs(String text, int color, int x, int y) {
		writeAbs(text, color, x - FONT.lengthOf(text) / 2, y - FONT.getHeight() / 2);
	}

	public void writeAbs(String text, int color, int x, int y) {
		FONT.write(text, color, this, x, y);
	}

	public void writeLarge(String text, int color, int x, int y) {
		LARGE.write(text, color, this, x - xOffset, y - yOffset);
	}

	public void writeCentredLarge(String text, int color, int x, int y) {
		writeLarge(text, color, x - LARGE.lengthOf(text) / 2, y - LARGE.getHeight() / 2);
	}

	public void writeAbsLarge(String text, int color, int x, int y) {
		LARGE.write(text, color, this, x, y);
	}

	public void writeCentredAbsLarge(String text, int color, int x, int y) {
		writeAbsLarge(text, color, x - LARGE.lengthOf(text) / 2, y - LARGE.getHeight() / 2);
	}

}
