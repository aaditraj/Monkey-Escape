package Levels;
import java.util.ArrayList;

import core.Bullet;
import core.ClickThrough;
import core.Collider;
import enemies.MobileEnemy;
import enemies.ShootingEnemy;
import enemies.SideShooter;
import obstacles.Lava;
import obstacles.Platform;
import players.ShootingPlayer;
import powerups.Coin;
import processing.core.PApplet;
import startpage.MainMenu;
import startpage.StartPage;
public class LevelSwitch extends PApplet{
	Level level;
	StartPage start; 
	MainMenu menu; 
	int points = 0;
	ClickThrough clickThrough = new ClickThrough("demoSet");
	String gameStatus = "Not Started";
	public void setup() {
		start = new StartPage(); 
		menu = new MainMenu(); 
		level = new Level1();
		((Level1)level).setup();
	}
	public void draw() {
		if(gameStatus.equals("Started")) {
			background(50);
			move();
			level.draw(this);
			if(level.isFinished == true) {
				if(level instanceof Level1) {
					points = points + level.player.points;
					level = new Level2();
					((Level2) level).setup();
					
				} else if (level instanceof Level2) {
					points = points + level.player.points;
					level = new Level3();
					((Level3) level).setup();
				} else if (level instanceof Level3) {
					gameStatus = "Finished";
				}
			}

		} else if (gameStatus.equals("Not Started")) {
			start.draw(this); 

		} else if (gameStatus.equals("In Clickthrough")) {
			clickThrough.draw(this);
			if(clickThrough.isFinished) {
				gameStatus = "Not Started";
			}
		} else if (gameStatus.equals("Finished"))  {
			
		} else if (gameStatus.equals("Main Menu")) {
			menu.draw(this);
		}
		
	}
	public void mousePressed() {
		if (level.player.getAmmo() > 0) {
			Bullet b = level.player.shoot(mouseX, mouseY);
			level.bullets.add(b);
		}
		
		if(gameStatus.equals("Main Menu"))
		{
			
			gameStatus = menu.checkClicked(mouseX, mouseY, width, height);
		}
		
	}		
	public void move() {
		if(level.keysPressed[0]) {
			level.player.moveBy(0, -level.playerSpeed - 15, level.objects);
		}
		if(level.keysPressed[1]) {
			level.player.moveBy(0, level.playerSpeed,  level.objects);
		}
		if(level.keysPressed[2]) {
			level.player.moveBy(level.playerSpeed, 0,level.objects);
		} 
		if(level.keysPressed[3]) {
			level.player.moveBy(-level.playerSpeed,0, level.objects);
		}
	}
	public void keyPressed() {
		if (keyCode == UP) { 
			level.keysPressed[0] = true;
			
		} 
		if (keyCode == DOWN) { 
			level.keysPressed[1] = true;
		}
		if (keyCode == RIGHT) {
			level.keysPressed[2] = true;

		}
		if (keyCode == LEFT) {
			level.keysPressed[3] = true;

		}
		if (key == 'w') {
			level.keysPressed[0] = true;

		} 
		if (key == 'd') {
			level.keysPressed[2] = true;

		}
		if (key == 's') {
			level.keysPressed[1] = true;
		
		}
		if (key == 'a') {
			level.keysPressed[3] = true;
	
		}
		
		
	}
	public void keyReleased() {
		if (keyCode == UP) { 
			level.keysPressed[0] = false;
			
		} 
		if (keyCode == DOWN) { 
			level.keysPressed[1] = false;
		}
		if (keyCode == RIGHT) {
			level.keysPressed[2] = false;

		}
		if (keyCode == LEFT) {
			level.keysPressed[3] = false;

		}
		if (key == 'w') {
			level.keysPressed[0] = false;

		} 
		if (key == 'd') {
			level.keysPressed[2] = false;

		}
		if (key == 's') {
			level.keysPressed[1] = false;
		
		}
		if (key == 'a') {
			level.keysPressed[3] = false;
	
		}
		if(key == ' ') {
			level.player.jump(level.objects);
		}

		
		if(gameStatus.equals("Not Started"))
		{
			if(key != 0)
			{
				gameStatus = "Main Menu";

			}
		}
		

		if(key == 'r') {
			if(!clickThrough.isFinished) {
				clickThrough.next();
			}
		}
	}
}
