package vulc.ld44.gfx.menu;

import java.util.ArrayList;
import java.util.List;

import vulc.ld44.Game;
import vulc.ld44.input.KeyBinding;

public class ListMenu extends Menu {

	public static enum ListOrientation {HORIZONTAL, VERTICAL}

	public ListOrientation orientation = ListOrientation.VERTICAL;
	public List<Button> buttons = new ArrayList<Button>();
	public int focusedPosition = 0;

	public ListMenu(Game game) {
		super(game);
	}

	public void tick() {
		if(buttons.size() == 0) return;

		int m = 0;
		if(orientation == ListOrientation.HORIZONTAL) {
			if(KeyBinding.A.isPressed()) m--;
			if(KeyBinding.D.isPressed()) m++;
		} else {
			if(KeyBinding.W.isPressed()) m--;
			if(KeyBinding.S.isPressed()) m++;
		}

		focusedPosition += m;
		if(focusedPosition < 0) focusedPosition = buttons.size() - 1;
		else if(focusedPosition >= buttons.size()) focusedPosition = 0;

		if(KeyBinding.ENTER.isPressed() || KeyBinding.SPACE.isPressed()) {
			Button button = buttons.get(focusedPosition);
			if(button.runnable != null) button.runnable.run();
		}
	}

	public static class Button {

		public String text;
		public Runnable runnable;

		public Button(String text, Runnable runnable) {
			setText(text);
			setRunnable(runnable);
		}

		public void setText(String text) {
			this.text = text;
		}

		public void setRunnable(Runnable runnable) {
			this.runnable = runnable;
		}

	}

}
