package players;
import java.util.ArrayList;

import core.Bullet;
import core.Collider;

public class ShootingPlayer extends Player{
	int jumpHeight;
	double defaultVx;
	double defaultVy;
	public int points, damage;
	public boolean damageUp; 
	public int playerSpeed;
	public static final String[] defCurrentImgs = new String[] {"assets/Player/Player.png"};
	public static final String[] defLeftImgs = new String[] {"assets/Player/PlayerLeft.png","assets/Player/PlayerLeft2.png"};
	public static final String[] defRightImgs = new String[] {"assets/Player/PlayerRight.png","assets/Player/PlayerRight2.png"};
	public ShootingPlayer(double health, double x, double y, double width, double height, double vx,
			double vy, int maxAmmo) {
		super(defCurrentImgs,defLeftImgs,defRightImgs, health, x, y, width, height, vx, vy, maxAmmo);
		setFrequency(3);
		defaultVx = vx;
		defaultVy = vy;
		jumpHeight = 50;
		damage = 1;
		playerSpeed = 10;
	}
	public ShootingPlayer(double health, double x, double y, double width, double height, double vx,
			double vy, int maxAmmo,int jumpHeight) {
		super(defCurrentImgs,defLeftImgs,defRightImgs, health, x, y, width, height, vx, vy, maxAmmo);
		defaultVx = vx;
		defaultVy = vy;
		setFrequency(3);
		this.jumpHeight = jumpHeight;
		damageUp = false;
		damage = 1;
		playerSpeed = 10;

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
		
		if(!damageUp)
		{
			
			if (mouseY < getY()) {
				b = new Bullet(x, this.getY(), "player",Bullet.bulletLocation,damage);

			} else if (mouseY >= getY() && mouseY <= getY() + getHeight()) {
				b = new Bullet(x, mouseY, "player",Bullet.bulletLocation,damage);

			} else {
				b = new Bullet(x, this.getY() + this.getHeight(), "player",Bullet.bulletLocation, damage);
			}
		}
		else {
//			System.out.println("Hi");
			if (mouseY < getY()) {
				b = new Bullet(x, this.getY(), "player",Bullet.bulletLocation2,damage);

			} else if (mouseY >= getY() && mouseY <= getY() + getHeight()) {
				b = new Bullet(x, mouseY, "player",Bullet.bulletLocation2,damage);

			} else {
				b = new Bullet(x, this.getY() + this.getHeight(), "player",Bullet.bulletLocation2, damage);
			}
		}
	
		return b;
	}
	
	
	


	

//	public void act(List<Sprite> obstacles) {
//		
//		yVelocity += 0.3;
//		xVelocity *= 0.9;
//
//		
//		y += yVelocity; 
//		
//		
//		boolean touchingY = false; 
//		double spriteY = 0; 
//		
//		for(Sprite o : obstacles)
//		{
//			if(super.intersects(o.getBounds2D()))
//			{
//				touchingY = true; 
//				spriteY = o.y;
//			}
//		}
//	
//		if(touchingY)
//		{
//			yVelocity = 0;
//			y = spriteY-super.height;
//		} 
//		
//		x += xVelocity;
//		
//		for(Sprite o : obstacles)
//		{
//			if(super.intersects(o.getBounds2D()))
//			{
//				x -= xVelocity;
//				xVelocity = 0;
//			}
//		}
//		
//	}
	
	public void jump(ArrayList<Collider> colliders) {
		if(this.intersects(colliders)[2]) {
			vy = -14;
		}
	}
	public void act(ArrayList<Collider> colliders) {
		moveBy(vx,vy,colliders);

		vy += 0.9;
		
		if (vy < defaultVy) {
			y += vy; 
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


