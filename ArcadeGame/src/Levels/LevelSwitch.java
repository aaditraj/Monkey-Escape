package Levels;
import java.util.HashMap;

import core.Bullet;
import core.ClickThrough;
import processing.core.PApplet;
import processing.core.PImage;
import startpage.Instructions;
import startpage.Leaderboard;
import startpage.MainMenu;
import startpage.StartPage;
import processing.sound.SoundFile;

/*
 * Start music:
 * 
 * Random Chance by Speck (c)
 *  copyright 2022 Licensed under a Creative Commons Attribution Noncommercial  (3.0) license. 
 *  http://dig.ccmixter.org/files/speck/64667 Ft: Zenboy1955, Martijn de Boer, Javolenus, Admiral Bob, Siobhan Dakay
 *  
 *  Level 1, Level 2 and Level 3: Music by Marllon Silva (xDeviruchi)
 *  
 */
public class LevelSwitch extends PApplet{
	Level level;
	StartPage start; 
	MainMenu menu; 
	Instructions instructions; 
	Leaderboard leaderboard;
	boolean promptQuit; 
	PImage quit; 
	PImage skip;
	PImage quitPrompt; 
	int previousPoints; 
	SoundFile startMusic;
	HashMap<String,Integer> leaderboardCopy;
	
	int points = 0;
	ClickThrough clickThrough = new ClickThrough("demoSet");
	int gameStatus = 3;
	String playerName;
	boolean keysEntered = false;
	boolean played;
	public static final int LEADERBOARD = 1;
	public static final int STARTED = 2;
	public static final int NOT_STARTED = 3;
	public static final int IN_CLICKTHROUGH = 4;
	public static final int FINISHED = 5;
	public static final int MAIN_MENU = 6;
	public static final int INSTRUCTIONS = 7;
	public static final int PROMPT_QUIT = 8;
	public void setup() {
		quit = loadImage("assets/SettingSymbol.png");
		skip = loadImage("assets/SkipSymbol.png");
		quitPrompt = loadImage("assets/QuitPrompt.png");
		startMusic = new SoundFile(this, "assets/Music/StartMusic.mp3");
		System.out.println("SFSampleRate= " + startMusic.sampleRate() + " Hz");
		System.out.println("SFSamples= " + startMusic.frames() + " samples");
		System.out.println("SFDuration= " + startMusic.duration() + " seconds");
		start = new StartPage(); 
		menu = new MainMenu(); 
		level = new Level1();
		instructions = new Instructions();
		leaderboard = new Leaderboard();
		keysEntered = false;
		playerName ="Enter Name";
		points = 0;
		played = false;
		((Level1)level).setup();
		startMusic.loop();
	}
	public void draw() {
		
		
		if(gameStatus == STARTED) {
			background(50);
			textFont(createFont("assets/ARCADE_N.TTF", 50));
			text(points, width-150, 150);
			image(quit, width-100, 25, 50, 50);
			image(skip, width-175, 30, 65, 40);
			move();
			level.draw(this);
			points = level.player.points;
			played = true;
			if(level.isFinished == true) {
				if(level instanceof Level1) {
					points = level.player.points;
					level = new Level2();
					((Level2) level).setup();
					level.player.points = points;
					previousPoints = level.player.points; 
					
				} else if (level instanceof Level2) {
					points = level.player.points;
					level = new Level3();
					((Level3) level).setup();
					level.player.points = points;
					previousPoints = level.player.points; 

				} else if (level instanceof Level3) {
					level.isFinished = false;
					gameStatus = LEADERBOARD;
				}
			}
			
			

			
			if(promptQuit)
			{
				promptQuit();
				gameStatus = PROMPT_QUIT;
			}

		} else if (gameStatus == NOT_STARTED) {
			start.draw(this); 
		} else if (gameStatus == IN_CLICKTHROUGH) {
			clickThrough.draw(this);
			if(clickThrough.isFinished) {
				gameStatus = NOT_STARTED;
			}
		} else if (gameStatus == FINISHED)  {
			leaderboard.add(points, "Player");
		} else if (gameStatus == MAIN_MENU) {
			menu.draw(this);
		} else if (gameStatus == INSTRUCTIONS) {
			instructions.draw(this);
		} else if (gameStatus == LEADERBOARD) {
			leaderboard.draw(this);
		}
		
		

		
	}
	public void mousePressed() {
		if (level.player.getAmmo() > 0) {
			Bullet b = level.player.shoot(mouseX, mouseY);
			level.bullets.add(b);
		}
		
		if(gameStatus == MAIN_MENU)
		{
			
			gameStatus = menu.checkClicked(mouseX, mouseY, width, height);
			if(gameStatus == STARTED) {
				setup();
			}
		}
		
		if(gameStatus == INSTRUCTIONS)
		{
			
			gameStatus = instructions.checkClicked(mouseX, mouseY, width, height);
		}
		
		if(gameStatus == LEADERBOARD)
		{
			
			gameStatus = leaderboard.checkClicked(mouseX, mouseY, width, height);
		}
		
		if(gameStatus == PROMPT_QUIT)
		{
			
			if(mouseX > (int)(width/3.5) && mouseX < (int)(width/3.5) + width/5)
			{
				if(mouseY > (int)(height/1.8) && mouseY < (int)(height/1.8) + height/5) 
					gameStatus = MAIN_MENU; 
					//setup();
					promptQuit = false;
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
					if(key == 'r') {
						if(!clickThrough.isFinished) {
							clickThrough.next();
						}
					}
					if(key == ESC) {
						System.exit(0);
					}
				
			}
			
			
			if(mouseX > (int)(width/1.95) && mouseX < (int)(width/1.95) + width/5)
			{
				if(mouseY > (int)(height/1.8) && mouseY < (int)(height/1.8) + height/5) 
					gameStatus = STARTED; 
					promptQuit = false;
				
				
					
			}
		}
		
		if(gameStatus == STARTED)
		{
			
			if(mouseX > width-100 && mouseX < width-100 + 50)
			{
				if(mouseY > 25 && mouseY < 25 + 50) 
				promptQuit = true; 
			}
			
			if(mouseX > width-175 && mouseX < width-175 + 65)
			{
				if(mouseY > 30 && mouseY < 30 + 40) 
				if(level instanceof Level1)
				{
					level.player.points = 0;
					level.isFinished = true; 
				}
				else if (level instanceof Level2)
				{
					level.player.points = previousPoints; 
					level.isFinished = true; 
				}
				else {
					level.player.points = previousPoints; 
					level.isFinished = true; 
				}
			}
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
		if(key == 'r') {
			if(!clickThrough.isFinished) {
				clickThrough.next();
			}
		}
		if(key == ESC) {
			System.exit(0);
		}
		if(gameStatus == NOT_STARTED)
		{
			if(key != 0)
			{
				gameStatus = MAIN_MENU;
				
			}
		}
	}
	
	public void promptQuit()
	{
		image(quitPrompt, width/4, height/4, width/2, height/2);
		
		

	}
	
	
}
