
import java.util.HashMap;

import core.Bullet;
import levels.GameStatus;
import levels.Level;
import levels.Level1;
import levels.Level2;
import levels.Level3;
import processing.core.PApplet;
import processing.core.PImage;
import processing.sound.Sound;
import processing.sound.SoundFile;
import startpage.ClickThrough;
import startpage.Instructions;
import startpage.Leaderboard;
import startpage.MainMenu;
import startpage.NameEnterPage;
import startpage.StartPage;

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
	Sound s;
	SoundFile startMusic;
	SoundFile level1Music;
	SoundFile level2Music;
	SoundFile level3Music;
	HashMap<String,Integer> leaderboardCopy;
	
	int points = 0;
	ClickThrough clickThrough = new ClickThrough("demoSet");
	int gameStatus = 3;
	String playerName;
	boolean keysEntered = false;
	boolean played;
	NameEnterPage page;
	private NameEnterPage namePage;
	public void setup() {
		quit = loadImage("assets/SettingSymbol.png");
		skip = loadImage("assets/SkipSymbol.png");
		quitPrompt = loadImage("assets/QuitPrompt.png");
		startMusic = new SoundFile(this, "assets/Music/StartMusic.wav");
		System.out.println("SFSampleRate= " + startMusic.sampleRate() + " Hz");
		System.out.println("SFSamples= " + startMusic.frames() + " samples");
		System.out.println("SFDuration= " + startMusic.duration() + " seconds");
		s = new Sound(this);
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
		if (level1Music == null || !level1Music.isPlaying()) {
			startMusic.play();
		}
	}
	public void draw() {
		
		s.volume(0.5f);
		
		if(gameStatus == GameStatus.STARTED) {
			background(50);
			textFont(createFont("assets/ARCADE_N.TTF", 50));
			text(points, width-150, 150);
			image(quit, width-100, 25, 50, 50);
			image(skip, width-175, 30, 65, 40);
			move();
			level.draw(this);
			points = level.getPlayer().points;
			played = true;
			if(level.isFinished() == true) {
				if(level instanceof Level1) {
					level1Music.stop();
					points = level.getPlayer().points;
					level = new Level2();
					((Level2) level).setup();
					level2Music.loop();
					level.getPlayer().points = points;
					previousPoints = level.getPlayer().points; 
					
				} else if (level instanceof Level2) {
					level2Music.stop();
					points = level.getPlayer().points;
					level = new Level3();
					((Level3) level).setup();
					level3Music.loop();
					level.getPlayer().points = points;
					previousPoints = level.getPlayer().points; 

				} else if (level instanceof Level3) {
					level3Music.stop();
					level.setFinished(false);
					gameStatus = GameStatus.FINISHED;
				}
			}
			
			

			
			if(promptQuit)
			{
				promptQuit();
				gameStatus = GameStatus.PROMPT_QUIT;
			}
		} else {
			if (!startMusic.isPlaying() && gameStatus != GameStatus.PROMPT_QUIT) {
				startMusic.jump(14.5f);
				startMusic.play();
			}
			if (gameStatus == GameStatus.FINISHED)  {
				namePage = new NameEnterPage(points,leaderboard);
				gameStatus = GameStatus.NAME_PAGE;
			} else if (gameStatus == GameStatus.MAIN_MENU) {
				menu.draw(this);
			} else if (gameStatus == GameStatus.INSTRUCTIONS) {
				instructions.draw(this);
			} else if (gameStatus == GameStatus.LEADERBOARD) {
				leaderboard.draw(this);
			} else if (gameStatus == GameStatus.NAME_PAGE) {
				namePage.draw(this);
				if(namePage.isFinished()) {
					gameStatus = GameStatus.LEADERBOARD;
				}
			}
		}

		
		

		
	}
	public void mousePressed() {
		if (level.getPlayer().getAmmo() > 0) {
			Bullet b = level.getPlayer().shoot(mouseX, mouseY);
			level.getBullets().add(b);
		}
		
		if(gameStatus == GameStatus.MAIN_MENU)
		{
			
			gameStatus = menu.checkClicked(mouseX, mouseY, width, height);
			if(gameStatus == GameStatus.STARTED) {
				startMusic.stop();
				level1Music = new SoundFile(this, "assets/Music/Level1.wav");
				level2Music = new SoundFile(this, "assets/Music/Level2.wav");
				level3Music = new SoundFile(this, "assets/Music/Level3.wav");
				level1Music.amp(0.5f);
				level2Music.amp(0.5f);
				level3Music.amp(0.5f);
				level1Music.loop();
				setup();
			}
		}
		
		if(gameStatus == GameStatus.INSTRUCTIONS)
		{
			
			gameStatus = instructions.checkClicked(mouseX, mouseY, width, height);
		}
		
		if(gameStatus == GameStatus.LEADERBOARD)
		{
			
			gameStatus = leaderboard.checkClicked(mouseX, mouseY, width, height);
		}
		
		if(gameStatus == GameStatus.PROMPT_QUIT)
		{
			
			if(mouseX > (int)(width/3.5) && mouseX < (int)(width/3.5) + width/5)
			{
				if(mouseY > (int)(height/1.8) && mouseY < (int)(height/1.8) + height/5) 
					gameStatus = GameStatus.MAIN_MENU; 
					level1Music.stop();
					level2Music.stop();
					level3Music.stop();
					//setup();
					promptQuit = false;
					if (keyCode == UP) { 
						level.getKeysPressed()[0] = false;
					} 
					if (keyCode == DOWN) { 
						level.getKeysPressed()[1] = false;
					}
					if (keyCode == RIGHT) {
						level.getKeysPressed()[2] = false;

					}
					if (keyCode == LEFT) {
						level.getKeysPressed()[3] = false;

					}
					if (key == 'w') {
						level.getKeysPressed()[0] = false;

					} 
					if (key == 'd') {
						level.getKeysPressed()[2] = false;

					}
					if (key == 's') {
						level.getKeysPressed()[1] = false;
					
					}
					if (key == 'a') {
						level.getKeysPressed()[3] = false;
				
					}
					if(key == ' ') {
						level.getPlayer().jump(level.getObjects());
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
					gameStatus = GameStatus.STARTED; 
					promptQuit = false;
				
				
					
			}
		}
		
		if(gameStatus == GameStatus.STARTED)
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
					level.getPlayer().points = 0;
					level.setFinished(true); 
				}
				else if (level instanceof Level2)
				{
					level.getPlayer().points = previousPoints; 
					level.setFinished(true); 
				}
				else {
					level.getPlayer().points = previousPoints; 
					level.setFinished(true); 
				}
			}
		}
		
	}		
	public void move() {
		if(level.getKeysPressed()[0]) {
			level.getPlayer().moveBy(0, -level.getPlayerSpeed() - 15, level.getObjects());
		}
		if(level.getKeysPressed()[1]) {
			level.getPlayer().moveBy(0, level.getPlayerSpeed(),  level.getObjects());
		}
		if(level.getKeysPressed()[2]) {
			level.getPlayer().moveBy(level.getPlayerSpeed(), 0,level.getObjects());
		} 
		if(level.getKeysPressed()[3]) {
			level.getPlayer().moveBy(-level.getPlayerSpeed(),0, level.getObjects());
		}
	}
	public void keyPressed() {
		if(gameStatus == GameStatus.NAME_PAGE) {
			namePage.interpretKeystroke((char)keyCode);
		} else {
			if (keyCode == UP) { 
				level.getKeysPressed()[0] = true;
				
			} 
			if (keyCode == DOWN) { 
				level.getKeysPressed()[1] = true;
			}
			if (keyCode == RIGHT) {
				level.getKeysPressed()[2] = true;

			}
			if (keyCode == LEFT) {
				level.getKeysPressed()[3] = true;

			}
			if (key == 'w') {
				level.getKeysPressed()[0] = true;

			} 
			if (key == 'd') {
				level.getKeysPressed()[2] = true;

			}
			if (key == 's') {
				level.getKeysPressed()[1] = true;
			
			}
			if (key == 'a') {
				level.getKeysPressed()[3] = true;
		
			}
		}
	}
	public void keyReleased() {
		if (keyCode == UP) { 
			level.getKeysPressed()[0] = false;
		} 
		if (keyCode == DOWN) { 
			level.getKeysPressed()[1] = false;
		}
		if (keyCode == RIGHT) {
			level.getKeysPressed()[2] = false;

		}
		if (keyCode == LEFT) {
			level.getKeysPressed()[3] = false;

		}
		if (key == 'w') {
			level.getKeysPressed()[0] = false;

		} 
		if (key == 'd') {
			level.getKeysPressed()[2] = false;

		}
		if (key == 's') {
			level.getKeysPressed()[1] = false;
		
		}
		if (key == 'a') {
			level.getKeysPressed()[3] = false;
	
		}
		if(key == ' ') {
			level.getPlayer().jump(level.getObjects());
		}
		if(key == 'r') {
			if(!clickThrough.isFinished) {
				clickThrough.next();
			}
		}
		if(key == ESC) {
			System.exit(0);
		}
		if(gameStatus == GameStatus.NOT_STARTED)
		{
			if(key != 0)
			{
				gameStatus = GameStatus.MAIN_MENU;
				
			}
		}
	}
	
	public void promptQuit()
	{
		image(quitPrompt, width/4, height/4, width/2, height/2);
		
		

	}
	
	
}
