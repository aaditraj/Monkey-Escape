	
import java.util.ArrayList;

import processing.core.PApplet;



public class DrawingSurface extends PApplet {

	private Collider collider1;
	private Collider collider2;
	private ShootingPlayer player;
	private MobileEnemy enemy1;
	private ShootingEnemy enemy2;
	private SideShooter sideShooter;
	ArrayList<Collider> bullets = new ArrayList<>();
	int playerSpeed = 5;
	public DrawingSurface() {
		player = new ShootingPlayer(100,100,100,150d,100d,0,0,3000);
		                           // health, startx, starty, endx, endy, width, height, vx, vy, direction
		//collider1 = new MobileEnemy(10d, 0d, 150d, 400d, 150d, 20d, 20d, 5d, 0d, 1);
		//collider2 = new MobileEnemy(10d, 400d, 150d, 0d, 150d, 20d, 20d, -5d, 0d, 1);
		//collider1 = new Collider(new String[] {"assets/MobileEnemy/gorilla-2.png"}, 10d, 0d, 151d, (int)404d/4, (int)434d/4, 2d, 0d);
		collider2 = new Collider(new String[] {"assets/MobileEnemy/gorilla.png"}, 100, 400, 150, (int)424d/4, (int)464d/4, 0d, 0d);
	}
	
	
	public void setup() {
		
	}
	
	
	public void draw() {
		background(50);
//		collider1.act(new Collider[] {collider2}, 1);
//		collider2.act(new Collider[] {collider1}, 0);
		player.draw(this);
		collider2.draw(this);
		bullets.add(collider2);
		for(Collider bullet : bullets) {
			//System.out.println("hi");
			bullet.draw(this);
			bullet.act(bullets);
		}
//		fill(255, 0, 0);
//		collider1.draw(this);
//		fill(0, 255, 0);
//		collider2.draw(this);
//		
//
//		fill(0, 0, 255);
//		Line[] l = collider1.getLines();
//		
//		Line[] l2 = collider2.getLines();
//		for(Line line : l) {
//			line.draw(this);
//		}
//		for(Line line : l2) {
//			line.draw(this);
//		}
//		
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
		bullets.add(player.shoot(mouseX, mouseY));
	}		
	
	public void keyPressed() {
		if (keyCode == UP) { 
			player.moveBy(0, -playerSpeed, bullets);
			
		} 
		if (keyCode == DOWN) { 
			player.moveBy(0, playerSpeed, bullets);
		}
		if (keyCode == RIGHT) {
			player.moveBy(playerSpeed, 0, bullets);

		}
		if (keyCode == LEFT) {
			player.moveBy(-playerSpeed, 0, bullets);

		}
		if (key == 'w') {
			player.moveBy(0, -playerSpeed, bullets);

		} 
		if (key == 'd') {
			player.moveBy(playerSpeed, 0, bullets);

		}
		if (key == 's') {
			player.moveBy(0, playerSpeed, bullets);
		
		}
		if (key == 'a') {
			player.moveBy(-playerSpeed, 0, bullets);
	
		}
		//etc.
	}


}
