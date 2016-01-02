/* name: Andrei Y Rybin
 * studentID: 609676182
 * CS 235 Fall 2011 Midterm 1
 */
public interface MidtermExpressionInterface
{
	/**
	 * Attempts to evaluate the given infix expression.
	 * 
	 * See exam specifications with regards to the functionality of this method.
	 * 
	 * @param input
	 *            a String to be evaluated as an infix expression
	 * @return a statement showing the original expression and its evaluated value or reason for
	 *         being invalid
	 */
	public String evaluateInfixExpression(String input);
}
