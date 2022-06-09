package obstacles;
import java.util.ArrayList;

import core.Collider;

public class Lava extends Collider{
//	private double riseRate; 
	/**
	 * Defines the lava object at the specified height
	 * @param health usually infinity
	 * @param x Lava's x-position
	 * @param y Lava's y-position
	 * @param width width of the lava
	 * @param height height of the lava
	 * @param riseRate The rate at which the lava rises
	 */
	public Lava(double health, double x, double y, double width, double height, double riseRate) {
		super(new String[] {"assets/lava.png"}, health, x, y, width, height, 0, riseRate);
//		this.riseRate = riseRate; 
		// TODO Auto-generated constructor stub
	}
	//Reset the height of the lava
	public void reset()
	{
		this.moveTo((int)getInitX(), (int)getInitY());
	}
	/**
	 * Move the lava up, and check if the lava collides with the player
	 * @param player
	 */
	public void increaseHeight(Collider player)
	{
		ArrayList<Collider> players = new ArrayList<Collider>();
		players.add(player);
		this.changeHeight(vy);
		this.moveBy(vx, -vy, players);
//		this.act(colliders);;
	}
	


}
