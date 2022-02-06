package Levels;

import java.util.ArrayList;

import core.Bullet;
import core.Collider;
import enemies.MobileEnemy;
import enemies.ShootingEnemy;
import enemies.SideShooter;
import obstacles.Lava;
import obstacles.Platform;
import players.ShootingPlayer;
import powerups.Coin;
import processing.core.PApplet;

public class Level1 extends PApplet {
	private ShootingPlayer player;
	private MobileEnemy enemy1;
	private MobileEnemy enemy2;
	private SideShooter shooter1;
	private SideShooter shooter2;
	private Platform platform1;
	private Platform platform2;
	private Platform platform3;
	private Platform platform4;
	private Coin coin1;
	private Coin coin2;
	private Coin coin3;
	private Coin coin4;
	private ShootingEnemy dropper1;
	private ShootingEnemy dropper2;
	int playerSpeed;
	private Collider endPiece;
	Lava lava;
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
		keysPressed = new boolean[4];
		enemy1 = new MobileEnemy(MobileEnemy.mobileEnemyImages,10,1200,600,800,600,-10,0,100,100);
		enemy2 = new MobileEnemy(MobileEnemy.mobileEnemyImages,10,1200,200,800,200,-10,0,100,100);
		shooter1 = new SideShooter(10,800,70, 100,100, 1);
		shooter2 = new SideShooter(10,400, 70, 100,100, 1);
		player = new ShootingPlayer(30,200,700,100,100,0,10,10);
		platform1 = new Platform(800,700,500,40,false);
		platform2 = new Platform(800,300,500,40,false);
		platform3 = new Platform(10,500,500,40,false);
		platform4 = new Platform(10,900,600,40,false);
		//platform6 = new Platform(10,900,100,40,false);
		lava = new Lava(10, 0, 950, 2000, 50, 0.1);
		playerSpeed = 10;
		staticPieces.add(platform1);
		staticPieces.add(platform2);
		staticPieces.add(platform3);
		staticPieces.add(platform4);
		staticPieces.add(shooter1);
		staticPieces.add(shooter2);
		mobilePieces.add(player);
		mobilePieces.add(enemy1);
		mobilePieces.add(enemy2);
	}
	public void setPlayer(ShootingPlayer player) {
		this.player = player;
	}
	public void draw() {
		background(50);
		move();
		time++;
		objects = new ArrayList<>();
		objects.addAll(mobilePieces);
		objects.addAll(staticPieces);
		if(time%shooter1.bulletFrequency == 0) {
			bullets.add(shooter2.shoot());
			bullets.add(shooter1.shoot());
		}
		if(time%10 == 0) {
			player.increaseAmmo();
		}
		for(int i = 0; i < mobilePieces.size(); i++) {
			if(mobilePieces.get(i).getHealth() == 0) {
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
		if(lava.intersects(player))
		{
			player.changeHealth(-1);
		}
		lava.increaseHeight(player);
		lava.draw(this);
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
			player.jump(objects);
		}
	}
}
