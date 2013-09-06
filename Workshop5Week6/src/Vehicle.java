
public class Vehicle {
	private String registration, make;
	private int year;
	protected double value;
	
	public Vehicle(String registration, String make, int year, double value) {
		this.registration = registration;
		this.make = make;
		this.year = year;
		this.setValue(value);
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getRegistration() {
		return registration;
	}

	public String getMake() {
		return make;
	}

	public int getYear() {
		return year;
	}
	
	public double dealerValue() {
	 return value * 1.10;
	}
	
}
