import java.util.Arrays;

public class Bullet extends Collider
{
	String owner;
	public Bullet(double x, double y, String owner) {
		super(new String[] {"assets/bullet.png"}, 1, x, y, 2.0, 2.0, 0, 0);
		this.owner = owner;
	}
	
	public String getOwner() {
		return owner;
	}
	
	

}
