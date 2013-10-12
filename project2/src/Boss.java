import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Boss extends Alien {

	private final static String _spriteAsset = Game.ASSETS_PATH + "/units/boss.png";

	public Boss(double x, double y, World world) throws SlickException {
		super(x, y, new Image(_spriteAsset), world);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub

	}

}
