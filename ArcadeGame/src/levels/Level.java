package levels;

import java.util.ArrayList;

import core.Collider;
import enemies.MobileEnemy;
import players.ShootingPlayer;
import powerups.Coin;
import powerups.PowerUp;
import processing.core.PApplet;
import processing.sound.SoundFile;

/**
 * The main Level class that is the basis for all levels
 * 
 * @author Julian, Aditya, Adithya
 */
public class Level {
	ShootingPlayer player;
	ShootingPlayer player2;
	ArrayList<Collider> staticPieces = new ArrayList<>();
	ArrayList<Collider> mobilePieces = new ArrayList<>();
	ArrayList<Collider> bullets = new ArrayList<>();
	ArrayList<Collider> totalPieces = new ArrayList<>();
	ArrayList<Collider> objects = new ArrayList<>();
	ArrayList<Collider> coins = new ArrayList<>();
	ArrayList<PowerUp> powerups = new ArrayList<>();
	SoundFile coinCollectSound;
	SoundFile bulletImpactSound;
	SoundFile gameOverSound;
	SoundFile roarSound;
	SoundFile gruntSound;
	SoundFile ominous;
	SoundFile success;
	String[] celebrations = new String[] {"Great job!", "Amazing!", "Awesome!", "You're a pro!", "Keep it up!"};
	String currentCelebration;
	ArrayList<Collider> defeatedObjects = new ArrayList<Collider>();
	int messageTime = -1;
	boolean isDead = false; 
	int hitTime = -1;
	
	int mobileEnemyHitTime = 0;

	String owner = ""; 
	int time = 0;
	boolean isFinished = false;
	boolean[] keysPressed = new boolean[3];
	boolean[] keysPressedPlayer2 = new boolean[3];
	int lavaHitTime = 0;
	boolean shot;
	boolean growled;
	boolean grunted;
	
	
	/**
	 * Abstract method to draw a Level, with all its objects
	 * @param marker The PApplet to be drawn on
	 */
	public void draw(PApplet marker) {
		
	}
	
	public void setupSoundEffects(PApplet marker) {
		coinCollectSound = new SoundFile(marker, "assets/SoundEffects/coin-collect.wav");
		coinCollectSound.amp(0.2f);
		bulletImpactSound = new SoundFile(marker, "assets/SoundEffects/damage.wav");
		bulletImpactSound.amp(0.3f);
		gameOverSound = new SoundFile(marker, "assets/SoundEffects/game-over.wav");
		roarSound = new SoundFile(marker, "assets/SoundEffects/roar.wav");
		gruntSound = new SoundFile(marker, "assets/SoundEffects/gruntWithDelay.wav");
		ominous = new SoundFile(marker, "assets/SoundEffects/ominous.wav");
		ominous.amp(0.75f);
		success = new SoundFile(marker, "assets/SoundEffects/success.wav");
	}
	/**
	 * Abstract method to move the main character
	 */
	public void move() {
		
	}
	
	/**
	 * Checks if the player is currently in an animation
	 * 
	 * @return boolean Whether or not the character is in an animation
	 */
	public boolean isInAnimation() {
		return false;
	}
	
	/**
	 * Method to display celebratory message when you collect a coin
	 * @param marker
	 */
	public void displayCelebrations(PApplet marker) {
		if (currentCelebration != null && defeatedObjects.size() > 0 && messageTime >= 0) {
			marker.push();
			marker.textFont(marker.createFont("assets/ARCADE_N.TTF", 64));
			marker.textAlign(PApplet.CENTER, PApplet.CENTER);
			marker.textSize(60);
			marker.fill(238,75,43);
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
	
	/**
	 * Method to display celebratory message when a player dies in endless
	 * @param marker
	 */
	public void endMessage(PApplet marker, String winningPlayer) {
		marker.push();
		marker.textFont(marker.createFont("assets/ARCADE_N.TTF", 64));
		marker.textAlign(PApplet.CENTER, PApplet.CENTER);
		marker.textSize(80);
		marker.fill(135,206,235);
		marker.text(winningPlayer + " WINS!", 0, 0, marker.width, marker.height);
		marker.pop();
	}
	

	/**
	 * Method to display a hit
	 * @param marker The PApplet display to be drawn on
	 * @param f The center x location of the player
	 * @param g The center y location of the player
	 */
	public void displayHit(PApplet marker, float f, float g)
	{	
		if(hitTime <= 8 && hitTime >= 0)
		{	
			if (owner == "ShootingEnemy") {
				bulletImpactSound.amp(0.75f);
			} else if (owner == "SideShooter") {
				bulletImpactSound.amp(0.3f);
			}
			if (!shot && !bulletImpactSound.isPlaying()) {
				bulletImpactSound.play();
				shot = true;
			}
			marker.push();
			marker.textFont(marker.createFont("assets/ARCADE_N.TTF", 16));
			marker.textAlign(PApplet.CENTER, PApplet.CENTER);
			marker.fill(225, 0 ,0 );
			if(owner == "ShootingEnemy") {
				marker.translate(f + 5, g + 5);
				marker.rotate(PApplet.radians(30));
				marker.text("-5", 0, 0);
			}
				
			if(owner == "SideShooter") {
				marker.translate(f - 65, g - 15);
				marker.rotate(PApplet.radians(30));
				marker.text("-2", 0, 0);
			}
			
			marker.pop(); 
			hitTime++;
		}
	
		if (hitTime >= 8) {
			shot = false;
			hitTime = -1;
		}
	}
	/**
	 * Method to display a dmage, when it is taken
	 * @param marker The PApplet display to be drawn on
	 * @param f The center x location of the player
	 * @param g The center y location of the player
	 */
	public void displayDamage(PApplet marker, float f, float g, boolean isLava)
	{	
		if (!isLava && !growled && !roarSound.isPlaying()) {
			roarSound.play();
			growled = true;
		} else if (isLava && !grunted && !gruntSound.isPlaying()) {
			gruntSound.play();
			grunted = true;
		}
		marker.push();
		marker.textFont(marker.createFont("assets/ARCADE_N.TTF", 16));
		marker.textAlign(PApplet.CENTER, PApplet.CENTER);
		marker.fill(225, 0 ,0 );
		marker.translate(f - 55, g - 55);
		marker.rotate(PApplet.radians(30));
		marker.text("-" + (isLava ? lavaHitTime : mobileEnemyHitTime) + "", 0, 0);
		marker.pop(); 
		
		if (isLava) {
			lavaHitTime++;
		} else {
			mobileEnemyHitTime++;
		}
	}
	/**
	 * collect a coin, given the coins position
	 * @param i The index of the coin in the array
	 */
	
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
	/**
	 * Tracks the hits on the owner
	 * @param owner A string that describes the owner of the bullet 
	 */
	public void getHit(String owner) {
		hitTime = 0;
		this.owner = owner; 

		
	}
	/**
	 * Defeats a mobile enemy, when its health zeroes out.
	 * @param i
	 */
	public void defeatMobileEnemy(int i) {
		messageTime = 0;
		MobileEnemy defeated = (MobileEnemy) mobilePieces.get(i);
		defeatedObjects.add(defeated);
		currentCelebration = celebrations[(int) (Math.random() * celebrations.length)];
		getPlayer().changePoints(50);
		mobilePieces.remove(i);
	}
	
	/**
	 * Returns the player 1 object
	 * @return The player 1 object reference
	 */
	public ShootingPlayer getPlayer() {
		return player;
	}
	
	/**
	 * Returns the player 2 object
	 * @return The player 2 object reference
	 */
	public ShootingPlayer getPlayer2() {
		return player2;
	}
	
	/**
	 * Returns the status of the level (if it is finished or not)
	 * @return boolean Whether or not the level has been completed
	 */
	public boolean isFinished() {
		return isFinished;
	}
	
	/**
	 * Sets the status of the level
	 * @param isFinished Whether or not the level has been completed
	 */
	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
	
	/**
	 * Returns the current exisitng bullets in the level
	 * @return Arraylist<Collider> The set of bullets that exist in the program
	 */
	public ArrayList<Collider> getBullets() {
		return bullets;
	}
	/**
	 * Returns a boolean array of all the keys pressed, used to move the player
	 * @return keysPressed a boolean array that describes the directions to move
	 */
	public boolean[] getKeysPressed() {
		return keysPressed;
	}
	/**
	 * Returns a boolean array of all the keys pressed, used to move the second player
	 * @return keysPressed a boolean array that describes the directions for the second player to move
	 */
	public boolean[] getKeysPressed2() {
		return keysPressedPlayer2;
	}
	/**
	 * Returns an array list of all the objects
	 * @return ArrayList<Collider> The objects that currently exist in the level 
	 */
	public ArrayList<Collider> getObjects() {
		return objects;
	}
	
	/**
	 * Returns the player speed
	 * @return An int that contains the value of the player's speed
	 */
	public int getPlayerSpeed() {
		return player.playerSpeed;
	}

	/**
	 * Plays the game over sound
	 * 
	 */
	public void playGameOverSound() {
		if (!gameOverSound.isPlaying()) {
			gameOverSound.play();
		}
		
	}
	
	/**
	 * Checks whether or not the player is dead
	 * @return boolean if player has died
	 */
	public boolean getDead()
	{
		return isDead;
	}
	
	/**
	 * Sets the player's death state
	 * @param Sets the player's state as dead
	 */
	public void setDead(boolean dead)
	{
		isDead = dead;
	}
	
	/**
	 * Gets a random integer between the starting to ending
	 * @return int The random number
	 */
	public int getRandomInt(int starting, int ending)
	{
		double r = Math.random() * (ending-starting);
		
		int num = (int)(r + starting + 0.5); 
		
		return num;
		
		
	}
}
