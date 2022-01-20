
public class ShootingPlayer extends Player{
	
	public ShootingPlayer(double health, double x, double y, double width, double height, double vx,
			double vy, double reloadTime) {
		super(new String[] {"assets/ShootingEnemy/monkey.png"}, health, x, y, width, height, vx, vy,reloadTime);
	}
	
	public Bullet shoot(int mouseX, int mouseY)
	{
		Bullet mc = new Bullet(this.getCenterX(), this.getCenterY());
		mc.setVelocity(mouseX - this.getCenterX(), mouseY - this.getCenterY());
		return mc;
	}

}


