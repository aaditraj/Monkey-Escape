package players;
import java.util.ArrayList;

import core.Bullet;
import core.Collider;

public class ShootingPlayer extends Player{
	int jumpHeight;
	double defaultVx;
	double defaultVy;
	public int points;
	public ShootingPlayer(double health, double x, double y, double width, double height, double vx,
			double vy, int maxAmmo) {
		super(new String[] {"assets/Player/Player.png"}, health, x, y, width, height, vx, vy, maxAmmo);
		setFrequency(3);
		defaultVx = vx;
		defaultVy = vy;
		jumpHeight = 50;
	}
	public ShootingPlayer(double health, double x, double y, double width, double height, double vx,
			double vy, int maxAmmo,int jumpHeight) {
		super(new String[] {"assets/Player/Player.png"}, health, x, y, width, height, vx, vy, maxAmmo);
		defaultVx = vx;
		defaultVy = vy;
		setFrequency(3);
		this.jumpHeight = jumpHeight;
	}
	public Bullet shoot(int mouseX, int mouseY)
	{
		Bullet b;
		if (mouseX > getX() + getWidth()) {
			b = constructBullet(this.getX() + this.getWidth(), mouseY);
		} else if (mouseX >= getX() && mouseX <= getX() + getWidth()) {
			b = constructBullet(mouseX, mouseY);
		} else {
			b = constructBullet(this.getX(), mouseY);
		}
		b.setVelocity(mouseX - b.getCenterX(), mouseY - b.getCenterY());
		decreaseAmmo();
		return b;
		
	}
	
	
	private Bullet constructBullet(double x, double mouseY) {
		Bullet b;
		if (mouseY < getY()) {
			b = new Bullet(x, this.getY(), "player",Bullet.bulletLocation);

		} else if (mouseY >= getY() && mouseY <= getY() + getHeight()) {
			b = new Bullet(x, mouseY, "player",Bullet.bulletLocation);

		} else {
			b = new Bullet(x, this.getY() + this.getHeight(), "player",Bullet.bulletLocation);
		}
		return b;
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
				vy *= 0.6;
				if (vy > -0.5) {
					vy *= -1;
				}
			} else {
				vy *= 4;
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


