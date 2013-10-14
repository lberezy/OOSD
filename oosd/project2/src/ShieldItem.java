import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ShieldItem extends Item {
	private final static String _spriteAsset = Game.ASSETS_PATH + "/items/shield.png";

	public ShieldItem(double x, double y, World world) throws SlickException {
		super(x, y, new Image(_spriteAsset), world);
		this.ownerWorld.registerItem(this);
	}

}