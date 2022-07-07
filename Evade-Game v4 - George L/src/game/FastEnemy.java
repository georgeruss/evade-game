package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemy extends GameObject{

	private handler Handler;
		
	public FastEnemy(int x, int y, ID id, handler Handler) {
		super(x, y, id);
			
		velX = 2;
		velY = 6;
			
			this.Handler = Handler;
			} // BasicEnemy

		@Override
	public void tick() {
		x += velX;
		y += velY;
			
		if (y <= 0 || y >= Game.HEIGHT - 120) {
			velY *= -1;
		} else if (x <= 0 || x >= Game.WIDTH - 32) {
			velX *= -1;
		} // if
			
		collision();
		Handler.addObject(new Trail((int)x, (int)y, ID.Trail, new Color(244,86, 86), 16, 16, 0.1f, Handler));
	} // tick

	@Override
		public void render(Graphics g) {
			g.setColor(new Color(120, 120, 120));
			g.fillRect((int)x, (int)y, 16, 16);
		} // render

		@Override
		public Rectangle getBounds() {
			
			return new Rectangle((int)x, (int)y, 16, 16);
		} // getBounds
		
		private void collision() {
			for (int i = 0; i < Handler.object.size(); i++) {
				GameObject tempObject = Handler.object.get(i);
				
				if (tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.BossEnemy || tempObject.getId() == ID.BasicEnemy
						|| tempObject.getId() == ID.Player) {
					if (getBounds().intersects(tempObject.getBounds())) {
						velX *= -1;
						velY *= -1;
					}
				}
			} // for
		} // collision

}
