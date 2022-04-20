package powerups;

import java.util.ArrayList;

import core.Collider;
import players.ShootingPlayer;
import processing.core.PApplet;

public class InvincibilityPowerUp extends PowerUp {
	
	public static final double INVINCIBILITY_PERIOD = 5;

	public InvincibilityPowerUp(PApplet drawer, String[] images, double health, double x, double y, double width, double height,
			double vx, double vy) {
		super(drawer, images, health, x, y, width, height, vx, vy, INVINCIBILITY_PERIOD);
		// TODO Auto-generated constructor stub
	}


	public void checkCollision(ArrayList<Collider> colliders) {
		// TODO Auto-generated method stub
		ShootingPlayer player = (ShootingPlayer) colliders.get(0);
	}


	@Override
	public void reset(ArrayList<Collider> colliders) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void powerup(ArrayList<Collider> colliders) {
		// TODO Auto-generated method stub
		
	}

}
