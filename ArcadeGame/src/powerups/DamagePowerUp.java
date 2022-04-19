package powerups;
import core.Collider;
import players.ShootingPlayer;
public class DamagePowerUp extends PowerUp {
	

	public DamagePowerUp(String[] images, double health, double x, double y, double width, double height, double vx,
			double vy) {
		super(images, health, x, y, width, height, vx, vy);
	}

	public void checkCollision(ShootingPlayer player) {
		if(this.intersects(player))
		{
			player.damage = 2; 
		}
	}
	
	
	
}
