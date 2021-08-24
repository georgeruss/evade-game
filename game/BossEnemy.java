package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemy extends GameObject{
	
	private handler Handler;
	private int timer = 80;
	private int timer2 = 50;
	Random r = new Random();
	
	public BossEnemy(int x, int y, ID id, handler Handler) {
		super(x, y, id);
			
		velX = 0;
		velY = 2;
			
		this.Handler = Handler;
	} // BossEnemy

		@Override
	public void tick() {
		x += velX;
		y += velY;
			
		if (timer <= 0) velY = 0;
		else timer--;
		
		if (timer <= 0)	timer2--;
		if (timer2 <= 0)
		{
			if (velX == 0) velX = 2;
				if (velX > 0) {
					velX += 0.005f;
				} else if (velX < 0) {
					velX -= 0.005f;
				}
				velX = Game.clamp(velX, -10, 10);
				
				int spawn = r.nextInt(10);
				if (spawn == 0) {
					Handler.addObject(new EnemyBossBullet((int)x + 48, (int)y + 48, ID.EnemyBossBullet, Handler));
				}
			
		}
		
		if (x <= 0 || x >= Game.WIDTH - 165) {
			velX *= -1;
		} // if*/
			
	//	Handler.addObject(new Trail((int)x, (int)y, ID.Trail, new Color(144, 12, 12), 120, 120, 0.08f, Handler));
	} // tick

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(144, 12, 12));
		
		g.fillRect((int)x, (int)y, 160, 160);
	} // render

	@Override
	public Rectangle getBounds() {
			
		return new Rectangle((int)x, (int)y, 96, 96);
	} // getBounds
}
