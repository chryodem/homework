
public class StationFactory
{
	/**
	 * Creates and returns an object whose class implements StationInterface.
	 * This should be an object of the class you have created.
	 * 
	 * Example: If you made a class called "Station", you might say "return new Station()"
	 * 
	 * @return a new object whose class implements StationInterface
	 */
	public static StationInterface createStation()
	{
		return new Station();
	}
}
