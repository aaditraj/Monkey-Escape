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
	boolean inDeathAnimation;
	int deathTime;
	String[] deathAnimation = new String[]{"assets/Player/Player.png","assets/Player/Player_body.png","assets/Player/Player_head.png","assets/Player/Player_head_dropped.png"};
	private Platform platform1Danger;
	private Platform platform2Danger;

	public void setup(PApplet marker) {
		staticPieces = new ArrayList<>();
		mobilePieces = new ArrayList<>();
		bullets = new ArrayList<>();
		totalPieces = new ArrayList<>();
		objects = new ArrayList<>();
		keysPressed = new boolean[5];
		player = new ShootingPlayer(100,0,900,100d,100d,0,10, 7,90);
		inDeathAnimation = false;
		deathTime = 0;
		platformBottom = new Platform(0,1000,1050,40,false);
		enemy1 = new MobileEnemy(MobileEnemy.mobileEnemyImages,10,525,900,900,900,15,0,100,125);
		coin1 = new Coin(950,950);
		coin2 = new Coin(950, 850);


		platform1 = new Platform(10,800,500,40,false);
		platform1Danger = new Platform(200,800,200,40,true);
		shooter1 = new SideShooter(10,725,60, 88,76, 1);
		coin3 = new Coin(10,650);
		
		
		platform2 = new Platform(650,600,400,40,false);
		platform2Danger = new Platform(740,600,150,40,true);
		shooter2 = new SideShooter(950,526, 20,88,76, 0);
		coin4 = new Coin(950, 475);

		platform3 = new Platform(10,500,500,40,false);
		
		shooter3 = new SideShooter(10,426, 70, 88,76, 1);
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

		mobilePieces.add(getPlayer());
		mobilePieces.add(enemy1);
		mobilePieces.add(enemy2);
		
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
		
		if (platform1Danger.intersects(getPlayer())) {
			playerSpeed = 5;
			platform1Danger.collide(getPlayer());
		} else {
			playerSpeed = 10;
		}
		
		if (platform2Danger.intersects(getPlayer())) {
			playerSpeed = 5;
			platform2Danger.collide(getPlayer());
		} else {
			playerSpeed = 10;
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
					if(suspect != null && suspect == currentMobileEnemy && !inDeathAnimation)
					displayDamage(marker, (float)getPlayer().getCenterX(), (float)getPlayer().getCenterY());
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
					bullet.act((ArrayList<Collider>)mobilePieces);
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
		for(int i = 0; i < coins.size(); i++) {
			coins.get(i).draw(marker);
			if(coins.get(i).intersects(getPlayer()))
			{
				collectCoin(i);
			}
		}
		for(int i = 0; i < staticPieces.size(); i++) {
			staticPieces.get(i).draw(marker);
		}
		if(inDeathAnimation && deathTime == 3 && time % player.getImgFrequency() == player.getImgFrequency()-1) {
			super.playGameOverSound();
			setup(marker);
		}
		if(inDeathAnimation && time % player.getImgFrequency() == 0) {
			deathTime++;
		}
		if(!inDeathAnimation) {
			lava.increaseHeight(getPlayer());
		}
		if(getPlayer().intersects(endPiece))
		{
			setFinished(true); 
		}
		lava.draw(marker);
		endPiece.draw(marker);
		displayCelebrations(marker);
		displayHit(marker, bulletHitX, bulletHitY);

	}
	public boolean isInDeathAnimation() {
		return inDeathAnimation;
	}
}

