public interface TravelInterface
{
	/**
	 * Loads flight information from the file whose name is given. This method should return true if
	 * the file was of the correct format and false otherwise; see the exam specifications for
	 * information regarding the correct format of the input files.
	 * 
	 * @param input_file_name
	 *            the name of the file whose information is to be loaded
	 * @return true if the file's information was loaded correctly; false otherwise
	 */
	public boolean loadFlights(String input_file_name);

	/**
	 * Reports the cost to fly directly from the first given city to the second given city.
	 * 
	 * Under normal conditions, the returned String should be a single integer cost to fly directly
	 * from the departure city to the arrival city. See the exam specifications for information
	 * regarding the correct format of this output and other error messages for abnormal conditions.
	 * 
	 * @param departure
	 *            the departure city's name
	 * @param arrival
	 *            the arrival city's name
	 * @return the cost to fly directly from the departure city to the arrival city or an error
	 *         message
	 */
	public String reportCost(String departure, String arrival);

	/**
	 * Calculates the least expensive itinerary to travel from the first given city to the second
	 * given city.
	 * 
	 * Under normal conditions, the returned String should be an itinerary from the origin city to
	 * the destination city. See the exam specifications for information regarding the correct
	 * format of this output and other error messages for abnormal conditions.
	 * 
	 * @param origin
	 *            the origin city's name
	 * @param destination
	 *            the destination city's name
	 * @return an itinerary from the origin city to the destination city or an error message
	 */
	public String developItinerary(String origin, String destination);
}
