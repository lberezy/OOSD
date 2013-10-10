import org.newdawn.slick.Image;


public abstract class Unit extends GameObject implements Drawable{
	public Unit(double x, double y, Image sprite, World world) {
		super(x, y, sprite, world);
		// TODO Auto-generated constructor stub
	}

	private double fullShield;
	private double damage;
	private double firepower;
	
	
	public double getFullShield() {
		return fullShield;
	}
	
	public double getDamage() {
		return damage;
	}
	
	public double getFirepower() {
		return firepower;
	}
	
}
