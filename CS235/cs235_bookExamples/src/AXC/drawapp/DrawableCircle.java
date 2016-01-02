package AXC.drawapp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.Icon;

public class DrawableCircle extends DrawableShape {
    // Data fields

    private static final String NAME = "Circle";

    // Methods
    // Constructors
    public DrawableCircle(int rad,
            Point poi, Color bor, Color in) {
        super(poi, bor, in, new Circle(rad));
    }

    public DrawableCircle() {
        super(new Circle());
    }

    // Operations
    @Override
    public Drawable newInstance() {
        return new DrawableCircle(0, new Point(0, 0),
                borderColor,
                interiorColor);
    }

    @Override
    public void drawMe(Graphics g) {
        BoundingRectangle br = getBoundingRectangle();
        g.setColor(interiorColor);
        g.fillOval(br.x, br.y, br.w, br.h);
        g.setColor(borderColor);
        g.drawOval(br.x, br.y, br.w, br.h);
        stretchableOutlineDrawn = false;
    }

    /*<exercise chapter="C" section="7" type="programming" number="1">*/
    @Override
    public void stretchMe(Graphics g, Point stretchPoint,
            Color background) {
        g.setColor(borderColor);
        g.setXORMode(background);
        if (stretchableOutlineDrawn) {
            BoundingRectangle br = getBoundingRectangle();
            g.drawOval(br.x, br.y, br.w, br.h);
        }
        double deltaX = pos.x - stretchPoint.x;
        double deltaY = pos.y - stretchPoint.y;
        ((Circle) theShape).setRadius((int) Math.sqrt(deltaX * deltaX
                + deltaY * deltaY));
        BoundingRectangle br = getBoundingRectangle();
        g.drawOval(br.x, br.y, br.w, br.h);
        stretchableOutlineDrawn = true;
        g.setPaintMode();
    }
    /*</exercise>*/

    @Override
    public String toString() {
        return "Drawable " + theShape + super.toString();
    }

    // Accessors:
    private double getRadius() {
        return ((Circle) theShape).getRadius();
    }

    /*<exercise chapter="C" section="6" type="programming" number="1">*/
    @Override
    public int getWidth() {
        return (int)(2 * getRadius());
    }

    @Override
    public int getHeight() {
        return (int)(2 * getRadius());
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Icon getIcon(int size) {
        ((Circle) theShape).setRadius(size / 2);
        pos.x = size / 2;
        pos.y = size / 2;
        return new DrawableFigureIcon(this);
    }
    /*</exercise>*/

    private BoundingRectangle getBoundingRectangle() {
        BoundingRectangle result = new BoundingRectangle();
        int radius = (int) getRadius();
        result.x = pos.x - radius;
        result.y = pos.y - radius;
        result.w = 2 * radius;
        result.h = 2 * radius;
        return result;
    }

    private static class BoundingRectangle {
        private int x;
        private int y;
        private int w;
        private int h;
    }
}

