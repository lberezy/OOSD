import org.newdawn.slick.Image;

public class Player {
	private Image sprite;
	private double x, y;
	private World ownerWorld;
	private double baseSpeed;
	private double moveSpeed;
	
	public Player(double x, double y, double baseSpeed, double moveSpeed, Image sprite, World world) {
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		this.baseSpeed = baseSpeed;
		this.moveSpeed = moveSpeed;
		this.ownerWorld = world; // reference to world that created the player
	}
	
	public void mapBounds() {
		// handles map boundaries, treats sprite bounds as rectangle
		// prevent leaving top of map
		if (this.y - sprite.getHeight()/2 <= 0) this.y = sprite.getHeight()/2;	// prevent leaving top of map
		
		// prevent leaving bottom of map without collision
		if (this.y + sprite.getHeight()/2 >= (ownerWorld.mapHeight * ownerWorld.tileSize)) {
			this.y = (ownerWorld.mapHeight * ownerWorld.tileSize) - sprite.getHeight()/2;
		}
		
		// prevent leaving sides of map
		if (this.x - sprite.getWidth()/2 <= 0) this.x = sprite.getWidth()/2;	// left side

		if (this.x + sprite.getWidth()/2 >= (ownerWorld.mapWidth * ownerWorld.tileSize)){	// right side
			this.x = (ownerWorld.mapWidth * ownerWorld.tileSize) - sprite.getWidth()/2;
		} 
	}
	
	public void cameraBounds() {
		
		Camera camera = ownerWorld.getCamera();
		// handles camera window boundaries
		
		if (this.y - sprite.getHeight()/2 <= camera.y) this.y = camera.y + sprite.getHeight()/2;	// prevent leaving top of map
		
		// prevent leaving bottom of map without collision
		// player is allowed to 3/4 of plane below the camera line for more fun
		if (this.y - sprite.getHeight()/4 >= camera.y + camera.getHeight()) {
			this.y = camera.y + camera.getHeight() + sprite.getHeight()/4;
		}
		
		if (this.x - sprite.getWidth()/2 <= camera.x) {
			this.x = camera.x + sprite.getWidth()/2;	// left side
		}
		
		if (this.x + sprite.getWidth()/2 >= camera.x + camera.getWidth()){	// right side
			this.x = camera.x + camera.getWidth() - sprite.getWidth()/2;
		} 
	}
	
	private Boolean isCollision() {
		// checks the coordinates just ahead of the object for map tiles that block movement
		
		return ownerWorld.blockAtPoint(new Point2D(this.x, this.y - 1));
			
	}
	

	public void draw() {
		sprite.drawCentered((float)x, (float)y);
	}
	
	public void update(double dir_x, double dir_y, int delta) {
		this.x += moveSpeed * dir_x * delta;
		this.y += (-baseSpeed * delta) + (moveSpeed * dir_y * delta);
		mapBounds();
		cameraBounds();
	}
	
	public Point2D getMidPoint() {
		return new Point2D( this.x + this.sprite.getWidth()/2, 
							this.y + this.sprite.getHeight()/2);
						
	}
}
