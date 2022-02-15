package startpage;
import processing.core.PApplet;
import processing.core.PImage;

public class Instructions extends PApplet {
	String location = "";
	PImage bg;
	
	
	public Instructions() {
		location = "assets/StartPage/Instructions.png";

	}
	public void draw(PApplet marker) {
		bg = marker.loadImage(location);

		marker.image(bg, 0, 0, marker.width, marker.height);
	}
}
