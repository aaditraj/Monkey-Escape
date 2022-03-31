package startpage;
import Levels.LevelSwitch;
import processing.core.PApplet;
import processing.core.PImage;

public class Instructions  {
	String location = "";
	PImage bg;
	
	int rectx1; 
	int recty1;
	
	public Instructions() {
		location = "assets/StartPage/Instructions.png";

	}
	public void draw(PApplet marker) {
		bg = marker.loadImage(location);
		marker.image(bg, 0, 0, marker.width, marker.height);
		
	}
	
	
	
	
	public int checkClicked(int mouseX, int mouseY, int width, int height)
	{
		rectx1 = 60;
		recty1 = (int)(height/25);
		
		if(mouseX > rectx1 && mouseX < rectx1 + 150)
		{
			if(mouseY > recty1 && mouseY < recty1 + 50) return LevelSwitch.MAIN_MENU; 
		}
		
		return LevelSwitch.INSTRUCTIONS;
		
	}
}
