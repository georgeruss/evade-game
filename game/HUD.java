package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Random;

import game.Game.STATE;

public class HUD {

	public static int HEALTH = 100;
	
	private int greenValue = 255;
	
	public static int balance = 0;
	public static int score = 0;
	public static int level = 1;
//	private int health = 100;
	
	private Game game;
	private handler Handler;
	private Random r = new Random();
	
	public void tick() {
		
		HEALTH = (int)Game.clamp(HEALTH, 0, 100);
		
		greenValue = (int)Game.clamp(greenValue, 0, 225);
		greenValue = HEALTH * 2;
		if (level >= 1) {
			score += 2;
		} // if
		//redValue = Game.clamp(redValue, 0, 225);
		 
		
		//blueValue = Game.clamp(blueValue, 0, 40);
		//blueValue = HEALTH + 2;
		
	} // tick
	
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(12, 625, 204, 34);
		g.setColor(Color.gray);
		g.fillRect(15, 625, 200, 32);
		g.setColor(new Color(140, greenValue, 0));
		g.fillRect(15, 625, HEALTH * 2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 625, 200, 32);
		
		g.drawString("Score: " + score, 10, 600);
		g.drawString("Level: " + level, 1080/2 - 32, 32);
		
		g.drawString("Health: " + (double)HEALTH, 70, 645);
		
		Font menu = new Font("times new roman", 1, 16);
		
		g.setFont(menu);
		g.setColor(Color.black);
		g.fillRect(300, 628, 68, 34);
		g.setColor(Color.white);
		g.fillRect(303, 628, 64, 32);
		g.setColor(Color.black);
		g.drawRect(303, 628, 64, 32);
		g.setColor(Color.black);
		g.drawString("Menu", 315, 649);
		
		
	} // render
	
	/**public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
			Font menu = new Font("sansrif", 1, 12);
			
			Font menu = new Font("Courier", 1, 35);
			
			g.setFont(menu);
			g.setColor(Color.black);
			g.fillRect(300, 628, 68, 34);
			g.setColor(Color.white);
			g.fillRect(303, 628, 64, 32);
			g.setColor(Color.black);
			g.drawRect(303, 628, 64, 32);
			g.setColor(Color.black);
			g.drawString("Menu", 318, 647);
			
			if (mouseOver(mx, my, )) {
				game.gameState = STATE.Menu;
				
			}
		
		//if (mouseOver(mx, my, 25, 500, 200, 64)) {
			//System.exit(0);
		//}
	} // mousePressed
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else {
				return false;
			}
		} else { 
			return false;
		}
	} // mouseOver */
	
	public void setScore(int score) {
		this.score = score;
	} // score
	
	public int getScore() {
		return score;
	} // getScore
	
	public int getLevel() {
		return level;
	} // getLevel
	
	public void setLevel(int level) {
		this.level = level;
	}
} // HUD
