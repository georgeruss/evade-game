package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class PlayerBullet extends GameObject{

		private handler Handler;
		Random r = new Random();
		
		public int bulletX = 0;
		public int bulletY = 0;
		
		public PlayerBullet(int x, int y, ID id, handler Handler, int bulletX, int bulletY) {
			super(x, y, id);
			
			this.Handler = Handler;
			
			velX = bulletX;
			velY = bulletY;
			
		} // PlayerBullet

		@Override
		public void tick() {
			x += velX;
			y += velY;
			
			/**if (y <= 0 || y >= Game.HEIGHT - 120) {
			Handler.removeObject(this);
			} else if (x <= 0 || x >= Game.WIDTH - 26) {
				velX *= -1;
			} */
			collision();
			
			if (y <= 0 || y >= Game.HEIGHT - 120) {
				Handler.removeObject(this);
			}
			if (x <= 0 || x >= Game.WIDTH - 26) {
				Handler.removeObject(this);
			}
			
			Handler.addObject(new Trail((int)x, (int)y, ID.Trail, new Color(227, 123, 53), 16, 16, 0.2f, Handler));
		} // tick

		@Override
		public void render(Graphics g) {
			g.setColor(Color.white);
			g.fillRect((int)x, (int)y, 16, 16);
		} // render

		@Override
		public Rectangle getBounds() {
			
			return new Rectangle((int)x, (int)y, 16, 16);
		} // getBounds
		private void collision() {
			for (int i = 0; i < Handler.object.size(); i++) {
				GameObject tempObject = Handler.object.get(i);
				
				if (tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy 
						|| tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.EnemyBossBullet) {
					if (getBounds().intersects(tempObject.getBounds())) {
						AudioPlayer.getSound("death_sound").play();
						Handler.removeObject(tempObject);
						
					}
				}
			} // for
		} // collision*/
	}

