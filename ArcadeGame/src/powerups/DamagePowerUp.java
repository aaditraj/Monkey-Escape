package powerups;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import core.Collider;
import processing.core.PApplet;
public class DamagePowerUp extends PowerUp {
	
	public static final double DAMAGE_PERIOD = 7500;
	public static final String[] powerupImages = new String[] {"assets/Powerups/Damage.png"};
	public static final String[] playerAnimation = null;

	public DamagePowerUp(ArrayList<Collider> mobilePieces, ArrayList<Collider> bullets, double x, double y, double width, double height) {
		super(powerupImages, null, mobilePieces, bullets, x, y, width, height, DAMAGE_PERIOD);
	}
	
	
	public void drawPowerupEffects(PApplet drawer, Point2D.Double playerLoc) {
		drawer.push();
		drawer.fill(150, 0, 0, 100);
		drawer.rect(0, 0, drawer.width, drawer.height);
		drawer.pop();
	}
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		player.damage = 1;
		player.damageUp = false;
	}
	@Override
	public void intermediate() {
		
	}

	public void powerup() {
		// TODO Auto-generated method stub
		player.damage = 2; 
		player.damageUp = true;
	}
	
	
	
}
