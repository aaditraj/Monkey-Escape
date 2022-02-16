package Levels;

import java.util.ArrayList;
import javax.swing.Timer;

import core.Collider;
import enemies.MobileEnemy;
import players.ShootingPlayer;
import powerups.Coin;
import processing.core.PApplet;

public class Level {
	ShootingPlayer player;
	ArrayList<Collider> staticPieces = new ArrayList<>();
	ArrayList<Collider> mobilePieces = new ArrayList<>();
	ArrayList<Collider> bullets = new ArrayList<>();
	ArrayList<Collider> totalPieces = new ArrayList<>();
	ArrayList<Collider> objects = new ArrayList<>();
	ArrayList<Collider> coins = new ArrayList<>();
	String[] celebrations = new String[] {"Great job!", "Amazing!", "Awesome!", "You're a pro!", "Keep it up!"};
	String currentCelebration;
	ArrayList<Collider> defeatedObjects = new ArrayList<Collider>();
	int messageTime = -1;
	int time = 0;
	boolean isFinished = false;
	boolean[] keysPressed = new boolean[4];
	int playerSpeed = 10;
	public void draw(PApplet marker) {
		
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
				marker.text("+15", (float) c.getX(), (float) c.getY());
			}
			marker.pop();
			messageTime++;
		} 
		if (messageTime >= 30) {
			messageTime = -1;
			defeatedObjects.clear();
		}
	}
	
	public void collectCoin(int i) {
		messageTime = 0;
		Coin collected = (Coin) coins.get(i);
		defeatedObjects.add(collected);
		currentCelebration = celebrations[(int) (Math.random() * celebrations.length)];
		coins.get(i).collide(player);
		player.changePoints(15);
		coins.remove(i);
	}
	
	public void defeatMobileEnemy(int i) {
		messageTime = 0;
		MobileEnemy defeated = (MobileEnemy) mobilePieces.get(i);
		defeatedObjects.add(defeated);
		currentCelebration = celebrations[(int) (Math.random() * celebrations.length)];
		player.changePoints(50);
		mobilePieces.remove(i);
	}
}
