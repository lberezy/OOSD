import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Drone extends Alien {

	private final static String _spriteAsset = Game.ASSETS_PATH + "/units/drone.png";

	public Drone(double x, double y, World world) throws SlickException {
		super(x, y, new Image(_spriteAsset), world);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(int delta) {
		double distX = ownerWorld.getPlayer().getX() - this.x;
		double distY = ownerWorld.getPlayer().getY() - this.y;
		double distTotal = Math.sqrt(distX * distX + distY * distY);
		double deltaX = distX / distTotal * delta;
		double deltaY = distY / distTotal * delta;

	}

}
