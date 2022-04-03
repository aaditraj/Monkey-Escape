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
		marker.textSize(90);
	
	
		marker.noFill();
		marker.strokeWeight(10);
		if(display[0] == true)
		{
			marker.text("INSTRUCTIONS", 230, 700);
			marker.text("LEADERBOARD", 260, 850);
			marker.fill(0,0,0);
			if(counter % 4 != 0)
			{
				marker.text("START GAME", 280, 550);
				marker.noFill();

				marker.rect(270, 450, 895, 120);
			}
			
		}
		else if(display[1] == true)
		{
			
			marker.text("START GAME", 280, 550);
			marker.text("LEADERBOARD", 260, 850);
			marker.fill(0,0,0);
			if(counter % 4 != 0)
			{
				marker.text("INSTRUCTIONS", 230, 700);
				marker.noFill();

				marker.rect(220, 600, 1070, 120);
			}
			
		}
		else if(display[2] == true)
		{
			marker.text("INSTRUCTIONS", 230, 700);
			marker.text("START GAME", 280, 550);
			marker.fill(0,0,0);
			if(counter % 4 != 0)
			{
				marker.text("LEADERBOARD", 260, 850);
				marker.noFill();

				marker.rect(250, 750, 970, 120);
			}
		}
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
