
public class ArenaFactory
{
	/**
	 * Creates and returns an object whose class implements ArenaInterface.
	 * This should be an object of the class you have created.
	 * 
	 * Example: If you made a class called "Arena", you might say "return new Arena()"
	 * 
	 * @return a new object whose class implements ArenaInterface
	 */
	public static ArenaInterface createArena()
	{
		return new Arena();
	}
}