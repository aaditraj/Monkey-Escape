package core;

import processing.core.PApplet;
import processing.core.PImage;

public class AnimationDisplayer {
	String[] images;
	int frequency = 10;
	int position;
	int time = 0;
	boolean animate = true;
	public AnimationDisplayer(String[] imgs) {
		images = imgs;
		time = 0;
		position = 0;
	}
	public void setImages(String[] imgs) {
		images = imgs;
		time = 0;
		position = 0;
	}
	public void setAnimateFalse() {
		animate = false;
	}
	public PImage getImage(PApplet surface) {
		if(time%frequency == 0 && animate) {
			position++;
			if(position > images.length - 1) {
				position = 0;
			}
		}
		time++;
		return surface.loadImage(images[position]);
	}
	public void goToImage(int position) {
		this.position = position;
		System.out.println(this.position);
	}
	public void setFrequency(int speed) {
		frequency = speed;
	}
	public int getFrequency() {
		return frequency;
	}
	public int getPosition() {
		return position;
	}
}
