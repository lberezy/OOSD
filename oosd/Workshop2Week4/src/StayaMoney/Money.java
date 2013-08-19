package StayaMoney;

public class Money {
	private int dollars, cents;
	 
	public Money(){
		this.dollars = 0;
		this.cents = 0;
	}
	
	public Money(int dollar){
		this.dollars = dollar;
	}
	
	public Money(int dollar, int cents){
		this.dollars = dollar;
		this.cents = cents;
	}
	
	public int getDollar(){
		//Holla Holla!
		System.out.println("Holla holla!");

		return this.dollars;
	}
}
