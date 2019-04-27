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
	ATTACK = new Key();

	public static void setupDefaultBindings() {
		W.setKeyBinding(KeyType.KEYBOARD, KeyEvent.VK_W);
		A.setKeyBinding(KeyType.KEYBOARD, KeyEvent.VK_A);
		S.setKeyBinding(KeyType.KEYBOARD, KeyEvent.VK_S);
		D.setKeyBinding(KeyType.KEYBOARD, KeyEvent.VK_D);

		INTERACT.setKeyBinding(KeyType.KEYBOARD, KeyEvent.VK_L);
		ATTACK.setKeyBinding(KeyType.KEYBOARD, KeyEvent.VK_K);
	}

	public static void init() {
		setupDefaultBindings();
	}

}
