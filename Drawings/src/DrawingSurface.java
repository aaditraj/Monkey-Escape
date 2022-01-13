	
import java.util.ArrayList;

import processing.core.PApplet;



public class DrawingSurface extends PApplet {

	private House house;
	private Person person;
	private ArrayList<Line> rain;
	private final double RAINSPEED = 2.0;
	private final double RAINDROP_LENGTH = 5.0;
	
	public DrawingSurface() {
		house = new House();
		person = new Person();
		rain = new ArrayList();
	}
	
	
	public void setup() {
		
	}
	
	
	public void draw() {
		
		// Instructions
		background(50);
		push();
		fill(255, 255, 255);
		text("Interactions: mouse clicks, up and down arrows, g, r", 10, 30);
		pop();
		
		
		// Draw the house and person
		house.draw(this);
		person.draw(this);	
		checkRainCollision();
	}
	
	
	public void mousePressed() {
		house.move(mouseX, mouseY);
	}		
	
	public void keyPressed() {
		if (keyCode == UP) { // Make the house bigger
			
			house.scale(true);
			
		} else if (keyCode == DOWN) { // Make the house smaller
			
			house.scale(false);
			
		} else if (key == 'g') { // Moves the person into the door using the house x, y, width, and height
			
			movePersonToDoor();
			
		} else if (key == 'r') { // Make rain fall from the sky to a random point on the roof
			
			createRaindrop();
		}
	}
	

	public void checkRainCollision() {
		// Loop through the list of lines, or "raindrops"
		for (int i = 0; i < rain.size(); i++) { 
			   Line raindrop = rain.get(i);
			   
			   // Check if raindrop is touching each side of the roof
			   boolean intersect1 = raindrop.intersects(house.getLeftRoof());
			   boolean intersect2 = raindrop.intersects(house.getRightRoof());
			   
			   boolean touchingRoof = intersect1 || intersect2;
			   
	           if (touchingRoof) { // if touching, destroy the raindrop
	        	   rain.remove(raindrop);
	        	   raindrop = null;
	           } else { // otherwise, draw and increase Y for falling effect
		           raindrop.draw(this);
		           raindrop.changeY(RAINSPEED);
	           }
	      }
	}
	
	public double generateSpawnPoint() {
		double startX = house.getX() - house.getWidth()/2;
		double endX = startX + house.getWidth();
		double spawnPoint = Math.random() * (endX - startX);
		spawnPoint += startX;
		return spawnPoint;
	}
	
	public void createRaindrop() {
		double spawnPoint = generateSpawnPoint();
		
		// Create raindrop and add it to ArrayList
		Line raindrop = new Line(spawnPoint, 0.0, spawnPoint, RAINDROP_LENGTH);
		raindrop.setColor(173, 216, 230);
		raindrop.setStrokeWeight(2);
		rain.add(raindrop);
	}
	
	public void movePersonToDoor() {
		person.setHeight(house.getHeight() * 3 / 8);
		person.setWidth(person.getHeight()/4);
		person.setX(house.getX() - house.getWidth()/16);
		person.setY(house.getY());
	}


}
