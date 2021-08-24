package game;

import java.awt.Graphics;
import java.util.LinkedList;

public class handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>(); 
	
	
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		} // for
	} // tick
		
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		} // for
	} // render
		
	public void addObject(GameObject object) {
		this.object.add(object);
	} // addObject
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	} // removeObject
	
	public void clearEnemies() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			if (tempObject.getId() == ID.Player) {
				object.clear();
				if (Game.gameState != Game.STATE.End) {
					addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player, this));
				}
			}
		}
	} // clearEnemies
}
