/*<listing chapter="AXC" number="6">*/
package AXC;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/** FlowLayoutDemo generates a simple frame that shows how components
 *  are arranged using FlowLayout.
 *  @author Koffman & Wolfgang
 **/
public class FlowLayoutDemo extends JFrame {

    // Main Method
    public static void main(String args[]) {
        // Construct a FlowLayoutDemo object.
        JFrame frame = new FlowLayoutDemo();
        // Display the frame.
        frame.setVisible(true);
    }

    // Constructor
    public FlowLayoutDemo() {
        setTitle("FlowLayoutDemo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Create a JPanel to hold some labels.
        JPanel aPanel = new JPanel();
        // Define the preferred size for the labels.
        Dimension preferredSize = new Dimension(20, 20);
        Border blackBorder =
                BorderFactory.createLineBorder(Color.BLACK);
        // Create some labels and add them to the panel.
        for (int i = 0; i < 25; i++) {
            JLabel aLabel =
                    new JLabel(Integer.toString(i), JLabel.CENTER);
            aLabel.setPreferredSize(preferredSize);
            aLabel.setBorder(blackBorder);
            aPanel.add(aLabel);
        }
        setContentPane(aPanel);
        pack();
    }
}
/*</listing>*/
