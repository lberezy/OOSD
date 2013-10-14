import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class FirepowerItem extends Item {
	private final static String _spriteAsset = Game.ASSETS_PATH + "/items/firepower.png";

	public FirepowerItem(double x, double y, World world) throws SlickException {
		super(x, y, new Image(_spriteAsset), world);
		this.ownerWorld.registerItem(this);
	}

}