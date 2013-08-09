import java.util.Scanner;

public class MultiplyNumbers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double a, b;
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please enter two numbers to multiply:");
		a = keyboard.nextDouble();
		System.out.println("You entered: " + a +"\n Please enter another number:");
		b = keyboard.nextDouble();
		System.out.println("You entered: " + b);
		System.out.println("The product of "+ a + " and " + b + " is " + a*b+".");
	}

}
