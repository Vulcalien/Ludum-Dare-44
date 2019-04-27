package vulc.ld44.input;

import java.awt.event.KeyEvent;

import vulc.ld44.input.InputHandler.Key;
import vulc.ld44.input.InputHandler.KeyType;

public abstract class KeyBinding {

	public static final Key
	W = new Key(KeyType.KEYBOARD, KeyEvent.VK_W),
	A = new Key(KeyType.KEYBOARD, KeyEvent.VK_A),
	S = new Key(KeyType.KEYBOARD, KeyEvent.VK_S),
	D = new Key(KeyType.KEYBOARD, KeyEvent.VK_D);

	public static void init() {
	}

}
