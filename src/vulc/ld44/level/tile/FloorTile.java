package vulc.ld44.level.tile;

import vulc.ld44.gfx.Atlas;
import vulc.ld44.gfx.Screen;
import vulc.ld44.level.Level;

public class FloorTile extends Tile {

	public FloorTile(int id) {
		super(id);
	}

	public void render(Screen screen, Level level, int xt, int yt) {
		if(level.hasLight(xt, yt)) {
			screen.renderSprite(Atlas.getTexture(14, 0), xt << T_SIZE, yt << T_SIZE);
		}
	}

}
