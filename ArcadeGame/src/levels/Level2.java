package levels;

import java.awt.geom.Point2D;
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
import powerups.InvincibilityPowerUp;
import powerups.PowerUp;
import powerups.SlowDownPowerUp;
import powerups.SpeedBoostPowerUp;
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
	private ShootingEnemy dropper1;
	private Collider endPiece;
	
	
	boolean inDoorAnimation; 
	int doorTime;
	String[] doorAnimation1 = new String[]{"assets/Open Door.png"};
	String[] doorAnimation2 = new String[]{"assets/DoorLeft.png"};
	private Lava lava;
	float bulletHitX = 0;
	float bulletHitY = 0;
	boolean inDeathAnimation;
	int deathTime;
	String[] deathAnimation = new String[]{"assets/Player/Player.png","assets/Player/Player_body.png","assets/Player/Player_head.png","assets/Player/Player_head_dropped.png"};
	public void setup(PApplet marker) {
		staticPieces = new ArrayList<>();
		mobilePieces = new ArrayList<>();
		bullets = new ArrayList<>();
		totalPieces = new ArrayList<>();
		objects = new ArrayList<>();
		keysPressed = new boolean[5];
		inDeathAnimation = false;
		deathTime = 0;
		enemy1 = new MobileEnemy(MobileEnemy.mobileEnemyImages,10,1000,510,650,500,-10,0,72,90);
		enemy2 = new MobileEnemy(MobileEnemy.mobileEnemyImages,10,1000,330,650,200,-10,0,72,90);
		enemy3 = new MobileEnemy(MobileEnemy.mobileEnemyImages,10,1000,710,650,200,-10,0,72,90);
		shooter1 = new SideShooter(10,830,50, 88,76, 1);
		shooter2 = new SideShooter(10,430, 50, 88,76, 1);
		lava = new Lava(10, 0, 1040, 2000, 100, 0.2);
		shooter3 = new SideShooter(1100,530, 50, 88,76, -1);
		dropper1 = new ShootingEnemy(1500, 300, 0, 75, 75);
		coin1 = new Coin(10,375);
		coins.clear();
		coins.add(coin1);
		player = new ShootingPlayer(120,250,700,100,100,0,10,10);
		platformTop = new Platform(0, 75, 600, 40, false);
		platform1 = new Platform(650,800,500,40,false);
		platform2 = new Platform(650,600,500,40,false);
		platform3 = new Platform(10,700,500,40,false);
		platform4 = new Platform(10,900,500,40,false);
		platform5 = new Platform(650,420,500,40,false);
		platform6 = new Platform(650,240,500,40,false);
		platform7 = new Platform(10,500,500,40,false);
		platform8 = new Platform(100,310,400,40,false);
		endPiece = new Collider(new String[] {"assets/door.png"},20,1000,150,100,100,0,0);

		
		player.playerSpeed = 10;
		player.setJumpHeight(-16);
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

		mobilePieces.add(getPlayer());
		mobilePieces.add(enemy1);
		mobilePieces.add(enemy2);
		mobilePieces.add(enemy3);
		
		int[][] positions = new int[2][2];
		positions[0] = new int[]{1100,720};
		positions[1] = new int[]{450,450};
		powerups.clear();
//		for(int i = 0; i < 2; i++) {
//			int random = (int)(Math.random() * 4); // TODO this is the powerup to test, can change arguments as needed
//			PowerUp powerup;
//			switch (random) {
//			case 0:
//				powerup = new DamagePowerUp(mobilePieces, bullets, 0, 0, 50, 50);
//				break;
//			case 1:
//				powerup = new InvincibilityPowerUp(mobilePieces, bullets, 0, 0, 50, 50);
//				break;
//
//			case 2:
//				powerup = new SlowDownPowerUp(mobilePieces, bullets, 0, 0, 50, 50);
//				break;
//
//			case 3:
//				powerup = new SpeedBoostPowerUp(mobilePieces, bullets, 0, 0, 50, 50);
//				break;
//			default:
//				powerup = new DamagePowerUp(mobilePieces, bullets, 0, 0, 50, 50);
//			}
//			powerup.moveTo(positions[i][0],positions[i][1]);
//			powerups.add(powerup);
//		}
		
		PowerUp speedBoost = new SpeedBoostPowerUp(mobilePieces, bullets, 0, 0, 50, 50);
		speedBoost.moveTo(positions[0][0], positions[0][1]);
		powerups.add(speedBoost);
		PowerUp slowDown = new SlowDownPowerUp(mobilePieces, bullets, 0, 0, 50, 50);
		slowDown.moveTo(positions[1][0], positions[1][1]);
		powerups.add(slowDown);
		
		setupSoundEffects(marker);
		
	}
	public void setPlayer(ShootingPlayer player) {
		this.player = player;
	}
	public void draw(PApplet marker) {
		//marker.image(marker.loadImage("assets/Backgrounds/forest2.jpg"),0,0,marker.width, marker.height);
		time++;
		objects = new ArrayList<>();
		getObjects().addAll(mobilePieces);
		getObjects().addAll(staticPieces);
		
		
		endPiece.draw(marker);

		
		if(time%shooter1.bulletFrequency == 0) {
			getBullets().add(shooter2.shoot());
			getBullets().add(shooter1.shoot());
			getBullets().add(shooter3.shoot());
			getBullets().add(dropper1.drop(getPlayer().getCenterX(),getPlayer().getCenterY()));
		}
		
		
		if(time%10 == 0) {
			getPlayer().increaseAmmo();
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
					if(suspect != null && suspect == currentMobileEnemy && !inDeathAnimation && player.canDamage)
					displayDamage(marker, (float)getPlayer().getCenterX(), (float)getPlayer().getCenterY(), false);
				}
				else {
					mobileEnemyHitTime = 0;
					growled = false;
					grunted = false;
				}
			}
			
			
			
			if(mobilePieces.get(i).getHealth() <= 0) {
				if(mobilePieces.get(i) instanceof ShootingPlayer) {
					if(!inDeathAnimation) {
						inDeathAnimation = true;
						super.playGameOverSound();
						player.setImages(deathAnimation);
						player.setFrequency(10);
						player.setHealth(0);
					}
					mobilePieces.get(i).draw(marker);
				} else {
					if(mobilePieces.get(i) instanceof MobileEnemy) {
						defeatMobileEnemy(i);
					}
				}
			} else {
				if(!inDeathAnimation) {
					mobilePieces.get(i).act(getObjects());
				}
				mobilePieces.get(i).draw(marker);
			}
		}
		
		
		
		
		for(int i = 0; i < coins.size(); i++) {
			coins.get(i).draw(marker);
			if(coins.get(i).intersects(getPlayer()))
			{
				collectCoin(i);
			}
		}
		
		
		
		for(int i = 0; i < getBullets().size(); i++) {
			Collider bullet = getBullets().get(i);
			if (bullet.getX() <= marker.width && bullet.getX() >= 0 && bullet.getY() <= marker.height && bullet.getY() >= 0 && bullet.getHealth() > 0) {
				bullet.draw(marker);
				if(!inDeathAnimation) {
					if(((Bullet)bullet).getOwner().equals("player")) {
						ArrayList<Collider> nonPlayers = (ArrayList<Collider>)mobilePieces.clone();
						nonPlayers.remove(player);
						bullet.act(nonPlayers);
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
		
		
		
		if(lava.intersects(getPlayer()))
		{
			getPlayer().changeHealth(-1);
		}
		if(inDeathAnimation && deathTime == 2 && time % player.getImgFrequency() == player.getImgFrequency()-1) {
			isDead = true;
			setup(marker);
		}
		if(inDeathAnimation && time % player.getImgFrequency() == 0) {
			deathTime++;
		}
		if(!inDeathAnimation) {
			lava.increaseHeight(getPlayer());
		}
		
		
		if(getPlayer().intersects(endPiece)) {
			inDoorAnimation = true;
			if (!ominous.isPlaying()) {
				ominous.play();
			} 
			if(endPiece.getCenterX() > player.getCenterX())
			{
				endPiece.setImages(doorAnimation2);

			} else {
				endPiece.setImages(doorAnimation1);
			}
			
			
		}
		
		if(inDoorAnimation) doorTime++;
		
		if(inDoorAnimation = true && doorTime == 10)
		setFinished(true);
		
		for(int i = 0; i < powerups.size(); i++) {
			 if (!powerups.get(i).collected) {
				if (powerups.get(i).intersects(player)) {
						powerups.get(i).start();
				}
				powerups.get(i).draw(marker);
			}
			 
			if (powerups.get(i).active) {
				powerups.get(i).drawPowerupEffects(marker, new Point2D.Double(player.getCenterX(), player.getCenterY()));
			} 
			
			
		}
		
		lava.draw(marker);

		displayCelebrations(marker);
		if (player.canDamage) displayHit(marker, bulletHitX, bulletHitY);

		
	}
	public boolean isInAnimation() {
		return inDeathAnimation;
	}
}
