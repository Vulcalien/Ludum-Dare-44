package vulc.ld44.level.entity;

import java.util.List;

import vulc.bitmap.Bitmap;
import vulc.ld44.gfx.Atlas;
import vulc.ld44.gfx.Screen;
import vulc.ld44.input.KeyBinding;
import vulc.ld44.item.ArmorItem;
import vulc.ld44.item.Inventory;
import vulc.ld44.item.Item;
import vulc.ld44.level.entity.particle.TestParticle;
import vulc.ld44.level.tile.Tile;
import vulc.ld44.sfx.Sound;

public class Player extends Mob {

	public Inventory inventory = new Inventory(10);
	public Item handheld = null;
	public ArmorItem armor = new ArmorItem("a", 1, 0, 50);

	public int range = 32;
	public int yo = 2;

	public int xWatched, yWatched;

	public int tickCount = 0;
	public int lastAttack = 0;
	public int lastAttacked = 0;

	public boolean talkingToShopkeeper = false;

	public Player(int xt, int yt) {
		this.x = (xt << T_SIZE) + (1 << T_SIZE) / 2;
		this.y = (yt << T_SIZE) + (1 << T_SIZE) / 2;

		xr = 12;
		yr = 14;

		hp = 1000;
	}

	public void init() {
		level.spreadLight(x >> T_SIZE, y >> T_SIZE);
	}

	public void tick() {
		tickCount++;

		if(animation != null) {
			animation.tick();
			if(animation.ended) animation = null;
			return;
		}

		if(talkingToShopkeeper) return;

		int speed = 2;

		int xm = 0, ym = 0;
		if(xKnockback == 0 && yKnockback == 0) {
			if(KeyBinding.W.isKeyDown()) ym -= speed;
			if(KeyBinding.A.isKeyDown()) xm -= speed;
			if(KeyBinding.S.isKeyDown()) ym += speed;
			if(KeyBinding.D.isKeyDown()) xm += speed;
		}

		move(xm, ym);

		if(xm != 0 || ym != 0) {
			int xd = 0, yd = 0;

			if(dir == 0) yd -= range;
			else if(dir == 1) xd -= range;
			else if(dir == 2) yd += range;
			else if(dir == 3) xd += range;

			xWatched = (x + xd) >> T_SIZE;
			yWatched = (y + yo + yd) >> T_SIZE;
		}

		if(KeyBinding.INTERACT.isPressed()) interact();
		if(KeyBinding.ATTACK.isKeyDown()) attack();
	}

	public void render(Screen screen) {
		if(!canBeAttacked() && tickCount / 5 % 2 == 0) return;

		Bitmap sprite = Atlas.getTexture(dir, moveCount / 10 % 2);
		screen.renderSprite(sprite, x - sprite.width / 2, y - sprite.height / 2 - 2);
	}

	public boolean[] move(int xm, int ym) {
		boolean[] result = super.move(xm, ym);
		if(moveCount % 10 == 0 && (xm != 0 || ym != 0)) Sound.FOOTSTEP.play();
		return result;
	}

	public void interact() {
		if(handheld != null && !handheld.mayInteract()) return;

		//try entity
		boolean done = false;
		if(dir == 0) done = interactOnEntities(x - 16, y - range + yo, x + 16, y - range + 16 + yo);
		else if(dir == 1) done = interactOnEntities(x - range, y - 16 + yo, x - range + 16, y + 16 + yo);
		else if(dir == 2) done = interactOnEntities(x - 16, y + range - 16 + yo, x + 16, y + range + yo);
		else if(dir == 3) done = interactOnEntities(x + range - 16, y - 16 + yo, x + range, y + 16 + yo);
		if(done) return;

		//try tile
		Tile tile = level.getTile(xWatched, yWatched);
		if(tile != null) tile.interactOn(level, xWatched, yWatched, this, handheld);
	}

	public boolean interactOnEntities(int x0, int y0, int x1, int y1) {
		level.addEntity(new TestParticle(x0, y0, x1 - x0, y1 - y0));

		List<Entity> entities = level.getEntities(x0, y0, x1, y1);
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if(e.onInteract(this, handheld)) return true;
		}
		return false;
	}

	public void attack() {
		if(handheld != null && !handheld.mayAttack()) return;

		if(tickCount - lastAttack < 20) return;
		lastAttack = tickCount;

		int range = this.range + (handheld != null ? handheld.getAttackRangeBonus() : 0);

		if(dir == 0) attackEntities(x - 16, y - range + yo, x + 16, y - range + 16 + yo);
		else if(dir == 1) attackEntities(x - range, y - 16 + yo, x - range + 16, y + 16 + yo);
		else if(dir == 2) attackEntities(x - 16, y + range - 16 + yo, x + 16, y + range + yo);
		else if(dir == 3) attackEntities(x + range - 16, y - 16 + yo, x + range, y + 16 + yo);

		Sound.PLAYER_ATTACK.play();
	}

	public void attackEntities(int x0, int y0, int x1, int y1) {
		level.addEntity(new TestParticle(x0, y0, x1 - x0, y1 - y0));

		List<Entity> entities = level.getEntities(x0, y0, x1, y1);
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if(e instanceof Mob) {
				Mob mob = (Mob) e;
				mob.onAttack(getAttackDamage(), dir, 16, this, handheld);
			}
		}
	}

	public int getAttackDamage() {
		int dmg = 1;
		if(handheld != null) {
			dmg += handheld.getDamageBonus();
		}
		return dmg;
	}

	public void touchedBy(Entity e) {
		if(e instanceof Enemy) {
			Enemy enemy = (Enemy) e;
			int dmg = enemy.getAttackDamage();
			if(armor != null) dmg = dmg * (100 - armor.getProtection()) / 100;
			if(onAttack(dmg, enemy.dir, 16, enemy, null)) {
				lastAttacked = tickCount;
				if(!removed) Sound.PLAYER_HURT.play();
			}
		}
	}

	public void remove() {
		super.remove();
		Sound.PLAYER_DEATH.play();
	}

	public boolean canBeAttacked() {
		return tickCount - lastAttacked >= 75;
	}

	public boolean obtainItem(Item item) {
		return inventory.add(item);
	}

}
