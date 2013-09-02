import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;

public class PlayerObject {
	private int x, y;
	private double yaw;
	private Image sprite;
	private double speed = 0.3;
	private int width, height;
	private double yawSpeed = 0.03;
	
	public PlayerObject(int x, int y, Image sprite){
		this.setPos(x, y);
		this.yaw = 0;
		this.sprite = sprite;
	} 
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void updateXPos(int delta)	 {
		this.x += delta*speed;
	}
	
	public void updateYPos(int delta) {
		this.y += delta*speed;
	}
	
	public int getWidth() {
		return this.sprite.getWidth();
	}
	
	public int getHeight() {
		return this.sprite.getHeight();
	}
	
	public void updateYaw(int delta) {
		this.yaw += yawSpeed*delta;
		this.sprite.rotate((float)yaw);
	}
	
	public void draw(Graphics context){
		context.drawImage(this.sprite, this.x, this.y);
	}
}
