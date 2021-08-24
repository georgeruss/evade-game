package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import game.Game.STATE;

public class KeyInput extends KeyAdapter{

	private handler Handler;
	
	private boolean[] keyDown = new boolean[5];
	private boolean[] keyDown2 = new boolean[5];
	
	private Random r = new Random();
	
	private Game game;
	
	public KeyInput (handler Handler, Game game) {
			this.Handler = Handler;
			
			keyDown[0] = false;
			keyDown[1] = false;
			keyDown[2] = false;
			keyDown[3] = false;
			keyDown[4] = false;
			
			keyDown2[0] = false;
			keyDown2[1] = false;
			keyDown2[2] = false;
			keyDown2[3] = false;
			keyDown2[4] = false;
			
			this.game = game;
	} // KeyInput
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < Handler.object.size(); i++) {
			GameObject tempObject = Handler.object.get(i);
			
			if (tempObject.getId() == ID.Player) {
				
				if (key == KeyEvent.VK_W) {
					tempObject.setVelY(-5); 
					keyDown[0] = true;
				} else if (key == KeyEvent.VK_S) {
					tempObject.setVelY(5);
					keyDown[1] = true;
				} else if (key == KeyEvent.VK_D) {
					tempObject.setVelX(5);
					keyDown[2] = true;
				} else if (key == KeyEvent.VK_A) {
					tempObject.setVelX(-5);
					keyDown[3] = true;
				} else if (key == KeyEvent.VK_ESCAPE) {
					if (game.gameState == STATE.Game) {
						if (Game.paused) {
							Game.paused = false;
						} else {
							Game.paused = true;
						}
					} // if
					/**Game.gameState = STATE.Menu;
					Handler.clearEnemies();
					for (int x = 0; x < Handler.object.size(); x++) {
						GameObject tempObject2 = Handler.object.get(x);
						if (tempObject.getId() == ID.Player) {
							Handler.removeObject(tempObject2);
						}
					} */
				} 
				
			} // if
			
			if (tempObject.getId() == ID.Player) {
				
				if (key == KeyEvent.VK_UP) {
					//tempObject.setVelY(-5);
					//PlayerBullet up = new PlayerBullet(0, 5, ID.PlayerBullet, Handler);
					AudioPlayer.getSound("fire_sound").play();
					Handler.addObject(new PlayerBullet((int)tempObject.getX() + 10, (int)tempObject.getY() - 20, ID.PlayerBullet, Handler, 0, -5));
					keyDown2[0] = true;
				} else if (key == KeyEvent.VK_DOWN) {
					//tempObject.setVelY(5);
					AudioPlayer.getSound("fire_sound").play();
					Handler.addObject(new PlayerBullet((int)tempObject.getX() + 10, (int)tempObject.getY() + 30, ID.PlayerBullet, Handler, 0, 5));
					keyDown2[1] = true;
				} else if (key == KeyEvent.VK_LEFT) {
					AudioPlayer.getSound("fire_sound").play();
					Handler.addObject(new PlayerBullet((int)tempObject.getX() - 20, (int)tempObject.getY() + 10, ID.PlayerBullet, Handler, -5, 0));
					//tempObject.setVelX(-5);
					keyDown2[2] = true;
				} else if (key == KeyEvent.VK_RIGHT) {
					//tempObject.setVelX(5);
					AudioPlayer.getSound("fire_sound").play();
					Handler.addObject(new PlayerBullet((int)tempObject.getX() + 30, (int)tempObject.getY() + 10, ID.PlayerBullet, Handler, 5, 0));
					keyDown2[3] = true;
				} 
				
			} // if */
			
			if (game.gameState == STATE.End) {
				if (key == KeyEvent.VK_SPACE) {
					HUD.level = 1;
					HUD.score = 0;;
					game.gameState = STATE.Menu;
					AudioPlayer.getSound("menu_sound").play();
				}
			} 
			
			if (game.gameState == STATE.Menu) {
				if (key == KeyEvent.VK_SPACE) {
					game.gameState = STATE.Game;
					Handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, Handler));
					Handler.clearEnemies();
					AudioPlayer.getSound("menu_sound").play();
					for (int x = 0; x < r.nextInt(1 - - 3) + 3; x++) {
						Handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(350), ID.BasicEnemy, Handler));
					}
				}
			}
			
		} // for
		
		//System.out.println(key);
	} // keyPressed
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < Handler.object.size(); i++) {
			GameObject tempObject = Handler.object.get(i);
			
			if (tempObject.getId() == ID.Player) {
				
				if (key == KeyEvent.VK_W) {
					keyDown[0] = false;
				} else if (key == KeyEvent.VK_S) {
					keyDown[1] = false;
				} else if (key == KeyEvent.VK_D) {
					keyDown[2] = false;
				} else if (key == KeyEvent.VK_A) {
					keyDown[3] = false;
				} 
				
				//vertical mov.
				if (!keyDown[0] && !keyDown[1]) {
					tempObject.setVelY(0);
				} 
				if (!keyDown[2] && !keyDown[3]) {
					tempObject.setVelX(0);
				}
			} // if
			
			if (tempObject.getId() == ID.Player) {
				
				if (key == KeyEvent.VK_UP) {
					keyDown2[0] = false;
				} else if (key == KeyEvent.VK_DOWN) {
					keyDown2[1] = false;
				} else if (key == KeyEvent.VK_LEFT) {
					keyDown2[2] = false;
				} else if (key == KeyEvent.VK_RIGHT) {
					keyDown2[3] = false;
				} 
				
				if (!keyDown[0] && !keyDown[1]) {
					tempObject.setVelY(0);
				} 
				if (!keyDown[2] && !keyDown[3]) {
					tempObject.setVelX(0);
				}
			} // if */
			
		} // for	
		
	} // keyReleased
}
