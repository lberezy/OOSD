
public abstract class GameObject {

	public double x, y; // object coordinates, can be freely changed by anything
	protected World ownerWorld; // world that object belongs to
	protected static int objID = 0; // gives each GameObject a unique (hopefully) number
	
	public GameObject(double x, double y, World world) {
		this.x = x;
		this.y = y;
		this.ownerWorld = world;
		objID++;
	}
	
	public GameObject(Point2D point, World world) {
		this.x = point.x;
		this.y = point.y;
		this.ownerWorld = world;
		objID++;
	}
	
	public World getOwnerWorld() {
		return this.ownerWorld;
	}
	
	// these getX/Y accessors are kind of useless but here for completeness
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public Point2D getPoint() {
		return new Point2D(this.x, this.y);
	}
	
	public int getObjID() {
		return this.objID;
	}
	

	
	public void update() {
		
	}
}
