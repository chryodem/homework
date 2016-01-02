
public class TravelFactory
{
	/**
	 * Creates and returns an object whose class implements TravelInterface.
	 * This should be an object of the class you have created.
	 * 
	 * Example: If you made a class called "Travel", you might say "return new Travel();"
	 * 
	 * @return a new object whose class implements TravelInterface
	 */
	public static TravelInterface createTravel()
	{
		return new Travel();
	}
}