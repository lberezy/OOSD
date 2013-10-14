import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class RepairItem extends Item {
	private final static String _spriteAsset = Game.ASSETS_PATH + "/items/repair.png";

	public RepairItem(double x, double y, World world) throws SlickException {
		super(x, y, new Image(_spriteAsset), world);
		this.ownerWorld.registerItem(this);
	}

}
