
public class Camera {
	public double x, y; //Camera current top-left position
	private double vSpeed; //Camera vertical speed (pixels/ms)
	private int width, height;
	
	public Camera(double x, double y, int w, int h, double vSpeed) {
		//Instantiate the camera given top-left coordinates
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.vSpeed = vSpeed; // default move speed

	}
	
	public void update(int delta, Point2D playerMid) {
		// adjust camera window by pixels/ms
		
		this.x = playerMid.x - Game.screenwidth/2; // follow player horizontally
		// this.y = playerMid.y - Game.screenheight/2;	// follow playe vertically
		this.y -= vSpeed * delta;
		mapBounds();
	}
	
	public void mapBounds() {
		// handles map boundaries
		if (this.y <= 0) this.y = 0;	// prevent camera from leaving top of map
		// prevent camera leaving sides of map
		if (this.x <= 0) this.x = 0;
		if (this.x + Game.screenwidth >= (World.mapWidth * World.tileSize)){
			this.x = (World.mapWidth * World.tileSize) - Game.screenwidth;
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
