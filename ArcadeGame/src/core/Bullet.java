package core;
import java.util.ArrayList;
import enemies.MobileEnemy;
import players.Player;

public class Bullet extends Collider
{
	public static final String bananaLocation = "assets/Projectiles/banana.png";
	public static final String bulletLocation = "assets/bullet.png";
	public String owner = "";
	int damage = 1;
	/**
	 * The constructor that constructs a new bullet
	 * @param x The x coordinate of the bullet
	 * @param y The y coordinate of the bullet
	 * @param owner The thing that shot the bullet
	 * @param image The image to display for the bullet
	 */
	public Bullet(double x, double y, String owner, String image) {
		super(new String[] {image}, 1, x, y, 5.0, 5.0, 0, 0);
		this.owner = owner;
		this.setMobile(true);
	}
	/**
	 * The second bullet constructor
	 * @param x The X coordinate of the bullet
	 * @param y The Y coordinate of the bullet
	 * @param owner The owner of the bullet
	 * @param image The image to display for the bullet
	 * @param width The width of the bullet
	 * @param height The height of the bullet
	 * @param damage The damage the bulet causes upon impact
	 */
	public Bullet(double x, double y, String owner, String image,double width,double height,int damage) {
		super(new String[] {image}, 1, x, y, width, height, 0, 0);
		this.owner = owner;
		this.damage = damage;
		this.setMobile(true);
	}
	/**
	 * A getter method to return the bullets owner
	 * @return The bullets owner
	 */
	public String getOwner() {
		return owner;
	}
	/**
	 * A method that moves the bullet, and makes sure it is not colliding wwith any other objects.
	 * @param x the amount to move the bullet in the x-axis
	 * @param y the amount to move the bullet in the y-axis
	 * @param colliders The colliders that it should not go through
	 */
	public void moveBy(double x, double y, ArrayList<Collider> colliders) {
		boolean[] directions = intersects(colliders);
		
		if ((!directions[0] && y < 0) || (!directions[2] && y > 0)) {
			this.y += y;
		} 
		if ((!directions[1] && x > 0) || (!directions[3] && x < 0)) {
			this.x += x;
		}
	}
	/**
	 * Make the bullet subtract the correct amount of health from the player
	 * @param collider
	 */
	public double collide(Collider collider) {
		if (owner == "player") {
			if (collider instanceof MobileEnemy) {
				collider.changeHealth(-damage);
			} else if(collider instanceof Player) {
				return 0.0;
			}
		} else {
			if (collider instanceof Player) {
				collider.changeHealth(-damage);
			}else {
				return 0.0;
			}
		}
		return getHealth();  
	}
	
	

}
