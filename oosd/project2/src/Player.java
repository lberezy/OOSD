import org.newdawn.slick.Image;

public class Player extends GameObject {
	private double baseSpeed;
	private double moveSpeed;
	private double shield;
	private Boolean inCollision; // as yet unused
	
	public Player(double x, double y, double baseSpeed, double moveSpeed, Image sprite, World world) {
		super(x, y, sprite, world);
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
		
		// top of camera
		if (this.y - sprite.getHeight()/2 <= camera.y) this.y = camera.y + sprite.getHeight()/2;	// prevent leaving top of map
		
		// prevent leaving bottom of map without collision
		if (this.y + sprite.getHeight()/2 >= camera.y + camera.getHeight() && isCollisionUp() == false) {
			this.y = camera.y + camera.getHeight() - sprite.getHeight()/2;
		}
		
		if (this.x - sprite.getWidth()/2 <= camera.x) {
			this.x = camera.x + sprite.getWidth()/2;	// left side
		}
		
		if (this.x + sprite.getWidth()/2 >= camera.x + camera.getWidth()){	// right side
			this.x = camera.x + camera.getWidth() - sprite.getWidth()/2;
		} 
	}
	
	
	// as yet unused
	private Boolean isCollision() {
		/** Checks 9 coordinates around the corners and sides of a rectangle
		 * around the sprite.
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
		return false;
	}

	// unused
	private Boolean checkVCollisions() {
		/** Check for collision at top and bottom of sprite
		 * 
		 */
		return (isCollisionUp() || isCollisionDown());
	}
	//unused
	private Boolean checkHCollisions() {
		/** Check for collision at sides of sprite
		 * 
		 */
		return (isCollisionLeft() || isCollisionRight());
	}
	
	
	// the following collision detection methods check a + shape over the sprite
	// it may be better to use a rectangle over the sprite so that the player can never
	// clip through tiles, but this makes the movement around corners feel weird and slow
	
	private Boolean isCollisionUp() {
		return ownerWorld.blockAtPoint((int)this.x, (int)this.y - sprite.getHeight()/2 - 1);
	}
	
	private Boolean isCollisionDown() {
		return ownerWorld.blockAtPoint((int)this.x, (int)this.y + sprite.getHeight()/2 + 1);
	}
	
	private Boolean isCollisionLeft() {
		return ownerWorld.blockAtPoint((int)this.x - sprite.getWidth()/2 - 1, (int)this.y);
	}
	
	private Boolean isCollisionRight() {
		return ownerWorld.blockAtPoint((int)this.x + sprite.getWidth()/2 + 1, (int)this.y);
	}

	
	public void draw() {
		this.sprite.drawCentered((float)x, (float)y);
	}
	
	
	/** Updates the player's position and future movement. Handles map blocking.
	 * @param dir_x -1 to move left, 1 to move right
	 * @param dir_y -1 to move up, 1 to move down
	 * @param delta milliseconds since last frame
	 * */
	public void update(double dir_x, double dir_y, int delta) {

		double deltaX = 0, deltaY = 0;
		
		deltaY = (-baseSpeed * delta) + (moveSpeed * dir_y * delta);
		deltaX = moveSpeed * dir_x * delta;
		
		if (!(isCollisionRight() && deltaX > 0 || isCollisionLeft() && deltaX < 0)) {
			this.x += deltaX;
		}
		
		if (!(isCollisionUp() && deltaY < 0 || isCollisionDown() && deltaY > 0)) {
			this.y += deltaY;
		}

		// handle bounding conditions
		mapBounds();
		cameraBounds();
	}
	
	public Point2D getMidPoint() {
		return new Point2D( this.x + this.sprite.getWidth()/2, 
							this.y + this.sprite.getHeight()/2);
						
	}

	@Override
	public void update(int delta) {
		// Required by interface, but can't override without breaking all other objects
		// Should be fine in this one case.
		
	}






}
