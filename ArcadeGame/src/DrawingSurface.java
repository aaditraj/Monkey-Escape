	
import java.util.ArrayList;

import processing.core.PApplet;



public class DrawingSurface extends PApplet {

	private Collider collider1;
	private Collider collider2;
	
	public DrawingSurface() {
		                           // health, startx, starty, endx, endy, width, height, vx, vy, direction
//		collider1 = new MobileEnemy(10d, 0d, 150d, 400d, 150d, 20d, 20d, 5d, 0d, 1);
//		collider2 = new MobileEnemy(10d, 400d, 150d, 0d, 150d, 20d, 20d, -5d, 0d, 1);
		collider1 = new Collider(new String[5], 10d, 0d, 151d, 41d, 30d, 2d, 0d);
		collider2 = new Collider(new String[5], 10d, 400d, 151d, 21d, 20d, -2d, 0d);
	}
	
	
	public void setup() {
		
	}
	
	
	public void draw() {
		background(50);
//		collider1.act(new Collider[] {collider2}, 1);
//		collider2.act(new Collider[] {collider1}, 0);
		fill(255, 0, 0);
		collider1.draw(this);
		fill(0, 255, 0);
		collider2.draw(this);
		

		fill(0, 0, 255);
		Line[] l = collider1.getLines();
		
		Line[] l2 = collider2.getLinesBundle(10);
		for(Line line : l) {
			line.draw(this);
		}
		for(Line line : l2) {
			line.draw(this);
		}
		
//		l[2].setStrokeWeight(10);
//		l[2].draw(this);
		
		//l[3].setStrokeWeight(1);
		//l[3].draw(this);
		
//		l[0].setStrokeWeight(10);
//		l[0].draw(this);
	
		
//		l2[7].setStrokeWeight(10);
//		l2[7].draw(this);
//		
//		l2[0].setStrokeWeight(10);
//		l2[0].draw(this);
//		
//		l2[3].setStrokeWeight(10);
//		l2[3].draw(this);
		
		//l2[6].setStrokeWeight(10);
		//l2[6].draw(this);
	
		

		
		
	}
	
	
	public void mousePressed() {
	}		
	
	public void keyPressed() {
		if (keyCode == UP) { 
			collider1.moveBy(0, -10, new Collider[] {collider2}, 1);
			
		} else if (keyCode == DOWN) { 
			collider1.moveBy(0, 10, new Collider[] {collider2}, 1);

			
		} else if (keyCode == RIGHT) {
			collider1.moveBy(10, 0, new Collider[] {collider2}, 1);

		} else if (keyCode == LEFT) {
			collider1.moveBy(-10, 0, new Collider[] {collider2}, 1);

		} else if (key == 'w') {
			collider2.moveBy(0, -10, new Collider[] {collider1}, 0);

		} else if (key == 'd') {
			collider2.moveBy(10, 0, new Collider[] {collider1}, 0);

		}
		else if (key == 's') {
				collider2.moveBy(0, 10, new Collider[] {collider1}, 0);
		
		}
		else if (key == 'a') {
			collider2.moveBy(-10, 0, new Collider[] {collider1}, 0);
	
		}
		//etc.
	}


}
