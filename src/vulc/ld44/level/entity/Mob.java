package vulc.ld44.level.entity;

public class Mob extends Entity {

	public int moveCount = 0;
	public int dir = 0;

	public boolean[] move(int xm, int ym) {
		if(xm != 0 || ym != 0) {
			moveCount++;

			if(xm < 0) dir = 1;
			else if(xm > 0) dir = 3;

			if(ym < 0) dir = 0;
			else if(ym > 0) dir = 2;
		}

		return super.move(xm, ym);
	}

	public boolean isBlockedBy(Entity e) {
		return true;
	}

	public boolean blocks(Entity e) {
		return true;
	}

}
