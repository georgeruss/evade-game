package game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject{

	private float alpha = 1;
	
	private Color color;
	private handler Handler;
	
	private int width, height;
	
	private float life;
	
	//life = 0.001 - 0.1
	
	public Trail(int x, int y, ID id, Color color,  int width, int height, float life, handler Handler) {
		super(x, y, id);
		this.color = color;
		this.Handler = Handler;
		this.width = width;
		this.height = height;
		this.life = life;
	} // Trail

	@Override
	public void tick() {
		if (alpha > life) {
			alpha -= life - 0.001f;
		} else {
			Handler.removeObject(this);
		} // if-else
		
	} // tick

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setComposite(makeTransparent(alpha));
		
		g.setColor(color);
		g.fillRect((int)x, (int)y, width, height);

		g2d.setComposite(makeTransparent(1));
	}

	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));
	} // makeTransparent
	
	@Override
	public Rectangle getBounds() {
		return null;
	}

}
