
public class VehicleTester {
	
	
	public static void main(String[] args) {
		Vehicle newCar = new Vehicle("asdf", "jkl", 1970, 100);
		SecondHandVehicle oldCar = new SecondHandVehicle("Oldasdf", "Oldjkl", 1960, 88, 2);
		
		
		System.out.println("newCar dealer price: " + newCar.dealerValue());
		System.out.println("oldCar dealer price: " + oldCar.dealerValue());

	}
}
