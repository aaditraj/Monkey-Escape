import java.util.Arrays;

public class Bullet extends Collider
{
	public Bullet(double x, double y) {
		super(new String[] {"assets/bullet.png"}, 1, x, y, 2.0, 2.0, 0, 0);
	}

}
