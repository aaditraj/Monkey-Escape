import java.util.ArrayList;

public class ShootingPlayer extends Player{
	int jumpHeight;
	double defaultVx;
	double defaultVy;
	public ShootingPlayer(double health, double x, double y, double width, double height, double vx,
			double vy, double reloadTime) {
		super(new String[] {"assets/ShootingEnemy/monkey.png"}, health, x, y, width, height, vx, vy,reloadTime);
		defaultVx = vx;
		defaultVy = vy;
		jumpHeight = 20;
	}
	
	public Bullet shoot(int mouseX, int mouseY)
	{
		Bullet mc = new Bullet(this.getCenterX(), this.getCenterY(), "player");
		mc.setVelocity(mouseX - this.getCenterX(), mouseY - this.getCenterY());
		return mc;
	}
	public void jump() {
		vy = -20;
	}
	public void act(ArrayList<Collider> colliders) {
		moveBy(vx,vy,colliders);
		if (vy < defaultVy) {
			vy++ ;
		}
	}
	
	public double collide(Collider collider) {
		if (collider instanceof Bullet) {
			Bullet bullet = (Bullet) collider;
			if (bullet.getOwner() != "player") {
				bullet.changeHealth(bullet.getHealth() * -1);
				return 1;
			}
		}
		return 0.0;
	}

}


