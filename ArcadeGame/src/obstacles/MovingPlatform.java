package obstacles;

import enemies.MobileEnemy;

public class MovingPlatform extends MobileEnemy {
	
	public static String[] movingPlatformImage = new String[] {"assets/Projectiles/treeBranch.png"};

	public MovingPlatform(String[] images, double startX, double startY,  double endX, double endY, double vx, double vy, double width, double height) {
		super(images, Integer.MAX_VALUE, startX, startY, endX, endY, vx, vy, width, height);
		this.setMobile(false);
	}

}