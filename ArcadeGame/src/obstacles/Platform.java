package obstacles;
import core.Collider;
import players.Player;

public class Platform extends Collider {
	
	private boolean doesDamage;
	
	// possible values of images: {"assets/Platform/leftTreeBranch.png"}, {"assets/Platform/rightTreeBranch.png"}, {"assets/Platform/treeBranch.png"}
	public Platform(double x, double y, double width, double height, boolean doesDamage) {
			super(new String[] {"assets/Platform/rock-platform.png"}, 10, x, y, width, height, 0, 0);
			this.doesDamage = doesDamage;
			if (doesDamage) {
				setImages(new String[] {"assets/lava.png"});
			}
	}
	
	public Platform(String image, double x, double y, double width, double height, boolean doesDamage) {
		super(new String[] {image}, 10, x, y, width, height, 0, 0);
		this.doesDamage = doesDamage;
		if (doesDamage) {
			setImages(new String[] {"assets/lava.png"});
		}
	}
	
	public boolean doesDamage() {
		return doesDamage;
	}
	
	public double collide(Collider collider) {
		if (collider instanceof Player && doesDamage) {
			collider.changeHealth(-0.5);
		}
		return 0.0;
	}

			
}


