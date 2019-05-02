/*******************************************************************************
 * Copyright (C) 2019 Vulcalien
 * This code or part of it is licensed under MIT License by Vulcalien
 ******************************************************************************/
package vulc.ld44.level.entity;

public abstract class Enemy extends Mob {

	public void receiveLight() {
		if(!isAwakened) level.awakenedEnemies++;
		super.receiveLight();
	}

	public void remove() {
		super.remove();
		level.awakenedEnemies--;
	}

}
