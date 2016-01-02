/*<listing chapter="C" number="3">*/
package AXC;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/** A CirclePanel will contain a circle and a button.
 *  @author Koffman & Wolfgang
 **/
public class CirclePanel extends JPanel {

    // Data Fields
    /** The button object */
    JButton onOffButton;
    /** The Circle object */
    MyCircle theCircle;

    // Constructor
    /** Construct a CirclePanel object. */
    public CirclePanel(int size) {
        setLayout(new BorderLayout());
        theCircle = new MyCircle(size);
        onOffButton = new JButton("On / Off");
        onOffButton.addActionListener(new ToggleState());
        add(theCircle, BorderLayout.CENTER);
        add(onOffButton, BorderLayout.SOUTH);
    }

    // Inner Class
    /** The action listener for the button. */
    private class ToggleState
            implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            theCircle.toggleState();
        }
    }
}
/*</listing>*/
