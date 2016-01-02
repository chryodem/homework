/*<listing chapter="C" number="8">*/
package AXC;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/** GridLayoutDemo generates a simple frame that shows how components
 *  are arranged using Grid Layout.
 *  @author Koffman & Wolfgang
 **/
public class GridLayoutDemo extends JFrame {

    // Main Method
    public static void main(String args[]) {
        // Construct a GridLayoutDemo object.
        JFrame frame = new GridLayoutDemo();
        // Display the frame.
        frame.setVisible(true);
    }

    // Constructor
    public GridLayoutDemo() {
        setTitle("GridLayoutDemo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Create a JPanel to hold a grid.
        JPanel thePanel = new JPanel();
        thePanel.setLayout(new GridLayout(5, 10));
        Border blackBorder =
                BorderFactory.createLineBorder(Color.BLACK);
        // Create some labels and add them to the panel.
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                JLabel aLabel = new JLabel(Integer.toString(i) + ", "
                        + Integer.toString(j),
                        JLabel.CENTER);
                aLabel.setBorder(blackBorder);
                thePanel.add(aLabel);
            }
        }
        setContentPane(thePanel);
        pack();
    }
}
/*</listing>*/
