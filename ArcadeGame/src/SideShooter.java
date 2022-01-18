import processing.core.PApplet;
import processing.core.PImage;
public class SideShooter extends Collider {
	int bulletFrequency = 0;
	int direction = 0;
	public SideShooter(int x, int y, int bulletFrequency, int width, int height, double vx, double vy, int direction) {
		super(new String[] {"sideShooter.png","SideShooterLeft.png"}, Integer.MAX_VALUE,x,y,width,height, 0,0);
		//1 stands for right, -1 stands for left
		if(direction == -1) {
			goToNextImage();
		}
		this.direction = direction;
		this.bulletFrequency = bulletFrequency;
	}
	public Collider shoot() {
		Collider mc = new Collider(new String[] {"bulletimage"}, 1, this.getX(), this.getY(), 2.0, 2.0, 0.0, 0.0);
		mc.setVelocity(direction * 3, 0);
		return mc;
	}
	
}
