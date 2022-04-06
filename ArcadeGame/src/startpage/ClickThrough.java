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
			imgList.add("assets/StartPage/ClickThroughOne.png");
			imgList.add("assets/StartPage/ClickThrough2.png");
			imgList.add("assets/StartPage/ClickThroughThree.png");
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
