
public class Camera {
	public double x, y; //Camera current top-left position
	private double vSpeed; //Camera vertical speed (pixels/ms)
	private double hSpeed; //Camera horizontal speed (pixels/ms)
	
	public Camera(double x, double y, double vSpeed, double hSpeed) {
		//Instantiate the camera given top-left coordinates
		this.x = x;
		this.y = y;
		this.vSpeed = vSpeed;
		this.hSpeed = hSpeed;
	}
	
	public void update(int delta) {
		// adjust camera window by pixels/ms
		this.y -= vSpeed * delta;
		this.x -= hSpeed * delta;
		if (this.y <= 0) this.y = 0;	//Prevent camera from leaving top of map
	}
	
	public double getHSpeed() {
		return this.hSpeed;
	}
	
	public void setHSpeed(double hSpeed) {
		this.hSpeed = hSpeed;
	}
	
	public double getVSpeed() {
		return this.vSpeed;
	}
	
	public void setVSpeed(double vSpeed) {
		this.vSpeed = vSpeed;
	}
}
