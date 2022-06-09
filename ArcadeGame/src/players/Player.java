package players;
import core.Collider;
import processing.core.PApplet;

public abstract class Player extends Collider{
	private double points;
	private int maxAmmo;
	private int currentAmmo;
	public static double shootingPlayerReloadTime = 20; // per bullet 
	private boolean isJumping; 
	private float proportion; 
	private float initHealth;
	private String[] images;
	private String[] left;
	private String[] right;

	public Player(String[] images, String[] left, String[] right, double health, double x, double y, double width, double height, double vx,
			double vy, int maxAmmo) {
		super(images, health, x, y, width, height, vx, vy);
		this.images = images;
		this.left = left;
		this.right = right;
		this.setMobile(true);
		proportion = (float)getWidth()/(float)getHealth(); 
		initHealth = (float)getHealth();
		this.maxAmmo = maxAmmo;
		this.currentAmmo = maxAmmo;
		// TODO Auto-generated constructor stub
	}

	public abstract Collider shoot(int mouseX, int mouseY);
	public void setLeft(String[] left) {
		this.left = left;
	}
	public void setRight(String[] right) {
		this.right = right;
	}
	public void setCurrent(String[] images) {
		this.images = images;
	}
	public String[] getLeft() {
		return left;
	}
	public String[] getRight() {
		return right;
	}
	public String[] getCurrent() {
		return images;
	}
	public void increaseAmmo() {
		if (currentAmmo < maxAmmo) {
			currentAmmo++;
		}
	}
	
	public void decreaseAmmo() {
		currentAmmo--;
	}
	
	public int getAmmo() {
		return currentAmmo;
	}
	public void setHealth(int health) {
		this.health = health;
	}
		
	public void draw(PApplet marker) {
		super.draw(marker);
		
		if(health < 0)
		{
			health = 0;
		}
		
		marker.push();
		marker.rect((float)getX(), (float)getY() - (float)getHeight()/3, (float)getWidth(), 10);
		marker.fill(marker.color(0,0,255));
		marker.rect((float)getX(), (float)getY() - (float)getHeight()/3, (float)(getWidth() - ((float)getWidth()/(float)maxAmmo)*Math.abs(maxAmmo-currentAmmo)), 10);
		
		marker.fill(marker.color(0,0,0));

		marker.rect((float)getX(), (float)getY() - (float)getHeight()/2, (float)getWidth(), 10);
		marker.fill(marker.color(0,255,0));
		marker.rect((float)getX(), (float)getY() - (float)getHeight()/2, (float)(getWidth() - proportion*Math.abs(initHealth-getHealth())), 10);
		marker.pop(); 
	}

}
