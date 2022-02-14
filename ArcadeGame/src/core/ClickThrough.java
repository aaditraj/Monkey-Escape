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
			imgList.add("assets/Projectiles/bullet.png");
			imgList.add("assets/Projectiles/Coconut.png");
			imgList.add("assets/Backgrounds/forest1.jpeg");
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
