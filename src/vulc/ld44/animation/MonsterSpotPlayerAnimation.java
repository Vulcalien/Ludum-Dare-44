/*******************************************************************************
 * Copyright (C) 2019 Vulcalien
 * This code or part of it is licensed under MIT License by Vulcalien
 ******************************************************************************/
package vulc.ld44.animation;

import vulc.ld44.level.entity.Monster;
import vulc.ld44.level.entity.Player;

public class MonsterSpotPlayerAnimation extends Animation {

	public int tickCount = 0;

	public MonsterSpotPlayerAnimation(Monster monster, Player player) {
		int xd = player.x - monster.x;
		int yd = player.y - monster.y;

		if(Math.abs(xd) > Math.abs(yd)) {
			if(xd < 0) {
				monster.dir = 1;
			} else {
				monster.dir = 3;
			}
		} else {
			if(yd < 0) {
				monster.dir = 0;
			} else {
				monster.dir = 2;
			}
		}
	}

	public void tick() {
		tickCount++;
		if(tickCount >= 60) ended = true;
	}

}
