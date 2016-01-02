
public interface ExpressionManager {
	
	/**
	 * Checks whether an expression is balanced on its parentheses
	 * 
	 * - The given expression will have a space between every number or operator
	 * 
	 * @return true if expression is balanced
	 * @return false otherwise
	 */
	public boolean isBalanced(String expression);
	
	/**
	 * Converts an infix expression into a postfix expression 
	 * and returns the postfix expression
	 * 
	 * - The given infix expression will have a space between every number or operator.
	 * - The returned postfix expression must have a space between every number or operator.
	 * - Check lab requirements for what will be considered invalid.
	 * 
	 * return the String "invalid" if infixExpression is not a valid infix expression.
	 * otherwise, return the correct postfix expression as a String.
	 */
	public String infixToPostfix(String infixExpression);
	
	/**
	 * Converts a postfix expression into an infix expression 
	 * and returns the infix expression.
	 * 
	 * - The given postfix expression will have a space between every number or operator.
	 * - The returned infix expression must have a space between every number or operator.
	 * - Redundant parentheses are acceptable i.e. ( ( 3 * 4 ) + 5 ).
	 * - Check lab requirements for what will be considered invalid.
	 * 
	 * return the String "invalid" if postfixExpression is not a valid postfix expression. 
	 * otherwise, return the correct infix expression as a String.
	 */
	public String postfixToInfix(String postfixExpression);
	
	/**
	 * Evaluates a postfix expression returns the result as a string
	 * 
	 * - The given postfix expression will have a space between every number or operator.
	 * - Check lab requirements for what will be considered invalid.
	 * 
	 * return the String "invalid" if postfixExpression is not a valid postfix Expression
	 * otherwise, return the correct evaluation as a String
	 */
	public String postfixEvaluate(String postfixExpression);

}
