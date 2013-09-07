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
    /** Create a new World object. */
    public World()
    throws SlickException
    {
        // TODO: Fill in
    	Image playerSprite = new Image(Game.ASSETS_PATH + "/units/player.png");
    	this.player = new Player(0, 0, playerSprite); // player starting location
    	this.worldMap = new TiledMap(Game.ASSETS_PATH + "/map.tmx", Game.ASSETS_PATH);
    	this.worldCamera = new Camera(1296, 13488, 0.4, 0);	//Default starting location, vSpeed = 0.4 pixels/ms
    }

    /** Update the game state for a frame.
     * @param dir_x The player's movement in the x axis (-1, 0 or 1).
     * @param dir_y The player's movement in the y axis (-1, 0 or 1).
     * @param delta Time passed since last frame (milliseconds).
     */
    public void update(double dir_x, double dir_y, int delta)
    throws SlickException
    {
        worldCamera.update(delta);
        player.update(dir_x, dir_y, delta);
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(Graphics g)
    throws SlickException
    {
        player.draw();
        worldMap.render(-1, -1,	//On-screen location (top left)
        		(int)worldCamera.x/worldMap.getTileWidth(), //Place in map to render (in tiles)
        		(int)worldCamera.y/worldMap.getTileHeight(),
        		Game.screenwidth/worldMap.getTileWidth() + 1, //Size of render (in tiles)
        		Game.screenheight/worldMap.getTileHeight() + 1
        		);
    }
}
