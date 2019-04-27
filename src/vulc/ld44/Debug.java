package vulc.ld44;

import vulc.ld44.level.Level;
import vulc.ld44.level.entity.Player;
import vulc.ld44.level.tile.Tile;

public abstract class Debug {

	public static Game game;

	public static void init(Game game) {
		Debug.game = game;
		game.level = new Level(10, 10);

		game.level.setTile(Tile.WALL, 8, 8);
		game.level.setTile(Tile.WALL, 9, 8);
		game.level.setTile(Tile.WALL, 8, 9);

		game.player = new Player(1, 1);
		game.level.addEntity(game.player);
	}

	public static void tick() {
	}

}
