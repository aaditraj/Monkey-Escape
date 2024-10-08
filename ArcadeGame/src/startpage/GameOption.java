package startpage; 
import levels.GameStatus;
import processing.core.PApplet;
import processing.core.PImage;
public class GameOption {
	public boolean[] display = new boolean[2];
	int index;
	int counter;
	PImage bg;
	private String first = "Use Arrow Keys To Navigate";
	private String second = "Press Enter To Begin";
	private String current = first;
	private int freq = 2;

	
	public GameOption()
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
	

	
		marker.noFill();
		marker.strokeWeight(10);
		if(display[0] == true)
		{
			marker.text("MULTIPLAYER", (float)(marker.width/6.52), (float)(marker.height/1.413));
			marker.text("RETURN TO MENU", (float)(marker.width/5.77), (float)(marker.height/1.16));
			marker.fill(255,255,0);
			marker.text("SINGLEPLAYER", (float)(marker.width/5.357), (float)(marker.height/1.80));
			marker.noFill();
			marker.stroke(255, 255, 0);
			if(counter % freq != 0)
			{
				

				marker.rect((float)(marker.width/5.55), (float)(marker.height/2.2), (float)(marker.width/1.676), (float)(marker.height/8.24));
			}
			
		}
		else if(display[1] == true)
		{
			
			marker.text("SINGLEPLAYERE", (float)(marker.width/5.36), (float)(marker.height/1.8));
			marker.text("RETURN TO MENU", (float)(marker.width/5.77), (float)(marker.height/1.16));
			marker.fill(255,255,0);
			marker.text("MULTIPLAYER", (float)(marker.width/6.52), (float)(marker.height/1.41));
			marker.noFill();
			marker.stroke(255, 255, 0);
			if(counter % freq != 0)
			{
				

				marker.rect((float)(marker.width/6.82), (float)(marker.height/1.65), (float)(marker.width/1.4), (float)(marker.height/8.24));
			}
			
		}
		else if(display[2] == true)
		{
			marker.text("MULTIPLAYER", (float)(marker.width/6.52), (float)(marker.height/1.413));
			marker.text("SINGLEPLAYER", (float)(marker.width/5.357), (float)(marker.height/1.80));
			marker.fill(255,255,0);
			marker.text("RETURN TO MENU", (float)(marker.width/5.77), (float)(marker.height/1.16));
			marker.noFill();
			marker.stroke(255, 255, 0);
			if(counter % freq != 0)
			{
				
				marker.rect((float)(marker.width/6.2), (float)(marker.height/1.32), (float)(marker.width/1.5), (float)(marker.height/8.24));
			}
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
			return GameStatus.MULTI_PLAYER;
		} else 
		{
			return GameStatus.MAIN_MENU;

		}
	}
	
	
	
}
