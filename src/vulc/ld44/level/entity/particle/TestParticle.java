package vulc.ld44.level.entity.particle;

import vulc.bitmap.Bitmap;
import vulc.ld44.gfx.Screen;

public class TestParticle extends Particle {

	private final int w, h;

	public TestParticle(int x, int y, int w, int h) {
		super(40, x, y);
		this.w = w;
		this.h = h;
	}

	public void render(Screen screen) {
		screen.renderSprite(new Bitmap(w, h, 0x00ff00), x, y);
	}

}
