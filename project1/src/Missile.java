import org.newdawn.slick.Image;


public class Missile extends GameObject{
	
	private double speed, direction;
	
	/**
	 * Constructs an arbitrary missile object.
	 * @param sprite Image to use for the missile
	 * @param speed Pixels per ms to move in direction
	 * @param Direction of movement. -1 is up, 1 is down.
	 */
	public Missile(double x, double y, Image sprite, double speed, int direction, World world) {

		super(x, y, sprite, world);
		this.speed = speed;
		this.direction = direction;
		this.getOwnerWorld().registerMissile(this);
	}

	public void update(int delta) {
		this.y -= speed*direction;
	}
	
	private void destroy() {
		// Removes self from world missile list, gc does the rest?
		this.getOwnerWorld().removeMissile(this);
	}

}
