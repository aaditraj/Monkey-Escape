package core;

import java.util.ArrayList;

import processing.core.PApplet;

public class ClickThrough {
	ArrayList<String> imgList = new ArrayList<>();
	int currPos = 0;
	public boolean isFinished;
	public ClickThrough(String imageSet) {
		switch(imageSet) {
		case "demoSet":
			imgList.add("assets/bullet.png");
			imgList.add("assets/Coconut.png");
			imgList.add("assets/Background.png");
		break;	
		}
	}
	public void draw(PApplet marker) {
		marker.push();
		if(!isFinished) {
			marker.image(marker.loadImage(imgList.get(currPos)), marker.width, marker.height);
		}
		marker.pop();
	}
	public void next() {
		if(currPos == imgList.size()) {
			isFinished = true;
		} else {
			currPos++;
		}
	}
}
