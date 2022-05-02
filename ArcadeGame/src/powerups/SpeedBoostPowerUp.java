package powerups;
import java.util.ArrayList;

import core.Collider;
import obstacles.Lava;
import players.ShootingPlayer;
import processing.core.PApplet;

public class SpeedBoostPowerUp extends PowerUp{
	
	public static final double SPEED_BOOST_PERIOD = 10000;
	private static String[] powerupImages = new String[] {"assets/Powerups/Speed1.png","assets/Powerups/Speed2.png","assets/Powerups/Speed3.png"};
	public static final String[] playerAnimation = null;

	public SpeedBoostPowerUp(ArrayList<Collider> mobilePieces, ArrayList<Collider> bullets, double x, double y, double width, double height) {
		super(powerupImages, null, mobilePieces, bullets, x, y, width, height, SPEED_BOOST_PERIOD);
	}





	@Override
	public void reset() {
		// TODO Auto-generated method stub
		player.playerSpeed = (int)(player.playerSpeed * 1/2.0);
	}

	public void powerup() {
		// TODO Auto-generated method stub
		player.playerSpeed = (int)(player.playerSpeed * 3.5/3.0); 
	}

}
