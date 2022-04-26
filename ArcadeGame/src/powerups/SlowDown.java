package powerups;
import java.util.ArrayList;

import core.Collider;
import players.ShootingPlayer;
import processing.core.PApplet;

public class SlowDown extends PowerUp {
	
	
	public static final double SLOW_DOWN_PERIOD = 5;
	public static final String[] images = new String[] {};

	public SlowDown(PApplet drawer, double x, double y, double width, double height) {
		super(drawer, images, x, y, width, height, SLOW_DOWN_PERIOD);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void reset(ArrayList<Collider> colliders) {
		// TODO Auto-generated method stub
		for (Collider c : colliders) {
			if (c != null) {
				c.scaleVelocities(2);
			}
		}
		
	}

	@Override
	public void powerup(ArrayList<Collider> colliders) {
		// colliders includes mobile enemies, enemy bullets, and lava
		// TODO Auto-generated method stub
		for (Collider c : colliders) {
			if (c != null) {
				c.scaleVelocities(0.5);
			}
		}
	}

}
