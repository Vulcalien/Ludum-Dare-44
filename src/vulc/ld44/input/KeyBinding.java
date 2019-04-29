package vulc.ld44.input;

import java.awt.event.KeyEvent;

import vulc.ld44.input.InputHandler.Key;
import vulc.ld44.input.InputHandler.KeyType;

public abstract class KeyBinding {

	public static final Key
	W = new Key(),
	A = new Key(),
	S = new Key(),
	D = new Key(),

	INTERACT = new Key(),
	ATTACK = new Key(),

	OPEN_INVENTORY = new Key(),

	ESC = new Key(),
	ENTER = new Key(),
	SPACE = new Key();

	public static void setupDefaultBindings() {
		W.setKeyBinding(KeyType.KEYBOARD, KeyEvent.VK_W);
		A.setKeyBinding(KeyType.KEYBOARD, KeyEvent.VK_A);
		S.setKeyBinding(KeyType.KEYBOARD, KeyEvent.VK_S);
		D.setKeyBinding(KeyType.KEYBOARD, KeyEvent.VK_D);

		INTERACT.setKeyBinding(KeyType.KEYBOARD, KeyEvent.VK_L);
		ATTACK.setKeyBinding(KeyType.KEYBOARD, KeyEvent.VK_K);

		OPEN_INVENTORY.setKeyBinding(KeyType.KEYBOARD, KeyEvent.VK_I);

		ESC.setKeyBinding(KeyType.KEYBOARD, KeyEvent.VK_ESCAPE);
		ENTER.setKeyBinding(KeyType.KEYBOARD, KeyEvent.VK_ENTER);
		SPACE.setKeyBinding(KeyType.KEYBOARD, KeyEvent.VK_SPACE);
	}

	public static void init() {
		setupDefaultBindings();
	}

}
