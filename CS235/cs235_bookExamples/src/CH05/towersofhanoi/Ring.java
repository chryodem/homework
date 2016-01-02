/**
   Towers of Hanoi
   @author Paul Wolfgang
*/

package CH05.towersofhanoi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;


/** A Ring object represents one of the rings
    in the Towers of Hanoi.
    @author Paul Wolfgang
*/
public class Ring {
    
    // Data Fields
    /** A table of colors to use to assign colors to the rings. */
    private static Color[] colors = new Color[] {
        new Color(  0,   0, 255),
        new Color(  0, 255, 255),
        new Color(  0, 255,   0),
        new Color(255, 255,   0),
        new Color(255,   0,   0),
        new Color(255,   0, 255),
        new Color(  0,   0, 127),
        new Color(  0, 127, 127),
        new Color(  0, 127,   0),
        new Color(127, 127,   0),
        new Color(127,   0,   0),
        new Color(127,   0, 127),
        new Color(127, 127, 255),
        new Color(127, 255, 255),
        new Color(127, 255, 127),
        new Color(255, 255, 127),
        new Color(255, 127, 127),
        new Color(255, 127, 255)
    };
    /** The size of the color table */
    private static final int NUM_COLORS = colors.length;
    /** Sequence index of the next ring to be generated */
    private static int nextIndex = 0;
    /** The x-coordinate of the top-left*/
    private int x;
    /** The y-coordinate of the top-left*/
    private int y;
    /** The diameter*/
    private int diam;
    /** The index for this ring */
    private int index;
    /** The color of this ring. */
    private Color theColor;
    /** The font for the number on the ring. Common to all rings*/
    private static Font font;
    /** The thickness of a ring.  Common to all rings. */
    private static int thick;

    // Constructor
    /** Constructs a ring.
        @param d The Diameter of the ring
        @param x0 The x-coordinate of the center of the ring.
        @param y0 The y-coordinate of the top of the ring.
    */
    public Ring(int d, int x0, int y0) {
        diam = d;
        x = x0;
        y = y0;
        theColor = colors[nextIndex * 7 % NUM_COLORS];
        index = nextIndex++;
    }

    // Methods
    /** Draw a ring.
        @param g2 The graphics environment.
    */
    public void draw(Graphics2D g2) {
        String seqno = Integer.toString(nextIndex - index);
        int xlo = x - diam / 2;
        int ylo = y;
        Rectangle recRing = new Rectangle(xlo, ylo, diam, thick);
        g2.setPaint(theColor);
        g2.fill(recRing);
        if (font == null) {
            createFont(g2, seqno);
        }
        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(seqno, context);
        int xmsg = x - (int) bounds.getWidth() / 2;
        g2.setPaint(Color.white);
        Rectangle box = new Rectangle(xmsg - 1, y, 
                                      (int) bounds.getWidth() + 2, 
                                      thick);
        g2.fill(box);
        g2.setPaint(Color.black);
        g2.setFont(font);
        g2.drawString(seqno, xmsg, y - (int) bounds.getY() + 2);
    }

    /** Move a ring from its current position to a new poisition
        @param newx The new x-coordinate
        @param newy The new y-coordinate
    */
    public void move(int newx, int newy) {
        x = newx;
        y = newy;
    }

    /** Change the y-coordinate  
     *  @param newy The new value of y */
    public void setY(int newy) {
        y = newy;
    }

    /** Set the diameter of the ring 
     *  @param diam The new value of the diameter */
    public void setDiam(int diam) {
        this.diam = diam;
    }

    /** Get the index 
     *  @return The current value of index */
    public int getIndex() {
        return index;
    }
        
    /** Reset the nextIndex number assigned to rings to zero.
     */
    public static void resetIndex() {
        nextIndex = 0;
    }

    /** Set the thickness of a ring.
     *  This invalidates the font used to label the rings.
     *  @param newThick The new value for thick
     */
    public static void setThick(int newThick) {
        thick = newThick;
        font = null;
    }

    /** Create a font that will fit nicely within the thickness of a ring 
     *  @param g2 The 2D graphics environment
     *  @param seqno The sequence number of this ring
     *  @return The generated font*/
    public static Font createFont(Graphics2D g2, String seqno) {
        if (font == null) { // if font not already defined.
            // Get the screen resolution in dot's per inch.
            Toolkit tk = Toolkit.getDefaultToolkit();
            int dotsPerInch = tk.getScreenResolution();
            // Compute a nominal font size in points
            int fontSize = thick * 72 / dotsPerInch;
            // Create a font that size
            font = new Font("SansSerif", Font.PLAIN, fontSize);
            // Find out how tall it really is.
            FontRenderContext context = g2.getFontRenderContext();
            Rectangle2D bounds = font.getStringBounds(seqno, context);
            // Compute a scale factor so that the height is the same as 
            //  the thickness
            double scaleFactor = thick / bounds.getHeight();
            // Apply this scale factor to the font.
            font = font.deriveFont(
                AffineTransform.getScaleInstance(scaleFactor, 
                                                 scaleFactor));
        }
        return font;
    }

}
