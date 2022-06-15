package powerups;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import core.Collider;
import obstacles.Lava;
import players.ShootingPlayer;
import processing.core.PApplet;

public class SpeedBoostPowerUp extends PowerUp{
	
	public static final double SPEED_BOOST_PERIOD = 10000;
	private static String[] powerupImages = new String[] {"assets/Powerups/Speed1.png","assets/Powerups/Speed2.png","assets/Powerups/Speed3.png"};
	private static String[] effectImages = new String[] {"assets/Speed/Speed1.png","assets/Speed/Speed2.png"};
	private static String[] leftImages = new String[] {"assets/Speed/SpeedLeft1.png","assets/Speed/SpeedLeft2.png"};
	private static String[] rightImages = new String[] {"assets/Speed/SpeedRight1.png","assets/Speed/SpeedRight2.png"};
	public static final String[] playerAnimation = null;

	public SpeedBoostPowerUp(ArrayList<Collider> mobilePieces, ArrayList<Collider> bullets, double x, double y, double width, double height) {
		super(powerupImages, null, mobilePieces, bullets, x, y, width, height, SPEED_BOOST_PERIOD);
	}


	public void drawPowerupEffects(PApplet drawer, Point2D.Double playerLoc) {
		drawer.push();
		drawer.fill(139, 0, 139, 100);
		drawer.rect(0, 0, drawer.width, drawer.height);
		drawer.pop();
	}


	@Override
	public void reset() {
		// TODO Auto-generated method stub
		player.playerSpeed = (int)(player.playerSpeed * 1/2);	
		player.setLeft(ShootingPlayer.defLeftImgs);
		player.setRight(ShootingPlayer.defRightImgs);
		player.setCurrent(ShootingPlayer.defCurrentImgs);
	}

	public void powerup() {
		// TODO Auto-generated method stub
		player.playerSpeed = (int)(player.playerSpeed * 2); 
		player.setLeft(leftImages);
		player.setRight(rightImages);
		player.setCurrent(effectImages);
	}

}
