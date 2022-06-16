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
import powerups.PowerUp;
import powerups.SlowDownPowerUp;
import processing.core.PApplet;
import processing.core.PImage;

public class Endless extends Level {
	

	Lava lava;
	PImage bg;
	float bulletHitX = 0;
	float bulletHitY = 0;
	boolean isDead; 
	boolean inDeathAnimation;
	int deathTime;
	int doorTime;
	String[] doorAnimation = new String[]{"assets/Open Door.png"};
	String[] deathAnimation = new String[]{"assets/Player/Player.png","assets/Player/Player_body.png","assets/Player/Player_head.png","assets/Player/Player_head_dropped.png"};
	public void setup(PApplet marker) {
		// TODO Auto-generated method stub
		staticPieces = new ArrayList<>();
		mobilePieces = new ArrayList<>();
		bullets = new ArrayList<>();
		totalPieces = new ArrayList<>();
		objects = new ArrayList<>();
		inDeathAnimation = false;
		deathTime = 0;
		
		
		player = new ShootingPlayer(50,200,700,100,100,0,10,10,125);
		
		powerups = new ArrayList<>();
		
	
		//platform6 = new Platform(10,900,100,40,false);
		lava = new Lava(10, 0, 950, 2000, 100, 0.1);

		player.playerSpeed = 10;
//		staticPieces.add(platform1);
//		staticPieces.add(platform2);
//		staticPieces.add(platform3);
//		staticPieces.add(platform4);
//		staticPieces.add(platform5);
//		staticPieces.add(shooter1);
//		staticPieces.add(shooter2);
//		staticPieces.add(dropper1);
//		mobilePieces.add(getPlayer());
//		mobilePieces.add(enemy1);
//		mobilePieces.add(enemy2);
		int[][] positions = new int[2][2];
		positions[0] = new int[]{1200,640};
		positions[1] = new int[]{110,440};
		for(int i = 0; i < 2; i++) {
			int random = (int)(Math.random() * 2); // TODO this is the powerup to test, can change arguments as needed
			PowerUp powerup;
			switch (random) {
			case 0:
				powerup = new DamagePowerUp(mobilePieces, bullets, 0, 0, 50, 50);
				break;
			case 1:
				powerup = new SlowDownPowerUp(mobilePieces, bullets, 0, 0, 50, 50);
				break;

			default:
				powerup = new DamagePowerUp(mobilePieces, bullets, 0, 0, 50, 50);
			}
			powerup.moveTo(positions[i][0],positions[i][1]);
			powerups.add(powerup);
		}

		setupSoundEffects(marker);
	}
	
	public void setPlayer(ShootingPlayer player) {
		this.player = player;
	}
	
	public void draw(PApplet marker) {
		time++;
		objects = new ArrayList<>();
		getObjects().addAll(mobilePieces);
		getObjects().addAll(staticPieces);
//		if(time%shooter1.bulletFrequency == 0) {
//			getBullets().add(shooter2.shoot());
//			getBullets().add(shooter1.shoot());
//			getBullets().add(dropper1.drop(getPlayer().getCenterX(),getPlayer().getCenterY()));
//		}
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
					if(suspect != null && suspect == currentMobileEnemy && !inDeathAnimation && player.canDamage) {
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
		for(int i = 0; i < powerups.size(); i++) {
			 if (!powerups.get(i).collected) {
				if (powerups.get(i).intersects(player)) {
						if(powerups.get(i) instanceof DamagePowerUp)
						{
							player.damageUp = true;
						}
						powerups.get(i).start();
				}
				powerups.get(i).draw(marker);
			}
			if (powerups.get(i).active) {
				powerups.get(i).drawPowerupEffects(marker, new Point2D.Double(player.getCenterX(), player.getCenterY()));
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

		displayCelebrations(marker);
		if (player.canDamage) displayHit(marker, bulletHitX, bulletHitY);

	}
	
	public boolean isInDeathAnimation() {
		return inDeathAnimation;
	}

}
