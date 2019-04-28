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
