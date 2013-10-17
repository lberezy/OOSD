import org.newdawn.slick.Image;

public abstract class Enemy extends Unit {
	protected final static double _moveSpeed = 0.2;

	public Enemy(double x, double y, Image sprite, World world) {
		super(x, y, sprite, world);
		this.ownerWorld.registerEnemy(this);
	}

	public void recieveDamage(int damage) {
		this.shield -= damage;
		if (shield < 0) {
			// schedule the object for removal
			this.getOwnerWorld().registerCleanup(this);
		}
	}

	public void destroy() {
		this.getOwnerWorld().removeEnemy(this);
	}

	public void update(int delta) {
		// deal damage to self when colliding with terrain
		if (isTerrainCollision() && (this instanceof Fighter))
			recieveDamage(this.damage);
		super.update(delta);
	}
}
