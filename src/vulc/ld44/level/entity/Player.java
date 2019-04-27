package vulc.ld44.level.entity;

import vulc.bitmap.Bitmap;
import vulc.ld44.gfx.Atlas;
import vulc.ld44.gfx.Screen;
import vulc.ld44.input.KeyBinding;

public class Player extends Living {

	public Player(int xt, int yt) {
		this.x = (xt << T_SIZE) + (1 << T_SIZE) / 2;
		this.y = (yt << T_SIZE) + (1 << T_SIZE) / 2;

		xr = 8;
		yr = 8;
	}

	public void init() {
		level.spreadLight(x >> T_SIZE, y >> T_SIZE);
	}

	public void tick() {
		int speed = 1;

		int xm = 0, ym = 0;
		if(KeyBinding.W.isKeyDown()) ym -= speed;
		if(KeyBinding.A.isKeyDown()) xm -= speed;
		if(KeyBinding.S.isKeyDown()) ym += speed;
		if(KeyBinding.D.isKeyDown()) xm += speed;

		move(xm, ym);
	}

	public void render(Screen screen) {
		Bitmap sprite = Atlas.getTexture(dir, moveCount / 10 % 3);
		screen.renderSprite(sprite, x - sprite.width / 2, y - sprite.height / 2);
	}

}
