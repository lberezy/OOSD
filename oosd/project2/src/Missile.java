import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Missile extends GameObject{
	
	private double direction;
	private final static String spritePlayer = Game.ASSETS_PATH + "/units/missile-player.png";
	private final static String spriteEnemy = Game.ASSETS_PATH + "/units/missile-enemy.png";
	private final double speed = 0.7;
	private final double damage = 8;
	
	/**
	 * Constructs an arbitrary missile object.
	 * @param sprite Image to use for the missile
	 * @param speed Pixels per ms to move in direction
	 * @param Direction of movement. -1 is up, 1 is down.
	 * @throws SlickException 
	 */
	public Missile(double x, double y, boolean Enemy, World world) throws SlickException {
		super(x, y, chooseSprite(Enemy), world);
		this.direction = (Enemy) ? 1 : -1;
		this.getOwnerWorld().registerMissile(this);
	}
	
	private static Image chooseSprite(boolean Enemy) throws SlickException {
		if (Enemy) {
			return new Image(spriteEnemy);
		} else {
			return new Image(spritePlayer);
		}
	}

	public void update(int delta) {

		double deltaY = speed*direction*delta;
		
		this.y += deltaY;
		updateBoundingBox();
		if(ownerWorld.blockAtPoint((int)this.x, (int)this.y)) {
			cleanup(); // schedules missile to be removed when appropriate to do so
		}
	}
	

	public void destroy() {
		// not to be called manually
		// Removes self from world missile list, GC does the rest?
		this.getOwnerWorld().removeMissile(this);
	}

}
