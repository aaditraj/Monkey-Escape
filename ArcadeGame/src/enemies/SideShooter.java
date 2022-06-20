package enemies;
import core.Bullet;
import core.Collider;
public class SideShooter extends Collider {
	//int bulletFrequency = 0;
	int direction = 0;
	int bulletSpeed;
	public int bulletFrequency;
	/**
	 * 
	 * @param x The sideshooters x-coordinate
	 * @param y The sideshooters y-coordinate
	 * @param bulletFrequency The frequency with with to shoot bananas
	 * @param width The sideshooters width
	 * @param height The sideshooters height
	 * @param direction The direction of the sideshooter(Shooting left, or right)
	 */
	public SideShooter(int x, int y,int bulletFrequency, int width, int height, int direction) {
		super(new String[] {"assets/SideShooter/SideShooter.png","assets/SideShooter/SideShooterLeft.png"}, Integer.MAX_VALUE,x,y,width,height, 0,0);
		if(direction == 1) {
			this.setImages(new String[] {"assets/SideShooter/SideShooterLeft.png","assets/SideShooter/SideShooterLeft2.png"});
		} else {
			this.setImages(new String[] {"assets/SideShooter/SideShooter.png","assets/SideShooter/SideShooter2.png"});
		}
		if(direction == 1) {
			goToImage(1); 
		}
		this.direction = direction;
		this.bulletFrequency = bulletFrequency;
		this.bulletSpeed = 10;
	}
	/**
	 * Shoot one banana
	 * @return the bullet shot
	 */
	public Bullet shoot() {
		Bullet mc = new Bullet(getCenterX(),getCenterY(),"SideShooter",new String[] {Bullet.bananaLocation}, 193/8d, 161/8d, 1);
		mc.setVelocity(direction * bulletSpeed, 0);
		return mc;
	}
	
}
