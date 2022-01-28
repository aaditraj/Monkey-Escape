package enemies;
import core.Bullet;
import core.Collider;
import processing.core.PApplet;
import processing.core.PImage;
public class SideShooter extends Collider {
	//int bulletFrequency = 0;
	int direction = 0;
	int bulletSpeed;
	public int bulletFrequency;
	public SideShooter(int x, int y,int bulletFrequency, int width, int height, int direction) {
		super(new String[] {"assets/SideShooter/SideShooter.png","assets/SideShooter/SideShooterLeft.png"}, Integer.MAX_VALUE,x,y,width,height, 0,0);
		//-1 stands for right, 1 stands for left
		if(direction == 1) {
			goToImage(1); 
		}
		this.direction = direction;
		this.bulletFrequency = bulletFrequency;
		this.bulletSpeed = 10;
	}
	public Bullet shoot() {
		Bullet mc = new Bullet(getCenterX(),getCenterY(),"SideShooter",Bullet.bulletLocation);
		mc.setVelocity(direction * bulletSpeed, 0);
		return mc;
	}
	
}
