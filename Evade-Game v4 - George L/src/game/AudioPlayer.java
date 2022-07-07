package game;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {
	
	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>(); 
	
	public static void load() {
		
		try {
			soundMap.put("menu_sound", new Sound ("res/mixkit-select-click-1109.wav"));
			soundMap.put("game_menu_sound", new Sound ("res/mixkit-mouse-click-close-1113.wav"));
			soundMap.put("fire_sound", new Sound ("res/click_fire.wav"));
			soundMap.put("death_sound", new Sound ("res/death_sound.wav"));
			musicMap.put("music", new Music ("res/background_music.ogg"));
		} catch (SlickException se) {
			se.printStackTrace();
		} // try-catch
	} // load
	
	public static Music getMusic(String key) {
		return musicMap.get(key);
	} // get music
	
	public static Sound getSound(String key) {
		return soundMap.get(key);
	} // get sound
} // AudioPlayer
