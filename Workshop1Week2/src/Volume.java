

public class Volume {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double r, volume;
		if(args.length == 1){
			r = Double.parseDouble(args[0]);
			volume = 4/3.0*java.lang.Math.PI*r*r;
			System.out.println("Radius: " + r);
			System.out.println("Volume: " + volume);
		} else {
			System.out.println("Submit 'radius' as a single command line argument.");
		}
	}
	
}
