package powerups;

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
	public boolean collected, active;
	
	public PowerUp(String[] powerupImage, String[] playerAnimation, ArrayList<Collider> mobilePieces, ArrayList<Collider> bullets, double x, double y, double width, double height, double timeLimit) {
		super(powerupImage, 10, x, y, width, height, 0, 0);
		this.timeLimit = timeLimit;
		this.mobilePieces = mobilePieces;
		this.bullets = bullets;
		this.player = (ShootingPlayer) mobilePieces.get(0);
		this.animation = playerAnimation;
	}
	
	class Reset extends TimerTask {
		
		Timer timer;
		
		public Reset(Timer timer) {
			super();
			this.timer = timer;
		}

		@Override
		public void run() {
			timer.cancel();
			reset();
			active = false;
		}
		
	}
	

	public void start() {
		if (animation != null) {
			player.setImages(animation);
		}
		collected = true;
		Timer timer = new Timer();
		active = true;
		powerup();
		timer.schedule(new Reset(timer), (long) timeLimit);
	}

	public abstract void drawPowerupEffects(PApplet marker);

	public abstract void reset();
	
	public abstract void powerup();
	
	
}
