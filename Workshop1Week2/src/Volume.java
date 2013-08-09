

public class Volume {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double radius, volume;
		if(args.length == 1){
			radius = Double.parseDouble(args[0]);
			volume = calculateVolume(radius);
			System.out.println("Radius: " + radius);
			System.out.println("Volume: " + volume);
			//
			System.out.println("Stuff");
		} else {
			System.out.println("Submit 'radius' as a single command line argument.");
		}
	}
	
	public static double calculateVolume(double r){
		return 4/3.0*java.lang.Math.PI*r*r;
	}
	
}
