import processing.core.PApplet;
import processing.core.PImage;

public class Instructions extends PApplet {
	String location = "";
	public Instructions() {
		location = "assets/StartPage/Instructions.png";
	}
	public void draw(PApplet marker) {
		marker.push();
		marker.image(marker.loadImage(location),0,0);
	}
}
