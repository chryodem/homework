/*<listing chapter="4" section="5">*/
package CH04;

import java.util.*;

/** A class to represent a passenger.
 *  @author Koffman & Wolfgang
 **/

public class Passenger {
    
    // Data Fields
    /** The ID number for this passenger. */
    private int passengerId;
    
    /** The time needed to process this passenger. */
    private int processingTime;
    
    /** The time this passenger arrives. */
    private int arrivalTime;
    
    /** The maximum time to process a passenger. */
    private static int maxProcessingTime;
    
    /** The sequence number for passengers. */
    private static int idNum = 0;
    
    /** A random number generator instance */
    private static Random random = new Random();
    
    /** Create a new passenger.
	@param arrivalTime The time this passenger arrives */
    public Passenger(int arrivalTime) {
	this.arrivalTime = arrivalTime;
	processingTime = 1 + random.nextInt(maxProcessingTime);
	passengerId = idNum++;
    }
    
    /** Get the arrival time.
	@return The arrival time */
    public int getArrivalTime() {
	return arrivalTime;
    }
    
    /** Get the processing time.
	@return The processing time */
    public int getProcessingTime() {
	return processingTime;
    }
    
    /** Get the passenger ID.
	@return The passenger ID */
    public int getId() {
	return passengerId;
    }
    
    /** Set the maximum processing time
	@param maxProcessingTime The new value */
    public static void setMaxProcessingTime(int maxProcessTime) {
	maxProcessingTime = maxProcessTime;
    }
}
/*</listing>*/
