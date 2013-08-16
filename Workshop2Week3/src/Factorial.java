public class Factorial { // return n!

		public static int factorial(int n) { 
			if (n < 0)
				throw new RuntimeException("Factorial of negative values is not defined");
			else if (n == 0)
				return 1;
			else {
				int recursion = factorial(n-1); int step = n * recursion; 
				return step;
			} 
		}
		
		public static void main(String[] args) { 
			int N = Integer.parseInt(args[0]); 
			for (int i = N; i <= N + 5; i++) {
				System.out.printf("Factorial of %d is: %d  %n",i,factorial(i));
			} 
		}
}