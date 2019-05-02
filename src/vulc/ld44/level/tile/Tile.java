/*******************************************************************************
 * Copyright (C) 2019 Vulcalien
 * This code or part of it is licensed under MIT License by Vulcalien
 ******************************************************************************/
package vulc.ld44.level.tile;

import java.util.Random;

import vulc.ld44.Game;
import vulc.ld44.gfx.Screen;
import vulc.ld44.item.Item;
import vulc.ld44.level.Level;
import vulc.ld44.level.entity.Entity;
import vulc.ld44.level.entity.Player;

public abstract class Tile {

	public static final int T_SIZE = Game.T_SIZE;
	public static final Tile[] TILES = new Tile[128];
	public static int tickCount = 0;

	public static final Tile
	FLOOR = new FloorTile(0),
	WALL = new WallTile(1),
	DOOR = new DoorTile(2),
	SELL = new SellTile(3),
	WIN = new WinTile(4);

	public final Random random = new Random();
	public final byte id;
	public boolean connectsToWall = false;

	public Tile(int id) {
		this.id = (byte) id;
		if(TILES[id] != null) {
			throw new RuntimeException("Duplicate tile ids");
		}
		TILES[id] = this;
	}

	public void init(Level level, int xt, int yt) {
		level.setData((byte) 0, xt, yt);
	}

	public void render(Screen screen, Level level, int xt, int yt) {
	}

	public void receiveLight(Level level, int xt, int yt) {
	}

	public void interactOn(Level level, int xt, int yt, Player player, Item item) {
	}

	public void onEnter(Level level, int xt, int yt, Entity e) {
	}

	public void onExit(Level level, int xt, int yt, Entity e) {
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
