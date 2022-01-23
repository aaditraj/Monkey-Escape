package players;
import core.Collider;

public class JumpingPlayer extends Player{
	
	public JumpingPlayer(String[] images, double health, double x, double y, double width, double height, double vx,
			double vy, double reloadTime) {
		super(images, health, x, y, width, height, vx, vy, reloadTime);
	}
	
	public Collider shoot(int mouseX, int mouseY)
	{
		Collider mc = new Collider(new String[] {"bulletimage"}, 1, this.getX(), this.getY(), 2.0, 2.0, 0.0, 0.0);
		mc.setVelocity(mouseX - this.getX(), this.getY() - mouseY);
		return mc;
	}
}


