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

public class Level2 extends Level {
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
	private Collider endPiece;
	private Lava lava;
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
		shooter1 = new SideShooter(10,850,50, 80,50, 1);
		shooter2 = new SideShooter(10,450, 50, 80,50, 1);
		lava = new Lava(10, 0, 950, 2000, 50, 0.1);
		shooter3 = new SideShooter(1100,550, 50, 80,50, -1);
		dropper1 = new ShootingEnemy(1500, 300, 0, 75, 75);
		coin2 = new Coin(10,775);
		coin1 = new Coin(10,375);
		coins.add(coin1);
		coins.add(coin2);
		player = new ShootingPlayer(30,200,700,100,80,0,10,10);
		platformTop = new Platform(0, 75, 1100, 20, false);
		platform1 = new Platform(650,800,500,40,false);
		platform2 = new Platform(650,600,500,40,false);
		platform3 = new Platform(10,700,500,40,false);
		platform4 = new Platform(10,900,500,40,false);
		platform5 = new Platform(650,400,500,40,false);
		platform6 = new Platform(650,240,500,40,false);
		platform7 = new Platform(10,500,500,40,false);
		platform8 = new Platform(100,350,400,40,false);
		endPiece = new Collider(new String[] {"assets/Projectiles/Bullet.png"},20,1000,220,100,100,0,0);

		
		playerSpeed = 10;
		staticPieces.add(platform1);
		staticPieces.add(platform2);
		staticPieces.add(platform3);
		staticPieces.add(platform4);
		staticPieces.add(platform5);
		staticPieces.add(platform6);
		staticPieces.add(platform7);
		staticPieces.add(platform8);
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
	public void draw(PApplet marker) {
		
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
		
		if(player.intersects(endPiece))
		{
			isFinished = true; 
		}
		
		if(time%10 == 0) {
			player.increaseAmmo();
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
				mobilePieces.get(i).draw(marker);
			}
		}
		for(int i = 0; i < coins.size(); i++) {
			coins.get(i).draw(marker);
			if(coins.get(i).intersects(player))
			{
				collectCoin(i);
			}
		}
		for(int i = 0; i < bullets.size(); i++) {
			Collider bullet = bullets.get(i);
			if (bullet.getX() <= marker.width && bullet.getX() >= 0 && bullet.getY() <= marker.height && bullet.getY() >= 0 && bullet.getHealth() > 0) {
				bullet.draw(marker);
				bullet.act((ArrayList<Collider>)mobilePieces);
				if(bullet.getHealth() <= 0) {
					bullets.remove(i);
				}
			} else {
				bullets.remove(i);
			}
		}
		for(int i = 0; i < staticPieces.size(); i++) {
			staticPieces.get(i).draw(marker);
		}
		if(lava.intersects(player))
		{
			player.changeHealth(-1);
		}
		lava.increaseHeight(player);
		lava.draw(marker);
		displayCelebrations(marker);
		
	}
}
