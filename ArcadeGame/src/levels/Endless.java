package levels;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

import javax.management.timer.Timer;

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
	
	final int platformHeight = 40;	
	final int minPlatformWidth = 100;
	final int platformIncrementFreq = 20000;
	final int maxHorizDist = 300;
	final int maxVerticalDist = 300;
	final int minHorizDist = 300;
	final int minVerticalDist = 300;
	final int numPlatformIncrements = 5;
	final int platformIncrement = 25;
	PImage bg;
	float bulletHitX = 0;
	float bulletHitY = 0;
	boolean isDead; 
	boolean inDeathAnimation;
	boolean onRightSide;
	int deathTime;
	int doorTime;
	boolean inAnimation;
	ArrayList<Collider> platforms1;
	ArrayList<Collider> platforms2;
	String[] doorAnimation = new String[]{"assets/Open Door.png"};
	String[] deathAnimation = new String[]{"assets/Player/PlayerFalling1.png","assets/Player/PlayerFalling2.png","assets/Player/PlayerFalling3.png","assets/Player/PlayerFalling4.png"};
	
	public void setup(PApplet marker) {
		// TODO Auto-generated method stub
		platforms1 = new ArrayList<>();
		platforms2 = new ArrayList<>();
		mobilePieces = new ArrayList<>();
		bullets = new ArrayList<>();
		totalPieces = new ArrayList<>();
		objects = new ArrayList<>();
		inAnimation = false;
		deathTime = 0;
		onRightSide = false;
		isDead = false;
		
		player = new ShootingPlayer(50,200,650,100,100,0,10,10,125);
		int platform1Width = 300;
		Platform platform1 = new Platform(0, 780, platform1Width, platformHeight, false);
		Platform platform2 = new Platform(marker.width/2, 780, platform1Width, platformHeight, false);
		platforms1.add(platform1);
		platforms2.add(platform2);
		onRightSide = !onRightSide;
		player.setJumpHeight(-25);
		
		while(platforms1.get(platforms1.size() - 1).getY() > 0) {
			addPlatform(marker,platforms1,true);
		}
		while(platforms2.get(platforms2.size() - 1).getY() > 0) {
			addPlatform(marker,platforms2,false);
		}
		
		
		
//		powerups = new ArrayList<>();
		
	
		//platform6 = new Platform(10,900,100,40,false);

		player.playerSpeed = 10;

//		platforms.add(shooter1);
//		platforms.add(shooter2);
//		platforms.add(dropper1);
		mobilePieces.add(getPlayer());
//		mobilePieces.add(enemy1);
//		mobilePieces.add(enemy2);
//		int[][] positions = new int[2][2];
//		positions[0] = new int[]{1200,640};
//		positions[1] = new int[]{110,440};
//		for(int i = 0; i < 2; i++) {
//			int random = (int)(Math.random() * 2); // TODO this is the powerup to test, can change arguments as needed
//			PowerUp powerup;
//			switch (random) {
//			case 0:
//				powerup = new DamagePowerUp(mobilePieces, bullets, 0, 0, 50, 50);
//				break;
//			case 1:
//				powerup = new SlowDownPowerUp(mobilePieces, bullets, 0, 0, 50, 50);
//				break;
//
//			default:
//				powerup = new DamagePowerUp(mobilePieces, bullets, 0, 0, 50, 50);
//			}
//			powerup.moveTo(positions[i][0],positions[i][1]);
//			powerups.add(powerup);
//		}

		setupSoundEffects(marker);
	}
	
	public void setPlayer(ShootingPlayer player) {
		this.player = player;
	}
	
	public void draw(PApplet marker) {
		time++;
		objects = new ArrayList<>();
		getObjects().clear();
		getObjects().addAll(mobilePieces);
		getObjects().addAll(platforms1);
		getObjects().addAll(platforms2);
		getObjects().remove(getPlayer());
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
					if(!(mobilePieces.get(i) instanceof ShootingPlayer)) {
						mobilePieces.get(i).act(getObjects());
					}
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
		getPlayer().updateVelocity();
		boolean[] directions = getPlayer().intersects(getObjects());
		ArrayList<Collider> removeList = new ArrayList<>();
		for(int i = 0; i < platforms1.size(); i++) {
			for(int j = 0; j < platforms1.size(); j++) {
				if(platforms1.get(j).intersects(player)) {
					System.out.println(platforms1.get(j));
					System.out.println(platforms1);
					System.out.println(i);
				}
			}
			if(!(player.getVY() < 0 && directions[0]) && !(player.getVY() > 0 && directions[2])) {
				ArrayList<Collider> playerList = new ArrayList<Collider>();
				playerList.add(player);
				platforms1.get(i).moveBy(0, player.getVY() * -1, playerList);
			}
			if (platforms1.get(i).getY() > 1040) {
				platforms1.remove(i);
				addPlatform(marker,platforms1,true);
			} else if (platforms1.get(i).getY() < -300) {
				platforms1.remove(i);
			} else {
				platforms1.get(i).draw(marker);
			}
		}
		
		if(platforms1.size() == 0 && !inAnimation) {
			inAnimation = true;
			player.setImages(deathAnimation);
			player.setFrequency(10);
		}
		for(int i = 0; i < platforms2.size(); i++) {
			for(int j = 0; j < platforms2.size(); j++) {
				if(platforms2.get(j).intersects(player)) {
					System.out.println(platforms2.get(j));
					System.out.println(platforms2);
					System.out.println(i);
				}
			}
			if(!(player.getVY() < 0 && directions[0]) && !(player.getVY() > 0 && directions[2])) {
				ArrayList<Collider> playerList = new ArrayList<Collider>();
				playerList.add(player);
				platforms2.get(i).moveBy(0, player.getVY() * -1, playerList);
			}
			if (platforms2.get(i).getY() > 1040) {
				platforms2.remove(i);
				addPlatform(marker,platforms2,false);
			} else if (platforms2.get(i).getY() < -300) {
				platforms2.remove(i);
			} else {
				platforms2.get(i).draw(marker);
			}
		}
		if(platforms2.size() == 0 && !inAnimation) {
			inAnimation = true;
			player.setImages(deathAnimation);
			player.setFrequency(10);
		}
//		for(int i = 0; i < coins.size(); i++) {
//			coins.get(i).draw(marker);
//			if(coins.get(i).intersects(getPlayer()))
//			{
//				collectCoin(i);
//			}
//		}
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

	
		if(inAnimation && deathTime == 3 && time % player.getImgFrequency() == player.getImgFrequency()-1) {
			super.playGameOverSound();
			isDead = true;
			setup(marker);
		}
		if(inAnimation && time % player.getImgFrequency() == 0) {
			deathTime++;
		}

		displayCelebrations(marker);
		if (player.canDamage) displayHit(marker, bulletHitX, bulletHitY);
		


	}
	
	private void addPlatform(PApplet marker, ArrayList<Collider> platforms, boolean left) {
		// TODO Auto-generated method stub
		Platform prevPlatform = (Platform) (platforms.get(platforms.size() - 1));
		int maxXDist = (time/platformIncrementFreq >= numPlatformIncrements ? maxHorizDist : time/platformIncrementFreq * platformIncrement + 100);
		int xDist = (int) (Math.random() * platformIncrement * 2 + 0.5) + maxXDist; 
		int maxYDist = (time/platformIncrementFreq >= numPlatformIncrements ? maxVerticalDist : time/platformIncrementFreq * platformIncrement + 100);
		int yDist = (int) (Math.random() * platformIncrement * 2 + 0.5) + maxYDist;
		Platform p;
		double x, width;
		if (onRightSide) {
			 
			
			x = prevPlatform.getX() + prevPlatform.getWidth() + xDist;
			width = (int)(Math.random() * (marker.width/2 - (x + minPlatformWidth)) + 0.5) + minPlatformWidth;
			width = ((int)width/100 * 100);
			if (width > 600) {
				width = 600;
			}
			if(left) {
				p = new Platform(x, prevPlatform.getY() - platformHeight - yDist, width, platformHeight, false);
			} else {
				System.out.println(marker.width/2 + " HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHhh");
				p = new Platform(x+marker.width/2, prevPlatform.getY() - platformHeight - yDist, width, platformHeight, false);
			}
			
		} else {
			
			
			x = prevPlatform.getX() - xDist;
			width = (int)(Math.random() * x + 0.5) + minPlatformWidth;
			width = ((int)width/100 * 100);
			if (width > 600) {
				width = 600;
			}
			x = x - width;
			p = new Platform(x, prevPlatform.getY() - platformHeight - yDist, width, platformHeight, false);
		}
		onRightSide = !onRightSide;
		platforms.add(p);
	}

	public boolean isInAnimation() {
		return inAnimation;
	}

}
