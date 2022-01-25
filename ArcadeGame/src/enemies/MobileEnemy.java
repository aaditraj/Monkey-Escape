package enemies;
import java.util.ArrayList;

import core.Collider;
import processing.core.PApplet;
import processing.core.PImage;

public class MobileEnemy extends Collider {
	
	private double endX, endY;
	private float proportion; 
	private float initHealth;
	private static final String[] images = new String[] {"assets/MobileEnemy/gorilla.png",
			"assets/MobileEnemy/gorilla-2.png", "assets/MobileEnemy/gorilla-3.png", "assets/MobileEnemy/gorilla-4.png"};
	
	// direction = 1 --> moving lateral, direction=0 --> moving vertical
	public MobileEnemy(double health, double startX, double startY,  double endX, double endY, double vx, double vy, double width, double height, int direction) {
		super(new String[] {"assets/MobileEnemy/gorilla.png",
				"assets/MobileEnemy/gorilla-2.png", "assets/MobileEnemy/gorilla-3.png", "assets/MobileEnemy/gorilla-4.png"}, health, startX, startY, width, height, vx, vy);
		this.endX = endX;
		this.endY = endY;
		this.setMobile();
		proportion = (float)getWidth()/(float)getHealth(); 
		initHealth = (float)getHealth();
	}
	
	// call this method before draw
	public void act(ArrayList<Collider> colliders) {
		super.moveBy(vx, vy, colliders);
		double largerX = Math.max(getInitX(), endX);
		double smallerX = Math.min(getInitY(), endY);
		double largerY = Math.max(getInitY(), endY);
		double smallerY = Math.min(getInitY(), endY);
		if (getX() >= largerX || getX() <= smallerX) {
			vx *= -1;
		}
		if (getY() >= largerY || getY() <= smallerY) {
			vy *= -1;
		}
	}
	
	public void draw(PApplet marker) {
		super.draw(marker);
		
		marker.push();
		marker.rect((float)getX(), (float)getY() - (float)getHeight()/2, (float)getWidth(), 10);
		marker.fill(marker.color(0,255,0));
		marker.rect((float)getX(), (float)getY() - (float)getHeight()/2, (float)(getWidth() - proportion*Math.abs(initHealth-getHealth())), 10);
		marker.pop();
	}
	
}
