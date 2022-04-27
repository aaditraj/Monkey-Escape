package powerups;
import java.util.ArrayList;

import core.Collider;
import obstacles.Lava;
import players.ShootingPlayer;
import processing.core.PApplet;
public class DamagePowerUp extends PowerUp {
	
	public static final double DAMAGE_PERIOD = 5;
	public static final String powerupImage ="assets/Powerups/hourglass.png";
	public static final String[] playerAnimation = null;

	public DamagePowerUp(ArrayList<Collider> mobilePieces, ArrayList<Collider> bullets, double x, double y, double width, double height) {
		super(new String[] {powerupImage}, null, mobilePieces, bullets, x, y, width, height, DAMAGE_PERIOD);
	}

	public void checkCollision(ArrayList<Collider> colliders) {
		ShootingPlayer player = (ShootingPlayer) colliders.get(0);
		if(this.intersects(player))
		{
			player.damage = 2; 
		}
	}




	@Override
	public void drawPowerupEffects(PApplet drawer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}


	public void powerup() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
