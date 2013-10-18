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

	/**
	 * Creates a new game object, gives it a unique ID.
	 * 
	 * @param x
	 * @param y
	 * @param sprite
	 * @param world
	 *            Reference to world that instantiated it.
	 */
	public GameObject(double x, double y, Image sprite, World world) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.ownerWorld = world;
		this.objID = IDcount++;
		this.boundingBox = new Rectangle((float) x - sprite.getWidth() / 2, (float) y - (float) sprite.getHeight() / 2,
				(float) sprite.getWidth(), (float) sprite.getHeight());
	}

	/**
	 * Alternate constructor using Point2D. Deprecated.
	 * 
	 * @param point
	 * @param sprite
	 * @param world
	 */
	public GameObject(Point2D point, Image sprite, World world) {
		this(point.x, point.y, sprite, world);
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

	/**
	 * 
	 * @return Rectangle object representing object's bounds.
	 */
	public Rectangle getBoundingBox() {
		return this.boundingBox;
	}

	/**
	 * 
	 * @return Unique game objectID.
	 */
	public int getObjID() {
		return this.objID;
	}

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

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	/**
	 * 
	 * @return Returns object's x,y coordinates as a point
	 */
	public Point2D getPoint() {
		return new Point2D(this.x, this.y);
	}

	/**
	 * Renders the object's sprite.
	 */
	public void render() {
		this.sprite.drawCentered((float) this.x, (float) this.y);
	}

	/**
	 * Updates the bounding box of the object. Call during update.
	 */
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
