import java.text.DecimalFormat;

public class DisplayDecimal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DecimalFormat percFormatter = new DecimalFormat("00.0");
		double value = 57.321;
		System.out.println(percFormatter.format(value) + '%');
	}

}
