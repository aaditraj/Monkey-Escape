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

public class Level1 extends Level{
	MobileEnemy enemy1;
	MobileEnemy enemy2;
	SideShooter shooter1;
	SideShooter shooter2;
	Platform platform1;
	Platform platform2;
	Platform platform3;
	Platform platform4;
	Coin coin1;
	Coin coin2;
	Coin coin3;
	Coin coin4;
	ShootingEnemy dropper1;
	ShootingEnemy dropper2;
	Collider endPiece;
	Lava lava;
	public void setup() {
		staticPieces = new ArrayList<>();
		mobilePieces = new ArrayList<>();
		bullets = new ArrayList<>();
		totalPieces = new ArrayList<>();
		objects = new ArrayList<>();
		enemy1 = new MobileEnemy(MobileEnemy.mobileEnemyImages,10,1200,600,800,600,-10,0,100,100);
		enemy2 = new MobileEnemy(MobileEnemy.mobileEnemyImages,10,1200,200,800,200,-10,0,100,100);
		shooter1 = new SideShooter(10,800,70, 100,100, 1);
		shooter2 = new SideShooter(10,400, 70, 100,100, 1);
		player = new ShootingPlayer(10,200,700,100,100,0,10,10,125);
		platform1 = new Platform(800,700,600,40,false);
		platform2 = new Platform(800,300,600,40,false);
		platform3 = new Platform(10,500,600,40,false);
		platform4 = new Platform(10,900,600,40,false);
		endPiece = new Collider(new String[] {"assets/Bullet.png"},20,1000,200,100,100,0,0);
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
	public void draw(PApplet marker) {
		marker.background(50);
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
		for(int i = 0; i < bullets.size(); i++) {
			Collider bullet = bullets.get(i);
			if (bullet.getX() <= marker.width && bullet.getX() >= 0 && bullet.getY() <= marker.height && bullet.getY() >= 0 && bullet.getHealth() > 0) {
				bullet.draw(marker);
				bullet.act((ArrayList<Collider>)objects);
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
		if(player.intersects(endPiece)) {
			isFinished = true;
		}
	}		
}
