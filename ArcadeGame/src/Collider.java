import java.awt.Color;

import javax.swing.*;

import processing.core.PApplet;
import processing.core.PImage;

public class Collider {
	
	    private double x, y, width, height, health, initX, initY;
	    private PImage[] images;
	    private int currentImage = 0;
	    
	    public Collider(PImage[] images, int health, int x, int y, int width, int height) {
	    	this.health = health;
	    	this.images = images;
	    	this.x = x;
	    	this.y = y;
	    	this.initX = x;
	    	this.initY = y;
	    	this.width = width;
	    	this.height = height;
	    }
	    
	    /** 
		 * Determines whether this rectangle is touching the other rectangle.
		 * 
		 * @param other The other rectangle to test intersection.
		 * @pre The object passed in must be of type Rectangle.
		 * @return true if the rectangles are overlapping or touching, false otherwise.
		 */
		public boolean[] intersects(Collider[] colliderList) {
			boolean[] directions = new boolean[4]; // north, east, south, west
			
			for (int i = 0; i < colliderList.length; i++) {
				Collider collider = colliderList[i];
				Line[] otherLines = collider.getLines();
				Line[] lines = this.getLines();
				for (int j = 0; j < 4; j++) {
					Line line = lines[j]; // top, right, bottom, left
					for (int k = 0; k < 4; k++) {
						Line otherLine = otherLines[k];
						if (line.intersects(otherLine)) {
							directions[k] = true;
						}
					}
				}
			}
			return directions;
			
		}
		
		
		
		/** 
		 * Determines whether the point x,y is contained inside this rectangle.
		 * 
		 * @param x The x position of the point.
		 * @param y The y position of the point.
		 * @return true if the point is inside the rectangle, false otherwise.
		 */
		public boolean isPointInside(double x, double y) {
			return (x >= getX() && x <= getX() + width && y >= getY() && y <= getY() + height);
		}
		
		
		/** 
		 * Draws the rectangle to the given Processing PApplet. The left and right
		 * edges of the rectangle at x and x + width. The top and bottom edges
		 * are at y and y + height.
		 * 
		 * @post Draws a rectangle to the PApplet.
		 * @param marker The Processing PApplet on which to draw the rectangle.
		 */
		public void draw(PApplet marker) {
			marker.push();
			marker.image(images[currentImage], (float) x, (float) y, (float) width, (float) height);
//			setDrawSettings(marker);
//			marker.rect((float) getX(), (float) getY(), (float) width, (float) height);
			marker.pop();
		}
		
		/**
		 * Translates this shape to a new location. 
		 * 
		 * @param x The new x-coordinate of the reference point.
		 * @param y The new y-coordinate of the reference point.
		 */
		public void setPoint(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
		
		/**
		 * Moves this shape by a given horizontal and vertical distance.
		 * 
		 * @param x The horizontal distance added to the x-coordinate of the reference point.
		 * @param y The vertical distance added to the y-coordinate of the reference point.
		 */
		public void moveBy(double x, double y, Collider[] colliders) {
			boolean[] directions = intersects(colliders);
			if ((!directions[0] && y < 0) || (!directions[2] && y > 0)) {
				this.y += y;
			} 
			if ((!directions[1] && x > 0) || (!directions[3] && x < 0)) {
				this.x += x;
			}
		}
		
		public void goToNextImage() {
			currentImage++;
			if (currentImage >= images.length) {
				currentImage = 0;
			}
		}
		
		public void changeHealth(int amount) {
			health += amount;
		}
		


		private double getY() {
			return y;
		}

		private double getX() {
			return x;
		}

		public void resize(double scale) {
			width *= scale;
			height *= scale;
		}
		
		public double getHealth() {
			return health;
		}

		public double getCenterX() {
			return getX() + width/2.0;
		}

		public double getCenterY() {
			return getY() + height/2.0;
		}
		
		public double getWidth() {
			return width;
		}
		
		public double getHeight() {
			return height;
		}
		
		public Line[] getLines() {
			Line[] lines = new Line[4];
			lines[0] = new Line(x, y, x + width, y);
			lines[1] = new Line(x + width, y, x + width, y + height);
			lines[2] = new Line(x, y + height, x + width, y + height);
			lines[3] = new Line(x, y, x, y + height);
			return lines;
		}
		
		/** 
		 * Calculates and returns the perimeter of the rectangle.
		 * 
		 * @return The perimeter of the rectangle as a double.
		 */
		public double getPerimeter() {
			return width * 2 + height * 2;
		}
		
		/** 
		 * Calculates and returns the area of the rectangle.
		 * 
		 * @return The area of the rectangle as a double.
		 */
		public double getArea() {
			return width * height;
		}
		
		/**
		 * Returns a String containing debugging info for the shape.
		 * 
		 * @return A String containing the values of the location of the top-left corner, the width, and the height.
		 */
		public String toString() {
			return super.toString() + "\nWidth: " + width + "\nHeight: " + height;
		}
   

}
