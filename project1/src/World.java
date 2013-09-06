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
	private Player player;
    /** Create a new World object. */
    public World()
    throws SlickException
    {
        // TODO: Fill in
    	Image playerSprite = new Image(Game.ASSETS_PATH + "/units/player.png");
    	this.player = new Player(1296, 13716, playerSprite); // player starting location
    }

    /** Update the game state for a frame.
     * @param dir_x The player's movement in the x axis (-1, 0 or 1).
     * @param dir_y The player's movement in the y axis (-1, 0 or 1).
     * @param delta Time passed since last frame (milliseconds).
     */
    public void update(double dir_x, double dir_y, int delta)
    throws SlickException
    {
        // TODO: Fill in
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(Graphics g)
    throws SlickException
    {
        player.draw();
    }
}
