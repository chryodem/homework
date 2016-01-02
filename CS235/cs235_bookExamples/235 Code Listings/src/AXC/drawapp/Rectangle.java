/** Rectangle.java
 *  @author Koffman & Wolfgang
 *  Represents a rectangle.
 *  Implements ShapeInt.
 */
package AXC.drawapp;

import java.util.Scanner;

public class Rectangle extends Shape implements ShapeInt {

    // Data Fields
    private int width;
    private int height;

    // Methods
    // Constructors
    public Rectangle() {
        super("Rectangle");
    }

    public Rectangle(int wid, int hei) {
        super("Rectangle");
        width = wid;
        height = hei;
    }

    // Accessors:
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double computeArea() {
        return height * width;
    }

    public double computePerimeter() {
        return 2 * (height + width);
    }

    // Mutators
    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    /** Read the parameters of the rectangle */
    public void readShapeData() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the width of the Rectangle:");
        width = scan.nextInt();
        System.out.println("Enter the height of the Rectangle:");
        height = scan.nextInt();
    }

    @Override
    public String toString() {
        return super.toString() + ": width is "
                + width + ", height is " + height;
    }
}

