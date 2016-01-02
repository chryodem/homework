
public class myDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String str = "sample_input1.txt";
		String str1 = "sample_input.txt";
		
		Travel travel = new Travel();
		
		System.out.println(travel.loadFlights(str));
		String departure = "San_Francisco";
		String arrival = "Salt_Lake_City";
		
		String departure1 = "Salt_Lake_City";
		String arrival1 = "Seattle";
		System.out.println(travel.loadFlights(str1));
		
	//	System.out.println(travel.myMaze.length);
		System.out.println(travel.reportCost(departure, arrival));
		
	System.out.println(travel.reportCost(departure1, arrival1));
		
	System.out.println(travel.developItinerary(departure, arrival));
		
		
	System.out.println(travel.developItinerary(departure1, arrival1));

	}

}
