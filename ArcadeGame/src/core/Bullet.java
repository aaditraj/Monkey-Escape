package core;
import java.util.Arrays;

import enemies.MobileEnemy;
import enemies.SideShooter;
import players.Player;

public class Bullet extends Collider
{
	String owner;
	public Bullet(double x, double y, String owner) {
		super(new String[] {"assets/bullet.png"}, 1, x, y, 5.0, 5.0, 0, 0);
		this.owner = owner;
		this.setMobile(true);
	}
	
	public String getOwner() {
		return owner;
	}
	
	public double collide(Collider collider) {
		if (owner == "player") {
			if (collider instanceof MobileEnemy) {
				collider.changeHealth(-1);
			} else if(collider instanceof Player) {
				return 0.0;
			}
		} else {
			if (collider instanceof Player) {
				collider.changeHealth(-1);
			} else if (collider instanceof SideShooter){
				return 0.0;
			}
		}
		return getHealth();  
	}
	
	

}
