
public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Investment Business = new Business(200,300,400,5);
		Business.profit();
		Investment Rental = new Rental(40,30,2,11,0);
		Rental.profit();
		Investment Stock = new Stock(120,110);
		Stock.profit();

	}

}
