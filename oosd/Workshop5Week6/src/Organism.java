
public class Organism {
	private char sprite;
	private int x, y;
	
	public Organism (int x, int y, char sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void update(Map canvas) {
		move();
		breed();
	}
	
	public void move() {
		
	}
	
	public void breed() {
		
	}
}
