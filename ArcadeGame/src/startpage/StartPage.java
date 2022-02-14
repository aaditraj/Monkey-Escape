package startpage; 
import processing.core.PApplet;
import processing.core.PImage;
public class StartPage {
	PImage bg;

	public void draw(PApplet marker) {
		bg = marker.loadImage("assets/BeginPage.png");
		marker.image(bg, 0, 0, marker.width, marker.height);

		
	}
	
	
}
