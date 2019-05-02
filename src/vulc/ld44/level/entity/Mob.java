/*******************************************************************************
 * Copyright (C) 2019 Vulcalien
 * This code or part of it is licensed under MIT License by Vulcalien
 ******************************************************************************/
package vulc.ld44.level.entity;

import vulc.ld44.animation.Animation;
import vulc.ld44.item.Item;

public abstract class Mob extends Entity {

	public int moveCount = 0;
	public int dir = 0;

	public boolean isAwakened = false;
	public int hp;
	public int xKnockback = 0, yKnockback = 0;

	public Animation animation;

	public boolean[] move(int xm, int ym) {
		if(xm != 0 || ym != 0) {
			moveCount++;

			if(xm < 0) dir = 1;
			else if(xm > 0) dir = 3;

			if(ym < 0) dir = 0;
			else if(ym > 0) dir = 2;
		}

		//BUG somehow the knockback doesn't work when moving
		//BUG monsters get inside other monsters
		boolean[] result = super.move(xm + xKnockback, ym + yKnockback);

		xKnockback = 0;
		yKnockback = 0;
		return result;
	}

	public void receiveLight() {
		super.receiveLight();
		isAwakened = true;
	}

	public boolean isBlockedBy(Entity e) {
		return true;
	}

	public boolean blocks(Entity e) {
		return true;
	}

	public boolean onAttack(int dmg, int attackDir, int knockback, Entity attacker, Item item) {
		if(attacker == this || !this.canBeAttacked()) return false;
		hp -= dmg;
		if(hp <= 0) {
			remove();
		} else {
			if(receivesKnockback()) {
				if(attackDir == 0) yKnockback = -knockback;
				if(attackDir == 1) xKnockback = -knockback;
				if(attackDir == 2) yKnockback = +knockback;
				if(attackDir == 3) xKnockback = +knockback;
			}
		}
		return true;
	}

	public int getAttackDamage() {
		return 0;
	}

	public boolean receivesKnockback() {
		return true;
	}

	public boolean canBeAttacked() {
		return true;
	}

}
