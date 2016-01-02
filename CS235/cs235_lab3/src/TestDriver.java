
public class TestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String test1 = "4 * ( 2 * + 4 - ( 2 + 2 ) ) - 4 / 5";
		
	//	String test2 = "( { } ] )";
		
		Expression ex = new Expression();
		
		System.out.println(ex.infixToPostfix(test1));
	//	System.out.println(ex.isBalanced(test2));

	}

}
