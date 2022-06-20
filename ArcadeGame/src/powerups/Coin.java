package powerups;
import core.Collider;
import players.ShootingPlayer;

//change to extends powerup later
public class Coin extends Collider {

	public Coin(double x, double y) {
		super(new String[] {"assets/Powerups/Coin1.png","assets/Powerups/Coin2.png","assets/Powerups/Coin3.png","assets/Powerups/Coin2.png"}, 1d, x, y, 50d, 50d, 0d, 0d);
	}
	
	public double collide(Collider collider) {
		if (collider instanceof ShootingPlayer) {
			ShootingPlayer player = (ShootingPlayer) collider;
			//player.changePoints(5);
			return getHealth();
		}
		return 0.0;
	}

}
