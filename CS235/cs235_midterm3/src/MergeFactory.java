
public class MergeFactory
{
	/**
	 * Creates and returns an object whose class implements MergeInterface.
	 * This should be an object of the class you have created.
	 * 
	 * Example: If you made a class called "Merge", you might say "return new Merge();"
	 * 
	 * @return a new object whose class implements MergeInterface
	 */
	public static MergeInterface createMerge()
	{
		return new Merge();
	}
}
