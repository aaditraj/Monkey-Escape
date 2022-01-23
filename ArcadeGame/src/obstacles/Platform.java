package obstacles;
import core.Collider;

public class Platform extends Collider {
	
	private boolean doesDamage;
	
	// possible values of images: {"assets/Platform/leftTreeBranch.png"}, {"assets/Platform/rightTreeBranch.png"}, {"assets/Platform/treeBranch.png"}
	public Platform(String[] images, double x, double y, double width, double height, boolean doesDamage) {
				
			super(images, 10, x, y, width, height, 0, 0);
			this.doesDamage = doesDamage;
	}
	
	public boolean doesDamage() {
		return doesDamage;
	}
			
}


