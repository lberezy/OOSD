
public class SecondHandVehicle extends Vehicle {
	private int numberOfOwners;
	
	public SecondHandVehicle(String registration, String make, int year,
			double value, int numberOfOwners) {
		super(registration, make, year, value);
		this.numberOfOwners = numberOfOwners;
	}
	public int getNumberOfOwners() {
		return numberOfOwners;
	}
	
	public double dealerValue() {
		 return this.getValue() * 1.20;
	}
}
