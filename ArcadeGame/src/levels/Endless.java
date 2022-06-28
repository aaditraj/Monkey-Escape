package levels;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import core.Bullet;
import core.Collider;
import enemies.MobileEnemy;
import obstacles.Platform;
import players.ShootingPlayer;
import powerups.DamagePowerUp;
import processing.core.PApplet;
import processing.core.PImage;

public class Endless extends Level {
	
	final int platformHeight = 40;	
	final int minPlatformWidth = 100;
	final int platformIncrementFreq = 20000;
	final int maxHorizDist = 260;
	final int maxVerticalDist = 260;
	int minHorizDist;
	int minVerticalDist;
	final int numPlatformIncrements = 5;
	final int platformIncrement =50;
	PImage bg;
	float bulletHitX = 0;
	float bulletHitY = 0;
	boolean isDead; 
	boolean inDeathAnimation;
	boolean onRightSideLeft;
	boolean onRightSideRight;
	int deathTime;
	int doorTime;
	int leftCounter;
	int rightCounter;
	boolean inAnimation;
	ArrayList<Collider> platforms1;
	ArrayList<Collider> platforms2;
	Platform prevPlatformRight;
	Platform prevPlatformLeft;
	String[] doorAnimation = new String[]{"assets/Open Door.png"};
	String[] deathAnimation = new String[]{"assets/Player/PlayerFalling1.png","assets/Player/PlayerFalling2.png","assets/Player/PlayerFalling3.png","assets/Player/PlayerFalling4.png"};
	String[] player2Death = new String[]{"assets/Player/PlayerBlueFalling1.png","assets/Player/PlayerBlueFalling2.png","assets/Player/PlayerBlueFalling3.png","assets/Player/PlayerBlueFalling4.png"};
	Platform border;
	Platform greenPlace;
	Platform bluePlace;
	boolean player2Dead;
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
		leftCounter = 0;
		rightCounter = 0;
		onRightSideLeft = true;
		onRightSideRight = true;
		isDead = false;
		minHorizDist = 150;
		minVerticalDist = 150;
		player2Dead = false;
		player = new ShootingPlayer(200,200,650,100,100,0,10,10,125);
		player2 = new ShootingPlayer(200,200+marker.width/2,650,100,100,0,10,10,125);
		int platform1Width = 300;
		Platform platform1 = new Platform(0, 780, platform1Width, platformHeight, false);
		Platform platform2 = new Platform("assets/Platform/rock-platform.png",marker.width/2, 780, platform1Width, platformHeight, false);
		border = new Platform("assets/Platform/Border.png",marker.width/2-20,0,40,1200,false);
		bluePlace = new Platform("assets/Platform/BlueProgress.png",0,680,marker.width/2,10,false);
		greenPlace = new Platform("assets/Platform/GreenProgress.png",marker.width/2,680,marker.width/2,10,false);
		platforms1.add(platform1);
		platforms2.add(platform2);
		prevPlatformLeft = platform1;
		prevPlatformRight = platform2;
		player.setJumpHeight(-25);
		player2.setJumpHeight(-25);
		player2.setLeft(new String[] {"assets/Player/PlayerBlueLeft.png","assets/Player/PlayerBlueLeft2.png"});
		player2.setRight(new String[] {"assets/Player/PlayerBlueRight.png","assets/Player/PlayerBlueRight2.png"});
		player2.setCurrent(new String[] {"assets/Player/PlayerBlue.png"});
		player2.setImages(new String[] {"assets/Player/PlayerBlue.png"});
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
		mobilePieces.add(getPlayer2());
		//player.setMobile(false);
		//player2.setMobile(false);
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
		getObjects().addAll(platforms1);
		getObjects().addAll(platforms2);
		getObjects().remove(getPlayer());
		getObjects().add(border);
		bluePlace.draw(marker);
		greenPlace.draw(marker);
//		if(time%shooter1.bulletFrequency == 0) {
//			getBullets().add(shooter2.sho
//			getBullets().add(shooter1.shoot());
//			getBullets().add(dropper1.drop(getPlayer().getCenterX(),getPlayer().getCenterY()));
//		}
		if(time%10 == 0) {
			getPlayer().increaseAmmo();
		}
		
		
		boolean[] directions = getPlayer().intersects(getObjects());
		boolean[] directionsPlayer2 = getPlayer2().intersects(getObjects());
		for(int i = 0; i < mobilePieces.size(); i++) {
			
			if(mobilePieces.get(i) instanceof MobileEnemy)
			{
				boolean hit = false;
				MobileEnemy currentMobileEnemy = (MobileEnemy) mobilePieces.get(i);
				MobileEnemy suspect = null;
				MobileEnemy suspect2 = null;
				
				for(int k = 0; k < mobilePieces.size(); k++) {
					if(mobilePieces.get(k).intersects(getPlayer()) && mobilePieces.get(k) instanceof MobileEnemy) 
					{
						suspect = (MobileEnemy) mobilePieces.get(k); 
						hit = true;
					}
					if(mobilePieces.get(k).intersects(getPlayer2()) && mobilePieces.get(k) instanceof MobileEnemy) 
					{
						suspect2 = (MobileEnemy) mobilePieces.get(k); 
						hit = true;
					}
					
					
				}
				
				if(hit) 
				{
					if(suspect != null && suspect == currentMobileEnemy && !inDeathAnimation && player.canDamage) {
						getPlayer().changeHealth(-1);
						displayDamage(marker, (float)getPlayer().getCenterX(), (float)getPlayer().getCenterY(), false);
					}
					if(suspect2 != null && suspect2 == currentMobileEnemy && !inDeathAnimation && player2.canDamage) {
						getPlayer2().changeHealth(-1);
						displayDamage(marker, (float)getPlayer2().getCenterX(), (float)getPlayer2().getCenterY(), false);
					}
				}
				else {
					growled = false;
					mobileEnemyHitTime = 0;
				}
				
				
			}
			if(mobilePieces.get(i).getHealth() <= 0 && !(mobilePieces.get(i) instanceof ShootingPlayer) ) {
				if(mobilePieces.get(i) instanceof MobileEnemy) {
					defeatMobileEnemy(i);
				}
				else {
					mobilePieces.remove(i);
				}
			} else {
				if(!inDeathAnimation) {
					if(!(mobilePieces.get(i) instanceof ShootingPlayer)) {
						mobilePieces.get(i).act(getObjects());
					}
					if (mobilePieces.get(i).getY() > 1040) {
						mobilePieces.remove(i);
					} 
//					else if (mobilePieces.get(i).getY() < -300) {
//						mobilePieces.remove(i);
//					} 
					else {
						mobilePieces.get(i).draw(marker);
					}
				}
			}
		}
		
		
	
//		for(int i = 0; i < getBullets().size(); i++) {
//			Collider bullet = getBullets().get(i);
//			if (bullet.getX() <= marker.width && bullet.getX() >= 0 && bullet.getY() <= marker.height && bullet.getY() >= 0 && bullet.getHealth() > 0) {
//				bullet.draw(marker);
//				if(!inDeathAnimation) {
//					if(((Bullet)bullet).getOwner().equals("player")) {
//						ArrayList<Collider> checkPieces = new ArrayList<Collider>();
//						checkPieces.addAll(mobilePieces);
//						checkPieces.add(border);
//						bullet.act((checkPieces));
//					} else {
//						ArrayList<Collider> playerList = new ArrayList<>();
//						playerList.add(player);
//						bullet.act(playerList);
//					}
//				}
//				if(bullet.getHealth() <= 0) {
//					getBullets().remove(i);
//					
//					if(bullet.intersects(getPlayer()))
//					{
//						Bullet bullet2 = (Bullet)bullet; 
//						bulletHitX = (float)bullet2.getCenterX(); 
//						bulletHitY = (float)bullet2.getCenterY(); 
//						getHit(bullet2.owner); 
//					}
//				}
//			} else {
//				getBullets().remove(i);
//			}
//		}
		getPlayer().updateVelocity();
		getPlayer2().updateVelocity();
		if(!(player.getVY() < 0 && directions[0]) && !(player.getVY() > 0 && directions[2])) {
			bluePlace.moveBy(0, player.getVY() * -1, new ArrayList<>());
			greenPlace.moveBy(0, player.getVY() * 1, new ArrayList<>());
		}
		if(!(player2.getVY() < 0 && directionsPlayer2[0]) && !(player2.getVY() > 0 && directionsPlayer2[2])) {
			bluePlace.moveBy(0, player2.getVY() * 1, new ArrayList<>());
			greenPlace.moveBy(0, player2.getVY() * -1, new ArrayList<>());
		}
		//boolean[] directions = getPlayer().intersects(getObjects());
		int platforms1MinHeight = -Integer.MAX_VALUE;
		ArrayList<Collider> removeList = new ArrayList<>();
		for(int i = 0; i < platforms1.size(); i++) {
			/*for(int j = 0; j < platforms1.size(); j++) {
				if(platforms1.get(j).intersects(player)) {
					System.out.println(platforms1.get(j));
					System.out.println(platforms1);
					System.out.println(i);
				}
			}*/
			if(!(player.getVY() < 0 && directions[0]) && !(player.getVY() > 0 && directions[2])) {
				ArrayList<Collider> playerList = new ArrayList<Collider>();
				playerList.add(player);
				platforms1.get(i).moveBy(0, player.getVY() * -1, playerList);
			}
			if (platforms1.get(i).getY() > 1090) {
				platforms1.remove(i);
				addPlatform(marker,platforms1,true);
			} else {
				platforms1.get(i).draw(marker);
			}
			if(platforms1.get(i).getY() > platforms1MinHeight) {
				platforms1MinHeight = (int)platforms1.get(i).getY();
			}
		}
		
		if((platforms1MinHeight < -300 || player.getHealth() <= 0)&& !inAnimation) {
			inAnimation = true;
			player.setImages(deathAnimation);
			player.setFrequency(15);
		}
		
		int platforms2MinHeight = -Integer.MAX_VALUE;
		
		for(int i = 0; i < platforms2.size(); i++) {
			/*for(int j = 0; j < platforms2.size(); j++) {
				if(platforms2.get(j).intersects(player)) {
					System.out.println(platforms2.get(j));
					System.out.println(platforms2);
					System.out.println(i);
				}
			}*/
			if(!(player2.getVY() < 0 && directionsPlayer2[0]) && !(player2.getVY() > 0 && directionsPlayer2[2])) {
				ArrayList<Collider> playerList = new ArrayList<Collider>();
				playerList.add(player2);
				platforms2.get(i).moveBy(0, player2.getVY() * -1, playerList);
			}
			if (platforms2.get(i).getY() > 1090) {
				platforms2.remove(i);
				addPlatform(marker,platforms2,false);
			} else {
				platforms2.get(i).draw(marker);
			}
			if(platforms2.get(i).getY() > platforms2MinHeight) {
				platforms2MinHeight = (int)platforms2.get(i).getY();
			}
		}
		if((platforms2MinHeight < -300 || player2.getHealth() <= 0)&& !inAnimation) {
			inAnimation = true;
			player2Dead = true;
			player2.setImages(player2Death);
			player2.setFrequency(15);
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
		border.draw(marker);
		if(player2Dead) {
			if(inAnimation && deathTime == 3 && time % player2.getImgFrequency() == player2.getImgFrequency()-1) {
				super.playGameOverSound();
				isDead = true;
				setup(marker);
			}
			if(inAnimation && time % player2.getImgFrequency() == 0) {
				deathTime++;
			}
			if (inAnimation) {
				endMessage(marker, "PLAYER 1");
			}
		} else {
			if(inAnimation && deathTime == 3 && time % player.getImgFrequency() == player.getImgFrequency()-1) {
				super.playGameOverSound();
				isDead = true;
				setup(marker);
			}
			if(inAnimation && time % player.getImgFrequency() == 0) {
				deathTime++;
			}
			if (inAnimation) {
				endMessage(marker, "PLAYER 2");
			}
		}
		if(player.getY() > bluePlace.getY()) {
			player.changeHealth(-0.1);
		}
		if(player2.getY() > greenPlace.getY()) {
			player2.changeHealth(-0.1);
		}
		displayCelebrations(marker);
		if (player.canDamage) displayHit(marker, bulletHitX, bulletHitY);
		


	}
	
	private void addPlatform(PApplet marker, ArrayList<Collider> platforms,boolean left) {
		// TODO Auto-generated method stub
		Platform prevPlatform;
		if(left) {
			prevPlatform = prevPlatformLeft;
		} else {
			prevPlatform = prevPlatformRight;
		}
		
//		int maxXDist = (time/platformIncrementFreq >= numPlatformIncrements ? maxHorizDist : time/platformIncrementFreq * platformIncrement + 100);
		int xDist = (int) (Math.random() * (maxHorizDist-minHorizDist)) + minHorizDist; 
//		int maxYDist = (time/platformIncrementFreq >= numPlatformIncrements ? maxVerticalDist : time/platformIncrementFreq * platformIncrement + 100);
		int yDist = (int) (Math.random() * (maxVerticalDist-minVerticalDist)) + minVerticalDist;
		Platform p;
		double x, width;
		if ((onRightSideLeft && left) || (onRightSideRight && !left)) {
			int maxWidth = (int) (marker.width/2 - (prevPlatform.getX() +prevPlatform.getWidth() + xDist));
			if(left) {
				maxWidth = (int) (marker.width/2 - (prevPlatform.getX() +prevPlatform.getWidth() + xDist));
			} else {
				maxWidth = (int) (marker.width - (prevPlatform.getX() +prevPlatform.getWidth() + xDist));
			}
			if (maxWidth < 100) {
				xDist = (int) (xDist -(100 - maxWidth));
				maxWidth = 100;
			}
			x = prevPlatform.getX() + prevPlatform.getWidth() + xDist;
			width = (Math.random() * (maxWidth - minPlatformWidth)) + minPlatformWidth;
			width = ((int)width/100 * 100);
			if (width > 300) {
				width = 300;
			}
			System.out.println(marker.displayWidth + " " + x + " " + " " + xDist + " " + yDist);
			if(left) {
				p = new Platform(x, prevPlatform.getY() - platformHeight - yDist, width, platformHeight, false);
			} else {
				System.out.println("JJJJJJJJJJJJJJJJJJJJJJJJJJJ");
				p = new Platform("assets/Platform/rock-platform.png",x, prevPlatform.getY() - platformHeight - yDist, width, platformHeight, false);
			}
			
		} else {
			
			
			x = prevPlatform.getX() - xDist;
			System.out.println(x + " " + prevPlatform.getX() + " reduction");
			width = (Math.random() * (300 - minPlatformWidth)) + minPlatformWidth;
			width = ((int)width/100 * 100);
			if(left) {
				if(x-width < 0) {
					x = width;
				}
			} else {
				if(x-width < marker.width/2) {
					x = marker.width/2+width;
				}
			}
			
			if (width > 300) {
				width = 300;
			}
			x = x - width;
			if(left) {
				p = new Platform(x, prevPlatform.getY() - platformHeight - yDist, width, platformHeight, false);
			} else {
				System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHH");
				p = new Platform("assets/Platform/rock-platform.png",x, prevPlatform.getY() - platformHeight - yDist, width, platformHeight, false);
			}
		}
		
//		int counter = 0;
	
		if(left) {
//			counter = leftCounter;
//			leftCounter++;
		 	onRightSideLeft = !onRightSideLeft;
		 	prevPlatformLeft = p;
		} else {
//			counter = rightCounter;
//			rightCounter++;
			onRightSideRight = !onRightSideRight;
			System.out.println(onRightSideRight);
			prevPlatformRight = p;
		}
		
//		if (counter % 3 == 0) {
			MobileEnemy enemy;
			if (p.getWidth() == 100.0) {
				enemy = new MobileEnemy(MobileEnemy.mobileEnemyImages, 10, p, 1, 0, 36, 45);
			} else {
				enemy = new MobileEnemy(MobileEnemy.mobileEnemyImages, 10, p, 5, 0, 72, 90);
			}
			mobilePieces.add(enemy);
//		}
		
		
		if(minHorizDist < maxHorizDist) {
			minHorizDist += 10;
		}
		if(minVerticalDist < maxVerticalDist) {
			minVerticalDist += 10;
		}
		//minVerticalDist += 10;
		platforms.add(p);
	}

	public boolean isInAnimation() {
		return inAnimation;
	}

}
