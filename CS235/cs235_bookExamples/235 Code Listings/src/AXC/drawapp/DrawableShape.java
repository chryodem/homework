package AXC.drawapp;

import java.awt.Color;
import java.awt.Point;

/**
 * Abstract class to represent a drawable shape
 * @author Koffman and Wolfgang
 */
public abstract class DrawableShape extends Drawable implements ShapeInt {

    protected ShapeInt theShape;

        // Constructors
    public DrawableShape(Point p, Color b, Color i, ShapeInt aShape) {
        super(p, b, i);
        theShape = aShape;
    }

    public DrawableShape(ShapeInt aShape) {
        theShape = aShape;
    }

    // Delegated Methods
    @Override
    public double computeArea() {
        return theShape.computeArea();
    }

    @Override
    public double computePerimeter() {
        return theShape.computePerimeter();
    }

    @Override
    public void readShapeData() {
        theShape.readShapeData();
    }

}
