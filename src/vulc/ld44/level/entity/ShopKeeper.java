package vulc.ld44.level.entity;

import vulc.bitmap.Bitmap;
import vulc.ld44.gfx.Atlas;
import vulc.ld44.gfx.Screen;
import vulc.ld44.input.KeyBinding;
import vulc.ld44.item.Item;
import vulc.ld44.level.LevelInfo;
import vulc.ld44.level.TileRef;
import vulc.ld44.level.Trade;

public class Shopkeeper extends Mob {

	public String[] dialog;
	public int dialogTime = 0;

	public Trade[] trades;
	public int placedItems = 0;

	public int id;
	public int tickCount = 0;
	public Player player;
	public boolean talkingToPlayer = false;

	public Shopkeeper(int xt, int yt) {
		this.x = (xt << T_SIZE) + (1 << T_SIZE) / 2;
		this.y = (yt << T_SIZE) + (1 << T_SIZE) / 2;

		xr = 6;
		yr = 7;
	}

	public void tick() {
		tickCount++;
		if(talkingToPlayer) {
			if(KeyBinding.INTERACT.isPressed()) {
				KeyBinding.INTERACT.overrideWasKeyDown(true);
				dialogTime++;
			}
			if(dialogTime >= dialog.length) {
				dialogTime = 0;
				talkingToPlayer = false;
				player.talkingToShopkeeper = false;
				level.talkedToShopkeeper = true;
			}
		}
	}

	public void render(Screen screen) {
		if(!hasLight) return;

		Bitmap sprite = Atlas.getTexture(tickCount / 20 % 2, 6);
		screen.renderSprite(sprite, x - sprite.width / 2, y - sprite.height / 2 - 1);

		if(!level.talkedToShopkeeper && level.awakenedEnemies == 0 && !talkingToPlayer) {
			Bitmap arrow = Atlas.getTexture(tickCount / 20 % 2, 7);
			screen.renderSprite(arrow, x - arrow.width / 2, y - (1 << T_SIZE) - arrow.height / 2 - 1);
		}

		if(talkingToPlayer) {
			if(dialogTime < dialog.length) screen.writeCentred(dialog[dialogTime], 0xffffff, x, y - (1 << T_SIZE));
		}
	}

	public void receiveLight() {
		if(!isAwakened) {
			if(level.shopkeeper == null) id = 0;
			else id = level.shopkeeper.id + 1;
			level.shopkeeper = this;

			level.talkedToShopkeeper = false;

			dialog = LevelInfo.SHOPKEEPER_DIALOGS[id];
			trades = LevelInfo.SHOPKEEPER_ITEMS[id];

			//init sellTiles
			for(int i = 0; i < level.sellTiles.size(); i++) {
				TileRef tile = level.sellTiles.get(i);
				if(placedItems >= trades.length) {
					level.setData((byte) -1, tile.xt, tile.yt);
				} else {
					level.setData((byte) placedItems, tile.xt, tile.yt);
					placedItems++;
				}
			}
		}
		super.receiveLight();
	}

	public boolean onInteract(Player player, Item item) {
		if(level.awakenedEnemies != 0) return false;

		player.talkingToShopkeeper = true;
		talkingToPlayer = true;
		this.player = player;
		return true;
	}

	public boolean canBeAttacked() {
		return false;
	}

}
