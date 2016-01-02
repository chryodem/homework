/*<listing chapter="A" section="4">*/
/** A class for displaying square roots.
 *  @author Koffman and Wolfgang
 */
public class SquareRoots {

    public static void main(String[] args) {
        System.out.println("n \tsquare root");
        for (int n = 1; n <= 10; n++) {
            System.out.println(n + "\t" + Math.sqrt(n));
        }
    }
}
/*</listing>*/