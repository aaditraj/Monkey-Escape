package powerups;

import java.util.ArrayList;

import core.Collider;
import obstacles.Lava;
import players.ShootingPlayer;
import processing.core.PApplet;

public class InvincibilityPowerUp extends PowerUp {
	
	public static final double INVINCIBILITY_PERIOD = 10000;
	private static String[] powerupImages = new String[] {"assets/Powerups/shield.png"};
	public static final String[] playerAnimation = null;

	public InvincibilityPowerUp(ArrayList<Collider> mobilePieces, ArrayList<Collider> bullets, double x, double y, double width, double height) {
		super(powerupImages, null, mobilePieces, bullets, x, y, width, height, INVINCIBILITY_PERIOD);
	}



	@Override
	public void drawPowerupEffects(PApplet marker) {
		// TODO Auto-generated method stub
//		drawer.push();
//		drawer.fill(139, 0, 139, 100);
//		drawer.rect(0, 0, drawer.width, drawer.height);
//		drawer.pop();
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
