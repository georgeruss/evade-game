package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 7587352373186487445L;

	public static int WIDTH = 1080;
	public static int HEIGHT = 720;
	
	
	private String gameTitle= "Survive v.1.08.11.20.21";
	private Thread thread;
	private boolean running = false;
	
	public static boolean paused = false;
	
	private handler Handler;
	private Random r;
	private Menu menu;
	
	private HUD hud;
	
	private Spawn spawner;
	
	public enum STATE {
		Menu,
		Select,
		Help,
		Game,
		End
	};
	
	public static STATE gameState = STATE.Menu;
	
	public Game() {
		
		Handler = new handler();
		
		menu  = new Menu(this, Handler);
		this.addKeyListener(new KeyInput(Handler, this));
		this.addMouseListener(menu);
	
		AudioPlayer.load();
	//	AudioPlayer.getMusic("music").loop();
		
		new window(WIDTH, HEIGHT, gameTitle, this);
		
		hud = new HUD();
		spawner = new Spawn(Handler, hud);
		r = new Random();
		
		if (gameState == STATE.Game) {
			
			for (int i = 0; i < Handler.object.size(); i++) {
				GameObject tempObject = Handler.object.get(i);
				
				Handler.removeObject(tempObject);
			} // for
			
			//Handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, Handler));
			//Handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(350), ID.BasicEnemy, Handler));
			//Handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(350), ID.FastEnemy, Handler));
			//Handler.addObject(new BossEnemy((Game.WIDTH / 2) -90, -120, ID.BossEnemy, Handler));
		} else {
			for (int i = 0; i < 15; i++) {
				//Handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(350), ID.MenuParticle, Handler));
			}
		}
		
	} // Game
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	} // start
	
	public void stop(){
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // stop
	
	public void run(){
		
		long lastTime = System.nanoTime();
		
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		
		long timer = System.currentTimeMillis();
		
		int frames = 0;
		
		while (running) { 
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while (delta >= 1) {
				tick();
				delta--;
			} // while
			
			if (running) 
				render();
				frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
			} // if
		} // while
		stop();
	} // run
	
	
	private void tick() {
		
		if (gameState == STATE.Game) {
			if (!paused) {
				Handler.tick();
				hud.tick();
				spawner.tick();
				
				if (HUD.HEALTH <= 0) {
					HUD.HEALTH = 100;
					gameState = STATE.End;
					Handler.clearEnemies();
					for (int i = 0; i < 50; i++) {
						Handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(350), ID.MenuParticle, Handler));
					}
					
				}
			}
		} else if (gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select){
			menu.tick();
			Handler.tick();
		}
		
	} // tick
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
	
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		} // if
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(new Color(227, 198, 121));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, WIDTH, HEIGHT - 100);
		
		Handler.render(g);
		
		if (paused) {
			//Font f = new Font("Courier", 1, 48);
			//g.setFont(font);
			g.setColor(Color.white);
			g.drawString("PAUSED", 415, 195);
		}
		
		if (gameState == STATE.Game) {
			hud.render(g);
		} else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End 
				  || gameState == STATE.Select){
			menu.render(g);
		} 
		
		g.dispose();
		bs.show();
	} // render
	
	public static float clamp(float var, float min, float max) {
		if (var >= max) {
			return var = max;
		} else if (var <= min) {
			return var = min;
		} else {
			return var;
		}
	} // clamp
	
	public static void main(String[] args){
		if (HUD.level == 1) {
			new Game();
		}
	} // main

} //game
