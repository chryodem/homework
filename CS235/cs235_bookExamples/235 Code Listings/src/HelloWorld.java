/*<listing chapter="A" section="1">*/
import javax.swing.*;

/** A HelloWorld class.
 *  @author Koffman and Wolfgang
 */
public class HelloWorld {

    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog("Enter your name");
        JOptionPane.showMessageDialog(null, "Hello " + name
                + ", welcome to Java!");
    }
}
/*</listing>*/

