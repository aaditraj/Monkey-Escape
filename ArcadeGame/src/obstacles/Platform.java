package obstacles;
import core.Collider;
import players.Player;

public class Platform extends Collider {
	
	private boolean doesDamage;
	
	// possible values of images: {"assets/Platform/leftTreeBranch.png"}, {"assets/Platform/rightTreeBranch.png"}, {"assets/Platform/treeBranch.png"}
	/**
	 * The Platform constructor
	 * @param x Platforms x-position
	 * @param y Platforms y-position
	 * @param width platform's width
	 * @param height platforms height
	 * @param doesDamage Checks if the platform does damamge or not.
	 */
	public Platform(double x, double y, double width, double height, boolean doesDamage) {
			super(new String[] {"assets/Platform/rock-platform.png"}, 10, x, y, width, height, 0, 0);
			switch((int)width) {
			case 100:
				setImages(new String[] {"assets/Platform/platform100.png"});
				break;
			case 200:
				setImages(new String[] {"assets/Platform/platform200.png"});
				break;
			case 300:
				setImages(new String[] {"assets/Platform/platform300.png"});
				break;	
			case 400:
				setImages(new String[] {"assets/Platform/platform400.png"});
				break;
			case 500:
				setImages(new String[] {"assets/Platform/platform500.png"});
				break;
			case 600:
				setImages(new String[] {"assets/Platform/platform600.png"});
				break;
			}
			this.doesDamage = doesDamage;
			if (doesDamage) {
				setImages(new String[] {"assets/lava.png"});
			}
	}
	/**
	 * 
	 * @param image
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param doesDamage
	 */
	public Platform(String image, double x, double y, double width, double height, boolean doesDamage) {
		super(new String[] {image}, 10, x, y, width, height, 0, 0);
		this.doesDamage = doesDamage;
		if (doesDamage) {
			setImages(new String[] {"assets/lava.png"});
		}
	}
	/**
	 * Checks if the platform does damage
	 * @return If the does damage
	 */
	public boolean doesDamage() {
		return doesDamage;
	}
	/**
	 * Collide with the player, if th platform does damage
	 */
	public double collide(Collider collider) {
		if (collider instanceof Player && doesDamage) {
			collider.changeHealth(-0.5);
		}
		return 0.0;
	}

			
}


