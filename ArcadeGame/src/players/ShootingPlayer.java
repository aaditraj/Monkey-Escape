package players;
import java.util.ArrayList;

import core.Bullet;
import core.Collider;

public class ShootingPlayer extends Player{
	int jumpHeight;
	double defaultVx;
	double defaultVy;
	int points;
	public ShootingPlayer(double health, double x, double y, double width, double height, double vx,
			double vy, int maxAmmo) {
		super(new String[] {"assets/ShootingEnemy/Monkey.png"}, health, x, y, width, height, vx, vy, maxAmmo);
		defaultVx = vx;
		defaultVy = vy;
		jumpHeight = 15;
	}
	public ShootingPlayer(double health, double x, double y, double width, double height, double vx,
			double vy, int maxAmmo,int jumpHeight) {
		super(new String[] {"assets/ShootingEnemy/Monkey.png"}, health, x, y, width, height, vx, vy, maxAmmo);
		defaultVx = vx;
		defaultVy = vy;
		this.jumpHeight = jumpHeight;
	}
	public Bullet shoot(int mouseX, int mouseY)
	{
		decreaseAmmo();
		Bullet mc = new Bullet(this.getCenterX(), this.getCenterY(), "player",Bullet.bulletLocation);
		mc.setVelocity(mouseX - this.getCenterX(), mouseY - this.getCenterY());
		return mc;
		
	}
	
	
	
	public void jump(ArrayList<Collider> colliders) {
		if(this.intersects(colliders)[2]) {
			vy = -jumpHeight;
		}
	}
	public void act(ArrayList<Collider> colliders) {
		moveBy(vx,vy,colliders);
		if (vy < defaultVy) {
			if(vy <= 0) {
				vy *= 0.5;
				if (vy > -0.1) {
					vy *= -1;
				}
			} else {
				vy *= 5;
			}
			
		} else {
			vy = defaultVy;
		}
	}
	public void changePoints(int amount) {
		points+=amount;
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


