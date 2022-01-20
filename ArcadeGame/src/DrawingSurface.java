	
import java.util.ArrayList;

import processing.core.PApplet;




public class DrawingSurface extends PApplet {

	private Leaderboard leaderboard;

	private Collider collider1;
	private Collider collider2;
	private ShootingPlayer player;
	private Lava lava;
	private Barrel barrel; 
	private MobileEnemy enemy1;
	private ShootingEnemy enemy2;
	private SideShooter sideShooter;
	ArrayList<Collider> bullets = new ArrayList<>();
	ArrayList<Collider> gamePieces = new ArrayList<Collider>();
	int playerSpeed = 5;
	public DrawingSurface() {
		player = new ShootingPlayer(100,100,100,150d,100d,0,0,3000);
		lava = new Lava(0, 0, 300, 650, 100, 0.1);
		barrel = new Barrel(0,0,10,50,50,0,0);
		leaderboard = new Leaderboard(this);
		//collider1 = new MobileEnemy(10d, 0d, 150d, 400d, 150d, 20d, 20d, 5d, 0d, 1);
		//collider2 = new MobileEnemy(10d, 400d, 150d, 0d, 150d, 20d, 20d, -5d, 0d, 1);
		//collider1 = new Collider(new String[] {"assets/MobileEnemy/gorilla-2.png"}, 10d, 0d, 151d, (int)404d/4, (int)434d/4, 2d, 0d);
		collider2 = new Collider(new String[] {"assets/MobileEnemy/gorilla.png"}, 100, 400, 150, (int)424d/4, (int)464d/4, 0d, 0d);
		gamePieces.add(player);
		gamePieces.add(barrel);
		gamePieces.add(collider2);

	}
	
	
	public void setup() {
	}
	
	
	public void draw() {
	
		
		for (int i = 0; i < bullets.size(); i++) {
			Collider bullet = bullets.get(i);
			if (bullet.getX() <= width && bullet.getX() >= 0 && bullet.getY() <= height && bullet.getY() >= 0) {
				bullet.draw(this);
				bullet.act((ArrayList<Collider>)bullets);
			} else {
				bullets.remove(i);
			}
		}
		System.out.println(bullets);	
		
		for (int i = 0; i < gamePieces.size(); i++) {
			if (gamePieces.get(i).getHealth() == 0) {
				gamePieces.remove(i);
				if (gamePieces.get(i) instanceof Bullet) {
					bullets.remove(i);
				}
			} else {
				gamePieces.get(i).draw(this);
			}
		}

		
		
	}
	
	
	public void mousePressed() {
		Bullet b = player.shoot(mouseX, mouseY);
		bullets.add(b);
		gamePieces.add(b);
	}		
	
	public void keyPressed() {
		if (keyCode == UP) { 
			player.moveBy(0, -playerSpeed, bullets);
			
		} 
		if (keyCode == DOWN) { 
			player.moveBy(0, playerSpeed, bullets);
		}
		if (keyCode == RIGHT) {
			player.moveBy(playerSpeed, 0, bullets);

		}
		if (keyCode == LEFT) {
			player.moveBy(-playerSpeed, 0, bullets);

		}
		if (key == 'w') {
			player.moveBy(0, -playerSpeed, bullets);

		} 
		if (key == 'd') {
			player.moveBy(playerSpeed, 0, bullets);

		}
		if (key == 's') {
			player.moveBy(0, playerSpeed, bullets);
		
		}
		if (key == 'a') {
			player.moveBy(-playerSpeed, 0, bullets);
	
		}
		//etc.
	}


}
