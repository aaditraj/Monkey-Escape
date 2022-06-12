package powerups;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import core.Collider;
import processing.core.PApplet;

public class InvincibilityPowerUp extends PowerUp {
	
	public static final double INVINCIBILITY_PERIOD = 6000;
	public static String[] powerupImages = new String[] {"assets/Powerups/shield.png"};
	public static final String[] playerAnimation = null;
	private double shieldRad;
	
	public InvincibilityPowerUp(ArrayList<Collider> mobilePieces, ArrayList<Collider> bullets, double x, double y, double width, double height) {
		super(powerupImages, null, mobilePieces, bullets, x, y, width, height, INVINCIBILITY_PERIOD);
		shieldRad = 150;
	}



	@Override
	public void drawPowerupEffects(PApplet drawer, Point2D.Double playerLoc) {
		drawer.push();
		drawer.stroke(3, 227, 252, 0);
		drawer.fill(3, 227, 252, 75);
		drawer.circle((float) playerLoc.getX(), (float) playerLoc.getY(), (float) shieldRad);
		drawer.pop();
		shieldRad--;
	}
	
	@Override
	public void intermediate() {
		
	}
	


	@Override
	public void reset() {
		// TODO Auto-generated method stub
		player.setDamagable(true);
	}


	@Override
	public void powerup() {
		// TODO Auto-generated method stub
		player.setDamagable(false);
	}

}
