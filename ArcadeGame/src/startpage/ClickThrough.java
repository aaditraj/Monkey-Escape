package startpage;

import java.util.ArrayList;

import processing.core.PApplet;

public class ClickThrough {
	ArrayList<String> imgList = new ArrayList<>();
	int currPos = 0;
	public boolean isFinished;
	public ClickThrough(String imageSet) {
		switch(imageSet) {
		case "realSet":
			imgList.add("assets/Backgrounds/forest1.jpeg");
			imgList.add("assets/Backgrounds/forest2.jpg");
			imgList.add("assets/Backgrounds/volcano-bg.jpg");
		break;	
		}
	}
	public void draw(PApplet marker) {
		if(!isFinished) {
			marker.image(marker.loadImage(imgList.get(currPos)),0,0,marker.width, marker.height);
		}
	}
	public void next() {
		System.out.println(currPos);
		currPos++;
		if(currPos >= imgList.size()) {
			isFinished = true;
		}	
	}
}
