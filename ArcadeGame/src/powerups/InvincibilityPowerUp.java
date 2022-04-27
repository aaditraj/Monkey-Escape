package powerups;

import java.util.ArrayList;

import core.Collider;
import obstacles.Lava;
import players.ShootingPlayer;
import processing.core.PApplet;

public class InvincibilityPowerUp extends PowerUp {
	
	public static final double INVINCIBILITY_PERIOD = 5;
	private static String powerupImage;
	public static final String[] playerAnimation = null;

	public InvincibilityPowerUp(ArrayList<Collider> mobilePieces, ArrayList<Collider> bullets, double x, double y, double width, double height) {
		super(new String[] {powerupImage}, null, mobilePieces, bullets, x, y, width, height, INVINCIBILITY_PERIOD);
	}



	@Override
	public void drawPowerupEffects(PApplet marker) {
		// TODO Auto-generated method stub
		
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
