/*<listing chapter="C" number="5">*/
package AXC;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

/** BorderLayoutDemo generates a simple frame that shows the positions
 *  available in a BorderLayout.
 *  @author Koffman & Wolfgang
 **/
public class BorderLayoutDemo extends JFrame {

    // Main Method
    public static void main(String args[]) {
        // Construct a BorderLayoutDemo object.
        JFrame frame = new BorderLayoutDemo();
        // Display the frame.
        frame.setVisible(true);
    }

    // Constructor
    public BorderLayoutDemo() {
        // Set the title.
        setTitle("BorderLayoutDemo");
        // Set the default close operation to exit on close.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Create five labels: "North", "South", "East", "West",
        // and "Center".
        JLabel north = new JLabel("North", JLabel.CENTER);
        JLabel south = new JLabel("South", JLabel.CENTER);
        JLabel east = new JLabel("East", JLabel.CENTER);
        JLabel west = new JLabel("West", JLabel.CENTER);
        JLabel center = new JLabel("Center", JLabel.CENTER);
        // Place a black border around each label.
        Border blackBorder =
                BorderFactory.createLineBorder(Color.BLACK);
        north.setBorder(blackBorder);
        south.setBorder(blackBorder);
        east.setBorder(blackBorder);
        west.setBorder(blackBorder);
        center.setBorder(blackBorder);
        center.setPreferredSize(new Dimension(200, 200));
        // Add the labels to the content pane.
        Container contentPane = getContentPane();
        contentPane.add(north, BorderLayout.NORTH);
        contentPane.add(south, BorderLayout.SOUTH);
        contentPane.add(east, BorderLayout.EAST);
        contentPane.add(west, BorderLayout.WEST);
        contentPane.add(center, BorderLayout.CENTER);
        // Size the frame to fit.
        pack();
    }
}
/*</listing>*/
