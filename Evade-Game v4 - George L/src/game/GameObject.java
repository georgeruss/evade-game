package game;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	
	protected float x, y;
	protected ID id;
	protected float velX, velY;
	
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	} // GameObject
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void setX(float x) {
		this.x = x;
	} // setX
	
	public void setY(float y) {
		this.y = y;
	} // setY
	
	public float getX() {
		return x;
	} // getX
	
	public float getY() { 
		return y;
	} // getY
	
	public void setId(ID id) {
		this.id = id;
	} // setId
	
	public ID getId() {
		return id;
	} // getId
	
	public void setVelX(float velX) {
		this.velX = velX;
	} // setVelX
	
	public void setVelY(float velY) {
		this.velY = velY;
	} // setVelY
	
	public float getVelX() {
		return velX;
	} // getVelX
	
	public float getVelY() {
		return velY;
	} // getVelY
	
} // GameObject
