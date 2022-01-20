

//change to extends powerup later
public class Coin extends Collider {

	public Coin(double x, double y) {
		super(new String[] {"assets/Powerups/Coin"}, 1d, x, y, 10d, 10d, 0d, 0d);
	}
	
	public double collide(Collider collider) {
		if (collider instanceof Player) {
			Player player = (Player) collider;
			player.pointUp();
			return getHealth();
		}
		return 0.0;
	}

}
