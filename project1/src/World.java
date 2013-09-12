/* SWEN20003 Object Oriented Software Development
 * Space Game Engine - Skeleton
 */

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/** Represents the entire game world.
 * (Designed to be instantiated just once for the whole game).
 */
public class World
{
	private Map worldMap;
	private Player player;
	private Camera worldCamera;
	private int mapWidth;
	private int mapHeight;
	private int tileSize;
    /** Create a new World object. */
    public World()
    
    throws SlickException
    {
        // TODO: Convert x, y parameters to Point2D parameters
    	Image playerSprite = new Image(Game.ASSETS_PATH + "/units/player.png");

    	this.worldMap = new Map(Game.ASSETS_PATH + "/map.tmx", Game.ASSETS_PATH);
    	
    	this.player = new Player(1296, 13716,	 // player starting location
    			0.25, 0.4,	// default speed, movement speed (pixels/ms)
    			playerSprite,	// sprite
    			this);	// callback reference to world
    	this.worldCamera = new Camera(1296, 13488,	// camera starting location
    								Game.screenwidth, Game.screenheight, // camera viewport size
    								0.25, // default vertical move speed (0.25 pixels/ms)
    								this);	// callback reference to world
    	
    	this.mapWidth = worldMap.getWidth();
    	this.mapHeight = worldMap.getHeight();
    	this.tileSize = worldMap.getTileWidth();
    }
    

    
    public Boolean blockAtPoint(int x, int y) {
    	/** queries the tilemap if a game-world point is in a region that should block movement */
    	// if the input location is invalid, return false (player leaving map, etc)
    	//System.out.println("blockpoint: " + x + " " + y);
    	x -= 1;
    	y -= 1;
    	if (true) {
        	int tileID = worldMap.getTileId(x/tileSize, y/tileSize, 0);	// get tileID at point, layer 0 of tilemap
        	//return worldMap.checkBlock(x/tileSize, y/tileSize);
        	return (worldMap.getTileProperty(tileID, "block", "0").equals("1"));
        	//return true;
    	} else {
    		return false;
    	}
    }
    
    
    public Boolean blockAtPoint(Point2D point) {
    	/** extends support for Point2D points to blockAtPoint.
    	 * Polymorphism is cool.
    	 */
    	int x = (int)point.x;
    	int y = (int)point.y;
    	return blockAtPoint(x, y);
    }
    


    public Camera getCamera() {
    	return this.worldCamera;
    }
    
    public Player getPlayer() {
    	return this.player;
    }
    
    public int mapWidth() {
    	return this.mapWidth;
    }
    
    public int mapHeight() {
    	return this.mapHeight;
    }
    
    public int tileSize() {
    	return this.tileSize;
    }
    
    /** Update the game state for a frame.
     * @param dir_x The player's movement in the x axis (-1, 0 or 1).
     * @param dir_y The player's movement in the y axis (-1, 0 or 1).
     * @param delta Time passed since last frame (milliseconds).
     */
    public void update(double dir_x, double dir_y, int delta)
    throws SlickException
    {
        worldCamera.update(delta, player.getMidPoint());
        player.update(dir_x, dir_y, delta);
    }
    

    /** Render the entire screen, so it reflects the current game state.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(Graphics g)
    throws SlickException
    {
   
        g.translate(-(float)worldCamera.x, -(float)worldCamera.y); // move the graphics coordinates relative to camera
    	// camera location is now (0, 0) for drawing
        drawMapRegion((int)worldCamera.x, (int)worldCamera.y, this.mapWidth, this.mapHeight);
        player.draw();
        // for debugging
        g.drawOval((float)player.x, (float)player.y - 32, 1f, 1f, 6);
        g.drawOval((float)player.x, (float)player.y + 32, 1f, 1f, 6);
        g.drawOval((float)player.x - 32, (float)player.y, 1f, 1f, 6);
        g.drawOval((float)player.x + 32, (float)player.y, 1f, 1f, 6);
        
        g.resetTransform(); // just in case anything else needs to be done elsewhere, reduce side-effects
    }
    
    public void drawMapRegion(int x, int y, int width, int height) {
    	/** Attempts to render only the tiles being seen by the camera */
    	int tileWidth = this.worldMap.getTileWidth();
    	int tileHeight = this.worldMap.getTileHeight();
    	// following variables in terms of tiles
    	int offsetX = x % tileWidth;
    	int offsetY = y % tileHeight;
    	int indexX = x / tileWidth;
    	int indexY = y / tileHeight;

    	
    	worldMap.render(x - offsetX,y - offsetY, // location in graphics context to render
    					indexX, indexY, // top left block of render window
    					mapWidth, // width of section to render (in tiles)
    					mapHeight);
    }
}
