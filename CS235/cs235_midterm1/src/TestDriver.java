/* name: Andrei Y Rybin
 * studentID: 609676182
 * CS 235 Fall 2011 Midterm 1
 */
public class TestDriver {

	/**
	 * @param args
	 * @throws ScriptException 
	 */
	public static void main(String[] args) {
		
		String test1 = "( ( 3 + 10 ) * ( 20 / 6 - 1 ) ) / 3"; // invalid
		
		String test2 = "( 10 / 10 * 5 * 2 ) + 3"; // valid
		
		String test3 = "( { } ] )"; //mismatched parentheses
		String test4 = "3 - 10 / 0 - 6 / 10 * 3"; // attempt to devide by 0;
		String test5 = "3"; //valid expression
		String test6 = "/ * + 3 10 / 20 - 6 1 3"; //improperly ordered expression
		String test7 = "3 10"; // no operands
		String test8 = "3 + 10 /"; //trying too many operands
		String test9 = "(3 + (-10))"; //trying negative number
		
		MidtermExpression me = new MidtermExpression();
		//testing different expression and values
		//me.evaluateInfixExpression(test2);
		System.out.println(me.evaluateInfixExpression(test6));
		System.out.println(me.evaluateInfixExpression(test2));
		int x = (10/10*5*2)+3; //making sure the values are the same
		System.out.println(x);
		System.out.println(me.evaluateInfixExpression(test1));
		System.out.println(me.evaluateInfixExpression(test5));
		System.out.println(me.evaluateInfixExpression(test3));
		System.out.println(me.evaluateInfixExpression(test6));
		System.out.println(me.evaluateInfixExpression(test7));
		System.out.println(me.evaluateInfixExpression(test8));
		System.out.println(me.evaluateInfixExpression(test9));
	
	}

}
