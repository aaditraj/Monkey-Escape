	
import java.util.ArrayList;

import core.Bullet;
import core.Collider;
import enemies.MobileEnemy;
import enemies.ShootingEnemy;
import enemies.SideShooter;
import obstacles.Lava;
import players.Player;
import players.ShootingPlayer;
import powerups.Coin;
import processing.core.PApplet;
import processing.core.PImage;
import obstacles.Platform;



public class DrawingSurface extends PApplet{

	private MobileEnemy mobileEnemy;
	private ShootingPlayer player;
	private Lava lava;
	private Platform platform; 
	private ShootingEnemy shootingEnemy;
	private SideShooter sideShooter;
	private Coin coin;
	ArrayList<Collider> bullets;
	ArrayList<Collider> gamePieces;
	ArrayList<Collider> playerPieces;
	private PImage bg;
	int playerSpeed = 10;
	int time = 0;
	boolean[] keysPressed;
	public DrawingSurface() {

	}

	
	
	public void setup() {
		player = new ShootingPlayer(10,100,100,131d,96d,0,10, 5);
		lava = new Lava(10, 0, 950, 2000, 50, 0.1);
		coin = new Coin(300,200);
		platform = new Platform(300,300,500,50,false);
		keysPressed = new boolean[4];
		mobileEnemy = new MobileEnemy(MobileEnemy.mobileEnemyImages, 10d, 400d, 150d, 0d, 150d, -5d, 0d,(int)424d/4, (int)464d/4);
		sideShooter = new SideShooter(100,200,50,100,100,1); // TODO change bulletfrequency back to lower
		shootingEnemy = new ShootingEnemy(10d, 500d, 100d, 131d, 96d);
		bullets = new ArrayList<>();
		gamePieces = new ArrayList<Collider>();
		playerPieces = new ArrayList<Collider>();
		gamePieces.add(platform);
		gamePieces.add(sideShooter);
		gamePieces.add(shootingEnemy);
		gamePieces.add(coin);
		playerPieces.add(player);
		playerPieces.add(mobileEnemy);
		bg = loadImage("assets/Background.jpg");
	}
	
	
	public void draw() {
		image(bg, 0, 0, width, height);
		ArrayList<Collider> objects = new ArrayList<>();
		objects.addAll(playerPieces);
		objects.addAll(gamePieces);
		if(time%sideShooter.bulletFrequency == 0) {
			bullets.add(sideShooter.shoot());
		}
		if(time%20 == 0) {
			bullets.add(shootingEnemy.drop());
		}
		if(time%Player.shootingPlayerReloadTime == 0) {
			player.increaseAmmo();
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
			if(gamePieces.get(i) instanceof Coin) {
				if(coin.intersects(player)) {
					coin.collide(player);
					gamePieces.remove(i);
				} else {
					gamePieces.get(i).draw(this);
				}
			} else {
				gamePieces.get(i).draw(this);
			}
			
		}
		
		if(lava.intersects(player))
		{
			player.changeHealth(-1);
		}
		lava.increaseHeight(player);
		lava.draw(this);
		
		move();
		time++;
	}
	
	
	public void mousePressed() {
		if (player.getAmmo() > 0) {
			Bullet b = player.shoot(mouseX, mouseY);
			bullets.add(b);
		}
		
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
