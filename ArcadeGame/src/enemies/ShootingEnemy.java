package enemies;
import java.util.ArrayList;
import java.util.Timer;

import core.Bullet;
import core.Collider;

public class ShootingEnemy extends Collider{
	private Timer shootRate; 
	private final long dropRate = 1000L;
	private final String[] coconutImages = new String[] {"coconut.png"};
	private final ArrayList<Bullet> coconuts;
	private int coconutSpeed = 10;
	/**
	 * The constructor for a shooting enemy
	 * @param health The health of the shooting enemy(Usually infinity)
	 * @param x The x-position of the shooting enemy
	 * @param y The y-position of the shooting enemy
	 * @param width The width of the shooting enemy
	 * @param height The height of the shooting enemy
	 */
	public ShootingEnemy(double health, double x, double y, double width, double height) {
		super(new String[] {"assets/ShootingEnemy/Monkey.png","assets/ShootingEnemy/Monkey2.png"}, health, x, y, width, height, 0, 0);
		
		
		shootRate = new Timer();	
		
		coconuts = new ArrayList<Bullet>();
		setMobile(true);
		
	}
	/**
	 * The method to drop one coconut straight down
	 * @return The bullet that was dropped
	 */
	public Bullet drop()
	{
		Bullet newBullet = new Bullet(getCenterX() - 25,(getCenterY() + getHeight()/2)+5, "ShootingEnemy",new String[] {"assets/Projectiles/Coconut.png"},50,50,5);
		newBullet.setVelocity(0,coconutSpeed);
		return newBullet;
	}
	/**
	 * The method to drop one coconut, so it moves toward the player
	 * @param playerX The players X coordinate
	 * @param playerY The players Y coordinate
	 * @return the bullet that was dropped
	 */
	public Bullet drop(double playerX, double playerY)
	{
		Bullet newBullet = new Bullet(getCenterX() - 25,(getCenterY() + getHeight()/2)+5, "ShootingEnemy",new String[] {"assets/Projectiles/Coconut.png"},50,50,5);
		newBullet.setVelocity(playerX - this.getCenterX(), playerY - this.getCenterY());
		return newBullet;
	}

}
