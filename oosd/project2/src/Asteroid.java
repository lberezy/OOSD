import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Asteroid extends Enemy {

	private final static String _spriteAsset = Game.ASSETS_PATH + "/units/asteroid.png";
	private final int _fullShield = 24;
	private final int _damage = 12;
	private final int _firepower = 0;

	public Asteroid(double x, double y, World world) throws SlickException {
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
		double deltaY = _moveSpeed * delta;

		if (!(isCollisionUp() && deltaY < 0 || isCollisionDown() && deltaY > 0)) {
			this.y += deltaY;
		}
		updateBoundingBox();
		super.update(delta);
	}

}
