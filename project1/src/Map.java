import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map extends TiledMap{
	private Boolean[][] blockTiles;
	
	public Map(String mapFile, String assetsPath) throws SlickException {
		super(mapFile, assetsPath);
		
		// build an array of blocked tiles, faster than always checking the map
		blockTiles = new Boolean[this.getWidth()][this.getHeight()];
		for (int x = 0; x < getWidth() - 1; x++) {
			for (int y = 0; y < getHeight() - 1; y++) {
		    	int tileID = this.getTileId(x, y, 0);	// get tileID at point, layer 0 of tilemap
				System.out.println(x + " " + y + " " + tileID );
		    	System.out.println(blockTiles[x][y] = this.getTileProperty(tileID, "block", "0").equals("1"));
			}
		}
		
	}
	
    public Boolean isValidPoint(Point2D point) {
    	/** checks if a game coordinate is a valid map tile location
    	 * 
    	 */
    	double x = point.x;
    	double y = point.y;
    	return isValidPoint(x, y);
    }
    
    public Boolean checkBlock(int x, int y) {
    	return blockTiles[x][y];
    }
    
    
    public Boolean isValidPoint(double x, double y) {
    	/** checks if a game coordinate is a valid map tile location
    	 * 
    	 */
    	if (x < 0 || x/this.getWidth() > this.getWidth()) {
    		return false;
    	}
    	if(y < 0 || x/this.getHeight() > this.getHeight()) {
    		return false;
    	}
    	return true;
    }
}
