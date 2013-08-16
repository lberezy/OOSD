import java.util.Scanner;


public class PredictHeight {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner keyboard = new Scanner(System.in);
		boolean loop = true;
		Child child;
		char childGender;
		double motherHeight, fatherHeight;
		while(loop){
			System.out.println("Please enter the mother's height (inches): ");
			motherHeight = keyboard.nextDouble();
			System.out.println("Please enter the father's height (inches): ");
			fatherHeight = keyboard.nextDouble();
			System.out.println("Please enter the child's gender (M/F): ");
			childGender = (keyboard.next()).charAt(0);
					
			child = new Child(motherHeight,fatherHeight,childGender);
			
			System.out.println("The predicted height of the child is: " + child.getHeight()/12 + " feet and " + 
								child.getHeight()%12 + " inches.");
			
			System.out.println("If you would like to run this again, press 'Y'");
			if((keyboard.next()).charAt(0) != 'Y'){
				loop = false;	
			}
		}
	}
		
	
}


