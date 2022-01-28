	
import java.util.ArrayList;

import core.Bullet;
import core.Collider;
import enemies.MobileEnemy;
import enemies.ShootingEnemy;
import enemies.SideShooter;
import obstacles.Barrel;
import obstacles.Lava;
import players.Player;
import players.ShootingPlayer;
import processing.core.PApplet;
import startpage.Leaderboard;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;


public class DrawingSurface extends PApplet implements Serializable{

	private Leaderboard leaderboard;
	private MobileEnemy mobileEnemy;
	private ShootingPlayer player;
	private Lava lava;
	private Barrel barrel; 
	private ShootingEnemy shootingEnemy;
	private SideShooter sideShooter;
	private Boolean tester = false;
	ArrayList<Collider> bullets;
	ArrayList<Collider> gamePieces;
	ArrayList<Collider> playerPieces;
	int playerSpeed = 10;
	int time = 0;
	boolean[] keysPressed;
	public DrawingSurface() {
		setup();
	}
	public void setup() {
		player = new ShootingPlayer(10,100,100,131d,96d,0,10,3000);
		lava = new Lava(0, 0, 300, 650, 100, 0.1);
		barrel = new Barrel(10,300,300,500,50,0,0);
		leaderboard = new Leaderboard();
		keysPressed = new boolean[4];
		mobileEnemy = new MobileEnemy(MobileEnemy.mobileEnemyImages, 10d, 400d, 150d, 0d, 150d, -5d, 0d,(int)424d/4, (int)464d/4, 1);
		sideShooter = new SideShooter(100,200,50,100,100,1); // TODO change bulletfrequency back to lower
		shootingEnemy = new ShootingEnemy(10d, 500d, 500d, 131d, 96d);
		bullets = new ArrayList<>();
		gamePieces = new ArrayList<Collider>();
		playerPieces = new ArrayList<Collider>();
		gamePieces.add(barrel);
		gamePieces.add(sideShooter);
		gamePieces.add(shootingEnemy);
		playerPieces.add(player);
		playerPieces.add(mobileEnemy);
	}
	
	
	public void draw() {
		

		background(50);
		ArrayList<Collider> objects = new ArrayList<>();
		objects.addAll(playerPieces);
		objects.addAll(gamePieces);
		leaderboard.draw(this);
		if(time%sideShooter.bulletFrequency == 0) {
			bullets.add(sideShooter.shoot());
		}
		if(time%40 == 0) {
			bullets.add(shootingEnemy.drop());
		}
		for (int i = 0; i < bullets.size(); i++) {
			Collider bullet = bullets.get(i);
			if (bullet.getX() <= width && bullet.getX() >= 0 && bullet.getY() <= height && bullet.getY() >= 0 && bullet.getHealth() > 0) {
				bullet.draw(this);
				bullet.act((ArrayList<Collider>)playerPieces);
				if(bullet.getHealth() <= 0) {
					bullets.remove(i);
				}
			} else {
				bullets.remove(i);
			}
		}
		
		for (int i = 0; i < playerPieces.size(); i++) {
			if (playerPieces.get(i).getHealth() <= 0) {
				if(playerPieces.get(i) instanceof Player) {
					setup();
				} else {
					playerPieces.remove(i);
				}
				
			} else {
				playerPieces.get(i).draw(this);
				playerPieces.get(i).act(objects);
			}
		}
		for(int i = 0; i < gamePieces.size(); i++) {
			gamePieces.get(i).draw(this);
		}
		
		if(mobileEnemy.getHealth() <= 0 && !tester)
		{
			tester = true;
		}
		
		lava.draw(this);
		move();
		time++;
	}
	
	
	public void mousePressed() {
		Bullet b = player.shoot(mouseX, mouseY);
		bullets.add(b);
	}		
	public void move() {
		if(keysPressed[0]) {
			player.moveBy(0, -playerSpeed, gamePieces);
		}
		if(keysPressed[1]) {
			player.moveBy(0, playerSpeed, gamePieces);
		}
		if(keysPressed[2]) {
			player.moveBy(playerSpeed, 0, gamePieces);
		} 
		if(keysPressed[3]) {
			player.moveBy(-playerSpeed,0, gamePieces);
		}
	}
	public void keyPressed() {
		if (keyCode == UP) { 
			keysPressed[0] = true;
			
		} 
		if (keyCode == DOWN) { 
			keysPressed[1] = true;
		}
		if (keyCode == RIGHT) {
			keysPressed[2] = true;

		}
		if (keyCode == LEFT) {
			keysPressed[3] = true;

		}
		if (key == 'w') {
			keysPressed[0] = true;

		} 
		if (key == 'd') {
			keysPressed[2] = true;

		}
		if (key == 's') {
			keysPressed[1] = true;
		
		}
		if (key == 'a') {
			keysPressed[3] = true;
	
		}
	}
	public void keyReleased() {
		if (keyCode == UP) { 
			keysPressed[0] = false;
			
		} 
		if (keyCode == DOWN) { 
			keysPressed[1] = false;
		}
		if (keyCode == RIGHT) {
			keysPressed[2] = false;

		}
		if (keyCode == LEFT) {
			keysPressed[3] = false;

		}
		if (key == 'w') {
			keysPressed[0] = false;

		} 
		if (key == 'd') {
			keysPressed[2] = false;

		}
		if (key == 's') {
			keysPressed[1] = false;
		
		}
		if (key == 'a') {
			keysPressed[3] = false;
	
		}
		if(key == ' ') {
			player.jump(gamePieces);
		}
	}

}
