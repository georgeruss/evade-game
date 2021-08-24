package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject{
	private handler Handler;
	
	private Random r = new Random();
	
	private Color col;
	
	public MenuParticle(int x, int y, ID id, handler Handler) {
		super(x, y, id);
		
		this.Handler = Handler;
		
		velX = 0;
		velY = 2;
			
		col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
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
		//collision();
		Handler.addObject(new Trail((int)x, (int)y, ID.Trail, col, 16, 16, 0.1f, Handler));
		
	} // tick

	@Override
		public void render(Graphics g) {
			g.setColor(col);
			g.fillRect((int)x, (int)y, 16, 16);
		} // render

		@Override
		public Rectangle getBounds() {
			
			return new Rectangle((int)x, (int)y, 16, 16);
		} // getBounds
		
		private void collision() {
			for (int i = 0; i < Handler.object.size(); i++) {
				GameObject tempObject = Handler.object.get(i);
				
				if (tempObject.getId() == ID.MenuParticle) {
					if (getBounds().intersects(tempObject.getBounds())) {
						velX *= -1;
						velY *= 1;
					}
				}
			} // for
		} // collision

}
