package CH08;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/** This program illustrates the problem of the
 *  Dutch National Flag as described by Dijkstra
 *  @author Koffman and Wolfgang */
public class DutchNationalFlag extends JFrame {

    /** The width of the flag */
    private static final int WIDTH = 323;
    /** The height of the flag */
    private static final int HEIGHT = 198;
    /** The flag */
    private Flag theFlag;

    /** Construct the frame */
    private DutchNationalFlag() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        theFlag = new Flag(WIDTH, HEIGHT);
        theFlag.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        getContentPane().add(theFlag, BorderLayout.CENTER);
        JButton sortButton = new JButton("SORT");
        sortButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    theFlag.sort();
                }
            }
                                     );
        getContentPane().add(sortButton, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    /** Main method
     *  @param args -- ignored */
    public static void main(String[] args) {
        DutchNationalFlag dnf = new DutchNationalFlag();
    }
}
