package powerups;
import java.util.ArrayList;

import core.Collider;
import players.ShootingPlayer;
import processing.core.PApplet;
public class DamagePowerUp extends PowerUp {
	
	public static final double DAMAGE_PERIOD = 5;
	public static final String[] images = new String[] {};

	public DamagePowerUp(PApplet drawer, double x, double y, double width, double height) {
		super(drawer, images, x, y, width, height, DAMAGE_PERIOD);
	}

	public void checkCollision(ArrayList<Collider> colliders) {
		ShootingPlayer player = (ShootingPlayer) colliders.get(0);
		if(this.intersects(player))
		{
			player.damage = 2; 
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
