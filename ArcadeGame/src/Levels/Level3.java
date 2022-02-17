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

public class Level3 extends Level {
	private MobileEnemy enemy1;
	private MobileEnemy enemy2;
	private SideShooter shooter1;
	private SideShooter shooter2;
	private SideShooter shooter3;

	private Platform platformBottom;
	private Platform platform1;
	private Platform platform2;
	private Platform platform3;
	private Platform platform4;
	private Platform platform5;
	private Platform platform6;
	private Platform platformTop;
	private Collider endPiece;
	private Coin coin1;
	private Coin coin2;
	private Coin coin3;
	private Coin coin4;
	private Coin coin5;
	private Coin coin6;
	private Lava lava;

	private ShootingEnemy dropper;

	private Platform platform1Danger;
	private Platform platform2Danger;
	private Platform wall;

	public void setup() {
		staticPieces = new ArrayList<>();
		mobilePieces = new ArrayList<>();
		bullets = new ArrayList<>();
		totalPieces = new ArrayList<>();
		objects = new ArrayList<>();
		keysPressed = new boolean[5];
		player = new ShootingPlayer(10,0,900,100d,100d,0,10, 7,90);
	
		platformBottom = new Platform(0,1000,1050,40,false);
		enemy1 = new MobileEnemy(MobileEnemy.mobileEnemyImages,10,525,900,900,900,15,0,100,125);
		coin1 = new Coin(950,950);
		coin2 = new Coin(950, 850);


		platform1 = new Platform(10,800,500,40,false);
		platform1Danger = new Platform(200,800,200,40,true);
		shooter1 = new SideShooter(10,725,40, 80,50, 1);
		coin3 = new Coin(10,650);
		
		
		platform2 = new Platform(650,600,400,40,false);
		platform2Danger = new Platform(740,600,150,40,true);
		shooter2 = new SideShooter(950,550, 20, 80,50, 0);
		coin4 = new Coin(950, 475);

		platform3 = new Platform(10,500,500,40,false);
		
		shooter3 = new SideShooter(10,450, 70, 80,50, 1);
		coin5 = new Coin(10, 375);
		
		platform4 = new Platform(0,300,150,40,false);
		platform5 = new Platform(850,250,200,40,false);
		platform6 = new Platform(275, 200, 400, 40, false);
		platformTop = new Platform(0, 75, 100, 20, false);

		enemy2 = new MobileEnemy(MobileEnemy.mobileEnemyImages, 10d, 275d, 100d, 600d, 100d, 15d, 0d,424d/4, 464d/4);
		dropper = new ShootingEnemy(1500, 0, 0, 75, 75);
		coin6 = new Coin(90, 10);
		
		lava = new Lava(10, 0, 1050, 2000, 50, 0.2);
		
		endPiece = new Collider(new String[] {"assets/door.png"},275,1000,150,100,100,0,0);


		playerSpeed = 15;
		staticPieces.add(platform2);
		staticPieces.add(platform1);
		staticPieces.add(platform3);
		staticPieces.add(platform1Danger);
		staticPieces.add(platform2Danger);
		staticPieces.add(platformBottom);
		staticPieces.add(platform5);
		staticPieces.add(platform6);
		staticPieces.add(platform4);
		coins.add(coin1);
		coins.add(coin2);
		coins.add(coin3);
		coins.add(coin4);
		coins.add(coin5);
		coins.add(coin6);


		staticPieces.add(platformTop);
		staticPieces.add(dropper);

		staticPieces.add(shooter1);
		staticPieces.add(shooter2);
		staticPieces.add(shooter3);

		mobilePieces.add(player);
		mobilePieces.add(enemy1);
		mobilePieces.add(enemy2);

	}
	public void setPlayer(ShootingPlayer player) {
		this.player = player;
	}
	public void draw(PApplet marker) {
		
		
		time++;
		objects = new ArrayList<>();
		objects.addAll(mobilePieces);
		objects.addAll(staticPieces);
		objects.remove(coin1);
		objects.remove(coin2);
		
		if(time%shooter1.bulletFrequency == 0) {
			bullets.add(shooter2.shoot());
			bullets.add(shooter1.shoot());
			bullets.add(shooter3.shoot());
			bullets.add(dropper.drop(player.getCenterX(), player.getCenterY()));
			

		}
		if(time%10 == 0) {
			player.increaseAmmo();
		}
		
		if (platform1Danger.intersects(player)) {
			playerSpeed = 5;
			platform1Danger.collide(player);
		} else {
			playerSpeed = 10;
		}
		
		if (platform2Danger.intersects(player)) {
			playerSpeed = 5;
			platform2Danger.collide(player);
		} else {
			playerSpeed = 10;
		}

		for(int i = 0; i < mobilePieces.size(); i++) {
			if(mobilePieces.get(i).getHealth() <= 0) {
				if(mobilePieces.get(i) instanceof ShootingPlayer) {
					setup();
				} else {
					if(mobilePieces.get(i) instanceof MobileEnemy) {
						defeatMobileEnemy(i);
					}
				}
			} else {
				mobilePieces.get(i).act(objects);
				mobilePieces.get(i).draw(marker);
			}
		}
		for(int i = 0; i < bullets.size(); i++) {
			Collider bullet = bullets.get(i);
			if (bullet.getX() <= marker.width && bullet.getX() >= 0 && bullet.getY() <= marker.height && bullet.getY() >= 0 && bullet.getHealth() > 0) {
				bullet.draw(marker);
				if (!((Bullet) bullet).getOwner().equals("player")) {
					ArrayList<Collider> playerList = new ArrayList<Collider>();
					playerList.add(player);
					bullet.act(playerList);
				} else {
					bullet.act(objects);
				}
				if(bullet.getHealth() <= 0) {
					bullets.remove(i);
				}
			} else {
				bullets.remove(i);
			}
		}
		for(int i = 0; i < coins.size(); i++) {
			coins.get(i).draw(marker);
			if(coins.get(i).intersects(player))
			{
				collectCoin(i);
			}
		}
		for(int i = 0; i < staticPieces.size(); i++) {
			staticPieces.get(i).draw(marker);
		}
		if(player.intersects(endPiece)) {
			isFinished = true;
		}
		lava.increaseHeight(player);
		lava.draw(marker);
		endPiece.draw(marker);
		displayCelebrations(marker);
	}
}

