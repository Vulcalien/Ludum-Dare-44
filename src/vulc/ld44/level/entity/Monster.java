/*******************************************************************************
 * Copyright (C) 2019 Vulcalien
 * This code or part of it is licensed under MIT License by Vulcalien
 ******************************************************************************/
package vulc.ld44.level.entity;

import vulc.bitmap.Bitmap;
import vulc.ld44.animation.Animation;
import vulc.ld44.animation.MonsterSpotPlayerAnimation;
import vulc.ld44.gfx.Atlas;
import vulc.ld44.gfx.Screen;
import vulc.ld44.item.Item;
import vulc.ld44.sfx.Sound;

public class Monster extends Enemy {

	public int tickCount = 0;
	public int lastAttacked = -1000;

	public Player target;
	public Animation animation;

	public Monster(int xt, int yt) {
		this.x = (xt << T_SIZE) + (1 << T_SIZE) / 2;
		this.y = (yt << T_SIZE) + (1 << T_SIZE) / 2;

		xr = 12;
		yr = 14;

		hp = 350 + random.nextInt(250);
	}

	public void tick() {
		if(!isAwakened) return;

		tickCount++;

		if(animation != null) {
			animation.tick();
			if(animation.ended) animation = null;
			return;
		}

		if(target == null) {
			if(!gainFocus()) {
				//TODO monster walks random
			}
		} else {
			int xd = target.x - x;
			int yd = target.y - y;

			int speed = 1;
			int xm = 0, ym = 0;

			if(tickCount - lastAttacked >= 10) {
				if(xd < 0) xm -= speed;
				else if(xd > 0) xm += speed;
				if(yd < 0) ym -= speed;
				else if(yd > 0) ym += speed;
			}

			move(xm, ym);

			if(target.removed) {
				target = null;
			}
		}
	}

	public void render(Screen screen) {
		if(!hasLight) return;

		Bitmap sprite = Atlas.getTexture(dir, 3 + moveCount / 10 % 2);
		screen.renderSprite(sprite, x - sprite.width / 2, y - sprite.height / 2 - 2);

		if(animation != null) {
			screen.writeCentredLarge("!", 0xcc0000, x + 1, y - (1 << T_SIZE) + 1);
			screen.writeCentredLarge("!", 0xff0000, x, y - (1 << T_SIZE));
		}
	}

	public boolean gainFocus() {
		//TODO add search alghorithm
		if(level.player != null) {
			target = level.player;
			animation = new MonsterSpotPlayerAnimation(this, target);
			return true;
		}
		return false;
	}

	public boolean onAttack(int dmg, int attackDir, int knockback, Entity attacker, Item item) {
		if(super.onAttack(dmg, attackDir, knockback, attacker, item)) {
			lastAttacked = tickCount;
			Sound.MONSTER_HURT.play();
			return true;
		}
		return false;
	}

	public int getAttackDamage() {
		return 105;
	}

	public void touchedBy(Entity e) {
		if(e instanceof Player) {
			e.touchedBy(this);
		}
	}

	public void remove() {
		super.remove();
		int drops = 1 + random.nextInt(4);
		for(int i = 0; i < drops; i++) {
			level.addEntity(new BloodDrop(x, y, 25 + random.nextInt(41)));
		}
	}

}
