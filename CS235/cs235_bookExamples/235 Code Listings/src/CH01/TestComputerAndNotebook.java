/*<listing chapter="1" section="3" sequence="1">*/
/**
   Un-numbered listing in section 1.3
   @author Koffman and Wolfgang
*/

package CH01;

public class TestComputerAndNotebook {
    
    /** Tests classes Computer and Notebook. Creates an object of each and
	displays them.
	@param args[] No control parameters
    */
    public static void main(String[] args) {
	Computer myComputer =
	    new Computer("Acme", "Intel", 2, 160, 2.4);
	Notebook yourComputer =
	    new Notebook("DellGate", "AMD", 4, 240,
			 1.8, 15.0, 7.5);
	System.out.println("My computer is:\n" + myComputer.toString());
	System.out.println("\nYour computer is:\n" +
			   yourComputer.toString());
    }
}
/*</listing>*/
