package powerups;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import core.Collider;
import processing.core.PApplet;
import processing.core.PGraphics;

public class InvincibilityPowerUp extends PowerUp {
	
	public static final double INVINCIBILITY_PERIOD = 6000;
	public static String[] powerupImages = new String[] {"assets/Powerups/Shield1.png","assets/Powerups/Shield2.png","assets/Powerups/Shield3.png"};
	public static final String[] playerAnimation = null;
	private double shieldRad;
	private PGraphics pGraphics;
	public InvincibilityPowerUp(ArrayList<Collider> mobilePieces, ArrayList<Collider> bullets, double x, double y, double width, double height) {
		super(powerupImages, null, mobilePieces, bullets, x, y, width, height, INVINCIBILITY_PERIOD);
		shieldRad = 150;
	}

	

	@Override
	public void drawPowerupEffects(PApplet drawer, Point2D.Double playerLoc) {
		drawer.push();
		if(pGraphics == null) {
			pGraphics = drawer.createGraphics((int)shieldRad/4,(int)shieldRad/4);
		}
		drawer.fill(140, 200, 210, 35);
		pGraphics.beginDraw();
		pGraphics.circle((float) shieldRad/8, (float) shieldRad/8,(float) shieldRad/16);
		pGraphics.endDraw();
		pGraphics.noSmooth();
		pGraphics.fill(3, 227, 252, 75);
		drawer.image(pGraphics,(float) (playerLoc.getX()-shieldRad/2),(float) (playerLoc.getY() - shieldRad/2),(float)shieldRad,(float)shieldRad);
		drawer.rect(0, 0, drawer.width, drawer.height);
		
		
		//drawer.circle((float) playerLoc.getX(), (float) playerLoc.getY(), (float) shieldRad);
		drawer.pop();
		shieldRad--;
	}
	
	@Override
	public void intermediate() {
		
	}
	


	@Override
	public void reset() {
		// TODO Auto-generated method stub
		player.setDamagable(true);
	}


	@Override
	public void powerup() {
		// TODO Auto-generated method stub
		player.setDamagable(false);
	}

}
