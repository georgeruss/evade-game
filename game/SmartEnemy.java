package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject{
	
	private handler Handler;
	private GameObject player;
	
	public SmartEnemy(int x, int y, ID id, handler Handler) {
		super((int)x, (int)y, id);
		
		for (int i = 0; i < Handler.object.size(); i++) {
			if (Handler.object.get(i).getId() == ID.Player) {
				player = Handler.object.get(i);
			} // if
		} // for
		this.Handler = Handler;
	} // SmartEnemy

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		float diffX = x - player.getX() - 8;
		float diffY = y - player.getY() - 8;
		
		float distance = (float)Math.sqrt((x - player.getX()) * (x - player.getX()) 
				+ (y - player.getY()) * (y - player.getY()));
	
		velX = (float)((-1.0 / distance) * diffX);
		velY = (float)((-1.0 / distance) * diffY);
		
		if (y <= 0 || y >= Game.HEIGHT - 120) {
			velY *= -1;
		} else if (x <= 0 || x >= Game.WIDTH - 26) {
			velX *= -1;
		} // if
		collision();
		Handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.ORANGE, 16, 16, 0.2f, Handler));
	} // tick

	@Override
	public void render(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect((int)x, (int)y, 16, 16);
	} // render

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, 16, 16);
	} // getBounds
	
	private void collision() {
		float distance = (float)Math.sqrt((x - player.getX()) * (x - player.getX()) 
				+ (y - player.getY()) * (y - player.getY()));
		float diffX = x - player.getX() - 8;
		float diffY = y - player.getY() - 8;
		
		for (int i = 0; i < Handler.object.size(); i++) {
			GameObject tempObject = Handler.object.get(i);
			
			if (tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.BossEnemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					velX = (float)((-1.0 / distance) * diffX);
					velY = (float)((-1.0 / distance) * diffY);
				}
			}
		} // for
	} // collision
} //SmartEnemy
