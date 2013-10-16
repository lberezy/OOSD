import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public abstract class GameObject implements Drawable {

	protected double x, y; // object coordinates, can be freely changed by
							// anything
	protected World ownerWorld; // world that object belongs to
	protected Image sprite;
	protected Rectangle boundingBox;
	private static int IDcount = 0;
	protected int objID;

	public GameObject(double x, double y, Image sprite, World world) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.ownerWorld = world;
		this.objID = IDcount++;
		this.boundingBox = new Rectangle((float) x - sprite.getWidth() / 2, (float) y - (float) sprite.getHeight() / 2,
				(float) sprite.getWidth(), (float) sprite.getHeight());
	}

	public GameObject(Point2D point, Image sprite, World world) {
		this.x = point.x;
		this.y = point.y;
		this.sprite = sprite;
		this.ownerWorld = world;
		this.objID = IDcount++;
	}

	public boolean equals(Object object) {
		// for use in ArrayList.remove(Object)
		if (object instanceof GameObject && ((GameObject) object).getObjID() == this.getObjID()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if there is a collision between two GameObjects
	 * 
	 * @Note Self-collision when iterating through objects!
	 * @param obj
	 *            GameObject to check collision with
	 * @return Returns true if this object collides with obj
	 */
	public boolean collidesWith(GameObject obj) {
		// assuming rectangles are centered at x,y, not top-left
		return this.getBoundingBox().intersects(obj.getBoundingBox());
	}

	public Rectangle getBoundingBox() {
		return this.boundingBox;
	}

	public int getObjID() {
		return this.objID;
	}

	// public boolean isSeen() {
	// /**
	// * Checks if object is visible to its owner world's camera.
	// */
	// Camera myCamera = this.ownerWorld.getCamera();
	// double camHeight = myCamera.getHeight();
	// double camWidth = myCamera.getWidth();
	//
	// if ((this.x - (this.sprite.getWidth() / 2) < myCamera.x)
	// || (this.x + (this.sprite.getWidth() / 2) > (myCamera.x +
	// myCamera.getWidth())
	// || (this.y - (this.sprite.getHeight() / 2) < myCamera.y) || (this.y
	// + (this.sprite.getHeight() / 2) > (myCamera.y + myCamera.getHeight()))))
	// {
	// return false;
	// } else {
	// return true;
	// }
	// }

	/**
	 * 
	 * @return Sprite associated with object.
	 */
	public Image getSprite() {
		return this.sprite;
	}

	/**
	 * 
	 * @return Reference to world that instantiated the object.
	 */
	public World getOwnerWorld() {
		return this.ownerWorld;
	}

	/**
	 * 
	 * @return Reference to world that instantiated the object.
	 */
	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public Point2D getPoint() {
		return new Point2D(this.x, this.y);
	}

	public void render() {
		this.sprite.drawCentered((float) this.x, (float) this.y);
	}

	protected void updateBoundingBox() {
		this.boundingBox.setLocation((float) this.x - sprite.getWidth() / 2, (float) this.y - sprite.getHeight() / 2);
	}

	/**
	 * Sends a message to the owner world to schedule this object to be
	 * destroyed.
	 */
	public void cleanup() {
		this.ownerWorld.registerCleanup(this);
	}

	public void update(int delta) {

	}

	public void destroy() {

	}

}
