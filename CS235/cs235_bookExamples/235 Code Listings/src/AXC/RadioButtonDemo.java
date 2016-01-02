/*<listing chapter="C" number="10">*/
package AXC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/** RadioButtonDemo generates a simple demonstration of
 *  radio buttons.
 *  @author Koffman & Wolfgang
 **/
public class RadioButtonDemo extends JFrame {

    // Data Fields
    String[] selections = {"Bacon", "Ham", "Sausage"};
    JRadioButton[] radioButtons = new JRadioButton[selections.length];
    ButtonGroup buttonGroup;

    // Main Method
    public static void main(String args[]) {
        // Create a RadioButtonDemo object.
        JFrame aFrame = new RadioButtonDemo();
        // Show it.
        aFrame.setVisible(true);
    }
    // Constructor
    public RadioButtonDemo() {
        setTitle("RadioButtonDemo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Create a JPanel to be used as the content pane.
        JPanel aPanel = new JPanel();
        aPanel.setLayout(new BoxLayout(aPanel, BoxLayout.X_AXIS));
        // Create a button group for the buttons.
        buttonGroup = new ButtonGroup();
        // Create radio buttons and add them to the panel.
        // Also add them to the button group.
        ActionListener newSelection = new SelectionChangeMade();
        for (int i = 0; i < selections.length; i++) {
            radioButtons[i] = new JRadioButton(selections[i]);
            radioButtons[i].getModel().setActionCommand(selections[i]);
            radioButtons[i].addActionListener(newSelection);
            buttonGroup.add(radioButtons[i]);
            aPanel.add(radioButtons[i]);
        }
        setContentPane(aPanel);
        pack();
    }

    // Action Listener Classes
    private class SelectionChangeMade implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String choice =
                    buttonGroup.getSelection().getActionCommand();
            System.out.println(choice + " is selected");
        }
    }
}
/*</listing>*/
