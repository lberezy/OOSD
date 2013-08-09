
public class DisplayPersonalInfo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length == 3){
			System.out.println("Name: " + args[0]);
			System.out.println("Telephone: " + args[1]);
			System.out.println("Address: " + args[2]);
		} else{
			System.out.println("Enter 3 command line arguments as Name, Telephone, Address!");
		}
	}

}
