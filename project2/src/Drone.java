import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Drone extends Alien {

	private final static String _spriteAsset = Game.ASSETS_PATH + "/units/drone.png";
	private int firepower = 0;
	private final int _fullShield = 24;
	private final int _damage = 9;
	private final int _firepower = 0;

	public Drone(double x, double y, World world) throws SlickException {
		super(x, y, new Image(_spriteAsset), world);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(int delta) {
		double distX = ownerWorld.getPlayer().getX() - this.x;
		double distY = ownerWorld.getPlayer().getY() - this.y;
		double distTotal = Math.sqrt(distX * distX + distY * distY);
		double deltaX = distX / distTotal * _moveSpeed * delta;
		double deltaY = distY / distTotal * _moveSpeed * delta;

		if (!(isCollisionRight() && deltaX > 0 || isCollisionLeft() && deltaX < 0)) {
			this.x += deltaX;
		}

		if (!(isCollisionUp() && deltaY < 0 || isCollisionDown() && deltaY > 0)) {
			this.y += deltaY;
		}
		updateBoundingBox();
		super.update(delta);
	}

}
