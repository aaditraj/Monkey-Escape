package powerups;
import java.util.ArrayList;

import core.Collider;
import players.ShootingPlayer;

public class SpeedBoostPowerUp extends PowerUp{

	public SpeedBoostPowerUp(String[] images, double health, double x, double y, double width, double height, double vx,
			double vy) {
		super(images, health, x, y, width, height, vx, vy);
		// TODO Auto-generated constructor stub
	}

	@Override
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
