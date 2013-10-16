import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Missile extends GameObject {

	private double direction;
	private final static String _spritePlayer = Game.ASSETS_PATH + "/units/missile-player.png";
	private final static String _spriteEnemy = Game.ASSETS_PATH + "/units/missile-enemy.png";
	private final double _speed = 0.7;
	private final double _damage = 8;
	private boolean enemy;

	/**
	 * Constructs an arbitrary missile object.
	 * 
	 * @param sprite
	 *            Image to use for the missile
	 * @param speed
	 *            Pixels per ms to move in direction
	 * @param Direction
	 *            of movement. -1 is up, 1 is down.
	 * @throws SlickException
	 */
	public Missile(double x, double y, boolean Enemy, World world) throws SlickException {
		super(x, y, chooseSprite(Enemy), world);
		this.direction = (Enemy) ? 1 : -1;
		this.getOwnerWorld().registerMissile(this);
		this.enemy = Enemy;
	}

	private static Image chooseSprite(boolean Enemy) throws SlickException {
		if (Enemy) {
			return new Image(_spriteEnemy);
		} else {
			return new Image(_spritePlayer);
		}
	}

	public boolean isEnemy() {
		return this.enemy;
	}

	public void update(int delta) {

		double deltaY = _speed * direction * delta;
		this.y += deltaY;
		updateBoundingBox();
		if (ownerWorld.blockAtPoint((int) this.x, (int) this.y)) {
			cleanup(); // schedules missile to be removed when appropriate to do
						// so
		}
		for (GameObject o : this.ownerWorld.getCollisions(this)) {
			if (o instanceof Unit) {
				((Unit) o).recieveDamage(_damage);
				this.ownerWorld.registerCleanup(this);
			}

		}
	}

	public void destroy() {
		// not to be called manually
		// Removes self from world missile list, GC does the rest?
		this.getOwnerWorld().removeMissile(this);
	}

}
