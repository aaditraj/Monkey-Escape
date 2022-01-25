package players;
import core.Collider;
import processing.core.PApplet;

public abstract class Player extends Collider{
	private double points, ammo; 
	private double reloadTime; 
	private boolean isJumping; 
	private float proportion; 
	private float initHealth;


	public Player(String[] images, double health, double x, double y, double width, double height, double vx,
			double vy, double reloadTime) {
		super(images, health, x, y, width, height, vx, vy);
		this.reloadTime = reloadTime; 
		this.setMobile();
		proportion = (float)getWidth()/(float)getHealth(); 
		initHealth = (float)getHealth();
		// TODO Auto-generated constructor stub
	}

	public abstract Collider shoot(int mouseX, int mouseY);
	
	
	public void draw(PApplet marker) {
		super.draw(marker);
		
		
		marker.push();
		marker.rect((float)getX(), (float)getY() - (float)getHeight()/2, (float)getWidth(), 10);
		marker.fill(marker.color(0,255,0));
		marker.rect((float)getX(), (float)getY() - (float)getHeight()/2, (float)(getWidth() - proportion*Math.abs(initHealth-getHealth())), 10);
		marker.pop(); 
	}

}
