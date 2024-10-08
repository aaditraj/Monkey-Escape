
import java.awt.Color;
import java.util.HashMap;

import core.Bullet;
import levels.Endless;
import levels.GameStatus;
import levels.Level;
import levels.Level1;
import levels.Level2;
import levels.Level3;
import processing.core.PApplet;
import processing.core.PImage;
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
/**
 * The main Level class that allows us to draw the correct UI based on the Game's Status
 * 
 * @author Julian, Aditya, Adithya
 */
public class LevelSwitch extends PApplet{
	Level level;
	StartPage start; 
	MainMenu menu; 
	Instructions instructions; 
	Leaderboard leaderboard;
	boolean promptQuit; 
	boolean promptSkip;
	PImage quit; 
	PImage background;
	PImage background2;

	PImage background3;

	PImage skip;
	PImage quitPrompt; 
	PImage skipPrompt; 

	int previousPoints = 0;
	SoundFile startMusic;
	SoundFile level1Music;
	SoundFile level2Music;
	SoundFile level3Music;
	SoundFile jumpSound;
	SoundFile shootSound;
	HashMap<String,Integer> leaderboardCopy;
	String[] playerLeftImgs = new String[]{"assets/Player/PlayerLeft.png","assets/Player/PlayerLeft2.png"};
	String[] playerRightImgs = new String[]{"assets/Player/PlayerRight.png","assets/Player/PlayerRight2.png"};
	String[] playerImg = new String[]{"assets/Player/Player.png"};
	int playerState = 1; 
	int playerState2 = 1;
	int points = 0;
	ClickThrough clickThrough = new ClickThrough("realSet");
	int gameStatus = GameStatus.NOT_STARTED;
	String playerName;
	boolean keysEntered = false;
	boolean played;
	private NameEnterPage namePage;
	int previousGameStatus;
	
	/**
	 * The main method that sets up LevelSwitch, a class that allows multiple classes to be drawn
	 * on a single screen, and allows multiple classes to communicate with each other in a cohesive manner
	 */
	public void settings() {
		noSmooth();
	}
	
	/**
	 * This method sets up the initial window, starting by loading the main menu and then the rest of the levels. 
	 * 
	 */
	public void setup() {
		quit = loadImage("assets/SettingSymbol.png");
		background = loadImage("assets/Backgrounds/background.png");
		skip = loadImage("assets/SkipSymbol.png");
		quitPrompt = loadImage("assets/QuitPrompt.png");
		skipPrompt = loadImage("assets/SkipPrompt.png");
		if (startMusic == null) startMusic = new SoundFile(this, "assets/Music/StartMusic.wav");
		if (level1Music == null) level1Music = new SoundFile(this, "assets/Music/Level1.wav");
		if (level2Music == null) level2Music = new SoundFile(this, "assets/Music/Level2.wav");
		if (level3Music == null) level3Music = new SoundFile(this, "assets/Music/Level3.wav");
		
		start = new StartPage(); 
		menu = new MainMenu(); 
		instructions = new Instructions();
		leaderboard = new Leaderboard();
		keysEntered = false;
		playerName ="Enter Name";
		points = 0;
		played = false;
		if (gameStatus == GameStatus.ENDLESS) {
			level = new Endless();
			((Endless)level).setup(this);
		} else {
			level = new Level1();
			((Level1)level).setup(this);
		}
		
		if (level1Music != null && !level1Music.isPlaying()) {
			startMusic.play();
		}
	}
	
	/**
	 * A processing methods that allows something to be rendered on the screen
	 * This draw method calls on the draw methods of other classes allowing them to be drawn on the screen
	 * 
	 * @post The background of the PApplet will be changed
	 * @post Text and images will be drawn on the image
	 */
	public void draw() {
		
		if(gameStatus == GameStatus.SINGLE_PLAYER || gameStatus == GameStatus.ENDLESS) {
			if(gameStatus == GameStatus.SINGLE_PLAYER) {
				image(background,0,0,width,height);
			} else {
				background(53,81,92);
			}
			
			//Draws the levels
			if (gameStatus == GameStatus.SINGLE_PLAYER) {
				textFont(createFont("assets/ARCADE_N.TTF", 50));
				text(points, width-150, 150);
			}
			image(quit, width-100, 25, 50, 50);
			if (gameStatus == GameStatus.SINGLE_PLAYER) {
				image(skip, width-175, 30, 65, 40);
			}
			move();
			level.draw(this);
			points = level.getPlayer().points;
			played = true;
			if(level.getDead())
			{
				level.getPlayer().points = previousPoints;
				level.setDead(false); 
			}
			
			//Checks if the levels are finished
			if(level.isFinished() == true) {
				if(level instanceof Level1) {
					level1Music.stop();
					points = level.getPlayer().points;
					level = new Level2();
					((Level2) level).setup(this);
					level2Music.loop();
					level.getPlayer().points = points;
					previousPoints = level.getPlayer().points; 
					
				} else if (level instanceof Level2) {
					level2Music.stop();
					points = level.getPlayer().points;
					level = new Level3();
					((Level3) level).setup(this);
					level3Music.loop();
					level.getPlayer().points = points;
					previousPoints = level.getPlayer().points; 

				} else if (level instanceof Level3) {
					level3Music.stop();
					level.setFinished(false);
					gameStatus = GameStatus.FINISHED;
				}
			}
			
			//Checks if the skip option has been clicked
			if(promptSkip)
			{
				previousGameStatus = gameStatus;
				promptSkip();
				gameStatus = GameStatus.PROMPT_SKIP;
			}

			
			//Checks if the quit option has been clicked
			if(promptQuit)
			{
				previousGameStatus = gameStatus;
				promptQuit();
				gameStatus = GameStatus.PROMPT_QUIT;
			}
			
			
		} else {
			if (!startMusic.isPlaying() && gameStatus != GameStatus.PROMPT_QUIT && gameStatus != GameStatus.PROMPT_SKIP) {
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
			} else if (gameStatus == GameStatus.NOT_STARTED) {
				start.draw(this);
			} else if (gameStatus == GameStatus.IN_CLICKTHROUGH) {
				clickThrough.draw(this);
				if(clickThrough.isFinished) {
					gameStatus = GameStatus.MAIN_MENU;
				}
			}
		}
		//}
	}
	/**
	 * A method that triggers when the mouse is clicked
	 * The mouse coordinates are passed to the correct class,
	 *  so that class can determine whether to change the window or not.
	 */
	public void mousePressed() {
		if (gameStatus == GameStatus.SINGLE_PLAYER) {
			if (level.getPlayer().getAmmo() > 0) {
				if (gameStatus == GameStatus.SINGLE_PLAYER && !shootSound.isPlaying() || gameStatus == GameStatus.ENDLESS && !shootSound.isPlaying()) {
					shootSound.play();
				}
				Bullet b = level.getPlayer().shoot(mouseX, mouseY);
				level.getBullets().add(b);
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
			
			//Yes Option
			if(mouseX > (int)(width/3.5) && mouseX < (int)(width/3.5) + width/5)
			{
				if(mouseY > (int)(height/1.8) && mouseY < (int)(height/1.8) + height/5) 
					gameStatus = GameStatus.MAIN_MENU; 
					level1Music.stop();
					level2Music.stop();
					level3Music.stop();
					//setup();
					promptQuit = false;
			}
			
			
			//No Option
			if(mouseX > (int)(width/1.95) && mouseX < (int)(width/1.95) + width/5)
			{
				if(mouseY > (int)(height/1.8) && mouseY < (int)(height/1.8) + height/5)
					gameStatus = previousGameStatus;
				
					promptQuit = false;
				
				
					
			}
		}
		
		if(gameStatus == GameStatus.PROMPT_SKIP)
		{
			
			//Yes Option
			if(mouseX > (int)(width/3.5) && mouseX < (int)(width/3.5) + width/5)
			{
				if(mouseY > (int)(height/1.8) && mouseY < (int)(height/1.8) + height/5) 
					gameStatus = GameStatus.SINGLE_PLAYER; 
				
					level.setFinished(true);
					
					promptSkip = false;
			}
			
			
			//No Option
			if(mouseX > (int)(width/1.95) && mouseX < (int)(width/1.95) + width/5)
			{
				
				if(mouseY > (int)(height/1.8) && mouseY < (int)(height/1.8) + height/5)
					gameStatus = GameStatus.SINGLE_PLAYER; 
				
					
				
					promptSkip = false;
				
				
					
			}
		}
		
		//Checks if skip button was clicked
		if(gameStatus == GameStatus.SINGLE_PLAYER || gameStatus == GameStatus.ENDLESS)
		{
			
			if(mouseX > width-100 && mouseX < width-100 + 50)
			{
				if(mouseY > 25 && mouseY < 25 + 50) 
				{
					promptQuit = true; 
					
				}
				
			}
			
			if(mouseX > width-175 && mouseX < width-175 + 65 && gameStatus == GameStatus.SINGLE_PLAYER)
			{
				if(mouseY > 30 && mouseY < 30 + 40) 
				if(level instanceof Level1)
				{
					level.getPlayer().points = 0;
					promptSkip = true;
				
				}
				else if (level instanceof Level2)
				{
					level.getPlayer().points = previousPoints; 
					promptSkip = true;
					
				}
				else {
					level.getPlayer().points = previousPoints; 
					promptSkip = true;
					
					
				}
			}
		}
		//Manages the clickthrough
		if(gameStatus == GameStatus.IN_CLICKTHROUGH) {
			clickThrough.next();
		}
	}
	
	/**
	 *The method that uses the keys pressed to determine which direction to move the main player. 
	 */
	public void move() {
		if(gameStatus == GameStatus.ENDLESS) {
			if(level.getKeysPressed()[0] && !level.isInAnimation()) {
				level.getPlayer().moveBy(0, level.getPlayerSpeed(),  level.getObjects());
				if(playerState != 1) {
					playerState = 1;
					level.getPlayer().setImages(level.getPlayer().getCurrent());
				}
			}
			if(level.getKeysPressed()[1] && !level.isInAnimation()) {
				level.getPlayer().moveBy(level.getPlayerSpeed(), 0,level.getObjects());
				if(playerState != 2) {
					playerState = 2;
					level.getPlayer().setImages(level.getPlayer().getRight());
				}
			} else if (playerState == 2 && !level.isInAnimation()) {
				playerState = 1;
				level.getPlayer().setImages(level.getPlayer().getCurrent());
			}
			if(level.getKeysPressed()[2] && !level.isInAnimation()) {
				level.getPlayer().moveBy(-level.getPlayerSpeed(),0, level.getObjects());
				if(playerState != 3) {
					playerState = 3;
					level.getPlayer().setImages(level.getPlayer().getLeft());
				}
			} else if(playerState == 3 && !level.isInAnimation()) {
				playerState = 1;
				level.getPlayer().setImages(level.getPlayer().getCurrent());
			}
			
			if(level.getKeysPressed2()[0] && !level.isInAnimation()) {
				level.getPlayer2().moveBy(0, level.getPlayerSpeed(),  level.getObjects());
				if(playerState2 != 1) {
					playerState2 = 1;
					level.getPlayer2().setImages(level.getPlayer2().getCurrent());
				}
			}
			if(level.getKeysPressed2()[1] && !level.isInAnimation()) {
				level.getPlayer2().moveBy(level.getPlayerSpeed(), 0,level.getObjects());
				if(playerState2 != 2) {
					playerState2 = 2;
					level.getPlayer2().setImages(level.getPlayer2().getRight());
				}
			} else if (playerState2 == 2 && !level.isInAnimation()) {
				playerState2 = 1;
				level.getPlayer2().setImages(level.getPlayer2().getCurrent());
			}
			if(level.getKeysPressed2()[2] && !level.isInAnimation()) {
				level.getPlayer2().moveBy(-level.getPlayerSpeed(),0, level.getObjects());
				if(playerState2 != 3) {
					playerState2 = 3;
					level.getPlayer2().setImages(level.getPlayer2().getLeft());
				}
			} else if(playerState2 == 3 && !level.isInAnimation()) {
				playerState2 = 1;
				level.getPlayer2().setImages(level.getPlayer2().getCurrent());
			}
		} else {
			if(level.getKeysPressed()[0] && !level.isInAnimation()) {
				level.getPlayer().moveBy(0, level.getPlayerSpeed(),  level.getObjects());
				if(playerState != 1) {
					playerState = 1;
					level.getPlayer().setImages(level.getPlayer().getCurrent());
				}
			}
			if(level.getKeysPressed()[1] && !level.isInAnimation()) {
				level.getPlayer().moveBy(level.getPlayerSpeed(), 0,level.getObjects());
				if(playerState != 2) {
					playerState = 2;
					level.getPlayer().setImages(level.getPlayer().getRight());
				}
			} else if (playerState == 2 && !level.isInAnimation()) {
				playerState = 1;
				level.getPlayer().setImages(level.getPlayer().getCurrent());
			}
			if(level.getKeysPressed()[2] && !level.isInAnimation()) {
				level.getPlayer().moveBy(-level.getPlayerSpeed(),0, level.getObjects());
				if(playerState != 3) {
					playerState = 3;
					level.getPlayer().setImages(level.getPlayer().getLeft());
				}
			} else if(playerState == 3 && !level.isInAnimation()) {
				playerState = 1;
				level.getPlayer().setImages(level.getPlayer().getCurrent());
			}
		}
	}
	/**
	 * Updates the keys pressed array, which shows which keys are currently pressed
	 * The keys pressed array is used by the move method to move a character.
	 */
	public void keyPressed() {
		if(gameStatus == GameStatus.NAME_PAGE) {
			namePage.interpretKeystroke((char)keyCode);
		} else if (gameStatus == GameStatus.ENDLESS) {
			if (keyCode == UP) { 
				boolean canJump = level.getPlayer2().jump(level.getObjects());
				if (canJump && !jumpSound.isPlaying()) {
					jumpSound.play();
				}
			} 
			if (keyCode == RIGHT) {
				level.getKeysPressed2()[1] = true;
			}
			if (keyCode == LEFT) {
				level.getKeysPressed2()[2] = true;
			}
			if (key == 'w') {
				boolean canJump = level.getPlayer().jump(level.getObjects());
				if (canJump && !jumpSound.isPlaying()) {
					jumpSound.play();
				}
			}
			if (key == 'd') {
				level.getKeysPressed()[1] = true;
			}
			if (key == 'a') {
				level.getKeysPressed()[2] = true;
			}
		}else {
			if (keyCode == UP) { 
				boolean canJump = level.getPlayer().jump(level.getObjects());
				if ((gameStatus == GameStatus.SINGLE_PLAYER) && canJump && !jumpSound.isPlaying()) {
					jumpSound.play();
				}
			} 
			if (keyCode == DOWN) { 
				if(gameStatus == GameStatus.MAIN_MENU) menu.modifyIndex(1);
				level.getKeysPressed()[0] = true;
			}
			if (keyCode == RIGHT) {
				level.getKeysPressed()[1] = true;
			}
			if (keyCode == LEFT) {
				level.getKeysPressed()[2] = true;
			}
			if (key == 'w') {
				boolean canJump = level.getPlayer().jump(level.getObjects());
				if ((gameStatus == GameStatus.SINGLE_PLAYER || gameStatus == GameStatus.ENDLESS) && canJump && !jumpSound.isPlaying()) {
					jumpSound.play();
				}
			}
			if (key == 'd') {
				level.getKeysPressed()[1] = true;
			}
			if (key == 's') {
				level.getKeysPressed()[0] = true;
			}
			if (key == 'a') {
				level.getKeysPressed()[2] = true;
			}
			if(key == ' ') {
				if ((gameStatus == GameStatus.SINGLE_PLAYER || gameStatus == GameStatus.ENDLESS) && !jumpSound.isPlaying()) {
					jumpSound.play();
				}
				level.getPlayer().jump(level.getObjects());
			}
		}
	}
	/**
	 * Removes the pressed key from the keys pressed array
	 */
	public void keyReleased() {
		if(gameStatus == GameStatus.ENDLESS) {
			if (keyCode == DOWN) { 
				level.getKeysPressed2()[0] = false;
			}
			if (keyCode == RIGHT) {
				level.getKeysPressed2()[1] = false;

			}
			if (keyCode == LEFT) {
				level.getKeysPressed2()[2] = false;

			}
			if (key == 'd') {
				level.getKeysPressed()[1] = false;

			}
			if (key == 's') {
				level.getKeysPressed()[0] = false;
			
			}
			if (key == 'a') {
				level.getKeysPressed()[2] = false;
		
			}
		} else {
			if (keyCode == DOWN) { 
				level.getKeysPressed()[0] = false;
			}
			if (keyCode == RIGHT) {
				level.getKeysPressed()[1] = false;

			}
			if (keyCode == LEFT) {
				level.getKeysPressed()[2] = false;

			}
			if (key == 'd') {
				level.getKeysPressed()[1] = false;

			}
			if (key == 's') {
				level.getKeysPressed()[0] = false;
			
			}
			if (key == 'a') {
				level.getKeysPressed()[2] = false;
		
			}
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
				gameStatus = GameStatus.IN_CLICKTHROUGH;
				
			}
		}
		
		if(key == ENTER)
		{
			if(gameStatus == GameStatus.MAIN_MENU)
			{
				gameStatus = menu.passMenu();
			
			
			
				if(gameStatus == GameStatus.SINGLE_PLAYER || gameStatus == GameStatus.ENDLESS) {
					startMusic.stop();
					jumpSound = new SoundFile(this, "assets/SoundEffects/jump.wav");
					shootSound = new SoundFile(this, "assets/SoundEffects/shoot.wav");
					shootSound.amp(0.2f);
					level1Music.amp(0.2f);
					level2Music.amp(0.2f);
					level3Music.amp(0.2f);
					level1Music.loop();
					setup();
				}
			}

		}
	}
	/**
	 * Used to display the quit menu
	 */
	public void promptQuit()
	{
		image(quitPrompt, width/4, height/4, width/2, height/2);
		
		

	}
	
	/**
	 * Used to display the skip menu
	 */
	public void promptSkip()
	{
		image(skipPrompt, width/4, height/4, width/2, height/2);
	}
	
	
}
