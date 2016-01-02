/* name: Andrei Y Rybin
 * studentID: 609676182
 * CS 235 Fall 2011 Midterm 1
 */
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

public class MidtermExpression implements MidtermExpressionInterface {


	private static final String OPEN = "([{<";
	private static final String CLOSE = ")]}>";
	private int answer = 0, addition,subtraction ,multiplication, devision, modulo, lhs, rhs, originalMiddle, removingValue;
	private String result;
	private int numberOfDigits = 0, numberOfOperands = 0;
	private ArrayList <String> noParen = new ArrayList<String>(); // to hold values without paren
	private ArrayList<String> beforeNoParen = new ArrayList<String>(); //to hold values between paren
	private ArrayList<String> theValues = new ArrayList<String>();

	@Override
	public String evaluateInfixExpression(String input) {
		theValues = new ArrayList <String> ();
		
		//check to make sure the parentheses are correct then proceed
		boolean isBalance = isBalanced(input);
		if(isBalance != true){
			return "invalid input, mismatched parentheses";
		}
		
		//if parenthesses are correct proceed
		String nextToken;
		Scanner scan = new Scanner(input);
		scan.useDelimiter(" ");
		
		//create a new data structure with the value of the string
		while(scan.hasNext()==true){
			nextToken = scan.next();
			if(nextToken.charAt(0)=='[' || nextToken.charAt(0)=='{' || nextToken.charAt(0)=='<'){
				nextToken = "(";
			}

			if(nextToken.charAt(0)==']' || nextToken.charAt(0)=='}' || nextToken.charAt(0)=='>'){
				nextToken = ")";
			}
			theValues.add(nextToken);
		} //end of while
		
		
		//check to make sure it's in the correct format
		numberOfDigits = 0;
		numberOfOperands = 0;
		for(int i = 0; i < theValues.size();i++){
			if(Character.isDigit(theValues.get(i).charAt(0))){
				numberOfDigits++;
			}
			else if(theValues.get(i).equals("+")|| theValues.get(i).equals("-")|| theValues.get(i).equals("*")
				|| theValues.get(i).equals("/") || theValues.get(i).equals("%")){
				numberOfOperands++;
			}
		} // end of for loop
		
		if((theValues.get(0).equals("+")|| theValues.get(0).equals("-")|| theValues.get(0).equals("*")
				|| theValues.get(0).equals("/") || theValues.get(0).equals("%"))){
			return "invalid input, incorrect infix format";
		}
		
		if(numberOfOperands>=numberOfDigits){
			return "invalid input, too many operands";
		}
		else if((numberOfDigits-numberOfOperands)>1){
			return "invalid input, insufficent operands";
		}
		
		ParentChecker(); //go inside the parentheses and perform the calculations

		return result;
	} //end of method

	private void ParentChecker() {

		beforeNoParen = new ArrayList<String>();//is populated with what's inside of the first paren
		noParen = new ArrayList<String>(); //same as above except no parentheses
		
		//go through and grab value inside of the set and then remove parentheses
		if(theValues.contains("(") || theValues.contains(")")){
			int i = 0;
			while(!(theValues.get(i).equals(")"))){
				beforeNoParen.add(theValues.get(i)); // adding values to )
				i++;

			}
			int j = (beforeNoParen.size()-1);
			while(!(beforeNoParen.get(j).equals("("))){
				noParen.add(0, beforeNoParen.get(j));
				j--;
			}

			while(noParen.contains("(")){
				int indexOfOpening = noParen.indexOf("(");
				noParen.remove(indexOfOpening);
			}
			int middle = noParen.size()/2;
			removingValue = middle +1;

			originalMiddle = theValues.indexOf(noParen.get(middle)); //value used for removal

			noParentheses();
			//grabbing values of the last parentheses


		} //end of if there are parens
		else{
			noParen = theValues;
			noParentheses();
			//method do calculate the values when no parentheses

		}

	}

	private void noParentheses(){
		char op;
		addition = noParen.indexOf("+");
		subtraction = noParen.indexOf("-");
		multiplication = noParen.indexOf("*");
		devision = noParen.indexOf("/");
		modulo = noParen.indexOf("%");
		
		try{
		if(noParen.contains("%")){

			op = noParen.get(modulo).charAt(0);
			lhs = Integer.parseInt(noParen.get(modulo-1));
			rhs = Integer.parseInt(noParen.get(modulo+1));
			if(rhs == 0){
				result = "invalid, cannot devide by zero";
			}
			else{
			answer = lhs%rhs;
			noParen.remove(modulo+1);
			noParen.set(modulo, Integer.toString(answer));
			noParen.remove(modulo-1);
			noParentheses();
			}

		}
		
		else if(noParen.contains("/")){
			op = noParen.get(devision).charAt(0);
			lhs = Integer.parseInt(noParen.get(devision-1));
			rhs = Integer.parseInt(noParen.get(devision+1));
			if(rhs == 0){
				result = "invalid, cannot devide by zero!";
			}
			else{
			answer = lhs/rhs;
			noParen.remove(devision+1);
			noParen.set(devision, Integer.toString(answer));
			noParen.remove(devision-1);
			noParentheses();
			}
		}
		else if(noParen.contains("*")){
			op = noParen.get(multiplication).charAt(0);
			lhs = Integer.parseInt(noParen.get(multiplication-1));
			rhs = Integer.parseInt(noParen.get(multiplication+1));
			answer = lhs * rhs;
			noParen.remove(multiplication+1);
			noParen.set(multiplication, Integer.toString(answer));
			noParen.remove(multiplication-1);
			noParentheses();

		}
		else if(noParen.contains("+")){
			op = noParen.get(addition).charAt(0);
			lhs = Integer.parseInt(noParen.get(addition-1));
			rhs = Integer.parseInt(noParen.get(addition+1));
			answer = lhs+rhs;
			noParen.remove(addition+1);
			noParen.set(addition, Integer.toString(answer));
			noParen.remove(addition-1);
			noParentheses();
		}
		else if(noParen.contains("-")){
			op = noParen.get(subtraction).charAt(0);
			lhs = Integer.parseInt(noParen.get(subtraction-1));
			rhs = Integer.parseInt(noParen.get(subtraction+1));
			answer = lhs-rhs;
			noParen.remove(subtraction+1);
			noParen.set(subtraction, Integer.toString(answer));
			noParen.remove(subtraction-1);
			noParentheses();
		}
		
		else if(noParen.size()==1){
			if(theValues.size()>1){
				
				
				//call this after the calculation inside the parentheses had been finished
				ValuesRemover();
			}
			else{
				theValues = noParen;
				result = theValues.get(0);
			}
		} //end of last else if
		}
		catch(Exception e){
			result = "invalid operation, invalid infix format";
		}
			

	} // end of evaluation inside the parens
	
	private void ValuesRemover() {
		
		theValues.set(originalMiddle, noParen.get(0));
		int remover = removingValue;
		while(remover>0){
			theValues.remove(originalMiddle+1);
			remover--;
		}

		remover = removingValue;
		int removeBefore = removingValue;
		while(remover>0){
			theValues.remove(originalMiddle-removeBefore);
			remover--;
		}
			ParentChecker();
	}

	private boolean isBalanced(String expression) {
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
	}
	private static boolean isOpen(char ch){
		return OPEN.indexOf(ch) > -1;
	}

	private static boolean isClose(char ch){
		return CLOSE.indexOf(ch)> -1;
	}
}
