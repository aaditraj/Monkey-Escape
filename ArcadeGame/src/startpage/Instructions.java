package startpage;
import levels.GameStatus;
import processing.core.PApplet;
import processing.core.PImage;

public class Instructions  {
	String location = "";
	PImage bg;
	
	int rectx1; 
	int recty1;
	
	public Instructions() {
		location = "assets/StartPage/GuideImage.png";

	}
	public void draw(PApplet marker) {
		bg = marker.loadImage(location);
		marker.image(bg, 0, 0, marker.width, marker.height);
		
		marker.push();
		
		marker.textFont(marker.createFont("assets/ARCADE_N.TTF", 30));
		marker.text("1. Use A/D or Left/Right Arrows to move", (float)(marker.width/7), (float)(marker.height/2.2));
		marker.text("2. Use Spacebar to jump", (float)(marker.width/7), (float)(marker.height/2));
		marker.text("3. Left-Click to Shoot", (float)(marker.width/7), (float)(marker.height/1.82));


		marker.text("1. Reach Doors and advance to next level", (float)(marker.width/7), (float)(marker.height/1.5));
		marker.text("2. Kill enemies & Dodge enemy projectiles", (float)(marker.width/7), (float)(marker.height/1.4));

		marker.text("1. Kill enemies & Collect coins", (float)(marker.width/7), (float)(marker.height/1.2));
		marker.text("2. Reach the top of the leaderboard!", (float)(marker.width/7), (float)(marker.height/1.13));
		
		marker.fill(255,255,0);
		marker.text("Points", (float)(marker.width/2.3), (float)(marker.height/1.3));
		marker.text("Objective", (float)(marker.width/2.4), (float)(marker.height/1.65));
		marker.text("Controls", (float)(marker.width/2.4), (float)(marker.height/2.5));

		marker.pop(); 
	}
	
	
	
	
	public int checkClicked(int mouseX, int mouseY, int width, int height)
	{
		rectx1 = 60;
		recty1 = (int)(height/25);
		
		if(mouseX > rectx1 && mouseX < rectx1 + 150)
		{
			if(mouseY > recty1 && mouseY < recty1 + 50) return GameStatus.MAIN_MENU; 
		}
		
		return GameStatus.INSTRUCTIONS;
		
	}
}
