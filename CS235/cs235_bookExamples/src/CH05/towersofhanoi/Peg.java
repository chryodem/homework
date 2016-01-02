/**
   Towers of Hanoi
   @author Paul Wolfgang
*/

package CH05.towersofhanoi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/** A Peg object represents the peg that holds the stack 
 *  of rings in the Towers of Hanoi.
 *  @author Paul Wolfgang
*/
public class Peg {

    // Data Fields
    /** The label for the peg */
    private String lbl;
    /** The array of rings. */
    private Ring[] theRings;
    /** index of the top ring */
    private int top;
    /** x-coordinate of the base of the peg */
    private int xBase;
    /** y-coordinate of the base of the peg */
    private int yBase;
    /** The thickness of a ring */
    private int ringThick;
    /** The thickness of the peg */
    private int pegThick;
    /** The height of the peg. */
    private int pegHeight;
    /** The number of rings */
    private int numRings;

    // Constructor
    /** Construct a <code>Peg</code>
        @param t The thickness
        @param h The height
        @param x0 The x-coordinate of the center
        @param y0 The y-coordinate of the bottom
        @param lbl The label for the peg
        @param maxDiam The maximum diamenter of a ring
        @param minDiam The minimum diameter of a ring
        @param numRings The number of rings
        @param full This peg should be initialized to contain the rings
    */
    public Peg(int t, int h, int x0, int y0, String lbl, int maxDiam, 
               int minDiam, int numRings, boolean full) {
        this.lbl = lbl;
        this.numRings = numRings;
        setSizes(t, h, x0, y0);
        top = 0;
        theRings = new Ring[numRings];
        int deltaRingDiam = (maxDiam - minDiam) / numRings;
        if (full) {
            Ring.resetIndex();
            for (int i = 0; i != numRings; ++i) {
                theRings[i] = new Ring(maxDiam - i * deltaRingDiam,
                                        xBase,
                                        yBase - (i + 1) * ringThick);
            }
            top = numRings;
        }
    }

    // Methods
    /** Change the size of a peg 
     *  @param t the peg's thickness
     *  @param h the peg's height
     *  @param x0 x-coordinate of the center
     *  @param y0 y-coordinate of the bottom
     *  @param maxDiam The maximum diameter of a ring
     *  @param minDiam The minimum diameter of a ring
     */
    public void changeSize(int t, int h, int x0, int y0, int maxDiam, 
                           int minDiam) {
        setSizes(t, h, x0, y0);
        int deltaRingDiam = (maxDiam - minDiam) / numRings;
        for (int i = 0; i != top; ++i) {
            theRings[i].setY(yBase - (i + 1) * ringThick);
            theRings[i].setDiam
                (maxDiam - deltaRingDiam * theRings[i].getIndex());
        }
    }

    /** Set the sizes of the pegs and rings
     *  @param t the peg's thickness
     *  @param h the peg's height
     *  @param x0 x-coordinate of the center
     *  @param y0 y-coordinate of the bottom
     */
    private void setSizes(int t, int h, int x0, int y0) {
        pegThick = t;
        pegHeight = h;
        ringThick = pegHeight / (numRings + 2);
        pegHeight = ringThick * numRings;
        xBase = x0;
        yBase = y0;
        Ring.setThick(ringThick);
    }

    /** Remove the top ring
        @return The top ring
    */
    public Ring pop() {
        return theRings[--top];
    }

    /** Place a ring on the top
        @param g2 The graphics environment
        @param aRing The ring to be placed on top.
    */
    public void push(Ring aRing) {
        aRing.move(xBase, yBase - (top + 1) * ringThick);
        theRings[top++] = aRing;
    }

    /** Draw the peg and its rings
        @param g2 The graphics envionment
    */
    public void draw(Graphics2D g2) {
        int xlo = xBase - pegThick / 2;
        int ylo = yBase - pegHeight;
        Rectangle rect = new Rectangle(xlo, ylo, pegThick, pegHeight);
        g2.setPaint(Color.black);
        g2.fill(rect);
        for (int i = 0; i != top; ++i) {
            theRings[i].draw(g2);
        }
        Font font = Ring.createFont(g2, lbl);
        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(lbl, context);
        int xmsg = xBase - (int) bounds.getWidth() / 2;
        int ymsg = ylo - (int) (0.5 * (double) ringThick);
        g2.setFont(font);
        g2.drawString(lbl, xmsg, ymsg);
    }

}
