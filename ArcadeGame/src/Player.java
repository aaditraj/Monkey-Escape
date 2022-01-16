
public abstract class Player extends Collider{
	private double points, ammo; 
	private double reloadTime; 
	private boolean isJumping; 

	public Player(String[] images, double health, double x, double y, double width, double height, double vx,
			double vy, double reloadTime) {
		super(images, health, x, y, width, height, vx, vy);
		this.reloadTime = reloadTime; 
		// TODO Auto-generated constructor stub
	}
	
	public abstract Collider shoot(int mouseX, int mouseY);
	
	

}
