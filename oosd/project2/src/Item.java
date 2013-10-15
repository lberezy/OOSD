import org.newdawn.slick.Image;

public abstract class Item extends GameObject {

	public Item(double x, double y, Image sprite, World world) {
		super(x, y, sprite, world);
		// TODO Auto-generated constructor stub
	}

	public void collect() {
		this.getOwnerWorld().registerCleanup(this);
	}

	public void destroy() {
		this.getOwnerWorld().removeItem(this);
	}

}
