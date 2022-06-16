package powerups;
import java.util.ArrayList;

import core.Collider;
import obstacles.Lava;
import players.ShootingPlayer;
import processing.core.PApplet;
public class DamagePowerUp extends PowerUp {
	
	public static final double DAMAGE_PERIOD = 10000;
	public static final String[] powerupImages = new String[] {"assets/Powerups/Damage.png"};
	public static final String[] playerAnimation = null;

	public DamagePowerUp(ArrayList<Collider> mobilePieces, ArrayList<Collider> bullets, double x, double y, double width, double height) {
		super(powerupImages, null, mobilePieces, bullets, x, y, width, height, DAMAGE_PERIOD);
	}
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		player.damage = 1;
	}
	@Override
	public void intermediate() {
		
	}

	public void powerup() {
		// TODO Auto-generated method stub
		player.damage = 10; 
	}
	
	
	
}
