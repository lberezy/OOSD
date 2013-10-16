import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends Unit {

	// change these for player settings
	private final static double _baseSpeed = 0.25;
	private final static double _moveSpeed = 0.4;
	private final int _fullShield = 100;
	private final int _maxFirepower = 3;
	private final int _firepower = 0;
	private final int _damage = 10;
	private final static String _spriteAsset = Game.ASSETS_PATH + "/units/player.png";

	private int cooldown = 0;
	private Boolean inCollision; // as yet unused

	public Player(double x, double y, World world) throws SlickException {
		super(x, y, new Image(_spriteAsset), world);
		this.firepower = _firepower;
		this.fullShield = _fullShield;
		this.shield = fullShield;
		this.damage = _damage;
	}

	public void mapBounds() {
		// handles map boundaries, treats sprite bounds as rectangle
		// prevent leaving top of map
		if (this.y - sprite.getHeight() / 2 <= 0)
			this.y = sprite.getHeight() / 2; // prevent leaving top of map
		// prevent leaving bottom of map without collision
		if (this.y + sprite.getHeight() / 2 >= (ownerWorld.mapHeight() * ownerWorld.tileSize())) {
			this.y = (ownerWorld.mapHeight() * ownerWorld.tileSize()) - sprite.getHeight() / 2;
		}

		// prevent leaving sides of map
		if (this.x - sprite.getWidth() / 2 <= 0)
			this.x = sprite.getWidth() / 2; // left side

		if (this.x + sprite.getWidth() / 2 >= (ownerWorld.mapWidth() * ownerWorld.tileSize())) { // right
																									// side
			this.x = (ownerWorld.mapWidth() * ownerWorld.tileSize()) - sprite.getWidth() / 2;
		}
	}

	public void cameraBounds() {

		Camera camera = ownerWorld.getCamera();
		// handles camera window boundaries

		// top of camera
		if (this.y - sprite.getHeight() / 2 <= camera.y)
			this.y = camera.y + sprite.getHeight() / 2; // prevent leaving top
														// of map

		// prevent leaving bottom of map without collision
		if (this.y + sprite.getHeight() / 2 >= camera.y + camera.getHeight() && isCollisionUp() == false) {
			this.y = camera.y + camera.getHeight() - sprite.getHeight() / 2;
		}

		if (this.x - sprite.getWidth() / 2 <= camera.x) {
			this.x = camera.x + sprite.getWidth() / 2; // left side
		}

		if (this.x + sprite.getWidth() / 2 >= camera.x + camera.getWidth()) { // right
																				// side
			this.x = camera.x + camera.getWidth() - sprite.getWidth() / 2;
		}
	}

	// as yet unused
	private Boolean isCollision() {
		/**
		 * Checks 9 coordinates around the corners and sides of a rectangle
		 * around the sprite.
		 */
		int i, j;

		for (i = -1; i <= 1; i += 2) {
			for (j = -1; j <= 1; j += 2) {
				if (ownerWorld.blockAtPoint(this.x + i * sprite.getWidth() / 2, this.y + j * sprite.getHeight() / 2)) {
					return true;
				}
			}
		}
		return false;
	}

	// unused
	private Boolean checkVCollisions() {
		/**
		 * Check for collision at top and bottom of sprite
		 * 
		 */
		return (isCollisionUp() || isCollisionDown());
	}

	// unused
	private Boolean checkHCollisions() {
		/**
		 * Check for collision at sides of sprite
		 * 
		 */
		return (isCollisionLeft() || isCollisionRight());
	}

	/**
	 * Updates the player's position and future movement. Handles map blocking.
	 * 
	 * @param dir_x
	 *            -1 to move left, 1 to move right
	 * @param dir_y
	 *            -1 to move up, 1 to move down
	 * @param firing
	 *            True of player wants to shoot
	 * @param delta
	 *            milliseconds since last frame
	 * */
	public void update(double dir_x, double dir_y, boolean firing, int delta) {
		cooldown(delta); // missile cooldown
		double deltaX = 0, deltaY = 0;
		deltaY = (-_baseSpeed * delta) + (_moveSpeed * dir_y * delta);
		deltaX = _moveSpeed * dir_x * delta;

		if (!(isCollisionRight() && deltaX > 0 || isCollisionLeft() && deltaX < 0)) {
			this.x += deltaX;
		}

		if (!(isCollisionUp() && deltaY < 0 || isCollisionDown() && deltaY > 0)) {
			this.y += deltaY;
		}
		if (firing) {
			fireMissile();
		}
		// handle bounding conditions
		mapBounds();
		cameraBounds();
		updateBoundingBox();
		// item collisions
		for (GameObject o : this.ownerWorld.getCollisions(this)) {
			if (o instanceof Item) {
				this.consumeItem((Item) o);
			}
		}
		super.update(delta); // handles collision with other units
	}

	private void consumeItem(Item item) {
		if (item instanceof RepairItem) {
			this.shield = fullShield;
		} else if (item instanceof ShieldItem) {
			this.shield += 40;
			this.fullShield += 40;
		} else if (item instanceof FirepowerItem)
			this.firepower += 1;
		if (this.firepower > _maxFirepower) {
			this.firepower = _maxFirepower;
			return; // disregard item, don't remove from world
		}
		item.getOwnerWorld().registerCleanup(item);
	}

	private void cooldown(int delta) {
		// saturated subtraction to 0
		cooldown -= delta;
		if (cooldown < 0)
			cooldown = 0;
	}

	private void fireMissile() {
		if (cooldown > 0)
			return;
		try {
			new Missile(this.x, this.y - 50, false, this.ownerWorld);
			cooldown = 300 - (80 * firepower);
		} catch (SlickException e) {
			if (Game.debug)
				System.err.println("Error: Unable to create missile!");
			e.printStackTrace();
		}
	}

	public void destroy() {
		// check if there are enough lives and respawn if possible at checkpoint
	}

	@Override
	public void update(int delta) {
		// Required by interface, but can't override without breaking all other
		// objects
		// Should be fine in this one case (only player has different type
		// signature)

	}

}
