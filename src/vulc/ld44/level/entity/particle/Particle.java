package vulc.ld44.level.entity.particle;

import vulc.ld44.level.entity.Entity;

public class Particle extends Entity {

	public final int lifeTime;
	public int remainingTime;

	public Particle(int lifeTime, int x, int y) {
		this.lifeTime = lifeTime;
		remainingTime = lifeTime;
		this.x = x;
		this.y = y;
	}

	public void tick() {
		remainingTime--;
		if(remainingTime == 0) remove();
	}

}
