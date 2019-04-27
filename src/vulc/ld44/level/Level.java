package vulc.ld44.level;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import vulc.ld44.Game;
import vulc.ld44.gfx.Screen;
import vulc.ld44.level.entity.Entity;
import vulc.ld44.level.entity.Player;
import vulc.ld44.level.tile.Tile;

public class Level {

	public static final int T_SIZE = Game.T_SIZE;

	public final int width, height;
	public final byte[] tiles;
	public final byte[] data;
	public final boolean[] lightInTiles;
	public final List<Entity> entities = new ArrayList<Entity>();
	public final List<Entity>[] entitiesInTile;

	public int xSpawn, ySpawn;
	public Player player;
	public int remainingEnemies = 0;

	private final Comparator<Entity> sorter = new Comparator<Entity>() {
		public int compare(Entity e0, Entity e1) {
			if(e0.y > e1.y) return +1;
			if(e0.y < e1.y) return -1;
			return 0;
		}
	};

	@SuppressWarnings("unchecked")
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		this.tiles = new byte[width * height];
		this.data = new byte[width * height];
		this.lightInTiles = new boolean[width * height];
		this.entitiesInTile = new ArrayList[width * height];
		for(int i = 0; i < entitiesInTile.length; i++) {
			entitiesInTile[i] = new ArrayList<Entity>();
		}
	}

	public void tick() {
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);

			int xt0 = e.x >> T_SIZE;
			int yt0 = e.y >> T_SIZE;

			e.tick();

			if(e.removed) {
				removeEntityFromTile(e, xt0, yt0);
				removeEntity(e);
				i--;
			} else {
				int xt1 = e.x >> T_SIZE;
				int yt1 = e.y >> T_SIZE;

				if(xt1 != xt0 || yt1 != yt0) {
					removeEntityFromTile(e, xt0, yt0);
					insertEntityInTile(e, xt1, yt1);
				}
			}
		}
	}

	public void render(Screen screen, int xTiles, int yTiles) {
		if(player != null) {
			screen.xOffset = player.x - (xTiles << T_SIZE) / 2;
			screen.yOffset = player.y - (yTiles << T_SIZE) / 2;
		}

		int xt0 = (screen.xOffset) >> T_SIZE;
		int yt0 = (screen.yOffset) >> T_SIZE;
		int xt1 = xt0 + xTiles;
		int yt1 = yt0 + yTiles;

		for(int yt = yt0; yt <= yt1; yt++) {
			if(yt < 0 || yt >= height) continue;

			for(int xt = xt0; xt <= xt1; xt++) {
				if(xt < 0 || xt >= width) continue;

				if(hasLight(xt, yt)) getTile(xt, yt).render(screen, this, xt, yt);
			}
		}

		List<Entity> entities = getEntitiesInTile(xt0 - 1, yt0 - 1, xt1 + 1, yt1 + 1);
		entities.sort(sorter);
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
	}

	public void setTile(Tile tile, int xt, int yt) {
		if(xt < 0 || xt >= width || yt < 0 || yt >= height) return;
		tile.init(this, xt, yt);
		tiles[xt + yt * width] = tile.id;
	}

	public Tile getTile(int xt, int yt) {
		if(xt < 0 || xt >= width || yt < 0 || yt >= height) return null;
		return Tile.TILES[tiles[xt + yt * width]];
	}

	public void setData(byte data, int xt, int yt) {
		if(xt < 0 || xt >= width || yt < 0 || yt >= height) return;
		this.data[xt + yt * width] = data;
	}

	public byte getData(int xt, int yt) {
		if(xt < 0 || xt >= width || yt < 0 || yt >= height) return 0;
		return this.data[xt + yt * width];
	}

	public void addEntity(Entity e) {
		entities.add(e);
		insertEntityInTile(e, e.x >> T_SIZE, e.y >> T_SIZE);
		e.removed = false;
		e.level = this;
		e.init();

		if(e instanceof Player) player = (Player) e;
	}

	public void removeEntity(Entity e) {
		entities.remove(e);
		removeEntityFromTile(e, e.y >> T_SIZE, e.y >> T_SIZE);
		e.removed = true;

		if(e instanceof Player) player = null;
	}

	public void insertEntityInTile(Entity e, int xt, int yt) {
		if(xt < 0 || xt >= width || yt < 0 || yt >= height) return;
		entitiesInTile[xt + yt * width].add(e);
	}

	public void removeEntityFromTile(Entity e, int xt, int yt) {
		if(xt < 0 || xt >= width || yt < 0 || yt >= height) return;
		entitiesInTile[xt + yt * width].remove(e);
	}

	public List<Entity> getEntities(int x0, int y0, int x1, int y1) {
		List<Entity> result = new ArrayList<Entity>();

		int xt0 = (x0 >> T_SIZE) - 1;
		int yt0 = (y0 >> T_SIZE) - 1;
		int xt1 = (x1 >> T_SIZE) + 1;
		int yt1 = (y1 >> T_SIZE) + 1;

		for(int yt = yt0; yt <= yt1; yt++) {
			if(yt < 0 || yt >= height) continue;

			for(int xt = xt0; xt <= xt1; xt++) {
				if(xt < 0 || xt >= width) continue;

				List<Entity> inTile = entitiesInTile[xt + yt * width];
				for(int i = 0; i < inTile.size(); i++) {
					Entity e = inTile.get(i);

					if(e.intersects(x0, y0, x1, y1)) result.add(e);
				}
			}
		}
		return result;
	}

	public List<Entity> getEntitiesInTile(int xt0, int yt0, int xt1, int yt1) {
		List<Entity> result = new ArrayList<Entity>();
		for(int yt = yt0; yt <= yt1; yt++) {
			if(yt < 0 || yt >= height) continue;

			for(int xt = xt0; xt <= xt1; xt++) {
				if(xt < 0 || xt >= width) continue;

				result.addAll(entitiesInTile[xt + yt * width]);
			}
		}
		return result;
	}

	public boolean hasLight(int xt, int yt) {
		if(xt < 0 || xt >= width || yt < 0 || yt >= height) return false;
		return lightInTiles[xt + yt * width];
	}

	public void setLight(boolean flag, int xt, int yt) {
		if(xt < 0 || xt >= width || yt < 0 || yt >= height) return;
		lightInTiles[xt + yt * width] = flag;
	}

	public void spreadLight(int xStart, int yStart) {
		List<TileRef> checked = new ArrayList<TileRef>();
		List<TileRef> toCheck = new ArrayList<TileRef>();

		TileRef current = new TileRef(xStart, yStart);
		toCheck.add(current);

		while(!toCheck.isEmpty()) {
			current = toCheck.get(0);
			int xt = current.xt, yt = current.yt;

			setLight(true, xt, yt);
			if(getTile(xt, yt).mayPassLight(this, xt, yt)) {
				for(int y = -1; y <= 1; y++) {
					int yNeighbor = yt + y;
					if(yNeighbor < 0 || yNeighbor >= height) continue;

					for(int x = -1; x <= 1; x++) {
						int xNeighbor = xt + x;
						if(xNeighbor < 0 || xNeighbor >= width) continue;

						if(hasLight(xNeighbor, yNeighbor)) continue;

						TileRef neighbor = new TileRef(xNeighbor, yNeighbor);
						if(!checked.contains(neighbor) && !toCheck.contains(neighbor)) toCheck.add(neighbor);
					}
				}
			}

			toCheck.remove(0);
			checked.add(current);
		}
	}

	public void clearLight() {
		for(int i = 0; i < lightInTiles.length; i++) {
			lightInTiles[i] = false;
		}
	}

}
