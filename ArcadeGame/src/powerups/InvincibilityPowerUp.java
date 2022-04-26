package powerups;

import java.util.ArrayList;

import core.Collider;
import players.ShootingPlayer;
import processing.core.PApplet;

public class InvincibilityPowerUp extends PowerUp {
	
	public static final double INVINCIBILITY_PERIOD = 5;
	public static final String[] images = new String[] {};

	public InvincibilityPowerUp(PApplet drawer, double x, double y, double width, double height) {
		super(drawer, images, x, y, width, height, INVINCIBILITY_PERIOD);
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
