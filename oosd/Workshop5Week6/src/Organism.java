
public abstract class Organism {
	private char sprite;
	private Point location;
	
	public Organism (Point point, char sprite) {
		this.location = point;
		this.sprite = sprite;
	}
	
	public void update(Map grid) {
		move(grid);
		breed(grid);
	}
	
	public void move(Map grid) {
		Point found = new Point(grid.findFree(location));
		if (found != null) {
			grid.swap(location, found);
		}
	}
	
	public void breed(Map grid) {
		
	}
	
	public char getSprite() {
		return this.sprite;
	}
	
}
