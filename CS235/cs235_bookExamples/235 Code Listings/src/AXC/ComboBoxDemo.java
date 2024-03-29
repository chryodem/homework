/*<listing chapter="C" number="11">*/
package AXC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/** ComboBoxDemo generates a simple demonstration of a combo box.
 *  @author Koffman & Wolfgang
 **/
public class ComboBoxDemo extends JFrame {

    // Data Fields
    String[] selections = {"Bacon", "Ham", "Sausage"};
    JComboBox comboBox;

    // Main Method
    public static void main(String args[]) {
        // Create a ComboBoxDemo object.
        JFrame aFrame = new ComboBoxDemo();
        // Show it.
        aFrame.setVisible(true);
    }

    // Constructor
    public ComboBoxDemo() {
        setTitle("ComboBoxDemo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Create a JPanel to be used as the content pane.
        JPanel aPanel = new JPanel();
        // Create the combo box.
        comboBox = new JComboBox(selections);
        comboBox.addActionListener(new SelectionChangeMade());
        aPanel.add(comboBox);
        setContentPane(aPanel);
        pack();
    }

    // Action Listener Classes
    private class SelectionChangeMade implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String choice = (String) comboBox.getSelectedItem();
            System.out.println(choice + " is selected");
        }
    }
}
/*</listing>*/
