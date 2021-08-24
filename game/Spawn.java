package game;

import java.util.Random;

public class Spawn {

	private handler Handler;
	private HUD hud;
	
	private int scoreKeep = 0;
	private Random r = new Random();
	
	public Spawn(handler Handler, HUD hud) {
		this.Handler = Handler;
		this.hud = hud;
	} // Spawn
	
	public void tick() {
		scoreKeep += 2;
		
		if (scoreKeep >= 500) {
			scoreKeep = 0;
			
			hud.setLevel(hud.getLevel() + 1);
			
			if (hud.getLevel() == 2) { 
				for (int i = 0; i < r.nextInt(1 - - 3) + 3; i++) {
					Handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 20),r.nextInt(350), ID.BasicEnemy, Handler));
				}
			} else if (hud.getLevel() == 3) {
				for (int i = 0; i < r.nextInt(1 - - 3) + 3; i++) {
					Handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(350), ID.BasicEnemy, Handler));
				}
			} else if (hud.getLevel() == 4) {
				for (int i = 0; i < r.nextInt(1 - - 3) + 3; i++) {
					Handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(350), ID.BasicEnemy, Handler));
				}
			} else if (hud.getLevel() == 5) {
				for (int i = 0; i < r.nextInt(1 - - 3) + 3; i++) {
					Handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(350), ID.SmartEnemy, Handler));
				}
				Handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(350), ID.BasicEnemy, Handler));
			} else if (hud.getLevel() == 7) {
				Handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(350), ID.FastEnemy, Handler));
			} else if (hud.getLevel() == 8) {
				Handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(350), ID.BasicEnemy, Handler));
			} else if (hud.getLevel() == 9) {
				Handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(350), ID.BasicEnemy, Handler));
			} else if (hud.getLevel() == 10) {
				Handler.clearEnemies();
				Handler.addObject(new BossEnemy((Game.WIDTH / 2) -90, -150, ID.BossEnemy, Handler));
			} else if (hud.getLevel() == 11) {
				for (int i = 0; i < r.nextInt(1 - - 3) + 3; i++) {
					Handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 20), r.nextInt(350), ID.SmartEnemy, Handler));
				}
			}
		}
	} // tick
	
} // Spawn
