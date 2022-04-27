package powerups;
import java.util.ArrayList;

import core.Collider;
import obstacles.Lava;
import players.ShootingPlayer;
import processing.core.PApplet;

public class SpeedBoostPowerUp extends PowerUp{
	
	public static final double SPEED_BOOST_PERIOD = 5;
	private static String powerupImage;
	public static final String[] playerAnimation = null;

	public SpeedBoostPowerUp(ArrayList<Collider> mobilePieces, ArrayList<Collider> bullets, double x, double y, double width, double height) {
		super(new String[] {powerupImage}, null, mobilePieces, bullets, x, y, width, height, SPEED_BOOST_PERIOD);
	}



	@Override
	public void drawPowerupEffects(PApplet marker) {
		// TODO Auto-generated method stub
		player.playerSpeed = player.playerSpeed * 1/2; 
	}


	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	public void powerup() {
		// TODO Auto-generated method stub
		player.playerSpeed = player.playerSpeed * 2; 
	}

}
