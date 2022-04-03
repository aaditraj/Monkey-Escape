package enemies;
import java.util.ArrayList;

import core.Collider;
import players.Player;
import processing.core.PApplet;

public class MobileEnemy extends Collider {
	
	private double endX, endY;
	private float proportion; 
	private float initHealth;
	private boolean isHittingPlayer;
	private int numHits;
	public static final String[] mobileEnemyImages = new String[] {"assets/MobileEnemy/left.png",
			"assets/MobileEnemy/right.png", "assets/MobileEnemy/mad-left-1.png", 
			"assets/MobileEnemy/mad-right-1.png"};
	
	// direction = 1 --> moving lateral, direction=0 --> moving vertical
	public MobileEnemy(String[] images, double health, double startX, double startY,  double endX, double endY, double vx, double vy, double width, double height) {
		super(images, health, startX, startY, width, height, vx, vy);
		this.endX = endX;
		this.endY = endY;
		this.setAnimationFalse();
		this.setMobile(true);
		proportion = (float)getWidth()/(float)getHealth(); 
		initHealth = (float)getHealth();
		if (startX < endX) {
			goToImage(1);
		} else {
			goToImage(0);
		}
	}
	
	// call this method before draw
	public void act(ArrayList<Collider> colliders) {
		super.moveBy(vx, vy, colliders);
		double largerX = Math.max(getInitX(), endX);
		double smallerX = Math.min(getInitX(), endX);
		double largerY = Math.max(getInitY(), endY);
		double smallerY = Math.min(getInitY(), endY);
		if (getX() >= largerX || getX() <= smallerX) {
			vx *= -1;
			if (getCurrentImage() == 0) {
				goToImage(1);
			}
			else if (getCurrentImage() == 1) {
				goToImage(0);
			} else if (getCurrentImage() == 2) {
				goToImage(3);
			} else if (getCurrentImage() == 3) {
				goToImage(2);
			}
		}
		if (getY() >= largerY || getY() <= smallerY) {
			vy *= -1;
		}
		
//		if(isHittingPlayer = true)
//		{
//			System.out.println("HI");
//		}
	}
	public void draw(PApplet marker) {
		super.draw(marker);
		
		marker.push();
		marker.rect((float)getX(), (float)getY() - (float)getHeight()/2, (float)getWidth(), 10);
		marker.fill(marker.color(0,255,0));
		marker.rect((float)getX(), (float)getY() - (float)getHeight()/2, (float)(getWidth() - proportion*Math.abs(initHealth-getHealth())), 10);
		marker.pop();
	}
	
	public double collide(Collider collider) {
		if (collider instanceof Player) {
			collider.changeHealth(-1);
			if (getCurrentImage() == 0) {
				goToImage(2);
			} else if(getCurrentImage() == 1) {
				goToImage(3);
			}
			isHittingPlayer = true;
		}
		return 0.0;
	}
	
	
	
}
