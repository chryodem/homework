

public class MimicFactory
{
	/**
	 * Creates and returns an object whose class implements MimicInterface.
	 * This should be an object of the class you have created.
	 * 
	 * Example: If you made a class called "Mimic", you might say "return new Mimic()"
	 * 
	 * @return a new object whose class implements MimicInterface
	 */
	public static MimicInterface createMimic()
	{
		return new Mimic();
	}
}