/** DrawableRectangle.java
 *  Represents a drawable rectangle.
 *  @author Koffman and Wolfgang
 */
package AXC.drawapp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.Icon;

public class DrawableRectangle extends DrawableShape {

    // Data Fields
    /** The name of this shape */
    private static final String NAME = "Rectangle";

    // Constructors
    /** Construct a DrawableRectangle with the specified values
     *  @param wid The width
     *  @param hei The height
     *  @param poi The coordinates of the upper left corner
     *  @param bor The border color
     *  @param in The interior color
     */
    public DrawableRectangle(int wid, int hei,
            Point pio, Color bor, Color in) {
        super(pio, bor, in, new Rectangle(wid, hei));
    }

    /** Construct a default DrawableRectangle
     */
    public DrawableRectangle() {
        super(new Rectangle());
    }

    /** Draw this shape
     *  @param g The graphics context
     */
    public void drawMe(Graphics g) {
        g.setColor(interiorColor);
        BoundingRectangle br = getBoundingRectangle();
        g.fillRect(br.x, br.y, br.w, br.h);
        g.setColor(borderColor);
        g.drawRect(br.x, br.y, br.w, br.h);
        stretchableOutlineDrawn = false;
    }

    /*<exercise chapter="C" section="7" type="programming" number="1">*/
    /** Erases the previously drawn outline, re-sizes the figure
     *  based upon the stretch point, and draws the new outline
     *  @param g The graphics context
     *  @param stretchPoint The current mouse poisition
     *  @param background The current background color
     */
    public void stretchMe(Graphics g, Point stretchPoint, Color background) {
        int x, y, w, h;
        g.setColor(borderColor);
        g.setXORMode(background);
        if (stretchableOutlineDrawn) {
            BoundingRectangle br = getBoundingRectangle();
            g.drawRect(br.x, br.y, br.w, br.h);
        }
        Rectangle rectangle = (Rectangle) theShape;
        rectangle.setWidth(stretchPoint.x - pos.x);
        rectangle.setHeight(stretchPoint.y - pos.y);
        BoundingRectangle br = getBoundingRectangle();
        g.drawRect(br.x, br.y, br.w, br.h);
        stretchableOutlineDrawn = true;
        g.setPaintMode();
    }
    /*</exercise>*/

    /** Create a new instance of a DrawableRectangle
     *  @return a new instance of a DrawableRectangle
     */
    @Override
    public Drawable newInstance() {
        return new DrawableRectangle(0, 0, new Point(0, 0),
                borderColor, interiorColor);
    }

    /*<exercise chapter="C" section="6" type="programming" number="1">*/
    /** Get the width of the rectangle
     *  @return The width of the rectangle
     */
    @Override
    public int getWidth() {
        return ((Rectangle) theShape).getWidth();
    }

    /** Get the height of the rectangle
     *  @return the height of the rectangle
     */
    @Override
    public int getHeight() {
        return ((Rectangle) theShape).getHeight();
    }

    /** Create an icon of a DrawableRectanle. The icon is a Drawable
     *  rectangle of width equal to the size and height equal to 3/4
     *  the size, and whose origin is at 0, 0
     *  @param size The size of the bounding rectangle.
     *  @return an icon of a DrawableRectangle
     */
    @Override
    public Icon getIcon(int size) {
        Rectangle rectangle = (Rectangle) theShape;
        rectangle.setWidth(size);
        rectangle.setHeight(3 * size / 4);
        setPoint(new Point(0, 0));
        return new DrawableFigureIcon(this);
    }

    /** Get the name of this shape
     *  @return the name of this shape
     */
    @Override
    public String getName() {
        return NAME;
    }
    /*</exericise>*/

    /** Create a string representation of this shape
     *  @return a string representation of this shape
     */
    @Override
    public String toString() {
        return "Drawable " + theShape + super.toString();
    }

    /** Get the bounding rectangle of this shape.
     *  The bounding rectangle has its origin at the upper left
     *  corner.
     *  @return the bounding rectangle of this shape
     */
    private BoundingRectangle getBoundingRectangle() {
        BoundingRectangle result = new BoundingRectangle();
        if (getWidth() < 0) {
            result.x = pos.x + getWidth();
            result.w = -getWidth();
        } else {
            result.x = pos.x;
            result.w = getWidth();
        }
        if (getHeight() < 0) {
            result.y = pos.y + getHeight();
            result.h = -getHeight();
        } else {
            result.y = pos.y;
            result.h = getHeight();
        }
        return result;
    }

    /** Static inner class to represent the bouding rectangle
     */
    private static class BoundingRectangle {

        /** The x-coordinate of the upper left corner */
        private int x;
        /** The y-coordinate of the upper left corner */
        private int y;
        /** The width */
        private int w;
        /** The height */
        private int h;
    }
}
