import java.util.ArrayList;

public class Lava extends Collider{
//	private double riseRate; 
	
	public Lava(double health, double x, double y, double width, double height, double riseRate) {
		super(new String[] {"assets/Projectiles/lava.png"}, health, x, y, width, height, 0, riseRate);
//		this.riseRate = riseRate; 
		// TODO Auto-generated constructor stub
	}
	
	public void reset()
	{
		this.setPoint((int)getInitX(), (int)getInitY());
	}
	
	public void increaseHeight()
	{
		this.changeHeight(-vy);
		this.moveBy(vx, 0, new ArrayList<Collider>());
//		this.act(colliders);;
	}
	


}
