package powerups;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import core.Collider;
import players.Player;
import players.ShootingPlayer;
import processing.core.PApplet;

public abstract class PowerUp extends Collider {
	
	protected double timeLimit;
	protected PApplet drawer;
	protected String[] animation;
	protected ShootingPlayer player;
	public PowerUp(PApplet drawer, String[] images, double x, double y, double width, double height, double timeLimit) {
		super(images, 10, x, y, width, height, 0, 0);
		this.timeLimit = timeLimit;
		this.drawer = drawer;
		// TODO Auto-generated constructor stub
	}
	
	class Reset extends TimerTask {
		
		ArrayList<Collider> colliders;
		
		public Reset(ArrayList<Collider> colliders) {
			super();
			this.colliders = colliders;
		}

		@Override
		public void run() {
			reset(colliders);
		}
		
	}
	public boolean checkCollides(ShootingPlayer player) {
		if(this.intersects(player)) {
			return true;
		}
		return false;
	}
	public void start(ArrayList<Collider> colliders,ShootingPlayer player) {
		powerup(colliders);
		this.player.setImages(animation);
		this.player = player;
		Timer timer = new Timer();
		timer.schedule(new Reset(colliders), (long) timeLimit);
	}

	public abstract void reset(ArrayList<Collider> colliders);
	
	public abstract void powerup(ArrayList<Collider> colliders);
	
	
}
