import processing.core.PApplet;
import processing.core.PImage;

public class MobileEnemy extends Collider {
	
	private int direction;
	private double endX, endY;
	private double vx, vy;
	private static final String[] images = new String[] {"assets/MobileEnemy/gorilla.png",
			"assets/MobileEnemy/gorilla-2.png", "assets/MobileEnemy/gorilla-3.png", "assets/MobileEnemy/gorilla-4.png"};
	
	
		public MobileEnemy(double health, double startX, double startY,  double endX, double endY, double vx, double vy, double width, double height, int direction) {
super(images, health, startX, startY, width, height, vx, vy);
		this.endX = endX;
		this.endY = endY;
//		this.vx = vx;
		this.vx = vx;
		this.vy = vy;
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
