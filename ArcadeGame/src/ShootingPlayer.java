
public class ShootingPlayer extends Player{
	
	public ShootingPlayer(double health, double x, double y, double width, double height, double vx,
			double vy, double reloadTime) {
		super(new String[] {"assets/ShootingEnemy/monkey.png"}, health, x, y, width, height, vx, vy,reloadTime);
	}
	
	public Collider shoot(int mouseX, int mouseY)
	{
		Collider mc = new Collider(new String[] {"assets/ShootingEnemy/coconut.png"}, 1, this.getX(), this.getY(), 5.0, 5.0, 0.0, 0.0);
		mc.setVelocity(mouseX - this.getX(), mouseY - this.getY());
		return mc;
	}
}


