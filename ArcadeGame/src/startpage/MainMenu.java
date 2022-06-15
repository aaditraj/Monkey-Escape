package startpage; 
import levels.GameStatus;
import processing.core.PApplet;
import processing.core.PImage;
public class MainMenu {
	public boolean[] display = new boolean[4];
	int index;
	int counter;
	PImage bg;
	private String first = "Use Arrow Keys To Navigate";
	private String second = "Press Enter To Begin";
	private String current = first;
	private int freq = 2;

	
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
				index = 3; 
				display[index] = true;
			} else {
				index--; 
				display[index] = true;
			}
			
		}
		else {
			display[index] = false;
			
			if(index == 3)
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
		marker.textSize((float)(marker.width/20));
	

	
		marker.noFill();
		marker.strokeWeight(10);
		if(display[0] == true)
		{
			marker.text("ENDLESS", (float)(marker.width/3.3), (float)(marker.height/1.5));
			marker.text("INSTRUCTIONS", (float)(marker.width/5.52), (float)(marker.height/1.3));
			marker.text("LEADERBOARD", (float)(marker.width/4.9), (float)(marker.height/1.15));
			marker.fill(255,255,0);
			marker.text("SINGLEPLAYER", (float)(marker.width/5.357), (float)(marker.height/1.80));
			marker.noFill();
			marker.stroke(255, 255, 0);
			if(counter % freq != 0)
			{
				marker.rect((float)(marker.width/5.85), (float)(marker.height/2.15), (float)(marker.width/1.6), (float)(marker.height/9.6));
			}
			
		}
		else if(display[1] == true)
		{
			marker.text("SINGLEPLAYER", (float)(marker.width/5.357), (float)(marker.height/1.80));
			marker.text("LEADERBOARD", (float)(marker.width/4.9), (float)(marker.height/1.15));
			marker.text("INSTRUCTIONS", (float)(marker.width/5.52), (float)(marker.height/1.3));
			marker.fill(255,255,0);
			marker.text("ENDLESS", (float)(marker.width/3.3), (float)(marker.height/1.5));
			marker.noFill();
			marker.stroke(255, 255, 0);
			if(counter % freq != 0)
			{
				marker.rect((float)(marker.width/5.25), (float)(marker.height/1.71), (float)(marker.width/1.73), (float)(marker.height/9.71));
			}
			
		}
		else if(display[2] == true)
		{
			marker.text("ENDLESS", (float)(marker.width/3.3), (float)(marker.height/1.5));
			marker.text("SINGLEPLAYER", (float)(marker.width/5.357), (float)(marker.height/1.80));
			marker.text("LEADERBOARD", (float)(marker.width/4.9), (float)(marker.height/1.15));
			marker.fill(255,255,0);
			marker.text("INSTRUCTIONS", (float)(marker.width/5.52), (float)(marker.height/1.3));
			marker.noFill();
			marker.stroke(255, 255, 0);
			if(counter % freq != 0)
			{
				
				marker.rect((float)(marker.width/6.1), (float)(marker.height/1.48), (float)(marker.width/1.6), (float)(marker.height/8.5));
			}
		}
		else if(display[3] == true)
		{
			marker.text("INSTRUCTIONS", (float)(marker.width/5.52), (float)(marker.height/1.3));
			marker.text("ENDLESS", (float)(marker.width/3.3), (float)(marker.height/1.5));
			marker.text("SINGLEPLAYER", (float)(marker.width/5.357), (float)(marker.height/1.80));
			marker.fill(255,255,0);
			marker.text("LEADERBOARD", (float)(marker.width/4.9), (float)(marker.height/1.15));
			marker.noFill();
			marker.stroke(255, 255, 0);
			if(counter % freq != 0)
			{
				
				marker.rect((float)(marker.width/5.56), (float)(marker.height/1.28), (float)(marker.width/1.75), (float)(marker.height/8.7));
			}
		}
		
		marker.textSize(marker.width/50);
		marker.fill(marker.color(255,255,255));
		if(counter % (2*freq) != 0)
		{
			
			marker.text(current, (float)(marker.width/3.7), marker.height * 0.95f);
			
			
		}
		else {
			if(current.equals(first)) current = second;
			else if(current.equals(second)) current = first;
		}
		marker.pop();
		
		counter++; 
	}
	
	public int passMenu()
	{
		if(display[0] == true)
		{
			return GameStatus.SINGLE_PLAYER;
		} else if(display[1] == true)
		{
			return GameStatus.ENDLESS;
		} else if(display[2] == true)
		{
			return GameStatus.INSTRUCTIONS;
		} else 
		{
			return GameStatus.LEADERBOARD;

		}
	}
	
	
	
}
