import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Fighter extends Alien {

	private final static String _spriteAsset = Game.ASSETS_PATH + "/units/fighter.png";
	private final static double _moveSpeed = 0.2;
	private int cooldown = 0;
	private int firepower = 0;

	public Fighter(double x, double y, World world) throws SlickException {
		super(x, y, new Image(_spriteAsset), world);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(int delta) {
		double deltaY = _moveSpeed * delta;
		if (!(isCollisionDown() && delta < 0)) {
			this.y += deltaY;
		}
		cooldown(delta);
		fireMissile();
		updateBoundingBox();
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
			new Missile(this.x, this.y + 25, true, this.ownerWorld);
			cooldown = 300 - (80 * firepower);
		} catch (SlickException e) {
			if (Game.debug)
				System.err.println("Error: Unable to create missile!");
			e.printStackTrace();
		}
	}

}
