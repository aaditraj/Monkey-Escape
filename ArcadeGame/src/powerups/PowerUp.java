package powerups;

import core.Collider;
import players.ShootingPlayer;

public abstract class PowerUp extends Collider {

	public PowerUp(String[] images, double health, double x, double y, double width, double height, double vx,
			double vy) {
		super(images, health, x, y, width, height, vx, vy);
		// TODO Auto-generated constructor stub
	}

	public abstract void checkCollision(ShootingPlayer player);
	
	
	
}
