/** DrawableTriangle.java
 *  @author Koffman & Wolfgang
 *  Represents a drawable triangle.
 */
package AXC.drawapp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import javax.swing.Icon;

public class DrawableTriangle extends DrawableShape {

    /* Data fields */
    private static String NAME = "Triangle";

    /* Methods */
    /* Constructors */
    public DrawableTriangle(int bas, int h,
            Point p, Color b, Color i) {
        super(p, b, i, new RtTriangle(bas, h));
    }

    public DrawableTriangle() {
        super(new RtTriangle());
    }

    // Methods
    @Override
    public Drawable newInstance() {
        return new DrawableTriangle(0, 0, new Point(0, 0),
                borderColor,
                interiorColor);
    }

    private Polygon makePolygon() {
        RtTriangle triangle = (RtTriangle) theShape;
        Polygon rtTri = new Polygon();
        rtTri.addPoint(pos.x, pos.y);
        rtTri.addPoint(pos.x, pos.y - triangle.getHeight());
        rtTri.addPoint(pos.x + triangle.getBase(), pos.y);
        return rtTri;
    }

    @Override
    public void drawMe(Graphics g) {
        Polygon rtTri = makePolygon();
        g.setColor(interiorColor);
        g.fillPolygon(rtTri);
        g.setColor(borderColor);
        g.drawPolygon(rtTri);
    }

    /*<exercise chapter="C" section="7" type="programming" number="1">*/
    @Override
    public void stretchMe(Graphics g, Point stretchPoint, Color background) {
        g.setColor(borderColor);
        g.setXORMode(background);
        Polygon tri = makePolygon();
        if (stretchableOutlineDrawn) {
            g.drawPolygon(tri);
        }
        RtTriangle triangle = (RtTriangle) theShape;
        triangle.setBase(stretchPoint.x - pos.x);
        triangle.setHeight(pos.y - stretchPoint.y);
        tri = makePolygon();
        g.drawPolygon(tri);
        stretchableOutlineDrawn = true;
    }
    /*</exercise>*/

    @Override
    public String toString() {
        return "Drawable " + theShape + super.toString();
    }

    /*<exercise chapter="C" section="6" type="programming" number="1">*/
    /** Accessors */
    @Override
    public int getWidth() {
        return ((RtTriangle) theShape).getBase();
    }

    @Override
    public int getHeight() {
        return ((RtTriangle) theShape).getHeight();
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Icon getIcon(int size) {
        RtTriangle triangle = (RtTriangle) theShape;
        triangle.setBase(size);
        triangle.setHeight(size);
        pos.x = 0;
        pos.y = size;
        return new DrawableFigureIcon(this);
    }
    /*</exercise>*/

}
