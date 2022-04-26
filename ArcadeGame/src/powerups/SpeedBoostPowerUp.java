package powerups;
import java.util.ArrayList;

import core.Collider;
import players.ShootingPlayer;
import processing.core.PApplet;

public class SpeedBoostPowerUp extends PowerUp{
	
	public static final double SPEED_BOOST_PERIOD = 5;
	public static final String[] images = new String[] {};

	public SpeedBoostPowerUp(PApplet drawer, double x, double y, double width, double height) {
		super(drawer, images, x, y, width, height, SPEED_BOOST_PERIOD);
		// TODO Auto-generated constructor stub
	}

	public void checkCollision(ArrayList<Collider> colliders) {
		ShootingPlayer player = (ShootingPlayer) colliders.get(0);
		if(this.intersects(player))
		{
			player.playerSpeed = player.playerSpeed * 2; 
		}
		
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
