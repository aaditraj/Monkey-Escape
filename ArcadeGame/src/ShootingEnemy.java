import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import processing.core.PApplet;
import processing.core.PImage;

public class ShootingEnemy extends Collider{
	private Timer shootRate; 
	private Collider projectile;
	private final long dropRate = 1000L;
	private final String[] coconutImages = new String[] {"coconut.png"};
	private final ArrayList<Collider> coconuts;
	
	
	public ShootingEnemy(int health, double x, double y, int width, int height, double projectileVX, double projectileVY) {
		super(new String[] {"monkey.png"}, health, x, y, width, height, 0, 0);
		
		projectile = new Collider(coconutImages, health, x, y, width, height, projectileVX, projectileVY);
		
		shootRate = new Timer();	
		
		coconuts = new ArrayList<Collider>();
	}
	
	public void startDropping(PApplet marker)
	{
		TimerTask task = new TimerTask() {
				public void run()
				{
					draw(marker);
	        }
	    };
	    
	    shootRate.schedule(task, dropRate);
	}
	
	 public void draw(PApplet marker) {
		Collider newProjectile = new Collider(coconutImages, projectile.getHealth(), projectile.getX(), projectile.getY(), projectile.getWidth(), projectile.getHeight(), projectile.getVX(), projectile.getVY());
     	
		coconuts.add(newProjectile); 
		
		for(Collider coconut: coconuts)
		{
			coconut.draw(marker);
			coconut.vx *= 1.01;
		}
	 }

}
