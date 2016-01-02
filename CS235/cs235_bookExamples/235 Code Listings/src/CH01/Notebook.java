/*<listing chapter="1" number="3">*/
/**
   Listing 1.3
   @author Koffman and Wolfgang
*/

package CH01;

/** Class that represents a notebook computer. */
public class Notebook extends Computer {
    // Data Fields
    private double screenSize;
    private double weight;
    // Methods
    /** Initializes a Notebook object with all properties specified.
	@param man The computer manufacturer
	@param proc The processor type
	@param ram The RAM size
	@param disk The disk size
	@param procSpeed The processor speed
	@param screen The screen size
	@param wei The weight
    */
    public Notebook(String man, String proc, double ram, int disk,
		    double
		    procSpeed, double screen, double wei) {
	super(man, proc, ram, disk, procSpeed);
	screenSize = screen;
	weight = wei;
    }
    /*<exercise chapter="1" section="2" number="2" type="programming">*/
    // Accessor and modifier methods
    public double getScreenSize() { return screenSize; }
    public double getWeight() { return weight; }
    public void setScreenSize(double screen) { screenSize = screen; }
    public void setWeight(double wei) { weight = wei; }
    /*</exercise>*/
}
/*</listing>*/
