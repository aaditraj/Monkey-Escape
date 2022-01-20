import java.util.Arrays;

public class Bullet extends Collider
{
	String owner;
	public Bullet(double x, double y, String owner) {
		super(new String[] {"assets/bullet.png"}, 1, x, y, 2.0, 2.0, 0, 0);
		this.owner = owner;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public double collide(Collider collider) {
		if (owner == "player") {
			if (collider instanceof MobileEnemy) {
				collider.changeHealth(-1);
				return getHealth();
			}
		} else {
			if (collider instanceof Player) {
				collider.changeHealth(-1);
				return getHealth();
			}
		}
		return 0.0;
	}
	
	

}
