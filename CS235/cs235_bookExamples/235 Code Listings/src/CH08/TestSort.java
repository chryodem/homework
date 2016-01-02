/*<listing chapter="8" number="11">*/
package CH08;

/** Driver program to test sorting methods.
    @param args Not used
    @author Koffman and Wolfgang
*/

import javax.swing.JOptionPane;
import java.util.Random;
import java.util.Arrays;

public class TestSort {
    
    public static void main(String[] args) {
	int size = Integer.parseInt
	    (JOptionPane.showInputDialog("Enter Array size:"));
	Integer[] items = new Integer[size]; // Array to sort.
	Integer[] copy = new Integer[size]; // Copy of array.
	Random rInt = new Random(); // For random number generation
	
	// Fill the array and copy with random Integers.
	for (int i = 0; i < items.length; i++) {
	    items[i] = rInt.nextInt();
	    copy[i] = items[i];
	}
	
	// Sort with utility method.
	long startTime = System.currentTimeMillis();
	Arrays.sort(items);
	System.out.println("Utility sort time is "
			   + (System.currentTimeMillis()
			      - startTime) + "ms");
	JOptionPane.showMessageDialog(null,
				      "Utility sort successful (true/false): "
				      + verify(items));
	
	// Reload array items from array copy.
	for (int i = 0; i < items.length; i++) {
	    items[i] = copy[i];
	}
	
	// Sort with quicksort.
	startTime = System.currentTimeMillis();
	(new QuickSort1()).sort(items);
	System.out.println("QuickSort time is "
			   + (System.currentTimeMillis()
			      - startTime) + " ms");
	JOptionPane.showMessageDialog(null,
				      "Your Sort successful (true/false): "
				      + verify(items));
	
	dumpTable(items); // Display part of the array.
	System.exit(0);
    }
    
    /** Verifies that the elements in array test are
	in increasing order.
	@param test The array to verify
	@return true if the elements are in increasing order;
	false if any 2 elements are not in increasing order
    */
    private static boolean verify(Comparable[] test) {
	boolean ok = true;
	int i = 0;
	while (ok && i < test.length - 1) {
	    ok = test[i].compareTo(test[i + 1]) <= 0;
	    i++;
	}
	return ok;
    }
    
    /*<exercise chapter="8" section="10" type="programming" number="1">*/
    private static void dumpTable(Integer[] thetable) {
	if (thetable.length <= 20) {
	    for (int i = 0; i < thetable.length; i++) {
		System.out.println(i + ": " + thetable[i]);
	    }
	} else {
	    int mid = 10;
	    for (int i = 0; i < mid; i++) {
		System.out.println(i + ": " + thetable[i]);
	    }
	    if (mid == 10) {
		System.out.println(". . .");
	    }
	    for (int i = thetable.length - mid; i < thetable.length; i++) {
		System.out.println(i + ": " + thetable[i]);
	    }
	}
    }
    /*</exercise>*/
}
/*</listing>*/
