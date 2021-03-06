/*******************************************************************************
 * Copyright (C) 2019 Vulcalien
 * This code or part of it is licensed under MIT License by Vulcalien
 ******************************************************************************/
package vulc.ld44.sfx;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	public static enum Volume {
		ON("ON"), OFF("OFF");

		public final String text;

		private Volume(String text) {
			this.text = text;
		}
	}

	public static Volume volume = Volume.ON;

	public static final Sound
	PLAYER_ATTACK = new Sound("/sfx/player_attack.wav"),
	PLAYER_HURT = new Sound("/sfx/player_hurt.wav"),
	PLAYER_DEATH = new Sound("/sfx/player_death.wav"),

	OPEN_DOOR = new Sound("/sfx/open_door.wav"),
	PICKUP_BLOOD = new Sound("/sfx/pickup_blood.wav"),
	BUY = new Sound("/sfx/buy.wav"),
	EQUIP = new Sound("/sfx/equip.wav"),

	MONSTER_HURT = new Sound("/sfx/monster_hurt.wav"),
	SHOPKEEPER_DIALOG = new Sound("/sfx/shopkeeper_dialog.wav");

	private Clip clip;

	public Sound(String file) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(Sound.class.getResource(file));
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			this.clip = clip;
		} catch(Exception e) {
			System.out.println("Error: could not load sound: " + file);
		}
	}

	public void play() {
		if(clip == null || volume == Volume.OFF) return;
		if(clip.isRunning()) clip.stop();
		clip.setFramePosition(0);
		clip.start();
	}

	public void loop(int count) {
		if(clip == null || volume == Volume.OFF) return;
		if(clip.isRunning()) clip.stop();
		clip.setFramePosition(0);
		clip.loop(count);
	}

	public void loop() {
		loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stop() {
		if(clip == null) return;
		clip.stop();
	}

	public static void init() {
	}

}
