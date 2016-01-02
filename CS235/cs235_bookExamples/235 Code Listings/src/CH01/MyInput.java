/*<listing chapter="1" section="6" sequence="1"*/

package CH01;

import java.util.Scanner;
import java.util.InputMismatchException;

/** Class to contain statin input methods */
public class MyInput {

    /** Reads an integer using a scanner.
	@return the first integer read.
    */
    public static int getIntValue(Scanner scan) {
	int nextInt = 0;          // next int value
	boolean validInt = false; // flag for valid input
	while (!validInt) {
	    try {
		System.out.println("Enter number of kids:");
		nextInt = scan.nextInt();
		validInt = true;
	    } catch (InputMismatchException ex) {
		scan.nextLine();  // clear buffer
		System.out.println("Bad data -- enter an integsr:");
	    }
	}
	return nextInt;
    }
}
/*</listing>*/
