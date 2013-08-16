
public class Child{
	private double motherHeight, fatherHeight, height;
	private char gender;
	
	public Child(double mHeight, double fHeight, char childGender){
		motherHeight = mHeight;
		fatherHeight = fHeight;
		gender = childGender;
		height = calcHeight();
	}
	private double calcHeight(){
		if(this.gender == 'M'){
			return ((this.motherHeight * 13/12) + this.fatherHeight)/2;
		} else if(this.gender == 'F'){
			return ((this.motherHeight * 12/13) + this.fatherHeight)/2;
		}
		System.out.println("Debug, gender: " + this.gender);
		return 0.0;
	}
	public double getHeight(){
		return height;
	}
	
}