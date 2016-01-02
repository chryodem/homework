/** Program to illustrate the Towers of Hanoi.
 *  @author Paul Wolfgang
 */

package CH05.towersofhanoi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
   JFrame to hold the board and control the game
*/
public class TowersOfHanoi extends JFrame {

    // Data Fields
    /** A reference to the board */
    private Board theBoard;
    /** The number of rings */
    private int numRings = 20;
    /** The screen width */
    private int screenWidth;
    /** The screen height */
    private int screenHeight;


    /** The constructor determines the initial frame size based upon 
     *  the total screen size.  It then Defines the Command menu.
     *  Finally it creates and sets the content pane. */
    public TowersOfHanoi() {
	setExtendedState(MAXIMIZED_BOTH);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Towers of Hanoi");
        JMenu commandMenu = new JMenu("Command");
        commandMenu.add(new SetNumberOfRingsAction());
        commandMenu.add(new PlaySlowlyAction());
        commandMenu.add(new PlayAction());
        commandMenu.add(new SingleStepAction());
        commandMenu.add(new PauseAction());
        commandMenu.add(new ResumeAction());
        commandMenu.add(new StopAction());
        commandMenu.add(new ExitAction());
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(commandMenu);
        TowersPanel panel = new TowersPanel();
        setContentPane(panel);
        addMouseListener(new MouseClicked());
	setVisible(true);
    }

    /** If the board has not been created, create it.
     *  Otherwise, if the board is not currently initialized,
     *  Stop any game in progress, and create a new board.
     */
    private void resetBoard() {
        if (theBoard != null && !theBoard.isInitialized()) {
            theBoard.setStopped();
            theBoard = null;
        }
        if (theBoard == null) {
            theBoard = new Board(screenWidth, screenHeight, 
                                 numRings, this);
        }
        // Force a repaint
        repaint();
    }


    /** The main method. 
     *  @param args Not Used.*/
    public static void main(String[] args) {
	new TowersOfHanoi();
    }

    // Action Classes for each of the commands.
    /** Encapsulates the action performed when the set number
     *  of rings command is selected from the menu. Sets the 
     *  number of rings.  The board is re-created in
     *  response to this command. */
    private class SetNumberOfRingsAction extends AbstractAction {
        /** Constructor to set the title. */
        public SetNumberOfRingsAction() {
            super("Set Number of Rings");
        }
        /** The action performed is as follows:
         *  1) A dialog is displayed requesting the new number of rings
         *  2) The board is contructed with the specified number of
         *  rings.
         *  @param event Not Used. */
        @Override
        public void actionPerformed(ActionEvent event) {
            String reply = 
                JOptionPane.showInputDialog("Set Number of Rings");
            try {
                numRings = Integer.parseInt(reply);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, 
                                              "Invalid number format",
                                              "Error", 
                                           JOptionPane.ERROR_MESSAGE);
            }
            if (theBoard != null) {
                theBoard.setStopped();
            }
            // Delete the old board
            theBoard = null;
            // Create a new one
            resetBoard();
        }
    }

    /** Encapsulates the action performed when the play
     *  slowly command is selected from the menu. The 
     *  game is played with a 500ms pause between moves. */
    private class PlaySlowlyAction extends AbstractAction {
        /** Constructor to set the title. */
        public PlaySlowlyAction() {
            super("Play Slowly");
        }
        /** The board is reset, the sleep duration set to
         *  500 msec, and the play is started.
         *  @param event Not Used. */
        @Override
        public void actionPerformed(ActionEvent event) {
            resetBoard();
            theBoard.setSleepDur(500);
            new Thread(theBoard).start();
        }
    };

    /** Encapsulates the action performed when the play
     *  command is selected from the menu.  The game is 
     *  played zero pause between moves. */
    private class PlayAction extends AbstractAction {
        /** Constructor to set the title. */
        public PlayAction() {
            super("Play");
        }
        /** The board is reset, the sleep duration set to
         *  zero, and the play is started.
         *  @param event Not Used. */
        @Override
        public void actionPerformed(ActionEvent event) {
            resetBoard();
            theBoard.setSleepDur(0);
            new Thread(theBoard).start();
        }
    };

    /** Encapsulates the action performed when the single 
     *  step command is selected from the menu. */
    private class SingleStepAction extends AbstractAction {
        /** Constructor to set the title. */
        public SingleStepAction() {
            super("Single Step");
        }
        /** The board is reset, the sleep duration is set to
         *  zero, single step mode it set true, and the
         *  play is started. 
         *  @param event Not Used */
        @Override
        public void actionPerformed(ActionEvent event) {
            resetBoard();
            theBoard.setSleepDur(0);
            theBoard.setSingleStep();
            new Thread(theBoard).start();
        }
    };

    /** Encapsulates the action performed when the pause
     *  command is selected from the menu. */
    private class PauseAction extends AbstractAction {
        /** Constructor to set the title. */
        public PauseAction() {
            super("Pause");
        }
        /** The pause flag is set.
         *  @param event Not Used. */
        @Override
        public void actionPerformed(ActionEvent event) {
            theBoard.pause();
        }
    };

    /** Encapsulates the action performed when the resume
     *  command is selected from the menu. */
    private class ResumeAction extends AbstractAction {
        /** Constructor to set the title. */
        public ResumeAction() {
            super("Resume");
        }
        /** The release method is called 
         *  @param event Not Used. */
        @Override
        public void actionPerformed(ActionEvent event) {
            theBoard.release();
        }
    };

    /** Encapsulates the action performed when the stop
     *  command is selected from the menu. */
    private class StopAction extends AbstractAction {
        /** Constructor to set the title. */
        public StopAction() {
            super("Stop");
        }
        /** The stopped flag is set
         *  @param event Not Used */
        @Override
        public void actionPerformed(ActionEvent event) {
            theBoard.setStopped();
        }
    }


    /** Encapsulates the action performed when the mouse
     *  is clicked. Release the paused game. */
    private class MouseClicked extends MouseAdapter {
        /** Call release to advance to the next step 
         *  @param e Not Used. */
        @Override
        public void mouseClicked(MouseEvent e) {
            theBoard.step();
        }
    }

    /** Encapsulates the action performed when the exit
     *  command is selected from the menu. */
    private class ExitAction extends AbstractAction {
        /** Constructor to set the title. */
        public ExitAction() {
            super("Exit");
        }
        /** If the game is in progress, it it stopped.
         *  The frame is then hidden, and disposed
         *  and the program exits.
         *  @param event Not Used */
        @Override
        public void actionPerformed(ActionEvent event) {
            if (theBoard != null && !theBoard.isInitialized()) {
                theBoard.setStopped();
            }
            setVisible(false);
            dispose();
            System.exit(0);
        }
    }

    /** The panel that is used for the content pane. */
    class TowersPanel extends JPanel {
        /** Construct a TowersPanel and set the background to 
         *  white. */
        public TowersPanel() {
            setBackground(Color.white);
        }
        /** Draw the board 
         *  @param g The Graphics object. */
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            Dimension mySize = getSize();
            screenWidth = mySize.width;
            screenHeight = mySize.height;
            if (theBoard == null) {
                theBoard = new Board(screenWidth, screenHeight, 
                                     numRings, TowersOfHanoi.this);
            } else {
                theBoard.resize(screenWidth, screenHeight);
            }
            theBoard.draw(g2);
        }
    }
}
