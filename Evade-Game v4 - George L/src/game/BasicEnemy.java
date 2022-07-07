package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject{

	private handler Handler;
	
	public BasicEnemy(int x, int y, ID id, handler Handler) {
		super(x, y, id);
		
		velX = 3;
		velY = 3;
		
		this.Handler = Handler;
	} // BasicEnemy

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if (y <= 0 || y >= Game.HEIGHT - 120) {
			velY *= -1;
		} else if (x <= 0 || x >= Game.WIDTH - 26) {
			velX *= -1;
		} 
		collision();
		
		
		//Handler.addObject(new Trail(x, y, ID.Trail, new Color(94, 164, 217), 16, 16, 0.2f, Handler));
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
			
			if (tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy 
					|| tempObject.getId() == ID.BossEnemy || tempObject.getId() == ID.Player) {
				if (getBounds().intersects(tempObject.getBounds())) {
					velX = -3;
					velY = -3;
				}
			}
		} // for
	} // collision
}
