
public class Map {
	private Organism[][] canvas; // 0 indexed
	private int sizeX, sizeY;
	private char defaultChar;
	
	public Map(char defaultChar, int sizeX, int sizeY) {
		assert(sizeX > 0);
		assert(sizeY > 0);
		
		this.defaultChar = defaultChar;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		
		this.canvas = new Organism[sizeY][sizeX];
	}
	
	public Boolean createOrganism(Organism critter, Point point) {
		int x = point.x;
		int y = point.y;
		
		if (validCoord(point) && getOrganism(point).getSprite() == defaultChar) {
			this.canvas[y-1][x-1] = critter;
			return true;
		} else {
			return false;
		}
			
	}
	
	public Boolean validCoord(Point point) {
		int x = point.x;
		int y = point.y;
		if ( (x > 0 && x <= sizeX) && (x > 0 && x <= sizeX) ) {
			return true;
		} else {
			return false;
		}
	}
	
	public Organism getOrganism(Point point) {
		return canvas[point.y-1][point.x-1];
	}
	
	public Boolean isFree(Point point) {
		int x = point.x;
		int y = point.y;
		if (canvas[y-1][x-1] == null && validCoord(point)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void swap(Point newPoint, Point oldPoint) {
		Organism temp;
		int oldX = oldPoint.x;
		int oldY = oldPoint.y;
		int newX = newPoint.x;
		int newY = newPoint.y;
		
		temp = canvas[oldY - 1][oldX - 1];
		canvas[oldY - 1][oldX - 1] = canvas[newY - 1][newX - 1];
		canvas[newY - 1][newX - 1] = temp;
	}
	public Point findFree(Point point) {
		int xOffset, yOffset;
		int x = point.x;
		int y = point.y;
		Point tmp = new Point(point);
		
		for (xOffset = -1; xOffset <= 1; xOffset++) {
			for (yOffset = -1; yOffset <= 1; yOffset++) {
				tmp.x = x + xOffset;
				tmp.y = y + yOffset;
				if (this.isFree(tmp)) {
					return new Point(x + xOffset, y + yOffset);
				}
			}
		}
		return null;
	}
	
	public void draw() {
		int x, y;
		Point tmp = new Point(0,0);
		
		for (y = 0; y < sizeY; y++) {
			for (x = 0; x < sizeX; x++) {
				if (canvas[y][x] == null)  {
					System.out.print(defaultChar);
				} else {
					tmp.x = x;
					tmp.y = y;
					System.out.print(this.getOrganism(tmp).getSprite());
				}
			}
			System.out.print('\n');
		}
	}
	
	
}
