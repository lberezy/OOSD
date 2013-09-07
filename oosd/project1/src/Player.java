import org.newdawn.slick.Image;

public class Player {
	private static boolean instanceLimit = false;
	private Image sprite;
	private double x, y;
	
	public Player(double x, double y, Image sprite) {
		// only allow one player instance
		if (instanceLimit) {
			
		}
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		instanceLimit = true;
	}
	
	public void draw() {
		sprite.drawCentered((float)x, (float)y);
	}
	
	public void update(double dir_x, double dir_y, int delta) {
		this.x += 0.4 * dir_x * delta;
		this.y += 0.4 * dir_y * delta;
	}
}
