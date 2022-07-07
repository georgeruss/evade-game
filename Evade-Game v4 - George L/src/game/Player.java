package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import game.Game.STATE;

public class Player extends GameObject {

	Random r = new Random();
	private handler Handler;
	private Game game;
	public Player(int x, int y, ID id, handler Handler) {
		super((int)x, (int)y, id);
		this.Handler = Handler;
		
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp((int)x, 0, Game.WIDTH - 48);
		y = Game.clamp((int)y, 0, Game.HEIGHT - 133);
		
		/**if (y <= 0 || y >= Game.HEIGHT - 32) {
			velY *= -1;
		} else if (x <= 0 || x >= Game.WIDTH - 32) {
			velX *= -1;
		} // if*/
		Handler.addObject(new Trail((int)x, (int)y, ID.Trail, new Color(236, 205, 80), 32, 32, 0.08f, Handler));
		collision();
		
	}

	private void collision() {
		for (int i = 0; i < Handler.object.size(); i++) {
			GameObject tempObject = Handler.object.get(i);
			
			if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy 
					|| tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.BossEnemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 45;
					Handler.removeObject(tempObject);
				}
			} else if (tempObject.getId() == ID.EnemyBossBullet) {
				if (getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 5;
					Handler.removeObject(tempObject);
				}
			}
			if (tempObject.getId() == ID.Player && HUD.HEALTH == 0) {
				
				Handler.removeObject(this);
				//System.out.println("Game Over!");
				Handler.object.clear();
				
				HUD.HEALTH = 0;
				//HUD.score = 0;
				//System.exit(0);
			}
		} // for
	} // collision
	
	@Override
	public void render(Graphics g) {
	
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(new Color(236, 205, 80));
		g2d.draw(getBounds());
		
		//if (id == ID.Player) {
		g.setColor(new Color(236, 205, 80));
	//	} else if (id == ID.Player2) {
			//g.setColor(Color.blue);
		//}
		//g.fillRect(x, y, 32, 32);
		g.fillRect((int)x, (int)y, 32, 32);
		
	}

	@Override
	public Rectangle getBounds() {
	
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	
}
