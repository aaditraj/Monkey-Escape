package obstacles;
import core.Collider;

public class Platform extends Collider {
	
	private boolean doesDamage;
	
	// possible values of images: {"assets/Platform/leftTreeBranch.png"}, {"assets/Platform/rightTreeBranch.png"}, {"assets/Platform/treeBranch.png"}
	public Platform(double x, double y, double width, double height, boolean doesDamage) {
				
			super(new String[] {"assets/Projectiles/treeBranch.png"}, 10, x, y, width, height, 0, 0);
			this.doesDamage = doesDamage;
	}
	
	public boolean doesDamage() {
		return doesDamage;
	}
			
}


