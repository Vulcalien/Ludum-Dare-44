/*******************************************************************************
 * Copyright (C) 2019 Vulcalien
 * This code or part of it is licensed under MIT License by Vulcalien
 ******************************************************************************/
package vulc.ld44.input;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import vulc.ld44.Game;
import vulc.ld44.gfx.menu.PauseMenu;

public class InputHandler implements KeyListener, MouseListener, FocusListener {

	public static enum KeyType {KEYBOARD, MOUSE}
	public static enum KeyAction {PRESS, RELEASE}

	public static final List<Key> KEYBOARD_KEYS = new ArrayList<Key>();
	public static final List<Key> MOUSE_KEYS = new ArrayList<Key>();

	public static Game game;

	public static void init(Game game) {
		InputHandler.game = game;

		InputHandler instance = new InputHandler();
		game.addKeyListener(instance);
		game.addMouseListener(instance);
		game.addFocusListener(instance);

		game.requestFocus();
	}

	public static void tick() {
		for(int i = 0; i < KEYBOARD_KEYS.size(); i++) {
			KEYBOARD_KEYS.get(i).tick();
		}
		for(int i = 0; i < MOUSE_KEYS.size(); i++) {
			MOUSE_KEYS.get(i).tick();
		}
	}

	private static void receiveInput(KeyAction action, KeyType type, int code) {
		List<Key> keys = getList(type);
		for(int i = 0; i < keys.size(); i++) {
			Key key = keys.get(i);
			if(key.code == code) {

				if(action == KeyAction.PRESS) {
					key.isKeyDown = true;
				} else if(action == KeyAction.RELEASE) {
					key.isReleased = true;
				}

				return;
			}
		}
	}

	private static List<Key> getList(KeyType type) {
		switch(type) {
			case KEYBOARD:
				return KEYBOARD_KEYS;

			case MOUSE:
				return MOUSE_KEYS;

			default:
				return null;
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		receiveInput(KeyAction.PRESS, KeyType.KEYBOARD, e.getKeyCode());
	}

	public void keyReleased(KeyEvent e) {
		receiveInput(KeyAction.RELEASE, KeyType.KEYBOARD, e.getKeyCode());
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		receiveInput(KeyAction.PRESS, KeyType.MOUSE, e.getButton());
	}

	public void mouseReleased(MouseEvent e) {
		receiveInput(KeyAction.RELEASE, KeyType.MOUSE, e.getButton());
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void focusGained(FocusEvent e) {
	}

	public void focusLost(FocusEvent e) {
		if(game.menu == null || !game.menu.blocksLevelTick()) game.menu = new PauseMenu(game);
		for(int i = 0; i < KEYBOARD_KEYS.size(); i++) {
			KEYBOARD_KEYS.get(i).isReleased = true;
		}
		for(int i = 0; i < MOUSE_KEYS.size(); i++) {
			MOUSE_KEYS.get(i).isReleased = true;
		}
	}

	public static class Key {

		private KeyType type;
		private int code;

		private boolean isKeyDown = false;
		private boolean wasKeyDown = false;
		private boolean isReleased = false;

		public Key() {
		}

		public Key(KeyType type, int code) {
			init(type, code);
		}

		private void init(KeyType type, int code) {
			this.type = type;
			this.code = code;
			getList(type).add(this);
		}

		private void tick() {
			wasKeyDown = isKeyDown;
			if(isReleased) {
				isKeyDown = false;
				isReleased = false;
			}
		}

		public void setKeyBinding(KeyType newType, int newCode) {
			if(this.type != null) getList(this.type).remove(this);
			init(newType, newCode);
		}

		public boolean isKeyDown() {
			return isKeyDown;
		}

		public boolean isPressed() {
			return !wasKeyDown && isKeyDown;
		}

		public boolean isReleased() {
			return wasKeyDown && !isKeyDown;
		}

		public void overrideIsKeyDown(boolean flag) {
			isKeyDown = flag;
		}

		public void overrideWasKeyDown(boolean flag) {
			wasKeyDown = flag;
		}

		public void overrideIsReleased(boolean flag) {
			isReleased = flag;
		}

	}

}
