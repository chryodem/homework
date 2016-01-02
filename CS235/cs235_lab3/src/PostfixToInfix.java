import java.util.Scanner;
import java.util.Stack;


public class PostfixToInfix {

	int numberOfOperators = 0;
	int numberOfDigits = 0;
	String lhs,rhs, nextToken, result;
	int numberOf = 0;

	public Stack<String> operators = new Stack<String>();
	public static final String OPERATORS = "-+*/()";
	public StringBuilder infix;

	public String convert (String postfix){
		operators = new Stack <String>();

		infix = new StringBuilder();

		Scanner scan = new Scanner(postfix);

		if(postfix.length()==1)
			return "invalid";

		//nextToken;
		//String result = null;
		//lhs = null;
		//rhs = null;

		while(scan.hasNext()==true){
			nextToken = scan.next();
			
			
			//System.out.println("value of next token is: " + nextToken);

			char firstChar = nextToken.charAt(0);

			if (Character.isJavaIdentifierStart(firstChar) || Character.isDigit(firstChar)) {
				Scanner newScanner = new Scanner(nextToken);
				
				if(newScanner.hasNextInt()!=true){
					return "invalid";
				}
				
				//System.out.println("Found a digit: " + firstChar);
				numberOfDigits++;
				operators.push(nextToken);
			//	System.out.println(operators);
				//System.out.println("what's on top of the stack: " + operators.peek());
			}
			else if (isOperator(firstChar)) {
				
			//pay attention to left hand side, right hand side operators
			
				if(operators.isEmpty()!=true){
					rhs = operators.pop();
				}
				
				if(operators.isEmpty()!=true){
					lhs = operators.pop();
				}
				numberOfOperators++;
				if(numberOfOperators >= numberOfDigits){
					return "invalid";
				}
				result = appending(lhs,rhs);
			}
			
		} //end of while
		
		if(numberOfOperators>=numberOfDigits || numberOfOperators == 0 || (numberOfDigits-numberOfOperators)>1){
			return "invalid";
		}
		
		return result;

	}

	private String appending(String lhs, String rhs) {
		
		infix = new StringBuilder();
		infix.append('(');
		infix.append(' ');
		infix.append(lhs);
		infix.append(' ');
		infix.append(nextToken);
		infix.append(' ');
		infix.append(rhs);
		infix.append(' ');
		infix.append(')');
		//System.out.println(infix);
		
		String tempResult = infix.toString();
	//	System.out.println("tempResult is:" + tempResult);
		operators.push(tempResult);
	
		
		return tempResult;
		// TODO Auto-generated method stub
		
	}

	private boolean isOperator(char ch) {
		return OPERATORS.indexOf(ch) != -1;
	}


}
