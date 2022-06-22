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
	float bulletHitX = 0;
	float bulletHitY = 0;
	private ShootingEnemy dropper;
	boolean inAnimation;
	boolean speedReduced = false;
	int deathTime;
	int successTime;
	String[] deathAnimation = new String[]{"assets/Player/Player.png","assets/Player/Player_body.png","assets/Player/Player_head.png","assets/Player/Player_head_dropped.png"};
	String[] successAnimation = new String[] {"assets/Player/Success1.png","assets/Player/Success2.png","assets/Player/Success3.png","assets/Player/Success4.png"};
	int animationType = 0;
	public final static int deathAnim = 1;
	public final static int successAnim = 2;
	private Platform platform1Danger;
	private Platform platform2Danger;
	
	public void setup(PApplet marker) {
		staticPieces = new ArrayList<>();
		mobilePieces = new ArrayList<>();
		bullets = new ArrayList<>();
		totalPieces = new ArrayList<>();
		objects = new ArrayList<>();
		keysPressed = new boolean[5];
		player = new ShootingPlayer(110,0,900,100d,100d,0,10, 7,90);
		inAnimation = false;
		deathTime = 0;
		platformBottom = new Platform(0,1000,1050,40,false);
		enemy1 = new MobileEnemy(MobileEnemy.mobileEnemyImages,10,525,900,900,900,15,0,100,125);
		coin1 = new Coin(950,950);
		coin2 = new Coin(950, 850);


		platform1 = new Platform(10,780,500,40,false);
		platform1Danger = new Platform(200,780,200,40,true);
		shooter1 = new SideShooter(10,705,60, 88,76, 1);
		coin3 = new Coin(10,630);
		
		
		platform2 = new Platform(650,600,500,40,false);
		platform2Danger = new Platform(740,600,150,40,true);
		shooter2 = new SideShooter(950,526, 20,88,76, 0);
		coin4 = new Coin(950, 475);

		platform3 = new Platform(10,500,500,40,false);
		
		shooter3 = new SideShooter(10,426, 70, 88,76, 1);
		coin5 = new Coin(90, 10);
		platform4 = new Platform(0,300,100,40,false);
		platform5 = new Platform(850,250,200,40,false);
		platform6 = new Platform(275, 200, 500, 40, false);
		platformTop = new Platform(0, 60, 100, 20, false);

		enemy2 = new MobileEnemy(MobileEnemy.mobileEnemyImages, 10d, 275d, 100d, 600d, 100d, 15d, 0d,424d/4, 464d/4);
		dropper = new ShootingEnemy(1500, 0, 0, 75, 75);
		coin6 = new Coin(435, 100);
		
		lava = new Lava(10, 0, 1050, 2000, 50, 0.2);
		
		endPiece = new Collider(new String[] {"assets/Player/KidTarzan.png"},275,990,170,80,80,0,0);


		player.playerSpeed = 10;
		staticPieces.add(platform2);
		staticPieces.add(platform1);
		staticPieces.add(platform3);
		staticPieces.add(platform1Danger);
		staticPieces.add(platform2Danger);
		staticPieces.add(platformBottom);
		staticPieces.add(platform5);
		staticPieces.add(platform6);
		staticPieces.add(platform4);
		coins.clear();
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

		mobilePieces.add(getPlayer());
		mobilePieces.add(enemy1);
		mobilePieces.add(enemy2);
		
		int[][] positions = new int[2][2];
		positions[0] = new int[]{260, 440};
		positions[1] = new int[]{900, 965};
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
		PowerUp invincibility = new InvincibilityPowerUp(mobilePieces, bullets, 0, 0, 50, 50);
		invincibility.moveTo(positions[0][0], positions[0][1]);
		powerups.add(invincibility);
		PowerUp damageUp = new DamagePowerUp(mobilePieces, bullets, 0, 0, 50, 50);
		damageUp.moveTo(positions[1][0], positions[1][1]);
		powerups.add(damageUp);
		
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
		getObjects().remove(coin1);
		getObjects().remove(coin2);
		
		if(time%shooter1.bulletFrequency == 0) {
			getBullets().add(shooter2.shoot());
			getBullets().add(shooter1.shoot());
			getBullets().add(shooter3.shoot());
			getBullets().add(dropper.drop(getPlayer().getCenterX(), getPlayer().getCenterY()));
			

		}
		if(time%10 == 0) {
			getPlayer().increaseAmmo();
		}
		
		if (platform1Danger.intersects(getPlayer()) || platform2Danger.intersects(getPlayer())) {
			if(!speedReduced) {
				player.playerSpeed = player.playerSpeed/2;
				speedReduced = true;
			}
			platform1Danger.collide(getPlayer());
			displayDamage(marker, (float) getPlayer().getCenterX(), (float) getPlayer().getCenterY(), true);
		} else if(speedReduced) {
			player.playerSpeed *= 2;
			speedReduced = false;
		}
		
		if (!platform1Danger.intersects(getPlayer()) && !platform2Danger.intersects(getPlayer())) {
			lavaHitTime = 0;
		}
		
		for(int i = 0; i < coins.size(); i++) {
			coins.get(i).draw(marker);
			if(coins.get(i).intersects(getPlayer()))
			{
				collectCoin(i);
			}
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
					if(suspect != null && suspect == currentMobileEnemy && !inAnimation && player.canDamage)
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
					if(!inAnimation) {
						inAnimation = true;
						animationType = deathAnim;
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
				if(!inAnimation) {
					mobilePieces.get(i).act(getObjects());
				}
				mobilePieces.get(i).draw(marker);
			}
		}
		for(int i = 0; i < getBullets().size(); i++) {
			Collider bullet = getBullets().get(i);
			if (bullet.getX() <= marker.width && bullet.getX() >= 0 && bullet.getY() <= marker.height && bullet.getY() >= 0 && bullet.getHealth() > 0) {
				bullet.draw(marker);
				if(!inAnimation) {
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
		
		endPiece.draw(marker);

	
		
		if(lava.intersects(getPlayer()))
		{
			getPlayer().changeHealth(-1);
		}
		
		if(inAnimation && deathTime == 3 && animationType == deathAnim) {
			super.playGameOverSound();
			isDead = true;
			setup(marker);
			
		}
		if(inAnimation && successTime == 3 && animationType == successAnim) {
			if (!success.isPlaying()) {
				success.play();
			} 
			setFinished(true); 
		}
		if(inAnimation && time % player.getImgFrequency() == 0) {
			if(animationType == deathAnim) {
				deathTime++;
			}
			if(animationType == successAnim) {
				successTime++;
			}
		}
		if(!inAnimation) {
			lava.increaseHeight(getPlayer());
		}
		if(getPlayer().intersects(endPiece))
		{	
			inAnimation = true;
			animationType = successAnim;
			player.setImages(successAnimation);
			player.setFrequency(10);
		}
		for(int i = 0; i < powerups.size(); i++) {
			 if (!powerups.get(i).collected) {
				if (powerups.get(i).intersects(player)) {
						powerups.get(i).start();
				}
				powerups.get(i).draw(marker);
			}
			if (powerups.get(0).active) {
				powerups.get(0).drawPowerupEffects(marker, new Point2D.Double(player.getCenterX(), player.getCenterY()));
			}
//			
			if (powerups.get(1).active) {
				powerups.get(1).drawPowerupEffects(marker, new Point2D.Double(player.getCenterX(), player.getCenterY()));
			}
		}
		
		lava.draw(marker);
		displayCelebrations(marker);
		if (player.canDamage) displayHit(marker, bulletHitX, bulletHitY);

	}
	public boolean isInAnimation() {
		return inAnimation;
	}
}

