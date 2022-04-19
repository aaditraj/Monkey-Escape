package levels;

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
import powerups.DamagePowerUp;
import powerups.PowerUp;
import processing.core.PApplet;
import processing.core.PImage;

public class Level1 extends Level{
	MobileEnemy enemy1;
	MobileEnemy enemy2;
	SideShooter shooter1;
	SideShooter shooter2;
	Platform platform1;
	Platform platform2;
	Platform platform3;
	Platform platform4;
	Platform platform5;
	Coin coin1;
	Coin coin2;
	Coin coin3;
	Coin coin4;
	ShootingEnemy dropper1;
	ShootingEnemy dropper2;
	Collider endPiece;
	Lava lava;
	PImage bg;
	float bulletHitX = 0;
	float bulletHitY = 0;
	boolean isDead; 

	boolean inDeathAnimation;
	int deathTime;
	String[] deathAnimation = new String[]{"assets/Player/Player.png","assets/Player/Player_body.png","assets/Player/Player_head.png","assets/Player/Player_head_dropped.png"};
	public void setup(PApplet marker) {
		staticPieces = new ArrayList<>();
		mobilePieces = new ArrayList<>();
		bullets = new ArrayList<>();
		totalPieces = new ArrayList<>();
		objects = new ArrayList<>();
		inDeathAnimation = false;
		deathTime = 0;
		enemy1 = new MobileEnemy(MobileEnemy.mobileEnemyImages,10,1200,600,800,600,-10,0,80,100);
		enemy2 = new MobileEnemy(MobileEnemy.mobileEnemyImages,10,1200,200,800,200,-10,0,80,100);
		shooter1 = new SideShooter(10,824,70, 88,76, 1);
		shooter2 = new SideShooter(10,424, 70, 88,76, 1);
		player = new ShootingPlayer(50,200,700,100,100,0,10,10,125);
		platform1 = new Platform("assets/Platform/log-platform.png", 800,700,600,40,false);
		platform2 = new Platform("assets/Platform/log-platform.png", 800,300,600,40,false);
		platform3 = new Platform("assets/Platform/log-platform.png", 10,500,600,40,false);
		platform4 = new Platform("assets/Platform/log-platform.png", 10,900,600,40,false);
		platform5 = new Platform("assets/Platform/log-platform.png", 10,200,200,40,false);
		endPiece = new Collider(new String[] {"assets/door.png"},20,1300,200,100,100,0,0);
		coin1 = new Coin(750,1400);
		coin2 = new Coin(350,1400);
		coin3 = new Coin(550,610);
		coin4 = new Coin(950,610);
		coins.add(coin1);
		coins.add(coin2);
		coins.add(coin3);
		coins.add(coin4);
		dropper1 = new ShootingEnemy(50,100,100,100,100);
		//platform6 = new Platform(10,900,100,40,false);
		lava = new Lava(10, 0, 950, 2000, 100, 0.1);
		
//		int r = this.getRandomInt(1, 4);
//		if(r == 1) p = new DamagePowerUp();
//		else if(r == 1) p = new DamagePowerUp();
//		else if(r == 1) p = new DamagePowerUp();
//		else p = new DamagePowerUp();

		player.playerSpeed = 10;
		staticPieces.add(platform1);
		staticPieces.add(platform2);
		staticPieces.add(platform3);
		staticPieces.add(platform4);
		staticPieces.add(platform5);
		staticPieces.add(shooter1);
		staticPieces.add(shooter2);
		staticPieces.add(dropper1);
		mobilePieces.add(getPlayer());
		mobilePieces.add(enemy1);
		mobilePieces.add(enemy2);
		setupSoundEffects(marker);
	}
	public void setPlayer(ShootingPlayer player) {
		this.player = player;
	}
	public void draw(PApplet marker) {
		//bg = marker.loadImage("assets/Backgrounds/forest1.jpeg");
		//marker.image(bg, 0, 0, marker.width, marker.height);
		time++;
		objects = new ArrayList<>();
		getObjects().addAll(mobilePieces);
		getObjects().addAll(staticPieces);
		if(time%shooter1.bulletFrequency == 0) {
			getBullets().add(shooter2.shoot());
			getBullets().add(shooter1.shoot());
			getBullets().add(dropper1.drop(getPlayer().getCenterX(),getPlayer().getCenterY()));
		}
		if(time%10 == 0) {
			getPlayer().increaseAmmo();
		}
		if(inDeathAnimation) {
			
		}
		
		for(int i = 0; i < mobilePieces.size(); i++) {
			
			if(mobilePieces.get(i) instanceof MobileEnemy)
			{
				boolean hit = false;
				MobileEnemy currentMobileEnemy = (MobileEnemy) mobilePieces.get(i);
				MobileEnemy suspect = null;

				
				for(int k = 0; k < mobilePieces.size(); k++)
				if(mobilePieces.get(k).intersects(getPlayer()) && mobilePieces.get(k) instanceof MobileEnemy) 
				{
					suspect = (MobileEnemy) mobilePieces.get(k); 
					hit = true;
				}
				
				
				if(hit) 
				{
					if(suspect != null && suspect == currentMobileEnemy && !inDeathAnimation) {
						displayDamage(marker, (float)getPlayer().getCenterX(), (float)getPlayer().getCenterY(), false);
					}
				}
				else mobileEnemyHitTime = 0;
			}
			

			
			if(mobilePieces.get(i).getHealth() <= 0) {
				if(mobilePieces.get(i) instanceof ShootingPlayer) {
					if(!inDeathAnimation) {
						inDeathAnimation = true;
						player.setImages(deathAnimation);
						player.setFrequency(10);
						player.setHealth(0);
					}
					mobilePieces.get(i).draw(marker);
				} else {
					if(mobilePieces.get(i) instanceof MobileEnemy) {
						defeatMobileEnemy(i);
					}
					else {
						mobilePieces.remove(i);
					}
				}
			} else {
				if(!inDeathAnimation) {
					mobilePieces.get(i).act(getObjects());
				}
				mobilePieces.get(i).draw(marker);
			}
		}
		
		
	
		for(int i = 0; i < getBullets().size(); i++) {
			Collider bullet = getBullets().get(i);
			if (bullet.getX() <= marker.width && bullet.getX() >= 0 && bullet.getY() <= marker.height && bullet.getY() >= 0 && bullet.getHealth() > 0) {
				bullet.draw(marker);
				if(!inDeathAnimation) {
					if(((Bullet)bullet).getOwner().equals("player")) {
						bullet.act((ArrayList<Collider>)mobilePieces);
					} else {
						ArrayList<Collider> playerList = new ArrayList<>();
						playerList.add(player);
						bullet.act(playerList);
					}
				}
				if(bullet.getHealth() <= 0) {
					getBullets().remove(i);
					
					if(bullet.intersects(getPlayer()))
					{
						Bullet bullet2 = (Bullet)bullet; 
						bulletHitX = (float)bullet2.getCenterX(); 
						bulletHitY = (float)bullet2.getCenterY(); 
						getHit(bullet2.owner); 
					}
				}
			} else {
				getBullets().remove(i);
			}
		}
		for(int i = 0; i < staticPieces.size(); i++) {
			staticPieces.get(i).draw(marker);
		}
		for(int i = 0; i < coins.size(); i++) {
			coins.get(i).draw(marker);
			if(coins.get(i).intersects(getPlayer()))
			{
				collectCoin(i);
			}
		}
		if(lava.intersects(getPlayer()))
		{
			getPlayer().changeHealth(-1);
		}
		if(!inDeathAnimation) {
			lava.increaseHeight(getPlayer());
		}
		if(inDeathAnimation && deathTime == 3 && time % player.getImgFrequency() == player.getImgFrequency()-1) {
			super.playGameOverSound();
			isDead = true;
			setup(marker);
		}
		if(inDeathAnimation && time % player.getImgFrequency() == 0) {
			deathTime++;
		}
		lava.draw(marker);
		endPiece.draw(marker);
		if(getPlayer().intersects(endPiece)) {
			setFinished(true);
		}
		displayCelebrations(marker);
		displayHit(marker, bulletHitX, bulletHitY);


	}	
	public boolean isInDeathAnimation() {
		return inDeathAnimation;
	}
}
