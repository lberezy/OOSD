import org.newdawn.slick.Image;

public abstract class Unit extends GameObject implements Drawable {
	public Unit(double x, double y, Image sprite, World world) {
		super(x, y, sprite, world);
	}

	protected int shield;
	protected int fullShield;
	protected int damage;
	protected int firepower;

	public int getFullShield() {
		return fullShield;
	}

	public int getShield() {
		return shield;
	}

	public int getDamage() {
		return damage;
	}

	public int getFirepower() {
		return firepower;
	}

	public void recieveDamage(double dmg) {
		this.shield -= dmg;
		if (shield <= 0) {
			this.getOwnerWorld().registerCleanup(this);
		}
	}

	public void update(int delta) {
		// units collide with other units
		for (GameObject o : this.ownerWorld.getCollisions(this)) {
			if (o instanceof Unit) {
				((Unit) o).recieveDamage(damage);
			}
		}
		if (shield <= 0) {
			this.getOwnerWorld().registerCleanup(this);
		}
	}

	protected boolean isTerrainCollision() {
		return (isCollisionUp() || isCollisionDown() || isCollisionRight() || isCollisionRight());
	}

	protected Boolean isCollisionUp() {
		return ownerWorld.blockAtPoint(this.x, this.y - sprite.getHeight() / 2);
	}

	protected Boolean isCollisionDown() {
		return ownerWorld.blockAtPoint(this.x, this.y + sprite.getHeight() / 2);
	}

	protected Boolean isCollisionLeft() {
		return ownerWorld.blockAtPoint(this.x - (sprite.getWidth() / 2), this.y);
	}

	protected Boolean isCollisionRight() {
		return ownerWorld.blockAtPoint(this.x + (sprite.getWidth() / 2), this.y);
	}

}
