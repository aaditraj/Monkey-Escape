package powerups;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import core.Collider;
import players.ShootingPlayer;

public abstract class PowerUp extends Collider {
	
	protected double timeLimit;
	
	public PowerUp(String[] images, double health, double x, double y, double width, double height, double vx,
			double vy, double timeLimit) {
		super(images, health, x, y, width, height, vx, vy);
		this.timeLimit = timeLimit;
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

	public void start(ArrayList<Collider> colliders) {
		powerup(colliders);
		Timer timer = new Timer();
		timer.schedule(new Reset(colliders), (long) timeLimit);
	}

	public abstract void reset(ArrayList<Collider> colliders);
	
	public abstract void powerup(ArrayList<Collider> colliders);
	
	
}
