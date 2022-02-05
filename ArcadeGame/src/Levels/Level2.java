package Levels;

import java.util.ArrayList;

import core.Bullet;
import core.Collider;
import enemies.MobileEnemy;
import enemies.ShootingEnemy;
import enemies.SideShooter;
import obstacles.Platform;
import players.ShootingPlayer;
import powerups.Coin;
import processing.core.PApplet;

public class Level2 extends PApplet {
	private ShootingPlayer player;
	private MobileEnemy enemy1;
	private MobileEnemy enemy2;
	private MobileEnemy enemy3;
	private SideShooter shooter1;
	private SideShooter shooter2;
	private SideShooter shooter3;

	private Platform platformTop;

	private Platform platform1;
	private Platform platform2;
	private Platform platform3;
	private Platform platform4;
	private Platform platform5;
	private Platform platform6;
	private Platform platform7;
	private Platform platform8;
	private Coin coin1;
	private Coin coin2;
	private Coin coin3;
	private Coin coin4;
	private ShootingEnemy dropper1;
	private ShootingEnemy dropper2;
	int playerSpeed;
	private Collider endPiece;
	ArrayList<Collider> staticPieces = new ArrayList<>();
	ArrayList<Collider> mobilePieces = new ArrayList<>();
	ArrayList<Collider> bullets = new ArrayList<>();
	ArrayList<Collider> totalPieces = new ArrayList<>();
	ArrayList<Collider> objects = new ArrayList<>();
	int time = 0;
	private boolean[] keysPressed;
	public void setup() {
		staticPieces = new ArrayList<>();
		mobilePieces = new ArrayList<>();
		bullets = new ArrayList<>();
		totalPieces = new ArrayList<>();
		objects = new ArrayList<>();
		keysPressed = new boolean[5];
		enemy1 = new MobileEnemy(MobileEnemy.mobileEnemyImages,10,1000,500,750,500,-10,0,100,100);
		enemy2 = new MobileEnemy(MobileEnemy.mobileEnemyImages,10,1000,300,750,200,-10,0,100,100);
		enemy3 = new MobileEnemy(MobileEnemy.mobileEnemyImages,10,1000,700,750,200,-10,0,100,100);
		shooter1 = new SideShooter(10,850,70, 80,50, 1);
		shooter2 = new SideShooter(10,450, 70, 80,50, 1);
		
		shooter3 = new SideShooter(1100,550, 70, 80,50, 0);
		dropper1 = new ShootingEnemy(1500, 300, 0, 75, 75);
		coin2 = new Coin(10,775);
		coin1 = new Coin(10,375);

		player = new ShootingPlayer(30,200,700,100,100,0,10,10);
		platformTop = new Platform(0, 75, 1100, 20, false);
		platform1 = new Platform(650,800,500,40,false);
		platform2 = new Platform(650,600,500,40,false);
		platform3 = new Platform(10,700,500,40,false);
		platform4 = new Platform(10,900,500,40,false);
		platform5 = new Platform(650,400,500,40,false);
		platform6 = new Platform(650,240,500,40,false);
		platform7 = new Platform(10,500,500,40,false);
		platform8 = new Platform(100,350,400,40,false);
		playerSpeed = 10;
		staticPieces.add(platform1);
		staticPieces.add(platform2);
		staticPieces.add(platform3);
		staticPieces.add(platform4);
		staticPieces.add(platform5);
		staticPieces.add(platform6);
		staticPieces.add(platform7);
		staticPieces.add(platform8);
		staticPieces.add(coin1);
		staticPieces.add(coin2);

		staticPieces.add(platformTop);
		staticPieces.add(dropper1);

		staticPieces.add(shooter1);
		staticPieces.add(shooter2);
		staticPieces.add(shooter3);

		mobilePieces.add(player);
		mobilePieces.add(enemy1);
		mobilePieces.add(enemy2);
		mobilePieces.add(enemy3);

	}
	public void setPlayer(ShootingPlayer player) {
		this.player = player;
	}
	public void draw() {
		background(50);
		time++;
		objects = new ArrayList<>();
		objects.addAll(mobilePieces);
		objects.addAll(staticPieces);
		
		if(time%shooter1.bulletFrequency == 0) {
			bullets.add(shooter2.shoot());
			bullets.add(shooter1.shoot());
			bullets.add(shooter3.shoot());
			bullets.add(dropper1.drop());
			

		}
		if(time%10 == 0) {
			player.increaseAmmo();
		}
		
		if(coin1.intersects(player)) {
			coin1.collide(player);
			staticPieces.remove(coin1);
		}
		
		
		if(coin2.intersects(player)) {
			coin2.collide(player);
			staticPieces.remove(coin2);
		}
		
		for(int i = 0; i < mobilePieces.size(); i++) {
			if(mobilePieces.get(i).getHealth() <= 0) {
				if(mobilePieces.get(i) instanceof ShootingPlayer) {
					setup();
				} else {
					mobilePieces.remove(i);
				}
			} else {
				mobilePieces.get(i).act(objects);
				mobilePieces.get(i).draw(this);
			}
		}
		for(int i = 0; i < bullets.size(); i++) {
			Collider bullet = bullets.get(i);
			if (bullet.getX() <= width && bullet.getX() >= 0 && bullet.getY() <= height && bullet.getY() >= 0 && bullet.getHealth() > 0) {
				bullet.draw(this);
				bullet.act((ArrayList<Collider>)mobilePieces);
				if(bullet.getHealth() <= 0) {
					bullets.remove(i);
				}
			} else {
				bullets.remove(i);
			}
		}
		for(int i = 0; i < staticPieces.size(); i++) {
			staticPieces.get(i).draw(this);
		}
		
		 move();
	}
	public void mousePressed() {
		if (player.getAmmo() > 0) {
			Bullet b = player.shoot(mouseX, mouseY);
			bullets.add(b);
		}
		
	}		
	public void move() {
		if(keysPressed[0]) {
			player.moveBy(0, -playerSpeed, objects);
		}
		if(keysPressed[1]) {
			player.moveBy(0, playerSpeed,  objects);
		}
		if(keysPressed[2]) {
			player.moveBy(playerSpeed, 0, objects);
		} 
		if(keysPressed[3]) {
			player.moveBy(-playerSpeed,0, objects);
		}
		if(keysPressed[4]) {
			player.jump(objects);
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
	
		}if(key == ' ') {
			keysPressed[4] = true;
		
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
			keysPressed[4] = false;
		
		}
	}
}
