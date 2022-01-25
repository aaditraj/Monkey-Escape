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
		Bullet newBullet = new Bullet(getX(), getY(), "ShootingEnemy");
		newBullet.setVelocity(0,coconutSpeed);
		return newBullet;
	}

}
