import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Asteroid extends Enemy {

	private final static String _spriteAsset = Game.ASSETS_PATH + "/units/asteroid.png";

	public Asteroid(double x, double y, World world) throws SlickException {
		super(x, y, new Image(_spriteAsset), world);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub

	}

}
