
public class PeriodToPay {
	final static double interestRateYear = 0.18;
	final static double interestRateMonth = interestRateYear/12;
	final static double monthlyPayment = 50;
	final static double initialCost = 1000;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			double totalPaidInterest = 0.0, debt = initialCost, monthInterestCost, pay;
			int months = 0;
			System.out.println("Debt:" + debt);
			while(debt >= 0){
				pay = monthlyPayment;
				monthInterestCost = debt*interestRateMonth;
				
				debt -= (pay - monthInterestCost);
				totalPaidInterest += monthInterestCost;
				months++;
			}
			System.out.println("The item took " + months + " months to pay off and you only paid " + totalPaidInterest + " in interest.");
			
	}

}
