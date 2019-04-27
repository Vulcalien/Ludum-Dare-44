package vulc.ld44.level.entity.animation;

import vulc.ld44.level.entity.Player;

public class DoorAnimation extends Animation {

	private final Player player;
	private final int xEnd, yEnd;
	private final int xStart, yStart;

	private boolean reachedStart = false;

	public DoorAnimation(Player player, int xDoor, int yDoor, int dir) {
		this.player = player;

		int xd = 0, yd = 0;

		if(dir == 0) yd = 1;
		else if(dir == 1) xd = 1;
		else if(dir == 2) yd = -1;
		else if(dir == 3) xd = -1;

		xStart = ((xDoor + xd) << T_SIZE) + (1 << T_SIZE) / 2;
		yStart = ((yDoor + yd) << T_SIZE) + (1 << T_SIZE) / 2;
		xEnd = ((xDoor - xd) << T_SIZE) + (1 << T_SIZE) / 2;
		yEnd = ((yDoor - yd) << T_SIZE) + (1 << T_SIZE) / 2;
	}

	public void tick() {
		System.out.println("d");

		int speed = 1;
		int xm = 0, ym = 0;

		int xTarget, yTarget;
		if(reachedStart) {
			xTarget = xEnd;
			yTarget = yEnd;
		} else {
			xTarget = xStart;
			yTarget = yStart;
		}

		int xd = xTarget - player.x;
		int yd = yTarget - player.y;

		if(xd < 0) xm -= speed;
		else if(xd > 0) xm += speed;
		if(yd < 0) ym -= speed;
		else if(yd > 0) ym += speed;

		boolean[] result = player.move(xm, ym);
		if(!result[0] && !result[1]) {
			ended = true;
			System.out.println("Error: animation was locked");
		}

		if(xTarget == player.x && yTarget == player.y) {
			if(!reachedStart) {
				reachedStart = true;
			} else {
				ended = true;
			}
		}
	}

}
