package enemies;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import core.Bullet;
import core.Collider;
import processing.core.PApplet;
import processing.core.PImage;

public class ShootingEnemy extends Collider{
	private Timer shootRate; 
	private final long dropRate = 1000L;
	private final String[] coconutImages = new String[] {"coconut.png"};
	private final ArrayList<Bullet> coconuts;
	private int coconutSpeed = 10;
	
	
	public ShootingEnemy(double health, double x, double y, double width, double height) {
		super(new String[] {"assets/ShootingEnemy/Monkey.png"}, health, x, y, width, height, 0, 0);
		
		
		shootRate = new Timer();	
		
		coconuts = new ArrayList<Bullet>();
		setMobile(true);
		
	}
	
	public Bullet drop()
	{
		Bullet newBullet = new Bullet(getCenterX() - 25,(getCenterY() + getHeight()/2)+5, "ShootingEnemy","assets/Coconut.png",50,50,5);
		newBullet.setVelocity(0,coconutSpeed);
		return newBullet;
	}

	public Bullet drop(double playerX, double playerY)
	{
		Bullet newBullet = new Bullet(getCenterX() - 25,(getCenterY() + getHeight()/2)+5, "ShootingEnemy","assets/Coconut.png",50,50,5);
		newBullet.setVelocity(playerX - this.getCenterX(), playerY - this.getCenterY());
		return newBullet;
	}

}
