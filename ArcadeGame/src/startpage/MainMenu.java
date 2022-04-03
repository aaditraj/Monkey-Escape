package startpage; 
import levels.GameStatus;
import processing.core.PApplet;
import processing.core.PImage;
public class MainMenu {
	public boolean[] display = new boolean[3];
	int index;
	int counter;
	PImage bg;
	
	int rectx1; 
	int recty1;
	
	int rectx2; 
	int recty2;
	
	int rectx3; 
	int recty3;
	
	int rectXSize;  
	int rectYSize; 
	
	int rectXSize2; 
	int rectYSize2;  

	int rectSize3;
	
	boolean rectOver1 = false;
	boolean rectOver2 = false;
	boolean rectOver3 = false;

	
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
	
	public int checkClicked(int mouseX, int mouseY, int width, int height)
	{
		rectx1 = width/21;
		recty1 = (int)(height/3.5);
		
		rectXSize = (int)(width/2.47);  
		rectYSize = (int)(height/5.5);
		
		rectx2 = (int)(width/1.82); 
		recty2 = recty1;

	
		rectx3 = width/11; 
		recty3 = (int)(height/1.7);
		
		rectXSize2 = 2*(int)(width/2.47);  
		rectYSize2 = (int)(height/5.5);
		
		
		if(mouseX > rectx1 && mouseX < rectx1 + rectXSize)
		{
			if(mouseY > recty1 && mouseY < recty1 + rectYSize) 
				{
				return GameStatus.LEADERBOARD; 
				}
		}
		
		if(mouseX > rectx2 && mouseX < rectx2 + rectXSize)
		{
			if(mouseY > recty2 && mouseY < recty2 + rectYSize) return GameStatus.INSTRUCTIONS; 
		}
		
		if(mouseX > rectx3 && mouseX < rectx3 + rectXSize2)
		{
			if(mouseY > recty3 && mouseY < recty3 + rectYSize2) {
	
				System.out.println("inside");
				return GameStatus.STARTED; 
			
			}
		
		}
		
		return GameStatus.MAIN_MENU;
	}
	
	
}
