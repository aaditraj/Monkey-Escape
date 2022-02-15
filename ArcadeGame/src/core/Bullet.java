package core;
import java.util.Arrays;

import enemies.MobileEnemy;
import enemies.SideShooter;
import players.Player;

public class Bullet extends Collider
{
	public static final String bananaLocation = "assets/Projectiles/banana.png";
	public static final String leafLocation = "assets/Projectiles/leaf.png";
	String owner;
	int damage = 1;
	public Bullet(double x, double y, String owner, String image) {
		super(new String[] {image}, 1, x, y, 20.0, 20.0, 0, 0);
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
