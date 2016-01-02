/*<exercise chapter="C" section="7" type="programming" number="2">*/
package AXC.drawapp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import javax.swing.Icon;

/**
 * Class to draw a rombus (diamond) that is enclosed in a bounding rectangle
 * @author Paul Wolfgang
 */
public class DrawableRombus extends DrawableShape {
    /* Data fields */
    private static String NAME = "Rombus";

    /* Methods */
    /* Constructors */
    public DrawableRombus(int w, int h,
            Point p, Color b, Color i) {
        super(p, b, i, new Rombus(w, h));
    }

    public DrawableRombus() {
        super(new Rombus());
    }

    // Methods
    @Override
    public Drawable newInstance() {
        return new DrawableRombus(0, 0, new Point(0, 0),
                borderColor,
                interiorColor);
    }

    private Polygon makePolygon() {
        Rombus rombus = (Rombus) theShape;
        Polygon p = new Polygon();
        p.addPoint(pos.x, pos.y - rombus.getHeight()/2);
        p.addPoint(pos.x + rombus.getWidth()/2, pos.y);
        p.addPoint(pos.x + rombus.getWidth(), pos.y - rombus.getHeight()/2);
        p.addPoint(pos.x + rombus.getWidth()/2, pos.y - rombus.getHeight());
        return p;
    }

    @Override
    public void drawMe(Graphics g) {
        Polygon p = makePolygon();
        g.setColor(interiorColor);
        g.fillPolygon(p);
        g.setColor(borderColor);
        g.drawPolygon(p);
    }

    @Override
    public void stretchMe(Graphics g, Point stretchPoint, Color background) {
        g.setColor(borderColor);
        g.setXORMode(background);
        Polygon p = makePolygon();
        if (stretchableOutlineDrawn) {
            g.drawPolygon(p);
        }
        Rombus rombus = (Rombus) theShape;
        rombus.setWidth(stretchPoint.x - pos.x);
        rombus.setHeight(pos.y - stretchPoint.y);
        p = makePolygon();
        g.drawPolygon(p);
        stretchableOutlineDrawn = true;
    }

    @Override
    public String toString() {
        return "Drawable " + theShape + super.toString();
    }

    //Accessors
    @Override
    public int getWidth() {
        return ((Rombus) theShape).getWidth();
    }

    @Override
    public int getHeight() {
        return ((Rombus) theShape).getHeight();
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Icon getIcon(int size) {
        Rombus rombus = (Rombus) theShape;
        rombus.setWidth(size);
        rombus.setHeight(size);
        pos.x = 0;
        pos.y = size;
        return new DrawableFigureIcon(this);
    }
}
/*</exercise>*/
