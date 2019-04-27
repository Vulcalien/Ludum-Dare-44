package vulc.ld44.level.entity;

import java.util.List;

import vulc.bitmap.Bitmap;
import vulc.ld44.gfx.Atlas;
import vulc.ld44.gfx.Screen;
import vulc.ld44.input.KeyBinding;
import vulc.ld44.item.Inventory;
import vulc.ld44.item.Item;
import vulc.ld44.level.entity.particle.TestParticle;
import vulc.ld44.level.tile.Tile;
import vulc.ld44.sfx.Sound;

public class Player extends Living {

	public final Inventory inventory = new Inventory(5);
	public final Item handheld = null;

	public int tickCount = 0;
	public int lastAttack = 0;

	public Player(int xt, int yt) {
		this.x = (xt << T_SIZE) + (1 << T_SIZE) / 2;
		this.y = (yt << T_SIZE) + (1 << T_SIZE) / 2;

		xr = 6;
		yr = 7;
	}

	public void init() {
		level.spreadLight(x >> T_SIZE, y >> T_SIZE);
	}

	public void tick() {
		tickCount++;

		int speed = 1;

		int xm = 0, ym = 0;
		if(KeyBinding.W.isKeyDown()) ym -= speed;
		if(KeyBinding.A.isKeyDown()) xm -= speed;
		if(KeyBinding.S.isKeyDown()) ym += speed;
		if(KeyBinding.D.isKeyDown()) xm += speed;

		move(xm, ym);

		if(KeyBinding.INTERACT.isPressed()) interact();
		if(KeyBinding.ATTACK.isKeyDown()) attack();
	}

	public void render(Screen screen) {
		Bitmap sprite = Atlas.getTexture(dir, moveCount / 10 % 3);
		screen.renderSprite(sprite, x - sprite.width / 2, y - sprite.height / 2 - 1);
	}

	public boolean[] move(int xm, int ym) {
		boolean[] result = super.move(xm, ym);
		if(moveCount % 10 == 0) Sound.FOOTSTEP.play();
		return result;
	}

	public void interact() {
		if(handheld != null && !handheld.mayInteract()) return;

		int yo = 1;

		//try entity
		boolean done = false;
		if(dir == 0) done = interactOnEntities(x - 8, y - 12 + yo, x + 8, y - 4 + yo);
		else if(dir == 1) done = interactOnEntities(x - 12, y - 8 + yo, x - 4, y + 8 + yo);
		else if(dir == 2) done = interactOnEntities(x - 8, y + 4 + yo, x + 8, y + 12 + yo);
		else if(dir == 3) done = interactOnEntities(x + 4, y - 8 + yo, x + 12, y + 8 + yo);
		if(done) return;

		//try tile
		int range = 12;
		int xd = 0, yd = 0;

		if(dir == 0) yd -= range;
		else if(dir == 1) xd -= range;
		else if(dir == 2) yd += range;
		else if(dir == 3) xd += range;

		int xt = (x + xd) >> T_SIZE;
		int yt = (y + yo + yd) >> T_SIZE;

		Tile tile = level.getTile(xt, yt);
		if(tile != null) tile.interactOn(level, xt, yt, this, handheld);
	}

	public boolean interactOnEntities(int x0, int y0, int x1, int y1) {
		level.addEntity(new TestParticle(x0, y0, x1 - x0, y1 - y0));

		List<Entity> entities = level.getEntities(x0, y0, x1, y1);
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if(e.interactOn(this, handheld)) return true;
		}
		return false;
	}

	public void attack() {
		if(handheld != null && !handheld.mayAttack()) return;

		if(tickCount - lastAttack < 30) return;
		lastAttack = tickCount;

		int yo = 1;

		if(dir == 0) attackEntities(x - 8, y - 12 + yo, x + 8, y - 4 + yo);
		else if(dir == 1) attackEntities(x - 12, y - 8 + yo, x - 4, y + 8 + yo);
		else if(dir == 2) attackEntities(x - 8, y + 4 + yo, x + 8, y + 12 + yo);
		else if(dir == 3) attackEntities(x + 4, y - 8 + yo, x + 12, y + 8 + yo);

		Sound.ATTACK.play();
	}

	public void attackEntities(int x0, int y0, int x1, int y1) {
		level.addEntity(new TestParticle(x0, y0, x1 - x0, y1 - y0));

		List<Entity> entities = level.getEntities(x0, y0, x1, y1);
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.attack(getDamage(), this, handheld);
		}
	}

	public int getDamage() {
		int dmg = 1;
		if(handheld != null) {
			dmg += handheld.getDamageBonus();
		}
		return dmg;
	}

}
