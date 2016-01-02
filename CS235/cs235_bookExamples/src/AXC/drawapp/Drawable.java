/** Drawable.java
 *  @author Koffman & Wolfgang
 */
package AXC.drawapp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.Icon;

public abstract class Drawable {
    // Data Fields

    protected Point pos = new Point(0, 0);   // position on screen
    protected Color borderColor = Color.black;
    protected Color interiorColor = Color.white;
    protected boolean stretchableOutlineDrawn;

    // Constructors

    public Drawable() {}

    public Drawable(Point p, Color b, Color i) {
        pos = p;
        borderColor = b;
        interiorColor = i;
    }

    // Abstract Methods
    public abstract int getWidth();

    public abstract int getHeight();

    public abstract void drawMe(Graphics g);

    public abstract Icon getIcon(int size);

    public abstract String getName();

    public abstract Drawable newInstance();

    public abstract void stretchMe(Graphics g,
            Point stretchPoint,
            Color background);
    // Accesors

    public Point getPoint() {
        return pos;
    }

    // Mutators
    public void setBorderColor(Color bor) {
        borderColor = bor;
    }

    public void setInteriorColor(Color inter) {
        interiorColor = inter;
    }

    public void setPoint(Point p) {
        pos = p;
    }

    public void setPoint(int x, int y) {
        pos = new Point(x, y);
    }

    @Override
    public String toString() {
        return "\nx coordinate is " + pos.x
                + ", y coordinate is " + pos.y
                + "\nborder color is " + borderColor
                + "\ninterior color is " + interiorColor;
    }
}

