import java.util.Scanner;


public class ComputeSquareRoot {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Please enter a number:");
		double num = keyboard.nextDouble();
		System.out.println("You entered: " + num);
		System.out.println("Its square root is: " + squareRoot(num));
		System.out.println("Math.sqrt() says: " + Math.sqrt(num));
	}
	
	private static double squareRoot(double n){
		double preg = n/2, curg = n;
		while(Math.abs(preg - curg) > 0.01*preg){
					preg = curg;
					curg = (curg+(n/curg))/2;
		}
		return preg;
		
	}

}
