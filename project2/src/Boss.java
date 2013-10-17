import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Boss extends Alien {
	private final static int _leftBound = 1024;
	private final static int _rightBound = 1580;
	private final static String _spriteAsset = Game.ASSETS_PATH + "/units/boss.png";
	private boolean bounce = false;
	private int cooldown = 0;
	private final int _fullShield = 240;
	private final int _damage = 100;
	private final int _firepower = 2;

	public Boss(double x, double y, World world) throws SlickException {
		super(x, y, new Image(_spriteAsset), world);
		this.firepower = _firepower;
		this.fullShield = _fullShield;
		this.shield = fullShield;
		this.damage = _damage;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub
		double deltaX = _moveSpeed * delta;

		if (this.x < _leftBound)
			bounce = true;
		if (this.x > _rightBound)
			bounce = false;
		if (bounce) {
			this.x += deltaX;
		} else {
			this.x -= deltaX;
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

	public void destroy() {
		super.destroy();
		this.ownerWorld.defeatBoss();
	}

}
