
public class Map {
	private Organism[][] canvas;
	
	public Map(char defaultChar, int sizeX, int sizeY) {
		int x, y;
		this.canvas = new Organism[sizeY][sizeX];
		for (x = 0; x < sizeX; x++) {
			for (y = 0; y < sizeY; y++) {
				canvas[y][x] = new Organism(x, y, defaultChar);
			}
		}
	}
	
	
}
