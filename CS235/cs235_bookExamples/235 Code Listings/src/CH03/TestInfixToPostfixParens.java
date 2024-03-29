package CH03;
import java.util.Scanner;

public class TestInfixToPostfixParens
{
    public static void main(String[] args) {

	InfixToPostfixParens evaluator = new InfixToPostfixParens();

	String line;
	Scanner in = new Scanner(System.in);
	do {

	    System.out.println("Enter an infix expression to evaluate");
	    if (in.hasNextLine()) {
		line = in.nextLine();
		try {
		    String result = evaluator.convert(line);
		    System.out.println("Value is " + result);
		} catch (InfixToPostfixParens.SyntaxErrorException ex) {
		    System.out.println("Syntax error " + ex.getMessage());
		}
	    } else {
		break;
	    }
	} while (true);
    }
}
