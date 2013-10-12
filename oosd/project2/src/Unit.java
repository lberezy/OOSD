import org.newdawn.slick.Image;

public abstract class Unit extends GameObject implements Drawable {
	public Unit(double x, double y, Image sprite, World world) {
		super(x, y, sprite, world);
		// TODO Auto-generated constructor stub
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
