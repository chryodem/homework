/*<listing chapter="C" number="14">*/
package AXC;

/** VolumeConverter is a class with static methods
 *  that convert between gallons and liters.
 *  @author Koffman & Wolfgang
 */
public class VolumeConverter {

    /** The number of liters in a gallon.
     */
    private static final double LITERS_PER_GALLON = 3.785411784;

    /** Convert a value in liters to gallons.
     *  @param liters The value in liters
     *  @return The value in gallons
     */
    public static double toGallons(double liters) {
        return liters / LITERS_PER_GALLON;
    }

    /** Convert a value in gallons to liters.
     *  @param gallons The value in gallons
     *  @return The value in liters
     */
    public static double toLiters(double gallons) {
        return gallons * LITERS_PER_GALLON;
    }
}
/*</listing>*/