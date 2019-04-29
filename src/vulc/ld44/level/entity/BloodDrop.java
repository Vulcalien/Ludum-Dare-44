package vulc.ld44.level.entity;

import vulc.bitmap.Bitmap;
import vulc.ld44.gfx.Screen;
import vulc.ld44.sfx.Sound;

public class BloodDrop extends Entity {

	public static final int MAX_LIFE = 600;
	public static final int SPREAD_TIME = 20;

	public int value;
	public int lifeTime = MAX_LIFE;
	private boolean hSpread, vSpread;

	public BloodDrop(int x, int y, int value) {
		this.value = value;
		this.x = x;
		this.y = y;

		xr = Screen.bloodSprite.width / 2;
		yr = Screen.bloodSprite.height / 2;

		hSpread = random.nextBoolean();
		vSpread = random.nextBoolean();
	}

	public void tick() {
		lifeTime--;
		if(lifeTime == 0) {
			remove();
			return;
		}
		if(MAX_LIFE - lifeTime <= SPREAD_TIME) {
			int xm = random.nextInt(2);
			int ym = random.nextInt(2);

			if(hSpread == false) xm = -xm;
			if(vSpread == false) ym = -ym;

			move(xm, ym);
		}
	}

	public void render(Screen screen) {
		if(!hasLight) return;
		if(lifeTime < 180 && lifeTime / 10 % 2 != 0) return;

		Bitmap sprite = Screen.bloodSprite;
		screen.renderSprite(sprite, x - sprite.width / 2, y - sprite.height / 2);
	}

	public void touchedBy(Entity e) {
		if(e instanceof Player) {
			Player player = (Player) e;
			if(player.heal(value)) {
				Sound.GAIN_BLOOD.play();
				remove();
			}
		}
	}

}
