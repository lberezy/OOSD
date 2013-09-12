/* SWEN20003 Object Oriented Software Development
 * Space Game Engine
 * Author: Matt Giuca <mgiuca>
 */

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;

/** Main class for the Role-Playing Game engine.
 * Handles initialisation, input and rendering.
 */
public class Game extends BasicGame
{
    /** Location of the "assets" directory. */
    public static final String ASSETS_PATH = "assets";
	public static final Boolean debug = true;


    /** The game state. */
    private World world;

    /** Screen width, in pixels. */
    public static final int screenwidth = 800;
    /** Screen height, in pixels. */
    public static final int screenheight = 600;

    /** Width of the play area, in pixels. */
    public static int playwidth()
    {
        return screenwidth;
    }
    /** Height of the play area, in pixels. */
    public static int playheight()
    {
        return screenheight;
    }

    /** Create a new Game object. */
    public Game()
    {
        super("Space Game");
    }

    /** Initialise the game state.
     * @param gc The Slick game container object.
     */
    @Override
    public void init(GameContainer gc)
    throws SlickException
    {
        world = new World();
    }

    /** Update the game state for a frame.
     * @param gc The Slick game container object.
     * @param delta Time passed since last frame (milliseconds).
     */
    @Override
    public void update(GameContainer gc, int delta)
    throws SlickException
    {
        // Get data about the current input (keyboard state).
        Input input = gc.getInput();

        // Update the player's movement direction based on keyboard presses.
        double dir_x = 0;
        double dir_y = 0;
        // supports analog control sticks!
        if(input.getControllerCount() >= 1) {
	    	dir_x = input.getAxisValue(0, 0);
	    	dir_y = input.getAxisValue(0, 1);
    	}
    	if (dir_x == 0 && dir_y == 0) {	// fall back to keyboard if no controller input
	        if (input.isKeyDown(Input.KEY_DOWN) || input.isControllerDown(0))
	            dir_y += 1;
	        if (input.isKeyDown(Input.KEY_UP) || input.isControllerUp(0))
	            dir_y -= 1;
	        if (input.isKeyDown(Input.KEY_LEFT))
	            dir_x -= 1;
	        if (input.isKeyDown(Input.KEY_RIGHT) )
	
	            dir_x += 1;
    	}

        // Let World.update decide what to do with this data.
        world.update(dir_x, dir_y, delta);
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param gc The Slick game container object.
     * @param g The Slick graphics object, used for drawing.
     */
    @Override
    public void render(GameContainer gc, Graphics g)
    throws SlickException
    {
        // Let World.render handle the rendering.
        world.render(g);
    }

    /** Start-up method. Creates the game and runs it.
     * @param args Command-line arguments (ignored).
     */
    public static void main(String[] args)
    throws SlickException
    {
        AppGameContainer app = new AppGameContainer(new Game());
        // setShowFPS(true), to show frames-per-second.
        app.setShowFPS(true);
        app.setVSync(true);
        app.setSmoothDeltas(true);
        app.setDisplayMode(screenwidth, screenheight, false);
        app.start();
    }
}
