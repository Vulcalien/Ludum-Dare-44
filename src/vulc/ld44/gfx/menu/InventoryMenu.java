package vulc.ld44.gfx.menu;

import vulc.ld44.Game;
import vulc.ld44.gfx.Screen;
import vulc.ld44.input.KeyBinding;
import vulc.ld44.item.Inventory;
import vulc.ld44.item.Item;
import vulc.ld44.item.WeaponItem;
import vulc.ld44.level.entity.Player;
import vulc.ld44.sfx.Sound;

public class InventoryMenu extends Menu {

	public final Player player;
	public final Inventory inventory;
	public int focusedItem = 0;

	public InventoryMenu(Game game, Player player, Inventory inventory) {
		super(game);
		this.player = player;
		this.inventory = inventory;
	}

	public void tick() {
		if(KeyBinding.OPEN_INVENTORY.isPressed() || KeyBinding.MENU.isPressed()) game.menu = null;
		if(KeyBinding.A.isPressed()) {
			if(focusedItem > 0) focusedItem--;
		}
		if(KeyBinding.D.isPressed()) {
			if(focusedItem + 1 < inventory.size) focusedItem++;
		}
		if(KeyBinding.W.isPressed()) {
			if(focusedItem >= 6) focusedItem -= 6;
		}
		if(KeyBinding.S.isPressed()) {
			if(focusedItem + 6 < inventory.size) focusedItem += 6;
		}

		if(KeyBinding.INTERACT.isPressed()) {
			Item focused = inventory.get(focusedItem);
			if(focused instanceof WeaponItem) {
				WeaponItem old = player.weapon;
				player.weapon = (WeaponItem) focused;
				inventory.set(focusedItem, old);
				Sound.EQUIP.play();
			}
		}
	}

	public void render(Screen screen) {
		int margin = 5;

		int width = 2 * margin + 6 * 36;
		int height = screen.height * 70 / 100;

		int x0 = (screen.width - width) / 2;
		int y0 = (screen.height - height) / 2;

		screen.fill(x0, y0, x0 + width, y0 + height, 0x773399);

		screen.fill(x0 + 1, y0 + 1, x0 + width - 1, y0 + 2, 0x333333);
		screen.fill(x0 + 1, y0 + 1, x0 + 2, y0 + height - 1, 0x333333);
		screen.fill(x0 + 1, y0 + height - 2, x0 + width - 2, y0 + height - 1, 0x333333);
		screen.fill(x0 + width - 2, y0 + 1, x0 + width - 1, y0 + height - 1, 0x333333);

		for(int i = 0; i < inventory.size; i++) {
			int x = i % 6;
			int y = i / 6;

			int xr = x0 + margin + x * 36;
			int yr = y0 + margin + y * 36;

			int background = i == focusedItem ? 0x999999 : 0x666666;
			screen.fill(xr + 1, yr + 1, xr + 34, yr + 34, background);

			Item item = inventory.get(i);
			if(item != null) {
				screen.draw(item.getSprite().getScaled(2), xr + 2, yr + 2);
			}
		}

		int offset = y0 + 2 * margin + 2 * 36;
		int lineDistance = Screen.FONT.getHeight() + 3;

		Item focused = inventory.get(focusedItem);
		if(focused == null) return;

		screen.writeAbs("NAME  : " + focused.name, 0xffffff, x0 + margin, offset);
		offset += lineDistance;

		if(focused instanceof WeaponItem) {
			offset += lineDistance;

			screen.writeAbs("DMG   : " + focused.getDamageBonus(), 0xffffff, x0 + margin, offset);
			offset += lineDistance;

			screen.writeAbs("RANGE : " + focused.getAttackRangeBonus(), 0xffffff, x0 + margin, offset);
			offset += lineDistance;
		}
	}

	public boolean blocksLevelRender() {
		return false;
	}

	public boolean blocksLevelTick() {
		return true;
	}

}
