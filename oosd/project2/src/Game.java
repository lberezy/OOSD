/* SWEN20003 Object Oriented Software Development
 * Space Game Engine
 * Author: Matt Giuca <mgiuca>
 */

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

/**
 * Main class for the Role-Playing Game engine. Handles initialisation, input
 * and rendering.
 */
public class Game extends BasicGame {
	/** Location of the "assets" directory. */
	public static final String ASSETS_PATH = "assets";
	public static final String DATA_PATH = "data";
	public static final Boolean debug = false;
	public static final Boolean particles = true;

	/** The game state. */
	private World world;

	/** Screen width, in pixels. */
	public static final int screenwidth = 800;
	/** Screen height, in pixels. */
	public static final int screenheight = 600;

	/** Width of the play area, in pixels. */
	public static int playwidth() {
		return screenwidth;
	}

	/** Height of the play area, in pixels. */
	public static int playheight() {
		return screenheight;
	}

	/** Create a new Game object. */
	public Game() {
		super("Space Game");
	}

	/**
	 * Initialise the game state.
	 * 
	 * @param gc
	 *            The Slick game container object.
	 */
	@Override
	public void init(GameContainer gc) throws SlickException {
		world = new World();
		Music backgroundMusic = new Music(Game.ASSETS_PATH + "/music/a_piece_of_magic.mod");
		backgroundMusic.loop();
	}

	/**
	 * Update the game state for a frame. Supports a single game controller
	 * (first detected by OS) with analog sticks and/or directional pad. Button
	 * 1 to fire.
	 * 
	 * @param gc
	 *            The Slick game container object.
	 * @param delta
	 *            Time passed since last frame (milliseconds).
	 */
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// Get data about the current input (keyboard state).
		Input input = gc.getInput();

		// Update the player's movement direction based on keyboard presses.
		double dir_x = 0;
		double dir_y = 0;
		boolean firing = false;
		// supports analog control sticks!
		if (input.getControllerCount() >= 1) {
			dir_x = input.getAxisValue(0, 0);
			dir_y = input.getAxisValue(0, 1);
		}
		if (dir_x == 0 && dir_y == 0) { // fall back to keyboard if no
										// controller input
			if (input.isKeyDown(Input.KEY_DOWN) || input.isControllerDown(0))
				dir_y += 1;
			if (input.isKeyDown(Input.KEY_UP) || input.isControllerUp(0))
				dir_y -= 1;
			if (input.isKeyDown(Input.KEY_LEFT) || input.isControllerLeft(0))
				dir_x -= 1;
			if (input.isKeyDown(Input.KEY_RIGHT) || input.isControllerRight(0))
				dir_x += 1;
		}

		// button 14 is X on PS3 controller
		firing = input.isKeyDown(Input.KEY_SPACE) || input.isButtonPressed(14, 0);
		// exit on esc or select
		if (input.isKeyDown(Input.KEY_ESCAPE) || input.isButton1Pressed(0))
			gc.exit();
		if (input.isButtonPressed(16, 0))
			if (gc.isPaused()) {
				gc.resume();
			} else {
				gc.pause();
			}

		// Let World.update decide what to do with this data.
		world.update(dir_x, dir_y, firing, delta);
	}

	/**
	 * Render the entire screen, so it reflects the current game state.
	 * 
	 * @param gc
	 *            The Slick game container object.
	 * @param g
	 *            The Slick graphics object, used for drawing.
	 */
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// Let World.render handle the rendering.
		world.render(g);
	}

	/**
	 * Start-up method. Creates the game and runs it.
	 * 
	 * @param args
	 *            Command-line arguments (ignored).
	 */
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Game());
		// setShowFPS(true), to show frames-per-second.
		app.setShowFPS(true);
		app.setVSync(true);
		// app.setSmoothDeltas(true);
		app.setDisplayMode(screenwidth, screenheight, false);
		app.start();
	}
}
