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
	public Bullet(double x, double y, String owner, String image) {
		super(new String[] {image}, 1, x, y, 5.0, 5.0, 0, 0);
		this.owner = owner;
		this.setMobile(true);
	}
	public Bullet(double x, double y, String owner, String image,double width,double height,int damage) {
		super(new String[] {image}, 1, x, y, width, height, 0, 0);
		this.owner = owner;
		this.damage = damage;
		this.setMobile(true);
	}
	public String getOwner() {
		return owner;
	}
	public void moveBy(double x, double y, ArrayList<Collider> colliders) {
		boolean[] directions = intersects(colliders);
		
		if ((!directions[0] && y < 0) || (!directions[2] && y > 0)) {
			this.y += y;
		} 
		if ((!directions[1] && x > 0) || (!directions[3] && x < 0)) {
			this.x += x;
		}
	}
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
