/*<listing chapter="C" number="7">*/
package AXC;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/** Demonstration of the Box Layout.
 *   @author Koffman & Wolfgang
 * */
public class BuildBoxLayout extends JFrame {

    public static void main(String[] args) {
        JFrame frame = new BuildBoxLayout();
    }

    public BuildBoxLayout() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Create a panel to hold the buttons.
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // Create buttons and add them to the panel.
        // Add/Change Entry
        JButton addEntryButton = new JButton("Add/Change Entry");
        panel.add(addEntryButton);
        // Look Up Entry
        JButton lookupEntryButton = new JButton("Look Up Entry");
        panel.add(lookupEntryButton);
        // Remove Entry
        JButton removeEntryButton = new JButton("Remove Entry");
        panel.add(removeEntryButton);
        // Save Directory
        JButton saveDirectoryButton =
                new JButton("Save Directory");
        panel.add(saveDirectoryButton);
        // Exit
        JButton exitButton = new JButton("Exit");
        panel.add(exitButton);
        // Put the panel into the frame.
        getContentPane().add(panel);
        // Size the frame to hold the panel.
        pack();
        setVisible(true);
    }
}
/*</listing>*/
