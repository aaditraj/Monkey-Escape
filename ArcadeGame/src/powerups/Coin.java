package powerups;
import java.util.ArrayList;

import core.Collider;
import players.Player;
import players.ShootingPlayer;

//change to extends powerup later
public class Coin extends Collider {

	public Coin(double x, double y) {
		super(new String[] {"assets/Powerups/Coin.png"}, 1d, x, y, 50d, 50d, 0d, 0d);
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
