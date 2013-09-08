/* SWEN20003 Object Oriented Software Development
 * Space Game Engine - Skeleton
 */

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/** Represents the entire game world.
 * (Designed to be instantiated just once for the whole game).
 */
public class World
{
	private TiledMap worldMap;
	private Player player;
	private Camera worldCamera;
	// okay as will refer to most recent instance of the world
	public static int mapWidth;
	public static int mapHeight;
	public static int tileSize;
    /** Create a new World object. */
    public World()
    
    throws SlickException
    {
        // TODO: Fill in
    	Image playerSprite = new Image(Game.ASSETS_PATH + "/units/player.png");
    	this.player = new Player(1296, 13716, 0.25, 0.4, playerSprite, this); // player starting location
    	this.worldMap = new TiledMap(Game.ASSETS_PATH + "/map.tmx", Game.ASSETS_PATH);
    	this.worldCamera = new Camera(1296, 13488, 0.25);	//Default starting location, vSpeed = 0.4 pixels/ms
    	this.mapWidth = worldMap.getWidth();
    	this.mapHeight = worldMap.getHeight();
    	this.tileSize = worldMap.getTileWidth();
    }
    
    public Boolean blockAtPoint(Point2D point) {
    	int x = (int) point.x;
    	int y = (int) point.y;
    	
    	int tileID = worldMap.getTileId(x, y, 0);	// get tileID at point, layer 0 of tilemap
    	if (worldMap.getTileProperty(tileID, "block", "0").equals("1")) {	// check tile type, "1" = blocking
    		return true;
    	} else {
    		return false;
    	}
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

        g.translate(-(float)worldCamera.x, -(float)worldCamera.y);
    	drawMapRegion((int)worldCamera.x, (int)worldCamera.y, World.mapWidth, World.mapHeight);
        player.draw();
        g.resetTransform();
    }
    
    public void drawMapRegion(int x, int y, int width, int height) {
    	/** Attempts to render only the tiles being seen by the camera */
    	int tileWidth = worldMap.getTileWidth();
    	int tileHeight = worldMap.getTileHeight();
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
