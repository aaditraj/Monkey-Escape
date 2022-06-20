package powerups;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import core.Bullet;
import core.Collider;
import players.ShootingPlayer;
import processing.core.PApplet;

public class SlowDownPowerUp extends PowerUp {
	
	/**<a href="https://www.flaticon.com/free-icons/time" title="time icons">Time icons created by Freepik - Flaticon</a>**/
	
	public static final double SLOW_DOWN_PERIOD = 10000;
	public static final String[] powerupImages = new String[] {"assets/Powerups/Time1.png","assets/Powerups/Time2.png","assets/Powerups/Time3.png"};
	public static final String[] playerAnimation = null;

	public SlowDownPowerUp(ArrayList<Collider> mobilePieces, ArrayList<Collider> bullets, double x, double y, double width, double height) {
		super(powerupImages, null, mobilePieces, bullets, x, y, width, height, SLOW_DOWN_PERIOD);
	}



	@Override
	public void drawPowerupEffects(PApplet drawer, Point2D.Double playerLoc) {
		drawer.push();
		drawer.fill(139, 0, 139, 100);
		drawer.rect(0, 0, drawer.width, drawer.height);
		drawer.pop();
	}


	@Override
	// TODO player is always first mobilePiece, ignore the rest
	public void powerup() {
		for (Collider c : mobilePieces) {
			if (c != null && !(c instanceof ShootingPlayer)) {
				c.scaleVelocities(0.25);
			}
		}
		
		scaleBulletVelocities(0.25);
	}
	public void scaleBulletVelocities(double num) {
		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i) != null) {
				Bullet b = (Bullet) bullets.get(i);
				if (b.getOwner() != "player") {
					System.out.println("HOLA");
					if(!b.getScaled()) {
						b.scaleVelocities(num);
					}
				}
			}
		}
	}
	public void descaleBulletVelocities(double num) {
		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i) != null) {
				Bullet b = (Bullet) bullets.get(i);
				if (b.getOwner() != "player") {
					System.out.println("HOLA");
					if(b.getScaled()) {
						b.descaleVelocities(num);
					}
				}
			}
		}
	}
	@Override
	public void intermediate() {
		scaleBulletVelocities(0.25);
	}
	@Override
	public void reset() {
		for (Collider c : mobilePieces) {
			if (c != null && !(c instanceof ShootingPlayer)) {
				c.descaleVelocities(4);
			}
		}
		descaleBulletVelocities(4);
		
	}

}
