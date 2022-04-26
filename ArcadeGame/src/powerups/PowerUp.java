package powerups;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import core.Collider;
import processing.core.PApplet;

public abstract class PowerUp extends Collider {
	
	protected double timeLimit;
	protected PApplet drawer;
	
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

	public void start(ArrayList<Collider> colliders) {
		powerup(colliders);
		Timer timer = new Timer();
		timer.schedule(new Reset(colliders), (long) timeLimit);
	}

	public abstract void reset(ArrayList<Collider> colliders);
	
	public abstract void powerup(ArrayList<Collider> colliders);
	
	
}
