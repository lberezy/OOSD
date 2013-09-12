import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map extends TiledMap{
	private Boolean[][] blockTiles;
	
	public Map(String mapFile, String assetsPath) throws SlickException {
		super(mapFile, assetsPath);
		
		// build an array of blocked tiles, faster than always checking the map tiles
		blockTiles = new Boolean[this.getWidth()][this.getHeight()];
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
		    	int tileID = this.getTileId(x, y, 0);	// get tileID at point, layer 0 of tilemap
		    	blockTiles[x][y] = this.getTileProperty(tileID, "block", "0").equals("1");
			}
		}
		
	}
	
    public Boolean isValidPoint(Point2D point) {
    	/** checks if a game coordinate is a valid map tile location
    	 * 
    	 */
    	int x = (int)point.x;
    	int y = (int)point.y;
    	return isValidPoint(x, y);
    }
    
    public Boolean isValidPoint(int x, int y) {
    	/** checks if a game coordinate is a valid map tile location
    	 * 
    	 */
    	if (x < 0 || x/this.getWidth() > this.getWidth()) {
    		return false;
    	}
    	if(y < 0 || y/this.getHeight() > this.getHeight()) {
    		return false;
    	}
    	return true;
    }
    
    public Boolean checkBlock(int x, int y) {
    	/** Checks if a map tile is a blocking tile
    	 * 
    	 */
    	return blockTiles[x][y];
    }
    
    

}
