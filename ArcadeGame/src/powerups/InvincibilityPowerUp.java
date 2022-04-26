package powerups;

import java.util.ArrayList;

import core.Collider;
import players.ShootingPlayer;
import processing.core.PApplet;

public class InvincibilityPowerUp extends PowerUp {
	
	public static final double INVINCIBILITY_PERIOD = 5;

	public InvincibilityPowerUp(PApplet drawer, String[] images, double health, double x, double y, double width, double height) {
		super(drawer, images, x, y, width, height, INVINCIBILITY_PERIOD);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void reset(ArrayList<Collider> colliders) {
		// TODO Auto-generated method stub
		player.setDamagable(true);
	}


	@Override
	public void powerup(ArrayList<Collider> colliders) {
		// TODO Auto-generated method stub
		player.setDamagable(false);
	}

}
