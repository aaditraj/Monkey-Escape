package core;
import java.util.ArrayList;

import processing.core.PApplet;
public class Collider {
	    protected double x;
		protected double y;
	    private double width, height;
		protected double health;
		private double initX;
		private double initY;
	    protected double vx, vy;
	    private final double DEFAULT_SPEED = 20; 
	    private int maxSpeed = 20;
	    private boolean stationary;
	    int time = 0;
	    AnimationDisplayer displayer;
	    public Collider(String[] images, double health, double x, double y, double width, double height, double vx, double vy) {
	    	this.health = health;
	    	displayer = new AnimationDisplayer(images);
	    	this.x = x;
	    	this.y = y;
	    	this.initX = x;
	    	this.initY = y;
	    	this.width = width;
	    	this.height = height;
	    	this.vx = vx; 
	    	this.vy = vy;
	    	stationary = true;
	    }
	    
	    public void setMobile(boolean b) {
	    	stationary = !b;
	    }
	    public void setImages(String[] images) {
	    	displayer.setImages(images);
	    }
	    public boolean isMovable() {
	    	return !stationary;
	    }
	    public int getImgFrequency() {
	    	return displayer.getFrequency();
	    }
	    public boolean[] intersects(ArrayList<Collider> colliderList) {
			boolean[] data = new boolean[4]; // north, east, south, west
			for (int i = 0; i < colliderList.size(); i++) {
				Collider collider = colliderList.get(i);
				Line[] otherLines = collider.getLinesBundle(maxSpeed);
				if(collider != this) {
					Line[] lines = this.getLines();
					for (int j = 0; j < 4; j++) {
						Line line = lines[j]; // top, right, bottom, left
						for (int k = 0; k < 4*maxSpeed; k++) {
							Line otherLine = otherLines[k];
							
							if (line.isCollinear(otherLine)) {
								changeHealth(collide(collider) * -1);
								//System.out.println("J: " + j + "K: " + k + colliderList);
								int moveDist = k%maxSpeed;
								if(isMovable()) {
									switch(j) {
										case 0:
											y += moveDist; 
										break;
										case 1:
											x -= moveDist;
										break;
										case 2:
											y-=moveDist;
										break;
										case 3:
											x += moveDist;
										break;	
									}
								} else {
										switch(j) {
										case 0:
											collider.superMove(0, -moveDist);
										break;
										case 1:
											collider.superMove(moveDist, 0);
										break;
										case 2:
											collider.superMove(0, moveDist);
										break;
										case 3:
											collider.superMove(-moveDist, 0);
										break;	
									}
								}
								data[j] = true;
							}
						}
					}
				}
			}
			return data;
		}
		/**
		 * use this method for checking if something collides with a specific object. E.g. mobile enemy
		 * collides with player.
		 */
		public boolean intersects(Collider collider) {
			Collider other2 = (Collider) collider;
			return (other2.isPointInside(getX(), getY()) || other2.isPointInside(getX() + width, getY()) || other2.isPointInside(getX() + width, getY() + height) || other2.isPointInside(getX(), getY() + height)
					|| isPointInside(other2.getX(), other2.getY()) || isPointInside(other2.getX() + other2.getWidth(), other2.getY()) || 
					isPointInside(other2.getX() + other2.getWidth(), other2.getY() + other2.getHeight()) || isPointInside(other2.getX(), other2.getY() + other2.getHeight()));
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
		public void setFrequency(int frequency) {
			displayer.setFrequency(frequency);
		}
		public void superMove(int x, int y) {
			this.x += x;
			this.y += y;
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
			marker.image(displayer.getImage(marker), (float)(int)(x), (float)(int)y, (float)width, (float) height);
			time++;
			marker.pop();
		}
		
		/**
		 * Translates this shape to a new location. 
		 * 
		 * @param x The new x-coordinate of the reference point.
		 * @param y The new y-coordinate of the reference point.
		 */
		public void setPoint(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public void setAnimationFalse() {
			displayer.setAnimateFalse();
		}
		
		/**
		 * Moves this shape by a given horizontal and vertical distance.
		 * 
		 * @param x The horizontal distance added to the x-coordinate of the reference point.
		 * @param y The vertical distance added to the y-coordinate of the reference point.
		 */
		public void moveBy(double x, double y, ArrayList<Collider> colliders) {
			boolean[] directions = intersects(colliders);
			
			if ((!directions[0] && y < 0) || (!directions[2] && y > 0)) {
				this.y += y;
			} 
			if ((!directions[1] && x > 0) || (!directions[3] && x < 0)) {
				this.x += x;
			}
		}
		
		/*
		 * @pre imageIndex must be within range of how many images there are for this Collider
		 * remember that currentImage is an index as well
		 */
		public void goToImage(int imageIndex) {
			displayer.goToImage(imageIndex);
		}
		
		public void changeHealth(double d) {
			health += d;
		}
		


		public double getY() {
			return y;
		}

		public double getX() {
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
		
		public void changeWidth(double width) {
			this.width += width;
		}
		
		public void changeHeight(double height) {
			this.height += height;
		}
		
		public Line[] getLinesBundle(int maxSpeed) {
			Line[] lines = new Line[4*maxSpeed];
			for(int i = 0; i < maxSpeed; i++) {
				lines[i] = new Line((int)x, (int)y+i, (int)x + width, (int)y+i);
			}
			for (int i = 0; i < maxSpeed; i++) {
				lines[i+maxSpeed] = new Line((int)x + width-i, (int)y,(int) x + width-i, (int)y + height);
			}
			for (int i = 0; i < maxSpeed; i++) {
				lines[i + 2 *maxSpeed] = new Line((int)x, (int)y + height-i, (int)x + width, (int)y + height-i);
			}
			for (int i = 0; i < maxSpeed; i++) {
				lines[i+3*maxSpeed] = new Line((int)x+i, (int)y, (int)x+i, (int)y + height);
			}
			//Old line system
			//lines[0] = new Line(x, y, x + width, y); // top
			//lines[1] = new Line(getCenterX(), y, getCenterX(), y + height/2); // vertical middle
			//lines[2] = new Line(x + width, y, x + width, y + height); // right
			//lines[3] = new Line(x + width/2, getCenterY(), x + width, getCenterY()); // horiz middle
			//lines[4] = new Line(x, y + height, x + width, y + height); // bottom
			//lines[5] = new Line(getCenterX(), y + height/2, getCenterX(), y + height); // vertical middle
			//lines[6] = new Line(x, y, x, y + height); // left
			//lines[7] = new Line(x, getCenterY(), x + width/2, getCenterY()); // horiz middle
			return lines;
			
		}
		public Line[] getLines() {
			Line[] lines = new Line[4];
			lines[0] = new Line((int)x, (int)y, (int)x + width, (int)y); // top
			lines[1] = new Line((int)x + width, (int)y, (int)x + width, (int)y + height); //right
			lines[2] = new Line((int)x, (int)y + height, (int)x + width, (int)y + height); // bottom
			lines[3] = new Line((int)x, (int)y, (int)x, (int)y + height); // left
			return lines;
			
		}
		
		public void act(ArrayList<Collider> colliders) {
			moveBy(vx, vy, colliders);
		}
		//returns how much damage to take and does other actions to collider
		public double collide(Collider collider) {
			return 0.0;
		};
		
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

		public double getInitX() {
			return initX;
		}

		public double getInitY() {
			return initY;
		}
		
		public double getVX() {
			return vx;
		}

		public double getVY() {
			return vy;
		}
		
		public int getCurrentImage() {
			return displayer.getPosition();
		}
   
   
		public void setVelocity(double run, double rise) {
			double currentSpeed = Math.sqrt(Math.pow(rise, 2) + Math.pow(run, 2));
			if (currentSpeed > DEFAULT_SPEED) {
				rise /= currentSpeed/DEFAULT_SPEED;
				run /= currentSpeed/DEFAULT_SPEED;
			} else {
				rise *= DEFAULT_SPEED/currentSpeed;
				run *=  DEFAULT_SPEED/currentSpeed;
			}
			this.vx = run;
			this.vy = rise;
		}

}
