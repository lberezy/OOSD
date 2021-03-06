import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Fighter extends Alien {

	private final static String _spriteAsset = Game.ASSETS_PATH + "/units/fighter.png";
	private int cooldown = 0;
	private final int _fullShield = 24;
	private final int _damage = 9;
	private final int _firepower = 0;

	public Fighter(double x, double y, World world) throws SlickException {
		super(x, y, new Image(_spriteAsset), world);
		this.firepower = _firepower;
		this.fullShield = _fullShield;
		this.shield = fullShield;
		this.damage = _damage;
	}

	@Override
	public void update(int delta) {
		double deltaY = _moveSpeed * delta;

		if (!(isCollisionUp() && deltaY < 0 || isCollisionDown() && deltaY > 0)) {
			this.y += deltaY;
		}
		cooldown(delta);
		fireMissile();
		updateBoundingBox();
		super.update(delta);
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
			new Missile(this.x, this.y + 50, true, this.ownerWorld);
			cooldown = 300 - (80 * _firepower);
		} catch (SlickException e) {
			if (Game.debug)
				System.err.println("Error: Unable to create missile!");
			e.printStackTrace();
		}
	}

}
