package startpage; 
import levels.GameStatus;
import processing.core.PApplet;
import processing.core.PImage;
public class MainMenu {
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
		
	}
	
	
	public void draw(PApplet marker) {
		bg = marker.loadImage("assets/StartPage/MainMenu.png");
		marker.image(bg, 0, 0, marker.width, marker.height);
		
//		marker.rect(rectx1, recty1, rectXSize, rectYSize);
//		marker.rect(rectx2, recty2, rectXSize, rectYSize);
//		marker.rect(rectx3, recty3, rectXSize2, rectYSize2);

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
