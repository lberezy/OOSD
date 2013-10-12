/* SWEN20003 Object Oriented Software Development
 * Space Game Engine - Skeleton
 * Author: Matt Giuca <mgiuca>
 * Derived Author: Lucas Berezy (588 236)
 * Based off supplied skeleton code. Said code reproduces works from copyrighted titles
 * Chromium B.S.U. and (Artistic License) and Battle for Wesnoth (GNU General Public License v.2+).
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Represents the entire game world. (Designed to be instantiated just once for
 * the whole game).
 */
public class World {
	private Map worldMap;
	private Player player;
	private Panel panel;
	private Camera worldCamera;
	private int mapWidth;
	private int mapHeight;
	private int tileSize;
	private int tileWidth;
	private int tileHeight;
	private ArrayList<Missile> missiles = new ArrayList<Missile>();
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<GameObject> cleanup = new ArrayList<GameObject>();

	// private ArrayList<? extends GameObject> missiles, enemies, items,
	// visible;
	// private ArrayList<? extends GameObject> j = new ArrayList<Missile>();

	public World() throws SlickException {
		/* Constructs the game world */

		// TODO: Convert x, y parameters to Point2D parameters, extend (x,y)
		// methods args with Point2D args

		// load map
		this.worldMap = new Map(Game.ASSETS_PATH + "/map.tmx", Game.ASSETS_PATH);

		// load player

		// TODO: Implement csv loading
		// this.player = new Player(1296, 13716, this);

		createUnits(Game.DATA_PATH + "/units.csv");

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
	}

	public Boolean blockAtPoint(int x, int y) {
		/**
		 * queries the map if a game-world point is in a region that should
		 * block movement returns false if the point is invalid anyway
		 */

		// TODO: use if (worldMap.isValidPoint(x, y)) test, but method is not
		// working correctly at this stage
		int tileX = x / tileSize;
		int tileY = y / tileSize;

		if (tileX < 0 || tileX > worldMap.getWidth() || tileY < 0 || tileY > worldMap.getHeight()) {
			return true;
		} else {
			return worldMap.checkBlock(x / tileSize, y / tileSize);

		}

		// following method also works, but is less efficient

		// int tileID = worldMap.getTileId(x/tileWidth, y/tileHeight, 0); // get
		// tileID at point, layer 0 of map
		// return (worldMap.getTileProperty(tileID, "block", "0").equals("1"));
	}

	private void createUnits(String unitsFile) {
		BufferedReader reader = null;
		try {
			File file = new File(unitsFile);
			reader = new BufferedReader(new FileReader(file));

			String line;
			String[] tokens;
			while ((line = reader.readLine()) != null) {
				tokens = line.split(","); // csv splitting

				// print the tokens
				for (String s : tokens) {
					System.out.println(s);
					// unit name, x, y
					createUnit(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
				}

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

	private void createUnit(String unitString, int x, int y) throws SlickException {
		Units unit = Units.valueOf(unitString);
		switch (unit) {
		case Player:
			player = new Player(x, y, this);
		case Asteroid:
			enemies.add(new Asteroid(x, y, this));
			break;
		case Boss:
			enemies.add(new Boss(x, y, this));
			break;
		case Drone:
			enemies.add(new Drone(x, y, this));
			break;
		case Fighter:
			enemies.add(new Fighter(x, y, this));
			break;
		default:
			// TODO: throw unhandled unit exception here
			break;
		}
	}

	public Boolean blockAtPoint(Point2D point) {
		/**
		 * extends support for Point2D points to blockAtPoint. Polymorphism is
		 * cool.
		 */
		int x = (int) point.x;
		int y = (int) point.y;
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
		updateEnemies(delta);
		updateMissiles(delta);
		runCleanup();
	}

	/**
	 * Render the entire screen, so it reflects the current game state.
	 * 
	 * @param g
	 *            The Slick graphics object, used for drawing.
	 */
	public void render(Graphics g) throws SlickException {
		g.translate(-(float) worldCamera.x, -(float) worldCamera.y); // move the
																		// graphics
																		// coordinates
																		// relative
																		// to
																		// camera
		// camera location is now (0, 0) for drawing
		drawMapRegion((int) worldCamera.x, (int) worldCamera.y, this.mapWidth, this.mapHeight);
		player.render();
		renderEnemies();
		renderMissiles();
		// for debugging
		if (Game.debug) {
			g.setColor(Color.orange);

			g.drawString("X tile: " + (int) player.x / tileSize, (float) player.x, (float) player.y);
			g.drawString("Y tile: " + (int) player.y / tileSize, (float) player.x, (float) player.y + 20);
			g.drawOval((float) player.x, (float) player.y - 32, 1f, 1f, 6);
			g.drawOval((float) player.x, (float) player.y + 32, 1f, 1f, 6);
			g.drawOval((float) player.x - 32, (float) player.y, 1f, 1f, 6);
			g.drawOval((float) player.x + 32, (float) player.y, 1f, 1f, 6);
			g.draw(player.getBoundingBox());
			g.resetTransform();
			g.drawString("Missile collection: " + missiles.size(), 0, 20);

		}
		g.resetTransform(); // leave graphics coordinates as they were found

		panel.render(g, player.getShield(), player.getFullShield(), player.getFirepower()); // render
																							// panel
																							// on
																							// top
																							// always
	}

	public void drawMapRegion(int x, int y, int width, int height) {
		/** Attempts to render only the tiles being seen by the camera */
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

	public void registerMissile(Missile m) {
		this.missiles.add(m);
	}

	public void removeMissile(Missile m) {
		this.missiles.remove(m);
	}

	private void renderMissiles() {
		for (Missile m : missiles) {
			m.render();
		}
	}

	private void updateMissiles(int delta) {
		for (Missile m : missiles) {
			m.update(delta);
			// build list of missiles to remove
			if (worldCamera.canSee(m) == false) {
				cleanup.add(m);
			}
		}
	}

	private void updateEnemies(int delta) {
		for (Enemy e : enemies) {
			if (worldCamera.canSee(e)) {
				e.update(delta);
			}
		}
	}

	private void renderEnemies() {
		for (Enemy e : enemies) {
			if (worldCamera.canSee(e)) {
				e.render();
			}
		}
	}

	private void runCleanup() {
		for (GameObject o : cleanup) {
			o.destroy();
		}
	}

	public void addToCleanup(GameObject o) {
		this.cleanup.add(o);
	}
}
