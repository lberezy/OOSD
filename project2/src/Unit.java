
public abstract class Unit extends GameObject implements Moveable{
	public Unit(double x, double y, World world) {
		super(x, y, world);
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
