/*******************************************************************************
 * Copyright (C) 2019 Vulcalien
 * This code or part of it is licensed under MIT License by Vulcalien
 ******************************************************************************/
package vulc.ld44.gfx.menu;

import vulc.ld44.Game;
import vulc.ld44.gfx.Screen;
import vulc.ld44.input.KeyBinding;
import vulc.ld44.sfx.Sound;
import vulc.ld44.sfx.Sound.Volume;

public class PauseMenu extends ListMenu {

	public PauseMenu(Game game) {
		super(game);

		Button volumeBtn = new Button("Sound: " + Sound.volume.text, null);
		volumeBtn.setRunnable(() -> {
			if(Sound.volume == Volume.ON) {
				Sound.volume = Volume.OFF;
			} else {
				Sound.volume = Volume.ON;
			}
			volumeBtn.text = "Sound: " + Sound.volume.text;
		});
		buttons.add(volumeBtn);
	}

	public void tick() {
		super.tick();
		if(KeyBinding.ESC.isPressed()) game.menu = null;
	}

	public void render(Screen screen) {
		int margin = 5;

		int width = screen.width * 50 / 100;
		int height = screen.height * 75 / 100;

		int x0 = (screen.width - width) / 2;
		int y0 = (screen.height - height) / 2;
		screen.fill(x0, y0, x0 + width, y0 + height, 0x773399);

		screen.fill(x0 + 1, y0 + 1, x0 + width - 1, y0 + 2, 0x333333);
		screen.fill(x0 + 1, y0 + 1, x0 + 2, y0 + height - 1, 0x333333);
		screen.fill(x0 + 1, y0 + height - 2, x0 + width - 2, y0 + height - 1, 0x333333);
		screen.fill(x0 + width - 2, y0 + 1, x0 + width - 1, y0 + height - 1, 0x333333);

		int lineDistance = Screen.FONT.getHeight() + 3;
		int offset = y0 + margin;

		screen.writeCentredAbs("++++PAUSE MENU++++", 0xcccccc, x0 + width / 2, offset + Screen.FONT.getHeight() / 2);
		offset += Screen.FONT.getHeight() + margin + 3 * lineDistance;

		for(int i = 0; i < buttons.size(); i++) {
			int color = i == focusedPosition ? 0xffffff : 0xcccccc;
			Button btn = buttons.get(i);

			if(i == 0) {
				screen.writeCentredAbs(btn.text, color, x0 + width / 2, offset);
			}

			offset += lineDistance;
		}

		screen.writeCentredAbs("ESC to return to game", 0xaaaaaa, x0 + width / 2, y0 + height - margin - Screen.FONT.getHeight() / 2);
	}

	public boolean blocksLevelRender() {
		return false;
	}

	public boolean blocksLevelTick() {
		return true;
	}

}
