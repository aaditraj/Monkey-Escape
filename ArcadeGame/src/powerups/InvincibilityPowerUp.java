package powerups;

import players.ShootingPlayer;

public class InvincibilityPowerUp extends PowerUp {

	public InvincibilityPowerUp(String[] images, double health, double x, double y, double width, double height,
			double vx, double vy) {
		super(images, health, x, y, width, height, vx, vy);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void checkCollision(ShootingPlayer player) {
		// TODO Auto-generated method stub
		
	}

}
