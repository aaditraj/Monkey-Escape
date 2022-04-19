package powerups;

import java.util.ArrayList;

import core.Collider;
import players.ShootingPlayer;

public class InvincibilityPowerUp extends PowerUp {

	public InvincibilityPowerUp(String[] images, double health, double x, double y, double width, double height,
			double vx, double vy) {
		super(images, health, x, y, width, height, vx, vy);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void checkCollision(ArrayList<Collider> colliders) {
		// TODO Auto-generated method stub
		ShootingPlayer player = (ShootingPlayer) colliders.get(0);
	}

}
