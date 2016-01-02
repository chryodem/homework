/*<listing chapter="C" number="13">*/
package AXC;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/** VolumeConverterGUI is a GUI application that converts
 *  values in gallons to liters and vice versa.
 *  @author Koffman & Wolfgang
 */
public class VolumeConverterGUI
        extends JFrame {

    // Data Fields
    /** Text field to hold gallons value */
    private JTextField gallonsField;
    /** Text field to hold liters value */
    private JTextField litersField;

    // Main Method
    /** Create an instance of the application and show it.
     *  @param args Command Line Arguments � not used
     */
    public static void main(String[] args) {
        JFrame frame = new VolumeConverterGUI();
        frame.setVisible(true);
    }

    // Constructor
    /** Construct the components and add them to the frame. */
    public VolumeConverterGUI() {
        super("Liquid Volume Converter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Get a reference to the content pane.
        Container contentPane = getContentPane();
        // Set the layout manager to grid layout.
        contentPane.setLayout(new GridLayout(2, 2));
        contentPane.add(new JLabel("Gallons"));
        gallonsField = new JTextField(15);
        gallonsField.addActionListener(new NewGallonsValue());
        contentPane.add(gallonsField);
        contentPane.add(new JLabel("Liters"));
        litersField = new JTextField(15);
        litersField.addActionListener(new NewLitersValue());
        contentPane.add(litersField);
        // Size the frame to fit.
        pack();
    }

    // Inner Classes
    /** Class to respond to new gallons value. */
    private class NewGallonsValue implements ActionListener {

        /** Convert the gallons value to corresponding liters value.
         *  @param e ActionEvent object - not used
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double gallonsValue =
                        Double.parseDouble(gallonsField.getText());
                double litersValue =
                        VolumeConverter.toLiters(gallonsValue);
                litersField.setText(Double.toString(litersValue));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Invalid Number Format",
                        "",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /** Class to respond to new liters value. */
    private class NewLitersValue implements ActionListener {

        /** Convert the liters value to corresponding gallons value.
         *  @param e ActionEvent object - not used
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double litersValue =
                        Double.parseDouble(litersField.getText());
                double gallonsValue =
                        VolumeConverter.toGallons(litersValue);
                gallonsField.setText(Double.toString(gallonsValue));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Invalid Number Format",
                        "",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
/*</listing>*/
