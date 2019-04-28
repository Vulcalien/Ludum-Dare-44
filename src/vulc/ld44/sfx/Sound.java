package vulc.ld44.sfx;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	public static enum Volume {ON, OFF}
	public static Volume volume = Volume.OFF;

	public static final Sound
	ATTACK = new Sound("/sfx/attack.wav"),
	OPEN_DOOR = new Sound("/sfx/open_door.wav"),
	FOOTSTEP = new Sound("/sfx/footstep.wav");

	private Clip clip;

	public Sound(String file) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(Sound.class.getResourceAsStream(file));
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			this.clip = clip;
		} catch(Exception e) {
			e.printStackTrace();
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
