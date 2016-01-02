/* name: Andrei Y Rybin
 * studentID: 609676182
 * CS 235 Fall 2011 Midterm 1
 */
public class MidtermExpressionFactory
{
	/**
	 * Creates and returns an object whose class implements MidtermExpressionInterface.
	 * This should be an object of the class you have created.
	 * 
	 * Example: If you made a class called "MidtermExpression", you might say "return new MidtermExpression()"
	 * 
	 * @return a new object whose class implements MidtermExpressionInterface
	 */
	public static MidtermExpressionInterface createMidtermExpression()
	{
		return new MidtermExpression();
	}
}
