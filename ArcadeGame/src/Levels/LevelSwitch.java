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
	String gameStatus = "Not Started";
	String playerName;
	boolean keysEntered = false;
	boolean played;
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
		leaderboardCopy = leaderboard.getLeaderboard();
		keysEntered = false;
		playerName ="Enter Name";
		points = 0;
		played = false;
		((Level1)level).setup();
		startMusic.loop();
	}
	public void draw() {
		
		
		if(gameStatus.equals("Started")) {
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
					gameStatus = "Leaderboard";
				}
			}
			
			

			
			if(promptQuit)
			{
				promptQuit();
				gameStatus = "PromptQuit";
			}

		} else if (gameStatus.equals("Not Started")) {
			start.draw(this); 
		} else if (gameStatus.equals("In Clickthrough")) {
			clickThrough.draw(this);
			if(clickThrough.isFinished) {
				gameStatus = "Not Started";
			}
		} else if (gameStatus.equals("Finished"))  {
			leaderboard.add(points, "Player");
		} else if (gameStatus.equals("Main Menu")) {
			menu.draw(this);
		} else if (gameStatus.equals("Instructions")) {
			instructions.draw(this);
		} else if (gameStatus.equals("Leaderboard")) {
			if(played) {
				leaderboard.shouldDispName = true;
				leaderboard.currName =  playerName;
				leaderboard.currPoints = points;
			}
			leaderboard.draw(this);
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
			if(gameStatus.equals("Started")) {
				setup();
			}
		}
		
		if(gameStatus.equals("Instructions"))
		{
			
			gameStatus = instructions.checkClicked(mouseX, mouseY, width, height);
		}
		
		if(gameStatus.equals("Leaderboard"))
		{
			
			gameStatus = leaderboard.checkClicked(mouseX, mouseY, width, height);
		}
		
		if(gameStatus.equals("PromptQuit"))
		{
			
			if(mouseX > (int)(width/3.5) && mouseX < (int)(width/3.5) + width/5)
			{
				if(mouseY > (int)(height/1.8) && mouseY < (int)(height/1.8) + height/5) 
					gameStatus = "Main Menu"; 
					//setup();
					promptQuit = false;

				
			}
			
			
			if(mouseX > (int)(width/1.95) && mouseX < (int)(width/1.95) + width/5)
			{
				if(mouseY > (int)(height/1.8) && mouseY < (int)(height/1.8) + height/5) 
					gameStatus = "Started"; 
					promptQuit = false;
				
				
					
			}
		}
		
		if(gameStatus.equals("Started"))
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
		if(!gameStatus.equals("Leaderboard")) {
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
	}
	public void keyReleased() {
		if(gameStatus.equals("Leaderboard")) {
			if(played) {
				leaderboard.currName =  playerName;
				leaderboard.currPoints = points;
				if(key == '\n') {
					if(!leaderboardCopy.containsKey(playerName)) {
						leaderboard.add(points, playerName);
						leaderboard.shouldDispName = false;
						keyPressed = false;
						setup();
					} else {
						
					}
				} else if (key == BACKSPACE && playerName.length() >=1 ) {
					if(!keysEntered) {
						keysEntered = true;
						playerName = "";
					}
					if(playerName.length() >= 1) {
						playerName = playerName.substring(0,playerName.length()-1);
					}
					leaderboard.currName = playerName;
				}else {
					if((int)key > 96 && (int)key < 123) {
						if(!keysEntered) {
							keysEntered = true;
							playerName = "";
						}
						if(playerName.length() < 10) {
							playerName = playerName + key;
						}
						leaderboard.currName = playerName;
					}
				}
			}
		} else {
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
		if(gameStatus.equals("Not Started"))
		{
			if(key != 0)
			{
				gameStatus = "Main Menu";
				
			}
		}
	}
	
	public void promptQuit()
	{
		image(quitPrompt, width/4, height/4, width/2, height/2);
		
		

	}
	
	
}
