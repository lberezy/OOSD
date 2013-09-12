
public class Camera extends GameObject{
	private double vSpeed; //Camera vertical speed (pixels/ms)
	private int width, height;
	
	public Camera(double x, double y, int w, int h, double vSpeed, World ownerWorld) {
		//Instantiate the camera given top-left coordinates
		super(x, y, ownerWorld);
		this.width = w;
		this.height = h;
		this.vSpeed = vSpeed; // default move speed

	}
	
	public void update(int delta, Point2D playerMid) {
		// adjust camera window by pixels/ms
		
		this.x = playerMid.x - Game.screenwidth/2; // follow player horizontally
		// this.y = playerMid.y - Game.screenheight/2;	// follow player vertically
		this.y -= vSpeed * delta;
		mapBounds();
	}
	
	public void mapBounds() {
		// handles map boundaries
		if (this.y <= 0) this.y = 0;	// prevent camera from leaving top of map
		// prevent camera leaving sides of map
		if (this.x <= 0) this.x = 0;
		if (this.x + Game.screenwidth >= (ownerWorld.mapWidth() * ownerWorld.tileSize())){
			this.x = (ownerWorld.mapWidth() * ownerWorld.tileSize()) - Game.screenwidth;
		} 
	}
	
	
	public double getVSpeed() {
		return this.vSpeed;
	}
	
	public void setVSpeed(double vSpeed) {
		this.vSpeed = vSpeed;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
}
