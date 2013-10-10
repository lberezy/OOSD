import org.newdawn.slick.Image;


public abstract class GameObject implements Drawable {

	public double x, y; // object coordinates, can be freely changed by anything
	protected World ownerWorld; // world that object belongs to
	protected Image sprite;
	private static int IDcount = 0;
	protected int objID;
	
	public GameObject(double x, double y, Image sprite, World world) {
		this.x = x;
		this.y = y;
		this.ownerWorld = world;
		this.objID = IDcount++;
	}
	
	public GameObject(Point2D point, Image sprite, World world) {
		this.x = point.x;
		this.y = point.y;
		this.ownerWorld = world;
		this.objID = IDcount++;
	}
	
	public boolean equals(Object object){
		// for use in ArrayList.remove(Object)
		if (object instanceof GameObject && ((GameObject)object).getObjID() == this.getObjID()) {
		    return true;
		} else {
		    return false;
		}
	}
	
	public int getObjID() {
		return this.objID;
		}
	
	
	public Boolean isSeen() {
		/**
		 * Checks if object is visible to its owner world's camera.
		 */
		Camera myCamera = this.ownerWorld.getCamera();
		double camHeight = myCamera.getHeight();
		double camWidth = myCamera.getWidth();
		
		if ( 
			(this.x - (this.sprite.getWidth()/2) < myCamera.x) ||
			(this.x + (this.sprite.getWidth()/2) > (myCamera.x + myCamera.getWidth()) ||
			(this.y - (this.sprite.getHeight()/2) < myCamera.y) ||
			(this.y + (this.sprite.getHeight()/2) > (myCamera.y + myCamera.getHeight())))
			) {
				return false;
			} else {
				return true;
			}
	}

	
	public Image getSprite() {
		return this.sprite;
	}
	
	public World getOwnerWorld() {
		return this.ownerWorld;
	}
	
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
		if (this.isSeen()) {
			this.sprite.drawCentered((float)this.x, (float)this.y);
		}
	}
	
	public void update() {
	}

}
