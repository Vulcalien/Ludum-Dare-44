/*******************************************************************************
 * Copyright (C) 2019 Vulcalien
 * This code or part of it is licensed under MIT License by Vulcalien
 ******************************************************************************/
package vulc.ld44;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import vulc.ld44.gfx.Atlas;
import vulc.ld44.gfx.ItemAtlas;
import vulc.ld44.gfx.Screen;
import vulc.ld44.gfx.menu.Menu;
import vulc.ld44.gfx.menu.PauseMenu;
import vulc.ld44.gfx.menu.StartMenu;
import vulc.ld44.input.InputHandler;
import vulc.ld44.input.KeyBinding;
import vulc.ld44.item.ItemList;
import vulc.ld44.level.Level;
import vulc.ld44.level.LevelInfo;
import vulc.ld44.level.LevelLoader;
import vulc.ld44.level.entity.Player;
import vulc.ld44.level.tile.Tile;
import vulc.ld44.sfx.Sound;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	//the size of the game screen (not the JFrame)
	public static final int WIDTH = 352, HEIGHT = 288;

	//the number of JFrame's pixels that correspond to 1 pixel of the game screen
	public static final int SCALE = 2;

	//Tile size: the number of pixels of a tile is 2^T_SIZE
	public static final int T_SIZE = 5;

	private final BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private final int[] pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();

	private final Screen screen = new Screen(this);

	public Player player;
	public Level level;
	public Menu menu;

	private Thread thread;
	private boolean running = false;

	public void start() {
		if(running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void stop() {
		if(!running) return;
		running = false;
		try {
			//wait for the thread to die
			thread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void init() {
		InputHandler.init(this);
		Atlas.init();
		ItemAtlas.init();
		Sound.init();
		Tile.init();
		KeyBinding.init();
		LevelInfo.init();
		ItemList.init();
		Screen.init();

		initLevel();
	}

	public void initLevel() {
		level = LevelLoader.loadLevel(this, "/levels/level.png");
		player = new Player(level.xSpawn, level.ySpawn);
		level.addEntity(player);

		menu = new StartMenu(this);
	}

	private void tick() {
		boolean levelShouldTick = true;
		boolean menuWasNull = menu == null;
		if(!menuWasNull) {
			levelShouldTick = !menu.blocksLevelTick();
			menu.tick();
		}

		if(levelShouldTick && level != null) {
			level.tick();
		}

		Tile.tickCount++;

		if(menuWasNull) {
			if(KeyBinding.ESC.isPressed()) {
				menu = new PauseMenu(this);
			}
		}

		//must tick the last
		InputHandler.tick();
	}

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen.render();

		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(img, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g.dispose();
		bs.show();
	}

	public void run() {
		int ticksPerSecond = 60;
		int ticks = 0, fps = 0;

		long nanosPerTick = 1_000_000_000 / ticksPerSecond;
		long unprocessedNanos = 0;
		long lastTime = System.nanoTime();

		while(running) {
			long now = System.nanoTime();
			long passedTime = now - lastTime;
			lastTime = now;

			if(passedTime < 0) passedTime = 0;
			if(passedTime > 1_000_000_000) passedTime = 1_000_000_000;

			unprocessedNanos += passedTime;

			boolean ticked = false;
			while(unprocessedNanos >= nanosPerTick) {
				unprocessedNanos -= nanosPerTick;

				tick();
				ticked = true;
				ticks++;

				if(ticks % ticksPerSecond == 0) {
					System.out.println(fps + " fps");
					fps = 0;
				}
			}

			if(ticked) {
				render();
				fps++;
			}

			try {
				Thread.sleep(4);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Game instance = new Game();
		instance.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		instance.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		instance.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame("Just a Dungeon");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		frame.add(instance);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		instance.init();
		instance.start();
	}

}
