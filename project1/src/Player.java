import org.newdawn.slick.Image;

public class Player extends GameObject {
	private Image sprite;
	private double baseSpeed;
	private double moveSpeed;
	
	public Player(double x, double y, double baseSpeed, double moveSpeed, Image sprite, World world) {
		super(x, y, world);
		this.sprite = sprite;
		this.baseSpeed = baseSpeed;
		this.moveSpeed = moveSpeed;

	}
	
	public void mapBounds() {
		// handles map boundaries, treats sprite bounds as rectangle
		// prevent leaving top of map
		if (this.y - sprite.getHeight()/2 <= 0) this.y = sprite.getHeight()/2;	// prevent leaving top of map
		// prevent leaving bottom of map without collision
		if (this.y + sprite.getHeight()/2 >= (ownerWorld.mapHeight() * ownerWorld.tileSize())) {
			this.y = (ownerWorld.mapHeight() * ownerWorld.tileSize()) - sprite.getHeight()/2;
		}
		
		// prevent leaving sides of map
		if (this.x - sprite.getWidth()/2 <= 0) this.x = sprite.getWidth()/2;	// left side

		if (this.x + sprite.getWidth()/2 >= (ownerWorld.mapWidth() * ownerWorld.tileSize())){	// right side
			this.x = (ownerWorld.mapWidth() * ownerWorld.tileSize()) - sprite.getWidth()/2;
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
		/** Checks 9 coordinates around the corners and sides of a rectangle
		 * around the sprite. Returns a tuple of values, horizontal and vertical. Coerces the Point2D
		 * class to do so (represents x, y as horizontal, vertical).
		 * 
		 */
		int i, j;

		for (i = -1; i <= 1; i += 2) {
			for (j = -1; j <= 1; j += 2) {
				if (ownerWorld.blockAtPoint(new Point2D(this.x + i * sprite.getWidth()/2,
														this.y + j * sprite.getHeight()/2))) {
					return true;
				}
					
			}
		}
		
		return ownerWorld.blockAtPoint(new Point2D(this.x, this.y - 1));
			
	}

	
	private Boolean checkVCollisions() {
		if (ownerWorld.blockAtPoint((int)this.x, (int)this.y + sprite.getHeight()/2) ||
			ownerWorld.blockAtPoint((int)this.x, (int)this.y - sprite.getHeight()/2)) {
			return true;
		}
		return false;
	}
	private Boolean checkHCollisions() {
		/** Check for collision at sides of sprite
		 * 
		 */
		if (ownerWorld.blockAtPoint((int)this.x + sprite.getWidth()/2, (int)this.y) ||
			ownerWorld.blockAtPoint((int)this.x - sprite.getWidth()/2, (int)this.y)) {
			return true;
		}
		return false;
	}
	

	public void draw() {
		sprite.drawCentered((float)x, (float)y);
	}
	
	public void update(double dir_x, double dir_y, int delta) {
		/** updates the players position and future movement (blocking) */
		// update player position
		if (checkVCollisions() == false) {
			this.y += (-baseSpeed * delta) + (moveSpeed * dir_y * delta);

		} else {
			this.y -= dir_y;
		}
		if (checkHCollisions() == false) {
			this.x += moveSpeed * dir_x * delta;

		}
		// handle bounding conditions
		mapBounds();
		cameraBounds();
		
	}
	
	public Point2D getMidPoint() {
		return new Point2D( this.x + this.sprite.getWidth()/2, 
							this.y + this.sprite.getHeight()/2);
						
	}
}
