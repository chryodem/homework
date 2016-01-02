package CH08;

import java.awt.Color;
import java.awt.Graphics;

/** The Thread class represents a single thread in the
 *  DutchNationalFlag */
public class Thread {

    /** The color of the thread */
    private Color color;

    // Constructor
    /** Construct a Thread of the given color 
     *  @param color - The color of this thread */
    public Thread(Color color) {
        this.color = color;
    }

    // Accessor
    /** Return the color of this thread
     *  @return The color of this thread */
    public Color getColor() {
        return color;
    }

    // Methods
    /** Draw the thread
     *  @param y - The y-coordinate
     *  @param width - The width of the flag
     *  @param g - The graphics environment */
    public void draw(int y, int width, Graphics g) {
        g.setColor(color);
        g.drawLine(0, y, width, y);
    }
}
