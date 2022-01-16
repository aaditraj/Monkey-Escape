	
import java.util.ArrayList;

import processing.core.PApplet;



public class DrawingSurface extends PApplet {

	private Collider collider1;
	private Collider collider2;
	
	public DrawingSurface() {
		                           // health, startx, starty, endx, endy, width, height, vx, vy, direction
//		collider1 = new MobileEnemy(10d, 0d, 150d, 400d, 150d, 20d, 20d, 5d, 0d, 1);
//		collider2 = new MobileEnemy(10d, 400d, 150d, 0d, 150d, 20d, 20d, -5d, 0d, 1);
		collider1 = new Collider(new String[5], 10d, 0d, 150d, 40d, 30d, 5d, 0d);
		collider2 = new Collider(new String[5], 10d, 400d, 150d, 20d, 20d, -5d, 0d);
	}
	
	
	public void setup() {
		
	}
	
	
	public void draw() {
		background(50);
		collider1.act(new Collider[] {collider2});
		collider2.act(new Collider[] {collider1});
		fill(255, 0, 0);
		collider1.draw(this);
		fill(0, 255, 0);
		collider2.draw(this);
		
		
	}
	
	
	public void mousePressed() {
	}		
	
	public void keyPressed() {
		if (keyCode == UP) { 
			
			
		} else if (keyCode == DOWN) { 
			
			
		}
		//etc.
	}


}
