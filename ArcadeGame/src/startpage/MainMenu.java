package startpage; 
import levels.GameStatus;
import processing.core.PApplet;
import processing.core.PImage;
public class MainMenu {
	public boolean[] display = new boolean[3];
	int index;
	int counter;
	PImage bg;
	
	
	public MainMenu()
	{
		display[0] = true;
		counter = 0;
		index = 0;
	}
	
	//direction = 0 is up, direction = 1 is down
	public void modifyIndex(int direction)
	{
		if(direction == 0)
		{
			

			display[index] = false;
			if(index == 0) {
				index = 2; 
				display[index] = true;
			} else {
				index--; 
				display[index] = true;
			}
			
		}
		else {
			display[index] = false;
			
			if(index == 2)
			{
				index = 0; 
				display[index] = true;
			}
			else {
				index++; 
				display[index] = true;
			}
		
		}
	}
	
	public void draw(PApplet marker) {
		marker.push();
		bg = marker.loadImage("assets/StartPage/Main Menu 2.png");
		marker.image(bg, 0, 0, marker.width, marker.height);
		
		marker.textFont(marker.createFont("assets/ARCADE_N.TTF", 50));
		marker.textSize((float)(marker.width/16.666666));
	
		System.out.println("width: " + marker.width);
		System.out.println("height: " + marker.height);

	
		marker.noFill();
		marker.strokeWeight(10);
		if(display[0] == true)
		{
			marker.text("INSTRUCTIONS", (float)(marker.width/6.52), (float)(marker.height/1.413));
			marker.text("LEADERBOARD", (float)(marker.width/5.77), (float)(marker.height/1.16));
			marker.fill(0,0,0);
			if(counter % 4 != 0)
			{
				marker.text("START GAME", (float)(marker.width/5.357), (float)(marker.height/1.80));
				marker.noFill();

				marker.rect((float)(marker.width/5.55), (float)(marker.height/2.2), (float)(marker.width/1.676), (float)(marker.height/8.24));
			}
			
		}
		else if(display[1] == true)
		{
			
			marker.text("START GAME", (float)(marker.width/5.36), (float)(marker.height/1.8));
			marker.text("LEADERBOARD", (float)(marker.width/5.77), (float)(marker.height/1.16));
			marker.fill(0,0,0);
			if(counter % 4 != 0)
			{
				marker.text("INSTRUCTIONS", (float)(marker.width/6.52), (float)(marker.height/1.41));
				marker.noFill();

				marker.rect((float)(marker.width/6.82), (float)(marker.height/1.65), (float)(marker.width/1.4), (float)(marker.height/8.24));
			}
			
		}
		else if(display[2] == true)
		{
			marker.text("INSTRUCTIONS", (float)(marker.width/6.52), (float)(marker.height/1.413));
			marker.text("START GAME", (float)(marker.width/5.357), (float)(marker.height/1.80));
			marker.fill(0,0,0);
			if(counter % 4 != 0)
			{
				marker.text("LEADERBOARD", (float)(marker.width/5.77), (float)(marker.height/1.16));
				marker.noFill();

				marker.rect((float)(marker.width/6), (float)(marker.height/1.32), (float)(marker.width/1.546), (float)(marker.height/8.24));
			}
		}
		marker.textSize(30);
		marker.fill(marker.color(255,255,0));
		marker.text("Copyright 2022 AAJ Corp", marker.width/3, (8)*100+marker.height * 0.15f);
		marker.pop();
		
		counter++; 
	}
	
	public int passMenu()
	{
		if(display[0] == true)
		{
			return GameStatus.STARTED;
		} else if(display[1] == true)
		{
			return GameStatus.INSTRUCTIONS;
		} else 
		{
			return GameStatus.LEADERBOARD;

		}
	}
	
	
	
}
