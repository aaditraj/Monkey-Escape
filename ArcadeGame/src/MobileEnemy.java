import processing.core.PApplet;
import processing.core.PImage;

public class MobileEnemy extends Collider {
	
	private int direction;
	private double endX, endY;
	private double vx, vy;
	private final String[] images = new String[] {"assets/gorilla.png",
			"assets/gorilla-2.png", "assets/gorilla-3.png", "assets/gorilla-4.png"
	};
	
	// direction = 1 --> moving lateral, direction=0 --> moving vertical
	public MobileEnemy(String[] images, double health, double startX, double startY, double width, double height, double endX, double endY, double vx, double vy, int direction) {
		super(images, health, startX, startY, width, height);
		this.endX = endX;
		this.endY = endY;
		this.vx = vx;
		this.vy = vy;
		this.direction = direction;
	}
	
	// call this method before draw
	public void act(Collider[] colliders) {
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
}
