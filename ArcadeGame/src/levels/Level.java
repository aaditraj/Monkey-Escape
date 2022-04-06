package levels;

import java.util.ArrayList;

import core.Collider;
import enemies.MobileEnemy;
import players.ShootingPlayer;
import powerups.Coin;
import processing.core.PApplet;
import processing.sound.SoundFile;

public class Level {
	ShootingPlayer player;
	ArrayList<Collider> staticPieces = new ArrayList<>();
	ArrayList<Collider> mobilePieces = new ArrayList<>();
	ArrayList<Collider> bullets = new ArrayList<>();
	ArrayList<Collider> totalPieces = new ArrayList<>();
	ArrayList<Collider> objects = new ArrayList<>();
	ArrayList<Collider> coins = new ArrayList<>();
	SoundFile coinCollectSound;
	SoundFile damageSound;
	SoundFile gameOverSound;
	String[] celebrations = new String[] {"Great job!", "Amazing!", "Awesome!", "You're a pro!", "Keep it up!"};
	String currentCelebration;
	ArrayList<Collider> defeatedObjects = new ArrayList<Collider>();
	int messageTime = -1;

	int hitTime = -1;
	
	int mobileEnemyHitTime = 0;

	String owner = ""; 

	int time = 0;
	boolean isFinished = false;
	boolean[] keysPressed = new boolean[4];
	int playerSpeed = 10;
	public void draw(PApplet marker) {
		
	}
	
	public void setupSoundEffects(PApplet marker) {
		coinCollectSound = new SoundFile(marker, "assets/SoundEffects/coin-collect.wav");
		damageSound = new SoundFile(marker, "assets/SoundEffects/damage.wav");
		gameOverSound = new SoundFile(marker, "assets/SoundEffects/game-over.wav");
	}
	public void move() {
		
	}
	
	public void displayCelebrations(PApplet marker) {
		if (currentCelebration != null && defeatedObjects.size() > 0 && messageTime >= 0) {
			marker.push();
			marker.textFont(marker.createFont("assets/ARCADE_N.TTF", 64));
			marker.textAlign(PApplet.CENTER, PApplet.CENTER);
			marker.textSize(60);
			marker.fill(135,206,235);
			marker.text(currentCelebration, 0, 0, marker.width, marker.height);
			marker.textSize(15);
			marker.fill(255, 255, 0);
			for (Collider c : defeatedObjects) {
				if(c instanceof MobileEnemy) {
					marker.text("+50", (float) c.getX(), (float) c.getY());
				} else {
					marker.text("+15", (float) c.getX(), (float) c.getY());
				}
			}
			marker.pop();
			messageTime++;
		} 
		if (messageTime >= 30) {
			messageTime = -1;
			defeatedObjects.clear();
		}
	}
	
	public void displayHit(PApplet marker, float f, float g)
	{
		if(hitTime <= 8 && hitTime >= 0)
		{
			marker.push();
			marker.textFont(marker.createFont("assets/ARCADE_N.TTF", 16));
			marker.textAlign(PApplet.CENTER, PApplet.CENTER);
			marker.fill(225, 0 ,0 );
			if(owner == "ShootingEnemy") {
				marker.translate(f + 5, g + 5);
				marker.rotate(PApplet.radians(30));
				marker.text("-50", 0, 0);
			}
				
			if(owner == "SideShooter") {
				marker.translate(f - 65, g - 15);
				marker.rotate(PApplet.radians(30));
				marker.text("-10", 0, 0);
			}
			
			marker.pop(); 
			hitTime++;
		}
	
	if (hitTime >= 8) {
		hitTime = -1;
	}
	}
	
	public void displayDamage(PApplet marker, float f, float g)
	{
		marker.push();
		marker.textFont(marker.createFont("assets/ARCADE_N.TTF", 16));
		marker.textAlign(PApplet.CENTER, PApplet.CENTER);
		marker.fill(225, 0 ,0 );
		marker.translate(f - 55, g - 55);
		marker.rotate(PApplet.radians(30));
		marker.text("-" + mobileEnemyHitTime + "", 0, 0);
		marker.pop(); 
		
		mobileEnemyHitTime += 1;
	}
	
	
	public void collectCoin(int i) {
		if (!coinCollectSound.isPlaying()) {
			coinCollectSound.play();
		}
		messageTime = 0;
		Coin collected = (Coin) coins.get(i);
		defeatedObjects.add(collected);
		currentCelebration = celebrations[(int) (Math.random() * celebrations.length)];
		coins.get(i).collide(getPlayer());
		getPlayer().changePoints(15);
		coins.remove(i);
	}
	
	public void getHit(String owner) {
		hitTime = 0;
		this.owner = owner; 

		
	}
	
	public void defeatMobileEnemy(int i) {
		messageTime = 0;
		MobileEnemy defeated = (MobileEnemy) mobilePieces.get(i);
		defeatedObjects.add(defeated);
		currentCelebration = celebrations[(int) (Math.random() * celebrations.length)];
		getPlayer().changePoints(50);
		mobilePieces.remove(i);
	}
	public ShootingPlayer getPlayer() {
		return player;
	}
	public boolean isFinished() {
		return isFinished;
	}
	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
	public ArrayList<Collider> getBullets() {
		return bullets;
	}
	public boolean[] getKeysPressed() {
		return keysPressed;
	}
	public ArrayList<Collider> getObjects() {
		return objects;
	}
	public int getPlayerSpeed() {
		return playerSpeed;
	}

	public void playGameOverSound() {
		if (!gameOverSound.isPlaying()) {
			gameOverSound.play();
		}
		
	}
}
