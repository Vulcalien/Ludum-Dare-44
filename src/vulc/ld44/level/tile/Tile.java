package vulc.ld44.level.tile;

import vulc.ld44.Game;
import vulc.ld44.gfx.Screen;
import vulc.ld44.level.Level;
import vulc.ld44.level.entity.Entity;

public class Tile {

	public static final int T_SIZE = Game.T_SIZE;

	public static final Tile[] TILES = new Tile[128];

	public static final Tile
	FLOOR = new FloorTile(0),
	WALL = new WallTile(1);

	public static int tickCount = 0;

	public final byte id;

	public Tile(int id) {
		this.id = (byte) id;
		if(TILES[id] != null) {
			throw new RuntimeException("Duplicate tile ids");
		}
		TILES[id] = this;
	}

	public void render(Screen screen, Level level, int xt, int yt) {
	}

	public boolean mayPass(Entity e, int xm, int ym, Level level, int xt, int yt) {
		return true;
	}

	public boolean mayPassLight(Level level, int xt, int yt) {
		return true;
	}

	public static void init() {
	}

}
