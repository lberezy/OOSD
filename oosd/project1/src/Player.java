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
		// handles map boundaries
		if (this.y <= 0) this.y = 0;	// prevent leaving top of map
		// prevent leaving sides of map
		if (this.x <= 0) this.x = 0;
		if (this.x + sprite.getWidth() >= (World.mapWidth * World.tileSize)){
			this.x = (World.mapWidth * World.tileSize) - sprite.getWidth();
		} 
	}
	
	public void cameraBounds() {
		// handles camera window boundaries
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
	}
	
	public Point2D getMidPoint() {
		return new Point2D( this.x + this.sprite.getWidth()/2, 
							this.y + this.sprite.getHeight()/2);
						
	}
}
