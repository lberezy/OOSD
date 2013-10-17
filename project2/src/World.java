/* SWEN20003 Object Oriented Software Development
 * Space Game Engine - Skeleton
 * Author: Matt Giuca <mgiuca>
 * Derived Author: Lucas Berezy (588 236)
 * Based off supplied skeleton code. Said code reproduces works from copyrighted titles
 * Chromium B.S.U. and (Artistic License) and Battle for Wesnoth (GNU General Public License v.2+).
 * Music: "A piece of magic" - Lizardking (No explicit license/Scene license)
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

/**
 * Represents the entire game world. (Designed to be instantiated just once for
 * the whole game).
 */
public class World {
	private Map worldMap;
	private Player player;
	private Panel panel;
	private Camera worldCamera;
	private ParticleSystem particleSystem;
	private ConfigurableEmitter emitter;

	private int mapWidth;
	private int mapHeight;
	private int tileSize;
	private int tileWidth;
	private int tileHeight;
	private int[] checkpoints = { 13716, 9756, 7812, 5796, 2844, 0 };
	private int checkpoint = 0;
	private boolean bossDefeated = false;

	private ArrayList<Missile> missiles = new ArrayList<Missile>();
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Item> items = new ArrayList<Item>();
	private ArrayList<GameObject> cleanup = new ArrayList<GameObject>();
	private ArrayList<GameObject> onScreen = new ArrayList<GameObject>();

	public World() throws SlickException {
		/* Constructs the game world */

		// TODO: Convert x, y parameters to Point2D parameters, extend (x,y)
		// methods args with Point2D args

		// load map
		this.worldMap = new Map(Game.ASSETS_PATH + "/map.tmx", Game.ASSETS_PATH);

		// load player

		// TODO: Implement csv loading
		// this.player = new Player(1296, 13716, this);

		createUnits(Game.DATA_PATH + "/units.csv", true);
		createItems(Game.DATA_PATH + "/items.csv");

		// create camera
		this.worldCamera = new Camera(1296, 13488, // camera starting location
				Game.screenwidth, Game.screenheight, // camera viewport size
				0.25, // default vertical move speed (0.25 pixels/ms)
				this); // callback reference to world

		// create stat panel for player
		this.panel = new Panel();
		// set misc variables
		this.mapWidth = worldMap.getWidth();
		this.mapHeight = worldMap.getHeight();
		this.tileSize = worldMap.getTileWidth();
		this.tileWidth = worldMap.getTileWidth();
		this.tileHeight = worldMap.getTileHeight();

		if (Game.particles) {
			try {
				Image particle = new Image(Game.ASSETS_PATH + "/particles/particle.png");
				particleSystem = new ParticleSystem(particle, 2000);
				emitter = ParticleIO.loadEmitter(new File(Game.ASSETS_PATH + "/particles/emitter.xml"));
				emitter.setPosition((float) player.x, (float) player.y);
				particleSystem.addEmitter(emitter);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isBossDefeated() {
		return this.bossDefeated;
	}

	public void defeatBoss() {
		this.bossDefeated = true;
		player.x = 1296;
	}

	public Boolean blockAtPoint(double x, double y) {
		/**
		 * queries the map if a game-world point is in a region that should
		 * block movement returns false if the point is invalid anyway
		 */

		int tileX = (int) (x / tileSize);
		int tileY = (int) (y / tileSize);

		if (tileX < 0 || tileX > worldMap.getWidth() || tileY < 0 || tileY > worldMap.getHeight()) {
			return true;
		} else {
			return worldMap.checkBlock(x, y);

		}

	}

	private void createUnits(String unitsFile, boolean initial) {
		BufferedReader reader = null;
		try {
			File file = new File(unitsFile);
			reader = new BufferedReader(new FileReader(file));

			String line;
			String[] tokens;
			while ((line = reader.readLine()) != null) {
				tokens = line.split(","); // csv splitting
				createUnit(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), initial);

			}

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void createItems(String unitsFile) {
		BufferedReader reader = null;
		try {
			File file = new File(unitsFile);
			reader = new BufferedReader(new FileReader(file));

			String line;
			String[] tokens;
			while ((line = reader.readLine()) != null) {
				tokens = line.split(","); // csv splitting
				createItem(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));

			}

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Creates specified unit at location.
	 * 
	 * @param unitString
	 * @param x
	 * @param y
	 * @param initial
	 *            Player only created if initial is true.
	 * @throws SlickException
	 */
	private void createUnit(String unitString, int x, int y, boolean initial) throws SlickException {

		Units unit = Units.valueOf(unitString);
		switch (unit) {
		case Player:
			if (initial)
				player = new Player(x, y, this);
			break;
		case Asteroid:
			new Asteroid(x, y, this);
			break;
		case Boss:
			new Boss(x, y, this);
			break;
		case Drone:
			new Drone(x, y, this);
			break;
		case Fighter:
			new Fighter(x, y, this);
			break;
		default:
			// TODO: throw unhandled unit exception here
			break;
		}
	}

	private void createItem(String itemString, double x, double y) throws SlickException {
		Items item = Items.valueOf(itemString);
		switch (item) {
		case Repair:
			new RepairItem(x, y, this);
			break;
		case Shield:
			new ShieldItem(x, y, this);
			break;
		case Firepower:
			new FirepowerItem(x, y, this);
			break;
		default:
			// TODO: throw unhandled unit exception here
			break;
		}
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

	public int getCheckpointY() {
		return checkpoints[checkpoint];
	}

	/**
	 * Handles the player death event. Moves player to correct position based on
	 * checkpoint reached. Makes call to player to do onRestore() event method.
	 * (restore shield, etc) Clears enemies from world and reloads them. Clears
	 * all missiles from world. Centers camera on player. Anyone can kill the
	 * player.
	 */
	public void playerDeath() {
		// move player
		player.x = 1296 + player.getSprite().getWidth() / 2;
		player.y = checkpoints[checkpoint];
		player.onRestore();
		enemies.clear();
		missiles.clear();
		onScreen.clear();
		createUnits(Game.DATA_PATH + "/units.csv", false);
		worldCamera.x = player.x - worldCamera.getWidth() / 2; // horizontal
																// camera
		worldCamera.y = player.y - (worldCamera.getHeight() - 145);
	}

	/**
	 * Update the game state for a frame.
	 * 
	 * @param dir_x
	 *            The player's movement in the x axis (-1, 0 or 1).
	 * @param dir_y
	 *            The player's movement in the y axis (-1, 0 or 1).
	 * @param firing
	 *            True if fire button is pressed.
	 * @param delta
	 *            Time passed since last frame (milliseconds).
	 */
	public void update(double dir_x, double dir_y, boolean firing, int delta) throws SlickException {
		worldCamera.update(delta, new Point2D(player.getX(), player.getY()));
		player.update(dir_x, dir_y, firing, delta);
		if (worldCamera.canSee(player))
			onScreen.add(player);
		updateItems(delta);
		updateEnemies(delta);
		updateMissiles(delta);
		Cleanup();

		onScreen.clear(); // clear onScreen objects after update methods are
							// done with them
		// handle updating checkpoint
		if (Game.debug) {
			// System.out.println(checkpoint);
			// System.out.println(checkpoints[checkpoint]);
		}

		if (Game.particles) {
			emitter.setPosition((float) player.x, (float) player.y);
			particleSystem.update(delta);

		}
		if (player.getY() < checkpoints[checkpoint + 1] && (checkpoint < (checkpoints.length)))
			checkpoint += 1;
	}

	/**
	 * Render the entire screen, so it reflects the current game state.
	 * 
	 * @param g
	 *            The Slick graphics object, used for drawing.
	 */
	public void render(Graphics g) throws SlickException {
		// move graphics coords relative to camera
		g.translate(-(float) worldCamera.x, -(float) worldCamera.y);
		// camera location is now (0, 0) for drawing
		drawMapRegion((int) worldCamera.x, (int) worldCamera.y, this.mapWidth, this.mapHeight);
		player.render();
		renderItems();
		renderEnemies();
		renderMissiles();
		// for debugging
		if (Game.debug) {
			g.setColor(Color.green);

			g.drawString("X tile: " + (int) player.x / tileSize, (float) player.x, (float) player.y);
			g.drawString("Y tile: " + (int) player.y / tileSize, (float) player.x, (float) player.y + 20);
			g.drawOval((float) player.x, (float) player.y - 32, 1f, 1f, 6);
			g.drawOval((float) player.x, (float) player.y + 32, 1f, 1f, 6);
			g.drawOval((float) player.x - 32, (float) player.y, 1f, 1f, 6);
			g.drawOval((float) player.x + 32, (float) player.y, 1f, 1f, 6);
			for (GameObject o : onScreen) {
				g.draw(o.getBoundingBox());
				if (o instanceof Unit) {
					g.drawString("Shield: " + ((Unit) o).shield, (float) o.x, (float) o.y - 20);
				}
			}
			g.resetTransform();
			g.drawString("Missile collection: " + missiles.size(), 0, 20);
			g.drawString("onScreen: " + onScreen.size(), 0, 40);
			g.drawString("checkpoint: " + checkpoints[checkpoint], 0, 60);

			g.flush();

		}

		if (Game.particles) {
			particleSystem.render();
		}
		g.resetTransform(); // leave graphics coordinates as they were found
		// render panel on top
		panel.render(g, player.getShield(), player.getFullShield(), player.getFirepower());
	}

	/**
	 * Draws a map region with top-left game pixel coordinates. All parameters
	 * in pixels.
	 * 
	 * @param x
	 *            Camera top-left coordinate
	 * @param y
	 *            Camera top-left coordinate
	 * @param width
	 *            View width in pixels
	 * @param height
	 *            View height in pixels
	 */
	public void drawMapRegion(int x, int y, int width, int height) {
		// variables in terms of map tiles
		int offsetX = x % tileWidth;
		int offsetY = y % tileHeight;
		int indexX = x / tileWidth;
		int indexY = y / tileHeight;

		worldMap.render(x - offsetX, y - offsetY, // location in graphics
													// context to render
				indexX, indexY, // top left block of render window
				mapWidth, // width of section to render (in tiles)
				mapHeight);
	}

	/*
	 * It would be better design to have a simpler register/update/destroy
	 * mechanism for all GameObjects and them implement the interface here, but
	 * was having issues with inheritance and checking.
	 */

	/**
	 * Register object with the world update/render routines. Should only be
	 * called inside GameObject derived constructor.
	 * 
	 * @param m
	 *            Object to register.
	 */
	public void registerMissile(Missile m) {
		this.missiles.add(m);
	}

	/**
	 * Removes the game object from world list.
	 * 
	 * @param m
	 *            Object to remove.
	 */
	public void removeMissile(Missile m) {
		this.missiles.remove(m);
	}

	/**
	 * Invokes the render method for all objects of type registered in world.
	 */
	private void renderMissiles() {
		for (Missile m : missiles) {
			m.render();
			onScreen.add(m);
		}
	}

	/**
	 * Invokes the update method for all objects of type registered in world.
	 */
	private void updateMissiles(int delta) {
		for (Missile m : missiles) {
			// build list of missiles to remove
			if (worldCamera.canSee(m) == false) {
				cleanup.add(m);
			} else {
				m.update(delta);
			}
		}
	}

	/**
	 * Invokes the update method for all objects of type registered in world.
	 */
	private void updateEnemies(int delta) {
		for (Enemy e : enemies) {
			if (worldCamera.canSee(e)) {
				e.update(delta);
			}
		}
	}

	/**
	 * Register object with the world update/render routines. Should only be
	 * called inside GameObject derived constructor.
	 * 
	 * @param m
	 *            Object to register.
	 */
	public void registerEnemy(Enemy o) {
		this.enemies.add(o);
	}

	/**
	 * Removes the game object from world list.
	 * 
	 * @param m
	 *            Object to remove.
	 */
	public void removeEnemy(Enemy o) {
		this.enemies.remove(o);
	}

	/**
	 * Invokes the render method for all objects of type registered in world.
	 */
	private void renderEnemies() {
		for (Enemy e : enemies) {
			if (worldCamera.canSee(e)) {
				e.render();
				onScreen.add(e);
			}
		}
	}

	/**
	 * Invokes the world cleanup routine. Calls back to objects to run their
	 * destroy routines. Usual side effect de-registers object from world.
	 */
	private void Cleanup() {
		for (GameObject o : cleanup) {
			o.destroy();
		}
	}

	/**
	 * Adds an object to the list of objects to be cleaned up. Clean up should
	 * be invoked once for each world update.
	 * 
	 * @param o
	 *            GameObject to be added to clean up schedule.
	 */
	public void registerCleanup(GameObject o) {
		this.cleanup.add(o);
	}

	/**
	 * Register object with the world update/render routines. Should only be
	 * called inside GameObject derived constructor.
	 * 
	 * @param m
	 *            Object to register.
	 */
	public void registerItem(Item o) {
		this.items.add(o);
	}

	/**
	 * Invokes the update method for all objects of type registered in world.
	 */
	public void updateItems(int delta) {
		for (Item i : items) {
			if (worldCamera.canSee(i)) {
				i.update(delta);
			}
		}
	}

	/**
	 * Invokes the render method for all objects of type registered in world.
	 */
	public void renderItems() {
		for (Item i : items) {
			if (worldCamera.canSee(i)) {
				i.render();
				onScreen.add(i);
			}
		}
	}

	/**
	 * Removes the game object from world list.
	 * 
	 * @param m
	 *            Object to remove.
	 */
	public void removeItem(Item item) {
		this.items.remove(item);
	}

	/**
	 * 
	 * @param o
	 *            Object to find collisions with
	 * @return Returns an ArrayList of on-screen objects colliding with o.
	 */
	public ArrayList<GameObject> getCollisions(GameObject o) {
		ArrayList<GameObject> output = new ArrayList<GameObject>();
		for (GameObject z : onScreen) {
			// add objects colliding with o that aren't itself.
			if ((z.equals(o) == false) && o.getBoundingBox().intersects(z.getBoundingBox())) {
				output.add(z);
			}
		}
		return output;

	}

}
