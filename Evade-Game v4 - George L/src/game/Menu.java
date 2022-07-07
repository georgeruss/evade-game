package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;

import game.Game.STATE;


public class Menu extends MouseAdapter {

	private Game game;
	private handler Handler;
	private Random r = new Random();
	private HUD hud;
	
	public static final Font TITLEFONT = new Font("Calibri", Font.BOLD, 40);
	
	Color col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
	
	public Menu (Game game, handler Handler) {
		this.game = game;
		this.Handler = Handler;
	} // Menu
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if (game.gameState == STATE.Menu) {
		// Play button
			if (mouseOver(mx, my, 423, 300, 200, 64)) {
				game.gameState = STATE.Select;
				AudioPlayer.getSound("menu_sound").play();
				/**game.gameState = STATE.Game;
				Handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, Handler));
				Handler.clearEnemies();
				AudioPlayer.getSound("menu_sound").play();
				for (int i = 0; i < r.nextInt(1 - - 3) + 3; i++) {
					Handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(350), ID.BasicEnemy, Handler));
				}*/
				
				//Handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(350), ID.FastEnemy, Handler));
			//Handler.addObject(new BossEnemy((Game.WIDTH / 2) -90, -120, ID.BossEnemy, Handler));
			
			}
		
		// Help button
			if (mouseOver(mx, my, 423, 400, 200, 64)) {
				game.gameState = STATE.Help;
				AudioPlayer.getSound("menu_sound").play();
			}
		
		// Quit Button
			if (mouseOver(mx, my, 423, 500, 200, 64)) {
				AudioPlayer.getSound("menu_sound").play();
				System.exit(1);
			}
		}
		
		if (game.gameState == STATE.Game) {
			if (mouseOver(mx, my, 305, 628, 32, 64)) {
				game.gameState = STATE.Menu;
				Handler.clearEnemies();
				AudioPlayer.getSound("game_menu_sound").play();
				
				for (int i = 0; i < Handler.object.size(); i++) {
					GameObject tempObject = Handler.object.get(i);
					if (tempObject.getId() == ID.Player) {
						Handler.removeObject(tempObject);
					}
				}
			}
		}
		// Back button
		if (game.gameState == STATE.Help) {
			if (mouseOver(mx, my, 25, 500, 200, 64)) {
				game.gameState = STATE.Menu;
				AudioPlayer.getSound("game_menu_sound").play();
				return;
			}
		}
		
		if (game.gameState == STATE.End) {
			if (mouseOver(mx, my, 443, 395, 203, 64)) {
				HUD.level = 1;
				HUD.score = 0;;
				game.gameState = STATE.Menu;
				AudioPlayer.getSound("menu_sound").play();
			} 
		}
	} // mousePressed
	
	public void mouseReleased(MouseEvent e) {
		
	} // mouseReleased
	
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
	} // mouseOver 
	
	public void tick() {
		
	} // tick
	
	public void render(Graphics g) {
		
		if (game.gameState == STATE.Menu) {
				try {
					//InputStream is = OptionsValues.class.getResourceAsStream("fonts//KOMIKAX_.ttf");
					URL fontUrl = new URL("file:res/Daydream.ttf");
					Font menu = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream()).deriveFont(Font.TRUETYPE_FONT, 60);
					
					URL fontUrl2 = new URL("file:res/04B_30__.ttf");
					Font icons = Font.createFont(Font.TRUETYPE_FONT, fontUrl2.openStream()).deriveFont(Font.TRUETYPE_FONT, 30);
					
				//	Font menu = new Font("Courier", 1, 75);
					//Font icons = new Font("Courier", 1, 35);
					
					g.setColor(Color.black);
					g.fillOval(275, 118, 480, 140);
					g.setColor(Color.white);
					g.fillOval(320, 130, 400, 125);
					g.setColor(new Color (220, 198, 121));
					g.fillOval(320, 130, 400, 110);
					g.setFont(menu);
					g.setColor(Color.BLACK);
					g.drawString("Evade", 412 - 65, 197);
					g.setColor(Color.white);
					g.drawString("Evade", 414 - 55, 197);
					g.setFont(menu);
					g.setColor(new Color(190, 76, 57));
					g.drawString("Evade", 415 - 50, 197);
					
					//g.setFont(menu);
					g.setColor(Color.white);
					//g.drawString("Menu", 65, 255);
					
					//Play Button
					g.setFont(icons);
					g.setColor(Color.BLACK);
					g.fillRect(420, 305, 203, 64);
					g.setColor(Color.white);
					g.fillRect(423, 300, 200, 64);
					g.setColor(new Color(159, 159, 159));
					g.fillRect(435, 307, 175, 50);
					g.setColor(Color.BLACK);
					g.drawString("Play", 475, 340 + 6);
					g.setColor(Color.white);
					g.drawString("Play", 477, 339 + 3);
					
					// Help Button
					g.setColor(Color.BLACK);
					g.fillRect(420, 405, 203, 64);
					g.setColor(Color.white);
					g.fillRect(423, 400, 200, 64);
					g.setColor(new Color(159, 159, 159));
					g.fillRect(435, 407, 175, 50);
					g.setColor(Color.BLACK);
					g.drawString("Help", 475, 440 + 6);
					g.setColor(Color.white);
					g.drawString("Help", 477, 439 + 3);
					
					// Quit Button
					g.setColor(Color.BLACK);
					g.fillRect(420, 505, 203, 64);
					g.setColor(Color.white);
					g.fillRect(423, 500, 200, 64);
					g.setColor(new Color(159, 159, 159));
					g.fillRect(435, 507, 175, 50);
					g.setColor(Color.BLACK);
					g.drawString("Quit", 475, 540 + 6);
					g.setColor(new Color(190, 79, 79));
					g.drawString("Quit", 477, 539 + 3);
				} catch (Exception e) {
					e.getStackTrace();
				}
		} else if (game.gameState == STATE.Help) {
			Font help = new Font("Courier", 1, 50);
			Font icons = new Font("Courier", 1, 35);
			g.setFont(help);
			g.setColor(Color.white);
			g.drawString("Help", 450, 175);
			
			g.setFont(icons);
			g.setColor(Color.black);
			g.fillRect(25, 505, 203, 64);
			g.setColor(Color.white);
			g.fillRect(28, 500, 200, 64);
			g.setColor(Color.black);
		//	g.drawRect(28, 500, , 52);
			g.setColor(Color.BLACK);
			g.drawString("Back", 80, 540);
			g.setColor(new Color(227, 198, 121));
			g.drawString("Back", 82, 539);
			
		} else if (game.gameState == STATE.End) {
			Font help = new Font("Courier", 1, 50);
			Font icons = new Font("Courier", 1, 35);
			Font TryAgain = new Font("Courier", 1, 25);
			
			g.setFont(help);
			g.setColor(Color.BLACK);
			g.drawString("Game Over", 415, 200);
			g.setColor(Color.WHITE);
			g.drawString("Game Over", 415, 195);
			
			g.setFont(icons);
			g.setColor(Color.black);
			g.drawString("You lost!", 450, 300);
			g.setColor(Color.white);
			g.drawString("You lost!", 453, 298);
			
			g.setColor(Color.black);
			g.drawString("Your Score was:" + HUD.score, 350, 350);
			g.setColor(col);
			g.drawString("Your Score was:" + HUD.score, 353, 348);
			
			g.setFont(TryAgain);
			g.setColor(Color.black);
			g.fillRect(440, 400, 203, 66);
			g.setColor(Color.white);
			g.fillRect(443, 395, 203, 64);
			g.setColor(Color.black);
			g.drawString("Try Again", 475, 435);
			g.setColor(new Color(190, 79, 79));
			g.drawString("Try Again", 476, 434);
			
		} else if (game.gameState == STATE.Select) {
			
			Font menu = new Font("Courier", 1, 50);
			Font icons = new Font("Courier", 1, 35);
			
			g.setFont(menu);
			g.setColor(Color.BLACK);
			g.drawString("Please", 428, 120);
			g.drawString("Select Difficulty", 290, 200);
			g.setFont(menu);
			g.setColor(Color.white);
			g.drawString("Please", 430, 115);
			g.setColor(col);
			g.drawString("Select Difficulty", 290, 195);
			
			//g.setFont(menu);
			g.setColor(Color.white);
			//g.drawString("Menu", 65, 255);
			
			// easy diff Button
			g.setFont(icons);
			g.setColor(Color.BLACK);
			g.fillRect(420, 305, 203, 64);
			g.setColor(Color.white);
			g.fillRect(423, 300, 200, 64);
			g.setColor(Color.BLACK);
			g.drawString("Easy", 480, 340);
			g.setColor(new Color(100, 177, 67));
			g.drawString("Easy", 482, 339);
			
			// Normal Diff Button
			g.setColor(Color.BLACK);
			g.fillRect(420, 405, 203, 64);
			g.setColor(Color.white);
			g.fillRect(423, 400, 200, 64);
			g.setColor(Color.BLACK);
			g.drawString("Normal", 465, 440);
			g.setColor(new Color(229, 173, 53));
			g.drawString("Normal", 467, 439);
			
			// hard diff Button
			g.setColor(Color.BLACK);
			g.fillRect(420, 505, 203, 64);
			g.setColor(Color.white);
			g.fillRect(423, 500, 200, 64);
			g.setColor(Color.BLACK);
			g.drawString("Hard", 475, 540);
			g.setColor(new Color(190, 79, 79));
			g.drawString("Hard", 477, 539);
			
			// back button
			g.setColor(Color.black);
			g.fillRect(25, 505, 203, 64);
			g.setColor(Color.white);
			g.fillRect(28, 500, 200, 64);
			g.setColor(Color.black);
		//	g.drawRect(28, 500, , 52);
			g.setColor(Color.BLACK);
			g.drawString("Back", 80, 540);
			g.setColor(new Color(202, 166, 88));
			g.drawString("Back", 82, 539);
		
		} // 
	}
}
