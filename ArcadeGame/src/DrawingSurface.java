	
import java.util.ArrayList;

import processing.core.PApplet;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;


public class DrawingSurface extends PApplet implements Serializable{

	private Leaderboard leaderboard;
	private BufferedWriter writer;
	private FileWriter fileWriter; 
	private Collider collider1;
	private MobileEnemy mobileEnemy;
	private ShootingPlayer player;
	private Lava lava;
	private Barrel barrel; 
	private MobileEnemy enemy1;
	private ShootingEnemy enemy2;
	private SideShooter sideShooter;
	private Boolean tester = false;
	ArrayList<Collider> bullets = new ArrayList<>();
	ArrayList<Collider> gamePieces = new ArrayList<Collider>();
	int playerSpeed = 5;
	public DrawingSurface() {
		player = new ShootingPlayer(100,100,100,100d,100d,0,5,3000);
		lava = new Lava(0, 0, 300, 650, 100, 0.1);
		barrel = new Barrel(10,0,10,50,50,0,0);
		leaderboard = new Leaderboard(this);
		mobileEnemy = new MobileEnemy(10d, 400d, 150d, 0d, 150d, -5d, 0d,(int)424d/4, (int)464d/4, 1);
		gamePieces.add(player);
		gamePieces.add(barrel);
		gamePieces.add(mobileEnemy);
		try {
			fileWriter = new FileWriter("data/HighScores.txt", true);
			writer = new BufferedWriter(fileWriter);
		} catch (IOException ioe) {
            ioe.printStackTrace();
        }
		
		

		

	}
	
	
	public void setup() {
	}
	
	
	public void draw() {
	
		background(50);
		
		leaderboard.draw();
		
		//System.out.println(mobileEnemy.getHealth());
		for (int i = 0; i < bullets.size(); i++) {
			Collider bullet = bullets.get(i);
			if (bullet.getX() <= width && bullet.getX() >= 0 && bullet.getY() <= height && bullet.getY() >= 0 && bullet.getHealth() > 0) {
				bullet.draw(this);
				bullet.act((ArrayList<Collider>)gamePieces);
				if(bullet.getHealth() <= 0) {
					bullets.remove(i);
				}
			} else {
				bullets.remove(i);
			}
		}
//		System.out.println(bullets);	
		
		for (int i = 0; i < gamePieces.size(); i++) {
			if (gamePieces.get(i).getHealth() <= 0) {
				gamePieces.remove(i);
			} else {
				gamePieces.get(i).draw(this);
				//if (gamePieces.get(i) instanceof MobileEnemy) {
				gamePieces.get(i).act(gamePieces);
				//}
			}
		}
//		System.out.println(gamePieces);

		if(mobileEnemy.getHealth() <= 0 && !tester)
		{
			promptLeaderboard(); 
			tester = true;
//			mobileEnemy.changeH ealth(20);
		}
		
	}
	
	public void promptLeaderboard()
	{
		try {
			fileWriter.append("1 ");
			fileWriter.close();
		 } catch (IOException ioe) {
	            ioe.printStackTrace();
	        }
		
		leaderboard.readData();
	}
	
	public void mousePressed() {
		Bullet b = player.shoot(mouseX, mouseY);
		bullets.add(b);
	}		
	
	public void keyPressed() {
		if (keyCode == UP) { 
			player.moveBy(0, -playerSpeed, gamePieces);
			
		} 
		if (keyCode == DOWN) { 
			player.moveBy(0, playerSpeed, gamePieces);
		}
		if (keyCode == RIGHT) {
			player.moveBy(playerSpeed, 0, gamePieces);

		}
		if (keyCode == LEFT) {
			player.moveBy(-playerSpeed, 0, gamePieces);

		}
		if (key == 'w') {
			player.moveBy(0, -playerSpeed, gamePieces);

		} 
		if (key == 'd') {
			player.moveBy(playerSpeed, 0, gamePieces);

		}
		if (key == 's') {
			player.moveBy(0, playerSpeed, gamePieces);
		
		}
		if (key == 'a') {
			player.moveBy(-playerSpeed, 0, gamePieces);
	
		}
		if(key == ' ') {
			player.jump();
		}
	}


}
