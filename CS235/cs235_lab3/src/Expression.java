import java.util.EmptyStackException;
import java.util.Stack;



public class Expression implements ExpressionManager {

	private static final String OPEN = "([{";
	private static final String CLOSE = ")]}";


	@Override
	public boolean isBalanced(String expression) {

		//create an empty stack
		Stack<Character> s = new Stack<Character>();
		boolean balanced = true;
		try{
			int index = 0;
			while (balanced && index < expression.length()){ //while condition is true, index<length
				char nextCh = expression.charAt(index); //grab the next character
				if (isOpen(nextCh)){ //next character is an opening parenthesis 
					s.push(nextCh); //push onto the stack
				}
				else if(isClose(nextCh)) { //if it's a closing one
					char topCh = s.pop(); //pop the stack
					balanced = OPEN.indexOf(topCh) == CLOSE.indexOf(nextCh); //compare types of parent.
				}
				index++; //increase the index
			}
		}
		catch (EmptyStackException ex){
			balanced = false; // in case of exception return false for balanced

		}
		return(balanced && s.empty());
		//return balanced;
	}

	private static boolean isOpen(char ch){
		return OPEN.indexOf(ch) > -1;
	}

	private static boolean isClose(char ch){
		return CLOSE.indexOf(ch)> -1;
	}



	@Override
	public String infixToPostfix(String infixExpression) {

		InfixToPostfixParens evaluator = new InfixToPostfixParens();
		boolean checkExpression =isBalanced(infixExpression);
		String result = null;
		if(checkExpression == true)
		{

			try {
				result = evaluator.convert(infixExpression);
				return result;
			} catch (InfixToPostfixParens.SyntaxErrorException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			// TODO Auto-generated method stub
		}
		else{
			result = "Invalid";
		}
		return result;
	} //end of method

	@Override
	public String postfixToInfix(String postfixExpression) {
		
		PostfixToInfix postfix = new PostfixToInfix();
		String result = null;
		result = postfix.convert(postfixExpression);
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public String postfixEvaluate(String postfixExpression) {
		boolean checkExpression =isBalanced(postfixExpression);
		String value = null;

		if (checkExpression==true){
			PostfixEvaluator myEvaluator = new PostfixEvaluator(); // call the constructor
			//int result = 0;
			String myResult = null;
			try{
				int result = myEvaluator.eval(postfixExpression);
				if(result == 1000){
					value = "invalid";
				}
				else{
					value = myResult.valueOf(result);
				}
				
			} catch (PostfixEvaluator.SyntaxErrorException e){

			}
			// TODO Auto-generated method stub
			return value;

		}
		else{
			value = "invalid";
			return value;
		}

	}

}
