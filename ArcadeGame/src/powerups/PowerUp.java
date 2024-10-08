package powerups;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import core.Collider;
import players.ShootingPlayer;
import processing.core.PApplet;

public abstract class PowerUp extends Collider {
	
	protected double timeLimit;
	protected String[] animation;
	protected ShootingPlayer player;
	protected ArrayList<Collider> mobilePieces;
	protected ArrayList<Collider> bullets;
	protected Timer timer;
	public boolean collected, active;

	public PowerUp(String[] powerupImage, String[] playerAnimation, ArrayList<Collider> mobilePieces, ArrayList<Collider> bullets, double x, double y, double width, double height, double timeLimit) {
		super(powerupImage, 10, x, y, width, height, 0, 0);
		timer = new Timer();
		this.timeLimit = timeLimit;
		this.mobilePieces = mobilePieces;
		this.bullets = bullets;
		this.player = (ShootingPlayer) mobilePieces.get(0);
		this.animation = playerAnimation;
	}
	// Shield Link: https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.artstation.com%2Fartwork%2FKrmJmx&psig=AOvVaw1w2sYcLDhRwKi_r2bVmBTt&ust=1651109791140000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCJie476Ns_cCFQAAAAAdAAAAABAJ
	// Fist link: https://www.google.com/url?sa=i&url=https%3A%2F%2Fpixeldungeon.fandom.com%2Fwiki%2FBurning_fist&psig=AOvVaw2ixfve52P2cyb5NDZM9YlL&ust=1651109931940000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCIjRv4-Os_cCFQAAAAAdAAAAABAD
	class Reset extends TimerTask {
		
		Timer timer;
		public Reset(Timer timer) {
			super();
			this.timer = timer;
		}

		@Override
		public void run() {
			reset();
			active = false;
			timer.cancel();
		}
		
	}
	
	class Intermediate extends TimerTask {
		
		public Intermediate() {
			super();
		}

		@Override
		public void run() {
			intermediate();
		}
		
	}
	

	public void start() {
		if (animation != null) {
			player.setImages(animation);
		}
		
		collected = true;
		active = true;
		powerup();
		
		timer.schedule(new Intermediate(), 0, 1);
		timer.schedule(new Reset(timer), (long)timeLimit);
	}

	public void drawPowerupEffects(PApplet marker, Point2D.Double playerLoc) {
		
	}

	public abstract void reset();
	public void intermediate() {
		
	}
	public abstract void powerup();
	
	
}
