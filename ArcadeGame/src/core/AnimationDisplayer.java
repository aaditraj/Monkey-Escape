package core;

import processing.core.PApplet;
import processing.core.PImage;
/**
 * Allows character animations to be displayed
 * Iterates thorugh image set, and uses frequency to 
 * determine how fast to iterate though the image set.
 * eg. Running animation
 *
 *
 *@author Adithya
 */
public class AnimationDisplayer {
	String[] images;
	int frequency = 10;
	int position;
	int time = 0;
	boolean animate = true;
	/**
	 * Defines a new Animation displayer, that takes in the images 
	 * it needs to iterate through
	 * @param imgs
	 */
	public AnimationDisplayer(String[] imgs) {
		images = imgs;
		time = 0;
		position = 0;
	}
	/**
	 * Sets the images of the function to the image array passed in
	 * @param imgs
	 */
	public void setImages(String[] imgs) {
		images = imgs;
		time = 0;
		position = 0;
	}
	/**
	 * Makes the image returned stay the same
	 * Meaning the animator does not animate the image
	 */
	public void setAnimateFalse() {
		animate = false;
	}
	/**
	 * Returns the image that should be displayed, when this image changes, it looks like 
	 * the character is animated
	 * @param surface the screen to display the image on
	 * @return the image to be displayed by surface
	 */
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
	/**
	 * Changes the position, so the image returned is of the given position.
	 * @param position
	 */
	public void goToImage(int position) {
		this.position = position;
		//System.out.println(this.position);
	}
	/**
	 * Sets the frequency/speed of the animation to the passed in speed
	 * @param speed the animation speed
	 */
	public void setFrequency(int speed) {
		frequency = speed;
	}
	/**
	 * 
	 * @return The speed of animation
	 */
	public int getFrequency() {
		return frequency;
	}
	/**
	 * 
	 * @return the position of the image being returned
	 */
	public int getPosition() {
		return position;
	}
}
